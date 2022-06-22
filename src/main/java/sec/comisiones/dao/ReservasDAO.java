package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import sec.comisiones.mapeos.Choferes;
import sec.comisiones.mapeos.Reservas;

public class ReservasDAO {

	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	SimpleDateFormat dateFormatLargo= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat dateFormatCorto= new SimpleDateFormat("dd/MM/yyyy");
	
	
//	public BeanContainer<Integer, Reservas> getAllReservasContainer()  {
//		 BeanContainer<Integer, Choferes> Container = null;
//		 Choferes it = null;
//	    try {
//	    	sql = "SELECT c.id, ISNULL(c.idEmpleado,0) AS idEmpleado, ISNULL(c.idCategoria,0) AS idCategoria, ISNULL(c.idPrograma,0) AS idPrograma, "
//	    		+ "		ISNULL(CAST(e.dni AS nvarchar(10)),'') +' - '+ ISNULL(e.apellido,'') + ', ' + ISNULL(e.nombre,'') AS descEmpleado, "
//	    		+ " 	ISNULL(c.numeroCarnet,0) AS numeroCarnet, ISNULL(c.fechaVencimiento, '01/01/1900') AS fechaVencimiento,"
//	    		+ "		ISNULL(CAST(c.idCategoria AS nvarchar(10)),'') +' - '+ ISNULL(cat.nombre,'') AS descCategoria, "
//	    		+ "		ISNULL(CAST(c.idPrograma AS nvarchar(10)),'') +' - '+ ISNULL(p.nombre,'') AS descPrograma  " 
//	    		+ " FROM Choferes c "
//	    		+ "		LEFT JOIN empleados e ON c.idEmpleado=e.id "
//	    		+ "		LEFT JOIN categorias cat ON c.idCategoria=cat.id "
//	    		+ "		LEFT JOIN programas p ON c.idPrograma=p.id "
//	    		+ " ORDER BY e.apellido, e.nombre ";
//	    	con=cnn.abrir().reserveConnection();
//	    	rs = cnn.obtenerCursor(sql, con);
//
//	        Container = new BeanContainer<Integer, Choferes> (Choferes.class);
//	        Container.setBeanIdProperty("id");
//			
//	        while(rs.next()) {
//	        	
//	        	it=new Choferes();
//	        	it.setId(rs.getInt("id"));
//	        	it.setIdEmpleado(rs.getInt("idEmpleado"));
//	        	it.setDescChofer(rs.getString("descEmpleado"));
//	        	it.setNumCarnet(rs.getLong("numeroCarnet"));
//	        	it.setFechaVencimiento(rs.getDate("fechaVencimiento"));
//	        	it.setIdCategoria(rs.getInt("idCategoria"));
//	        	it.setIdPrograma(rs.getInt("idPrograma"));
//	        	it.setDescCategoria(rs.getString("descCategoria"));
//	        	it.setDescPrograma(rs.getString("descPrograma"));
//	        	
//	        	Container.addBean(it);	    			 
//	        }
//		    } catch (SQLException e) {
//		        e.printStackTrace(); 
//		    }finally{
//		        try {
//					rs.close();
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//		    }
//	    
//	    return Container;
//	}
	

	public Item getItem()  {
		Reservas a = new Reservas();
		BeanItem<Reservas> item = new BeanItem<Reservas>(a);	
	    return item;
	}
	
	
	
