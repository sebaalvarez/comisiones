package sec.comisiones.manager;


import sec.comisiones.dao.UsuariosDAO;
import sec.comisiones.mapeos.Usuarios;
import com.vaadin.ui.Table;
import com.vaadin.data.util.BeanContainer;



public class UsuariosManager {

	private UsuariosDAO c = new UsuariosDAO();
	private BeanContainer<String, Usuarios> sc = null;


	
 
	public Table cargaTabla(){
		
		sc=c.getAllUsuariosContainer();
		
	   Table table = new Table(null,sc);//  new Table("This is my Table");
	   
	   //Establecemos el tamaño de Grid
	    table.setWidth("98%"); //Ocupa todo el ancho del navegador
	    table.setHeight("100%"); //Altura del Grid.

	    //Opciones en la selección de la tabla
	    table.setSizeFull();
	    table.setSelectable(true);			    // Hacemos que se puedan seleccionar las filas del Grid.
	    table.setMultiSelect(false);			// Selección de múltiples filas del Grid.
	    table.setImmediate(true);				// Envia los cambios de la selección immediately to server.
	    table.setColumnCollapsingAllowed(true); // poder ocultar columnas
	//    table.setPageLength(10);					// cantidad de filas en la tabla
	    
		table.setVisibleColumns(new Object[] {"nombre", "eliminado"});
		table.setColumnHeaders(new String[]  {"Nombre","Bloqueado"});

/*	    
	    table.addContainerProperty ("id", Integer.class, null);
        table.addContainerProperty ("nombre", String.class, null);
        table.addContainerProperty ("password", String.class, null);
        table.addContainerProperty ("empleado", Integer.class, null);
        table.addContainerProperty ("tipodeusuario", Integer.class, null);
        table.addContainerProperty ("creo", Integer.class, null);
        table.addContainerProperty ("modifico", Integer.class, null);
        table.addContainerProperty ("fechacreacion", Date.class, null);
        table.addContainerProperty ("fechamodificacion", Date.class, null);
        table.addContainerProperty ("eliminado", Boolean.class, null);
*/ 
 /*       
  		table.setColumnHeaders("id","id");
        table.setColumnHeaders("nombre","nombre");
        table.setColumnHeaders("password","password");
      	table.setColumnHeaders("empleado","empleado");
        table.setColumnHeaders("tipodeusuario","tipodeusuario");
        table.setColumnHeaders("creo","creo");
        table.setColumnHeaders("modifico","modifico");
        table.setColumnHeaders("fechacreacion","fechacreacion");
        table.setColumnHeaders("fechamodificacion","fechamodificacion");
        table.setColumnHeaders("eliminado","eliminado"); 
*/  

//	    table.setColumnHeaders(new String[]  {"id", "Nombre", "Password","empleado","tipodeusuario","creo","modifico","fechacreacion","fechamodificacion","eliminado" });

/*		
	    // Definimos el nombre y tipo de dato de las columnas. The "default value" parameter is meaningless here. 
	    table.addContainerProperty("name", String.class, null);
	    table.addContainerProperty("city", String.class, null);
	    table.addContainerProperty("year", Integer.class, null);

	    // Carga de filas en la tabla
	    table.addItem(new Object[] {"Nicolaus", "Copernicus", new Integer(1473)}, new Integer(1));
	    table.addItem(new Object[] {"Tycho", "Brahe", new Integer(1546)}, new Integer(2));
	    table.addItem(new Object[] {"Giordano", "Bruno", new Integer(1548)}, new Integer(3));
	    table.addItem(new Object[] {"Galileo", "Galilei", new Integer(1564)}, new Integer(4));
	    table.addItem(new Object[] {"Johannes", "Kepler", new Integer(1571)}, new Integer(5));
	    table.addItem(new Object[] {"Isaac", "Newton", new Integer(1643)}, new Integer(6));
*/    
	    return table;
	}
    

 /*   
	
	public List<Usuarios> obtenerTodos(){
		
		System.out.println("entro a obtener todos");

		sc=c.getAllUsuariosContainer();
	
		int index=0;
		int index0=0;
		int size=sc.size();
		
		System.out.println("tamaño cursor:" + size);
//		Object index0 = 0 //sc.getIdByIndex(index);
//		System.out.println("indice:" + index0);
		
		List<Usuarios> listaUsuarios = new ArrayList<>();
		
		for(index=0; index<size; index++){
			
	//		Object index0 = sc.getIdByIndex(index);
			System.out.println("indice:" + index);
			Usuarios usuario = new Usuarios();	// Creo un objeto de la clase 

			// Pongo los atributos al objeto 
			usuario.setId((int) sc.getItem(index0).getItemProperty("id").getValue());
			usuario.setNombre((String) sc.getItem(index0).getItemProperty("nombre").getValue());
			usuario.setPassword((String) sc.getItem(index0).getItemProperty("password").getValue());
			usuario.setModifico((int) sc.getItem(index0).getItemProperty("modifico").getValue());
			usuario.setFechaModificacion((Date) sc.getItem(index0).getItemProperty("fechamodificacion").getValue());
			usuario.setFechaCreacion((Date) sc.getItem(index0).getItemProperty("fechacreacion").getValue());
			usuario.setidPersonal((int) sc.getItem(index0).getItemProperty("empleado").getValue());
			usuario.setEliminado((boolean) sc.getItem(index0).getItemProperty("activo").getValue());
			usuario.setCreo((int) sc.getItem(index0).getItemProperty("creo").getValue());
			
			System.out.println("valor: " + (String) sc.getItem(index0).getItemProperty("nombre").getValue());
			
			listaUsuarios.add(usuario);		// Añadimos el objeto al ArrayList
		}
		return listaUsuarios;
	}
	
*/
	

	
	
}

