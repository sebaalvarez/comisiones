package sec.comisiones.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Roles;
import sec.comisiones.mapeos.RolesPorUsuarios;
import sec.comisiones.mapeos.Usuarios;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;



public class RolesDAO {

	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, Roles> getAllRolesContainer()  {
		 BeanContainer<Integer, Roles> Container = null;
	    try {
	    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion FROM Roles ORDER BY nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Roles> (Roles.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new Roles(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion")));	    			
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
	

	
	public BeanContainer<Integer, RolesPorUsuarios> getRolesDeUsuarioContainer(String nomUser)  {
		 BeanContainer<Integer, RolesPorUsuarios> Container = null;
	    try {
	    	sql = "SELECT u.id AS idUser, u.nombre AS nombreUser, r.id AS idRol, ISNULL(r.nombre,'') AS nombreRol, ISNULL(r.descripcion,'') AS descRol, ru.id AS idRolUser "
	    		+ " FROM Usuarios u LEFT JOIN RolesPorUsuarios ru ON u.id= ru.idUsuario LEFT JOIN Roles r ON ru.idRol=r.id "
	    		+ " WHERE u.nombre='" + nomUser + "' "
	    		+ " ORDER BY r.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	        Container = new BeanContainer<Integer, RolesPorUsuarios> (RolesPorUsuarios.class);
	        Container.setBeanIdProperty("id");
	        
	        while(rs.next()) {
	        	Usuarios usr = new Usuarios (rs.getInt("idUser"), rs.getString("nombreUser"));
	        	Roles rol = new Roles(rs.getInt("idRol"),  rs.getString("nombreRol"),  rs.getString("descRol"));
	        	Container.addBean(new RolesPorUsuarios(rs.getInt("idRolUser"), rol, usr));	    			
		//	    System.out.println("user: " + nomUser + " Id: " + id + " nombre: " + nombre + " Password: " + password  ); 
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
		Roles a = new Roles();
		BeanItem<Roles> item = new BeanItem<Roles>(a);	
	    return item;
	}
	
	public Roles DevuelveRol(String nom, String descripcion) throws SQLException{
		Roles it = new Roles();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(descripcion,'') AS descripcion FROM roles WHERE nombre='"+ nom +"' ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		it = new Roles(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"));
    		}
    	rs.close();
		con.close();
		return it;
	}
	
	

	

	public void modifica(String nombre, String desc, int usrLog){
		sql = "UPDATE roles SET descripcion='" + desc + "', fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
		msg="Se actualizaron los datos del Usuario: "+ nombre;
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(String nom, String desc, int usrLog) {	
	    sql = "INSERT INTO roles (nombre, descripcion, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ nom +"', '"+ desc +"', GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "Se Registro el Rol "+ nom;
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(String nombre, int usrLog) {	
	    sql = "DELETE FROM roles WHERE nombre='"+ nombre +"' ";
	    msg = "Se Eliminó el rol "+ nombre;
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
		sql = "SELECT COUNT(*) As cant FROM Roles WHERE nombre='" + nombre +"'";
		existe = cnn.existeRegistro(sql, 1);
	    return existe;
	}
	
	public int existeRolUsuario(int usuario, int rol) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM RolesPorUsuarios WHERE idUsuario=0" + usuario +" AND idRol=0"+ rol +"";
		existe = cnn.existeRegistro(sql, 1);
	    return existe;
	}
	
	

	
	
}
