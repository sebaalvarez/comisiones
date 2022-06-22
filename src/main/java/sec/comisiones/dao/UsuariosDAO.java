package sec.comisiones.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import sec.comisiones.mapeos.Roles;
import sec.comisiones.mapeos.Usuarios;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;

import net.ucanaccess.converters.TypesMap.AccessType; 
import net.ucanaccess.ext.FunctionType; 
import net.ucanaccess.jdbc.UcanaccessConnection; 
import net.ucanaccess.jdbc.UcanaccessDriver;

import com.agpro.controles.Conexion;
import com.agpro.controles.EncryptDecrypt;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.sqlcontainer.query.QueryDelegate;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

@SuppressWarnings("unused")
public class UsuariosDAO {
	
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	private EncryptDecrypt enc = new EncryptDecrypt();
 	private String pwd="";
 	
	public BeanContainer<String, Usuarios> getAllUsuariosContainer()  {
		 BeanContainer<String, Usuarios> ContainerUsuarios = null;
	    try {
	    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(password,'') AS password, ISNULL(idEmpleado,'') AS idEmpleado, ISNULL(eliminado,'') AS eliminado "
				+ " FROM usuarios "
				+ " ORDER BY nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
		    ContainerUsuarios = new BeanContainer<String, Usuarios> (Usuarios.class);
			ContainerUsuarios.setBeanIdProperty("nombre");
			
	        while(rs.next()) {
	        	if(rs.getString("password").length()>=20){
	        		pwd=enc.decryptPropertyValue(rs.getString("password"));
	        	} else {
	        		pwd=rs.getString("password");
	        	}
	        	ContainerUsuarios.addBean(new Usuarios(rs.getInt("id"), rs.getString("nombre"),pwd,rs.getInt("idEmpleado"),rs.getBoolean("eliminado")));	    			
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
	    
	    return ContainerUsuarios;
	}
	

	
	public Usuarios getUsuario(String nomUser) throws SQLException {
		Usuarios item = null;
		sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(idEmpleado,'') AS idEmpleado, ISNULL(eliminado,'') AS eliminado "
			+ " FROM usuarios WHERE nombre='"+ nomUser +"' ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		    
	    int a = size(rs); 
		if (a>0) {
			rs.next() ;	  
			item = new Usuarios(rs.getInt("id"),rs.getString("nombre"));
		}
		rs.close();
		con.close();
		return item;    
	}
	
	
	public Usuarios getUsuario(int id) throws SQLException{
		Usuarios usr = new Usuarios();
		
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(password,'') AS password, ISNULL(idEmpleado,'') AS idEmpleado, ISNULL(eliminado,'') AS eliminado "
			+ " FROM usuarios WHERE id=0"+ id +"";
    	con=cnn.getConn();
        rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
		
    		if(rs.getString("password").length()>=20){
    			pwd=enc.decryptPropertyValue(rs.getString("password"));
    		} else {
    			pwd=rs.getString("password");
    		}
    		usr = new Usuarios(rs.getInt("id"), rs.getString("nombre"),pwd,rs.getInt("idEmpleado"),rs.getBoolean("eliminado"));
			}
	return usr;
	}
	
	
	
	public Usuarios getUsuarioLogin(String nom, String password, int rol) {
		Usuarios usr = new Usuarios();
		try{
 //   	sql = "SELECT id, ISNULL(nombre,'') AS nombre, ISNULL(password,'') AS password, ISNULL(idEmpleado,'') AS idEmpleado "
//				+ " FROM usuarios WHERE nombre='"+ nom +"' AND password='"+ password +"'";
    	
		sql = "SELECT u.id AS idUser, u.nombre AS nombreUser, r.id AS idRol, ISNULL(r.nombre,'') AS nombreRol, ISNULL(r.descripcion,'') AS descRol, ru.id AS idRolUser, "
    			+ " ISNULL(u.password,'') AS password, u.idEmpleado, ISNULL(e.nombre,'') AS nombreEmp, ISNULL(e.apellido,'') AS apellidoEmp, ISNULL(e.dni,0) AS dniEmp "
    			+ " FROM Usuarios u INNER JOIN RolesPorUsuarios ru ON u.id= ru.idUsuario "
    			+ "		INNER JOIN Roles r ON ru.idRol=r.id "
    			+ "		LEFT JOIN Empleados e ON u.idEmpleado=e.id "
    			+ " WHERE u.nombre='" + nom + "' /* AND u.password='"+ password +"'*/ AND ru.id=0"+ rol +" AND ISNULL(u.eliminado,0)=0";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	    	int a = size(rs); 
	    	if (a>0) {
	    		rs.next();
	    		if(rs.getString("password").length()>=20){
	        		pwd=enc.decryptPropertyValue(rs.getString("password"));
	        	} else {
	        		pwd=rs.getString("password");
	        	}
	    		if(pwd.equals(password.toString())){
		    		Roles rolus = new Roles(rs.getInt("idRol"), rs.getString("nombreRol"), rs.getString("descRol"));
		    		usr = new Usuarios(rs.getInt("idUser"),rs.getString("nombreUser"),pwd,rolus,rs.getInt("idEmpleado"),rs.getString("nombreEmp"),rs.getString("apellidoEmp"),rs.getInt("dniEmp"));
		    		this.registraIngEgr("",rs.getInt("idUser"), rs.getInt("idRol"),"Ingreso","OK",con);
		    		
	    		} else {
	    			int idUsuario = this.getUsuario(nom).getId();
	    			this.registraIngEgr("",idUsuario,rol,"Ingreso","ERROR",con);
	    			
	    		}
    	
    	
	    	}else{
				int idUsuario = this.getUsuario(nom).getId();
				this.registraIngEgr("",idUsuario,rol,"Ingreso","ERROR",con);
			}
	    		
	    	rs.close();
			con.close();
		}catch(Exception e){
			
		}
			return usr;
		}
	

	public Item getItem()  {
		Usuarios a = new Usuarios();
		BeanItem<Usuarios> item = new BeanItem<Usuarios>(a);	
	    return item;
	}

	
	
	public void modificaPassword(int id, String password, int usrlog){
		sql = "UPDATE usuarios SET password='" + enc.encryptPropertyValue(password) + "',  "
				+ " userModificacion=0"+ usrlog +", fechamodificacion=GETDATE() WHERE id=0" + id +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrlog);		
	}
	
	
	public void modificaUsuario(String nombre, String password, int idEmpleado, Boolean eliminado, int usrlog){
		sql = "UPDATE usuarios SET password='" + enc.encryptPropertyValue(password) + "', eliminado='" + eliminado + "', idEmpleado=0"+ idEmpleado +", "
				+ " userModificacion=0"+ usrlog +", fechamodificacion=GETDATE() WHERE nombre='" + nombre +"'";
		msg="Se actualizaron los datos del Usuario: "+ nombre;
		cnn.grabarEnBase(sql,msg,usrlog);		
	}
	
