package com.agpro.controles;



import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.vaadin.haijian.ExcelExporter;

import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.util.converter.StringToBooleanConverter;
import com.vaadin.data.util.converter.StringToFloatConverter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.data.util.converter.StringToLongConverter;
import com.vaadin.data.validator.FloatRangeValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.LongRangeValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.renderers.HtmlRenderer;
import com.vaadin.ui.renderers.NumberRenderer;



public class UtilUI {

	
	static String nuevalinea = System.getProperty("line.separator"); // salto linea java

	

	

	public static String valoresWebBrowser() {
		StringBuilder description = new StringBuilder();
		
		WebBrowser webBrowser = Page.getCurrent().getWebBrowser();
		String ipAddress = webBrowser.getAddress();
		String touchDevice = String.valueOf( webBrowser.isTouchDevice() );
		String screenSize = webBrowser.getScreenWidth() + "x" + webBrowser.getScreenHeight();
		String locale = webBrowser.getLocale().toString();
		
		description.append( " { IP_Address=" ).append( ipAddress );
		description.append( " | Locale=" ).append( locale );
		description.append( " | TouchDevice=" ).append( touchDevice );
		description.append( " | ScreenSize=" ).append( screenSize );
		description.append( " }" );
		
		return String.valueOf(description);
	}
	

	public static int devuelveAltoBrowser() {
		int datos=0;
		WebBrowser webBrowser = Page.getCurrent().getWebBrowser();
		datos=webBrowser.getScreenHeight()-150;
		
		return datos;
	}
	
	public static int devuelveAltoCuerpo() {
		int datos=0;
		WebBrowser webBrowser = Page.getCurrent().getWebBrowser();
		datos=webBrowser.getScreenHeight()-250;
		
		return datos;
	}
	
	public static int devuelveAltoCuerpoCns() {
		int datos=0;
		datos=devuelveAltoCuerpo()-10;
		
		return datos;
	}
	
	public static int devuelveAltoCuerpoCnsForm() { // altura para los listados que tienen Myform
		int datos=0;
		datos=devuelveAltoCuerpo()-30;
		
		return datos;
	}
	
	
	public static Window setWindows(Window w,String caption, boolean modal, boolean closable, boolean resizable, float width, float height) { 
				
		w.setCaption(caption);		
		w.center();
		w.setModal(modal);
		w.setClosable(closable);
    		w.setResizable(resizable);
    		if(width!=0) {w.setWidth(width, Unit.EM);}
    		if(height!=0) {w.setHeight(height, Unit.EM);}
    		
		return w;
	}
	
	public static VerticalLayout buildMainLayoutCns() {
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setSizeFull();
		
//		setWidth("100.0%");
//		setHeight(this.devuelveAltoCuerpo()-10,Unit.PIXELS);
		
//		mainLayout.addComponent(buildContenedorPanel());
 
		return mainLayout;
	}
	
	
	
	public static VerticalLayout buildMainLayout(VerticalLayout vl) {
		
		VerticalLayout mainLayout = UtilUI.buildVL("", true, 0);
		
		Panel contenedorPanel = UtilUI.buildPnl("", true, 0, 0);
		
//		vl = UtilUI.buildVL("", true,true, 0);
		
		contenedorPanel.setContent(vl);
		
		mainLayout.addComponent(contenedorPanel);
		
		return mainLayout;		
	}
	
	public static VerticalLayout buildMainLayout(VerticalLayout vl1,VerticalLayout vl2) {
		
		VerticalLayout mainLayout = UtilUI.buildVL("", true,0);
		
		Panel contenedorPanel = UtilUI.buildPnl("", true, 0, 0);
		
		
		Panel contenedorPanel1 = UtilUI.buildPnl("", true, 0, 0);
		contenedorPanel1.setContent(vl1);
		
		Panel contenedorPanel2 = UtilUI.buildPnl("", true, 0, 0);
		contenedorPanel2.setContent(vl2);
		
		
		VerticalLayout mainLayout1 = UtilUI.buildVL("", true, false, 0);
		mainLayout1.addComponents(contenedorPanel1,contenedorPanel2);
		
		contenedorPanel.setContent(mainLayout1);
		
	
		mainLayout.addComponent(contenedorPanel);
		
		return mainLayout;		
	}
	
