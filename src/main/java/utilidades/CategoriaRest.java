package utilidades;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import modelo.Calificacion;

//import modelo.CarritoDetalle;

//import modelo.Categoria;
import modelo.ReferenciaAPA;
import modelo.Usuario;




//import negocio.DirectorDao;
import negocio.ReferenciaAPADao;
import negocio.UsuarioDao;


//Path donde se ejecutar los servicio web
@Path("/servicio")
public class CategoriaRest {

//Declaracion de atributos de la clases utilizando  objeto para: CategoriaDao, UsuarioDao
//y Categoria	
	
/*	@Inject
	private CategoriaDao dao;
	
	
	@Inject
	private CalificacionDao caldao;
	
	@Inject
	private CarritoComprasDao carrDao;
	
	
	@Inject
	private CarroDao carroDao;
	
	
	@Inject
	private CarroDetalleDao carroDDao;
	
	/*@Inject
	private CarritoDetalleDao carrDeta;
	
	public CarritoDetalleDao getCarrDeta() {
		return carrDeta;
	}

	public void setCarrDeta(CarritoDetalleDao carrDeta) {
		this.carrDeta = carrDeta;
	}*/

/*	public CarroDetalleDao getCarroDDao() {
		return carroDDao;
	}

	public void setCarroDDao(CarroDetalleDao carroDDao) {
		this.carroDDao = carroDDao;
	}

	public CarritoComprasDao getCarrDao() {
		return carrDao;
	}

	public void setCarrDao(CarritoComprasDao carrDao) {
		this.carrDao = carrDao;
	}

	public CalificacionDao getCaldao() {
		return caldao;
	}

	public void setCaldao(CalificacionDao caldao) {
		this.caldao = caldao;
	}

	@Inject
	private PeliculaDao pdao;
	
	
	@Inject
	private UsuarioDao daoU;
	
	Categoria newCategoria;

	
//Getters y Setters de los atributos de la clase
	public CategoriaDao getDao() {
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

	
	/*Metodo web Service que me listara todas las categorias existentes
	 donde sera de tipo get y se llamara listacategoria ademas que se visualizara
	 en formato de salida application/json
	 */	
/*		@GET
		@Path("/listapelicula")
		@Produces("application/json")
	   public List<Pelicula> listarPeliculas()
	   {
			List<Pelicula> pelicula=pdao.getPelicula();
			return pelicula;
	   }
	
		@GET
		@Path("/listapeliculaano")
		@Produces("application/json")
	   public List<Pelicula> listarPeliculasAno()
	   {
			List<Pelicula> pelicula=pdao.getPeliculaPorAno();
			return pelicula;
	   }
	
	
	
/*Metodo web Service que me listara todas las categorias existentes
 donde sera de tipo get y se llamara listacategoria ademas que se visualizara
 en formato de salida application/json
 */	
	/*@GET
	@Path("/listacategoria")
	@Produces("application/json")
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
/*	@POST
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
	
	
	@POST
	@Path("/calificacion")
	@Produces("application/json")
	@Consumes("application/json")
   public Respuesta guardarCalificacion(Calificacion cal)
   {
		Respuesta res=new Respuesta();
		try {
		caldao.insertarcalificacion(cal);
		res.setCodigo(1);
		res.setMensaje("Registro de Calificacion a sido Satisfactorio");
		
		}catch(Exception e)
		{
			res.setCodigo(-1);
			res.setMensaje("Error en Registro de Usuario");
		}
		return res;
   }
	
	
	@POST
	@Path("/guardarcarrito")
	@Produces("application/json")
	@Consumes("application/json")
   public Respuesta guardarCarrito(CarritoCompras car)
   {
		Respuesta res=new Respuesta();
		try {
		carrDao.insertarcarrito(car);
		res.setCodigo(1);
		res.setMensaje("Registro de Carrito de Compras a sido Satisfactorio");
		
		}catch(Exception e)
		{
			res.setCodigo(-1);
			res.setMensaje("Error en Registro de Usuario");
		}
		return res;
   }
	
	@POST
	@Path("/detalle")
	@Produces("application/json")
	@Consumes("application/json")
   public Respuesta guardardetalle(CarroDetalle car)
   {
		Respuesta res=new Respuesta();
		try {
			carroDDao.insertarcarroDetalle(car);
		res.setCodigo(1);
		res.setMensaje("Registro de Carrito de Compras a sido Satisfactorio");
		
		}catch(Exception e)
		{
			res.setCodigo(-1);
			res.setMensaje("Error en Registro de Usuario");
		}
		return res;
   }
	
	@POST
	@Path("/carro")
	@Produces("application/json")
	@Consumes("application/json")
   public Respuesta guardarCarro(Carro car)
   {
		Respuesta res=new Respuesta();
		try {
		carroDao.insertarcarro(car);
		res.setCodigo(1);
		res.setMensaje("Registro de Carrito de Compras a sido Satisfactorio");
		
		}catch(Exception e)
		{
			res.setCodigo(-1);
			res.setMensaje("Error en Registro de Usuario");
		}
		return res;
   }
	
	
	/*@POST
	@Path("/guardarcarritodetalle")
	@Produces("application/json")
	@Consumes("application/json")
   public Respuesta guardarCarritoDetalle(CarritoDetalle car)
   {
		Respuesta res=new Respuesta();
		try {
		carrDeta.insertarcarritoDetalle(car);
		res.setCodigo(1);
		res.setMensaje("Registro de Usuario a sido Satisfactorio");
		
		}catch(Exception e)
		{
			res.setCodigo(-1);
			res.setMensaje("Error en Registro de Usuario");
		}
		return res;
   }*/
	
	
	
	
	
	
	
	
	
	
/*	@POST
	@Path("/usuarioLogin")
	@Produces("application/json")
	@Consumes("application/json")
   public boolean loginUsuario(Usuario usuario)
   {
		boolean logging;
		//Respuesta res=new Respuesta();
			Usuario us=new Usuario();
		us=daoU.buscarUsuario(usuario);
		if(us!=null){
			System.out.println("el usuario "+us.getUsrCorreo()+" si existe");
			logging = true;
	//return logging;
			//destino="indexUsuario";
			
			//return "indexUsuario";
		}else {
			System.out.println("el usuario "+us.getUsrCorreo()+" no existe");
			logging=false;
			//return null;
		}
		
		return logging;
   }
	
	
	
	

	
//Metodo de prueba registra nueva categoria	
	@POST
	@Path("/guardarcategoria")
	@Produces("application/json")
	@Consumes("application/json")
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
	@GET
	@Path("/categoriaid")
	@Produces("application/json")
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


		@GET
		@Path("listado")
		@Produces("application/json")
		public List<Usuario> listar(@QueryParam("filtro") String filtro){
			//return dao.getPersonasPorNombre(filtro);
			
			return daoU.getUsuarios();
			
			//return
		}
	
	*/
}
