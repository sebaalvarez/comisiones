package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Categorias;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class CategoriasDAO {

	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, Categorias> getAllCategoriasContainer()  {
		 BeanContainer<Integer, Categorias> Container = null;
	    try {
	    	sql = " SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion, ISNULL(montoProvincial, 0.0) AS montoProvincial,"
	    		+" 		ISNULL(montoNacional, 0.0) AS montoNacional, ISNULL(montoInternacional, 0.0) AS montoInternacional "
	    		+" 	FROM Categorias ORDER BY nombre";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Categorias> (Categorias.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new Categorias(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"),
	        					rs.getFloat("montoProvincial"),rs.getFloat("montoNacional"),rs.getFloat("montoInternacional")));	    			

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
		Categorias a = new Categorias();
		BeanItem<Categorias> item = new BeanItem<Categorias>(a);	
	    return item;
	}
	
	public Categorias DevuelveCategoria(int id) {
		Categorias rol = null;
    	try {
    		sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion, ISNULL(montoProvincial, 0.0) AS montoProvincial, "
    			+" 		ISNULL(montoNacional, 0.0) AS montoNacional, ISNULL(montoInternacional, 0.0) AS montoInternacional "	
    			+"	FROM Categorias WHERE id=0"+ id +" ";
    		con=cnn.getConn();
			rs = cnn.obtenerCursor(sql, con);
        
			int a = size(rs); 
			if (a>0) {
				rs.next();
				rol = new Categorias(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"),
						rs.getFloat("montoProvincial"),rs.getFloat("montoNacional"),rs.getFloat("montoInternacional"));
			}
			rs.close();
    		con.close();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return rol;
		}
		
	}
	
	public Categorias DevuelveCategoria(String nom, String descripcion) throws SQLException{
		Categorias rol = new Categorias();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion, ISNULL(montoProvincial, 0.0) AS montoProvincial, "
    		+" 		ISNULL(montoNacional, 0.0) AS montoNacional, ISNULL(montoInternacional, 0.0) AS montoInternacional "
    		+"	FROM Categorias WHERE nombre='"+ nom +"' ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		rol = new Categorias(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"),
    				rs.getFloat("montoProvincial"),rs.getFloat("montoNacional"),rs.getFloat("montoInternacional"));
    		}
    	rs.close();
    	con.close();
		return rol;
	}
	
	

	

	public void modifica(String nombre, String desc, float montoProvincial, float montoNacional, float montoInternacional, int usrLog){
		sql = "UPDATE Categorias SET descripcion='" + desc + "', montoProvincial=0"+ montoProvincial +", montoNacional=0"+montoNacional+", "
			+ " montoInternacional=0"+montoInternacional+", fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
		msg="Se actualizaron los datos de la Categoria: "+ nombre;
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(String nom, String desc, float montoProvincial, float montoNacional, float montoInternacional, int usrLog) {	
	    sql = "INSERT INTO Categorias (nombre, descripcion, montoProvincial, montoNacional, montoInternacional, "
	    		+ "		fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ nom +"', '"+ desc +"', 0"+ montoProvincial +", 0"+ montoNacional +", 0"+ montoInternacional +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "Se Registro la Categoria: "+ nom;
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(String nombre, int usrLog) {	
	    sql = "DELETE FROM Categorias WHERE nombre='"+ nombre +"' ";
	    msg = "Se Eliminó la Categoria: "+ nombre;
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
		sql = "SELECT COUNT(*) As cant FROM Categorias WHERE nombre='" + nombre +"'";
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
	    	sql = "SELECT ISNULL(dbo.RegistrosIdCategoria('"+ nombre +"'),0) AS cant";
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