	public static VerticalLayout buildMainLayout(VerticalLayout vl1,VerticalLayout vl2,VerticalLayout vl3) {
		
		VerticalLayout mainLayout = UtilUI.buildVL("", false,0);
		
		Panel contenedorPanel = UtilUI.buildPnl("", true, 0, 0);
		
		
		Panel contenedorPanel1 = UtilUI.buildPnl("", true, 0, 0);
		contenedorPanel1.setContent(vl1);
		
		Panel contenedorPanel2 = UtilUI.buildPnl("", true, 0, 0);
		contenedorPanel2.setContent(vl2);
		
		Panel contenedorPanel3 = UtilUI.buildPnl("", true, 0, 0);
		contenedorPanel3.setContent(vl3);
		
		
		VerticalLayout mainLayout1 = UtilUI.buildVL("", true, false, 0);
		mainLayout1.addComponents(contenedorPanel1,contenedorPanel2,contenedorPanel3);
		
		contenedorPanel.setContent(mainLayout1);
		
	
		mainLayout.addComponent(contenedorPanel);
		
		return mainLayout;		
	}


	
	public static Panel buildPnl() {
		Panel hl = new Panel();
		hl.setImmediate(false);
		hl.setSizeFull();
		
		return hl;
	}
	
	public static Panel buildPnl(String label, boolean enabled, float widthEM, float heightEM) {
		Panel hl = buildPnl();

		if(label != "") {hl.setCaption(label);}
		
		if(widthEM != 0) {hl.setWidth(widthEM, Unit.EM);}
		
		if(heightEM != 0) {hl.setHeight(heightEM, Unit.EM);}
		
		hl.setEnabled(enabled);

		return hl;
	}
	
	public static HorizontalLayout buildHL() {
		HorizontalLayout hl = new HorizontalLayout();
		hl.setImmediate(false);
//		hl.setSizeFull(); // al habilitar el sizeFull no me pone alto por eso lo comento
		hl.setSpacing(true);
		hl.setMargin(false);
		
		return hl;
	}
	
	public static HorizontalLayout buildHL(String label, boolean spacing, float widthEM) {
		HorizontalLayout hl = buildHL();

		if(label != "") {hl.setCaption(label);}
		
		if(widthEM != 0) {hl.setWidth(widthEM, Unit.EM);}
		
		hl.setSpacing(spacing);

		return hl;
	}
	
	// tengo una con EM y otra con % porque sino cuando no ocupo el 100% me distribuye los elementos en todo el form
	public static HorizontalLayout buildHL(String label, boolean spacing, String widthPorc) {
		HorizontalLayout hl = buildHL();

		if(label != "") {hl.setCaption(label);}
		
		hl.setWidth(widthPorc);
		
		hl.setSpacing(spacing);

		return hl;
	}
	
	public static HorizontalLayout buildHL(String label, boolean spacing, boolean margin, float widthEM) {
		HorizontalLayout hl = buildHL(label, spacing, widthEM);

		hl.setMargin(margin);

		return hl;
	}
	
	public static VerticalLayout buildVL() {
		VerticalLayout hl = new VerticalLayout();
		hl.setImmediate(false);
//		hl.setSizeFull();  // al habilitar el sizeFull no me pone alto por eso lo comento
		hl.setSpacing(true);
		hl.setMargin(false);

		return hl;
	}
	
	public static VerticalLayout buildVL(String label, boolean spacing, float widthEM) {
		VerticalLayout hl = buildVL();

		if(label != "") {hl.setCaption(label);}
		
		if(widthEM != 0) {hl.setWidth(widthEM, Unit.EM);}
		
		hl.setSpacing(spacing);
		return hl;
	}
	
	public static VerticalLayout buildVL(String label, boolean spacing, boolean margin, float widthEM) {
		VerticalLayout hl = buildVL(label, spacing, widthEM);

		hl.setMargin(margin);

		return hl;
	}
	
	public static Table buildTabla(BeanContainer<?, ?> sc,String formatFecha, String formatDecimal) {
		Table table = new Table(null,sc){
			private static final long serialVersionUID = -5853659654299001264L;

			String formatoFecha="dd/MM/yyyy";
			String formatoDecimal="#,###,##0.00";
			
			protected String formatPropertyValue(Object rowId,Object colId, @SuppressWarnings("rawtypes") Property property) {  
				if(formatFecha!="") {formatoFecha=formatFecha;}
				if(formatDecimal!="") {formatoDecimal=formatDecimal;}
				
				if (property.getType() == Date.class) {
					SimpleDateFormat df = new SimpleDateFormat(formatoFecha);
					return df.format((Date)property.getValue());
					
				} else if (property.getType() == Float.class) {
					DecimalFormat df = new DecimalFormat(formatoDecimal);
					return df.format((Float) property.getValue());  
					
				} else if (property.getType() == Long.class) {
	        	   	   DecimalFormat df = new DecimalFormat("######################");
	               return df.format((Long) property.getValue());   
	               
	           } else if (property.getType() == Timestamp.class) {
       	   			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       	   			return df.format((Timestamp)property.getValue());  
	           } 
				return super.formatPropertyValue(rowId, colId, property);
			}
		};
	   
	    table.setWidth("98%");
	    table.setHeight("100%");

	    table.setSizeFull();
	    table.setSelectable(true);
	    table.setMultiSelect(false);
	    table.setImmediate(true);
	    table.setColumnCollapsingAllowed(true);
	    table.setPageLength(17);

	    return table;
	}
	