	public String criterio(String id){
		String criterio="";
		try{
			criterio=" AND c.id="+id+"";
			
		} catch(Exception e){
			Notification.show("Error","No se pudo obtener el reporte. "+e.getMessage(),Type.ERROR_MESSAGE);
		}
		
		return criterio;
	}
	
	
	public Reservas DevuelveReserva(int id) {
		Reservas it = new Reservas();
		try{
			sql = " SELECT c.id, c.observaciones, c.fechaHoraSalida, c.fechaHoraRegreso, c.fechaAutorizacion, c.idChofer, c.idVehiculo, c.chofer, c.vehiculo, "
				+ "		ISNULL(ma.id,'') AS descMarca, ISNULL(mo.id,'') AS descModelo, ISNULL(v.id,'') AS descDominio "
		    	+ " FROM Reservas c "
		    	+ "		LEFT JOIN vehiculos v ON c.idVehiculo=v.id "
		    	+ "		LEFT JOIN Marcas ma ON v.idMarca=ma.id "
		    	+ "		LEFT JOIN Modelos mo ON v.idModelo=mo.id "
	    		+ " WHERE c.id=0"+ id +" ";
			con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	    	int a = size(rs); 
	    	if (a>0) {
	    		rs.next();
	    		
	    		it=new Reservas();
	        	it.setId(rs.getInt("id"));
	        	it.setFechaHoraRegreso(rs.getTimestamp("fechaHoraRegreso"));
	        	it.setFechaHoraSalida(rs.getTimestamp("fechaHoraSalida"));
	        	it.setFechaAutorizacion(rs.getDate("fechaAutorizacion"));
	        	it.setIdChofer(rs.getInt("idChofer"));
	        	it.setIdVehiculo(rs.getInt("idVehiculo"));
	        	it.setChofer(rs.getBoolean("chofer"));
	        	it.setVehiculo(rs.getBoolean("vehiculo"));
	        	it.setObservaciones(rs.getString("observaciones"));
	        	it.setDescMarca(rs.getInt("descMarca"));
	        	it.setDescModelo(rs.getInt("descModelo"));
	        	it.setDescDominio(rs.getInt("descDominio"));
        	
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
		return it;
	}
	
	
	public String FechaReserva (int idComision, int idChofer) throws SQLException {
		String mensaje="";
		
		sql =" SELECT TOP 1 'El chofer selecionado se encuentra asignado a la comisión '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
			+" 		+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
			+"		CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
			+" FROM comisiones c "
			+" 		LEFT JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
			+" WHERE dv.idComision<>0"+ idComision +" AND dv.idChofer= 0"+ idChofer + " AND c.idEstado<>5 "
			+"		AND ( "
			+"				(SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
			+"				OR "
			+"				(SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
			+"				OR "
			+"				(c.fechaHoraSalida BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
			+"				OR "
			+"				(c.FechaHoraRegreso BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
			+"			) ";
		con=cnn.getConn();
		rs = cnn.obtenerCursor(sql,con);
		
		int a = 0;
		a=size(rs); 
    	if (a>0) {
    		rs.next();
    		mensaje = rs.getString("mensaje");

    	}
		
		return mensaje;
	}
	
	
	
	public void modifica(Reservas it, int usrLog){
		sql = " UPDATE Reservas SET observaciones='"+ it.getObservaciones() +"', fechaHoraSalida='"+ dateFormatLargo.format(it.getFecSalida()) +"', "
			+ "		fechaHoraRegreso='"+ dateFormatLargo.format(it.getFecRegreso()) +"', fechaAutorizacion='"+ dateFormatCorto.format(it.getFechaAutorizacion()) +"', "
			+ "		idChofer=0"+ it.getIdChofer()+", idVehiculo=0"+ it.getIdVehiculo() +", "
			+ "		chofer='"+ it.isChofer() +"', vehiculo='"+ it.isVehiculo() +"', "
			+ " 	fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" "
			+ " WHERE id=" + it.getId() +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(Reservas it, int usrLog) {	
	    sql = "INSERT INTO Reservas (observaciones, fechaHoraSalida, fechaHoraRegreso, fechaAutorizacion, idChofer, idVehiculo, chofer, vehiculo,  "
	    	+ "		fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    	+ " VALUES ('"+ it.getObservaciones() +"', '"+ dateFormatLargo.format(it.getFecSalida()) +"','"+ dateFormatLargo.format(it.getFecRegreso()) +"', "
	    	+ "		'"+ dateFormatCorto.format(it.getFechaAutorizacion()) +"', 0"+ it.getIdChofer()+", 0"+ it.getIdVehiculo() +", '"+ it.isChofer() +"', "
	    	+ "		'"+ it.isVehiculo() +"', "
	    	+ "		GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int id, int usrLog) {	
	    sql = "DELETE FROM Reservas WHERE id=0"+ id +" ";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
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


	public int existe(int idEmpleado)  {
	    int existe=0;
	    try{
	    	sql = "SELECT COUNT(*) As cant FROM Reservas WHERE idEmpleado='" + idEmpleado +"'";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	    	rs.next();
	    	existe = rs.getInt("cant");
	    	rs.close();
	    	con.close();
	    } catch(Exception e){
	    	
	    }finally{
	    	return existe;
	    }
	}
	
	
	@SuppressWarnings("finally")
	public int existenRegistrosRelacionados(int nombre) {
	    int existe=0;
	    try{
	    	sql = "SELECT ISNULL(dbo.RegistrosIdChofere("+ nombre +"),0) AS cant";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	    	rs.next();
	    	existe = rs.getInt("cant");
	    	
	    } catch(Exception e) {
	    	Notification.show("Error al obtener el registro.",Type.ERROR_MESSAGE);
	    } finally{
	    	try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return existe;
	    }   
	}
	
	
	public int getUltimoId() throws SQLException {
	    int existe=0;
		sql = "SELECT MAX(id) AS id FROM Reservas ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("id");
		rs.close();
		con.close();
	    return existe;
	}
	
	
	
}
