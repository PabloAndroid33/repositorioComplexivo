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
	
	
	


	
	
	/**
     * Objeto de ReferenciaDao a ser inyectada
     * en la clase Controlador
     */
	@Inject
	private ReferenciaAPADao daoReferencia;
	
	/**
     * Objeto de ReferenciaDao declarada
     */
	private ReferenciaAPA newReferencia;
	
	/**
     * Objeto de lista ReferenciaAPA declarada
     */
	private List<ReferenciaAPA> listReferencia;
	/**
     * Objeto de lista ReferenciaAPA para utilizar
     * en lista ordenada por Calificacion Mayor
     */
	private List<ReferenciaAPA> listReferenciaOrdenadas;
	
	/**
     * Objeto de ComentarioDao a ser inyectada
     * en la clase Controlador
     */
	@Inject
	private ComentarioDao daoComentario;
	/**
     * Objeto newComentario de la clase Comentario
     */
	private Comentario newComentario;
	/**
     * Objeto de lista Comentario declarada
     */
	private List<Comentario> listComentario;

	private List<Comentario> listComentarioA;
	
	///////////////////////////////////
	

	
	
	public List<Comentario> getListComentarioA() {
		return listComentarioA;
	}

	public void setListComentarioA(List<Comentario> listComentarioA) {
		this.listComentarioA = listComentarioA;
	}
	
	//////////////////////////////////
	
	
	
	/**
     * Objeto de LecturaDao a ser inyectada
     * en la clase Controlador
     */
	@Inject
	private LecturaDao daoLectura;
	
	/**
     * Objeto newLectura de la clase Lectura
     */
	private Lectura newLectura;
	/**
     * Objeto de lista Lectura declarada
     */
	private List<Lectura> listLectura;
	
	
	
	/**
     * Objeto del UsuarioDao a ser inyectada
     * en la clase Controlador
     */
	@Inject
	private  UsuarioDao dao;
	
	//Usuario para Correo
	
	/**
     * Objeto del Usuario a ser utilizada
     * para operaciones de usuario Super Admin
     */
	private Usuario newUsuario=new Usuario();
	/**
     * Objeto del Usuario a ser utilizada
     * para operaciones de usuario Bibliotecario
     */
	private Usuario newUsuarioB=new Usuario();
	/**
     * Objeto del Usuario a ser utilizada
     * para operaciones de usuario Lector
     */
	private Usuario newUsuarioLector=new Usuario();
	
	
	/**
     * Atributo id para utilizar 
     * en referencias a objetos:"Referencias,Comentarios,Lecturas,Usuarios" 
     */
	private int id;
	@Inject
	private FacesContext fc;

	/**
     * Objeto del Usuario a ser utilizada
     * para comparar y verificar usuario
     */
	private Usuario usuarioVerificar;
	
	/**
     * Atributo para verificar logueo de usuario 
     */
	private boolean logging = false;
	/**
     * Atributo para verificar logueo  de Administrador de usuario 
     */
	private boolean administrador = false; 
	
	/**
     * Objeto de lista Lectura declarada
     */
	private List<Usuario> ltsUsuario;
	/**
     * Objeto de lista Usuario para
     * utilizar en usuarios tipo "lector"
     */
	private List<Usuario> ltsUsuarioLector;
	
	
	/**
     * Objeto del tipo Part para guardar archivos
     */
	private Part uploadedFile;
	
	/**
     * Atributo String que guarda la ruta inicial de archivo
     */
	private String folder = "resources/";


	/**
     * Objeto del tipo Uploaded para manejo de archivos
     */
	private UploadedFile archivo;
	
	/**
     * Objeto del tipo Uploaded para utilizar al modificar los archivos
     */
	private UploadedFile archivoModifica;
	
	String rutaAbsoluta;
	 
	/**
     * Objeto del tipo Socket para utilizar en comunicacion con Python
     */
	Socket socket;
	
	/**
     * Atributo String que trabaja con el texto de archivo generado de Python
     */
	String saludo;
	
	   /**
	     * Atributo String que corta el texto de archivo recuperado de Python
	     */
	String texto;
	 /**
     * Atributo String para directorio de archivos
     */
	private String destino;
	
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////GETTERS Y SETTERS DE LOS ATRIBUTOS/////////////////////////
	
	/**
	 * Obtiene un Comentario Dao
	 * @return - devuelve un objeto del UsuarioDao
	 **/
	public ComentarioDao getDaoComentario() {
		return daoComentario;
	}

	/**
	 * Este metodo estable el objeto Comentario Dao
	 * @param daoComentario - objeto ComentarioDao a ingresar
	 **/
	public void setDaoComentario(ComentarioDao daoComentario) {
		this.daoComentario = daoComentario;
	}

	/**
	 * Obtiene un objeto Comentario
	 * @return - devuelve un objeto del UsuarioDao
	 **/
	public Comentario getNewComentario() {
		return newComentario;
	}

	/**
	 * Este metodo estable el objeto Comentario
	 * @param newComentario - objeto Comentario a ingresar
	 **/
	public void setNewComentario(Comentario newComentario) {
		this.newComentario = newComentario;
	}

	/**
	 * Obtiene un objeto de lista Comentario
	 * @return - devuelve un objeto de lista Comentario
	 **/
	public List<Comentario> getListComentario() {
		return listComentario;
	}

	
	/**
	 * Este metodo establece una lista de objeto Comentario
	 * @param listComentario - objeto lista Comentario a ingresar
	 **/
	public void setListComentario(List<Comentario> listComentario) {
		this.listComentario = listComentario;
	}




	/**
	 * Obtiene una Lectura Dao
	 * @return - devuelve un objeto de LecturaDao
	 **/
	public LecturaDao getDaoLectura() {
		return daoLectura;
	}

	/**
	 * Este metodo estable el objeto Lectura Dao
	 * @param daoLectura - objeto LecturaDao a ingresar
	 **/
	public void setDaoLectura(LecturaDao daoLectura) {
		this.daoLectura = daoLectura;
	}

	/**
	 * Obtiene un objeto Lectura
	 * @return - devuelve un objeto de Lectura
	 **/
	public Lectura getNewLectura() {
		return newLectura;
	}
	/**
	 * Este metodo estable el objeto Lectura
	 * @param newLectura - objeto Lectura a ingresar
	 **/
	public void setNewLectura(Lectura newLectura) {
		this.newLectura = newLectura;
	}
	/**
	 * Obtiene un objeto lista Lectura
	 * @return - devuelve un objeto de lisraLectura
	 **/
	public List<Lectura> getListLectura() {
		return listLectura;
	}
	/**
	 * Este metodo establece una lista de objeto Lectura
	 * @param listLectura - objeto lista Lectura a ingresar
	 **/
	public void setListLectura(List<Lectura> listLectura) {
		this.listLectura = listLectura;
	}

	
	/**
	 * Obtiene un objeto Usuario para usar en el tipo lector
	 * @return - devuelve un objeto de LecturaDao para tipo lector
	 **/
	public List<Usuario> getLtsUsuarioLector() {
		return ltsUsuarioLector;
	}
	/**
	 * Este metodo establece una lista de objeto Comentario para usar en el tipo lector
	 * @param ltsUsuarioLector - objeto lista Usuario a ingresar para tipo lector
	 **/
	public void setLtsUsuarioLector(List<Usuario> ltsUsuarioLector) {
		this.ltsUsuarioLector = ltsUsuarioLector;
	}

	
	
	
	
	/**
	 * Obtiene un objeto UploadedFile para manejo de Archivos a modificar
	 * @return - devuelve un objeto UploadedFile para manejo de Archivos a modificar
	 **/
	public UploadedFile getArchivoModifica() {
		return archivoModifica;
	}
	/**
	 * Este metodo establece el objeto UploadedFile para archivos a modificar
	 * @param archivoModifica - objeto UploadedFile a ingresar
	 **/
	public void setArchivoModifica(UploadedFile archivoModifica) {
		this.archivoModifica = archivoModifica;
	}

	
	/**
	 * Obtiene un String texto para cortar palabra keywords
	 * @return - devuelve un String para cortar palabra keywords
	 **/
	public String getTexto() {
		return texto;
	}
	/**
	 * Este metodo establece el String texto para cortar palabra keywords
	 * @param texto - String texto a ingresar
	 **/
	public void setTexto(String texto) {
		this.texto = texto;
	}
	/**
	 * Obtiene un Socket socket  para comunicarse con Python
	 * @return - devuelve un objeto Socket para comunicarse con Python
	 **/
	public Socket getSocket() {
		return socket;
	}
	/**
	 * Este metodo establece el objeto Socket para comunicarse con Python
	 * @param socket - Socket ingraso para comunicarse con Python
	 **/
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	/**
	 * Obtiene un String saludo para guardar texto obtenido de Python
	 * @return - devuelve un String para guardar texto obtenido de Python
	 **/
	public String getSaludo() {
		return saludo;
	}
	/**
	 * Este metodo establece el String saludo para guardar texto obtenido de Python
	 * @param saludo - String saludo a ingresar para guardar texto obtenido de Python
	 **/
	public void setSaludo(String saludo) {
		this.saludo = saludo;
	}
	/**
	 * Obtiene un String de ruta completa
	 * @return - devuelve un String de ruta completa
	 **/
	public String getRutaAbsoluta() {
		return rutaAbsoluta;
	}
	/**
	 * Este metodo establece el String rutaAbsoluta para directoria de archivos
	 * @param rutaAbsoluta - String rutaAbsoluta a ingresar para directorio de archivos
	 **/
	public void setRutaAbsoluta(String rutaAbsoluta) {
		this.rutaAbsoluta = rutaAbsoluta;
	}
	/**
	 * Obtiene un Objeto UploadedFile para manejo de archivos
	 * @return - devuelve un objeto Uploaded para manejo de archivos
	 **/
	public UploadedFile getArchivo() {
		return archivo;
	}
	/**
	 * Este metodo establece el objeto UploadedFile para manejo de archivos
	 * @param archivo - Uploaded a ingresar para manejo de archivos
	 **/
	public void setArchivo(UploadedFile archivo) {
		this.archivo = archivo;
	}
	/**
	 * Obtiene un String destino para ruta de archivo
	 * @return - devuelve un String para ruta de Archivo
	 **/
	public String getDestino() {
		return destino;
	}
	/**
	 * Obtiene un String de ruta a utilizar en archivos
	 * @return - devuelve un String de ruta de archivos
	 **/
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	/**
	 * Obtiene un Objeto Part para manejo de archivos
	 * @return - devuelve un objeto Part para manejo de archivos
	 **/
	public Part getUploadedFile() {
		return uploadedFile;
	}
	/**
	 * Este metodo establece el objeto Part para manejo de archivos
	 * @param uploadedFile - Part a ingresar para manejo de archivos
	 **/
	public void setUploadedFile(Part uploadedFile) {
		this.uploadedFile = uploadedFile;
	}


	/**
	 * Obtiene un entero id para id de los objetos "Usuario,Referencia, Lectura, Comentarios"
	 * @return - devuelve un objeto id para los objetos "Usuario,Referencia, Lectura, Comentarios"
	 **/
	public int getId() {
		return id;
	}
	/**
	 * Este metodo establece el entero id para id de los objetos "Usuario,Referencia, Lectura, Comentarios"
	 * @param id - Entero a ingresar para id de los objetos "Usuario,Referencia, Lectura, Comentarios"
	 **/
	public void setId(int id) {
		this.id = id;
	}


	
	
	//***-
	/**
	 * Obtiene un objeto Referencia Dao
	 * @return - devuelve un objeto de Referencia Dao
	 **/
	public ReferenciaAPADao getDaoReferencia() {
		return daoReferencia;
	}
	/**
	 * Este metodo estable el objeto ReferenciaAPA Dao
	 * @param daoReferencia - objeto ReferenciaDao a ingresar
	 **/
	public void setDaoReferencia(ReferenciaAPADao daoReferencia) {
		this.daoReferencia = daoReferencia;
	}
	/**
	 * Obtiene un objeto Referencia
	 * @return - devuelve un objeto ReferenciaAPA
	 **/
	public ReferenciaAPA getNewReferencia() {
		return newReferencia;
	}
	/**
	 * Este metodo estable el objeto Referencia
	 * @param newReferencia - objeto Referencia a ingresar
	 **/
	public void setNewReferencia(ReferenciaAPA newReferencia) {
		this.newReferencia = newReferencia;
	}

	/**
	 * Obtiene un objeto lista ReferenciaAPA
	 * @return - devuelve un objeto de lista ReferenciaAPA
	 **/
	public List<ReferenciaAPA> getListReferencia() {
		return listReferencia;
	}
	/**
	 * Este metodo establece una lista de objeto Referencia
	 * @param listReferencia - objeto lista Referencia a ingresar
	 **/
	public void setListReferencia(List<ReferenciaAPA> listReferencia) {
		this.listReferencia = listReferencia;
	}
	/**
	 * Obtiene un objeto Usuario
	 * @return - devuelve un objeto de Usuario
	 **/
	public Usuario getNewUsuario() {
		return newUsuario;
	}
	/**
	 * Este metodo estable el objeto Usuario
	 * @param newUsuario - objeto Usuario a ingresar
	 **/
	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}
	
	/**
	 * Obtiene un objeto Usuario para tipo Bibliotecario
	 * @return - devuelve un objeto de Usuario para usar Bibliotecario
	 **/
	public Usuario getNewUsuarioB() {
		return newUsuarioB;
	}
	/**
	 * Este metodo estable el objeto Usuario para tipo Bibliotecario
	 * @param newUsuarioB - objeto Usuario tipo Bibliotecario a ingresar
	 **/
	public void setNewUsuarioB(Usuario newUsuarioB) {
		this.newUsuarioB = newUsuarioB;
	}
	/**
	 * Obtiene un objeto Usuario para tipo Lector
	 * @return - devuelve un objeto de Usuario para usar en Lector
	 **/
	public Usuario getNewUsuarioLector() {
		return newUsuarioLector;
	}
	/**
	 * Este metodo estable el objeto Usuario para tipo Lector
	 * @param newUsuarioLector - objeto Usuario tipo Lector a ingresar
	 **/
	public void setNewUsuarioLector(Usuario newUsuarioLector) {
		this.newUsuarioLector = newUsuarioLector;
	}

	
	/**
	 * Obtiene un objeto Usuario para usar comparativa en Logueo
	 * @return - devuelve un objeto de Usuario para usar comparativa en Logueo
	 **/
	public Usuario getUsuarioVerificar() {
		return usuarioVerificar;
	}
	/**
	 * Este metodo estable el objeto Usuario para tipo Bibliotecario
	 * @param newUsuarioVerificar - objeto Usuario tipo Bibliotecario a ingresar
	 **/
	public void setUsuarioVerificar(Usuario usuarioVerificar) {
		this.usuarioVerificar = usuarioVerificar;
	}

	/**
	 * Obtiene un objeto Usuario Dao
	 * @return - devuelve un objeto de UsuarioDao
	 **/
	public UsuarioDao getDao() {
		return dao;
	}
	/**
	 * Este metodo estable el objeto Usuario Dao
	 * @param dao - objeto UsuarioDao a ingresar
	 **/
	public void setDao(UsuarioDao dao) {
		this.dao = dao;
	}
	/**
	 * Obtiene un booleano para confirmar logueo
	 * @return - devuelve un boolean para logueo
	 **/
	public boolean isLogging() {
		return logging;
	}
	/**
	 * Este metodo establece el atributo logging
	 * @param logging -  booleano logging a ingresar
	 **/
	public void setLogging(boolean logging) {
		this.logging = logging;
	}
	/**
	 * Obtiene un booleano para confirmar logueo Administrador
	 * @return - devuelve un boolean para logueo Administrador
	 **/
	public boolean isAdministrador() {
		return administrador;
	}
	/**
	 * Este metodo establece el booleano administrador
	 * @param administrador - booleano administrador a ingresar
	 **/
	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	/**
	 * Obtiene un objeto lista Usuario
	 * @return - devuelve un objeto de lista Usuario
	 **/
	public List<Usuario> getLtsUsuario() {
		return ltsUsuario;
	}
	/**
	 * Este metodo establece una lista de objeto Usuario
	 * @param ltsUsuario - objeto lista Usuario a ingresar
	 **/
	public void setLtsUsuario(List<Usuario> ltsUsuario) {
		this.ltsUsuario = ltsUsuario;
	}

	/**
	 * Obtiene un objeto lista ReferenciaAPA ordenadas por Calificacion
	 * @return - devuelve un objeto de lista ReferenciaAPA ordenadas por Calificacion
	 **/
	public List<ReferenciaAPA> getListReferenciaOrdenadas() {
		return listReferenciaOrdenadas;
	}
	/**
	 * Este metodo establece una lista de objeto Referencia ordenadas por Calificacion
	 * @param listReferenciaOrdenadas - objeto lista Comentario a ingresar
	 **/
	public void setListReferenciaOrdenadas(List<ReferenciaAPA> listReferenciaOrdenadas) {
		this.listReferenciaOrdenadas = listReferenciaOrdenadas;
	}

	
	

	/**
	 * Este metodo que inicializa los valores objeto
	 * de "Referencia, Comentario, Lectura, Usuario"
	 * sus respectivos dao clases modelo y listas objeto
	 **/
