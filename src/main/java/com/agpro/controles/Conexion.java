package com.agpro.controles;


import static java.lang.System.err;

import java.io.File;
import java.io.IOException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import javax.sql.DataSource;

import java.util.Properties;

import net.ucanaccess.converters.TypesMap.AccessType; 
import net.ucanaccess.ext.FunctionType; 
import net.ucanaccess.jdbc.UcanaccessConnection; 
import net.ucanaccess.jdbc.UcanaccessDriver;

import com.vaadin.data.util.sqlcontainer.connection.JDBCConnectionPool;
import com.vaadin.data.util.sqlcontainer.connection.SimpleJDBCConnectionPool;

import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WebBrowser;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import com.agpro.controles.infoSistema.InfoHost;
//import org.apache.commons.dbcp2.BasicDataSource;



@SuppressWarnings("unused")
public class Conexion {

    static JDBCConnectionPool connectionPool;

    private DataSource dataSource=null;
    private Connection conn=null; 

    
    private static Properties p = new Properties();
    
    
    private static Statement statement;
    
    private static EncryptDecrypt enc = new EncryptDecrypt();
    
    private static String driver = "";
	private static String cadena = "";
	private static String user = "";
	private static String password = "";
	
	private static String serverInstancia="";
	private static String user1 = "";
	private static String password1 = "";
	private static String base = "";
	private static String tipoBase = "";
	private static String sistema = "";
	private static String sistemaNombre = "";
	
	private static boolean login = false;
	private static String usr = "";
	private static String pass = "";
	private static int idRolUsuario = 0;
	
	private static String nuevalinea = System.getProperty("line.separator"); 
	
//	private String serverInstancia="192.168.56.101";
//	private String user1 = "seba";
//	private String password1 = "habilitacion";

	
//	private String serverInstancia="localhost\\SQL2014EXP";
//	String user1 = "seba";
//	String password1 = "S3b4st14n$1";
	
	
//	private String base="AchalayCatering";
//	private String base="AchalayCerrillos";

	

	
	
	// Find the application directory  VER SI DE ESTA MANERA ES MEJOR REFERENCIAR A LAS IMAGENES Y REPORTES
	//	String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	//	FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/logochico.png"));
		
	public static String getpathReporteSistema() {
		//		String path = "C:\\Users\\Seba\\Documents\\Repositorios\\AchalayV7\\trunk\\WSTesis\\ProyectoAchalay7\\src\\com\\achalay\\reportes\\";
		//		String path = "//Users//seba//eclipse-workspace//ProyectoAchalay7//src//com//achalay//reportes//"; 
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		String path = basepath + "//WEB-INF//classes//com//agpro//reportes//";
		return path;
	}
	
	
	public static String getpathImagenesSistema() {
		//		String path = "C:\\Users\\Seba\\Documents\\Repositorios\\AchalayV7\\trunk\\WSTesis\\ProyectoAchalay7\\WebContent\\WEB-INF\\images\\";
		//		String path = "//Users//seba//eclipse-workspace//ProyectoAchalay7//WebContent//WEB-INF//images//";
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		String path = basepath + "//WEB-INF//imagenes//"+Conexion.getSistema()+"//";
		
		return path;
	}
	
	
	public static String getpathUploads() {
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath().substring(0, 
				VaadinService.getCurrent().getBaseDirectory().getAbsolutePath().length()-
				VaadinService.getCurrent().getBaseDirectory().getName().length()-1
				);
		String path = basepath + "//"+Conexion.getSistema()+"_Upload//";
		new File(path).mkdir();
		return path;
	}
	
	
	public static String getpathReportesGenerados() {
		String path = Conexion.getpathUploads() + "ReportesGenerados//";
		new File(path).mkdir();
		return path;
	}
	
	
	public static String getpathUploadsVarios() {
		String path = Conexion.getpathUploads() + "UploadsVarios//";
		new File(path).mkdir();
		return path;
	}
	
	
	public static String getpathUploadsImagenes() {
		String path = Conexion.getpathUploads() + "Imagenes//";
		new File(path).mkdir();
		return path;
	}
	
	
	public static String getpathkml() {
		//		String path = "C:\\Users\\Seba\\Documents\\Repositorios\\AchalayV7\\trunk\\WSTesis\\ProyectoAchalay7\\WebContent\\WEB-INF\\images\\";
		//		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		//		String path = basepath + "\\WEB-INF\\kml\\";
		String path = "http://138.219.43.202/kml/";
		return path;
	}
	
	
	public static String getpathConfiguracion() {
		//		String path = "C:\\Users\\Seba\\Documents\\Repositorios\\AchalayV7\\trunk\\WSTesis\\ProyectoAchalay7\\src\\com\\achalay\\reportes\\";
		//		String path = "//Users//seba//eclipse-workspace//ProyectoAchalay7//src//com//achalay//reportes//"; 
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
		String path = basepath + "//WEB-INF//Configuracion//";
		return path;
	}

	
	
	
	public static boolean isLogin() {
		return login;
	}


