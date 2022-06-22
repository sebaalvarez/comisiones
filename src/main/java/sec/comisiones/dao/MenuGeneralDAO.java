package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.MenuGeneral;

import com.agpro.controles.Conexion;
import com.vaadin.data.util.BeanContainer;


public class MenuGeneralDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";

	
	
	public ResultSet getMenuRolPadre(String rol)  {
    	try {
    	    sql = "SELECT g.id, g.padre, g.hijo, mr.idRol, r.nombre " 
    	    		+" FROM MenuGeneral g LEFT JOIN MenuPorRol mr ON g.id=mr.idMenu LEFT JOIN Roles r ON mr.idRol=r.id "
    	    		+" WHERE ISNULL(g.hijo,'')='' AND r.nombre='"+ rol +"' ORDER BY g.orden";
    	    con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return rs;
	}


	public ResultSet getMenuRolHijo(String rol, String padre)  {
    	try {
      	    sql = "SELECT g.id, g.padre, g.hijo, mr.idRol, r.nombre " 
    	    		+" FROM MenuGeneral g LEFT JOIN MenuPorRol mr ON g.id=mr.idMenu LEFT JOIN Roles r ON mr.idRol=r.id "
    	    		+" WHERE g.padre='"+ padre +"' AND ISNULL(g.hijo,'')<>'' AND r.nombre='"+ rol +"' ORDER BY g.orden";
      	  con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return rs;
	}
	
	
	
	public BeanContainer<String, MenuGeneral> getAllMenu(int rol)  {
		 BeanContainer<String, MenuGeneral> ContainerMenuGeneral = null;
	    try {
				sql="SELECT a.nPadre, a.nHijo, a.padre, a.hijo, a.idMenu, b.idRol, "
					+ " CASE WHEN idMenuRol IS NULL THEN 0 ELSE idMenuRol END AS idMenuRol, CASE WHEN idMenuRol IS NULL THEN 0 ELSE 1 END AS activo "
					+" FROM "
					+"	    (SELECT DISTINCT CASE WHEN ISNULL(g.hijo,'')='' THEN g.padre ELSE '                  +' END AS nPadre, "
					+"	        CASE WHEN ISNULL(g.hijo,'')<>'' THEN g.hijo ELSE '-------' END AS nHijo, "
					+"	        g.padre, ISNULL(g.hijo,'') AS hijo, g.id AS idMenu, g.orden"
					+"	      FROM MenuGeneral g "
					+"	    ) a LEFT JOIN "
					+"	    (SELECT CASE WHEN r.id IS NULL THEN 0 ELSE mr.id END AS idMenuRol, g.id As idMenu, mr.idRol AS idRol "
					+"	        FROM MenuGeneral g INNER JOIN MenuPorRol mr ON g.id=mr.idMenu  "
					+"	            INNER JOIN Roles r ON mr.idRol=r.id AND ISNULL(r.id,'') IN (0"+ rol +")   "
					+"	        ) b ON a.idMenu=b.idMenu "
					+ "ORDER BY a.orden";
				
				con=cnn.getConn();
				rs = cnn.obtenerCursor(sql, con);
	   
				ContainerMenuGeneral = new BeanContainer<String, MenuGeneral> (MenuGeneral.class);
				ContainerMenuGeneral.setBeanIdProperty("id");
			
				while(rs.next()) {
					ContainerMenuGeneral.addBean(new MenuGeneral(
				    	rs.getInt("idMenu"), rs.getInt("idMenuRol"),rs.getString("nPadre"), rs.getString("nHijo"), 
				    	rs.getString("padre"), rs.getString("hijo"),rs.getInt("activo"))); 

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
	    return ContainerMenuGeneral;
	}
	
	
	public BeanContainer<String, MenuGeneral> getAllMenuGeneral()  {
		 BeanContainer<String, MenuGeneral> ContainerMenuGeneral = null;
	    try {
		    	sql= "SELECT g.id, g.padre, g.hijo, g.orden FROM MenuGeneral g ORDER BY g.orden";  	
		    	con=cnn.getConn();
		    	rs = cnn.obtenerCursor(sql, con);
		   
		    	ContainerMenuGeneral = new BeanContainer<String, MenuGeneral> (MenuGeneral.class);
		    	ContainerMenuGeneral.setBeanIdProperty("id");
				
		        while(rs.next()) {
		        	ContainerMenuGeneral.addBean(new MenuGeneral(rs.getInt("id"), rs.getString("padre"), rs.getString("hijo"),rs.getInt("orden"))); 
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
	    return ContainerMenuGeneral;
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

	
	
	public void agregaARol(int idMenu, int rol, int usrLog) {	
	    sql = "INSERT INTO MenuPorRol (idMenu, idRol, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES (0"+ idMenu +", 0"+ rol +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void eliminaARol(int id, int usrLog) {	
	    sql = "DELETE FROM MenuPorRol WHERE id=0"+ id +"";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	
	public void agregaMenu(String padre, String hijo, int orden, int usrLog) {	
	    sql = "INSERT INTO MenuGeneral (padre, hijo, orden, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ padre +"', '"+ hijo +"', 0"+ orden +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
}
