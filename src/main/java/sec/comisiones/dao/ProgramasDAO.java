package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Areas;
import sec.comisiones.mapeos.Programas;
import sec.comisiones.dao.AreasDAO;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class ProgramasDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, Programas> getAllProgramasContainer()  {
		 BeanContainer<Integer, Programas> Container = null;
	    try {
	    	sql = "SELECT p.id, ISNULL(p.nombre,'') AS nombre, ISNULL(p.descripcion,'') AS descripcion, ISNULL(p.idArea,'') AS idArea, ISNULL(p.idEmpleado,'') AS idEmpleado, "
	    		+ " 	 ISNULL(a.nombre,'') AS descArea, ISNULL(e.apellido,'')+', '+ISNULL(e.nombre,'') AS descEmpleado"
	    		+ " FROM Programas p "
	    		+ "		LEFT JOIN Areas a ON p.idArea=a.id "
	    		+ "		LEFT JOIN Empleados e ON p.idEmpleado=e.id "
	    		+ " ORDER BY p.nombre ";

	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Programas> (Programas.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
//	        	Areas ar= new AreasDAO().DevuelveArea(rs.getInt("area"));

	        	Container.addBean(new Programas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"), rs.getInt("idArea"),rs.getInt("idEmpleado"),rs.getString("descArea"),rs.getString("descEmpleado")));	    			
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
	

	public BeanContainer<Integer, Programas> getAllProgramasContainer(int idArea)  {
		 BeanContainer<Integer, Programas> Container = null;
	    try {
	    	sql = "SELECT p.id, ISNULL(p.nombre,'') AS nombre, ISNULL(p.descripcion,'') AS descripcion, ISNULL(p.idArea,'') AS idArea, ISNULL(p.idEmpleado,'') AS idEmpleado, "
		    	+ " 	 ISNULL(a.nombre,'') AS descArea, ISNULL(e.apellido,'')+', '+ISNULL(e.nombre,'') AS descEmpleado"
		    	+ " FROM Programas p "
		    	+ "		LEFT JOIN Areas a ON p.idArea=a.id "
		    	+ "		LEFT JOIN Empleados e ON p.idEmpleado=e.id "
	    		+ " WHERE p.idArea=0"+idArea+" "
	    		+ " ORDER BY p.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Programas> (Programas.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
//	        	Areas ar= new AreasDAO().DevuelveArea(rs.getInt("area"));

	        	Container.addBean(new Programas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"), rs.getInt("idArea"),rs.getInt("idEmpleado"),rs.getString("descArea"),rs.getString("descEmpleado")));	    			
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
		Programas a = new Programas();
		BeanItem<Programas> item = new BeanItem<Programas>(a);	
	    return item;
	}
	
	
	public Programas DevuelveProg(String nom, String descripcion) throws SQLException{
		Programas it = new Programas();
		sql = "SELECT p.id, ISNULL(p.nombre,'') AS nombre, ISNULL(p.descripcion,'') AS descripcion, ISNULL(p.idArea,'') AS idArea, ISNULL(p.idEmpleado,'') AS idEmpleado, "
		    + " 	 ISNULL(a.nombre,'') AS descArea, ISNULL(e.apellido,'')+', '+ISNULL(e.nombre,'') AS descEmpleado"
		    + " FROM Programas p "
		    + "		LEFT JOIN Areas a ON p.idArea=a.id "
		    + "		LEFT JOIN Empleados e ON p.idEmpleado=e.id "
    		+ " WHERE p.nombre='"+ nom +"' ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		Areas ar= new AreasDAO().DevuelveArea(rs.getInt("area"));
    		it = new Programas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"), rs.getInt("idArea"),rs.getInt("idEmpleado"),rs.getString("descArea"),rs.getString("descEmpleado"));
    		}
    	rs.close();
    	con.close();
		return it;
	}
	
	
	public Programas DevuelveProg(int id) throws SQLException{
		Programas it = new Programas();
		sql = "SELECT p.id, ISNULL(p.nombre,'') AS nombre, ISNULL(p.descripcion,'') AS descripcion, ISNULL(p.idArea,'') AS idArea, ISNULL(p.idEmpleado,'') AS idEmpleado, "
		    	+ " 	 ISNULL(a.nombre,'') AS descArea, ISNULL(e.apellido,'')+', '+ISNULL(e.nombre,'') AS descEmpleado"
		    	+ " FROM Programas p "
		    	+ "		LEFT JOIN Areas a ON p.idArea=a.id "
		    	+ "		LEFT JOIN Empleados e ON p.idEmpleado=e.id "
    		+ " WHERE p.id=0"+ id +" ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
   // 		Areas ar= new AreasDAO().DevuelveArea(rs.getInt("area"));
    		it = new Programas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"), rs.getInt("idArea"),rs.getInt("idEmpleado"),rs.getString("descArea"),rs.getString("descEmpleado"));
    		}
    	rs.close();
    	con.close();
		return it;
	}
	

	public void modifica(String nombre, String desc, int idArea, int idEmpleado, int usrLog){
		sql = "UPDATE Programas SET descripcion='" + desc + "', idArea=0"+ idArea +", idEmpleado=0"+ idEmpleado +" , fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(String nom, String desc, int idArea, int idEmpleado, int usrLog) {	
	    sql = "INSERT INTO Programas (nombre, descripcion, idArea, idEmpleado, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ nom +"', '"+ desc +"', 0"+ idArea +", 0"+ idEmpleado +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(String nombre, int usrLog) {	
	    sql = "DELETE FROM Programas WHERE nombre='"+ nombre +"' ";
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


	public int existe(String nombre) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM Programas WHERE nombre='" + nombre +"'";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
	
	
	@SuppressWarnings("finally")
	public int existenRegistrosRelacionados(String nombre) {
	    int existe=0;
	    try{
	    	sql = "SELECT ISNULL(dbo.RegistrosIdPrograma('"+ nombre +"'),0) AS cant";
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
	
	
}
