package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import sec.comisiones.mapeos.DetalleGastosComisionados;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;



public class DetalleGastosComisionadosDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	SimpleDateFormat dateFormatCorto= new SimpleDateFormat("dd/MM/yyyy");
	private DetalleGastosComisionados obj = new DetalleGastosComisionados();
	
	
	public BeanContainer<Integer, DetalleGastosComisionados> getAllDetalleGastosComisionadosContainer(int idComision, int idComisionado)  {
		 BeanContainer<Integer, DetalleGastosComisionados> Container = null;
	    try {
	    	sql = " SELECT dgc.id, dgc.idComision, dgc.idEmpleado, ISNULL(dgc.fecha,'01/01/1900') AS fecha, ISNULL(dgc.concepto,'') AS concepto, "
	    			+ " 	ISNULL(dgc.importe,0) AS importe "
	    			+ " FROM DetallesGastosComisionados dgc "
	        		+ " WHERE dgc.idEmpleado=0"+ idComisionado +" AND dgc.idComision=0"+ idComision +" ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, DetalleGastosComisionados> (DetalleGastosComisionados.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	obj = new DetalleGastosComisionados();
	        	obj.setId(rs.getInt("id"));
	        	obj.setIdComision(rs.getInt("id"));
	        	obj.setIdComisionado(rs.getInt("id"));
	        	obj.setFecha(rs.getDate("fecha"));
	        	obj.setConcepto(rs.getString("concepto"));
	        	obj.setImporte(rs.getFloat("importe"));
	        	        	
	        	Container.addBean(obj);	    			

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
		DetalleGastosComisionados a = new DetalleGastosComisionados();
		BeanItem<DetalleGastosComisionados> item = new BeanItem<DetalleGastosComisionados>(a);	
	    return item;
	}

	
	public DetalleGastosComisionados DevuelveDetalleGastosComisionados(int idEmpleado, int idComision) throws SQLException{

		sql = " SELECT dgc.id, dgc.idComision, dgc.idEmpleado, ISNULL(dgc.fecha,'01/01/1900') AS fecha, ISNULL(dgc.concepto,'') AS concepto, "
			+ " 	ISNULL(dgc.importe,0) AS importe "
			+ " FROM DetallesGastosComisionados dgc "
    		+ " WHERE dgc.idEmpleado=0"+ idEmpleado +" AND dgc.idComision=0"+ idComision +" ";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
    		obj = new DetalleGastosComisionados();
    		obj.setId(rs.getInt("id"));
    		obj.setIdComision(rs.getInt("id"));
    		obj.setIdComisionado(rs.getInt("id"));
    		obj.setFecha(rs.getDate("fecha"));
    		obj.setConcepto(rs.getString("concepto"));
    		obj.setImporte(rs.getFloat("importe"));
    		
    	}
    	rs.close();
    	con.close();
		return obj;
	}
	
	
	@SuppressWarnings("finally")
	public float totalDetalleGastosComisionados(int idComision, int idEmpleado)  {
	    float existe=0.00f;
	    try{
			sql = " SELECT ISNULL(SUM(importe),0.00) AS total FROM DetallesGastosComisionados "
				+ " WHERE idComision=0"+ idComision +" AND idEmpleado=0"+ idEmpleado +" ";
			con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
			rs.next();
			existe = rs.getFloat("total");
			rs.close();
			con.close();
	    }catch( SQLException e){
	    	
	    }finally{
	    	return existe;
	    }
	}
	
	
	@SuppressWarnings("finally")
	public int hayDetalleGastosComisionados(int idComision, int idEmpleado)  {
	    int existe=0;
	    try{
			sql = "SELECT COUNT(*) As cant FROM DetallesGastosComisionados WHERE idComision=0"+ idComision +" AND idEmpleado=0"+ idEmpleado +" ";
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
	
	
	

	
//	public void modifica(DetalleGastosComisionados a, int usrLog){
//		sql = "UPDATE DetallesComisionados SET idEmpleado="+ a.getIdEmpleado() +", idPrograma="+ a.getIdPrograma() +", idEspecialidad="+ a.getIdEspecialidad() +", "
//			+ " idCategoria="+ a.getIdCategoria() +", montoCategoria="+ a.getMontoCategoria() +", tiempoDuracion="+ a.getTiempoDuracion() +", "
//			+ " viaticos="+ a.getViaticos()+", "
//			+ " fechaModificacion=GETDATE(), userModificacion="+ usrLog +" WHERE id="+ a.getId() +" ";
//		msg="";
//		cnn.grabarEnBase(sql,msg,usrLog);		
//	}

	
	public void agrega(DetalleGastosComisionados a, int usrLog) {	
	    sql = "INSERT INTO DetallesGastosComisionados (idComision, idEmpleado, fecha, concepto, importe, "
	    	+ "		fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    	+ " VALUES ("+ a.getIdComision() +", "+ a.getIdComisionado() +", '"+ dateFormatCorto.format(a.getFecha()) +"', '"+ a.getConcepto() +"', "+ a.getImporte() +", "
	    	+ "		GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";

	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	
	public void elimina(int id, int usrLog) {	
	    sql = "DELETE FROM DetallesGastosComisionados WHERE id=0"+ id +" ";
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


	

//	public int existe(DetalleGastosComisionados a) {
//	    int existe=0;
//	    try{
//	    	sql = "SELECT COUNT(*) As cant FROM DetallesComisionados "
//	    		+ " WHERE idComision="+ a.getIdComision() +" AND idEmpleado="+ a.getIdEmpleado() +" AND id<>"+ a.getId() +"";
//	    	con=cnn.abrir().reserveConnection();
//	    	rs = cnn.obtenerCursor(sql, con);
//	    	rs.next();
//	    	existe = rs.getInt("cant");
//	    	rs.close();
//	    	con.close();
//	    } catch(Exception e){
//	    	
//	    }finally{
//	        return existe;
//	    }
//	}
}
