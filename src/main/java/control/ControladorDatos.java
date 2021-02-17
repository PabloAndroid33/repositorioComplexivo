package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
//import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import org.primefaces.context.RequestContext;
//import org.hibernate.validator.internal.util.logging.Log_.logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.shaded.commons.io.IOUtils;
//import org.python.icu.text.RelativeDateTimeFormatter.Direction;


//import ec.edu.ups.wikidatups.modelo.Usuario;
//import ec.edu.ups.wikidatups.negocio.GestorDAO;
import modelo.Comentario;

import modelo.Lectura;
import modelo.ReferenciaAPA;


import modelo.Usuario;

import negocio.ComentarioDao;

import negocio.LecturaDao;
import negocio.ReferenciaAPADao;

import negocio.UsuarioDao;



/** 
 * Esta clase permite llamar 
 * a los objetos Dao de las
 * base da datos ademas proporciona 
 * controlador Bean para que la 
 * capa de Presentacion interactuen
 * con los objetos de la capa modelo
 *@autor Pablo Siguenza
 
 */

@ManagedBean (name="controler")
@ViewScoped
public class ControladorDatos implements Serializable{

	static Logger logger = Logger.getAnonymousLogger();
	
	
	

	@Inject
	ReferenciaAPADao daoReferencia2;
	
	@Inject
	private ReferenciaAPADao daoReferencia;
	private ReferenciaAPA newReferencia;
	private List<ReferenciaAPA> listReferencia;
	private List<ReferenciaAPA> listReferenciaOrdenadas;
	
	

	

	

	@Inject
	private ComentarioDao daoComentario;
	private Comentario newComentario;
	private List<Comentario> listComentario;

	
	
	
	public ComentarioDao getDaoComentario() {
		return daoComentario;
	}

	public void setDaoComentario(ComentarioDao daoComentario) {
		this.daoComentario = daoComentario;
	}

	public Comentario getNewComentario() {
		return newComentario;
	}

	public void setNewComentario(Comentario newComentario) {
		this.newComentario = newComentario;
	}

	public List<Comentario> getListComentario() {
		return listComentario;
	}

	public void setListComentario(List<Comentario> listComentario) {
		this.listComentario = listComentario;
	}

/////////////////////////////////////////////////////////////////
	
	@Inject
	private LecturaDao daoLectura;
	private Lectura newLectura;
	private List<Lectura> listLectura;
	
	
	
	
	
	
	
	
	
	
	
/////////////////////////////////////////////////////////////////	





	public LecturaDao getDaoLectura() {
		return daoLectura;
	}

	public void setDaoLectura(LecturaDao daoLectura) {
		this.daoLectura = daoLectura;
	}

	public Lectura getNewLectura() {
		return newLectura;
	}

	public void setNewLectura(Lectura newLectura) {
		this.newLectura = newLectura;
	}

	public List<Lectura> getListLectura() {
		return listLectura;
	}

	public void setListLectura(List<Lectura> listLectura) {
		this.listLectura = listLectura;
	}

	@Inject
	private  UsuarioDao dao;
	
	//Usuario para Correo
	private Usuario newUsuario=new Usuario();
	private Usuario newUsuarioB=new Usuario();
	private Usuario newUsuarioLector=new Usuario();
	//Usuario para User
	private Usuario newUser=new Usuario();
	
	private int id;
	@Inject
	private FacesContext fc;

	private Usuario usuarioVerificar;
	private boolean logging = false;
	private boolean administrador = false; 
	private List<Usuario> ltsUsuario;
	
	private List<Usuario> ltsUsuarioLector;
	
	public List<Usuario> getLtsUsuarioLector() {
		return ltsUsuarioLector;
	}

	public void setLtsUsuarioLector(List<Usuario> ltsUsuarioLector) {
		this.ltsUsuarioLector = ltsUsuarioLector;
	}

	private Part uploadedFile;
	
	
	private String folder = "resources/";


	/*****/
	private UploadedFile archivo;
	private String destino;
	
	
	
	
	
	
	  String rutaAbsoluta;
	  Socket socket;
	   String saludo;
	
