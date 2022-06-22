package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.Comisiones;
import sec.comisiones.mapeos.EmpleadosPorPrograma;
import sec.comisiones.mapeos.Programas;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;

public class EmpleadosPorProgramaDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, EmpleadosPorPrograma> getAllEmpleadosPorProgramaContainer()  {
		 BeanContainer<Integer, EmpleadosPorPrograma> Container = null;
	    try {
	    	sql = "SELECT ep.id, ISNULL(ep.idCategoria,0) AS idCategoria, ISNULL(ep.idEspecialidad,0) AS idEspecialidad, "
	    		+ "		ISNULL(ep.idPrograma,0) AS idPrograma, ISNULL(p.idArea,0) AS idArea, ISNULL(ep.idEmpleado,0) AS idEmpleado, "
	    		+ " 	ISNULL(CAST(e.dni AS nvarchar(11)),'') +' - '+ ISNULL(e.nombre,'') +', '+ ISNULL(e.apellido,'') AS descEmpleado, "
	    		+ " 	ISNULL(p.nombre,'') AS descPrograma, ISNULL(c.nombre,'') AS descCategoria, ISNULL(esp.nombre,'') AS descEspecialidad, "
	    		+ " 	ISNULL(a.nombre,'') AS descArea   "
	       		+ " FROM EmpleadosPorProgramas ep "
	       		+ "		INNER JOIN Programas p ON ep.idprograma=p.id "
	       		+ " 	INNER JOIN Empleados e ON ep.idEmpleado=e.id "
	       		+ " 	LEFT JOIN Categorias c ON ep.idCategoria=c.id "
	       		+ " 	LEFT JOIN Especialidades esp ON ep.idEspecialidad=esp.id "
	       		+ "		LEFT JOIN Areas a ON p.idArea=a.id "
	       		+ " ORDER BY e.apellido, e.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, EmpleadosPorPrograma> (EmpleadosPorPrograma.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new EmpleadosPorPrograma(
	        			rs.getInt("id"),rs.getInt("idEmpleado"),rs.getInt("idPrograma"),rs.getInt("idCategoria"),rs.getInt("idEspecialidad"),
	        			rs.getString("descEmpleado"),rs.getString("descPrograma"),rs.getString("descCategoria"),
	        			rs.getString("descEspecialidad"),rs.getString("descArea")
	        	));	    			
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

	public BeanContainer<Integer, EmpleadosPorPrograma> getAllEmpleadosPorProgramaContainer(int idPrograma)  {
		 BeanContainer<Integer, EmpleadosPorPrograma> Container = null;
	    try {
	    	sql = "SELECT ep.id, ISNULL(ep.idCategoria,0) AS idCategoria, ISNULL(ep.idEspecialidad,0) AS idEspecialidad, "
   			+ "		ISNULL(ep.idPrograma,0) AS idPrograma, ISNULL(p.idArea,0) AS idArea, ISNULL(ep.idEmpleado,0) AS idEmpleado, "
   		    + " 	ISNULL(CAST(e.dni AS nvarchar(10))+' - '+ e.apellido+', '+ e.nombre,'') AS descEmpleado, ISNULL(p.nombre,'') AS descPrograma, "
   		    + "		ISNULL(a.nombre,'') AS descArea, ISNULL(esp.nombre,'') AS descEspecialidad, ISNULL(c.nombre,'') AS descCategoria "
   			+ " FROM EmpleadosPorProgramas ep "
   			+ "		INNER JOIN Programas p ON ep.idprograma=p.id "
   			+ "		INNER JOIN Areas a ON p.idArea=a.id "
   			+ " 	INNER JOIN Empleados e ON ep.idEmpleado=e.id "
   			+ " 	LEFT JOIN Categorias c ON ep.idCategoria=c.id "
   			+ " 	LEFT JOIN Especialidades esp ON ep.idEspecialidad=esp.id "
	    	+ " WHERE p.id=0"+idPrograma+" "
	    	+ " ORDER BY e.apellido, e.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, EmpleadosPorPrograma> (EmpleadosPorPrograma.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new EmpleadosPorPrograma(
	        			rs.getInt("id"),rs.getInt("idEmpleado"),rs.getInt("idPrograma"),rs.getInt("idCategoria"),rs.getInt("idEspecialidad"),
	        			rs.getString("descEmpleado"),rs.getString("descPrograma"),rs.getString("descCategoria"),
	        			rs.getString("descEspecialidad"),rs.getString("descArea")
	        			));		    			
	//		    System.out.println(" Id: " + rs.getInt("id")+ " nombre: " + rs.getString("descEmpleado") + "  "  ); 
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
	
	public EmpleadosPorPrograma getEmpleadoDeProgramaContainer(int id)  {
		EmpleadosPorPrograma it = new EmpleadosPorPrograma();
	    try {
	    	sql = "SELECT ep.id, ISNULL(ep.idCategoria,0) AS idCategoria, ISNULL(ep.idEspecialidad,0) AS idEspecialidad, "
  			+ "		ISNULL(ep.idPrograma,0) AS idPrograma, ISNULL(p.idArea,0) AS idArea, ISNULL(ep.idEmpleado,0) AS idEmpleado, "
  		    + " 	ISNULL(CAST(e.dni AS nvarchar(10))+' - '+ e.apellido+', '+ e.nombre,'') AS descEmpleado, ISNULL(p.nombre,'') AS descPrograma, "
  		    + "		ISNULL(a.nombre,'') AS descArea, ISNULL(esp.nombre,'') AS descEspecialidad, ISNULL(c.nombre,'') AS descCategoria "
  			+ " FROM EmpleadosPorProgramas ep "
  			+ "		INNER JOIN Programas p ON ep.idprograma=p.id "
  			+ "		INNER JOIN Areas a ON p.idArea=a.id "
  			+ " 	INNER JOIN Empleados e ON ep.idEmpleado=e.id "
  			+ " 	LEFT JOIN Categorias c ON ep.idCategoria=c.id "
  			+ " 	LEFT JOIN Especialidades esp ON ep.idEspecialidad=esp.id "
	    	+ " WHERE ep.id=0"+id+" ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
			
	        while(rs.next()) {
	        	it = new EmpleadosPorPrograma(
	        			rs.getInt("id"),rs.getInt("idEmpleado"),rs.getInt("idPrograma"),rs.getInt("idCategoria"),rs.getInt("idEspecialidad"),
	        			rs.getString("descEmpleado"),rs.getString("descPrograma"),rs.getString("descCategoria"),
	        			rs.getString("descEspecialidad"),rs.getString("descArea")
	        			);		    			
	//		    System.out.println(" Id: " + rs.getInt("id")+ " nombre: " + rs.getString("descEmpleado") + "  "  ); 
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
	    
	    return it;
	}
	
	public Item getItem()  {
		EmpleadosPorPrograma a = new EmpleadosPorPrograma();
		BeanItem<EmpleadosPorPrograma> item = new BeanItem<EmpleadosPorPrograma>(a);	
	    return item;
	}
	
		
	public Item getItemEmpleadosDePrograma(Programas objCat)  {
		
		EmpleadosPorPrograma a = new EmpleadosPorPrograma(objCat);
		BeanItem<EmpleadosPorPrograma> item = new BeanItem<EmpleadosPorPrograma>(a);	
		
//	    System.out.println("fila: " + filas + " Id: " + id + " nombre: " + nombre + " Password: " + password  );   
//		Notification.show("item selected: 1 "+ item.toString(), Notification.Type.HUMANIZED_MESSAGE ); 
	    return item;
	}
	

	

	public void modifica(int id, int idCategoria, int idEspecialidad, int usrLog){
		sql = "UPDATE EmpleadosPorProgramas SET idCategoria=0" + idCategoria + ", idEspecialidad=0"+ idEspecialidad +",  fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" "
				+ " WHERE id=0" + id +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(int idPrograma, int idEmpleado, int idCategoria, int idEspecialidad, int usrLog) {	
	    sql = "INSERT INTO EmpleadosPorProgramas (idPrograma, idEmpleado, idCategoria, idEspecialidad, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES (0"+ idPrograma +", 0"+ idEmpleado +", 0"+ idCategoria +", 0"+ idEspecialidad +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int id, int usrLog) {	
	    sql = "DELETE FROM EmpleadosPorProgramas WHERE id=0"+ id +" ";
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


	public int existe(int idPrograma, int idEmpleado) {
	    int existe=0;
	    try{
	    	sql = "SELECT COUNT(*) As cant FROM EmpleadosPorProgramas WHERE idPrograma=0" + idPrograma +" AND idEmpleado=0"+ idEmpleado +" ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	    	rs.next();
	    	existe = rs.getInt("cant");
	    	rs.close();
	    	con.close();
	    }catch (Exception e){
	    	
	    }finally{
	    	return existe;
	    }
	}
	
	public int getIdEmpleadoPorPrograma(int idPrograma, int idEmpleado) {
	    int existe=0;
	    try{
	    	sql = "SELECT id As id FROM EmpleadosPorProgramas WHERE idPrograma=0" + idPrograma +" AND idEmpleado=0"+ idEmpleado +" ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	    	int a = size(rs); 
	    	if (a>0) {
	    		rs.next();
	    		existe = rs.getInt("id");
	    	}
	    	rs.close();
	    	con.close();
	    }catch (Exception e){
	    	
	    }finally{
	    	return existe;
	    }
	}
	
	
}
