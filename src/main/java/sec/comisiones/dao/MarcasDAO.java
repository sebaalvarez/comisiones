package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Marcas;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class MarcasDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, Marcas> getAllMarcasContainer()  {
		 BeanContainer<Integer, Marcas> Container = null;
	    try {
	    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion FROM Marcas ORDER BY nombre";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	        Container = new BeanContainer<Integer, Marcas> (Marcas.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new Marcas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion")));	    			
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
		Marcas a = new Marcas();
		BeanItem<Marcas> item = new BeanItem<Marcas>(a);	
	    return item;
	}
	
	public Marcas DevuelveMarca(String nom, String descripcion) throws SQLException{
		Marcas it = new Marcas();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion FROM Marcas WHERE nombre='"+ nom +"' ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
       
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		it = new Marcas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"));
    		}
    	rs.close();
    	con.close();
		return it;
	}
	
	
	public Marcas DevuelveMarca(int id) throws SQLException{
		Marcas it = new Marcas();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion FROM Marcas WHERE id=0"+ id +" ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);

    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		it = new Marcas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"));
    		}
    	rs.close();
    	con.close();
		return it;
	}
	

	public void modifica(String nombre, String desc, int usrLog){
		sql = "UPDATE Marcas SET descripcion='" + desc + "', fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
		msg="Se actualizaron los datos de la Provincia: "+ nombre;
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(String nom, String desc, int usrLog) {	
	    sql = "INSERT INTO Marcas (nombre, descripcion, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ nom +"', '"+ desc +"', GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "Se Registro la Provincia "+ nom;
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(String nombre, int usrLog) {	
	    sql = "DELETE FROM Marcas WHERE nombre='"+ nombre +"' ";
	    msg = "Se Eliminó la Provincia "+ nombre;
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
		sql = "SELECT COUNT(*) As cant FROM Marcas WHERE nombre='" + nombre +"'";
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
	    	sql = "SELECT ISNULL(dbo.RegistrosIdMarca('"+ nombre +"'),0) AS cant";
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