@PostConstruct
	public void init(){
	
	    newReferencia=new ReferenciaAPA();
	    newComentario=new Comentario();
	    
	    newLectura=new Lectura();
	    
	  //  newComentario.setUsuario(newUsuario);
	    
	    newComentario.setReferencia(newReferencia);

	    
	    listComentarioA=daoComentario.getComentario();

	    listReferencia=daoReferencia.getReferencia();
	    
	    listLectura=daoLectura.getLecturaUsuario(newUsuarioLector.getIdUsuario());
	    listComentario=daoComentario.getComentario();
	    listReferenciaOrdenadas=daoReferencia.getReferenciaOrdenada();
	    
	    newUsuario=new Usuario();
	    newUsuarioB=new Usuario();
	    newUsuarioLector=new Usuario();
	    
	    //newUser=new Usuario();
	    
	    usuarioVerificar=new Usuario();
		ltsUsuario= dao.getUsuarios();
		ltsUsuarioLector= dao.getUsuariosLector();
		
}
/////////////////////////////////////////////////////////////////////////////////////
/////////////////////CONTROLADOR PARA OPERACIONES DE USUARIO/////////////////////////

/**
* Llama a DaoInsertar que 
* luego sera invocado por controlador
* en Vista para insertar un Usuario del tipo Bibliotecario
* @return - devuelve un String que me redirige a la lista de 
*  los usuarios del tipo Biblio
**/
public String insertarUsuarioBiblio() {
newUsuario.setUsrRol("biblio"); 
//newUsuario.setUsrTopic(false);
dao.save(newUsuario);
//init();
//logging = true;
//validarUsuario();
return "usuarioAdministradorListar";
}

