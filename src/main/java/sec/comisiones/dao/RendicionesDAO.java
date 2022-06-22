package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import sec.comisiones.mapeos.Rendiciones;
import sec.comisiones.mapeos.Usuarios;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class RendicionesDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	SimpleDateFormat dateFormatCorto= new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormatLargo= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public BeanContainer<Integer, Rendiciones> getAllRendicionesContainer(String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2,boolean vencimiento, Usuarios usr)  {
		 BeanContainer<Integer, Rendiciones> Container = null;
		 Rendiciones it= new Rendiciones();
		 
		 String criterio = " ";
		 if (numResolucion != ""){ criterio = criterio + " AND REPLACE(c.id,'.','') LIKE REPLACE('%"+ numResolucion +"%','.','') ";}
		 if (fec != ""){ criterio = criterio + " AND c.fecha = '"+ fec +"' ";}
		 if (area != ""){ criterio = criterio + " AND c.idArea ="+ area +" ";}
		 if (programa != ""){ criterio = criterio + " AND c.idPrograma ="+ programa +" ";}
		 if (estado != ""){ criterio = criterio + " AND c.idEstado ="+ estado +" ";}
		 if (vencimiento){
			if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (c.fechaHoraSalida BETWEEN  '"+ fec1 +"' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) ";}
		 	if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND ((c.fechaHoraSalida BETWEEN  '01/01/1900' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) OR (c.fechaHoraSalida IS NULL))";}
		 	if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND (c.fechaHoraSalida BETWEEN  '"+ fec1 +"' AND '01/01/2100')";}
		 	if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((c.fechaHoraSalida BETWEEN  '01/01/1900' AND '01/01/2100') OR (c.fechaHoraSalida IS NULL))";}
		 } else {
			if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (c.fechaHoraRegreso BETWEEN  '"+ fec1 +"' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) ";}
			if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND (c.fechaHoraRegreso BETWEEN  '02/01/1900' AND DATEADD(MINUTE,1439,'"+ fec2 +"'))";}
			if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND ((c.fechaHoraRegreso BETWEEN  '"+ fec1 +"' AND '01/01/2100') OR (c.fechaHoraRegreso IS NULL))";}
		 	if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((c.fechaHoraRegreso BETWEEN  '01/01/1900' AND '01/01/2100') OR (c.fechaHoraRegreso IS NULL))";}
		 }


	    try {
	    	sql = "SELECT c.id,c.fecha,ISNULL(c.idEstado,0) AS idEstado,ISNULL(c.idArea,0) AS idArea, "
    			+ "		ISNULL(c.idPrograma,0) AS idPrograma, ISNULL(c.idSolicitante,0) AS idSolicitante,ISNULL(c.motivo,'') AS motivo, "
    			+ "		c.fechaHoraSalida,c.fechaHoraRegreso,ISNULL(c.tiempoDuracion,0) AS duracion, ISNULL(c.reqVehiculo,0) AS reqVehiculo,"
    			+ "		c.fechaAutorizacion, ISNULL(c.userAutorizacion,0) AS idAutorizador, ISNULL(c.autorizada,0) AS autorizada, "
    			+ "		ISNULL(e.nombre,'') AS descEstado, ISNULL(a.nombre,'') AS descArea, ISNULL(p.nombre,'') AS descPrograma, "
    			+ "		(ISNULL(emp.apellido,'')+', '+ISNULL(emp.nombre,'')) AS descSolicitante, "
    			+ "		(ISNULL(emp1.apellido,'')+', '+ISNULL(emp1.nombre,'')) AS descAutorizador "
    			+ "	FROM Rendiciones c "
    			+ "		LEFT JOIN Estados e ON c.idEstado=e.id "
    			+ "		LEFT JOIN Areas a ON c.idArea=a.id "
    			+ "		LEFT JOIN Programas p ON c.idPrograma=p.id "
    			+ "		LEFT JOIN Empleados emp ON c.idSolicitante=emp.id "
    			+ "		LEFT JOIN Empleados emp1 ON c.userAutorizacion=emp1.id "
	    		+ " WHERE 1=1 AND (c.userCreacion="+usr.getId()+" OR (SELECT TOP 1 u.id FROM programas p INNER JOIN Usuarios u ON p.idEmpleado=u.idEmpleado WHERE c.idPrograma=p.id)="+usr.getId()+") " + criterio;
	    	
	    	
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Rendiciones> (Rendiciones.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	
//	        	it= new Rendiciones();
//	        	it.setId(id);
//	        	it.setIdComision(idComision);
//	        	it.setIdComisionado(idComisionado);
//	        	it.setIdBanco(idBanco);
	        	

	        	
	        	Container.addBean(it);	    			

	        }
		    } catch (SQLException e) {
		        e.printStackTrace(); 
		    }finally{
		        try {
					rs.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		    }
	    
	    return Container;
	}
	

	
	
	
	
	public Item getItem()  {
		Rendiciones a = new Rendiciones();
		BeanItem<Rendiciones> item = new BeanItem<Rendiciones>(a);	
	    return item;
	}
	
	public Rendiciones DevuelveRendicion(int idComision, int idComisionado) throws SQLException{
		Rendiciones it = new Rendiciones();
		
		sql = " SELECT c.id, c.fecha, c.idComision, ISNULL(c.nExpediente,'') AS nExpediente, ISNULL(c.idComisionado,0) AS idComisionado, "
			+ "		ISNULL(c.anticipo,0) AS anticipo, ISNULL(c.viaticos,0) AS viatico, ISNULL(c.gastos,0) AS gastos, "
			+ "		ISNULL(c.gastosTotal,0) AS gastosTotal, ISNULL(c.saldo,0) AS saldo, ISNULL(c.aFavorDe,'') AS aFavorDe, "
			+ "		ISNULL(c.idBanco,0) AS idBanco, ISNULL(c.nCuenta,'') AS nCuenta, ISNULL(c.nDeposito,'') AS nDeposito, "
    		+ "		c.fechaHoraSalida, c.fechaHoraRegreso, ISNULL(c.Duracion,0) AS duracion "
    		+ "	FROM Rendiciones c "
    		+ " WHERE c.idComision=0"+ idComision +" AND c.idComisionado="+idComisionado+" ";
    	
    	
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		
    		it.setId(rs.getInt("id"));
    		it.setIdComision(rs.getInt("idComision"));
    		it.setIdComisionado(rs.getInt("idComisionado"));
    		it.setnExpediente(rs.getString("nExpediente"));
    		it.setFecha(rs.getDate("fecha"));
    		try {
				it.setFechaHoraSalida(dateFormatLargo.parse(dateFormatLargo.format(rs.getTimestamp("fechaHoraSalida"))));
				it.setFechaHoraRegreso(dateFormatLargo.parse(dateFormatLargo.format(rs.getTimestamp("fechaHoraRegreso"))));
			} catch (ParseException e) {
				Notification.show(""+e.getMessage(),Type.ERROR_MESSAGE);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		it.setDuracion(rs.getFloat("duracion"));
    		it.setAnticipo(rs.getFloat("anticipo"));
			it.setViaticos(rs.getFloat("viatico"));
			it.setGastos(rs.getFloat("gastos"));
			it.setGastosTotal(rs.getFloat("gastosTotal"));
			it.setSaldo(rs.getFloat("saldo"));
			it.setaFavorDe(rs.getString("aFavorDe"));
			it.setIdBanco(rs.getInt("idBanco"));
			it.setnCuenta(rs.getString("nCuenta"));
			it.setnDeposito(rs.getString("nDeposito"));

    	}
    	rs.close();
		con.close();
		return it;
	}
	
	

	

	public void modifica(Rendiciones it, int usrLog){
		sql = "UPDATE Rendiciones SET fecha='"+dateFormatCorto.format(it.getFecha())+"', fechaHoraSalida='"+dateFormatLargo.format(it.getFechaHoraSalida())+"', "
			+ " fechaHoraRegreso='"+dateFormatLargo.format(it.getFechaHoraRegreso())+"', "
			+ " duracion="+it.getDuracion()+", nExpediente='"+it.getnExpediente()+"', "
	    	+ " anticipo="+it.getAnticipo()+", viaticos="+it.getViaticos()+", gastos="+it.getGastos()+", "
	    	+ " gastosTotal="+it.getGastosTotal()+", saldo="+it.getSaldo()+", aFavorDe='"+it.getaFavorDe()+"', idBanco="+it.getIdBanco()+", "
	    	+ " nCuenta='"+it.getnCuenta()+"', nDeposito='"+it.getnDeposito()+"', "
			+ "fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE id=0" + it.getId() +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(Rendiciones it, int usrLog) {	 
	    sql = " INSERT INTO Rendiciones (idComision,nExpediente,idComisionado,fecha,fechaHoraSalida,fechaHoraRegreso, "
	    	+ " 	duracion,anticipo,viaticos,gastos,gastosTotal,saldo,aFavorDe,idBanco,nCuenta,nDeposito, "
	    	+ "		fechaCreacion,userCreacion,fechaModificacion,userModificacion) "
	    	+ " VALUES ("+it.getIdComision()+",'"+it.getnExpediente()+"',"+it.getIdComisionado()+",'"+dateFormatCorto.format(it.getFecha())+"', "
	    	+ "		'"+dateFormatLargo.format(it.getFechaHoraSalida())+"','"+dateFormatLargo.format(it.getFechaHoraRegreso())+"',"+it.getDuracion()+", "
	    	+ "		"+it.getAnticipo()+","+it.getViaticos()+", "
	    	+ "		"+it.getGastos()+","+it.getGastosTotal()+","+it.getSaldo()+",'"+it.getaFavorDe()+"',"+it.getIdBanco()+",'"+it.getnCuenta()+"', "
	    	+ "		'"+it.getnDeposito()+"', "
	    	+ "		GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +") ";

	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int nombre, int usrLog) {	
	    sql = "DELETE FROM Rendiciones WHERE id='"+ nombre +"' ";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}


	
	public int getUltimoId() throws SQLException {
	    int existe=0;
		sql = "SELECT MAX(id) AS id FROM Rendiciones ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("id");
		rs.close();
		con.close();
	    return existe;
	}
	
	
	public int size (ResultSet rsint){
	    try {
			rsint.last();
		    int numRows = rsint.getRow(); 
		    rsint.beforeFirst();
			return numRows;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} 
	}

	
	public int existe(int nombre) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM Rendiciones WHERE id='" + nombre +"'";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
	
	
	public int existe(int idComision, int idComisionado) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM Rendiciones WHERE idComision=0"+ idComision +" AND idComisionado="+idComisionado+" ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
	
	
	
	

}
