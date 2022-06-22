package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Areas;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class AreasDAO {

	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, Areas> getAllAreasContainer()  {
		 BeanContainer<Integer, Areas> Container = null;
	    try {
	    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion FROM Areas ORDER BY nombre";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Areas> (Areas.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new Areas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion")));	    			
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
		Areas a = new Areas();
		BeanItem<Areas> item = new BeanItem<Areas>(a);	
	    return item;
	}
	
	public Areas DevuelveArea(String nom, String descripcion) throws SQLException{
		Areas it = new Areas();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion FROM Areas WHERE nombre='"+ nom +"' ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		it = new Areas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"));
    		}
    	rs.close();
    	con.close();
		return it;
	}
	
	
	public Areas DevuelveArea(int id) throws SQLException{
		Areas it = new Areas();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion FROM Areas WHERE id=0"+ id +" ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		it = new Areas(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"));
    		}
    	rs.close();
    	con.close();
		return it;
	}
	

	public void modifica(String nombre, String desc, int usrLog){
		sql = "UPDATE Areas SET descripcion='" + desc + "', fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(String nom, String desc, int usrLog) {	
	    sql = "INSERT INTO Areas (nombre, descripcion, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ nom +"', '"+ desc +"', GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(String nombre, int usrLog) {	
	    sql = "DELETE FROM Areas WHERE nombre='"+ nombre +"' ";
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
		sql = "SELECT COUNT(*) As cant FROM Areas WHERE nombre='" + nombre +"'";
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
	    	sql = "SELECT ISNULL(dbo.RegistrosIdArea('"+ nombre +"'),0) AS cant";
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