	public static String getUsr() {
		return usr;
	}


	public static String getPass() {
		return pass;
	}


	public static int getIdRolUsuario() {
		return idRolUsuario;
	}
	
	
	public static String getBase(){ // a este método lo voy a usar en todos los DAO cuando tenga diferentes tipos de bases
		return tipoBase;
	}
	
	
	public static String getSistema() {
		return sistema;
	}
	
	
	public static String getSistemaNombre() {
		return sistemaNombre;
	}
	
	
	public static void getDatosServer(){

//		LeerArchivoTXT cnn = new LeerArchivoTXT();
//
//		String path = this.getpathConfiguracion() + "conf.ini";
//		
//		serverInstancia=cnn.DevuelveValor(path, "server");
//		user1 = cnn.DevuelveValor(path, "user");
//		password1=enc.decryptPropertyValue(cnn.DevuelveValor(path, "pass"));
//		base = cnn.DevuelveValor(path, "base");	
//		tipoBase = cnn.DevuelveValor(path, "tipoBase");	
//		sistema = cnn.DevuelveValor(path, "sistema");
//		sistemaNombre = cnn.DevuelveValor(path, "sistemaNombre");
		
//		System.out.println("Server: "+serverInstancia+" // User: "+user1+" // Pass: "+password1+" // base: "+base);
		
		
		p = new LeerProperties().DevuelveProperties(Conexion.getpathConfiguracion(),"conf");
		
		serverInstancia = p.getProperty("server").trim();
		user1 = p.getProperty("base.user").trim();
		password1 = enc.decryptPropertyValue(p.getProperty("base.pass").trim());
		base = p.getProperty("base.nombre").trim();
		tipoBase = p.getProperty("tipoBase").trim();
		sistema = p.getProperty("sistema.nombre").trim();
		sistemaNombre = p.getProperty("sistemaNombre").trim();
		login = Boolean.parseBoolean(p.getProperty("login").trim());
		usr = p.getProperty("sistema.user").trim();
		pass = new EncryptDecrypt().decryptPropertyValue(p.getProperty("sistema.pass").trim());
		idRolUsuario = Integer.parseInt(p.getProperty("sistema.idRolUsuario").trim());

	
		
//		Notification.show("","serverInstancia:'"+serverInstancia+"', user1: '"+user1+"' "	
//				+ ", password1: '"+password1+"', base: '"+base+"', tipoBase: '"+tipoBase+"' "	
//				+ ", sistema: '"+sistema+"', login: '"+login+"', usr: '"+usr+"', pass: '"+pass+"' "	
//				+ ", idRolUsuario: '"+idRolUsuario+"' ",Type.ERROR_MESSAGE);
		
		
		
	}
	
	
	

	
	public static String getBaseInterno_1(){ // este método lo uso en esta misma clase en el método devuelveSqlLog 
//		String base="MySQL";
//		String base="SQLServer";
		String base="";
//		Conexion.getDatosServer();
		base=tipoBase;
		return base;
	}
	
	
	
	public String getDatosMail(){
		String mail="";
		return mail;
	}
	
	
	
