package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.DetalleComisionados;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;

public class DetalleComisionadosDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	public BeanContainer<Integer, DetalleComisionados> getAllDetalleComisionadosContainer(int idComision)  {
		 BeanContainer<Integer, DetalleComisionados> Container = null;
	    try {
	    	sql = "SELECT dc.id, dc.idComision, ISNULL(dc.idPrograma,0) AS idPrograma, ISNULL(p.idArea,0) AS idArea, ISNULL(dc.idEmpleado,0) AS idEmpleado, "
	    		+ "		ISNULL(dc.idTipoComisionado,'') AS idTipoComisionado, "
	    		+ " 	ISNULL(e.apellido+', '+ e.nombre,'') AS descPersonal, ISNULL(p.nombre,'') AS descPrograma, ISNULL(a.nombre,'') AS descArea "
	    		+ " FROM DetallesComisionados dc " 
	    //		+ " 	INNER JOIN EmpleadosPorProgramas ep ON dc.idPersonalPorPrograma=ep.id "
	    		+ " 	INNER JOIN Empleados e ON dc.idEmpleado=e.id "
	    		+ "		LEFT JOIN Programas p ON dc.idPrograma=p.id "
	    		+ "		LEFT JOIN Areas a ON p.idArea=a.id "
	    		+ " WHERE dc.idComision=" + idComision;
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, DetalleComisionados> (DetalleComisionados.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	Container.addBean(new DetalleComisionados(rs.getInt("id"), rs.getInt("idComision"),
	        			rs.getInt("idEmpleado"),rs.getInt("idArea"),rs.getInt("idPrograma"),rs.getInt("idTipoComisionado"),
	        				rs.getString("descPersonal"),rs.getString("descPrograma"),rs.getString("descArea")));	    			
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
		DetalleComisionados a = new DetalleComisionados();
		BeanItem<DetalleComisionados> item = new BeanItem<DetalleComisionados>(a);	
	    return item;
	}

	
	public DetalleComisionados DevuelveDetalleComisionados(int id, int idComision) throws SQLException{
		DetalleComisionados rol = new DetalleComisionados();
		sql = "SELECT dc.id, dc.idComision, ISNULL(dc.idPrograma,0) AS idPrograma, ISNULL(p.idArea,0) AS idArea, ISNULL(dc.idEmpleado,0) AS idEmpleado, "
			+ "		ISNULL(dc.idTipoComisionado,'') AS idTipoComisionado, "
			+ "		ISNULL(e.apellido+', '+ e.nombre,'') AS descPersonal, ISNULL(p.nombre,'') AS descPrograma, ISNULL(a.nombre,'') AS descArea "
	    	+ " FROM DetallesComisionados dc " 
	//    	+ " 	INNER JOIN EmpleadosPorProgramas ep ON dc.idPersonalPorPrograma=ep.id "
	    	+ " 	INNER JOIN Empleados e ON dc.idEmpleado=e.id "
	    	+ "		LEFT JOIN Programas p ON dc.idPrograma=p.id "
	    	+ "		LEFT JOIN Areas a ON p.idArea=a.id "
    		+ " WHERE dc.id=0"+ id +" AND dc.idComision=0"+ idComision +" ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		rol = new DetalleComisionados(rs.getInt("id"), rs.getInt("idComision"),
    				rs.getInt("idEmpleado"), rs.getInt("idArea"),rs.getInt("idPrograma"),rs.getInt("idTipoComisionado"),
    				rs.getString("descPersonal"),rs.getString("descPrograma"),rs.getString("descArea"));
    		}
    	rs.close();
    	con.close();
		return rol;
	}
	
	

	@SuppressWarnings("finally")
	public int hayComisionados(int idComision)  {
	    int existe=0;
	    try{
			sql = "SELECT COUNT(*) As cant FROM DetallesComisionados WHERE idComision=0"+ idComision +" ";
			con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
			rs.next();
			existe = rs.getInt("cant");
			rs.close();
			con.close();
	    }catch( SQLException e){
	    	
	    }finally{
	    	return existe;
	    }
	}
	
	
	public void actualizaMontoViatico(int idComision, int usrLog){
		
		sql = "UPDATE DetallesComisionados SET montoCategoria = ISNULL(dbo.getMontoComisionado(0"+ idComision +", idCategoria),0), "
			+ "		viaticos = ISNULL(dbo.getMontoComisionado(0"+ idComision +", idCategoria),0)*tiempoDuracion, "
			+ " 	fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE idComision=0" + idComision +" ";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	
	public void actualizaDuracion(int idComision, float duracion, int usrLog){
		sql = "UPDATE DetallesComisionados SET tiempoDuracion = 0"+ duracion +", viaticos = ISNULL(montocategoria,0)*0"+ duracion +", "
			+ " fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE idComision=0" + idComision +" ";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	
	public void modifica(int id, int idEmpleado, int idCategoria, int idEspecialidad, int idPrograma, float monto, int usrLog){
		sql = "UPDATE DetallesComisionados SET idEmpleado = 0"+ idEmpleado +", idPrograma=0"+idPrograma+", idEspecialidad=0"+idEspecialidad+", "
			+ " idCategoria=0"+idCategoria+", monto=0"+monto+", "
			+ " fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" WHERE id=0" + id +" ";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void modifica(DetalleComisionados a, int usrLog){
		sql = "UPDATE DetallesComisionados SET idEmpleado="+ a.getIdEmpleado() +", idPrograma="+ a.getIdPrograma() +", idEspecialidad="+ a.getIdEspecialidad() +", "
			+ " idCategoria="+ a.getIdCategoria() +", montoCategoria="+ a.getMontoCategoria() +", tiempoDuracion="+ a.getTiempoDuracion() +", "
			+ " viaticos="+ a.getViaticos()+", "
			+ " fechaModificacion=GETDATE(), userModificacion="+ usrLog +" WHERE id="+ a.getId() +" ";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(int idComision, int idEmpleado, int idCategoria, int idEspecialidad, int idPrograma, float monto, int usrLog) {	
	    sql = "INSERT INTO DetallesComisionados (idComision, idEmpleado, idPrograma, idCategoria, idEspecialidad, montoCategoria, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    	+ " VALUES (0"+ idComision +", 0"+ idEmpleado +", 0"+ idPrograma +", 0"+ idCategoria +", 0"+ idEspecialidad +", 0"+ monto +", GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	
	public void agrega(DetalleComisionados a, int usrLog) {	
	    sql = "INSERT INTO DetallesComisionados (idComision, idEmpleado, idPrograma, idCategoria, idEspecialidad, montoCategoria, "
	    	+ "		tiempoDuracion, viaticos, idTipoComisionado, "
	    	+ "		fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    	+ " VALUES ("+ a.getIdComision() +", "+ a.getIdEmpleado() +", "+ a.getIdPrograma() +", "+ a.getIdCategoria() +", "+ a.getIdEspecialidad() +", "
	    	+ "		"+ a.getMontoCategoria() +", "+ a.getTiempoDuracion() +", "+ a.getViaticos() +", "+ a.getIdTipoComisionado() +", "
	    	+ "		GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	
	public void elimina(int id, int usrLog) {	
	    sql = "DELETE FROM DetallesComisionados WHERE id=0"+ id +" ";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}

	public void elimina(int idComision, int idEmpleado, int usrLog) {	
	    sql = "DELETE FROM DetallesComisionados WHERE idComision=0"+ idComision +" AND idEmpleado=0"+ idEmpleado +" AND idTipoComisionado=2 ";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void eliminaComisionadoChofer(int idComision, int usrLog) {	
	    sql = "DELETE FROM DetallesComisionados WHERE idComision=0"+ idComision +" AND idTipoComisionado=2 ";
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


	@SuppressWarnings("finally")
	public int existe(int id, int idComision, int idPersPrograma) {
	    int existe=0;
	    try{
	    	sql = "SELECT COUNT(*) As cant FROM DetallesComisionados WHERE idComision=0"+ idComision +" AND idEmpleado=0"+ idPersPrograma +" AND id<>0"+id+"";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	    	rs.next();
	    	existe = rs.getInt("cant");
	    	rs.close();
	    	con.close();
	    } catch(Exception e){
	    	
	    }finally{
	        return existe;
	    }
	}
	
	@SuppressWarnings("finally")
	public int existe(DetalleComisionados a) {
	    int existe=0;
	    try{
	    	sql = "SELECT COUNT(*) As cant FROM DetallesComisionados "
	    		+ " WHERE idComision="+ a.getIdComision() +" AND idEmpleado="+ a.getIdEmpleado() +" AND id<>"+ a.getId() +"";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	    	rs.next();
	    	existe = rs.getInt("cant");
	    	rs.close();
	    	con.close();
	    } catch(Exception e){
	    	
	    }finally{
	        return existe;
	    }
	}
}