	public static void coloreaTabla(Table table, String attLabel, String a[][], String modoComparacion) {
		
		table.setCellStyleGenerator(new Table.CellStyleGenerator() {

			private static final long serialVersionUID = 1L;
			
			public String getStyle(Table source, Object itemId, Object propertyId) {
				if (propertyId == null) {
					Item item = table.getItem(itemId);
					String firstName = (String) item.getItemProperty(attLabel).getValue();
					
					for(int i=0;i<a.length;i++) {
						
						if(modoComparacion=="igual") {
							if (firstName.equals(a[i][0])) {return a[i][1];}
							
						} else if(modoComparacion=="empieza") {
							if (firstName.toLowerCase().startsWith(a[i][0])) {return a[i][1];}
							
						} else if(modoComparacion=="contiene") {
							if (firstName.contains(a[i][0])) {return a[i][1];}
							
						}   	            
					}
					return null;
			
				} else {
				    return null;
				
				}   
			}
		});

	}
	
	public static void exportExcelTabla(Table tb, String nombreExcel, String nombreHoja, String formatDate, String formatDecimal) {
		ExcelExport excelExport;
		SimpleDateFormat expFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	   	String date="dd/MM/yyyy";
	   	String decimal="#,###,##0.00";
	   	
		excelExport = new ExcelExport(tb, nombreHoja); 
		
		excelExport.excludeCollapsedColumns();
//       excelExport.setReportTitle("Demo Report");
		  
		excelExport.setExportFileName(nombreExcel+ expFormat.format(new Date()) + ".xls");
		
		excelExport.setDisplayTotals(false);
//		excelExport.setRowHeaders(true);
		  
		if(formatDate!="") { date=formatDate;}
		excelExport.setExcelFormatOfProperty("date", date);
		excelExport.setDateDataFormat(date);
		
		if(formatDecimal!="") { decimal=formatDecimal;}
		excelExport.setDoubleDataFormat(decimal);
		
//       excelExport.setNextTable(tb, "Second Sheet");
		excelExport.setTable(tb);
		excelExport.export();
    }
		

	

	
	public static Grid buildGrid(BeanContainer<?, ?> sc, Object[][] columns, float widthEM, String modeSel_SING_MULT_NONE, 
			String formatFecha, String formatTimestamp, String formatInt, String formatFloat, String formatLong) {
		Grid grd = new Grid();
		
		grd.addStyleName("small");
		grd.addStyleName("compact");
		
		grd.setWidth("100%");
		if(widthEM !=0 ) {grd.setWidth(widthEM, Unit.EM);}
	
		
		if(modeSel_SING_MULT_NONE=="MULT") {
			grd.setSelectionMode(SelectionMode.MULTI);
			
		}else if(modeSel_SING_MULT_NONE=="NONE") {
			grd.setSelectionMode(SelectionMode.NONE);
			
		}else {
			grd.setSelectionMode(SelectionMode.SINGLE);
			
		}
		
		grd.setImmediate(true);
//		grd.setWidth(60f, Unit.EM);
		grd.setHeightMode(HeightMode.ROW);
		grd.setHeightByRows(17);
		
		grd.setContainerDataSource(sc);
		
		Object[] columns1 = new Object[columns.length];
		
		for(int i=0;i<columns.length;i++) {
			columns1[i]=columns[i][0];
			
		}
		grd.setColumns(columns1);
		
		formatGrid(grd, formatFecha, formatTimestamp, formatInt, formatFloat, formatLong); 
		
		alignColGrid(grd,columns); 
		
		return grd;
		
		
	}
	
