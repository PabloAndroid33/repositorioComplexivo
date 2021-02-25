package utilidades;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import modelo.Comentario;
import modelo.Lectura;

//import modelo.CarritoDetalle;

//import modelo.Categoria;
import modelo.ReferenciaAPA;
import modelo.Usuario;
import negocio.ComentarioDao;
import negocio.LecturaDao;
//import negocio.DirectorDao;
import negocio.ReferenciaAPADao;
import negocio.UsuarioDao;



/** 
 * Esta clase define los Servicios Web Rest
 *para que luego puedan ser accedidos desde
 *aplicacion movil
 
 */
@Path("/servicio")
public class ServiciosRest {
	
	
	/**
     * Objeto del UsuarioDao a ser inyectada
     * en la clase ServicioRest
     */
	@Inject
	private UsuarioDao daoU;
	
	/**
     * Objeto de ReferenciaAPADao a ser inyectada
     * en la clase ServicioRest
     */
	@Inject
	private ReferenciaAPADao daoR;
	
	
	/**
     * Objeto de LecturaDao a ser inyectada
     * en la clase ServicioRest
     */
	@Inject
	private LecturaDao daoL;
	
	
	/**
     * Objeto de ComentarioDao a ser inyectada
     * en la clase ServicioRest
     */
	@Inject
	private ComentarioDao daoC;
	
	
	
	/**
	 * Obtiene un Usuario Dao
	 * @return - devuelve un objeto del UsuarioDao
	 **/
	public UsuarioDao getDaoU() {
		return daoU;
	}

	/**
	 * Este metodo estable el objeto Usuario Dao
	 * @param daoU - objeto UsuarioDao a ingresar
	 **/
	public void setDaoU(UsuarioDao daoU) {
		this.daoU = daoU;
	}
	
	
	
	/**
	 * Obtiene una RefenciaAPA Dao
	 * @return - devuelve un objeto del ReferenciaDao
	 **/
	public ReferenciaAPADao getDaoR() {
		return daoR;
	}

	/**
	 * Este metodo estable el objeto Referencia Dao
	 * @param daoU - objeto ReferenciaAPADao a ingresar
	 **/
	public void setDaoR(ReferenciaAPADao daoR) {
		this.daoR = daoR;
	}

	/**
	 * Obtiene una Lectura Dao
	 * @return - devuelve un objeto de las LecturasDao
	 **/
	public LecturaDao getDaoL() {
		return daoL;
	}

	
	/**
	 * Este metodo estable el objeto Usuario Dao
	 * @param daoU - objeto LecturaDao a ingresar
	 **/
	public void setDaoL(LecturaDao daoL) {
		this.daoL = daoL;
	}

	
	
	
	/**
	 * Obtiene un Comentario Dao
	 * @return - devuelve un objeto del UsuarioDao
	 **/
	public ComentarioDao getDaoC() {
		return daoC;
	}

	/**
	 * Este metodo estable el objeto Comentario Dao
	 * @param daoU - objeto ComentarioDao a ingresar
	 **/
	public void setDaoC(ComentarioDao daoC) {
		this.daoC = daoC;
	}

//Usuarios	
	
	
/**
 * Declara un web Service que me lista todos
 * los usuarios tipo-"Lector"
* @param filtro -Para filtrar Usuarios
* @return - devuelve la lista de Usuarios
**/
	@GET
	@Path("listado")
	@Produces("application/json")
	public List<Usuario> listar(@QueryParam("filtro") String filtro){
		//return dao.getPersonasPorNombre(filtro);
		
		return daoU.getUsuariosLector();
		
		//return
	}
	
	/**
	 * Declara un web Service que me lista 
	 * un usuario especifico de acuerdo a id
	* @param filtro -ingresamos el id "Entero" a buscar
	* @return - devuelve un objeto Usuario 
	**/
	
