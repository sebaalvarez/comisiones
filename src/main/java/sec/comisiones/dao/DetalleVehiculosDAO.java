package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import sec.comisiones.mapeos.DetalleVehiculos;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;

public class DetalleVehiculosDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, DetalleVehiculos> getAllDetalleVehiculosContainer(int idComision)  {
		 BeanContainer<Integer, DetalleVehiculos> Container = null;
	    try {
	    	sql = "SELECT dv.id, dv.idComision,	dv.idChofer, ISNULL(ch.idEmpleado,0) AS idEmpleadoChofer, "
	    			+ "		ISNULL(e.apellido+', '+ e.nombre,'') AS descChofer, "
	    			+ " 	dv.userAutorizacion AS idAutorizador, " //ISNULL(u.idEmpleado,0) AS idEmpleadoAut, "
	    			+ "		ISNULL(e1.apellido+', '+ e1.nombre,'') AS descAutorizador, "
	    			+ "		dv.idVehiculo, ISNULL(v.dominio,'') AS Dominio, ISNULL(v.idMarca,0) AS idMarca, ISNULL(ma.nombre,'') AS descMarca, "
	    			+ "		ISNULL(v.idModelo,0) AS idModelo, ISNULL(mo.nombre,'') AS descModelo, "
	    			+ "		ISNULL(dv.ImpEstExceso,0) AS impEstExceso, ISNULL(dv.ImpEstTarjetaComb,0) AS impEstTarjetaComb, "
	    			+ "		ISNULL(dv.cantEstKmRecorrer,0) AS cantEstKmRecorrer, ISNULL(dv.cantEstLitrosComb,0) AS cantEstLitrosComb, "
	    			+ "		ISNULL(dv.fechaAutorizacion,'01/01/1900') AS fechaAutorizacion, ISNULL(dv.observaciones,'') AS observaciones "
	    	    	+ " FROM DetallesVehiculos dv "
	        		+ "		LEFT JOIN Choferes ch ON ch.id=dv.idChofer " 
	    	    	+ " 	LEFT JOIN Empleados e ON ch.idEmpleado=e.id "
	    	//   	+ "		LEFT JOIN usuarios u ON dv.userAutorizacion=u.id "
	    	    	+ "		LEFT JOIN Empleados e1 ON dv.userAutorizacion=e1.id "
	    	    	+ "		LEFT JOIN vehiculos v ON dv.idVehiculo=v.id "
	    	    	+ "		LEFT JOIN Marcas ma ON v.idMarca=ma.id "
	    	    	+ "		LEFT JOIN Modelos mo ON v.idModelo=mo.id "
	        		+ " WHERE dv.idComision=0"+ idComision +" ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, DetalleVehiculos> (DetalleVehiculos.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	// int id, int idComision, int idPersonalPorPrograma, String descPersonal,String descPrograma, String descArea
	        	Container.addBean(new DetalleVehiculos(
	        			rs.getInt("id"), rs.getInt("idComision"),rs.getInt("userAutorizacion"),rs.getDate("fechaAutorizacion"),rs.getInt("idChofer"),rs.getInt("idVehiculo"),
	        			rs.getFloat("cantEstKmRecorrer"),rs.getFloat("cantEstLitrosComb"),rs.getFloat("impEstTarjetaComb"),rs.getFloat("impEstExceso"),
	        			rs.getString("observaciones"),rs.getString("descAutorizador"),rs.getString("descChofer"),rs.getInt("idMarca"),rs.getInt("idModelo"),
	        			rs.getString("descDominio")
	        			));	    			
//			    System.out.println("fila: " + filas + " Id: " + id + " nombre: " + nombre + " Password: " + password  ); 
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
		DetalleVehiculos a = new DetalleVehiculos();
		BeanItem<DetalleVehiculos> item = new BeanItem<DetalleVehiculos>(a);	
	    return item;
	}

	
	public DetalleVehiculos DevuelveDetalleVehiculos(int idComision) {
		DetalleVehiculos rol = new DetalleVehiculos();
		try{
			sql = "SELECT dv.id, dv.idComision,	dv.idChofer, ISNULL(ch.idEmpleado,0) AS idEmpleadoChofer, "
				+ "		ISNULL(e.apellido+', '+ e.nombre,'') AS descChofer, "
				+ " 	dv.userAutorizacion AS idAutorizador, " //ISNULL(u.idEmpleado,0) AS idEmpleadoAut, "
				+ "		ISNULL(ISNULL(e1.apellido,'')+', '+ ISNULL(e1.nombre,''),'') AS descAutorizador, "
				+ "		dv.idVehiculo, ISNULL(v.dominio,'') AS Dominio, ISNULL(v.idMarca,0) AS idMarca, ISNULL(ma.nombre,'') AS descMarca, "
				+ "		ISNULL(v.idModelo,0) AS idModelo, ISNULL(mo.nombre,'') AS descModelo, "
				+ "		ISNULL(dv.ImpEstExceso,0) AS impEstExceso, ISNULL(dv.ImpEstTarjetaComb,0) AS impEstTarjetaComb, "
				+ "		ISNULL(dv.cantEstKmRecorrer,0) AS cantEstKmRecorrer, ISNULL(dv.cantEstLitrosComb,0) AS cantEstLitrosComb, "
				+ "		ISNULL(dv.fechaAutorizacion,'01/01/1900') AS fechaAutorizacion, ISNULL(dv.observaciones,' ') AS observaciones "
		    	+ " FROM DetallesVehiculos dv "
	    		+ "		LEFT JOIN Choferes ch ON ch.id=dv.idChofer " 
		    	+ " 	LEFT JOIN Empleados e ON ch.idEmpleado=e.id "
		    //	+ "		LEFT JOIN usuarios u ON dv.userAutorizacion=u.id "
		    	+ "		LEFT JOIN Empleados e1 ON dv.userAutorizacion=e1.id "
		    	+ "		LEFT JOIN vehiculos v ON dv.idVehiculo=v.id "
		    	+ "		LEFT JOIN Marcas ma ON v.idMarca=ma.id "
		    	+ "		LEFT JOIN Modelos mo ON v.idModelo=mo.id "
	    		+ " WHERE dv.idComision=0"+ idComision +" ";
			con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
        
	    	int a = size(rs); 
	    	if (a>0) {
	    		rs.next();
	    		rol = new DetalleVehiculos(
	    				rs.getInt("id"), rs.getInt("idComision"),rs.getInt("idAutorizador"),rs.getDate("fechaAutorizacion"),rs.getInt("idChofer"),rs.getInt("idVehiculo"),
	        			rs.getFloat("cantEstKmRecorrer"),rs.getFloat("cantEstLitrosComb"),rs.getFloat("impEstTarjetaComb"),rs.getFloat("impEstExceso"),
	        			rs.getString("observaciones"),rs.getString("descAutorizador"),rs.getString("descChofer"),rs.getInt("idMarca"),rs.getInt("idModelo"),
	        			rs.getString("Dominio")
	    				);
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

		return rol;
	}
	
	


	public void modifica(int id, int userAutorizacion, int idChofer, int idVehiculo, float cantEstKmRecorrer, 
			float cantEstLitrosComb, float ImpEstTarjetaComb, float ImpEstExceso, String observaciones, int usrLog){
		sql = " UPDATE DetallesVehiculos SET userAutorizacion=0"+ userAutorizacion +", idChofer=0"+ idChofer +", idVehiculo=0"+ idVehiculo +", "
			+" cantEstKmRecorrer=0"+ cantEstKmRecorrer +", "
			+" cantEstLitrosComb=0"+ cantEstLitrosComb +",ImpEstTarjetaComb=0"+ ImpEstTarjetaComb +",ImpEstExceso=0"+ ImpEstExceso +","
			+" observaciones=observaciones + ' // "+ observaciones +"', "
			+" fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" "
			+" WHERE id=0" + id +" ";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(int idComision, int userAutorizacion, int idChofer, int idVehiculo, float cantEstKmRecorrer, 
			float cantEstLitrosComb, float ImpEstTarjetaComb, float ImpEstExceso, String observaciones, int usrLog) {	
	    sql = "INSERT INTO DetallesVehiculos (idComision, userAutorizacion, fechaAutorizacion, idChofer, idVehiculo, cantEstKmRecorrer, cantEstLitrosComb, "
	    		+ " ImpEstTarjetaComb, ImpEstExceso, observaciones, userCreacion, fechaCreacion, userModificacion, fechaModificacion)"
	    		+ " VALUES (0"+ idComision +", 0"+ userAutorizacion +", GETDATE(), 0"+ idChofer +", 0"+ idVehiculo +", 0"+ cantEstKmRecorrer +", "
	    		+ " 0"+ cantEstLitrosComb +", 0"+ ImpEstTarjetaComb +", 0"+ ImpEstExceso +", '"+ observaciones +"', 0"+ usrLog +", GETDATE(), 0"+ usrLog +", GETDATE())";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int id, int usrLog) {	
	    sql = "DELETE FROM DetallesVehiculos WHERE id=0"+ id +" ";
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


	public int existe(int id, int idComision, int idPersPrograma) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM DetalleVehiculos WHERE idComision=0"+ idComision +" AND idPersonalPorPrograma=0"+ idPersPrograma +" AND id<>0"+id+"";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
}