	public static void formatGrid(Grid grd, String formatFecha0, String formatTimestamp0, 
			String formatInt0, String formatFloat0, String formatLong0) {
			
		String attLabel = new String();
		
		String formatFecha = "dd/MM/yyyy"; 
		String formatTimestamp = "dd/MM/yyyy HH:mm:ss"; 
		String formatInt = "###,###,##0";
		String formatFloat = "#,###,##0.00";
		String formatLong = "######################";
		
		if(formatFecha0 != "") {formatFecha = formatFecha0;}
		if(formatTimestamp0 != "") {formatTimestamp = formatTimestamp0;}
		if(formatInt0 != "") {formatInt = formatInt0;}
		if(formatFloat0 != "") {formatFloat = formatFloat0;}
		if(formatLong0 != "") {formatLong = formatLong0;}
		
		
		for(int i=0;i<grd.getColumns().size();i++) {
			attLabel=grd.getColumns().get(i).getPropertyId().toString();
//			String attLabel="pruebaBoolean";
			try{
		   		if(grd.getContainerDataSource().getType(attLabel)==Boolean.class) {
	//	   			Notification.show("",""+grd.getContainerDataSource().getType(attLabel),Type.ERROR_MESSAGE);
		   	   	 	grd.getColumn(attLabel).setRenderer(new HtmlRenderer(),new StringToBooleanConverter(FontAwesome.CHECK_SQUARE_O.getHtml(), FontAwesome.SQUARE_O.getHtml()));
		
		   		} else if(grd.getContainerDataSource().getType(attLabel)==Date.class) {
	//	   			Notification.show("",""+grd.getContainerDataSource().getType(attLabel),Type.ERROR_MESSAGE);
			   	   	 grd.getColumn(attLabel).setRenderer(new DateRenderer(new SimpleDateFormat(formatFecha)));
		
		   		} else if(grd.getContainerDataSource().getType(attLabel)==Timestamp.class) {
	//	   			Notification.show("",""+grd.getContainerDataSource().getType(attLabel),Type.ERROR_MESSAGE);
			   	   	 grd.getColumn(attLabel).setRenderer(new DateRenderer(new SimpleDateFormat(formatTimestamp)));
		
		   		} else if(grd.getContainerDataSource().getType(attLabel)==Integer.class) {
	//	   			Notification.show("",""+grd.getContainerDataSource().getType(attLabel),Type.ERROR_MESSAGE);
			   	   	 grd.getColumn(attLabel).setRenderer(new NumberRenderer(new DecimalFormat(formatInt)));
		
		   		} else if(grd.getContainerDataSource().getType(attLabel)==Float.class) {
	//	   			Notification.show("",""+grd.getContainerDataSource().getType(attLabel),Type.ERROR_MESSAGE);
			   	   	 grd.getColumn(attLabel).setRenderer(new NumberRenderer(new DecimalFormat(formatFloat)));
		
		   		} else if(grd.getContainerDataSource().getType(attLabel)==Long.class) {
	//	   			Notification.show("",""+grd.getContainerDataSource().getType(attLabel),Type.ERROR_MESSAGE);
			   	   	 grd.getColumn(attLabel).setRenderer(new NumberRenderer(new DecimalFormat(formatLong)));
		
		   		} else if(grd.getContainerDataSource().getType(attLabel)==TextField.class) {
	//	   			Notification.show("",""+grd.getContainerDataSource().getType(attLabel),Type.ERROR_MESSAGE);
//			   	   	 grd.getColumn(attLabel).setRenderer(new HtmlRenderer());
		   			
		   		} 
			} catch(Exception e) {
				LogAndNotification.printError("Error ", "No se puede formatear la columna "+attLabel, e);
			
			}	
   		
		}
   	
   		// AGREGAR EL SIGNO $ EN CADA CELDA DE LA COLUMNA
//   	   	grd.getColumn("id").setRenderer(new NumberRenderer(NumberFormat.getCurrencyInstance(Locale.US)));
   		
   	
   	
		
		
		
	}
	
	public static void formatColGrid(Grid grd, String attLabel, String nombreCol, float widthPx,
			boolean hidable, boolean hidden, boolean editable, boolean sortable) {
		
		grd.getColumn(attLabel).getPropertyId().toString();
   		grd.getColumn(attLabel).setHeaderCaption(nombreCol);
   		grd.getColumn(attLabel).setHidable(hidable);
   		grd.getColumn(attLabel).setHidden(hidden);
   		grd.getColumn(attLabel).setEditable(editable);
   		if(widthPx!=0) {grd.getColumn(attLabel).setWidth(widthPx);}
   		grd.getColumn(attLabel).setSortable(sortable);
   		
	}
	
	public static void alignColGrid(Grid grd,Object a[][]) {
		grd.setCellStyleGenerator((Grid.CellReference cellReference) -> {
			for(int i=0;i<a.length;i++) {

				if (cellReference.getPropertyId().toString().equals((String) a[i][0]) && a[i][1]=="center") {
					if (grd.isEditorEnabled() && grd.getColumn(a[i][0]).isEditable()) {
	   					grd.getColumn(cellReference.getPropertyId().toString()).setHeaderCaption(cellReference.getPropertyId().toString()+"*");}
					
						return "align-center";
	   					
	   			} else if (cellReference.getPropertyId().toString().equals((String) a[i][0]) && a[i][1]=="right") {
					if (grd.isEditorEnabled() && grd.getColumn(a[i][0]).isEditable()) {
	   					grd.getColumn(cellReference.getPropertyId().toString()).setHeaderCaption(cellReference.getPropertyId().toString()+"*");}
					
						return "align-right";
						
	   			} else if (cellReference.getPropertyId().toString().equals((String) a[i][0]) && a[i][1]=="left") {
					if (grd.isEditorEnabled() && grd.getColumn(a[i][0]).isEditable()) {
	   					grd.getColumn(cellReference.getPropertyId().toString()).setHeaderCaption(cellReference.getPropertyId().toString()+"*");}
					
	   				return "align-left";	
	   				
	   			}	
			}
			return null;
		});
	}
	
