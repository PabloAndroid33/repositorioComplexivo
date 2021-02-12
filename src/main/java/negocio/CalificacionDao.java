package negocio;

import java.util.List;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.Calificacion;
//import ec.edu.ups.wikidatups.modelo.Usuario;

import modelo.Usuario;

//import ec.edu.ups.ticketbus.modelo.Ruta;

@Stateless

public class CalificacionDao {

	@Inject
	private EntityManager em;
	
	/*public void save(Categoria categoria){
		if(em.find(Categoria.class, categoria.getId())==null)
			insertar(categoria);
		else
			actualizar(categoria);
	}*/
	
	public void insertarcalificacion(Calificacion datos) {
		System.out.println("Insertado usuario");
		em.persist(datos);

	}
	
	public void actualizarCalificacion(Calificacion c){
		//System.out.println("Actualizando: " + c.getValor());
		em.merge(c);
	}
	
	public Calificacion extraerUsuario (int idCal){
		String sql = "SELECT c FROM Usuario c WHERE u.idUsuario = " + idCal;
		System.out.println("El sql" +sql);
		Query query = em.createQuery(sql, Usuario.class);
		
		List<Calificacion> calificacion = query.getResultList();
		Calificacion u = new Calificacion();
		for (int i = 0; i < calificacion.size(); i++) {
			u.setId(calificacion.get(i).getId());
		//	u.setValor(calificacion.get(i).getValor());
		//	u.setArrGustos(usuarios.get(i).getArrGustos());
		}
		return u;
		
	}
	
	public List<Calificacion> getCalificacion(){
		String sql = "SELECT g FROM Calificacion g";
		Query q = em.createQuery(sql, Calificacion.class);
		List<Calificacion> calificacion = q.getResultList();
		return calificacion;
	}
	
	public Usuario buscarUsuario(Usuario usr) {
		System.out.println("---------" + usr.getUsrCorreo());
		Usuario u = new Usuario();
		boolean encontrado = false;
		String sql = "SELECT u FROM Usuario u WHERE usrCorreo = '" + usr.getUsrCorreo() + "'" + "AND usrPassword = '"
				+ usr.getUsrPassword() + "'";
		System.out.println("La Sql" +sql);
		Query query = em.createQuery(sql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getUsrCorreo().equalsIgnoreCase(usr.getUsrCorreo())) {
				u = usuarios.get(i);
				break;
			} else {
			
			}
		}
		return u;
	}
	

   

	
	public List<Usuario> getUsuarios(){
		String sql = "SELECT u FROM Usuario u";
		Query q = em.createQuery(sql, Usuario.class);
		List<Usuario> usuario = q.getResultList();
		return usuario;
	}
	
}