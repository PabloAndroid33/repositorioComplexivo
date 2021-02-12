package utilidades;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.ws.WebServiceClient;

//import modelo.Categoria;
//import modelo.Respuesta;
import modelo.Usuario;
//import negocio.CategoriaDao;
//import negocio.DirectorDao;
import negocio.UsuarioDao;


//Path donde se ejecutar los servicio web
@WebService
public class CategoriaSoap {

//Declaracion de atributos de la clases utilizando  objeto para: CategoriaDao, UsuarioDao
//y Categoria	
	
/*	@Inject
	private CategoriaDao dao;
	
	@Inject
	private UsuarioDao daoU;
	
	Categoria newCategoria;*/

	
//Getters y Setters de los atributos de la clase
/*	public CategoriaDao getDao() {
		return dao;
	}

	public void setDao(CategoriaDao dao) {
		this.dao = dao;
	}


	
	public Categoria getNewCategoria() {
		return newCategoria;
	}

	public void setNewCategoria(Categoria newCategoria) {
		this.newCategoria = newCategoria;
	}

	private List<Categoria> listCategoria;
	
	public UsuarioDao getDaoU() {
		return daoU;
	}

	public void setDaoU(UsuarioDao daoU) {
		this.daoU = daoU;
	}

	@PostConstruct
	public void init(){
		
	    
	    newCategoria=new Categoria();
	 }
	*/
/*Metodo web Service que me listara todas las categorias existentes
 donde sera de tipo get y se llamara listacategoria ademas que se visualizara
 en formato de salida application/json
 */	
	
	/*
	@WebMethod
   public List<Categoria> listarCategoria()
   {
		List<Categoria> categorias=dao.getCategoria();
		return categorias;
   }
	*/

/*Metodo web Service que me guardara un nuevoUsuario
donde sera de tipo gpost y se llamara guardarusuario ademas que se visualizara
en formato de salida application/json
*/	
/*	@WebMethod
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

	
//Metodo de prueba registra nueva categoria	
	@WebMethod
   public Respuesta guardarCategoria(Categoria cat)
   {
		
		Respuesta res=new Respuesta();
		try {
		
		dao.save(cat);
		res.setCodigo(1);
		res.setMensaje("Registro Satisfactorio");
		
		}catch(Exception e)
		{
			res.setCodigo(-1);
			res.setMensaje("Error en Registro");
		}
		return res;
   }
	
	
	
//Metodo Prueba busca categoria de acuerdo a id
	@WebMethod
   public Categoria getCategoriaId(@QueryParam("id") int id)
   {
		
		Categoria categoria=dao.leer(id);
		System.out.println(categoria);
		
		return categoria;
   }

	//Metodo Prueba obtiene categoria no desde el dao	
		@GET
		@Path("/categoria")
		@Produces("application/json")
	   public Categoria getCategoria(@QueryParam("id") int id)
	   {
			Categoria categoria=new Categoria();
			categoria.setId(id); 
			categoria.setNombre("Terror") ;
			categoria.setDescripcion("Da miedo");
			return categoria;
	   }
*/

	
	
	
}