	public static void resaltaColEditableGrid(Grid grd) {
		grd.setCellStyleGenerator((Grid.CellReference cellReference) -> {
			for(int i=0;i<grd.getColumns().size();i++) {
				if (grd.isEditorEnabled() && grd.getColumn(cellReference.getPropertyId().toString()).isEditable()) {
					grd.getColumn(cellReference.getPropertyId().toString())
						.setHeaderCaption(cellReference.getPropertyId().toString()+"*");

				} 
			}
			return null;
		});
	}

	public static void coloreaRowGrid(Grid grd, String attLabel, String a[][], String modoComparacion) {
		grd.setRowStyleGenerator(rowRef -> {
			for(int i=0;i<a.length;i++) {
				if(modoComparacion=="igual") {
					if (rowRef.getItem().getItemProperty(attLabel).getValue().toString().equals(a[i][0])) {return a[i][1];}
					
				} else if(modoComparacion=="empieza") {
					if (rowRef.getItem().getItemProperty(attLabel).getValue().toString().toLowerCase().startsWith(a[i][0])) {return a[i][1];}
					
				} else if(modoComparacion=="contiene") {
					if (rowRef.getItem().getItemProperty(attLabel).getValue().toString().contains(a[i][0])) {return a[i][1];}
					
				} else {
					return null;
				} 
			}
			return null;
		});
	}
		
	
	

	
	

	public static void exportExcelGrid(ExcelExporter excelExporter, Grid grd, String nombreExcel, String nombreHoja, 
			String formatDate, String formatDecimal) {

		SimpleDateFormat expFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	   	String date="dd/mm/yyyy";
//	   	String decimal="#,###,##0.00";
	   	
					
		int tam=grd.getColumns().size();
		String[] a=new String[tam];
		
		if(formatDate!="") {date=formatDate;}
		excelExporter.setDateFormat(date);
		
//		if(formatDecimal!="") { decimal=formatDecimal;}
//		excelExporter.setLocale(locale);
		
		excelExporter.setContainerToBeExported(grd.getContainerDataSource());
		
		for(int i=0;i<tam;i++) {
			a[i]=grd.getColumns().get(i).getPropertyId().toString();
		}
		excelExporter.setVisibleColumns(a);
		
		for(int i=0;i<tam;i++) {
			excelExporter.setColumnHeader(a[i],grd.getColumns().get(i).getHeaderCaption().toString());
		}
		
		excelExporter.setDownloadFileName(nombreExcel + expFormat.format(new Date()) + ".xls");
		
		
		
    }
	
	
	
	
	
	private static NativeButton buildBtn() {
		NativeButton btn= new NativeButton();
		
		return btn;
	}
	
	public static NativeButton buildBtn(String caption, String description) {
		NativeButton btnNew = buildBtn();
		btnNew.setCaptionAsHtml(true);
		btnNew.setCaption(caption);
		btnNew.setDescription(description);
//		btnNew.setStyleName("btn");
		
		return btnNew;
	}
	
	public static NativeButton buildBtnGrabar() {
		return buildBtn("Grabar","Grabar");
	}
	
	public static NativeButton buildBtnModificar() {
		return buildBtn("Modificar","Modificar");
	}
	
	public static NativeButton buildBtnEliminar() {
		return buildBtn("Eliminar","Eliminar");
	}
	
	public static NativeButton buildBtnSalir() {
		return buildBtn("Salir","Salir");
	}
	
	public static NativeButton buildBtnListar(String descripcion) {
		return buildBtn("...",descripcion);
	}
	
	public static NativeButton buildBtnNew(String description) {
		NativeButton btnNew = buildBtn();
		btnNew.setCaptionAsHtml(true);
		btnNew.setCaption(FontAwesome.FILE_O.getHtml());
		btnNew.setDescription(description);
		btnNew.setStyleName("btn");
		
		return btnNew;
	}
	
	public static NativeButton buildBtnFiltro(String description) {
		NativeButton btnFiltro = new NativeButton();
		btnFiltro.setCaptionAsHtml(true);
		btnFiltro.setCaption(FontAwesome.SEARCH.getHtml());
		btnFiltro.setDescription(description);
		btnFiltro.setStyleName("btn");
		
		return btnFiltro;
	}
	
	public static NativeButton buildBtnExportar(String description) {
		NativeButton btnExportar = new NativeButton();
		btnExportar.setCaptionAsHtml(true);
		btnExportar.setCaption(FontAwesome.FILE_EXCEL_O.getHtml());
		btnExportar.setDescription(description);
		btnExportar.setStyleName("btn");
		
		return btnExportar;
	}
	