	@GET
	@Path("leerusuario")
	@Produces("application/json")
	public Usuario obtenerUsuario(@QueryParam("filtro") int filtro){
		//return dao.getPersonasPorNombre(filtro);
		
		return daoU.leer(filtro);
		
		//return
	}
	
	

	
	

	
	
	
	
	
	
	/**
	 * Declara un web Service que me Guarda
	 * un Usuario
	* @param usu -ingresamos un objeto de tipo Usuario
	* para que este sea guardado
	* @return - devuelve un objeto tipo resultado "Si" 
	* en caso de insercion de usuario concretada 
	**/
	@POST
	@Path("/guardarusuario")
	@Produces("application/json")
	@Consumes("application/json")
   public Respuesta guardarUsuario(Usuario usu)
   {
		Respuesta res=new Respuesta();
		try {
		daoU.insertarUsuario(usu);
		res.setCodigo(1);
		res.setMensaje("Registro de Usuario a sido Satisfactorio");
		
		}catch(Exception e)
		{
			res.setCodigo(-1);
			res.setMensaje("Error en Registro de Usuario");
		}
		return res;
   }
	
	
	
	/**
	 * Declara un web Service que me actualiza
	 * un Usuario
	* @param usu -ingresamos un objeto de tipo Usuario
	* para que este sea actualizado
	* @return - devuelve un objeto tipo resultado "Si" 
	* en caso de actualizacion de usuario concretada 
	**/
	@POST
	@Path("/actualizausuario")
	@Produces("application/json")
	@Consumes("application/json")
   public Respuesta actualizaUsuario(Usuario usu)
   {
		Respuesta res=new Respuesta();
		try {
			//usu.act
		daoU.actualizarUsuario(usu);
		res.setCodigo(1);
		res.setMensaje("Registro de Usuario a sido Satisfactorio");
		
		}catch(Exception e)
		{
			res.setCodigo(-1);
			res.setMensaje("Error en Registro de Usuario");
		}
		return res;
   }
	
////////////////////////////////////////////////////////////////////////	
	

	
	/**
	 * Declara un web Service que me lista 
	 * referencias ordenadas por calificacion mayor
	* @return - devuelve un objeto Usuario 
	**/	

	@GET
	@Path("/listadocumentopuntuacion")
	@Produces("application/json")
   public List<ReferenciaAPA> listarReferenciasOrdenadas()
   {
		List<ReferenciaAPA> referencia=daoR.getReferenciaOrdenada();
		return referencia;
   }
	
	
	
//Si utilizado para las Busquedas y webService
	
	/**
	 * Declara un web Service que me lista 
	 * las keywords de cada una de las Referencia
	* @return - devuelve una lista de referencias
	* con sus respectivos keywords
	**/
	@GET
	@Path("/listakeywords")
	@Produces("application/json")
   public List<ReferenciaAPA> listarReferenciasKeywords()
   {
		List<ReferenciaAPA> referencia=daoR.getReferenciaKeywords();
		return referencia;
   }
	
/////////////////////////////////////////////////////////////
	

//Lecturas
	
	
	/**
	 * Declara un web Service que me lista todos
	 * los lecturas agregadas por un usuario especifico
	* @param filtro - id de usuario para listar sus lecturas
	* @return - devuelve la lista de lecturas de un usuario especifico
	**/
	@GET
	@Path("/listalecturas")
	@Produces("application/json")
   public List<Lectura> listarLecturas(@QueryParam("filtro") int filtro)
   {
		List<Lectura> referencia=daoL.getLecturaUsuarioRef(filtro);
		return referencia;
   }

	
	
	/**
	 * Declara un web Service que me lista todos
	 * los comentarios de una referencia especifica
	* @param filtro - id de referencia para listar sus comentarios
	* @return - devuelve la lista de comentarios de referencia especifica
	**/
	@GET
	@Path("/listacomentarios")
	@Produces("application/json")
   public List<Comentario> listarComentarios(@QueryParam("filtro") int filtro)
   {
		List<Comentario> comentario=daoC.getComentarioArticulo(filtro);
		return comentario;
   }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