	public Conexion(){
//		pool();
		Conexion.getDatosServer();
		
		if(tipoBase.equals("SQLServer")){
			driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";	
			cadena = "jdbc:sqlserver://"+serverInstancia+";databaseName="+base+";";
			//jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
			user = user1;
			password = password1;
					
		} else if(tipoBase.equals("MySQL")){
			driver = "com.mysql.jdbc.Driver";	
			cadena = "jdbc:mysql://localhost:3307/sif1?useSSL=false";
			user = "root";
			password = "";

		}	
		

//		System.out.println("AbsolutePath: "+VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+nuevalinea+
//					" - Name: "+ VaadinService.getCurrent().getBaseDirectory().getName()+nuevalinea+
//					" - filePath: "+ VaadinService.getCurrent().getBaseDirectory().getAbsolutePath().substring(0, 
//							VaadinService.getCurrent().getBaseDirectory().getAbsolutePath().length()-
//							VaadinService.getCurrent().getBaseDirectory().getName().length()-1
//							)
//);
	
	}
	


    public boolean conexionAccess(){
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection conexion = DriverManager.getConnection("jdbc:ucanaccess://\\\\SVRSMA\\att\\att2000.mdb");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("select * from DEPARTMENTS");
            while ( rs.next() )
            {
                System.out.println(rs.getObject(1) + " - " + rs.getObject(2));

            }
            return true;
        }
        catch ( Exception e )
        {
            System.out.println("Error al ejecutar la consulta "+e.getMessage());
            return false;
        }
    }

	
	
	static JDBCConnectionPool abrir(){
		try {
			connectionPool = new SimpleJDBCConnectionPool(driver, cadena, user, password, 1, 50);

		}catch(Exception e){
			System.out.println("No se pudo obtener el pool de conexión");
			Notification.show("No se puede conectar a la base de datos","Comuniquese con el administrador del sistema.",Type.ERROR_MESSAGE);
			e.printStackTrace ();
			
		}
			
		return connectionPool;
	}

	
	public static Connection getConn() {
		Connection conn = null;

		try {
			 conn = abrir().reserveConnection();
//			 conn = DriverManager.getConnection(cadena, user, password);
			 
		} catch (SQLException e) {
			System.out.println("No se pudo obtener la conexión");
			Notification.show("No se puede conectar a la base de datos","Comuniquese con el administrador del sistema.",Type.ERROR_MESSAGE);
//			Notification.show("",this.createSqlExceptionInfo(e),Type.ERROR_MESSAGE);

		}
		return conn;
	}
	
	

	
	
//    private void pool(){
//    		try {
//			basicDataSource = new BasicDataSource();
//	        basicDataSource.setDriverClassName(driver);
//	        basicDataSource.setUsername(user);
//	        basicDataSource.setPassword(password);
//	        basicDataSource.setUrl(cadena);
//	        basicDataSource.setMaxActive(50);
//	        basicDataSource.setMaxIdle(50);
//	        basicDataSource.setRemoveAbandoned(true);
//	        basicDataSource.setRemoveAbandonedTimeout(5000);
//	        basicDataSource.setMaxWait(6000);
//	
//	        dataSource = basicDataSource;
//	    
//	        
//			conn=dataSource.getConnection();
//			
//		} catch (SQLException e) {
//			System.out.println("No se pudo obtener la conexión");
//			Notification.show("No se puede conectar a la base de datos",nuevalinea +"Comuniquese con el administrador del sistema."
//					+ nuevalinea + e.getMessage(),Type.ERROR_MESSAGE);
//			
//		} catch (Exception e) {
//			Notification.show("ERROR","Exception: "+e.getMessage(),Type.ERROR_MESSAGE);
//			
//		}
//
//    }
//	
//	
//    public Connection getConn () {
//    		pool();
//		return conn;
//    }
//
//
//	public void devolverConexion() {
//			if(conn!=null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//	}
	
	

	
	
	
	public static ResultSet obtenerCursor(String sql, Connection con){
		ResultSet rs=null;

		if(con!= null){
			try{	
				statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );
		        rs = statement.executeQuery(sql);
//		        this.grabarEnLog(sql, 0, 0, "Cns", "OK", "", con);
		        
			}catch(SQLException e){
				System.out.println("No se pudo cargar el RecordSet. "+ e.getMessage());
				System.out.println("SQL: " + sql);
				Notification.show("Error al Obtener Cursor: ",  e.getMessage(), Type.ERROR_MESSAGE);
				
//				Notification.show("",this.createSqlExceptionInfo(e),Type.ERROR_MESSAGE);
				
//				err.println(createSqlExceptionInfo(e));
				
				Conexion.grabarEnLog(sql, 0, 0, "Cns", "ERROR", e.getMessage(), con);

	        } finally {
//	        		connectionPool.releaseConnection(con);
	            resetStatement();
	            
	        }
		} 
				
	  return rs;
	}
	
	

	