	String texto;
	
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}

	public String getRutaAbsoluta() {
		return rutaAbsoluta;
	}

	public void setRutaAbsoluta(String rutaAbsoluta) {
		this.rutaAbsoluta = rutaAbsoluta;
	}

	public UploadedFile getArchivo() {
		return archivo;
	}

	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public void nuevoDocumento() 
	{
		if(uploadPdf()) {
			newReferencia.setUrlArchivo(destino);
			//create();
		}
	}
	
	
	public Boolean uploadPdf() 
	{
		try{
		destino=Constantes.url;
		System.out.println("Destino metodo Upload "+destino);
		
		File folder=new File(FaceUtils.getPath()+destino);
		System.out.println("Destino con faceUtils "+FaceUtils.getPath()+destino);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		copyFile(getArchivo().getFileName(),getArchivo().getInputstream());
		return true;
		}catch(IOException e) 
		{
			return false;
		}
	}
	
	
	public void copyFile(String fileName,InputStream in) 
	{
		try {
			destino=destino+ fileName;
			System.out.println("destinoXXXX del copy file= "+destino);
			
			OutputStream out=new FileOutputStream(new File(FaceUtils.getPath()+destino));
			
			int read=0;
			byte[] bytes=new byte[1024];
			
			//System.out.println("COORTADO SSSS xxxxFF "+destino.substring(destino.length()-3,destino.length()));
			
			while((read=in.read(bytes))!=-1) 
			{
				out.write(bytes,0,read);
			}
			in.close();
			out.flush();
			out.close();
			
			
			
		}catch(IOException e) {
			System.out.println(e.getMessage());
			//JsfUtil.addErrorMessage(e,ResourceBundle.getBundle("/Bundle").getString("error-cargar-doc"));
		}
	}
	/**////
	
	
	
	

	public Part getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	
	
	

	

	
	
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	

	


//	Getters y setters Pelicula
	
	
	
	


	
	
	
	
	
	
	
	
	

	public ReferenciaAPADao getDaoReferencia2() {
		return daoReferencia2;
	}

	public void setDaoReferencia2(ReferenciaAPADao daoReferencia2) {
		this.daoReferencia2 = daoReferencia2;
	}

	public ReferenciaAPADao getDaoReferencia() {
		return daoReferencia;
	}

	public void setDaoReferencia(ReferenciaAPADao daoReferencia) {
		this.daoReferencia = daoReferencia;
	}

	public ReferenciaAPA getNewReferencia() {
		return newReferencia;
	}

	public void setNewReferencia(ReferenciaAPA newReferencia) {
		this.newReferencia = newReferencia;
	}

	public List<ReferenciaAPA> getListReferencia() {
		return listReferencia;
	}

	public void setListReferencia(List<ReferenciaAPA> listReferencia) {
		this.listReferencia = listReferencia;
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}
	
	
	
	
	
	
	

	public Usuario getNewUsuarioB() {
		return newUsuarioB;
	}

	public void setNewUsuarioB(Usuario newUsuarioB) {
		this.newUsuarioB = newUsuarioB;
	}

	public Usuario getNewUsuarioLector() {
		return newUsuarioLector;
	}

	public void setNewUsuarioLector(Usuario newUsuarioLector) {
		this.newUsuarioLector = newUsuarioLector;
	}

	public Usuario getNewUser() {
		return newUser;
	}

	public void setNewUser(Usuario newUser) {
		this.newUser = newUser;
	}

	public Usuario getUsuarioVerificar() {
		return usuarioVerificar;
	}

	public void setUsuarioVerificar(Usuario usuarioVerificar) {
		this.usuarioVerificar = usuarioVerificar;
	}

	public UsuarioDao getDao() {
		return dao;
	}

	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}

	public boolean isLogging() {
		return logging;
	}

	public void setLogging(boolean logging) {
		this.logging = logging;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public List<Usuario> getLtsUsuario() {
		return ltsUsuario;
	}

	public void setLtsUsuario(List<Usuario> ltsUsuario) {
		this.ltsUsuario = ltsUsuario;
	}


	public List<ReferenciaAPA> getListReferenciaOrdenadas() {
		return listReferenciaOrdenadas;
	}

	public void setListReferenciaOrdenadas(List<ReferenciaAPA> listReferenciaOrdenadas) {
		this.listReferenciaOrdenadas = listReferenciaOrdenadas;
	}

	@PostConstruct
	public void init(){
	
	    newReferencia=new ReferenciaAPA();
	    newComentario=new Comentario();
	    
	    newLectura=new Lectura();
	    
	  //  newComentario.setUsuario(newUsuario);
	    
	    newComentario.setReferencia(newReferencia);

	    
	    
	    
	  //  newPelicula.setCategoria(newCategoria);
	    
	    
	    
	    
	    
	   // newPelicula.setDirector(newDirector);
	    listReferencia=daoReferencia.getReferencia();
	    
	    listLectura=daoLectura.getLecturaUsuario(newUsuarioLector.getIdUsuario());
	    listComentario=daoComentario.getComentarioArticulo(id);
	    listReferenciaOrdenadas=daoReferencia.getReferenciaOrdenada();
	    
	    newUsuario=new Usuario();
	    newUsuarioB=new Usuario();
	    newUsuarioLector=new Usuario();
	    newUser=new Usuario();
	    
	    usuarioVerificar=new Usuario();
		ltsUsuario= dao.getUsuarios();
		ltsUsuarioLector= dao.getUsuariosLector();
		
}

	
	
	public List<Comentario> getComentarioArticulo(int id){
		
		List<Comentario> comentario = listComentario=daoComentario.getComentarioArticulo(id);
		return comentario;
	}
	
public List<Lectura> getLecturaUsuario(int id){
		
		List<Lectura> lectura = listLectura=daoLectura.getLecturaUsuario(id);
		return lectura;
	}
	//////////////////////////////////////	
	//Metodos Crud Categoria
	/*public String insertarCategoria(){
		if(newCategoria.getNombre().equals("")||newCategoria.getDescripcion().equals("")){
			return "noData";
		}else{
			daoCategoria.save(newCategoria);
			init();
			return "categoriaListar";
		}
	}*/
	/*public String actualizarCategoria(int id){
		Categoria categoria =daoCategoria.leer(id);
		newCategoria=categoria;
		return "categoriaRegistro";
	}*/
  
    
    
    
    




//////////////////////////////////////
//Metodos Crud Articulo


public String saveFile() {
	
	
	 String fileName = "";
	
	  try {
		  
		  fileName = getFilename(uploadedFile);
		  
		  System.out.println("fileName  " + fileName);
		  
		  uploadedFile.write(folder+fileName);
		 // return folder+fileName;
		  
           
       } catch (IOException ex) {
           System.out.println(ex);
           return "error";
           
           
       }
	  
	  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("File " + fileName+ " Uploaded!"));
	  return folder+fileName;
	
}