	public void agregaUsuario(String nom, String pass, int idEmpleado, boolean eliminado, int usrlog) {	
	    sql = "INSERT INTO usuarios (nombre, password, idEmpleado, fechacreacion, userCreacion, fechamodificacion, userModificacion, eliminado) "
				+ " VALUES ('"+ nom +"', '"+ enc.encryptPropertyValue(pass) +"', 0"+ idEmpleado +", GETDATE(), 0"+ usrlog +", GETDATE(), 0"+ usrlog +", 0)";
	    msg = "Se Registro el Usuario "+ nom;
	    cnn.grabarEnBase(sql,msg,usrlog);
	}
	
	public void eliminaUsuario(String nombre, int usrlog) {	
	    sql = "DELETE FROM usuarios WHERE nombre='"+ nombre +"' ";
	    msg = "Se Eliminó el Usuario "+ nombre;
	    cnn.grabarEnBase(sql,msg,usrlog);
	}

	
	public void registraIngEgr(String sql, int nom, int tipousuario, String tipo, String status, Connection conn) {
	    sql = "INSERT INTO logAcceso (idUsuario, idRol, fechaAcceso, accion, status) VALUES (0"+ nom +", 0"+ tipousuario +", GETDATE(),'"+ tipo +"','"+ sql +"')";
	    msg = "";
	    cnn.grabarEnLog(sql,nom,tipousuario,tipo, status,"",conn);
		
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
		sql = "SELECT COUNT(*) As cant FROM Usuarios WHERE nombre='" + nombre +"'";
		existe = cnn.existeRegistro(sql, 1);
	    return existe;
	}
	
	
	public int existe(String nombre, int idEmpleado) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM Usuarios WHERE nombre='" + nombre +"' AND idEmpleado=0"+ idEmpleado +" ";
		existe = cnn.existeRegistro(sql, 1);
	    return existe;

	}
	
	
	
	
	
}