/**
* Llama a DaoInsertar que 
* luego sera invocado por controlador
* en Vista para insertar un Usuario del tipo Lector
* @return - devuelve un String que me redirige a la lista de 
*  los usuarios del tipo Biblio
**/
public String insertarUsuarioLector() {
newUsuarioLector.setUsrRol("lector"); 
//newUsuario.setUsrTopic(false);
dao.save(newUsuarioLector);
//init();
//logging = true;
//validarUsuario();
return "lectorLogin";
}

/**
* Redirige a pagina LectorRegistro
* @return - devuelve un String con pagina LectorRegistro
**/
public String botonIngresarUsuarioLector() {

return "lectorRegistro";
}

/**
*Carga los Valores de Usuario
*llenando los campos con estos valores 
*de acuerdo al id Seleccionado
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
* Llama a metodo leer de Usuario 
* @param codigo -ingresamos el id "Entero" para buscar usuario
* @return - devuelve un objeto tipo Usuario de acuerdo al id de usuario
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
*Actualiza un usauario
*con los nuevos datos establecidos
* @return - devuelve un String que me redirige a la pagina
* actualizar Usuario con el registro ya actualizado
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


/////////////////////////////////////////////////////////////////////////////////////
/////////////////////CONTROLADOR PARA LOGGING DE USUARIOS/////////////////////////

/**
* Valida si usario tipo Lector existe
* y pregunta si es igual el correo y password para ingreso
* @return - devuelve un string de redireccion dependiendo
* si el usuario y password es corecto a inicio usuario o si no
* a error de registro "no existe"
**/
public String validarUsuarioLector(){
String destino="";
System.out.println("////////"+usuarioVerificar.getUsrCorreo());
newUsuarioLector=dao.buscarCorreo(usuarioVerificar);
String var=newUsuarioLector+" ";

System.out.println("Valor de USUARIO "+" "+var.substring(19,20));
int valor=Integer.parseInt(var.substring(19,20));
//System.out.println("Si existe  y se llama: "+ newUsuario.getUsrNombre());
if(valor>0){

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


//System.err.println(newUsuario.getUsuarioTwitter());


//init();	

return destino;
}


/**
* Valida si usario tipo Admin existe
* y pregunta si es igual el correo y password para ingreso
* @return - devuelve un string de redireccion dependiendo
* si el usuario y password es corecto a inicio usuario o si no
* a error de registro "no existe"
**/
public String validarAdmin() throws IOException{

FacesMessage message2 = null;


String destino="";
System.out.println("////////"+usuarioVerificar.getUsrCorreo());
newUsuario=dao.buscarCorreoAdmin(usuarioVerificar);
String var=newUsuario+" ";

System.out.println("Valor Completo "+" "+var);
System.out.println("Valor de USUARIO"+" "+var.substring(19,20));
int valor=Integer.parseInt(var.substring(19,20));



newUsuarioB=dao.buscarCorreoBiblio(usuarioVerificar);
String varB=newUsuarioB+" ";

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


//System.err.println(newUsuario.getUsuarioTwitter());


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

/**
* Permite ver si sesion  de Lector esta abierta
* o si no redirige a pagina verificar sesion
**/
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

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////CONTROLADOR PARA MANEJO DE ARCHIVOS PARA SERVIDOR/////////////////////////


/**
* Metodo que nos permite subir un archivo
* en el servidor Wildfly dentro de su directorio
* y consumirlo para nuestras nesesidades
*   @return - devuelve un boolean si 
*   operacion de subida de archivo hacia el servidor
*   es correcta
**/
public Boolean uploadPdf() 
	{
		try{
		destino=Constantes.url;
		System.out.println("Ver Formato Libro  "+getArchivo()+" "+getArchivo());
		File folder=new File(FaceUtils.getPath()+destino);
		System.out.println("Destino con faceUtils "+FaceUtils.getPath()+destino);
		//System.out.println("Ver Formato Libro  "+getArchivo().getFileName()+" "+getArchivo().getInputstream());
		if(!folder.exists()) {
			folder.mkdirs();
		}
		//System.out.println("Ver Formato Libro  "+getArchivo().getFileName()+" "+getArchivo().getInputstream());
		copyFile(getArchivo().getFileName(),getArchivo().getInputstream());
		return true;
		}catch(IOException e) 
		{
			return false;
		}
	}
	
/**
* Metodo que lee el Archivo
* @param filename -ingresamos nombre de archivo String
* @param in -flujo de datos input para leer el archivo
**/
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
	
	
	/**
	* Metodo que trabaja con getFileName
	*  para cargarlo en la vista p:upload File
	* @param event -objeto del tipo event para escuchar
	* manejo de archivos en vista
	**/
	
	@RequestScoped
	public void handleFileUpload(FileUploadEvent event) {  
	FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
	FacesContext.getCurrentInstance().addMessage(null, msg);  
	} 
	
	
	/**
	* Metodo que obtiene la direccion del archivo
	* para cargarlo en la vista p:upload File
	* @param part -objeto Part para manejo de archivos
	*  p:upload File de vista
	*   @return - devuelve un string de el archivo 
	*  cargado en la vista
	**/
	private static String getFilename(Part part) {
	   for (String cd : part.getHeader("content-disposition").split(";")) {
	       if (cd.trim().startsWith("filename")) {
	           String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	           return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	       }
	   }
	   return null;
	}




/////////////////////////////////////////////////////////////////////////////////////
/////////////////////CONTROLADOR PARA CONEXION Y EJECUCION  DE PYTHON/////////////////////////
/**
*Conecta con el servicio de Python
*para ejecutar comandos que ejecuta Python para obtener
*un archivo con la lista de las palabras keywords con nltk
* @return - devuelve un boolean cuando se aya ejecutado todas
* las instrucciones de python
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
*Conecta con el servicio de Python mediante socket
*que nos servira despues para ejecutar script python
* @return - devuelve un boolean en caso de que coneccion 
* con socket sea exitosa
**/
public  boolean mensaFromServidor()
{
	
	/*for (int i = 1; i <= 1500000; i++){ //borde de arriba
        System.out.print("#"); 
   }*/
	
	
	
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
        //System.out.println("Ver Formato Libro  "+getArchivo()+" "+getArchivo());
        System.out.println("Conectado ");
      /*  System.out.println("Ver Formato Libro  "+getArchivo()+" "+getArchivo());
        System.out.println("ARCHIVO XXXXXXXXXXXXXXX  ----- "+saludo);*/
        return true;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	 
          
}


/**
*Metodo que nos permite leer el archivo de lista palabras
*y cortarlas para que se guarden en un string
* @return - devuelve un String de todas las palabras clave
* estas palabras se guardar una tras otra con ","
* -->"Palabra1, Palabra2, Palabra3"
**/
public String cortaPalabras() 
{
	for (int i = 1; i <= 4000000; i++){ //borde de arriba
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
    		if(!part1.equals("la")) {
    			if(!part1.equals("el")) {
    				if(!part1.equals("en")) {
    					if(!part1.equals("a")) {
    						if(!part1.equals("â€œ")) {
    							if(!part1.equals("â€�")) {
    								if(!part1.equals("se")) {
    									if(!part1.equals("para")) {
    										if(!part1.equals("â€“")) {
    											if(!part1.equals("los")) {
    												if(!part1.equals("â€¢")) {
    													if(!part1.equals("cada")) {
    														if(part1.length()>1) {
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

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////CONTROLADOR PARA OPERACIONES DE REFERENCIA/////////////////////////
/**
* Llama a metodo save del Dao de Referencia
* que luego sera invocado por controlador
* en Vista para insertar una Referencia ahi que tener en cuenta
* que incluira el proceso de carga de archivos pdf
* @return - devuelve un String que me redirige a la pagina
* donde mostraremos todas las referencias insertadas
**/
public String insertarReferencia() throws IOException, InterruptedException{
System.out.println("Ver Formato Libro  "+getArchivo()+" "+getArchivo());




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





daoReferencia.save(newReferencia);
}

init();


System.out.println("Ver Formato Libro  "+getArchivo().getFileName()+" "+getArchivo().getInputstream());

return "referenciaApaListar";

}


/**
* Llama a metodo save del Dao de Referencia
* que luego sera invocado por controlador
* en Vista para insertar una Referencia ahi que tener en cuenta
* que incluira el proceso de carga de archivos pdf
* @return - devuelve un String que me redirige a la pagina
* donde mostraremos todas las referencias insertadas
* esto para el usuario Bibliotecario
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





//init();
System.out.println("ARCHIVO XXXXXXXXXXXXXXX  ----- "+saludo);
System.out.println("EL TEXTO XXXXXXXXXXXXXXX  ----- "+texto);
//return "";
return "referenciaBApaListar";

}


/**
*Actualiza una Referencia
*con los nuevos datos establecidos
* @return - devuelve un String que me redirige a la pagina
*actualizar referencia con el registro ya actualizado
**/
public String actualizarReferencia(int id){
ReferenciaAPA pelicula =daoReferencia.leer(id);
newReferencia=pelicula;
return "referenciaApaRegistro";
}
/**
* Muestra pagina de la Referencia seleccionada con sus datos
* @param codigo -id de la Referencia a ser visualizado
* @return - devuelve un String de la pagina referencia segun id seleccionado
**/
public String editarReferencia(int codigo) {
	
	
	return "referencia?faces-redirect=true&id="+codigo;
}

/**
* Muestra pagina detalle Referenica de acuerdo al id seleccionado
* @param codigo -id de la referencia para ser accedido a detalle segun
* referencia
* @return - devuelve un String de la pagina detalle segun id de Referencia
**/
public String mostrarArticulo(int codigo) {
	
	
	return "lectorLibroDetalle?faces-redirect=true&id="+codigo;
}
/**
*Carga los Valores de Referencia
*llenando los campos con estos valores 
*de acuerdo al id Seleccionado
**/
public void loadDatosReferencia() {
	//System.out.println("codigo editar " + id);
	if(id==0)
		return;
	
	newReferencia = getReferencia(id);
	//System.out.println(newUsuario.getIdUsuario() + " " + newUsuario.getNombre() );
	//System.out.println("#telefonos: " + " " + persona.getTelefonos().size());
	
}

/**
* Llama a metodo leer de Referencia
* @param codigo -ingresamos el id "Entero" para buscar referencia
* @return - devuelve un objeto tipo Referencia de acuerdo al id de referencia
**/
public ReferenciaAPA getReferencia(int codigo) {
	ReferenciaAPA ref = daoReferencia.leer(codigo);
	
	return ref;
	
}

/**
 * Llama a DaoCargar que 
 * luego sera invocado por controlador
 * en Vista Caegar datos Referencia
 * @throws IOException 
 **/
public String cargarDatosReferencia() throws IOException {
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
* Llama a metodo actualizar del Dao Referencia
* en donde se actualizara el campo calificacion
* obteniendo un promedio del total de calificaciones
* en Vista para eliminar unua Lectura
* @return - devuelve un String que me redirige a la misma pagina detalle
* referencia pero ya con el valor de calificacion modificado
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
* Llama a metodo borrar del Dao Referencia que  
* luego sera invocado por controlador
* en Vista para borrar unua Lectura
* @return - devuelve un String que me redirige a la misma pagina referencia
* eliminar pero ya con  la referencia eliminada de la lista
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

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////CONTROLADOR PARA OPERACIONES DE COMENTARIOS/////////////////////////

/**
* Llama a metodo save del Dao Comentario que  
* luego sera invocado por controlador
* en Vista para insertar un Comentario 
* @return - devuelve un String que me redirige a la referencia
* detalle con todos los comentarios
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
* Muestra pagina del Comentario seleccionado con sus datos
* @param codigo -id del comentario a ser visualizado
* @return - devuelve un String de la pagina comentario segun id seleccionado
**/
public String editarComentario(Integer codigo) {
	
	
	return "comentario?faces-redirect=true&id="+codigo;
}

/**
* Llama a metodo leer Comentario 
* @param codigo -ingresamos el id "Entero" para busca comentario
* @return - devuelve un objeto tipo Comentario de acuerdo al id de comentario
**/
public Comentario getComentario(int codigo) {
	Comentario ref = daoComentario.leer(codigo);
	
	return ref;
	
}
/**
*Carga los Valores de Comentario
*llenando los campos con estos valores 
*de acuerdo al id Seleccionado
**/
public void loadDatosComentario() {
	//System.out.println("codigo editar " + id);
	if(id==0)
		return;
	
	newComentario = getComentario(id);
	//System.out.println(newUsuario.getIdUsuario() + " " + newUsuario.getNombre() );
	//System.out.println("#telefonos: " + " " + persona.getTelefonos().size());
	
}

/**
*Actualiza un Comentario
*con los nuevos datos establecidos
* @return - devuelve un String que me redirige a la pagina
*detalle lectura con el registro ya actualizado
**/
public String cargarDatosComentario() throws IOException {
	//System.out.println("Ver Formato Libro  "+getArchivo()+" "+getArchivo());
	try {
		daoComentario.save(newComentario);
		init();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
init();
	
	
	
	
	return "indexLector";
}

/**
* Llama a metodo borrar del Dao Comentario que  
* luego sera invocado por controlador
* en Vista para eliminar un Comentario
* @return - devuelve un String que me redirige a la misma pagina detalle
* referencia pero con comentario ya borrado
**/
public String eliminarComentario(int id){
	//FaceUtils.getPath();
	System.out.println("Estamos aqui XXXXXXXXXXXXXPUXXXX");
	daoComentario.borrar(id);
	
init();
return "lectorLibroDetalle?faces-redirect=true&id="+id;
}

/**
* Metodo que llama al daoComentario para obtener
* una lista de Comentarios de acuerdo a Referencia
* @param id -entero id para ver lista de comentarios de acuerdo a 
* referencia
*   @return - devuelve un objeto lista comentarios de acuerdo
*   al id de referencia
**/
public List<Comentario> getComentarioArticulo(int id){
	
	List<Comentario> comentario = listComentario=daoComentario.getComentarioArticulo(id);
	return comentario;
}

/////////////////////////////////////////////////////////////////////////////////////
/////////////////////CONTROLADOR PARA OPERACIONES DE LECTURA/////////////////////////

/**
* Llama a metodo save del Dao Lectura que  
* luego sera invocado por controlador
* en Vista para insertar unua Lectura
* @return - devuelve un String que me redirige a la misma pagina detalle
* referencia pero con lectura ya agregada
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
* Llama a metodo borrar del Dao Lectura que  
* luego sera invocado por controlador
* en Vista para eliminar unua Lectura
* @return - devuelve un String que me redirige a la misma pagina lecturas
* pero ya con referencia eliminada de lectura
**/
public String eliminarLectura(int id){
	//FaceUtils.getPath();
	
	daoLectura.borrar(id);
	
init();
return null;
}


/**
* Metodo que llama al daoLectura para obtener
* una lista de lecturas de acuerdo a Usuario
* @param id -entero id para ver lista de lecturas de acuerdo a 
* usuario
*   @return - devuelve un objeto lista Lecturas de acuerdo
*   al id de usuario
**/
public List<Lectura> getLecturaUsuario(int id){
	
	List<Lectura> lectura = listLectura=daoLectura.getLecturaUsuario(id);
	return lectura;
}






}