private static String getFilename(Part part) {
   for (String cd : part.getHeader("content-disposition").split(";")) {
       if (cd.trim().startsWith("filename")) {
           String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
           return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
       }
   }
   return null;
}





/**
 * Llama a DaoInsertar que 
 * luego sera invocado por controlador
 * en Vista InsertaReferencia
 **/

public String insertarReferencia() throws IOException, InterruptedException{

	
	
	
	
	//System.out.println("COORTADO SSSS xxxxFF "+wey.substring(otro-3,otro));
	if(uploadPdf()) {
		//int otro=0+destino.length();
		if(conectaServidor()) {
		
		
		
		if(mensaFromServidor()) 
		{
			newReferencia.setKeywords(cortaPalabras());
		}
		}
		
		
		newReferencia.setUrlArchivo(destino);
	
	//newReferencia.setUrlArchivo(saveFile());
		
	
	/*for (int i = 1; i <= 2300000; i++){ //borde de arriba
        System.out.print("#"); 
   }*/
	
	
	
	daoReferencia.save(newReferencia);
	}
	
	
	
	
	
	
	
	
	//saveFile();
	init();
	
	
	
//daoPelicula.save(newPelicula);

//	init();
	System.out.println("ARCHIVO XXXXXXXXXXXXXXX  ----- "+saludo);
	System.out.println("EL TEXTO XXXXXXXXXXXXXXX  ----- "+texto);
	//return "";
return "referenciaApaListar";

}


/**
 * Llama a DaoInsertar que 
 * luego sera invocado por controlador
 * en Vista Insertar Referencia Bibliotecario
 **/
public String insertarReferenciaBiblio() throws IOException, InterruptedException{

	
	
	
	
	//System.out.println("COORTADO SSSS xxxxFF "+wey.substring(otro-3,otro));
	if(uploadPdf()) {
		//int otro=0+destino.length();
		if(conectaServidor()) {
		
		
		
		if(mensaFromServidor()) 
		{
			newReferencia.setKeywords(cortaPalabras());
		}
		}
		
		
		newReferencia.setUrlArchivo(destino);
	
	//newReferencia.setUrlArchivo(saveFile());
		
	
	/*for (int i = 1; i <= 2300000; i++){ //borde de arriba
        System.out.print("#"); 
   }*/
	
	
	
	daoReferencia.save(newReferencia);
	}
	
	
	
	
	
	
	
	
	//saveFile();
	init();
	
	
	
//daoPelicula.save(newPelicula);

//	init();
	System.out.println("ARCHIVO XXXXXXXXXXXXXXX  ----- "+saludo);
	System.out.println("EL TEXTO XXXXXXXXXXXXXXX  ----- "+texto);
	//return "";
return "referenciaBApaListar";

}









//////////////////////////////////////////////////////
/**
 * Ejecuta Python desde cmd
 **/
