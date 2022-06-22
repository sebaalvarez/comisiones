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



public class RolesPorUsuariosDAO {

	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	

	public BeanContainer<Integer, RolesPorUsuarios> getAllRolesDeUsuarioContainer()  {
		 BeanContainer<Integer, RolesPorUsuarios> ContainerRoles = null;
	    try {
	    	sql = " SELECT u.id AS idUser, u.nombre AS nombreUser, r.id AS idRol, ISNULL(r.nombre,'') AS nombreRol, ISNULL(r.descripcion,'') AS descRol, ru.id AS idRolUser "
	    		+ " FROM Usuarios u LEFT JOIN RolesPorUsuarios ru ON u.id= ru.idUsuario LEFT JOIN Roles r ON ru.idRol=r.id "
	    		+ " ORDER BY r.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	        ContainerRoles = new BeanContainer<Integer, RolesPorUsuarios> (RolesPorUsuarios.class);
	        ContainerRoles.setBeanIdProperty("id");
	        
	        while(rs.next()) {
	        	Usuarios usr = new Usuarios (rs.getInt("idUser"), rs.getString("nombreUser"));
	        	Roles rol = new Roles(rs.getInt("idRol"),  rs.getString("nombreRol"),  rs.getString("descRol"));
	        	ContainerRoles.addBean(new RolesPorUsuarios(rs.getInt("idRolUser"), rol, usr));	    			
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
	    
	    return ContainerRoles;
	}
	
	
	public BeanContainer<Integer, RolesPorUsuarios> getRolesDeUsuarioContainer(String nomUser)  {
		 BeanContainer<Integer, RolesPorUsuarios> ContainerRoles = null;
	    try {
	    	sql = " SELECT u.id AS idUser, u.nombre AS nombreUser, r.id AS idRol, ISNULL(r.nombre,'') AS nombreRol, ISNULL(r.descripcion,'') AS descRol, ru.id AS idRolUser "
	    		+ " FROM Usuarios u LEFT JOIN RolesPorUsuarios ru ON u.id= ru.idUsuario LEFT JOIN Roles r ON ru.idRol=r.id "
	    		+ " WHERE u.nombre='" + nomUser + "' "
	    		+ " ORDER BY r.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	        ContainerRoles = new BeanContainer<Integer, RolesPorUsuarios> (RolesPorUsuarios.class);
	        ContainerRoles.setBeanIdProperty("id");
	        
	        while(rs.next()) {
	        	Usuarios usr = new Usuarios (rs.getInt("idUser"), rs.getString("nombreUser"));
	        	Roles rol = new Roles(rs.getInt("idRol"),  rs.getString("nombreRol"),  rs.getString("descRol"));
	        	ContainerRoles.addBean(new RolesPorUsuarios(rs.getInt("idRolUser"), rol, usr));	    			
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
	    
	    return ContainerRoles;
	}
	
	
	public Item getItemRolesDeUsuario(Usuarios objUsr)  {
		

		RolesPorUsuarios a = new RolesPorUsuarios(objUsr);
		BeanItem<RolesPorUsuarios> item = new BeanItem<RolesPorUsuarios>(a);	
		
//	    System.out.println("fila: " + filas + " Id: " + id + " nombre: " + nombre + " Password: " + password  );   
//		Notification.show("item selected: 1 "+ item.toString(), Notification.Type.HUMANIZED_MESSAGE ); 
	    return item;
	}
	
	


	public int existe(String especialidad, int empleado) throws SQLException  {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM EmpleadosPorEspecialidades WHERE especialidad='" + especialidad +"' AND empleado=0"+ empleado +"";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
	
	
	
	public Item getItem()  {
		RolesPorUsuarios a = new RolesPorUsuarios();
		BeanItem<RolesPorUsuarios> item = new BeanItem<RolesPorUsuarios>(a);	
	    return item;
	}
	



	public void modifica(String nombre, String desc, int usrLog){
		sql = "UPDATE roles SET descripcion='" + desc + "', fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
		msg="Se actualizaron los datos del Usuario: "+ nombre;
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(int idUsuario, int idRol, int usrLog) {	
	    sql = "INSERT INTO rolesPorUsuarios (idUsuario, idRol, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES (0"+ idUsuario +", 0"+ idRol +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int idUsuario, int idRol, int usrLog) {	
	    sql = "DELETE FROM rolesPorUsuarios WHERE idUsuario=0"+ idUsuario +" AND idRol=0"+ idRol +" ";
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



	
	public int existe(int idUsuario, int idRol) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM RolesPorUsuarios WHERE idUsuario=0" + idUsuario +" AND idRol=0"+ idRol +"";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
	
}
