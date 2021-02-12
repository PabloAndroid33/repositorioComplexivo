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

@Stateless
@Startup
public class LecturaDao {

	@Inject
	private EntityManager em;

	
	@Inject
	private EntityManager conexion;

	public void save(Lectura lectura){
		if(em.find(Comentario.class, lectura.getId())==null)
		//	logger.info("Entrando en la aplicacion "+pelicula.getCategoria().getId());
			insertar(lectura);
		else
			actualizar(lectura);
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
	
	
	
	
	
	
	
	public void insertar (Lectura lectura){
		
		// Log.e(TAG, "Mensaje de error");
		//em.persist(pelicula.set);
		//em.persist(c);
		//pelicula.getCategoria().setId(1);
		em.persist(lectura);
		
		//System.out.println("CHUCHA CHUCHA CHUAC "+pelicula.getNombre()+" Sinopsis "+pelicula.getSinopsis()+" Foranea "+pelicula.getCategoria());
	}
	
	public void actualizar (Lectura lectura){
		em.merge(lectura);
	}
	
	public void borrar (int id){
		Lectura lectura= em.find(Lectura.class, id);
		em.remove(lectura);
	}
	
	public Lectura leer (int id){
		Lectura lectura = em.find(Lectura.class, id);
		return lectura;
	}
	
	public List<Lectura> getLectura(){
		String sql = "SELECT l FROM Lectura l ";
		Query q = em.createQuery(sql);
		List<Lectura> lectura = q.getResultList();
		return lectura;
	}
	
	public List<Lectura> getLecturaUsuario(int id){
		String sql = "SELECT l FROM Lectura l where usuario="+id;
		System.out.println("ESTA ES LA CONSULTAXXXXXXXXXXXXX ....." + sql);
		Query q = em.createQuery(sql);
		List<Lectura> lectura = q.getResultList();
		return lectura;
	}
	
	
	public List<Comentario> getPeliculaPorAno(){
		String sql = "SELECT p FROM Pelicula p order by  anoPelicula desc";
		Query q = em.createQuery(sql);
		List<Comentario> comentario = q.getResultList();
		return comentario;
	}
	
}