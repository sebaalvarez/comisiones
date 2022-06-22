package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sec.comisiones.mapeos.Choferes;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class ChoferesDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	SimpleDateFormat dateFormatCorto= new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	
	public BeanContainer<Integer, Choferes> getAllChoferesContainer()  {
		 BeanContainer<Integer, Choferes> Container = null;
		 Choferes it = null;
	    try {
	    	sql = "SELECT c.id, ISNULL(c.idEmpleado,0) AS idEmpleado, ISNULL(c.idCategoria,0) AS idCategoria, ISNULL(c.idPrograma,0) AS idPrograma, "
	    		+ "		ISNULL(CAST(e.dni AS nvarchar(10)),'') +' - '+ ISNULL(e.apellido,'') + ', ' + ISNULL(e.nombre,'') AS descEmpleado, "
	    		+ " 	ISNULL(c.numeroCarnet,0) AS numeroCarnet, ISNULL(c.fechaVencimiento, '01/01/1900') AS fechaVencimiento,"
	    		+ "		ISNULL(CAST(c.idCategoria AS nvarchar(10)),'') +' - '+ ISNULL(cat.nombre,'') AS descCategoria, "
	    		+ "		ISNULL(CAST(c.idPrograma AS nvarchar(10)),'') +' - '+ ISNULL(p.nombre,'') AS descPrograma  " 
	    		+ " FROM Choferes c "
	    		+ "		LEFT JOIN empleados e ON c.idEmpleado=e.id "
	    		+ "		LEFT JOIN categorias cat ON c.idCategoria=cat.id "
	    		+ "		LEFT JOIN programas p ON c.idPrograma=p.id "
	    		+ " ORDER BY e.apellido, e.nombre ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Choferes> (Choferes.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	
	        	it=new Choferes();
	        	it.setId(rs.getInt("id"));
	        	it.setIdEmpleado(rs.getInt("idEmpleado"));
	        	it.setDescChofer(rs.getString("descEmpleado"));
	        	it.setNumCarnet(rs.getLong("numeroCarnet"));
	        	it.setFechaVencimiento(rs.getDate("fechaVencimiento"));
	        	it.setIdCategoria(rs.getInt("idCategoria"));
	        	it.setIdPrograma(rs.getInt("idPrograma"));
	        	it.setDescCategoria(rs.getString("descCategoria"));
	        	it.setDescPrograma(rs.getString("descPrograma"));
	        	
	        	Container.addBean(it);	    			 
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
		Choferes a = new Choferes();
		BeanItem<Choferes> item = new BeanItem<Choferes>(a);	
	    return item;
	}
	
	
	public Choferes DevuelveChofer(int id) {
		Choferes it = new Choferes();
		try{
			sql = "SELECT c.id, ISNULL(c.idEmpleado,0) AS idEmpleado, ISNULL(c.idCategoria,0) AS idCategoria, ISNULL(c.idPrograma,0) AS idPrograma, "
		    	+ "		ISNULL(CAST(e.dni AS nvarchar(10)),'') +' - '+ ISNULL(e.apellido,'') + ', ' + ISNULL(e.nombre,'') AS descEmpleado, "
		    	+ " 	ISNULL(c.numeroCarnet,0) AS numeroCarnet, ISNULL(c.fechaVencimiento, '01/01/1900') AS fechaVencimiento,"
		    	+ "		ISNULL(CAST(c.idCategoria AS nvarchar(10)),'') +' - '+ ISNULL(cat.nombre,'') AS descCategoria, "
		    	+ "		ISNULL(CAST(c.idPrograma AS nvarchar(10)),'') +' - '+ ISNULL(p.nombre,'') AS descPrograma  " 
		    	+ " FROM Choferes c "
		    	+ "		LEFT JOIN empleados e ON c.idEmpleado=e.id "
		    	+ "		LEFT JOIN categorias cat ON c.idCategoria=cat.id "
		    	+ "		LEFT JOIN programas p ON c.idPrograma=p.id "
	    		+ " WHERE c.id=0"+ id +" ";
			con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	        
	    	int a = size(rs); 
	    	if (a>0) {
	    		rs.next();
	    		
	    		it=new Choferes();
	        	it.setId(rs.getInt("id"));
	        	it.setIdEmpleado(rs.getInt("idEmpleado"));
	        	it.setDescChofer(rs.getString("descEmpleado"));
	        	it.setNumCarnet(rs.getLong("numeroCarnet"));
	        	it.setFechaVencimiento(rs.getDate("fechaVencimiento"));
	        	it.setIdCategoria(rs.getInt("idCategoria"));
	        	it.setIdPrograma(rs.getInt("idPrograma"));
	        	it.setDescCategoria(rs.getString("descCategoria"));
	        	it.setDescPrograma(rs.getString("descPrograma"));
        	
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
	
	
	public String FechaReserva (int idComision, int idChofer) {
		String mensaje="";
		try{
			sql =" SELECT TOP 1 a.mensaje "
				+" FROM ( "	
				+"		SELECT TOP 1 'El chofer selecionado se encuentra asignado a la Comisión '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
				+" 			+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
				+"			CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
				+" 		FROM comisiones c "
				+" 			LEFT JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
				+" 		WHERE dv.idComision<>0"+ idComision +" AND dv.idChofer= 0"+ idChofer + " AND c.idEstado<>5 "
				+"			AND ( "
				+"				(SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"				OR "
				+"				(SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"				OR "
				+"				(c.fechaHoraSalida BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
				+"				OR "
				+"				(c.FechaHoraRegreso BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
				+"			) "
				+"		UNION ALL "
				+"		SELECT TOP 1 'El chofer selecionado se encuentra asignado a la Reserva '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
				+" 			+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
				+" 			CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
				+" 		FROM Reservas c "
				+" 		WHERE c.idChofer= 0"+ idChofer + " "			
				+"			AND ( "
				+"				(SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"				OR "
				+"				(SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"				OR "
				+"				(c.fechaHoraSalida BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
				+"				OR "
				+"				(c.FechaHoraRegreso BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
				+"          )	"
				+"		) a ";
			con=cnn.getConn();
			rs = cnn.obtenerCursor(sql,con);
			
			int a = 0;
			a=size(rs); 
	    	if (a>0) {
	    		rs.next();
	    		mensaje = rs.getString("mensaje");
	    	}
		} catch(Exception e){
			
		}
		return mensaje;
	}
	
	
	public String FechaReserva1 (int idReserva, int idChofer,Date fechaSalida, Date fechaRegreso) {
		String mensaje="";
		try{
			sql =" SELECT TOP 1 a.mensaje "
				+" FROM ( "	
				+"		SELECT TOP 1 'El chofer selecionado se encuentra asignado a la Comisión '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
				+" 			+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
				+"			CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
				+" 		FROM comisiones c "
				+" 			LEFT JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
				+" 		WHERE dv.idChofer= 0"+ idChofer + " AND c.idEstado<>5 "
				+"			AND ( "
				+"				'"+dateFormat.format(fechaSalida)+"' BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"				OR "
				+"				'"+dateFormat.format(fechaRegreso)+"' BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"				OR "
				+"				(c.fechaHoraSalida BETWEEN '"+dateFormat.format(fechaSalida)+"' AND '"+dateFormat.format(fechaRegreso)+"') "
				+"				OR "
				+"				(c.FechaHoraRegreso BETWEEN '"+dateFormat.format(fechaSalida)+"' AND '"+dateFormat.format(fechaRegreso)+"') "
				+"			) "
				+"		UNION ALL "
				+"		SELECT TOP 1 'El chofer selecionado se encuentra asignado a la Reserva '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
				+" 			+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
				+" 			CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
				+" 		FROM Reservas c "
				+" 		WHERE  c.id<>0"+ idReserva +" AND c.idChofer= 0"+ idChofer + " "			
				+"			AND ( "
				+"				'"+dateFormat.format(fechaSalida)+"' BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"				OR "
				+"				'"+dateFormat.format(fechaRegreso)+"' BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"				OR "
				+"				(c.fechaHoraSalida BETWEEN '"+dateFormat.format(fechaSalida)+"' AND '"+dateFormat.format(fechaRegreso)+"') "
				+"				OR "
				+"				(c.FechaHoraRegreso BETWEEN '"+dateFormat.format(fechaSalida)+"' AND '"+dateFormat.format(fechaRegreso)+"') "
				+"          )	"
				+"		) a ";
			con=cnn.getConn();
			rs = cnn.obtenerCursor(sql,con);
			
			int a = 0;
			a=size(rs); 
	    	if (a>0) {
	    		rs.next();
	    		mensaje = rs.getString("mensaje");
	    	}
		} catch(Exception e){
			
		}
		return mensaje;
	}
	
	public void modifica(Choferes it, int usrLog){
		sql = " UPDATE Choferes SET numeroCarnet=0" + it.getNumCarnet() + ", fechaVencimiento='"+ dateFormatCorto.format(it.getFechaVencimiento()) +"', "
			+ "		idCategoria = "+ it.getIdCategoria() +", idPrograma= "+ it.getIdPrograma() +", "
			+ " 	fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" "
			+ " WHERE id=" + it.getId() +"";
		msg="";
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(Choferes it, int usrLog) {	
	    sql = "INSERT INTO Choferes (idEmpleado, idCategoria, idPrograma, numeroCarnet, fechaVencimiento, "
	    	+ "		fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    	+ " VALUES (0"+ it.getIdEmpleado() +", 0"+ it.getIdCategoria()+", 0"+ it.getIdPrograma() +", 0"+ it.getNumCarnet() +", "
	    	+ "		'"+ dateFormatCorto.format(it.getFechaVencimiento()) +"', GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "";
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(int id, int usrLog) {	
	    sql = "DELETE FROM Choferes WHERE id=0"+ id +" ";
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


	public int existe(int idEmpleado)  {
	    int existe=0;
	    try{
	    	sql = "SELECT COUNT(*) As cant FROM Choferes WHERE idEmpleado='" + idEmpleado +"'";
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
	public int existenRegistrosRelacionados(int nombre) {
	    int existe=0;
	    try{
	    	sql = "SELECT ISNULL(dbo.RegistrosIdChofere("+ nombre +"),0) AS cant";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);
	    	rs.next();
	    	existe = rs.getInt("cant");
	    	
	    } catch(Exception e) {
	    	Notification.show("Error al obtener el registro.",Type.ERROR_MESSAGE);
	    } finally{
	    	try {
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	return existe;
	    }   
	}
	
	
	
}
