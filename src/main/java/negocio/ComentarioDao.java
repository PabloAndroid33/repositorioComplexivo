
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

//import ec.edu.ups.ticketbus.modelo.Ruta;


/** 
 * Esta clase permite operar con la base
 * de datos de la tabla Comentarios
 *@autor Pablo Siguenza
 
 */
@Stateless
@Startup
public class ComentarioDao {

	/**
     * Objeto del tipo Entity manager para operaciones de "Ingreso, Lectura, Actualizacion y borrado"
     */
	@Inject
	private EntityManager em;

	

	/**
	 * Este metodo guarda un Comentrario en caso que exista actualiza Comentario
	 * @param lectura - objeto Comentario para ser guardado o actualizado
	 **/
	public void save(Comentario comentario){
		if(em.find(Comentario.class, comentario.getId())==null)
		//	logger.info("Entrando en la aplicacion "+pelicula.getCategoria().getId());
			insertar(comentario);
		else
			actualizar(comentario);
	}
	
	
	/**
	 * Este metodo insertar un Comentario mediante JPA
	 * @param comentario - objeto Comentario para ser guardado
	 **/
public void insertar (Comentario comentario){
		
		// Log.e(TAG, "Mensaje de error");
		//em.persist(pelicula.set);
		//em.persist(c);
		//pelicula.getCategoria().setId(1);
		em.persist(comentario);
		
		//System.out.println("CHUCHA CHUCHA CHUAC "+pelicula.getNombre()+" Sinopsis "+pelicula.getSinopsis()+" Foranea "+pelicula.getCategoria());
	}
/**
 * Este metodo actualiza un Comentario mediante JPA
 * @param comentario - objeto Comentario para ser actualizado
 **/
	public void actualizar (Comentario comentario){
		em.merge(comentario);
	}
	/**
	 * Este metodo borra un Comentario mediante JPA
	 * @param id - entero id utilizado para borrar Comentario
	 **/	
	public void borrar (int id){
		Comentario comentario= em.find(Comentario.class, id);
		em.remove(comentario);
	}
	/**
	 * Este metodo lee un Comentario mediante JPA
	 * @param id - entero id utilizado para leer Comentario
	 **/	
	public Comentario leer (int id){
		Comentario comentario = em.find(Comentario.class, id);
		
		return comentario;
	}
	
	/**
	 * Este metodo lista todos los Comentario mediante JPA
	 * @return - devuelve una lista de todos los comentarios
	 **/
	public List<Comentario> getComentario(){
		String sql = "SELECT c FROM Comentario c ";
		Query q = em.createQuery(sql);
		List<Comentario> comentario = q.getResultList();
		return comentario;
	}
	
	/**
	 * Este metodo lista todos los Comentarios de acuerdo a Referencia mediante JPA
	 * @param id - entero id utilizado para leer Comentario
	 * @return - devuelve una lista de todos los comentarios de a cuerdo a Referencia
	 **/
	public List<Comentario> getComentarioArticulo(int id){
		String sql = "SELECT c FROM Comentario c where referencia="+id;
		//System.out.println("ESTA ES LA CONSULTAX ....." + sql);
		Query q = em.createQuery(sql);
		List<Comentario> comentario = q.getResultList();
		return comentario;
	}
	/**
	 * Este metodo lista todos los Comentarios de acuerdo a Referencia mediante JPA
	 * aplicando cruce de tablas con referencia se utiliza con los web servers
	 * @param id - entero id utilizado para leer Comentario
	 * @return - devuelve una lista de todos los comentarios de a cuerdo a Referencia
	 **/
	public List<Comentario> getComentarioArticuloRef(int id){
		String sql = "SELECT c FROM Comentario c, ReferenciaAPA r where  c.referencia=r.id  and c.referencia="+id;
		//System.out.println("ESTA ES LA CONSULTAX ....." + sql);
		Query q = em.createQuery(sql);
		List<Comentario> comentario = q.getResultList();
		return comentario;
	}
	
	
	
	
	
}