package sec.comisiones.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import com.agpro.controles.Conexion;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.components.calendar.event.BasicEvent;




public class EventosDAO {
	private Conexion cnn = new Conexion();
	Connection con=null;
	private ResultSet rs1 = null;
	private String sql;
	private String msg = "";

	
	public BeanItemContainer<BasicEvent> getAllEventosContainer(String tipoEvento, String param1, String param2, String fec1, String fec2,boolean venc){
		BeanItemContainer<BasicEvent> container = new BeanItemContainer<BasicEvent>(BasicEvent.class);
		
		
		if(tipoEvento=="choferes"){
			container=getAllEventosChoferesContainer(fec1,fec2,venc);
			
		} else if (tipoEvento=="vehiculos"){
			container=getAllEventosVehiculosContainer(fec1,fec2,venc);
			
		} else if (tipoEvento=="comisiones"){
			container=getAllEventosComisionesContainer(param1,param2);
			
		} else if (tipoEvento=="todos"){
			container=getAllEventosTodosContainer(fec1,fec2,venc);
			
		} 
		
		
		return container;
		
	}

	
	
	public BeanItemContainer<BasicEvent> getAllEventosChoferesContainer(String fec1, String fec2,boolean venc) {
		BeanItemContainer<BasicEvent> container = new BeanItemContainer<BasicEvent>(BasicEvent.class);
		try {
			String criterio="";
			if (venc){
				if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraSalida BETWEEN  '"+ fec1 +"' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) ";}
			 	if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND ((fechaHoraSalida BETWEEN  '01/01/1900' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) OR (fechaHoraSalida IS NULL))";}
			 	if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND (fechaHoraSalida BETWEEN  '"+ fec1 +"' AND '01/01/2100')";}
			 	if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraSalida BETWEEN  '01/01/1900' AND '01/01/2100') OR (fechaHoraSalida IS NULL))";}
			 } else {
				if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraRegreso BETWEEN  '"+ fec1 +"' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) ";}
				if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraRegreso BETWEEN  '02/01/1900' AND DATEADD(MINUTE,1439,'"+ fec2 +"'))";}
				if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraRegreso BETWEEN  '"+ fec1 +"' AND '01/01/2100') OR (fechaHoraRegreso IS NULL))";}
			 	if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraRegreso BETWEEN  '01/01/1900' AND '01/01/2100') OR (fechaHoraRegreso IS NULL))";}
			 }
			
			sql = "SELECT id, caption, descripcion, fechaHoraSalida, fechaHoraRegreso, AnioS, MesS, DiaS, HoraS, MinutoS, SegundoS, AnioR, MesR, DiaR, HoraR, MinutoR, SegundoR, "
				+ "		idChofer, idVehiculo "
				+ " FROM ("
				+ "		SELECT c.id, 'Com.: ' + RIGHT('000000'+CAST(c.id AS nvarchar(10)),6) +' // '+e.apellido+', '+e.nombre AS caption, '' AS descripcion, "
				+ " 		c.fechaHoraSalida, c.fechaHoraRegreso, "
				+ "			DATEPART(YEAR,c.fechaHoraSalida) AS AnioS, DATEPART(MONTH,c.fechaHoraSalida)-1 AS MesS, DATEPART(DAY,c.fechaHoraSalida) AS DiaS, "
				+ "			DATEPART(HOUR,c.fechaHoraSalida) AS HoraS, DATEPART(MINUTE,c.fechaHoraSalida) AS MinutoS, DATEPART(SECOND,c.fechaHoraSalida) AS SegundoS,  "
				+ "			DATEPART(YEAR,c.fechaHoraRegreso) AS AnioR, DATEPART(MONTH,c.fechaHoraRegreso)-1 AS MesR, DATEPART(DAY,c.fechaHoraRegreso) AS diaR,  "
				+ "			DATEPART(HOUR,c.fechaHoraRegreso) AS HoraR, DATEPART(MINUTE,c.fechaHoraRegreso) AS MinutoR, DATEPART(SECOND,c.fechaHoraRegreso) AS SegundoR, "
				+ "			dv.idChofer, ch.idEmpleado, e.apellido, e.nombre AS nomEmp, dv.idVehiculo, v.dominio, v.idMarca, ma.nombre As nomMa, v.idModelo, mo.nombre AS nomMo, '' AS styleName "
				+ " 	FROM Comisiones c  INNER JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
				+ "			LEFT JOIN Choferes ch ON dv.idChofer=ch.id LEFT JOIN Empleados e ON ch.idEmpleado=e.id "
				+ "			LEFT JOIN Vehiculos v ON dv.idVehiculo=v.id LEFT JOIN Marcas ma ON v.idMarca=ma.id "
				+ "			LEFT JOIN Modelos mo ON v.idModelo=mo.id "
				+ " 	UNION ALL "
				+ "		SELECT c.id, 'Res.: ' + RIGHT('000000'+CAST(c.id AS nvarchar(10)),6) +' // '+e.apellido+', '+e.nombre AS caption, '' AS descripcion, "
				+ "			c.fechaHoraSalida, c.fechaHoraRegreso, " 
				+ "			DATEPART(YEAR,c.fechaHoraSalida) AS AnioS, DATEPART(MONTH,c.fechaHoraSalida)-1 AS MesS, DATEPART(DAY,c.fechaHoraSalida) AS DiaS, " 
				+ "			DATEPART(HOUR,c.fechaHoraSalida) AS HoraS, DATEPART(MINUTE,c.fechaHoraSalida) AS MinutoS, DATEPART(SECOND,c.fechaHoraSalida) AS SegundoS,"
				+ "			DATEPART(YEAR,c.fechaHoraRegreso) AS AnioR, DATEPART(MONTH,c.fechaHoraRegreso)-1 AS MesR, DATEPART(DAY,c.fechaHoraRegreso) AS diaR, " 
				+ "			DATEPART(HOUR,c.fechaHoraRegreso) AS HoraR, DATEPART(MINUTE,c.fechaHoraRegreso) AS MinutoR, DATEPART(SECOND,c.fechaHoraRegreso) AS SegundoR, "
				+ "			c.idChofer, ch.idEmpleado, e.apellido, e.nombre, 0, '', '', '', '', '', '' AS styleName "
				+ "		FROM Reservas c  "
				+ "			INNER JOIN Choferes ch ON c.idChofer=ch.id AND c.chofer=1"
				+ "			LEFT JOIN Empleados e ON ch.idEmpleado=e.id "
				+ " ) a "
				+ " WHERE 1=1 " + criterio;

			con=cnn.getConn();
	    	rs1 = cnn.obtenerCursor(sql, con);

			int i=1;
			while (rs1.next()) {
				BasicEvent a = new BasicEvent(rs1.getString("caption"),rs1.getString("descripcion"),
									new GregorianCalendar(rs1.getInt("AnioS"),rs1.getInt("MesS"),rs1.getInt("DiaS"),rs1.getInt("HoraS"),rs1.getInt("MinutoS")).getTime(),
									new GregorianCalendar(rs1.getInt("AnioR"),rs1.getInt("MesR"),rs1.getInt("DiaR"),rs1.getInt("HoraR"),rs1.getInt("MinutoR")).getTime()
								);
				a.setStyleName("color"+rs1.getInt("idChofer"));
				i++;
				container.addBean(a);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
	        try {
				rs1.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return container;
	}

	
	public BeanItemContainer<BasicEvent> getAllEventosVehiculosContainer(String fec1, String fec2,boolean venc) {
		BeanItemContainer<BasicEvent> container = new BeanItemContainer<BasicEvent>(BasicEvent.class);
		try {
			String criterio="";

			if (venc){
				if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraSalida BETWEEN  '"+ fec1 +"' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) ";}
			 	if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND ((fechaHoraSalida BETWEEN  '01/01/1900' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) OR (fechaHoraSalida IS NULL))";}
			 	if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND (fechaHoraSalida BETWEEN  '"+ fec1 +"' AND '01/01/2100')";}
			 	if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraSalida BETWEEN  '01/01/1900' AND '01/01/2100') OR (fechaHoraSalida IS NULL))";}
			 } else {
				if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraRegreso BETWEEN  '"+ fec1 +"' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) ";}
				if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraRegreso BETWEEN  '02/01/1900' AND DATEADD(MINUTE,1439,'"+ fec2 +"'))";}
				if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraRegreso BETWEEN  '"+ fec1 +"' AND '01/01/2100') OR (fechaHoraRegreso IS NULL))";}
			 	if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraRegreso BETWEEN  '01/01/1900' AND '01/01/2100') OR (fechaHoraRegreso IS NULL))";}
			 }
			
			
			sql = "SELECT id, caption, descripcion, fechaHoraSalida, fechaHoraRegreso, AnioS, MesS, DiaS, HoraS, MinutoS, SegundoS, AnioR, MesR, DiaR, HoraR, MinutoR, SegundoR, "
				+ "		idChofer, idVehiculo "
				+ " FROM ("
				+ "		SELECT c.id, 'Com.: ' + RIGHT('000000'+CAST(c.id AS nvarchar(10)),6) +' // '+v.dominio+' - '+ma.nombre+' - '+mo.nombre AS caption, '' AS descripcion, "
				+ " 		c.fechaHoraSalida, c.fechaHoraRegreso, "
				+ "			DATEPART(YEAR,c.fechaHoraSalida) AS AnioS, DATEPART(MONTH,c.fechaHoraSalida)-1 AS MesS, DATEPART(DAY,c.fechaHoraSalida) AS DiaS, "
				+ "			DATEPART(HOUR,c.fechaHoraSalida) AS HoraS, DATEPART(MINUTE,c.fechaHoraSalida) AS MinutoS, DATEPART(SECOND,c.fechaHoraSalida) AS SegundoS,  "
				+ "			DATEPART(YEAR,c.fechaHoraRegreso) AS AnioR, DATEPART(MONTH,c.fechaHoraRegreso)-1 AS MesR, DATEPART(DAY,c.fechaHoraRegreso) AS diaR,  "
				+ "			DATEPART(HOUR,c.fechaHoraRegreso) AS HoraR, DATEPART(MINUTE,c.fechaHoraRegreso) AS MinutoR, DATEPART(SECOND,c.fechaHoraRegreso) AS SegundoR, "
				+ "			dv.idChofer, ch.idEmpleado, e.apellido, e.nombre AS nomEmp, dv.idVehiculo, v.dominio, v.idMarca, ma.nombre As nomMa, v.idModelo, mo.nombre AS nomMo, '' AS styleName "
				+ " 	FROM Comisiones c  INNER JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
				+ "			LEFT JOIN Choferes ch ON dv.idChofer=ch.id LEFT JOIN Empleados e ON ch.idEmpleado=e.id "
				+ "			LEFT JOIN Vehiculos v ON dv.idVehiculo=v.id LEFT JOIN Marcas ma ON v.idMarca=ma.id "
				+ "			LEFT JOIN Modelos mo ON v.idModelo=mo.id "
				+ " 	UNION ALL "
				+ "		SELECT c.id, 'Res.: ' + RIGHT('000000'+CAST(c.id AS nvarchar(10)),6) +' // '+v.dominio+' - '+ma.nombre+' - '+mo.nombre AS caption, '' AS descripcion, "
				+ "			c.fechaHoraSalida, c.fechaHoraRegreso, " 
				+ "			DATEPART(YEAR,c.fechaHoraSalida) AS AnioS, DATEPART(MONTH,c.fechaHoraSalida)-1 AS MesS, DATEPART(DAY,c.fechaHoraSalida) AS DiaS, " 
				+ "			DATEPART(HOUR,c.fechaHoraSalida) AS HoraS, DATEPART(MINUTE,c.fechaHoraSalida) AS MinutoS, DATEPART(SECOND,c.fechaHoraSalida) AS SegundoS,"
				+ "			DATEPART(YEAR,c.fechaHoraRegreso) AS AnioR, DATEPART(MONTH,c.fechaHoraRegreso)-1 AS MesR, DATEPART(DAY,c.fechaHoraRegreso) AS diaR, " 
				+ "			DATEPART(HOUR,c.fechaHoraRegreso) AS HoraR, DATEPART(MINUTE,c.fechaHoraRegreso) AS MinutoR, DATEPART(SECOND,c.fechaHoraRegreso) AS SegundoR, "
				+ "			0, 0, '', '', c.idVehiculo, v.dominio, v.idMarca, ma.nombre AS nomMa, v.idModelo, mo.nombre AS nomMo, '' AS styleName "
				+ "		FROM Reservas c "
				+ "			INNER JOIN Vehiculos v ON c.idVehiculo=v.id AND c.vehiculo=1 "
				+ "			LEFT JOIN Marcas ma ON v.idMarca=ma.id "
				+ "			LEFT JOIN Modelos mo ON v.idModelo=mo.id "
				+ " ) a "
				+ " WHERE 1=1 " + criterio;

			con=cnn.getConn();
	    	rs1 = cnn.obtenerCursor(sql, con);

			int i=1;
			while (rs1.next()) {
				BasicEvent a = new BasicEvent(rs1.getString("caption"),rs1.getString("descripcion"),
									new GregorianCalendar(rs1.getInt("AnioS"),rs1.getInt("MesS"),rs1.getInt("DiaS"),rs1.getInt("HoraS"),rs1.getInt("MinutoS")).getTime(),
									new GregorianCalendar(rs1.getInt("AnioR"),rs1.getInt("MesR"),rs1.getInt("DiaR"),rs1.getInt("HoraR"),rs1.getInt("MinutoR")).getTime()
								);
				a.setStyleName("color"+rs1.getInt("idVehiculo"));
				i++;
				container.addBean(a);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
	        try {
				rs1.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return container;
	}
	
	
	public BeanItemContainer<BasicEvent> getAllEventosComisionesContainer(String tipo, String idUser) {
		BeanItemContainer<BasicEvent> container = new BeanItemContainer<BasicEvent>(BasicEvent.class);
		try {
			String criterio= "";
			
			if(tipo!=""){
				if(tipo=="TODASGENERADAS"){
					criterio= " AND ( c.userCreacion="+idUser+" "
							+ "			OR (SELECT TOP 1 u.id FROM programas p INNER JOIN Usuarios u ON p.idEmpleado=u.idEmpleado WHERE c.idPrograma=p.id)="+idUser+""
							+ " ) ";
				} else if(tipo=="TODAS"){
					criterio= " ";
				}
			}
			
			sql = "SELECT c.id, 'Com.: ' + RIGHT('000000'+CAST(c.id AS nvarchar(10)),6) AS caption, '' AS descripcion, "
				+ " 	c.fechaHoraSalida, c.fechaHoraRegreso, "
				+ "		DATEPART(YEAR,c.fechaHoraSalida) AS AnioS, DATEPART(MONTH,c.fechaHoraSalida)-1 AS MesS, DATEPART(DAY,c.fechaHoraSalida) AS DiaS, "
				+ "		DATEPART(HOUR,c.fechaHoraSalida) AS HoraS, DATEPART(MINUTE,c.fechaHoraSalida) AS MinutoS, DATEPART(SECOND,c.fechaHoraSalida) AS SegundoS,  "
				+ "		DATEPART(YEAR,c.fechaHoraRegreso) AS AnioR, DATEPART(MONTH,c.fechaHoraRegreso)-1 AS MesR, DATEPART(DAY,c.fechaHoraRegreso) AS diaR,  "
				+ "		DATEPART(HOUR,c.fechaHoraRegreso) AS HoraR, DATEPART(MINUTE,c.fechaHoraRegreso) AS MinutoR, DATEPART(SECOND,c.fechaHoraRegreso) AS SegundoR, "
				+ "		dv.idChofer, ch.idEmpleado, e.apellido, e.nombre, dv.idVehiculo, v.dominio, v.idMarca, ma.nombre, v.idModelo, mo.nombre, '' AS styleName, "
				+ "		RIGHT(CAST(c.id AS nvarchar(10)),1) AS color"
				+ " FROM Comisiones c  LEFT JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
				+ "		LEFT JOIN Choferes ch ON dv.idChofer=ch.id LEFT JOIN Empleados e ON ch.idEmpleado=e.id "
				+ "		LEFT JOIN Vehiculos v ON dv.idVehiculo=v.id LEFT JOIN Marcas ma ON v.idMarca=ma.id "
				+ "		LEFT JOIN Modelos mo ON v.idModelo=mo.id "
				+ " WHERE 1=1 "+ criterio;

			con=cnn.getConn();
	    	rs1 = cnn.obtenerCursor(sql, con);

			int i=1;
			while (rs1.next()) {
				BasicEvent a = new BasicEvent(rs1.getString("caption"),rs1.getString("descripcion"),
									new GregorianCalendar(rs1.getInt("AnioS"),rs1.getInt("MesS"),rs1.getInt("DiaS"),rs1.getInt("HoraS"),rs1.getInt("MinutoS")).getTime(),
									new GregorianCalendar(rs1.getInt("AnioR"),rs1.getInt("MesR"),rs1.getInt("DiaR"),rs1.getInt("HoraR"),rs1.getInt("MinutoR")).getTime()
								);
				a.setStyleName("color"+String.valueOf(rs1.getInt("color")));
				i++;
				container.addBean(a);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
	        try {
				rs1.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return container;
	}
	
	
	public BeanItemContainer<BasicEvent> getAllEventosTodosContainer(String fec1, String fec2,boolean venc) {
		BeanItemContainer<BasicEvent> container = new BeanItemContainer<BasicEvent>(BasicEvent.class);
		try {
			String criterio="";
			if (venc){
				if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraSalida BETWEEN  '"+ fec1 +"' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) ";}
			 	if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND ((fechaHoraSalida BETWEEN  '01/01/1900' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) OR (fechaHoraSalida IS NULL))";}
			 	if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND (fechaHoraSalida BETWEEN  '"+ fec1 +"' AND '01/01/2100')";}
			 	if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraSalida BETWEEN  '01/01/1900' AND '01/01/2100') OR (fechaHoraSalida IS NULL))";}
			 } else {
				if (fec1 != "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraRegreso BETWEEN  '"+ fec1 +"' AND DATEADD(MINUTE,1439,'"+ fec2 +"')) ";}
				if (fec1 == "" && fec2 != ""){ criterio = criterio + " AND (fechaHoraRegreso BETWEEN  '02/01/1900' AND DATEADD(MINUTE,1439,'"+ fec2 +"'))";}
				if (fec1 != "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraRegreso BETWEEN  '"+ fec1 +"' AND '01/01/2100') OR (fechaHoraRegreso IS NULL))";}
			 	if (fec1 == "" && fec2 == ""){ criterio = criterio + " AND ((fechaHoraRegreso BETWEEN  '01/01/1900' AND '01/01/2100') OR (fechaHoraRegreso IS NULL))";}
			 }
			
			sql = "SELECT id, caption, descripcion, fechaHoraSalida, fechaHoraRegreso, AnioS, MesS, DiaS, HoraS, MinutoS, SegundoS, AnioR, MesR, DiaR, HoraR, MinutoR, SegundoR, "
				+ "		idChofer, idVehiculo "
				+ " FROM ("
//				+ "		SELECT c.id, 'Com.: ' + RIGHT('000000'+CAST(c.id AS nvarchar(10)),6) +' // '+e.apellido+', '+e.nombre AS caption, '' AS descripcion, "
//				+ " 		c.fechaHoraSalida, c.fechaHoraRegreso, "
//				+ "			DATEPART(YEAR,c.fechaHoraSalida) AS AnioS, DATEPART(MONTH,c.fechaHoraSalida)-1 AS MesS, DATEPART(DAY,c.fechaHoraSalida) AS DiaS, "
//				+ "			DATEPART(HOUR,c.fechaHoraSalida) AS HoraS, DATEPART(MINUTE,c.fechaHoraSalida) AS MinutoS, DATEPART(SECOND,c.fechaHoraSalida) AS SegundoS,  "
//				+ "			DATEPART(YEAR,c.fechaHoraRegreso) AS AnioR, DATEPART(MONTH,c.fechaHoraRegreso)-1 AS MesR, DATEPART(DAY,c.fechaHoraRegreso) AS diaR,  "
//				+ "			DATEPART(HOUR,c.fechaHoraRegreso) AS HoraR, DATEPART(MINUTE,c.fechaHoraRegreso) AS MinutoR, DATEPART(SECOND,c.fechaHoraRegreso) AS SegundoR, "
//				+ "			dv.idChofer, ch.idEmpleado, e.apellido, e.nombre AS nomEmp, dv.idVehiculo, v.dominio, v.idMarca, ma.nombre As nomMa, v.idModelo, mo.nombre AS nomMo, '' AS styleName "
//				+ " 	FROM Comisiones c  INNER JOIN DetallesVehiculos dv ON c.Id=dv.idComision  "
//				+ "			LEFT JOIN Choferes ch ON dv.idChofer=ch.id LEFT JOIN Empleados e ON ch.idEmpleado=e.id "
//				+ "			LEFT JOIN Vehiculos v ON dv.idVehiculo=v.id LEFT JOIN Marcas ma ON v.idMarca=ma.id "
//				+ "			LEFT JOIN Modelos mo ON v.idModelo=mo.id "
//				+ " 	UNION ALL "
				+ "		SELECT c.id, 'Res.: ' + RIGHT('000000'+CAST(c.id AS nvarchar(10)),6) +' // '+e.apellido+', '+e.nombre AS caption, '' AS descripcion, "
				+ "			c.fechaHoraSalida, c.fechaHoraRegreso, " 
				+ "			DATEPART(YEAR,c.fechaHoraSalida) AS AnioS, DATEPART(MONTH,c.fechaHoraSalida)-1 AS MesS, DATEPART(DAY,c.fechaHoraSalida) AS DiaS, " 
				+ "			DATEPART(HOUR,c.fechaHoraSalida) AS HoraS, DATEPART(MINUTE,c.fechaHoraSalida) AS MinutoS, DATEPART(SECOND,c.fechaHoraSalida) AS SegundoS,"
				+ "			DATEPART(YEAR,c.fechaHoraRegreso) AS AnioR, DATEPART(MONTH,c.fechaHoraRegreso)-1 AS MesR, DATEPART(DAY,c.fechaHoraRegreso) AS diaR, " 
				+ "			DATEPART(HOUR,c.fechaHoraRegreso) AS HoraR, DATEPART(MINUTE,c.fechaHoraRegreso) AS MinutoR, DATEPART(SECOND,c.fechaHoraRegreso) AS SegundoR, "
				+ "			c.idChofer, ch.idEmpleado, e.apellido, e.nombre, 0 AS idVehiculo, '' AS dominio, '' AS idMarca, '' AS nomMa, '' AS idModelo, '' AS nomMo, '' AS styleName "
				+ "		FROM Reservas c  "
				+ "			INNER JOIN Choferes ch ON c.idChofer=ch.id AND c.chofer=1"
				+ "			LEFT JOIN Empleados e ON ch.idEmpleado=e.id "
				+ " 	UNION ALL "
				+ "		SELECT c.id, 'Res.: ' + RIGHT('000000'+CAST(c.id AS nvarchar(10)),6) +' // '+v.dominio+' - '+ma.nombre+' - '+mo.nombre AS caption, '' AS descripcion, "
				+ "			c.fechaHoraSalida, c.fechaHoraRegreso, " 
				+ "			DATEPART(YEAR,c.fechaHoraSalida) AS AnioS, DATEPART(MONTH,c.fechaHoraSalida)-1 AS MesS, DATEPART(DAY,c.fechaHoraSalida) AS DiaS, " 
				+ "			DATEPART(HOUR,c.fechaHoraSalida) AS HoraS, DATEPART(MINUTE,c.fechaHoraSalida) AS MinutoS, DATEPART(SECOND,c.fechaHoraSalida) AS SegundoS,"
				+ "			DATEPART(YEAR,c.fechaHoraRegreso) AS AnioR, DATEPART(MONTH,c.fechaHoraRegreso)-1 AS MesR, DATEPART(DAY,c.fechaHoraRegreso) AS diaR, " 
				+ "			DATEPART(HOUR,c.fechaHoraRegreso) AS HoraR, DATEPART(MINUTE,c.fechaHoraRegreso) AS MinutoR, DATEPART(SECOND,c.fechaHoraRegreso) AS SegundoR, "
				+ "			0, 0, '', '', c.idVehiculo, v.dominio, v.idMarca, ma.nombre AS nomMa, v.idModelo, mo.nombre AS nomMo, '' AS styleName "
				+ "		FROM Reservas c "
				+ "			INNER JOIN Vehiculos v ON c.idVehiculo=v.id AND c.vehiculo=1"
				+ "			LEFT JOIN Marcas ma ON v.idMarca=ma.id "
				+ "			LEFT JOIN Modelos mo ON v.idModelo=mo.id "
				+ " ) a "
				+ " WHERE 1=1 " + criterio;

			con=cnn.getConn();
	    	rs1 = cnn.obtenerCursor(sql, con);

			int i=1;
			while (rs1.next()) {
				BasicEvent a = new BasicEvent(rs1.getString("caption"),rs1.getString("descripcion"),
									new GregorianCalendar(rs1.getInt("AnioS"),rs1.getInt("MesS"),rs1.getInt("DiaS"),rs1.getInt("HoraS"),rs1.getInt("MinutoS")).getTime(),
									new GregorianCalendar(rs1.getInt("AnioR"),rs1.getInt("MesR"),rs1.getInt("DiaR"),rs1.getInt("HoraR"),rs1.getInt("MinutoR")).getTime()
								);
				a.setStyleName("color"+rs1.getInt("idChofer"));
				i++;
				container.addBean(a);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
	        try {
				rs1.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return container;
	}

	
}
