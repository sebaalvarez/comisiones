package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Empleados;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;



public class EmpleadosDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, Empleados> getAllEmpleadosContainer()  {
		 BeanContainer<Integer, Empleados> Container = null;
	    try {
	    	sql = "SELECT id, dni, ISNULL(nombre,'') AS nombre, ISNULL(apellido,'') AS apellido FROM Empleados ORDER BY apellido, nombre";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Empleados> (Empleados.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new Empleados(rs.getInt("id"),rs.getInt("dni"),rs.getString("nombre"),rs.getString("apellido")));	    			
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
	
	
	public BeanContainer<Integer, Empleados> getAllEmpleadosContainer(String nombre)  {
		 BeanContainer<Integer, Empleados> Container = null;
		 
	    try {
	    	String criterio = " ";
			 if (nombre != ""){ criterio = criterio + " AND (dbo.removerTildes(apellido) LIKE dbo.removerTildes('%"+ nombre +"%') OR dbo.removerTildes(nombre) LIKE dbo.removerTildes('%"+ nombre +"%')) ";}
	    	sql = "SELECT id, dni, ISNULL(nombre,'') AS nombre, ISNULL(apellido,'') AS apellido "
	    		+ "	FROM Empleados "
	    		+ " WHERE 1=1 "+ criterio +" "
	    		+ " ORDER BY apellido, nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Empleados> (Empleados.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new Empleados(rs.getInt("id"),rs.getInt("dni"),rs.getString("nombre"),rs.getString("apellido")));	    			
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
	
	
	public BeanContainer<Integer, Empleados> getAllEmpleadosProgramasContainer(int idPrograma)  {
		 BeanContainer<Integer, Empleados> Container = null;
	    try {
	    	sql = "SELECT ISNULL(p.nombre,'') AS programa, ISNULL(p.descripcion,'') AS descripcion, "
   			+ " 	e.id, e.dni, e.nombre, e.apellido, c.nombre AS categoria, esp.nombre AS especialidad  "
   			+ " FROM EmpleadosPorProgramas ep INNER JOIN Programas p ON ep.idprograma=p.id "
   			+ " 	INNER JOIN Empleados e ON ep.idEmpleado=e.id "
   			+ " 	LEFT JOIN Categorias c ON ep.idCategoria=c.id "
   			+ " 	LEFT JOIN Especialidades esp ON ep.idEspecialidad=esp.id "
	    	+ " WHERE p.id=0"+idPrograma+" "
	    	+ " ORDER BY e.apellido, e.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Empleados> (Empleados.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new Empleados(rs.getInt("id"),rs.getInt("dni"),rs.getString("nombre"),rs.getString("apellido")));		    			
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
		Empleados a = new Empleados();
		BeanItem<Empleados> item = new BeanItem<Empleados>(a);	
	    return item;
	}
	
	
	public Empleados DevuelveEmpleado(int dni) throws SQLException{
		Empleados it = new Empleados();
		sql = "SELECT id, dni, ISNULL(nombre,'') AS nombre, ISNULL(apellido,'') AS apellido FROM Empleados WHERE dni=0"+ dni +" ";
		con=cnn.getConn();
		rs = cnn.obtenerCursor(sql, con);
        
    		int a = size(rs); 
    		if (a>0) {
    			rs.next();
    			it = new Empleados(rs.getInt("id"),rs.getInt("dni"), rs.getString("nombre"),rs.getString("apellido"));
    		}
    		rs.close();
		con.close();
		return it;
	}
	
	

	

	public void modifica(int dni, String nombre, String desc, int usrLog){
		sql = "UPDATE Empleados SET nombre='" + nombre + "', apellido='"+ desc +"',  fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE dni=0" + dni +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(int dni, String nom, String desc, int usrLog) {	
	    sql = "INSERT INTO Empleados (dni, nombre, apellido, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES (0"+ dni +", '"+ nom +"', '"+ desc +"', GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int nombre, int usrLog) {	
	    sql = "DELETE FROM Empleados WHERE dni='"+ nombre +"' ";
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


	@SuppressWarnings("finally")
	public int existe(int nombre) {
	    int existe=0;
	    try{
	    	sql = "SELECT COUNT(*) As cant FROM Empleados WHERE dni='" + nombre +"'";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	    	rs.next();
	    	existe = rs.getInt("cant");
	    	
	    } catch(Exception e) {
	    	
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
	
	@SuppressWarnings("finally")
	public int existenRegistrosRelacionados(int nombre) {
	    int existe=0;
	    try{
	    	sql = "SELECT ISNULL(dbo.RegistrosIdEmpleado("+ nombre +"),0) AS cant";
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
