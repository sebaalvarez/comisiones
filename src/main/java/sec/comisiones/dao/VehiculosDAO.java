package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sec.comisiones.mapeos.Vehiculos;
import sec.comisiones.mapeos.Marcas;
import sec.comisiones.mapeos.Modelos;
import sec.comisiones.mapeos.TiposVehiculos;

import com.agpro.controles.Conexion;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

public class VehiculosDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs = null;
	private String sql;
	private String msg = "";
	
	SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public BeanContainer<Integer, Vehiculos> getAllVehiculosContainer()  {
		 BeanContainer<Integer, Vehiculos> Container = null;
		 Vehiculos it = new Vehiculos();
	    try {
	    	sql = "SELECT v.id, ISNULL(v.dominio,'') AS dominio, ISNULL(v.anio, 1900) AS anio, "
	    		+ " 	ISNULL(v.idMarca,'') AS idMarca, ISNULL(v.idModelo,'') AS idModelo, ISNULL(v.idTipo,'') AS idTipo, ISNULL(v.activo,0) AS activo, "
	    		+ " 	ISNULL(ma.nombre,'') AS descMarca, ISNULL(mo.nombre,'') AS descModelo, ISNULL(tv.nombre,'') AS descTipoVehiculo "
	    		+ " FROM Vehiculos v LEFT JOIN Modelos mo ON v.idModelo=mo.id "
	    		+ "		LEFT JOIN Marcas ma ON v.idMarca=ma.id LEFT JOIN tiposVehiculos tv ON v.idTipo=tv.id "
	    		+ " ORDER BY ma.nombre, mo.nombre, v.dominio ";
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Vehiculos> (Vehiculos.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	
	        	it = new Vehiculos();
	        	it.setId(rs.getInt("id"));
	        	it.setDominio(rs.getString("dominio"));
	        	it.setAnio(rs.getInt("anio"));
	        	it.setIdMarca(rs.getInt("idMarca"));
	        	it.setIdModelo(rs.getInt("idModelo"));
	        	it.setIdTipo(rs.getInt("idTipo"));
	        	it.setDescMarca(rs.getString("descMarca"));
	        	it.setDescModelo(rs.getString("descModelo"));
	        	it.setDescTipo(rs.getString("descTipoVehiculo"));
	        	it.setActivo(rs.getBoolean("activo"));

	        	Container.addBean(it);	    			
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
	

	
	public BeanContainer<Integer, Vehiculos> getAllVehiculosContainer(int marca, int modelo)  {
		 BeanContainer<Integer, Vehiculos> Container = null;
		 Vehiculos it = new Vehiculos();
	    try {
	    	sql = "SELECT v.id, ISNULL(v.dominio,'') AS dominio, ISNULL(v.anio, 1900) AS anio, "
		    	+ " 	ISNULL(v.idMarca,'') AS idMarca, ISNULL(v.idModelo,'') AS idModelo, ISNULL(v.idTipo,'') AS idTipo, ISNULL(v.activo,0) AS activo, "
		    	+ " 	ISNULL(ma.nombre,'') AS descMarca, ISNULL(mo.nombre,'') AS descModelo, ISNULL(tv.nombre,'') AS descTipoVehiculo "
		    	+ " FROM Vehiculos v LEFT JOIN Modelos mo ON v.idModelo=mo.id "
		    	+ "		LEFT JOIN Marcas ma ON v.idMarca=ma.id LEFT JOIN tiposVehiculos tv ON v.idTipo=tv.id "
		    	+ " WHERE v.idMarca = 0"+ marca +" AND v.idModelo = 0"+ modelo +" "
		    	+ " ORDER BY ma.nombre, mo.nombre, v.dominio ";
	    	
	    	
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Vehiculos> (Vehiculos.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	
	        	it = new Vehiculos();
	        	it.setId(rs.getInt("id"));
	        	it.setDominio(rs.getString("dominio"));
	        	it.setAnio(rs.getInt("anio"));
	        	it.setIdMarca(rs.getInt("idMarca"));
	        	it.setIdModelo(rs.getInt("idModelo"));
	        	it.setIdTipo(rs.getInt("idTipo"));
	        	it.setDescMarca(rs.getString("descMarca"));
	        	it.setDescModelo(rs.getString("descModelo"));
	        	it.setDescTipo(rs.getString("descTipoVehiculo"));
	        	it.setActivo(rs.getBoolean("activo"));
	        	
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
	
	
	
	public BeanContainer<Integer, Vehiculos> getAllVehiculosActivosContainer(int marca, int modelo)  {
		 BeanContainer<Integer, Vehiculos> Container = null;
		 Vehiculos it = new Vehiculos();
	    try {
	    	sql = "SELECT v.id, ISNULL(v.dominio,'') AS dominio, ISNULL(v.anio, 1900) AS anio, "
		    	+ " 	ISNULL(v.idMarca,'') AS idMarca, ISNULL(v.idModelo,'') AS idModelo, ISNULL(v.idTipo,'') AS idTipo, ISNULL(v.activo,0) AS activo, "
		    	+ " 	ISNULL(ma.nombre,'') AS descMarca, ISNULL(mo.nombre,'') AS descModelo, ISNULL(tv.nombre,'') AS descTipoVehiculo "
		    	+ " FROM Vehiculos v LEFT JOIN Modelos mo ON v.idModelo=mo.id "
		    	+ "		LEFT JOIN Marcas ma ON v.idMarca=ma.id LEFT JOIN tiposVehiculos tv ON v.idTipo=tv.id "
		    	+ " WHERE v.idMarca = 0"+ marca +" AND v.idModelo = 0"+ modelo +" AND ISNULL(v.activo,0)=1 "
		    	+ " ORDER BY ma.nombre, mo.nombre, v.dominio ";
	    	
	    	
	    	con=cnn.getConn();
	    	rs = cnn.obtenerCursor(sql, con);

	        Container = new BeanContainer<Integer, Vehiculos> (Vehiculos.class);
	        Container.setBeanIdProperty("id");
			
	        while(rs.next()) {
	        	
	        	it = new Vehiculos();
	        	it.setId(rs.getInt("id"));
	        	it.setDominio(rs.getString("dominio"));
	        	it.setAnio(rs.getInt("anio"));
	        	it.setIdMarca(rs.getInt("idMarca"));
	        	it.setIdModelo(rs.getInt("idModelo"));
	        	it.setIdTipo(rs.getInt("idTipo"));
	        	it.setDescMarca(rs.getString("descMarca"));
	        	it.setDescModelo(rs.getString("descModelo"));
	        	it.setDescTipo(rs.getString("descTipoVehiculo"));
	        	it.setActivo(rs.getBoolean("activo"));
	        	
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
		Vehiculos a = new Vehiculos();
		BeanItem<Vehiculos> item = new BeanItem<Vehiculos>(a);	
	    return item;
	}
	
	
	public Vehiculos DevuelveVehiculo(String nom) throws SQLException{
		Vehiculos it = new Vehiculos();
    	
    	sql = "SELECT v.id, ISNULL(v.dominio,'') AS dominio, ISNULL(v.anio, 1900) AS anio, "
	    	+ " 	ISNULL(v.idMarca,'') AS idMarca, ISNULL(v.idModelo,'') AS idModelo, ISNULL(v.idTipo,'') AS idTipo, ISNULL(v.activo,0) AS activo, "
	    	+ " 	ISNULL(ma.nombre,'') AS descMarca, ISNULL(mo.nombre,'') AS descModelo, ISNULL(tv.nombre,'') AS descTipoVehiculo "
	    	+ " FROM Vehiculos v LEFT JOIN Modelos mo ON v.idModelo=mo.id "
	    	+ "		LEFT JOIN Marcas ma ON v.idMarca=ma.id LEFT JOIN tiposVehiculos tv ON v.idTipo=tv.id "
	    	+ " WHERE v.dominio='"+ nom +"' "
	    	+ " ORDER BY ma.nombre, mo.nombre, v.dominio ";
    	
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();

        	it = new Vehiculos();
        	it.setId(rs.getInt("id"));
        	it.setDominio(rs.getString("dominio"));
        	it.setAnio(rs.getInt("anio"));
        	it.setIdMarca(rs.getInt("idMarca"));
        	it.setIdModelo(rs.getInt("idModelo"));
        	it.setIdTipo(rs.getInt("idTipo"));
        	it.setDescMarca(rs.getString("descMarca"));
        	it.setDescModelo(rs.getString("descModelo"));
        	it.setDescTipo(rs.getString("descTipoVehiculo"));
        	it.setActivo(rs.getBoolean("activo"));
        	
    		}
    	rs.close();
    	con.close();
		return it;
	}
	
	
	public Vehiculos DevuelveVehiculo(int id) throws SQLException{
		Vehiculos it = new Vehiculos();
    	
    	sql = "SELECT v.id, ISNULL(v.dominio,'') AS dominio, ISNULL(v.anio, 1900) AS anio, "
    	    	+ " 	ISNULL(v.idMarca,'') AS idMarca, ISNULL(v.idModelo,'') AS idModelo, ISNULL(v.idTipo,'') AS idTipo, ISNULL(v.activo,0) AS activo, "
    	    	+ " 	ISNULL(ma.nombre,'') AS descMarca, ISNULL(mo.nombre,'') AS descModelo, ISNULL(tv.nombre,'') AS descTipoVehiculo "
    	    	+ " FROM Vehiculos v LEFT JOIN Modelos mo ON v.idModelo=mo.id "
    	    	+ "		LEFT JOIN Marcas ma ON v.idMarca=ma.id LEFT JOIN tiposVehiculos tv ON v.idTipo=tv.id "
    	    	+ " WHERE v.id=0"+ id +" "
    	    	+ " ORDER BY ma.nombre, mo.nombre, v.dominio ";
    	
    	con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
    	int a = size(rs); 
    	if (a>0) {
    		rs.next();
   	     	
        	it = new Vehiculos();
        	it.setId(rs.getInt("id"));
        	it.setDominio(rs.getString("dominio"));
        	it.setAnio(rs.getInt("anio"));
        	it.setIdMarca(rs.getInt("idMarca"));
        	it.setIdModelo(rs.getInt("idModelo"));
        	it.setIdTipo(rs.getInt("idTipo"));
        	it.setDescMarca(rs.getString("descMarca"));
        	it.setDescModelo(rs.getString("descModelo"));
        	it.setDescTipo(rs.getString("descTipoVehiculo"));
        	it.setActivo(rs.getBoolean("activo"));
        	
    		}
    	rs.close();
    	con.close();
		return it;
	}
	

	
	public String FechaReserva (int idComision, int idVehiculo) {
		String mensaje="";
		try{
			sql =" SELECT TOP 1 a.mensaje "
				+" FROM ( "	
				+"			SELECT TOP 1 'El vehículo selecionado se encuentra asignado a la comisión '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
				+" 				+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
				+" 				CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
				+" 			FROM comisiones c "
				+" 				LEFT JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
				+" 			WHERE dv.idComision<>0"+ idComision +" AND dv.idVehiculo= 0"+ idVehiculo + " AND c.idEstado<>5  "			
				+"				AND ( "
				+"					(SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"					OR "
				+"					(SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"					OR "
				+"					(c.fechaHoraSalida BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
				+"					OR "
				+"					(c.FechaHoraRegreso BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
				+"          		)	"
				+"			UNION ALL "
				+"			SELECT TOP 1 'El vehículo selecionado se encuentra asignado a la Reserva '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
				+" 				+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
				+" 				CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
				+" 			FROM Reservas c "
				+" 			WHERE c.idVehiculo= 0"+ idVehiculo + " "			
				+"				AND ( "
				+"					(SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"					OR "
				+"					(SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +") BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"					OR "
				+"					(c.fechaHoraSalida BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
				+"					OR "
				+"					(c.FechaHoraRegreso BETWEEN (SELECT fechaHoraSalida FROM Comisiones WHERE id=0"+ idComision +") AND (SELECT FechaHoraRegreso FROM Comisiones WHERE id=0"+ idComision +")) "
				+"          		)	"
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
			//Notification.show("","Error al consultar la reserva.",Type.ERROR_MESSAGE);
		}	
		return mensaje;
	}
	
	

	public String FechaReserva1 (int idReserva, int idVehiculo,Date fechaSalida, Date fechaRegreso) {
		String mensaje="";
		try{
			sql =" SELECT TOP 1 a.mensaje "
				+" FROM ( "	
				+"			SELECT TOP 1 'El vehículo selecionado se encuentra asignado a la comisión '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
				+" 				+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
				+" 				CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
				+" 			FROM comisiones c "
				+" 				LEFT JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
				+" 			WHERE  dv.idVehiculo= 0"+ idVehiculo + " AND c.idEstado<>5  "			
				+"				AND ( "
				+"					'"+dateFormat.format(fechaSalida)+"' BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"					OR "
				+"					'"+dateFormat.format(fechaRegreso)+"' BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"					OR "
				+"					(c.fechaHoraSalida BETWEEN '"+dateFormat.format(fechaSalida)+"' AND '"+dateFormat.format(fechaRegreso)+"') "
				+"					OR "
				+"					(c.FechaHoraRegreso BETWEEN '"+dateFormat.format(fechaSalida)+"' AND '"+dateFormat.format(fechaRegreso)+"') "
				+"          		)	"
				+"			UNION ALL "
				+"			SELECT TOP 1 'El vehículo selecionado se encuentra asignado a la Reserva '+ CONVERT(nvarchar(10),c.id)  +' entre el '"
				+" 				+ CONVERT(nvarchar(10),c.fechaHoraSalida,103) + ' ' + CONVERT(nvarchar(8),c.fechaHoraSalida,114) +' y ' + " 
				+" 				CONVERT(nvarchar(10),c.FechaHoraRegreso,103) +' ' + CONVERT(nvarchar(8),c.FechaHoraRegreso,114) AS mensaje "
				+" 			FROM Reservas c "
				+" 			WHERE c.id<>0"+ idReserva +" AND c.idVehiculo= 0"+ idVehiculo + " "			
				+"				AND ( "
				+"					'"+dateFormat.format(fechaSalida)+"' BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"					OR "
				+"					'"+dateFormat.format(fechaRegreso)+"' BETWEEN c.fechaHoraSalida AND c.FechaHoraRegreso "
				+"					OR "
				+"					(c.fechaHoraSalida BETWEEN '"+dateFormat.format(fechaSalida)+"' AND '"+dateFormat.format(fechaRegreso)+"') "
				+"					OR "
				+"					(c.FechaHoraRegreso BETWEEN '"+dateFormat.format(fechaSalida)+"' AND '"+dateFormat.format(fechaRegreso)+"') "
				+"          		)	"
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
			//Notification.show("","Error al consultar la reserva.",Type.ERROR_MESSAGE);
		}	
		return mensaje;
	}
	
	
	public void modifica(String dominio, int anio, int idMarca, int idModelo, int idTipo, Boolean activo, int usrLog){
		sql = "UPDATE Vehiculos SET anio=0" + anio + ", idMarca=0"+ idMarca +", idModelo=0"+ idModelo +", idTipo=0"+ idTipo +", activo='"+ activo +"', "
			+ " 	fechaModificacion=GETDATE(), userModificacion=0"+ usrLog +" "
			+ " WHERE dominio='" + dominio +"'";
		msg="Se actualizaron los datos del Vehiculo: "+ dominio;
		cnn.grabarEnBase(sql,msg,usrLog);		
	}
	
	public void agrega(String dominio, int anio, int idMarca, int idModelo, int idTipo, Boolean activo, int usrLog) {	
	    sql = "INSERT INTO Vehiculos (dominio, anio, idMarca, idModelo, idTipo, activo, fechaCreacion, userCreacion, fechaModificacion, userModificacion) "
	    		+ " VALUES ('"+ dominio +"', 0"+ anio +", 0"+ idMarca +", 0"+ idModelo +", 0"+ idTipo +", '"+activo+"', GETDATE(), 0"+ usrLog +", GETDATE(), 0"+ usrLog +")";
	    msg = "Se Registro el Vehiculo "+ dominio;
	    cnn.grabarEnBase(sql,msg,usrLog);
	}
	
	public void elimina(String dominio, int usrLog) {	
	    sql = "DELETE FROM Vehiculos WHERE dominio='"+ dominio +"' ";
	    msg = "Se Eliminó el Vehiculo "+ dominio;
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


	public int existe(String dominio) throws SQLException {
	    int existe=0;
		sql = "SELECT COUNT(*) As cant FROM Vehiculos WHERE dominio='" + dominio +"'";
		con=cnn.getConn();
    	rs = cnn.obtenerCursor(sql, con);
        
		rs.next();
		existe = rs.getInt("cant");
		rs.close();
		con.close();
	    return existe;
	}
	
	
	@SuppressWarnings("finally")
	public int existenRegistrosRelacionados(String nombre) {
	    int existe=0;
	    try{
	    	sql = "SELECT ISNULL(dbo.RegistrosIdVehiculo('"+ nombre +"'),0) AS cant";
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
