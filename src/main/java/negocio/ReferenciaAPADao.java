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

	@Inject
	private EntityManager em;

	
	@Inject
	private EntityManager conexion;

	public void save(ReferenciaAPA referencia){
		if(em.find(ReferenciaAPA.class, referencia.getId())==null)
		//	logger.info("Entrando en la aplicacion "+referencia.getCategoria().getId());
			insertar(referencia);
		else
			actualizar(referencia);
	}
	
	
	public void saveReferencia(ReferenciaAPA referencia) {
		if(this.leer(referencia.getId())!=null)
			this.actualizar(referencia);
		else
			this.insertar(referencia);
		
	}
	
	public java.util.List<ReferenciaAPA> getReferenciaByRating(){
		String sql="SELECT distinct p FROM ReferenciaAPA p "
				+ "join p.actor actor "
				+ "join p.director director "
				+ "join p.categoria categoria "
				+ "order by p.rating desc";
		Query query=conexion.createQuery(sql, ReferenciaAPA.class);
		List<ReferenciaAPA> referencia= query.getResultList();
		return referencia;
	}

	
	
	
	public Optional<ReferenciaAPA> saveP(ReferenciaAPA referencia) {
        try {
            em.getTransaction().begin();
            em.persist(referencia);
            em.getTransaction().commit();
            return Optional.of(referencia);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
	
	
	
	
	
	
	
	public void insertar (ReferenciaAPA referencia){
		
		// Log.e(TAG, "Mensaje de error");
		//em.persist(pelicula.set);
		//em.persist(c);
		//pelicula.getCategoria().setId(1);
		em.persist(referencia);
		
		//System.out.println("CHUCHA CHUCHA CHUAC "+pelicula.getNombre()+" Sinopsis "+pelicula.getSinopsis()+" Foranea "+pelicula.getDirector());
	}
	
	public void actualizar (ReferenciaAPA referencia){
		em.merge(referencia);
	}
	
	public void borrar (int id){
		ReferenciaAPA referencia = em.find(ReferenciaAPA.class, id);
		em.remove(referencia);
	}
	
	public ReferenciaAPA leer (int id){
		ReferenciaAPA referencia = em.find(ReferenciaAPA.class, id);
		return referencia;
	}
	
	public List<ReferenciaAPA> getReferencia(){
		String sql = "SELECT e FROM ReferenciaAPA e ";
		Query q = em.createQuery(sql);
		List<ReferenciaAPA> referencia = q.getResultList();
		return referencia;
	}
	
	
	public List<ReferenciaAPA> getReferenciaOrdenada(){
		String sql = "SELECT r FROM ReferenciaAPA r order by calificacionTotal desc";
		Query q = em.createQuery(sql);
		List<ReferenciaAPA> referencia = q.getResultList();
		return referencia;
	}
	
	public List<ReferenciaAPA> getReferenciaKeywords(){
		String sql = "select r from ReferenciaAPA r";
		Query q = em.createQuery(sql);
		List<ReferenciaAPA> referencia = q.getResultList();
		return referencia;
	}
	public List<ReferenciaAPA> getReferenciaKeywordsNativo(){
		Query nativeQuery = em.createNativeQuery("select r.ref_id, unnest(string_to_array(r.keywords, ','))from tbl_referenciaapa r");
	    
	    List<ReferenciaAPA> results = nativeQuery.getResultList();
	     
	    return results;
	}
	
	
	
	
	
	public List<ReferenciaAPA> getReferenciaPorAno(){
		String sql = "SELECT p FROM ReferenciaAPA p order by  anoPelicula desc";
		Query q = em.createQuery(sql);
		List<ReferenciaAPA> referencia = q.getResultList();
		return referencia;
	}
	
	
	
	
	
}