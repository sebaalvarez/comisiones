package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import sec.comisiones.mapeos.LogAuditoria;

import com.agpro.controles.Conexion;
import com.agpro.controles.Formateos;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;

public class LogAuditoriaDAO {
//	private Conexion Conexion = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;

	
	Formateos fo = new Formateos();

	
	
	public BeanContainer<Integer, LogAuditoria> getAllLogAuditoriaContainer(String par1,String par2,String par3,String par4,String fec1,String fec2)  {
		 BeanContainer<Integer, LogAuditoria> Container = null;
		 
		 String criterio = " ";
		 if(Conexion.getBase().equals("SQLServer")){
			 if (par1 != ""){ criterio = criterio + " AND REPLACE(l.Estatus,'.','') LIKE REPLACE('%"+ par1 +"%','.','') ";}
			 if (par2 != ""){ criterio = criterio + " AND REPLACE(u.nombre,'.','') LIKE REPLACE('%"+ par2 +"%','.','') ";}
			 if (par3 != ""){ criterio = criterio + " AND l.Accion LIKE '%"+ par3 +"%' ";}
			 if (par4 != ""){ criterio = criterio + " AND l.SQLExecute LIKE '%"+ par4 +"%' ";}
			 
			 if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (l.fechaAcceso BETWEEN  '"+ fec1 +"' AND '"+ fec2 +"') ";}
			 if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND ((l.fechaAcceso BETWEEN  '01/01/1900' AND '"+ fec2 +"') OR (l.fechaAcceso IS NULL))";}
			 if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND (l.fechaAcceso BETWEEN  '"+ fec1 +"' AND '01/01/2100')";}
			 if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((l.fechaAcceso BETWEEN  '01/01/1900' AND '01/01/2100') OR (l.fechaAcceso IS NULL))";}

		 } if(Conexion.getBase()=="MySQL"){			 
			 if (par1 != ""){ criterio = criterio + " AND REPLACE(l.Estatus,'.','') LIKE REPLACE('%"+ par1 +"%','.','') ";}
			 if (par2 != ""){ criterio = criterio + " AND REPLACE(u.nombre,'.','') LIKE REPLACE('%"+ par2 +"%','.','') ";}
			 if (par3 != ""){ criterio = criterio + " AND l.Accion LIKE '%"+ par3 +"%' ";}
			 if (par4 != ""){ criterio = criterio + " AND l.SQLExecute LIKE '%"+ par4 +"%' ";}
			 
			 if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (l.fechaAcceso BETWEEN  '"+ fo.formatDate(fec1, "dd/MM/yyyy", "yyyy-MM-dd") +"' AND '"+ fo.formatDate (fec2, "dd/MM/yyyy", "yyyy-MM-dd") +" 23:59:59') ";}
			 if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND ((l.fechaAcceso BETWEEN  '1900-01-01' AND '"+ fo.formatDate (fec2, "dd/MM/yyyy", "yyyy-MM-dd") +" 23:59:59') OR (l.fechaAcceso IS NULL))";}
			 if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND (l.fechaAcceso BETWEEN  '"+ fo.formatDate(fec1, "dd/MM/yyyy", "yyyy-MM-dd") +"' AND '2100-01-01')";}
			 if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((l.fechaAcceso BETWEEN  '1900-01-01' AND '2100-01-01') OR (l.fechaAcceso IS NULL))";}
			 
		 }
		 
	    try {
	    	if(Conexion.getBase().equals("SQLServer")){
		    	sql = " SELECT  l.id,l.fechaAcceso,l.idRol,ISNULL(r.nombre,'') AS descRol,l.idUsuario,ISNULL(u.nombre,'') AS descUsuario, "
		    		+ " 		l.pc,l.Accion,l.SQLExecute,l.Estatus,l.Comentario, ISNULL(l.infoSistema,'') AS infoSistema, "
		    		+ "		ISNULL(l.descripcion,'') AS descripcion "
		    		+ " FROM logAcceso l "
		    		+ " 		LEFT JOIN Usuarios u ON l.idUsuario=u.id "
		    		+ " 		LEFT JOIN Roles r ON l.idRol=r.id "
		    		+ " WHERE 1=1 " + criterio;

	    	} if(Conexion.getBase()=="MySQL"){
	    		sql = " SELECT  l.id,l.fechaAcceso,l.idRol,IFNULL(r.nombre,'') AS descRol,l.idUsuario,IFNULL(u.nombre,'') AS descUsuario, "
	    	    	+ " 		l.pc,l.Accion,l.SQLExecute,l.Estatus,l.Comentario, IFNULL(l.infoSistema,'') AS infoSistema, "
	    	    	+ "		IFNULL(l.descripcion,'') AS descripcion  "
	    	    	+ " FROM logAcceso l "
	    	    	+ " 		LEFT JOIN Usuarios u ON l.idUsuario=u.id "
	    	    	+ " 		LEFT JOIN Roles r ON l.idRol=r.id "
	    	    	+ " WHERE 1=1 " + criterio;
				 
			}
	    	

	    	con=Conexion.getConn();
	    	rs = Conexion.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, LogAuditoria> (LogAuditoria.class);
	        Container.setBeanIdProperty("id");
	        
	        while(rs.next()) {  	
	        	LogAuditoria a = new LogAuditoria();
	        	
	        	a.setId(rs.getInt("id"));
	        	a.setIdRol(rs.getInt("idRol"));
	        	a.setIdUsuario(rs.getInt("idUsuario"));
	        	a.setPc(rs.getString("pc"));
	        	a.setFecha(rs.getTimestamp("fechaAcceso"));
	        	a.setAccion(rs.getString("Accion"));
	        	a.setEstatus(rs.getString("Estatus"));
	        	a.setComentario(rs.getString("Comentario"));
	        	a.setDescRol(rs.getString("descRol"));
	        	a.setDescUsuario(rs.getString("descUsuario"));
	        	a.setSql(rs.getString("SQLExecute"));
	        	a.setInfoSistema(rs.getString("infoSistema"));
	        	a.setDescripcion(rs.getString("descripcion"));

	        	Container.addBean(a);	    			
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
		LogAuditoria a = new LogAuditoria();
		BeanItem<LogAuditoria> item = new BeanItem<LogAuditoria>(a);	
	    return item;
	}
	

	public LogAuditoria DevuelveCertificadoId(int numero) throws SQLException{
		LogAuditoria it = new LogAuditoria();
		if(Conexion.getBase().equals("SQLServer")){
			sql = "SELECT c.Id, c.numero, c.num, ISNULL(c.jurisdiccion,0) AS jurisdiccion, ISNULL(c.expediente,'') AS expediente, ISNULL(c.titular,'') AS titular, "
    			+ " ISNULL(c.cuit,0) AS cuit, ISNULL(c.Domicilio,'') AS domicilio, ISNULL(c.tipo,0) AS idTipoExp, "
    			+ " ISNULL(c.superficie_exp,0) AS supExplotada, ISNULL(c.fechaemision,'') AS fechaEmision, ISNULL(c.fechavence,'') AS fechaVencimiento, "
    			+ " ISNULL(c.suspendido,0) AS suspendido, ISNULL(j.NombreJurisdiccion,'') AS nomJurisdiccion, ISNULL(te.TipoExplotación,'') AS nomTipoExplotacion  "
    			+ " FROM Certificados c LEFT JOIN Jurisdicciones j ON c.jurisdiccion=j.Id_Jurisdiccion LEFT JOIN TipoExplotacion te ON c.tipo=te.Id_TipoExplotacion "
    			+ " WHERE c.id= 0"+ numero +" ";
			
		} if(Conexion.getBase()=="MySQL"){
			sql = "SELECT c.Id, c.numero, c.num, IFNULL(c.jurisdiccion,0) AS jurisdiccion, IFNULL(c.expediente,'') AS expediente, IFNULL(c.titular,'') AS titular, "
	    		+ " IFNULL(c.cuit,0) AS cuit, IFNULL(c.Domicilio,'') AS domicilio, IFNULL(c.tipo,0) AS idTipoExp, "
	    		+ " IFNULL(c.superficie_exp,0) AS supExplotada, IFNULL(c.fechaemision,'') AS fechaEmision, IFNULL(c.fechavence,'') AS fechaVencimiento, "
	    		+ " IFNULL(c.suspendido,0) AS suspendido, IFNULL(j.NombreJurisdiccion,'') AS nomJurisdiccion, IFNULL(te.TipoExplotación,'') AS nomTipoExplotacion  "
	    		+ " FROM Certificados c LEFT JOIN Jurisdicciones j ON c.jurisdiccion=j.Id_Jurisdiccion LEFT JOIN TipoExplotacion te ON c.tipo=te.Id_TipoExplotacion "
	    		+ " WHERE c.id= 0"+ numero +" ";
			 
		}
		
		con=Conexion.getConn();
    	rs = Conexion.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
	        	it=new LogAuditoria();	
    		}
    	rs.close();
    	con.close();
		return it;
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



	
	

}