public  boolean conectaServidor() 
{
boolean var;
	
	rutaAbsoluta = "C:\\TESIS REMIGIO FINAL\\Programas Desarollados\\SistemaWebSociedadLector";
	try {
	//System.out.println("la rutas absolutasXXXXXXXXXXXXXXX  ----- "+rutaAbsoluta);
	
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" cd "+rutaAbsoluta+" && py nltk_pdf.py && exit");
		var=true;
	//	return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		var=false;
		//return false;
	}
	//String command = "C:\\TESIS REMIGIO FINAL\\Programas Desarollados\\SistemaWebSociedadLector\\comando.cmd";
	//Runtime.getRuntime().exec(command);
	return var;
}






/**
 *Se conecta al servidor Socket 
 *para ejecutar codigo Python
 **/
public  boolean mensaFromServidor()
{
	
	for (int i = 1; i <= 700000; i++){ //borde de arriba
        System.out.print("#"); 
   }
	
	
	
	saludo=FaceUtils.getPath()+Constantes.url+getArchivo().getFileName();
	
	
	System.out.println("ARCHIVO XXXXXXXXXXXXXXX  ----- "+saludo);
	
	
	
	try {
		socket = new Socket("localhost",3035);

        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader stdIn =new BufferedReader(isr);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String in = stdIn.readLine();
        System.out.println(in);
        
        out.print(saludo);
        out.flush();
        System.out.println("Conectado ");
        System.out.println("ARCHIVO XXXXXXXXXXXXXXX  ----- "+saludo);
        return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	 
            
            
            
            
           
       
            
            
            
        
            
            
            
            
            
}


/**
 * Lee el Documento de Keywoords
 * coortandolas y guardandolas en un String
 * que luego sera guardado en un Campo de Base de 
 * Datos
 **/
public String cortaPalabras() 
{
	for (int i = 1; i <= 3000000; i++){ //borde de arriba
        System.out.print("#"); 
   }
	
	
	saludo=FaceUtils.getPath()+Constantes.url+getArchivo().getFileName();

	System.out.println("Metodo 	cortaPalabras XXXXXXXXXXXXXXX  ----- "+saludo);
    ArrayList lineas = new ArrayList();
	String part1,part2,valor="";
	 //texto = "";
    try {
        /*Incio del fichero y creación de BufferedReader para poder
         ** lectura (disponer del metodo readLine())*/
      //  File archivo = new File(saludo+"KEYWORDS");
       // File archivo = new File("C:\\TESIS REMIGIO FINAL\\wildfly-20.0.0.Final\\wildfly-20.0.0.Final\\standalone\\deployments\\SistemaWebSociedadLector.war/resources/docs/Protocolos_de_Enrutamiento_Para_la_Capa.pdf"+"KEYWORDS");
        
    	FileReader entrada = new FileReader(saludo+"KEYWORDS.txt");
    	//FileReader entrada = new FileReader("C:\\TESIS REMIGIO FINAL\\wildfly-20.0.0.Final\\wildfly-20.0.0.Final\\standalone\\deployments\\SistemaWebSociedadLector.war/resources/docs/Protocolos_de_Enrutamiento_Para_la_Capa.pdf"+"KEYWORDS.txt");
        BufferedReader br = new BufferedReader(entrada);

        /*Leer Fichero*/
     while((texto=br.readLine())!=null){
       // texto=texto+", "+texto;
    	 
    	 String[] parts = texto.split(" ");
    	
    	part1 = parts[0].replaceAll("Ã", "í").replaceAll("í¡", "á").replaceAll("í³", "ó").replaceAll("í©", "é").replaceAll("í“", "Ó").replaceAll("í±", "ñ"); // 123
    	//part2 = parts[1];
    	
    	
    	
    	if(!part1.equals("--")) {
    	// if((part1.compareTo("--")!=0)) {
    		
    		 //System.out.println("SE DECONTROS");
    		// if((part1.compareTo("El")!=0)) {
    		if(!part1.equals("La")) {
    			if(!part1.equals("El")) {
    				if(!part1.equals("En")) {
    					if(!part1.equals("A")) {
    						if(!part1.equals("â€œ")) {
    							if(!part1.equals("â€�")) {
    								if(!part1.equals("Se")) {
    									if(!part1.equals("Para")) {
    										if(!part1.equals("â€“")) {
    											if(!part1.equals("Los")) {
    												if(!part1.equals("â€¢")) {
    										lineas.add(part1);
    												}
    											}
    										}
    										}
    									}
    							}
    						}
    					}
    				}
    			}
    		}
    		// }
    	 }
        
    	// System.out.println(texto);
     }
     
        entrada.close();
      //  return lineas;

        } 
    catch (IOException e) {

        System.out.println("No se ha encontrado el archivo:" + e.getMessage());
   //     return "error";
        }
    
    
    for (int i = 0; i <= 15; i++){ //borde de arriba
        
		
    	valor=valor+lineas.get(i)+";";
		 //System.out.print(lineas.get(i)); 
	   }
  //  .substring(0, cortaPalabras().length()-1)
    
   // return texto;
    System.out.println("PutoValor XXXXXXXXXXXZZZZZZZZZ" + valor);
	return valor.substring(0, valor.length()-1);
	
}





/////////////////////////////////////////////////////











/**
 *Actualiza la Referencia 
 **/

public String actualizarReferencia(int id){
ReferenciaAPA pelicula =daoReferencia.leer(id);
newReferencia=pelicula;
return "referenciaApaRegistro";
}
/**
 *Redirige a referencia
 *segun cual se seleccione
 **/
public String editarReferencia(int codigo) {
	
	
	return "referencia?faces-redirect=true&id="+codigo;
}

/**
 * Muestra La referencia
 * detasllado segun la referencia
 * que se seleccione
 **/
public String mostrarArticulo(int codigo) {
	
	
	return "lectorLibroDetalle?faces-redirect=true&id="+codigo;
}
/**
 * Carga los Datos de Referencia
 **/
public void loadDatosReferencia() {
	//System.out.println("codigo editar " + id);
	if(id==0)
		return;
	newReferencia = getReferencia(id);
	//System.out.println(newUsuario.getIdUsuario() + " " + newUsuario.getNombre() );
	//System.out.println("#telefonos: " + " " + persona.getTelefonos().size());
	
}

public ReferenciaAPA getReferencia(int codigo) {
	ReferenciaAPA ref = daoReferencia.leer(codigo);
	
	return ref;
	
}

/**
 * Llama a DaoCargar que 
 * luego sera invocado por controlador
 * en Vista Caegar datos Referencia
 **/
public String cargarDatosReferencia() {
	
	try {
		daoReferencia.saveReferencia(newReferencia);
		init();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "referenciaApaActualizacion";
}


/**
 * Llama a DaoActualizar que 
 * luego sera invocado por controlador
 * en Vista Actualizar Calificacion Referencia
 **/
public String actualizarCalificacion() {
	
	try {
		ReferenciaAPA ref2=new ReferenciaAPA();
		
		ref2=daoReferencia.leer(id);
		String nombre=ref2.getNombre();
		String referencia=ref2.getReferencia();
		String resumen=ref2.getResumen();
		String url=ref2.getUrlArchivo();
		String keywords=ref2.getKeywords();
		int numCalifica=newReferencia.getNumCalifica();
		int cuenta=numCalifica+1;
		
		newReferencia.setId(id);
		newReferencia.setNombre(nombre);
		newReferencia.setReferencia(referencia);
		newReferencia.setResumen(resumen);
		newReferencia.setUrlArchivo(url);
		newReferencia.setKeywords(keywords);
		
		newReferencia.setNumCalifica(cuenta);
		
		//newReferencia.setSumatoria(cuenta);
		//newReferencia.setSumatoria();
		
		daoReferencia.save(newReferencia);
		
		
		newReferencia.setId(id);
		newReferencia.setNombre(nombre);
		newReferencia.setReferencia(referencia);
		newReferencia.setResumen(resumen);
		newReferencia.setUrlArchivo(url);
		newReferencia.setKeywords(keywords);
		int suma=newReferencia.getCalificacion();
        newReferencia.setSumatoria(newReferencia.getSumatoria()+suma);
        
        
        float div = (float)newReferencia.getSumatoria()/cuenta;
        
        newReferencia.setCalificacion((int)Math.round (div));
        
        
        DecimalFormat frmt = new DecimalFormat();
		frmt.setMaximumFractionDigits(2);
		float calTotal = Float.parseFloat(frmt.format(div));
        
        newReferencia.setCalificacionTotal(calTotal);
		
        System.out.println( "SUMAXXXXX " + suma +" CuentaXX " +cuenta + " DIVIXX "+div);
        
        
        daoReferencia.save(newReferencia);
        
        System.out.println( "SUMAXXXXX " + suma +" CuentaXX " +cuenta + " DIVIXX "+div);
		
		
		
		init();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "lectorLibroDetalle?faces-redirect=true&id="+id;
}


/**
 * Llama a DaoInsertar que 
 * luego sera invocado por controlador
 * en Vista Eliminar Referencia
 **/
public String eliminarReferencia(int id){
	//FaceUtils.getPath();
	String var=daoReferencia.leer(id).getUrlArchivo();
	File folder=new File(FaceUtils.getPath()+var);
	System.out.println("archivo a eliminar "+ FaceUtils.getPath()+var);
	daoReferencia.borrar(id);
	
//File archivo=new File("/resources/docs/4G tecnologias.pdf");
System.out.println("archivo a eliminar "+ FaceUtils.getPath()+var);
folder.delete();
init();
return null;
}
////////////////////////////////////////////
//Metodos Crud Usuario	

/**
 * Llama a DaoInsertar que 
 * luego sera invocado por controlador
 * en Vista Insertar Usuario Bibliotecario
 **/
public String insertarUsuarioBiblio() {
	newUsuario.setUsrRol("biblio"); 
//	newUsuario.setUsrTopic(false);
	dao.save(newUsuario);
	//init();
	//logging = true;
	//validarUsuario();
	return "usuarioAdministradorListar";
}

/**
 * Llama a DaoInsertar que 
 * luego sera invocado por controlador
 * en Vista Insertar Usuario Lector
 **/
public String insertarUsuarioLector() {
	newUsuarioLector.setUsrRol("lector"); 
//	newUsuario.setUsrTopic(false);
	dao.save(newUsuarioLector);
	//init();
	//logging = true;
	//validarUsuario();
	return "lectorLogin";
}


public String botonIngresarUsuarioLector() {

	return "lectorRegistro";
}

/**
 * Carga los datos del Usuario
 **/
public void loadDatosUsuario() {
	System.out.println("codigo editar " + id);
	if(id==0)
		return;
	newUsuario = getUsuario(id);
	System.out.println(newUsuario.getIdUsuario() + " " + newUsuario.getNombre() );
	//System.out.println("#telefonos: " + " " + persona.getTelefonos().size());
	
}

/**
 * Obtiene Usuario de acuerdo al id
 **/
public Usuario getUsuario(int codigo) {
	Usuario aux = dao.leer(codigo);
	
	return aux;
	
}
/**
 * Redirige a UsuarioEditar de acuerdo al
 * usuario que se aya seleccionado a editar
 **/
public String editarUsuario(int codigo) {
	
	
	return "usuario?faces-redirect=true&id="+codigo;
}

/**
 * Llama a DaoCargar que 
 * luego sera invocado por controlador
 * en Vista Cargar Lectura
 **/
public String cargarDatos() {
	
	try {
		dao.save(newUsuario);
		init();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "usuarioAdministradorActualizacion";
}

/**
 * Llama a DaoEliminar que 
 * luego sera invocado por controlador
 * en Vista Eliminar Usuario
 **/
public String eliminarUsuario(int id){
dao.borrar(id);
init();
return null;
}


////////////////////////////

/**
 * Iniciar Sesion de  Usuario Lector
 * ve si usuario y password son correctos 
 * para acceder como Usuario lector 
 **/
public String validarUsuarioLector(){
	String destino="";
	System.out.println("////////"+usuarioVerificar.getUsrCorreo());
	newUsuarioLector=dao.buscarCorreo(usuarioVerificar);
	String var=newUsuarioLector+" ";
	//newUser=dao.buscarUsuario(usuarioVerificar);
	System.out.println("Valor de USUARIO "+" "+var.substring(19,20));
	int valor=Integer.parseInt(var.substring(19,20));
	//System.out.println("Si existe  y se llama: "+ newUsuario.getUsrNombre());
	if(valor>0){
		//System.out.println("el usuario "+usuarioVerificar.getUsrCorreo()+" si existe");
		//logging = true;.substring(0,8);
		//destino="indexUsuario";
		
			//System.out.println("el usuario "+usuarioVerificar.getUsrCorreo()+"es usuarioLector");
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("lector",newUsuarioLector);
		//administrador = true;
		administrador = true;	
			destino="indexLector";
			//return "indexAdministrador";
		
		//return "indexUsuario";
	}else {
		System.out.println("el usuario lector "+usuarioVerificar.getUsrCorreo()+" no existe");
		
		destino="lectorLogin";
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                                        "Usuario o Password Incorectos",
		                                        "Usuario No existe");
		context.addMessage(null, message);
		context.validationFailed();
		
		return destino;
	}

	
//	System.err.println(newUsuario.getUsuarioTwitter());


	//init();	
	
	return destino;
}





////////////////////////////


/**
 * Iniciar Sesion de  Usuario Administrativo 
 **/
public String validarAdmin() throws IOException{
	
	FacesMessage message2 = null;
	
	
	String destino="";
	System.out.println("////////"+usuarioVerificar.getUsrCorreo());
	newUsuario=dao.buscarCorreoAdmin(usuarioVerificar);
	String var=newUsuario+" ";
	//newUser=dao.buscarUsuario(usuarioVerificar);
	System.out.println("Valor Completo "+" "+var);
	System.out.println("Valor de USUARIO"+" "+var.substring(19,20));
	int valor=Integer.parseInt(var.substring(19,20));
	
	
	
	newUsuarioB=dao.buscarCorreoBiblio(usuarioVerificar);
	String varB=newUsuarioB+" ";
	//newUser=dao.buscarUsuario(usuarioVerificar);
	System.out.println("Valor Completo "+" "+varB);
	System.out.println("Valor de USUARIO BIBLIO"+" "+varB.substring(19,20));
	int valorB=Integer.parseInt(varB.substring(19,20));
	
	
	
	
	//System.out.println("Si existe  y se llama: "+ newUsuario.getUsrNombre());
	if(valor>0){
		//System.out.println("el usuario "+usuarioVerificar.getUsrCorreo()+" si existe");
		//logging = true;.substring(0,8);
		//destino="indexUsuario";
		
			//System.out.println("el usuario "+usuarioVerificar.getUsrCorreo()+"es usuarioLector");
			
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",newUsuario);
			administrador = true;	
			destino="indexAdministrador.xhtml";
			//return "indexAdministrador";
		
		//return "indexUsuario";
	}else if(valorB>0)
	{
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",newUsuarioB);
		administrador = true;	
		destino="indexBibliotecario.xhtml";
		
	}else {
		System.out.println("el usuario lector "+usuarioVerificar.getUsrCorreo()+" no existe");
		
		destino="usuarioLogin";
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                                        "Usuario o Password Incorectos",
		                                        "Usuario No existe");
		context.addMessage(null, message);
		context.validationFailed();
		
		return destino;
	}

	
//	System.err.println(newUsuario.getUsuarioTwitter());


	//init();	
	//FacesContext context2 = FacesContext.getCurrentInstance();
	
	FacesContext.getCurrentInstance().getExternalContext().redirect(destino);
	return destino;
}


/**
 * Permite ver si sesion esta abierta
 * o si no redirige a pagina verificar sesion
 **/
public void verificarSesion() 
{
	try {
		FacesContext context=FacesContext.getCurrentInstance();
		newUsuarioB=(Usuario)context.getExternalContext().getSessionMap().get("usuario");
		if(newUsuarioB==null){
			context.getExternalContext().redirect("usuarioSinPermiso.xhtml");
		}
		
		/*if(usu!=null){
			context.getExternalContext().redirect("usuariofueRegistrado.xhtml");
		}*/
	}catch(Exception e)
	{
		//Algun registro de eror
	}
}
/**
 * Permite cerrar la sesion a Usuario Administrativo
 * accediendo a la pagina usuarioLogin
 **/
public String cerrarSesion()
{
	//FacesContext.getCurrentInstance().getExternalContext().redirect("usuarioLogin");
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "usuarioLogin";
	
}

public void verificarSesionLector() 
{
	try {
		FacesContext context=FacesContext.getCurrentInstance();
		newUsuarioLector=(Usuario)context.getExternalContext().getSessionMap().get("lector");
		if(newUsuarioLector==null){
			context.getExternalContext().redirect("lectorLoginError.xhtml");
		}
		
		/*if(usu!=null){
			context.getExternalContext().redirect("usuariofueRegistrado.xhtml");
		}*/
	}catch(Exception e)
	{
		//Algun registro de eror
	}
}

/**
 * Permite cerrar la sesion a Usuario Lector
 * accediendo a la pagina LectorLogin
 **/

public String cerrarSesionLector()
{
	//FacesContext.getCurrentInstance().getExternalContext().redirect("usuarioLogin");
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "lectorLogin";
	
}

@RequestScoped
public void handleFileUpload(FileUploadEvent event) {  
    FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
    FacesContext.getCurrentInstance().addMessage(null, msg);  
} 







/*public String validarUsuario(){
	String destino="";
	System.out.println("////////"+usuarioVerificar.getUsrCorreo());
	newUsuario=dao.buscarUsuario(usuarioVerificar);
	//System.out.println("Si existe  y se llama: "+ newUsuario.getUsrNombre());
	if(newUsuario!=null){
		System.out.println("el usuario "+usuarioVerificar.getUsrCorreo()+" si existe");
		logging = true;
		destino="indexUsuario";
		if (newUsuario.getUsrRol()){
			//System.out.println("el usuario "+usuarioVerificar.getUsrCorreo()+"es administrador");
			administrador = true;	
			destino="indexAdministrador";
			//return "indexAdministrador";
		}
		//return "indexUsuario";
	}else {
		System.out.println("el usuario "+usuarioVerificar.getUsrCorreo()+" no existe");
		destino="usuarioLogin";
		return destino;
	}
	
//	System.err.println(newUsuario.getUsuarioTwitter());


	//init();	
	
	return destino;
}*/









//////////////////////////////

private String accion;



public String getAccion() {
	return accion;
}

public void setAccion(String accion) {
	this.accion = accion;
}
/**
 * Llama a DaoInsertar que 
 * luego sera invocado por controlador
 * en Vista Insertar Comentario
 **/
public String insertarComentario(){

	//System.out.println("Estamos aqui");
	
//	final String TAG = "MyTag";
	newComentario.setReferencia(newReferencia);
	newComentario.setUsuario(newUsuarioLector);
	daoComentario.save(newComentario);
	//saveFile();

	return "lectorLibroDetalle?faces-redirect=true&id="+id;
	

}
/**
 * Llama a DaoEliminar que 
 * luego sera invocado por controlador
 * en Vista Eliminar Comentario
 **/
public String eliminarComentario(int id){
	//FaceUtils.getPath();
	System.out.println("Estamos aqui XXXXXXXXXXXXXPUXXXX");
	daoComentario.borrar(id);
	
init();
return "lectorLibroDetalle?faces-redirect=true&id="+id;
}

/**
 * Llama a DaoModificar que 
 * luego sera invocado por controlador
 * en Vista Modificar Comentario
 **/
public String modificaCome(int id,String comentario) {
	
	try {
		newComentario=daoComentario.leer(id);
		newComentario.setComentario(comentario);
		System.out.println("Los comentarios Aqui jeje "  + newComentario.getComentario());
		daoComentario.save(newComentario);
		System.out.println("Los comentarios Aqui jeje "  + newComentario.getComentario());
		init();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return "lectorLibroDetalle?faces-redirect=true&id="+id;
}


public String obtenerComentario(int id)
{
	String comentario=daoComentario.leer(id).getComentario();
	this.setAccion("M");
	//System.out.print("Este es el comentario" +comentario); 
	return comentario;

}

public void mostrarComentario()
{
	RequestContext req=RequestContext.getCurrentInstance();
}


///////////////////////////////////

/**
 * Llama a DaoInsertar que 
 * luego sera invocado por controlador
 * en Vista Insertar Lectura
 **/
public String insertarLectura(){

	//System.out.println("Estamos aqui");
	boolean variable=false;
	 listLectura=getLecturaUsuario(newUsuarioLector.getIdUsuario());
	
	 
	 
	    for (int i=0;i<listLectura.size();i++) {
	        
	    	
	        System.out.println("Miercoles xxxx  "+listLectura.get(i).getReferencia().getId());
	        if(id==listLectura.get(i).getReferencia().getId())
	        {
	        	variable=true;
	        	break;
	        }
	        //System.out.println("Variable del Verdadero "+variable);
	      }
	    
	 
	
//	final String TAG = "MyTag";
	if(variable==false){
		newLectura.setReferencia(newReferencia);
		newLectura.setUsuario(newUsuarioLector);
		System.out.println("Este es el usuario Escoge"+" "+newLectura.getUsuario()+" Este es el Articulo Lector " +newLectura.getReferencia().getId()+" El controlador" +id);
		daoLectura.save(newLectura);	
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El Documento a sido Agregado a Mis Lecturas", "El Documento a sido Agregado a Mis Lecturas"));
		/*FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
		                                        "El Documento a sido Agregado a Mis Lecturas",
		                                        "Usuario No existe");
		context.addMessage(null, message);
		context.validationFailed();*/
	
		
	}
	
	if(variable==true) {
		
		System.out.println("Este registro ya existe");
		
		//String mensaje = "<html><p style='color:blue;'>Hola EsStackoverflow</p></html>";
		//JOptionPane.showMessageDialog(null,mensaje);
		
	/*	FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		                                        "Este Documento ya existe en Mis Lecturas",
		                                        "Usuario No existe");
		context.addMessage(null, message);
		context.validationFailed();*/
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Este Documento ya existe en Mis Lecturas", "System Error"));
		
	}
	
	//saveFile();
 return "";
	//return "lectorLibroDetalle?faces-redirect=true&id="+id;
	

}
/**
 * Llama a DaoEliminar que 
 * luego sera invocado por controlador
 * en Vista Eliminar Lectura
 **/
public String eliminarLectura(int id){
	//FaceUtils.getPath();
	
	daoLectura.borrar(id);
	
init();
return null;
}


}
