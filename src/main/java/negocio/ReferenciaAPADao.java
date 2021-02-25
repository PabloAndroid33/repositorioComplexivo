package negocio;

import java.util.List;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

//import org.hibernate.validator.internal.util.logging.Log.logger;

import java.util.Optional;




import modelo.ReferenciaAPA;
import modelo.Usuario;

//import ec.edu.ups.ticketbus.modelo.Ruta;

/** 
 * Esta clase permite operar con la base
 * de datos de la tabla Referencia
 *@autor Pablo Siguenza
 
 */
@Stateless
@Startup
public class ReferenciaAPADao {

	/**
     * Objeto del tipo Entity manager para operaciones de "Ingreso, Lectura, Actualizacion y borrado"
     */
	@Inject
	private EntityManager em;



	/**
	 * Este metodo guarda un Referencia en caso que exista actualiza Referencia
	 * @param u - objeto Usuario para ser guardado o actualizado
	 **/
	public void save(ReferenciaAPA referencia){
		if(em.find(ReferenciaAPA.class, referencia.getId())==null)
		//	logger.info("Entrando en la aplicacion "+referencia.getCategoria().getId());
			insertar(referencia);
		else
			actualizar(referencia);
	}
	
	/**
	 * Este metodo guarda un Referencia en caso que exista actualiza Referencia
	 * @param u - objeto Usuario para ser guardado o actualizado
	 **/
	public void saveReferencia(ReferenciaAPA referencia) {
		if(this.leer(referencia.getId())!=null)
			this.actualizar(referencia);
		else
			this.insertar(referencia);
		
	}
	/**
	 * Este metodo inserta una Referencia mediante JPA
	 * @param referencia - objeto Referencia para ser guardado
	 **/
public void insertar (ReferenciaAPA referencia){
		
		// Log.e(TAG, "Mensaje de error");
		//em.persist(pelicula.set);
		//em.persist(c);
		//pelicula.getCategoria().setId(1);
		em.persist(referencia);
		
		//System.out.println("CHUCHA CHUCHA CHUAC "+pelicula.getNombre()+" Sinopsis "+pelicula.getSinopsis()+" Foranea "+pelicula.getDirector());
	}
/**
 * Este metodo actualiza una Referencia mediante JPA
 * @param referencia - objeto Referencia para ser actualizado
 **/
	public void actualizar (ReferenciaAPA referencia){
		em.merge(referencia);
	}
	/**
	 * Este metodo borra un Referencia mediante JPA
	 * @param id - entero id utilizado para borrar Referencia
	 **/
	public void borrar (int id){
		ReferenciaAPA referencia = em.find(ReferenciaAPA.class, id);
		em.remove(referencia);
	}
	/**
	 * Este metodo lee un Referencia mediante JPA
	 * @param id - entero id utilizado para leer Referencia
	 **/
	public ReferenciaAPA leer (int id){
		ReferenciaAPA referencia = em.find(ReferenciaAPA.class, id);
		return referencia;
	}
	/**
	 * Este metodo lista todas las Referencias mediante JPA
	 * @return - devuelve una lista de todas las Referencias
	 **/
	public List<ReferenciaAPA> getReferencia(){
		String sql = "SELECT e FROM ReferenciaAPA e ";
		Query q = em.createQuery(sql);
		List<ReferenciaAPA> referencia = q.getResultList();
		return referencia;
	}
	

	/**
	 * Este metodo lista todas las Referencias ordenadas por Calificacion mayor mediante JPA
	 * @return - devuelve una lista de todas las Referencias ordenadas por Calificacion
	 **/
	public List<ReferenciaAPA> getReferenciaOrdenada(){
		String sql = "SELECT r FROM ReferenciaAPA r order by calificacionTotal desc";
		Query q = em.createQuery(sql);
		List<ReferenciaAPA> referencia = q.getResultList();
		return referencia;
	}
	
	//Si utilizado para las Busquedas y webService
	
	/**
	 * Este metodo lista todas las Referencias especificando las Keywords mediante JPA
	 * @return - devuelve una lista de todas las Referencias especificando las Keywords
	 **/
	public List<ReferenciaAPA> getReferenciaKeywords(){
		String sql = "select r from ReferenciaAPA r";
		Query q = em.createQuery(sql);
		List<ReferenciaAPA> referencia = q.getResultList();
		return referencia;
	}
	
	
	
	
}