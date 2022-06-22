package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.DetalleDestinos;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;

public class DetalleDestinosDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, DetalleDestinos> getAllDetalleDestinosContainer(int idComision)  {
		 BeanContainer<Integer, DetalleDestinos> Container = null;
	    try {
	    	sql = "SELECT dd.id, dd.idComision, ISNULL(dd.idLocalidad,0) AS idLocalidad, ISNULL(l.idProvincia,0) AS idProvincia, "
    			+ "		ISNULL(l.nombre,'') AS descLocalidad, ISNULL(p.nombre,'') AS descProvincia "
    			+ " FROM DetallesDestinos dd "
    			+ " 	INNER JOIN Localidades l ON dd.idLocalidad=l.id "
    			+ " 	LEFT JOIN Provincias p ON l.idProvincia=p.id "
	    		+ " WHERE dd.idComision=" + idComision;
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, DetalleDestinos> (DetalleDestinos.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new DetalleDestinos(
	        			rs.getInt("id"), rs.getInt("idComision"),rs.getInt("idLocalidad"),rs.getInt("idProvincia"),
	        			rs.getString("descLocalidad"),rs.getString("descProvincia")));	    			
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
		DetalleDestinos a = new DetalleDestinos();
		BeanItem<DetalleDestinos> item = new BeanItem<DetalleDestinos>(a);	
	    return item;
	}

	
	public DetalleDestinos DevuelveDetalleDestinos(int id, int idComision) throws SQLException{
		DetalleDestinos rol = new DetalleDestinos();
		sql = "SELECT dd.id, dd.idComision, ISNULL(dd.idLocalidad,0) AS idLocalidad, ISNULL(l.idProvincia,0) AS idProvincia, "
    		+ "		ISNULL(l.nombre,'') AS descLocalidad, ISNULL(p.nombre,'') AS descProvincia "
    		+ " FROM DetallesDestinos dd "
    		+ " 	INNER JOIN Localidades l ON dd.idLocalidad=l.id "
    		+ " 	LEFT JOIN Provincias p ON l.idProvincia=p.id "
    		+ " WHERE dd.id=0"+ id +" AND dd.idComision=0"+ idComision +" ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		rol = new DetalleDestinos(
    				rs.getInt("id"), rs.getInt("idComision"),rs.getInt("idLocalidad"),rs.getInt("idProvincia"),
        			rs.getString("descLocalidad"),rs.getString("descProvincia"));
    		}
    	rs.close();
    	con.close();
		return rol;
	}
	
	

	public int hayDestinos(int idComision)  {
	    int existe=0;
	    try{
			sql = "SELECT COUNT(*) As cant FROM DetallesDestinos WHERE idComision=0"+ idComision +" ";
			con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
			rs.next();
			existe = rs.getInt("cant");
			rs.close();
			con.close();
	    }catch( SQLException e){
    	
	    }
	    return existe;
	}
	

	public void modifica(int id, int idPersPrograma, int usrLog){
		sql = "UPDATE DetallesDestinos SET idLocalidad = 0"+ idPersPrograma +", fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE id=0" + id +" ";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(int idComision, int idPersPrograma, int usrLog) {	
	    sql = "INSERT INTO DetallesDestinos (idComision, idLocalidad, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES (0"+ idComision +", 0"+ idPersPrograma +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int id, int usrLog) {	
	    sql = "DELETE FROM DetallesDestinos WHERE id=0"+ id +" ";
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


	public int existe(int id, int idComision, int idPersPrograma) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM DetallesDestinos WHERE idComision=0"+ idComision +" AND idLocalidad=0"+ idPersPrograma +" AND id<>0"+id+"";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
}
