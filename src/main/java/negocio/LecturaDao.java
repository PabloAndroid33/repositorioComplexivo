package negocio;

import java.util.List;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

//import org.hibernate.validator.internal.util.logging.Log.logger;

import java.util.Optional;



//import modelo.Categoria;
import modelo.Comentario;
//import modelo.Pelicula;
import modelo.Lectura;

//import ec.edu.ups.ticketbus.modelo.Ruta;

/** 
 * Esta clase permite operar con la base
 * de datos de la tabla Lectura
 *@autor Pablo Siguenza
 
 */
@Stateless
@Startup
public class LecturaDao {

	/**
     * Objeto del tipo Entity manager para operaciones de "Ingreso, Lectura, Actualizacion y borrado"
     */
	@Inject
	private EntityManager em;

	

	/**
	 * Este metodo guarda una Lectura en caso que exista actualiza Lectura
	 * @param lectura - objeto Lectura para ser guardado o actualizado
	 **/
	public void save(Lectura lectura){
		if(em.find(Comentario.class, lectura.getId())==null)
		//	logger.info("Entrando en la aplicacion "+pelicula.getCategoria().getId());
			insertar(lectura);
		else
			actualizar(lectura);
	}
	
	/**
	 * Este metodo inserta una Lectura mediante JPA
	 * @param lectura - objeto Lectura para ser guardado
	 **/
public void insertar (Lectura lectura){
		
		// Log.e(TAG, "Mensaje de error");
		//em.persist(pelicula.set);
		//em.persist(c);
		//pelicula.getCategoria().setId(1);
		em.persist(lectura);
		
		//System.out.println("CHUCHA CHUCHA CHUAC "+pelicula.getNombre()+" Sinopsis "+pelicula.getSinopsis()+" Foranea "+pelicula.getCategoria());
	}
/**
 * Este metodo actualiza una Lectura mediante JPA
 * @param lectura - objeto Lectura para ser actualizado
 **/
	public void actualizar (Lectura lectura){
		em.merge(lectura);
	}
	/**
	 * Este metodo borra una Lectura mediante JPA
	 * @param id - entero id utilizado para borrar Lectura
	 **/
	public void borrar (int id){
		Lectura lectura= em.find(Lectura.class, id);
		em.remove(lectura);
	}
	/**
	 * Este metodo lee una Lectura mediante JPA
	 * @param id - entero id utilizado para leer Lectura
	 **/
	public Lectura leer (int id){
		Lectura lectura = em.find(Lectura.class, id);
		return lectura;
	}
	/**
	 * Este metodo lista todas las Lecturas mediante JPA
	 * @return - devuelve una lista de todas las Lecturas
	 **/
	public List<Lectura> getLectura(){
		String sql = "SELECT l FROM Lectura l ";
		Query q = em.createQuery(sql);
		List<Lectura> lectura = q.getResultList();
		return lectura;
	}
	/**
	 * Este metodo lista todas las Lecturas de acuerdo a Usuario mediante JPA
	 * @param id - entero id utilizado para leer Lecturas de acuerdo a id de usuario
	 * @return - devuelve una lista de todas las lecturas de acuerdo a id de usuario
	 **/
	public List<Lectura> getLecturaUsuario(int id){
		String sql = "SELECT l FROM Lectura l where usuario="+id;
		System.out.println("ESTA ES LA CONSULTAXXXXXXXXXXXXX ....." + sql);
		Query q = em.createQuery(sql);
		List<Lectura> lectura = q.getResultList();
		return lectura;
	}
	/**
	 * Este metodo lista todas las Lecturas de acuerdo a Usuario mediante JPA
	 * con un cruce de tablas se utilizara para web servers
	 * @param id - entero id utilizado para leer Lecturas de acuerdo a id de usuario
	 * @return - devuelve una lista de todas las lecturas de acuerdo a id de usuario
	 **/
	public List<Lectura> getLecturaUsuarioRef(int id){
		String sql = "SELECT r FROM Lectura l, ReferenciaAPA r where l.referencia=r.id and l.usuario="+id;
		System.out.println("ESTA ES LA CONSULTAXXXXXXXXXXXXX ....." + sql);
		Query q = em.createQuery(sql);
		List<Lectura> lectura = q.getResultList();
		return lectura;
	}
	
	
	
	
	
}