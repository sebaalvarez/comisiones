package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;

import sec.comisiones.mapeos.Asignaciones;
import sec.comisiones.mapeos.Reservas;
import sec.comisiones.mapeos.Usuarios;



public class AsignacionesDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	SimpleDateFormat dateFormatLargo= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat dateFormatCorto= new SimpleDateFormat("dd/MM/yyyy");
	
	
	
	public BeanContainer<Integer, Asignaciones> getAllAsignacionesContainer(String fec1,Usuarios usr)  {
		 BeanContainer<Integer, Asignaciones> Container = null;
		 Asignaciones it = null;
	    try {
	    	
	    	sql="EXEC dbo.cargaReporteVehiculo "+ usr.getId() +",'"+ fec1 +"' ";
	    	msg="";
	    	cnn.grabarEnBase(sql,msg,usr.getId());
	    	
	    	
	    	sql = "	SELECT row_number() OVER(ORDER BY dato ASC) AS id, iduser, anio, mes, semana, dato, "
	    		+ " 	MAX(dia1) AS dia1, MAX(dia2) AS dia2, MAX(dia3) AS dia3, MAX(dia4) AS dia4, "
	    		+ " 	MAX(dia5) AS dia5, MAX(dia6) AS dia6, MAX(dia7) AS dia7 "
	    		+ " FROM reporteVehiculos "
	    		+ "	WHERE dato<>'' AND iduser="+ usr.getId() +" "
	    		+ " GROUP BY anio, mes, semana, dato, iduser ";
		 
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Asignaciones> (Asignaciones.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	
	        	it=new Asignaciones();
	        	it.setId(rs.getInt("id"));
	        	it.setAnio(rs.getInt("anio"));
	        	it.setMes(rs.getInt("mes"));
	        	it.setSemana(rs.getInt("semana"));
	        	it.setIdUser(rs.getInt("iduser"));
	        	it.setDato(rs.getString("dato"));
	        	it.setDia1(rs.getString("dia1"));
	        	it.setDia2(rs.getString("dia2"));
	        	it.setDia3(rs.getString("dia3"));
	        	it.setDia4(rs.getString("dia4"));
	        	it.setDia5(rs.getString("dia5"));
	        	it.setDia6(rs.getString("dia6"));
	        	it.setDia7(rs.getString("dia7"));
	        	
	        	TextArea ta1= new TextArea();
	        	TextArea ta2= new TextArea();
	        	TextArea ta3= new TextArea();
	        	TextArea ta4= new TextArea();
	        	TextArea ta5= new TextArea();
	        	TextArea ta6= new TextArea();
	        	TextArea ta7= new TextArea();
	        	
	        	ta1.setWidth(100, Unit.PERCENTAGE);
	        	ta2.setWidth(100, Unit.PERCENTAGE);
	        	ta3.setWidth(100, Unit.PERCENTAGE);
	        	ta4.setWidth(100, Unit.PERCENTAGE);
	        	ta5.setWidth(100, Unit.PERCENTAGE);
	        	ta6.setWidth(100, Unit.PERCENTAGE);
	        	ta7.setWidth(100, Unit.PERCENTAGE);
	        	
	        	ta1.setEnabled(false);
	        	ta2.setEnabled(false);
	        	ta3.setEnabled(false);
	        	ta4.setEnabled(false);
	        	ta5.setEnabled(false);
	        	ta6.setEnabled(false);
	        	ta7.setEnabled(false);
	        	
	        	ta1.setValue(rs.getString("dia1"));
	        	it.setD1(ta1);
	        	ta2.setValue(rs.getString("dia2"));
	        	it.setD2(ta2);
	        	ta3.setValue(rs.getString("dia3"));
	        	it.setD3(ta3);
	        	ta4.setValue(rs.getString("dia4"));
	        	it.setD4(ta4);
	        	ta5.setValue(rs.getString("dia5"));
	        	it.setD5(ta5);
	        	ta6.setValue(rs.getString("dia6"));
	        	it.setD6(ta6);
	        	ta7.setValue(rs.getString("dia7"));
	        	it.setD7(ta7);
	        	
	        	
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
	
	
	public Asignaciones DevuelveAsignacion(Usuarios usr) {
		Asignaciones it = new Asignaciones();
		try{
			sql = " SELECT dia1, dia2, dia3, dia4, dia5, dia6, dia7 "
				+ " FROM reporteVehiculos "
				+ " WHERE iduser="+ usr.getId() +" "; 
		
	
			con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	    	int a = size(rs); 
	    	if (a>0) {
	    		rs.next();
	    		
	    		it=new Asignaciones();

	        	it.setDia1(rs.getString("dia1"));
	        	it.setDia2(rs.getString("dia2"));
	        	it.setDia3(rs.getString("dia3"));
	        	it.setDia4(rs.getString("dia4"));
	        	it.setDia5(rs.getString("dia5"));
	        	it.setDia6(rs.getString("dia6"));
	        	it.setDia7(rs.getString("dia7"));
	        	
	        	TextArea ta1= new TextArea();
	        	TextArea ta2= new TextArea();
	        	TextArea ta3= new TextArea();
	        	TextArea ta4= new TextArea();
	        	TextArea ta5= new TextArea();
	        	TextArea ta6= new TextArea();
	        	TextArea ta7= new TextArea();
	        	
	        	ta1.setWidth(100, Unit.PERCENTAGE);
	        	ta2.setWidth(100, Unit.PERCENTAGE);
	        	ta3.setWidth(100, Unit.PERCENTAGE);
	        	ta4.setWidth(100, Unit.PERCENTAGE);
	        	ta5.setWidth(100, Unit.PERCENTAGE);
	        	ta6.setWidth(100, Unit.PERCENTAGE);
	        	ta7.setWidth(100, Unit.PERCENTAGE);
	        	
	        	ta5.setEnabled(false);
	        	ta1.setValue(rs.getString("dia1"));
	        	it.setD1(ta1);
	        	ta2.setValue(rs.getString("dia2"));
	        	it.setD2(ta2);
	        	ta3.setValue(rs.getString("dia3"));
	        	it.setD3(ta3);
	        	ta4.setValue(rs.getString("dia4"));
	        	it.setD4(ta4);
	        	ta5.setValue(rs.getString("dia5"));
	        	it.setD5(ta5);
	        	ta6.setValue(rs.getString("dia6"));
	        	it.setD6(ta6);
	        	ta7.setValue(rs.getString("dia7"));
	        	it.setD7(ta7);
	    		
	        	ta1.setRows(2);
	    		
        	
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