//	public String select(String tableName, String fields[], String crits) {
//        String selectStatement = "SELECT * "+ "FROM " + tableName + " "+ "WHERE " + crits;
//        String ret = "";        
//
//        try {
//        		Connection connection=null;
//        		statement = connection.createStatement();
//            ResultSet result = statement.executeQuery(selectStatement);
//
//            while (result.next()) {
//                for (String field : fields) {
//                    String currentFieldValue = result.getString(field);
//
//                    if (currentFieldValue != null) {
//                        ret += result.getString(field) + "\t";
//                    }
//                }
//
//                ret = ret.substring(0, ret.length() - 1) + "\n";
//            }
//
//        } catch (SQLException e) {
//            err.println(createSqlExceptionInfo(e));
//            
//            
//        } finally {
//            resetStatement();
//            
//        }
//
//        return ret;
//    }
	
	
	
	
	
	
	
	
	
	public static boolean grabarEnBase(String sql, String msg, int usr) {
		Connection conn=null;
		
		try{
			conn = Conexion.getConn();
		    statement = conn.createStatement();
	        statement.executeUpdate(sql);
	        statement.close();
	        conn.commit();
	        
	        if (msg!=""){
	        		LogAndNotification.printSuccessOk(msg);
	        }
	        
	        Conexion.grabarEnLog(sql, usr, 0, "Exec", "OK", "", conn);
	        return true;
		} catch (SQLException e) {
			System.out.println("No se pudo grabar en la base");
			System.out.println("SQL: " + sql);
			
			String mensaje;
			if(e.getMessage().contains("DELETE en conflicto con la restricción REFERENCE")) {
				mensaje= "No se puede eliminar el registro debido a que tiene registros asociado";
			} else {
				mensaje=e.getMessage();
			}
			
			Notification.show("Error al intentar Grabar el Registro: ", mensaje, Type.ERROR_MESSAGE);
//			Notification.show("",this.createSqlExceptionInfo(e),Type.ERROR_MESSAGE);
			Conexion.grabarEnLog(sql, usr, 0, "Exec", "ERROR", e.getMessage(), conn);
			
			e.printStackTrace ();
			return false;
		}finally {
			try {
				conn.close();
//				connectionPool.releaseConnection(conn);
	            resetStatement();
	            
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
            
        }
		
	
	}
	
	

	public static void grabarEnLog(String sql, int idUser, int idRol, String accion, String estatus, String comentario, Connection con) {
		String sql1="";
		try{
			
			WebBrowser webBrowser = Page.getCurrent().getWebBrowser();
			String ipAddress = webBrowser.getAddress();
			String touchDevice = String.valueOf( webBrowser.isTouchDevice() );
			String screenSize = webBrowser.getScreenWidth() + "x" + webBrowser.getScreenHeight();
			String locale = webBrowser.getLocale().toString();
			String infoSistema = new InfoHost().toStringGeneral();
	
			StringBuilder description = new StringBuilder();
			description.append( " { IP_Address=" ).append( ipAddress );
			description.append( " | Locale=" ).append( locale );
			description.append( " | TouchDevice=" ).append( touchDevice );
			description.append( " | ScreenSize=" ).append( screenSize );
			description.append( " }" );
			
			String descripcion = new String();
			descripcion = " { IP_Address="+ ipAddress 
					+" | Locale="+ locale 
					+" | TouchDevice="+ touchDevice 
					+" | ScreenSize="+ screenSize 
					+" }";
			
			sql1=devuelveSqlLog(sql, idRol, accion, ipAddress, idUser, estatus, comentario, infoSistema, descripcion);
			
			if(con.isClosed()) {
				Connection conn=Conexion.getConn();
			    statement = conn.createStatement();
		        statement.executeUpdate(sql1);
		        statement.close();
		        conn.commit();
		        conn.close();
			} else {
			    statement = con.createStatement();
		        statement.executeUpdate(sql1);
		        statement.close();
		        con.commit();
			}
	        
		} catch (SQLException e) {
			Notification.show("Error al intentar Grabar el Log: ", e.getMessage(), Type.ERROR_MESSAGE);
//			Notification.show("",this.createSqlExceptionInfo(e),Type.ERROR_MESSAGE);
			System.out.println("No se pudo grabar en la base");
			System.out.println("SQL: " + sql);
			e.printStackTrace ();
			
		} finally {
			resetStatement();
    
		}
	}
	
	
	
	public static String devuelveSqlLog(String sqlEx, int idRol, String accion, String ip, int idUser, String estado, 
			String comentario, String infoSistema, String descripcion){
		String sql="";
		sqlEx = sqlEx.replace("'", "´");
		comentario = comentario.replace("'", "´").replace("-", "//");
		infoSistema = infoSistema.replace("'", "´");
		descripcion = descripcion.replace("'", "´");
		
		if(Conexion.getBase().equals("SQLServer")){
			sql = "INSERT INTO logAcceso (idUsuario,idRol,fechaAcceso,accion,pc,sqlExecute,estatus,comentario, infoSistema, descripcion) "
				+ "	VALUES (0"+ idUser +", 0"+ idRol +", GETDATE(),'"+ accion  +"','"+ ip +"','"+ sqlEx +"','"+ estado +"','"+ comentario +"',"
				+ "		'"+ infoSistema +"', '"+ descripcion +"')";
		
		} else if(Conexion.getBase().equals("MySQL")){
			sql = "INSERT INTO logAcceso (idUsuario,idRol,fechaAcceso,accion,pc,sqlExecute,estatus,comentario, infoSistema, descripcion) "
				+ "	VALUES (0"+ idUser +", 0"+ idRol +", NOW(),'"+ accion  +"','"+ ip +"','"+ sqlEx +"','"+ estado +"','"+ comentario +"', "
				+ "		'"+ infoSistema +"', '"+ descripcion +"')";
		}
		return sql;
	}
	
	

	
	
	
	
	public static int existeRegistro(String sql,int i) {
		int existe=1;

		Connection con=null;
		ResultSet rs = null;
		try {
				con=Conexion.getConn();
				rs = Conexion.obtenerCursor(sql, con);
				rs.next();
				existe = rs.getInt(i);

				return existe;
			} catch (SQLException e){
				e.printStackTrace();
				return 1;
			}finally{
				try {
					rs.close();
					con.close();
//					connectionPool.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
	}
	
	
	public static float getFloat(String sql, int i) {
		float existe=1;

		Connection con=null;
		ResultSet rs = null;
		try {
				con=Conexion.getConn();
				rs = Conexion.obtenerCursor(sql, con);
				rs.next();
				existe = rs.getFloat(i);

				return existe;
			} catch (SQLException e){
				e.printStackTrace();
				return 1;
			}finally{
				try {
					rs.close();
					con.close();
//					connectionPool.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
	}	
	
	
	public static String getString(String sql, int i) {
		String existe="";

		Connection con=null;
		ResultSet rs = null;
		try {
				con=Conexion.getConn();
				rs = Conexion.obtenerCursor(sql, con);
				rs.next();
				existe = rs.getString(i);

				return existe;
			} catch (SQLException e){
				e.printStackTrace();
				return "";
			}finally{
				try {
					rs.close();
					con.close();
//					connectionPool.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
	}
	
	
	public static boolean getBoolean(String sql, int i) {
		boolean existe=false;

		Connection con=null;
		ResultSet rs = null;
		try {
				con=Conexion.getConn();
				rs = Conexion.obtenerCursor(sql, con);
				rs.next();
				existe = rs.getBoolean(i);

				return existe;
			} catch (SQLException e){
				e.printStackTrace();
				return false;
			}finally{
				try {
					rs.close();
					con.close();
//					connectionPool.releaseConnection(con);
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
	}
	
	
    public static int getUltimoId(String string) {

    		return Conexion.existeRegistro("SELECT MAX(id) As id FROM "+ string +"", 1);
	}
    
    
    public static String createExceptionInfo(Exception e) {
        String ret   = "Description:\t"          + e.getMessage();

        return ret;
    }
    
	
    public static String createSqlExceptionInfo(SQLException e) {
        String ret   = "SQL-State:\t"            + e.getSQLState()  + "\n";
        ret         += "SQL error-code:\t"       + e.getErrorCode() + "\n";
        ret         += "Description:\t"          + e.getMessage();

        return ret;
    }

    
    private static void resetStatement() {
        if (statement != null) {
            statement = null;
        }
    }


	

	
	
}
