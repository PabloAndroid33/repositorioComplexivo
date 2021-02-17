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

	@Inject
	private EntityManager em;

	
	@Inject
	private EntityManager conexion;

	public void save(Comentario comentario){
		if(em.find(Comentario.class, comentario.getId())==null)
		//	logger.info("Entrando en la aplicacion "+pelicula.getCategoria().getId());
			insertar(comentario);
		else
			actualizar(comentario);
	}
	
	public java.util.List<Comentario> getComentarioByRating(){
		String sql="SELECT distinct p FROM Comentario p "
				+ "join p.actor actor "
				+ "join p.director director "
				+ "join p.categoria categoria "
				+ "order by p.rating desc";
		Query query=conexion.createQuery(sql, Comentario.class);
		List<Comentario> comentario= query.getResultList();
		return comentario;
	}

	
	
	
	public Optional<Comentario> saveC(Comentario comentario) {
        try {
            em.getTransaction().begin();
            em.persist(comentario);
            em.getTransaction().commit();
            return Optional.of(comentario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
	
	
	
	
	
	
	
	public void insertar (Comentario comentario){
		
		// Log.e(TAG, "Mensaje de error");
		//em.persist(pelicula.set);
		//em.persist(c);
		//pelicula.getCategoria().setId(1);
		em.persist(comentario);
		
		//System.out.println("CHUCHA CHUCHA CHUAC "+pelicula.getNombre()+" Sinopsis "+pelicula.getSinopsis()+" Foranea "+pelicula.getCategoria());
	}
	
	public void actualizar (Comentario comentario){
		em.merge(comentario);
	}
	
	public void borrar (int id){
		Comentario comentario= em.find(Comentario.class, id);
		em.remove(comentario);
	}
	
	public Comentario leer (int id){
		Comentario comentario = em.find(Comentario.class, id);
		
		return comentario;
	}
	public Comentario leer2 (){
		Comentario comentario = em.find(Comentario.class,null);
		
		return comentario;
	}
	
	
	public List<Comentario> getComentario(){
		String sql = "SELECT c FROM Comentario c ";
		Query q = em.createQuery(sql);
		List<Comentario> comentario = q.getResultList();
		return comentario;
	}
	
	public List<Comentario> getComentarioArticulo(int id){
		String sql = "SELECT c FROM Comentario c where referencia="+id;
		//System.out.println("ESTA ES LA CONSULTAX ....." + sql);
		Query q = em.createQuery(sql);
		List<Comentario> comentario = q.getResultList();
		return comentario;
	}
	
	
	public List<Comentario> getPeliculaPorAno(){
		String sql = "SELECT p FROM Pelicula p order by  anoPelicula desc";
		Query q = em.createQuery(sql);
		List<Comentario> comentario = q.getResultList();
		return comentario;
	}
	
}