	public static NativeButton buildBtnReporte(String description) {
		NativeButton btnReporte = new NativeButton();
		btnReporte.setCaptionAsHtml(true);
		btnReporte.setCaption(FontAwesome.FILE_PDF_O.getHtml());
		btnReporte.setDescription(description);
		btnReporte.setStyleName("btn");
		
		return btnReporte;
	}
	
	public static NativeButton buildBtnExporter(String description) {
		NativeButton excelExporter= new NativeButton();
		excelExporter.setCaptionAsHtml(true);
		excelExporter.setCaption(FontAwesome.FILE_EXCEL_O.getHtml());
		excelExporter.setDescription("Exportar a Excel");
		excelExporter.setStyleName("btn");
			
		return excelExporter;
	}
	
	
	public static ExcelExporter buildBtnExporter1(String description) {
		ExcelExporter excelExporter=new ExcelExporter();
		excelExporter.setCaptionAsHtml(true);
		excelExporter.setCaption(FontAwesome.FILE_EXCEL_O.getHtml());
		excelExporter.setDescription("Exportar a Excel");
		excelExporter.setStyleName("btn");
			
		return excelExporter;
	}
	
	
	public static ComboBox buildCB() {
		ComboBox cb = new ComboBox();
//		cb.addStyleName(ValoTheme.COMBOBOX_TINY);

		cb.setWidth("100%");
		cb.setHeightUndefined();
		
		cb.setValidationVisible(false);
		cb.setVisible(true);
		cb.setEnabled(true);
		cb.setReadOnly(false);
		cb.setImmediate(true);
		
		cb.setRequiredError("El campo es requerido. Es decir no debe estar vacio.");

	    	cb.setInvalidAllowed(true);
	    	cb.setNullSelectionAllowed(true);

		cb.setFilteringMode(FilteringMode.CONTAINS);

		return cb;
	}
	
	public static ComboBox buildFieldCB(String label, String attName, float widthEM, boolean required , boolean readOnly, boolean input, 
			BeanContainer<?,?> container) {
		
		ComboBox cb = buildCB();
		
		try {

			if(label!="") {cb.setCaption(label);}
	
			cb.setTextInputAllowed(input);
			
			cb.setRequiredError("El campo '" + label + "' es requerido. Es decir no debe estar vacio.");
			
			cb.setRequired(required);
			if (cb.isRequired()) {
				cb.setNullSelectionAllowed(false);
			}
			
			if(widthEM> 0) {
				cb.setWidth(widthEM, Unit.EM);
			}
			
			
			
			cb.setContainerDataSource(container);
			cb.setItemCaptionPropertyId(attName);
			

	//		BeanItemContainer optionsBIC = new BeanItemContainer(clazz,new ArrayList());
	//
	//		for (Object option : options) {
	//			optionsBIC.addBean(option);
	//		}
	//		cb.setContainerDataSource(optionsBIC);
	//		
	//		cb.setPropertyDataSource(dtoBI.getItemProperty(attName));
			
		
		
//			if (container.size() == 1) {
//				cb.setValue(container.getIdByIndex(0));
//			}
			
			cargaCombo(cb, container);
			
			
//			if (cb.isRequired() && container.size() > 0) {
//				cb.setValue(container.getIdByIndex(0));
//			}
	
			
			cb.setReadOnly(readOnly);
			
		}catch(Exception e) {
			
		}

		return cb;
	}
	
	
	public static void cargaCombo(ComboBox cb,  BeanContainer<?,?> container) {
		
		cb.setContainerDataSource(container);
		
		if (container.size() == 1) {
			cb.setValue(container.getIdByIndex(0));
		}
		
		
	}
	
