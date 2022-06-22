package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Bancos;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;

public class BancosDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	
	
	public BeanContainer<Integer, Bancos> getAllBancosContainer()  {
		 BeanContainer<Integer, Bancos> Container = null;
	    try {
	    	sql = "SELECT id, ISNULL(nombre,'') AS nombre FROM Bancos ORDER BY nombre";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Bancos> (Bancos.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new Bancos(rs.getInt("id"), rs.getString("nombre")));	    			
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
		Bancos a = new Bancos();
		BeanItem<Bancos> item = new BeanItem<Bancos>(a);	
	    return item;
	}
	

	
	
	public Bancos DevuelveBanco(int id) throws SQLException{
		Bancos it = new Bancos();
    	sql = "SELECT id, ISNULL(nombre,'') AS nombre FROM Areas WHERE id=0"+ id +" ";
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		it = new Bancos(rs.getInt("id"), rs.getString("nombre"));
    		}
    	rs.close();
    	con.close();
		return it;
	}
	

//	public void modifica(String nombre, String desc, int usrLog){
//		sql = "UPDATE Areas SET descripcion='" + desc + "', fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE nombre='" + nombre +"'";
//		msg="";
//		cnn.grabarEnBase(sql,msg,usrLog);		
//	}
//	
//	public void agrega(String nom, String desc, int usrLog) {	
//	    sql = "INSERT INTO Areas (nombre, descripcion, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
//	    		+ " VALUES ('"+ nom +"', '"+ desc +"', GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
//	    msg = "";
//	    cnn.grabarEnBase(sql,msg,usrLog);
//	}
//	
//	public void elimina(String nombre, int usrLog) {	
//	    sql = "DELETE FROM Areas WHERE nombre='"+ nombre +"' ";
//	    msg = "";
//	    cnn.grabarEnBase(sql,msg,usrLog);
//	}

	
	
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


	public int existe(int id) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM Bancos WHERE id=" + id +"";
		con=cnn.getConn();
		rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
	

}
