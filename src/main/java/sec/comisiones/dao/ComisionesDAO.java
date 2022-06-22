package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import sec.comisiones.mapeos.Comisiones;
import sec.comisiones.mapeos.Usuarios;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class ComisionesDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	SimpleDateFormat dateFormatCorto= new SimpleDateFormat("dd/MM/yyyy");
	
	
	
	
	public String criterio(String id){
		String criterio="";
		try{
			criterio=" AND c.id="+id+"";
			
		} catch(Exception e){
			Notification.show("Error","No se pudo obtener el reporte. "+e.getMessage(),Type.ERROR_MESSAGE);
		}
		
		return criterio;
	}
	
	
	
	
	public BeanContainer<Integer, Comisiones> DevuelveBeanComisiones(String criterio)  {
		 BeanContainer<Integer, Comisiones> Container = null;

		 try {
		    	sql = "SELECT c.id,c.fecha,ISNULL(c.idEstado,0) AS idEstado,ISNULL(c.idArea,0) AS idArea, "
	    			+ "		ISNULL(c.idPrograma,0) AS idPrograma, ISNULL(c.idSolicitante,0) AS idSolicitante,ISNULL(c.motivo,'') AS motivo, "
	    			+ "		c.fechaHoraSalida,c.fechaHoraRegreso,ISNULL(c.tiempoDuracion,0) AS duracion, ISNULL(c.reqVehiculo,0) AS reqVehiculo, "
	    			+ "		ISNULL(c.observacionesInternas,'') AS observaciones, ISNULL(c.nExpediente,'') AS nExpediente, "
	    			+ "		c.fechaAutorizacion, ISNULL(c.userAutorizacion,0) AS idAutorizador, ISNULL(c.autorizada,0) AS autorizada, "
	    			+ "		ISNULL(e.nombre,'') AS descEstado, ISNULL(a.nombre,'') AS descArea, ISNULL(p.nombre,'') AS descPrograma, "
	    			+ "		(ISNULL(emp.apellido,'')+', '+ISNULL(emp.nombre,'')) AS descSolicitante, "
	    			+ "		(ISNULL(emp1.apellido,'')+', '+ISNULL(emp1.nombre,'')) AS descAutorizador, ISNULL(u.nombre,'') AS descUsuario "
	    			+ "	FROM Comisiones c "
	    			+ "		LEFT JOIN Estados e ON c.idEstado=e.id "
	    			+ "		LEFT JOIN Areas a ON c.idArea=a.id "
	    			+ "		LEFT JOIN Programas p ON c.idPrograma=p.id "
	    			+ "		LEFT JOIN Empleados emp ON c.idSolicitante=emp.id "
	    			+ "		LEFT JOIN Empleados emp1 ON c.userAutorizacion=emp1.id "
	    			+ "		LEFT JOIN Usuarios u ON c.userCreacion=u.id "
		    		+ " WHERE 1=1 " + criterio;
		    	
		    	con=cnn.getConn();
		    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Comisiones> (Comisiones.class);
	        Container.setBeanIdProperty("id");
				
	        while(rs.next()) {
	        		Container.addBean(new Comisiones(rs.getInt("id"), rs.getDate("fecha"), rs.getInt("idEstado"), rs.getInt("idArea"),rs.getInt("idPrograma"), rs.getInt("idSolicitante"), 
	        			rs.getTimestamp("fechaHoraSalida"), rs.getTimestamp("fechaHoraRegreso"), rs.getFloat("duracion"), rs.getString("motivo"), rs.getString("observaciones"), 
	        			rs.getBoolean("reqVehiculo"), rs.getBoolean("autorizada"), 
	        			rs.getInt("idAutorizador"), rs.getDate("fechaAutorizacion"), rs.getString("descEstado"), rs.getString("descArea"), rs.getString("descPrograma"), 
	        			rs.getString("descSolicitante"), rs.getString("descAutorizador"), rs.getString("descUsuario"), rs.getString("nExpediente")));	    			 
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

	public BeanContainer<Integer, Comisiones> getAllComisionesContainer(String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2,boolean vencimiento, Usuarios usr)  {
//		 BeanContainer<Integer, Comisiones> Container = null;
		 
		 String criterio = "";
		 
		 // muestro solamente las comisiones creadas por el usuario logueado o las comisiones del programa si el usuario logueado es responsable del programa
	//	 criterio = criterio + " AND (c.userCreacion="+usr.getId()+" OR "+usr.getId()+" IN (SELECT u.id FROM programas p INNER JOIN Usuarios u ON p.idEmpleado=u.idEmpleado WHERE c.idPrograma=p.id)) ";
		 
		 
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

		 
		 BeanContainer<Integer, Comisiones> Container= DevuelveBeanComisiones(criterio);

	    return Container;
	}
	
	public BeanContainer<Integer, Comisiones> getAllComisionesSolicitanteContainer(String tipo, String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2,boolean vencimiento, Usuarios usr)  {
//		 BeanContainer<Integer, Comisiones> Container = null;
		 
		 String criterio = " ";
		 
		 criterio = criterio +" AND (c.userCreacion="+usr.getId()+" OR "+usr.getId()+" IN (SELECT u.id FROM programas p INNER JOIN Usuarios u ON p.idEmpleado=u.idEmpleado WHERE c.idPrograma=p.id)) ";
		 
		 if (tipo != ""){ 
			 if (tipo == "CARGA"){
				criterio = criterio + " AND c.idEstado IN (1,5,6) ";
			 } else if(tipo=="AUTORIZADAS"){
				 criterio = criterio + " AND c.idEstado=4 ";
			 } else if(tipo=="TODASGENERADAS"){
				 criterio = criterio + " ";
			 }
		 }
		 if (numResolucion != ""){ criterio = criterio + " AND REPLACE(c.id,'.','') LIKE REPLACE('%"+ numResolucion +"%','.','') ";}
		 if (fec != ""){ criterio = criterio + " AND c.fecha = '"+ fec +"' ";}
		 if (area != ""){ criterio = criterio + " AND c.idArea ="+ area +" ";}
		 if (programa != ""){ criterio = criterio + " AND c.idPrograma ="+ programa +" ";}
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

		 BeanContainer<Integer, Comisiones> Container= DevuelveBeanComisiones(criterio);
	    
		 return Container;
	}
	
	public BeanContainer<Integer, Comisiones> getAllComisionesPendienteVehiculoContainer(String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2,boolean vencimiento)  {
//		 BeanContainer<Integer, Comisiones> Container = null;
		 
		 String criterio = " ";
		 
		 criterio = criterio + " AND c.idEstado IN (2,7) AND c.reqVehiculo=1 "; //AND (SELECT COUNT(*) FROM DetallesVehiculos WHERE idComision=c.id )=0 ";
		 
		 if (numResolucion != ""){ criterio = criterio + " AND REPLACE(c.id,'.','') LIKE REPLACE('%"+ numResolucion +"%','.','') ";}
		 if (fec != ""){ criterio = criterio + " AND c.fecha = '"+ fec +"' ";}
		 if (area != ""){ criterio = criterio + " AND c.idArea ="+ area +" ";}
		 if (programa != ""){ criterio = criterio + " AND c.idPrograma ="+ programa +" ";}
//		 if (estado != ""){ criterio = criterio + " AND c.idEstado ="+ estado +" ";}
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
	    
	    BeanContainer<Integer, Comisiones> Container= DevuelveBeanComisiones(criterio);
	    
	    return Container;
	}
	
	public BeanContainer<Integer, Comisiones> getAllComisionesPendienteAutorizacionContainer(String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2,boolean vencimiento)  {
//		 BeanContainer<Integer, Comisiones> Container = null;
		 
		 String criterio = " ";
		 
		 criterio = criterio + " AND c.idEstado=3 AND ISNULL(c.autorizada,0)=0 ";
		 
		 if (numResolucion != ""){ criterio = criterio + " AND REPLACE(c.id,'.','') LIKE REPLACE('%"+ numResolucion +"%','.','') ";}
		 if (fec != ""){ criterio = criterio + " AND c.fecha = '"+ fec +"' ";}
		 if (area != ""){ criterio = criterio + " AND c.idArea ="+ area +" ";}
		 if (programa != ""){ criterio = criterio + " AND c.idPrograma ="+ programa +" ";}
//		 if (estado != ""){ criterio = criterio + " AND c.idEstado ="+ estado +" ";}
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

	    
	    BeanContainer<Integer, Comisiones> Container= DevuelveBeanComisiones(criterio);
	    
	    return Container;
	}
	
	public BeanContainer<Integer, Comisiones> getAllComisionesPendienteRendicionContainer(String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2,boolean vencimiento, Usuarios usr)  {
//		 BeanContainer<Integer, Comisiones> Container = null;
		 
		 String criterio = " ";
		 
		 // Trae las comisiones generadas por ese usuario o las del programa si es jefe de programa//
		 criterio = criterio + " AND (c.userCreacion="+usr.getId()+" OR "+usr.getId()+" IN (SELECT u.id FROM programas p INNER JOIN Usuarios u ON p.idEmpleado=u.idEmpleado WHERE c.idPrograma=p.id)) ";
		 
		 // Trae las comisiones que tienen al menos un comisionado sin rendir //
		 criterio = criterio + " AND ISNULL(c.autorizada,0)=1 AND ISNULL(c.nExpediente,'') <> '' AND c.id IN (SELECT DISTINCT idComision FROM DetallesComisionados WHERE ISNULL(idRendicion,0) = 0) ";  
	 
		 
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

	    
	    BeanContainer<Integer, Comisiones> Container= DevuelveBeanComisiones(criterio);
	    
	    return Container;
	}
	
	public BeanContainer<Integer, Comisiones> getAllComisionesPendienteExpedienteContainer(String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2,boolean vencimiento, Usuarios usr)  {
//		 BeanContainer<Integer, Comisiones> Container = null;
		 
		 String criterio = " ";
		 
		 // Trae las comisiones generadas por ese usuario o las del programa si es jefe de programa//
		 //criterio = criterio + " AND (c.userCreacion="+usr.getId()+" OR "+usr.getId()+" IN (SELECT u.id FROM programas p INNER JOIN Usuarios u ON p.idEmpleado=u.idEmpleado WHERE c.idPrograma=p.id)) ";
		 
		 // Trae las comisiones que tienen al menos un comisionado sin rendir //
		 criterio = criterio + " AND ISNULL(c.autorizada,0)=1 AND ISNULL(c.nExpediente,'') = '' ";  
	 
		 
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

	    
	    BeanContainer<Integer, Comisiones> Container= DevuelveBeanComisiones(criterio);
	    
	    return Container;
	}
	
	public BeanContainer<Integer, Comisiones> getAllComisionesPendienteImpresionContainer(String numResolucion, String fec, String area, String programa, String estado, 
			String fec1, String fec2,boolean vencimiento, Usuarios usr)  {
//		 BeanContainer<Integer, Comisiones> Container = null;
		 
		 String criterio = " ";
		 
		 // Trae las comisiones generadas por ese usuario o las del programa si es jefe de programa//
		 criterio = criterio + " AND (c.userCreacion="+usr.getId()+" OR "+usr.getId()+" IN (SELECT u.id FROM programas p INNER JOIN Usuarios u ON p.idEmpleado=u.idEmpleado WHERE c.idPrograma=p.id)) ";
		 
		 // Trae las comisiones que tienen al menos un comisionado sin rendir //
		 criterio = criterio + " AND ISNULL(c.autorizada,0)=1 AND ISNULL(c.nExpediente,'') = '' ";  
	 
		 
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

	    
	    BeanContainer<Integer, Comisiones> Container= DevuelveBeanComisiones(criterio);
	    
	    return Container;
	}
	
	
	
	
	
	public Item getItem()  {
		Comisiones a = new Comisiones();
		BeanItem<Comisiones> item = new BeanItem<Comisiones>(a);	
	    return item;
	}
	
	public Comisiones DevuelveComision(int id) throws SQLException{
		Comisiones it = new Comisiones();
		
		sql = "SELECT c.id,c.fecha,ISNULL(c.idEstado,0) AS idEstado,ISNULL(c.idArea,0) AS idArea, "
			+ "		ISNULL(c.idPrograma,0) AS idPrograma, ISNULL(c.idSolicitante,0) AS idSolicitante,ISNULL(c.motivo,'') AS motivo, "
			+ "		c.fechaHoraSalida,c.fechaHoraRegreso,ISNULL(c.tiempoDuracion,0) AS duracion, ISNULL(c.reqVehiculo,0) AS reqVehiculo, "
			+ "		ISNULL(c.observacionesInternas,'') AS observaciones, ISNULL(c.nExpediente,'') AS nExpediente, "
			+ "		ISNULL(c.fechaAutorizacion,'01/01/1900') AS fechaAutorizacion, ISNULL(c.userAutorizacion,0) AS idAutorizador, ISNULL(c.autorizada,0) AS autorizada, "
			+ "		ISNULL(e.nombre,'') AS descEstado, ISNULL(a.nombre,'') AS descArea, ISNULL(p.nombre,'') AS descPrograma, "
			+ "		(ISNULL(emp.apellido,'')+', '+ISNULL(emp.nombre,'')) AS descSolicitante, "
			+ "		(ISNULL(emp1.apellido,'')+', '+ISNULL(emp1.nombre,'')) AS descAutorizador, ISNULL(u.nombre,'') AS descUsuario  "
			+ "	FROM Comisiones c "
			+ "		LEFT JOIN Estados e ON c.idEstado=e.id "
			+ "		LEFT JOIN Areas a ON c.idArea=a.id "
			+ "		LEFT JOIN Programas p ON c.idPrograma=p.id "
			+ "		LEFT JOIN Empleados emp ON c.idSolicitante=emp.id "
	//			+ "		LEFT JOIN usuarios u ON c.userAutorizacion=u.id "
			+ "		LEFT JOIN Empleados emp1 ON c.userAutorizacion=emp1.id "
			+ "		LEFT JOIN Usuarios u ON c.userCreacion=u.id "
			+ " WHERE c.id=0"+ id +" ";
		
		
		con=cnn.getConn();
		rs = cnn.obtenerCursor(sql, con);
		
		int a = size(rs); 
		if (a>0) {
			rs.next();
			it = new Comisiones(rs.getInt("id"), rs.getDate("fecha"), rs.getInt("idEstado"), rs.getInt("idArea"),rs.getInt("idPrograma"), rs.getInt("idSolicitante"), 
					rs.getTimestamp("fechaHoraSalida"), rs.getTimestamp("fechaHoraRegreso"), rs.getFloat("duracion"), rs.getString("motivo"), rs.getString("observaciones"), 
					rs.getBoolean("reqVehiculo"), rs.getBoolean("autorizada"),
					rs.getInt("idAutorizador"), rs.getDate("fechaAutorizacion"), rs.getString("descEstado"), rs.getString("descArea"), rs.getString("descPrograma"), 
					rs.getString("descSolicitante"), rs.getString("descAutorizador"), rs.getString("descUsuario"), rs.getString("nExpediente"));
			}
		rs.close();
		con.close();
		return it;
	}
	
	

	

	public void modifica(int id, String fecha, int idEstado, int idArea, int idPrograma, int idSolicitante, String fechaSalida, String fechaRegreso, 
				float duracion, String motivo, boolean reqVehiculo, int usrLog){
		sql = "UPDATE Comisiones SET fecha='"+ fecha +"', fechaHoraSalida='"+ fechaSalida +"', fechaHoraRegreso='"+ fechaRegreso +"', tiempoDuracion=0"+ duracion +", "
						+ "idArea=0"+ idArea +", idPrograma=0"+ idPrograma +", idSolicitante=0"+ idSolicitante +", motivo='"+ motivo +"', reqVehiculo='"+ reqVehiculo +"', "
						+ "fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE id=0" + id +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);
		
	}
	
	public void agrega(String fecha, int idEstado, int idArea, int idPrograma, int idSolicitante, String fechaSalida, String fechaRegreso, 
				float duracion, String motivo, boolean reqVehiculo, int usrLog) {	 
	    sql = "INSERT INTO Comisiones (fecha,idEstado,idArea,idPrograma,idSolicitante,motivo,fechaHoraSalida,fechaHoraRegreso, "
	    		+ " tiempoDuracion, autorizada, reqVehiculo, fechaCreacion,userCreacion,fechaModificacion,userModificacion) "
	    		+ " VALUES ('"+ fecha +"', 0"+ idEstado +", 0"+ idArea +", 0"+ idPrograma +", 0"+ idSolicitante +", '"+ motivo +"', '"+ fechaSalida +"','"+ fechaRegreso +"', "
	    				+ " 0"+ duracion +", 0, '"+ reqVehiculo +"', GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int nombre, int usrLog) {	
	    sql = "DELETE FROM Comisiones WHERE id='"+ nombre +"' ";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}

	public void autoriza(int id, int idEmp, int usrLog){
		sql = "UPDATE Comisiones SET autorizada=1, idestado=4, fechaAutorizacion=GETDATE(), userAutorizacion=0"+ idEmp +", "
				+ " fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE id=0" + id +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void modificaEstado(int id, int estado, int usrLog){
		sql = "UPDATE Comisiones SET idestado=0"+ estado +", fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE id=0" + id +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void modificaEstadoObservacion(int id, int estado, String obs, int usrLog){
		String sqlExec = obs.replace("'", "´");
		sql = "UPDATE Comisiones SET idestado=0"+ estado +", ObservacionesInternas=ISNULL(ObservacionesInternas,'') + ' ' +'"+ sqlExec +"', fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE id=0" + id +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void modificaExpediente(int id, String nExpediente, int usrLog){
		sql = "UPDATE Comisiones SET nExpediente='"+ nExpediente +"', fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE id=0" + id +"";
		msg="";
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

	public int existe(int nombre) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM Comisiones WHERE id='" + nombre +"'";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
	
	public int getUltimoId() throws SQLException {
	    int existe=0;
		sql = "SELECT MAX(id) AS id FROM Comisiones ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("id");
		rs.close();
		con.close();
	    return existe;
	}
}