	public static DateField buildDF() {
		
		DateField df=new DateField();
		
		df.setWidth("100%");
		df.setHeightUndefined();
		
		df.setValidationVisible(false);
		df.setVisible(true);
		df.setEnabled(true);
		df.setReadOnly(false);
		df.setImmediate(true);
		
		df.setRequiredError("El campo es requerido. Es decir no debe estar vacio.");

		try {
			df.setRangeStart(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/1900"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		df.setDateOutOfRangeMessage("La fecha ingresada se encuentra fuera del rango permitido (fechas válidas desde '01/01/1900' en adelante) ");
		df.setParseErrorMessage("El formato de fecha ingresado es incorrecto (formato válido dd/mm/yyyy)");
		
		
		df.setInvalidAllowed(true);
		
		//df.setValue(new Date());
		df.setDateFormat("dd/MM/yyyy");
		df.setResolution(Resolution.DAY);
		df.setLenient(true);
		df.validate();
		return df;
	}
	
	public static DateField buildFieldDF(String label, boolean diaDeHoy, float widthEM, boolean required, boolean visible, boolean enabled, 
			String formatDate, Resolution resolution) {
		
		DateField tf = buildDF();
		
		try {
			
			if(label!="") {tf.setCaption(label);}
	
			
			tf.setRequired(required);
			tf.setRequiredError("El campo '" + label + "' es requerido. Es decir no debe estar vacio.");
			

			if(widthEM> 0) {
				tf.setWidth(widthEM, Unit.EM);
			}
			
			tf.setVisible(visible);
			tf.setEnabled(enabled);
				
			if(diaDeHoy) {tf.setValue(new Date());}
			
			
			tf.setDateFormat(formatDate);

			tf.setResolution(resolution);
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}

	
	public static TextArea buildTA() {
		TextArea tf = new TextArea();

		tf.setWidth("100%");
		tf.setHeightUndefined();
			
		tf.setVisible(true);
		tf.setEnabled(true);
		tf.setReadOnly(false);
		tf.setImmediate(true);
		tf.setValidationVisible(false);
		
		tf.setRequiredError("El campo es requerido. Es decir no debe estar vacio.");

		return tf;
	}
	
	public static TextArea buildFieldTA(String label, float widthEM, float heightEM, boolean required, boolean visible, boolean enabled) {
		
		TextArea tf = buildTA();
		
		try {
			tf.setCaption(label);
	
			tf.setRequired(required);
			tf.setRequiredError("El campo '" + label + "' es requerido. Es decir no debe estar vacio.");
			

			if(widthEM> 0) {
				tf.setWidth(widthEM, Unit.EM);
			}
			
			if(heightEM> 0) {
				tf.setHeight(heightEM, Unit.EM);
			}
			
			tf.setVisible(visible);
			tf.setEnabled(enabled);
					
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}
	
	public static TextArea buildFieldTAstring(String label, float widthEM, float heightEM, boolean required, boolean visible, boolean enabled, int lenght) {
		
		TextArea tf = buildFieldTA(label,widthEM,heightEM,required,visible,enabled);
		
		try {		
			if(lenght>0) {
			tf.addValidator(new StringLengthValidator("El campo '" + label + "' permite una longitud Máxima de "+ lenght +" caracteres", 0, lenght, true) );
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}
	
	
	public static TextField buildTXT() {
		TextField tf = new TextField();

		tf.setWidth("100%");
		tf.setHeightUndefined();
			
		tf.setVisible(true);
		tf.setEnabled(true);
		tf.setReadOnly(false);
		tf.setImmediate(true);
		tf.setValidationVisible(false);
		
		tf.setRequiredError("El campo es requerido. Es decir no debe estar vacio.");

		return tf;
	}
	
	public static TextField buildFieldTXT(String label, float widthEM, boolean required, boolean visible, boolean enabled) {
		
		TextField tf = buildTXT();
		
		try {
			tf.setCaption(label);
	
			tf.setRequired(required);
			tf.setRequiredError("El campo '" + label + "' es requerido. Es decir no debe estar vacio.");
			

			if(widthEM> 0) {
				tf.setWidth(widthEM, Unit.EM);
			}
			
			tf.setVisible(visible);
			tf.setEnabled(enabled);
					
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}
	
	public static TextField buildFieldTXTstring(String label, float widthEM, boolean required, boolean visible, boolean enabled, int lenght) {
		
		TextField tf = buildFieldTXT(label,widthEM,required,visible,enabled);
		
		try {		
			if(lenght>0) {
			tf.addValidator(new StringLengthValidator("El campo '" + label + "' permite una longitud Máxima de "+ lenght +" caracteres", 0, lenght, true) );
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}
	
	public static TextField buildFieldTXTfloat(String label, float widthEM, boolean required, boolean visible, boolean enabled, float min, float max) {
		
		TextField tf = buildFieldTXT(label,widthEM,required,visible,enabled);
		
		try {
			tf.setConverter(new StringToFloatConverter());
			tf.setConversionError("El campo '" + label + "' solo permite ingresar números");
			tf.setNullRepresentation("");
			if(min>0 || max>0) {
				tf.addValidator(new FloatRangeValidator("El campo '" + label + "' solo permite números entre "+ min +" y "+ max +" - {0} no es correcto", min, max));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}
	
	public static TextField buildFieldTXTint(String label, float widthEM, boolean required, boolean visible, boolean enabled, int min, int max) {
		
		TextField tf = buildFieldTXT(label,widthEM,required,visible,enabled);
		
		try {
			tf.setConverter(new StringToIntegerConverter());
			tf.setConversionError("El campo '" + label + "' solo permite ingresar números enteros");
			tf.setNullRepresentation("");
			if(min>0 || max>0) {
				tf.addValidator(new IntegerRangeValidator("El campo '" + label + "' solo permite números enteros entre "+ min +" y "+ max +" - {0} no es correcto", min, max));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}
	
	public static TextField buildFieldTXTlong(String label, float widthEM, boolean required, boolean visible, boolean enabled, long min, long max) {
		
		TextField tf = buildFieldTXT(label,widthEM,required,visible,enabled);
		
		try {
			tf.setConverter(new StringToLongConverter());
			tf.setConversionError("El campo '" + label + "' solo permite ingresar números enteros");
			tf.setNullRepresentation("");
			if(min>0 || max>0) {
				tf.addValidator(new LongRangeValidator("El campo '" + label + "' solo permite números enteros entre "+ min +" y "+ max +" - {0} no es correcto", min, max));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}
	
	
	public static PasswordField buildPWD() {
		PasswordField tf = new PasswordField();

		tf.setWidth("100%");
		tf.setHeightUndefined();
			
		tf.setVisible(true);
		tf.setEnabled(true);
		tf.setReadOnly(false);
		tf.setImmediate(true);
		tf.setValidationVisible(false);
		
		tf.setRequiredError("El campo es requerido. Es decir no debe estar vacio.");

		return tf;
	}
	
	public static PasswordField buildFieldPwd(String label, float widthEM, boolean required, boolean visible, boolean enabled) {
		
		PasswordField tf = buildPWD();
		
		try {
			tf.setCaption(label);
	
			tf.setRequired(required);
			tf.setRequiredError("El campo '" + label + "' es requerido. Es decir no debe estar vacio.");
			

			if(widthEM> 0) {
				tf.setWidth(widthEM, Unit.EM);
			}
			
			tf.setVisible(visible);
			tf.setEnabled(enabled);
					
		}catch(Exception e) {
			e.printStackTrace();
		}

		return tf;
	}
	
	public static CheckBox buildCHK() {
		CheckBox tf = new CheckBox();

		tf.setWidth("100%");
		tf.setHeightUndefined();
		
		tf.setVisible(true);
		tf.setEnabled(true);
		tf.setReadOnly(false);
		tf.setImmediate(true);
		
		return tf;
	}
	
	public static CheckBox buildFieldCHK(String label, float widthEM, boolean visible, boolean enabled, boolean value) {
		
		CheckBox tf = buildCHK();
		
		try {
			tf.setCaption(label);

			if(widthEM> 0) {
				tf.setWidth(widthEM, Unit.EM);
			}
			
			tf.setVisible(visible);
			tf.setEnabled(enabled);
			tf.setValue(value);
			
		}catch(Exception e) {
			
		}

		return tf;
	}
	
	
	
	public static Exception validar(PasswordField tf) {
		tf.setValidationVisible(true);
		tf.validate();
		return null;
	}
	
	public static Exception validar(TextField tf) {
		tf.setValidationVisible(true);
		tf.validate();
		return null;
	}
	
	public static Exception validar(ComboBox tf) {
		tf.setValidationVisible(true);
		tf.validate();
		return null;
	}
	
	public static Exception validar(TextArea tf) {
		tf.setValidationVisible(true);
		tf.validate();
		return null;
	}
	
	public static Exception validar(DateField tf) {
		tf.setValidationVisible(true);
		tf.validate();
		return null;
	}
	
	public static void msgValidar() {
		Notification.show("Existen errores de validación revisar campos resaltados en rojo",Type.ERROR_MESSAGE);
		
	}
	
	public static void msgValidar(Exception e) {
		LogAndNotification.printError("Existen errores de validación", "Revisar campos resaltados en rojo:", e);

	}
	
	
	public static RegexpValidator validaExpString(int maxLenght) {
		
		int max=maxLenght-2;
		return  new RegexpValidator("^\\S([a-zA-Zá-úÁ-Úä-üÄ-Ü_0-9 ]){0,"+max+"}\\S","{0} no es válido: No se permite caracteres especiales - No se permite espacios al inicio o final - Cantidad mínima de caracteres 2 - Cantidad máxima de caracteres "+ maxLenght) ;
	
	}
	
	
	
	public static RegexpValidator validaExpInt(int min, int max) {
		
		return new RegexpValidator("[1-9][0-9]{4}", "{0} no es válido: Solo se permite ingresar números - No puede empezar con 0 - Rango válido de 1 a 9999");
		
	}
	
	
	public static RegexpValidator validaExpFloat(int precision) {
		
		return new RegexpValidator("[1-9][0-9]*(\\.[0-9]{1,"+precision+"}){0,1}", "{0} no es válido: Solo se permite ingresar números - No puede empezar con 0 - Usar el punto como separador decimal - Solo se permiten "+precision+" decimales"); 
	
	}
	
}
