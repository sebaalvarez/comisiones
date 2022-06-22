package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Modelos;
import sec.comisiones.mapeos.Marcas;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class ModelosDAO {
	private Conexion cnn = new Conexion();
	Connection con = null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, Modelos> getAllModelosContainer()  {
		 BeanContainer<Integer, Modelos> Container = null;
	    try {
	    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion, ISNULL(idMarca,'') AS marca FROM Modelos ORDER BY nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	        Container = new BeanContainer<Integer, Modelos> (Modelos.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Marcas ar= new MarcasDAO().DevuelveMarca(rs.getInt("marca"));

	        	Container.addBean(new Modelos(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"), ar));	    			
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
	
	
	public BeanContainer<Integer, Modelos> getAllModelosContainer(int idMarca)  {
		 BeanContainer<Integer, Modelos> Container = null;
	    try {
	    	sql = " SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion, ISNULL(idMarca,'') AS marca FROM Modelos "
	    		+ " WHERE idMarca=0"+ idMarca+" "
	    		+ " ORDER BY nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	        Container = new BeanContainer<Integer, Modelos> (Modelos.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Marcas ar= new MarcasDAO().DevuelveMarca(rs.getInt("marca"));

	        	Container.addBean(new Modelos(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"), ar));	    			
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
		Modelos a = new Modelos();
		BeanItem<Modelos> item = new BeanItem<Modelos>(a);	
	    return item;
	}
	
	
	public Modelos DevuelveProg(String nom, String descripcion) throws SQLException{
		Modelos it = new Modelos();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion, ISNULL(idMarca,'') AS marca FROM Modelos WHERE nombre='"+ nom +"' ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		Marcas ar= new MarcasDAO().DevuelveMarca(rs.getInt("marca"));
    		it = new Modelos(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"), ar);
    		}
    	rs.close();
    	con.close();
		return it;
	}
	
	
	public Modelos DevuelveModelo(int id) throws SQLException{
		Modelos it = new Modelos();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion, ISNULL(idMarca,'') AS marca FROM Modelos WHERE id=0"+ id +" ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		Marcas ar= new MarcasDAO().DevuelveMarca(rs.getInt("marca"));
    		it = new Modelos(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"), ar);
    		}
    	rs.close();
    	con.close();
		return it;
	}
	

	public void modifica(String nombre, String desc, int idMarca, int usrLog){
		sql = "UPDATE Modelos SET descripcion='" + desc + "', idMarca=0"+ idMarca +", fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
		msg="Se actualizaron los datos del Modelo: "+ nombre;
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(String nom, String desc, int idMarca, int usrLog) {	
	    sql = "INSERT INTO Modelos (nombre, descripcion, idMarca, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ nom +"', '"+ desc +"', 0"+ idMarca +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "Se Registro el Modelo "+ nom;
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(String nombre, int usrLog) {	
	    sql = "DELETE FROM Modelos WHERE nombre='"+ nombre +"' ";
	    msg = "Se Eliminó el Modelo "+ nombre;
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
		sql = "SELECT COUNT(*) As cant FROM Modelos WHERE nombre='" + nombre +"'";
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
	    	sql = "SELECT ISNULL(dbo.RegistrosIdModelo('"+ nombre +"'),0) AS cant";
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
