package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Provincias;
import sec.comisiones.mapeos.Localidades;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class LocalidadesDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	
	public BeanContainer<Integer, Localidades> getAllLocalidadesContainer()  {
		 BeanContainer<Integer, Localidades> Container = null;
	    try {
	    	sql = "SELECT l.id, ISNULL(l.nombre,'') AS nombre, ISNULL(l.kmDesdeOrigen,0) AS kmDesdeOrigen, ISNULL(l.idProvincia,'') AS idProvincia, "
	    		+ " ISNULL(p.nombre,'') AS descProvincia "
	    		+ " FROM Localidades l LEFT JOIN Provincias p ON l.idProvincia=p.id "
	    		+ " ORDER BY l.nombre  ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Localidades> (Localidades.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
//	        	Provincias ar= new ProvinciasDAO().DevuelveProv(rs.getInt("provincia"));

	        	Container.addBean(new Localidades(rs.getInt("id"), rs.getString("nombre"),rs.getFloat("kmDesdeOrigen"), rs.getInt("idProvincia"), rs.getString("descProvincia")));	    			
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
	

	public BeanContainer<Integer, Localidades> getAllLocalidadesContainer(int idProvincia)  {
		 BeanContainer<Integer, Localidades> Container = null;
	    try {
	    	sql = "SELECT l.id, ISNULL(l.nombre,'') AS nombre, ISNULL(l.kmDesdeOrigen,0) AS kmDesdeOrigen, ISNULL(l.idProvincia,'') AS idProvincia, "
	    		+ " ISNULL(p.nombre,'') AS descProvincia "
	    		+ " FROM Localidades l LEFT JOIN Provincias p ON l.idProvincia=p.id "
	    		+ " WHERE l.idProvincia="+ idProvincia+" "
	    		+ " ORDER BY l.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Localidades> (Localidades.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	     //   	Provincias ar= new ProvinciasDAO().DevuelveProv(rs.getInt("provincia"));

	        	Container.addBean(new Localidades(rs.getInt("id"), rs.getString("nombre"),rs.getFloat("kmDesdeOrigen"), rs.getInt("idProvincia"), rs.getString("descProvincia")));	    			
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
		Localidades a = new Localidades();
		BeanItem<Localidades> item = new BeanItem<Localidades>(a);	
	    return item;
	}
	
	
	public Localidades DevuelveLocalidad(String nom) throws SQLException{
		Localidades it = new Localidades();
    	sql = "SELECT l.id, ISNULL(l.nombre,'') AS nombre, ISNULL(l.kmDesdeOrigen,0) AS kmDesdeOrigen, ISNULL(l.idProvincia,'') AS provincia, "
    			+ "ISNULL(p.nombre,'') AS descProvincia  "
    			+ " FROM Localidades l LEFT JOIN Provincias p ON l.idProvincia=p.id "
    			+ " WHERE l.nombre='"+ nom +"' ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
   // 		Provincias ar= new ProvinciasDAO().DevuelveProv(rs.getInt("provincia"));
    		it = new Localidades(rs.getInt("id"), rs.getString("nombre"),rs.getFloat("kmDesdeOrigen"), rs.getInt("idProvincia"), rs.getString("descProvincia") );
    		}
    	rs.close();
    	con.close();
		return it;
	}
	
	
	public Localidades DevuelveLocalidad(int id) throws SQLException{
		Localidades it = new Localidades();
    	sql = "SELECT l.id, ISNULL(l.nombre,'') AS nombre, ISNULL(l.kmDesdeOrigen,0) AS kmDesdeOrigen, ISNULL(l.idProvincia,'') AS provincia, "
    			+ " ISNULL(p.nombre,'') AS descProvincia "
    			+ " FROM Localidades l LEFT JOIN Provincias p ON l.idProvincia=p.id "
    			+ " WHERE l.id=0"+ id +" ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
 //   		Provincias ar= new ProvinciasDAO().DevuelveProv(rs.getInt("provincia"));
    		it = new Localidades(rs.getInt("id"), rs.getString("nombre"),rs.getFloat("kmDesdeOrigen"), rs.getInt("idProvincia"), rs.getString("descProvincia"));
    		}
    	rs.close();
    	con.close();
		return it;
	}
	

	public void modifica(String nombre, float kmDesdeOrigen, int idProvincia, int usrLog){
		sql = "UPDATE Localidades SET kmDesdeOrigen=0" + kmDesdeOrigen + ", idProvincia=0"+ idProvincia +", fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
		msg="Se actualizaron los datos de la Localidad: "+ nombre;
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(String nom, float kmDesdeOrigen, int idProvincia, int usrLog) {	
	    sql = "INSERT INTO Localidades (nombre, kmDesdeOrigen, idProvincia, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ nom +"', 0"+ kmDesdeOrigen +", 0"+ idProvincia +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "Se Registro la Localidad "+ nom;
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(String nombre, int usrLog) {	
	    sql = "DELETE FROM Localidades WHERE nombre='"+ nombre +"' ";
	    msg = "Se Eliminó la Localidad "+ nombre;
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
		sql = "SELECT COUNT(*) As cant FROM Localidades WHERE nombre='" + nombre +"'";
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
	    	sql = "SELECT ISNULL(dbo.RegistrosIdLocalidades('"+ nombre +"'),0) AS cant";
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
