package negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

//import ec.edu.ups.wikidatups.modelo.Usuario;

import modelo.Usuario;

//import ec.edu.ups.ticketbus.modelo.Ruta;


/** 
 * Esta clase permite operar con la base
 * de datos de la tabla Usuario
 *@autor Pablo Siguenza
 
 */
@Stateless
public class UsuarioDao {

	@Inject
	private EntityManager em;
	
	/*public void save(Categoria categoria){
		if(em.find(Categoria.class, categoria.getId())==null)
			insertar(categoria);
		else
			actualizar(categoria);
	}*/
	
	public void insertarUsuario(Usuario datos) {
		System.out.println("Insertado usuario");
		em.persist(datos);

	}
	public void save(Usuario u) {
		if(this.leer(u.getIdUsuario())!=null)
			this.actualizarUsuario(u);
		else
			this.insertarUsuario(u);
		
	}

	
	public void actualizarUsuario(Usuario u){
		//System.out.println("Actualizando: " + u.getUsrNombre());
		em.merge(u);
	}
	
	
	public void borrar (int id){
		Usuario usuario = em.find(Usuario.class, id);
		em.remove(usuario);
	}
	
	public Usuario leer (int id){
		Usuario usuario= em.find(Usuario.class, id);
		
		return usuario;
	}
	
	
	public Usuario extraerUsuario (int idUsr){
		String sql = "SELECT u FROM Usuario u WHERE u.idUsuario = " + idUsr;
		System.out.println("El sql" +sql);
		Query query = em.createQuery(sql, Usuario.class);
		
		List<Usuario> usuarios = query.getResultList();
		Usuario u = new Usuario();
		for (int i = 0; i < usuarios.size(); i++) {
			u.setIdUsuario(usuarios.get(i).getIdUsuario());
			u.setUsrNombre(usuarios.get(i).getUsrNombre());
		//	u.setArrGustos(usuarios.get(i).getArrGustos());
		}
		return u;
		
	}
	
	public List<Usuario> getUsuarios(){
		String sql = "SELECT u FROM Usuario u where usrRol='biblio' order by idusuario";
		Query q = em.createQuery(sql, Usuario.class);
		List<Usuario> usuario = q.getResultList();
		return usuario;
	}
	
	public List<Usuario> getUsuariosLector(){
		
		String sql = "SELECT u FROM Usuario u where usrRol='lector' order by idusuario";
		Query q = em.createQuery(sql, Usuario.class);
		List<Usuario> usuario = q.getResultList();
		return usuario;
	}
	public Usuario buscarCorreo(Usuario usr) {
		System.out.println("---------" + usr.getUsrCorreo());
		Usuario u = new Usuario();
		boolean encontrado = false;
		String sql = "SELECT u FROM Usuario u WHERE usrCorreo='"+usr.getUsrCorreo()+"'" + " AND usrPassword = '"
				+ usr.getUsrPassword() + "'"+"AND usrRol='lector'";
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
	
	
	
	
	
	
	
	
	
	
	
	public Usuario buscarCorreoAdmin(Usuario usr) {
		System.out.println("---------" + usr.getUsrCorreo());
		Usuario u = new Usuario();
		boolean encontrado = false;
		String sql = "SELECT u FROM Usuario u WHERE usrCorreo='"+usr.getUsrCorreo()+"'" + " AND usrPassword = '"
				+ usr.getUsrPassword() + "'"+"AND usrRol='admin'";
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
	
	public Usuario buscarCorreoBiblio(Usuario usr) {
		System.out.println("---------" + usr.getUsrCorreo());
		Usuario u = new Usuario();
		boolean encontrado = false;
		String sql = "SELECT u FROM Usuario u WHERE usrCorreo='"+usr.getUsrCorreo()+"'" + " AND usrPassword = '"
				+ usr.getUsrPassword() + "'"+"AND usrRol='biblio'";
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Usuario buscarUsuario(Usuario usr) {
		System.out.println("---------" + usr.getUsrNombre());
		Usuario u = new Usuario();
		boolean encontrado = false;
		String sql = "SELECT u FROM Usuario u WHERE usrNombre = '" + usr.getUsrNombre() + "'" + "AND usrPassword = '"
				+ usr.getUsrPassword() + "'";
		System.out.println("La Sql" +sql);
		Query query = em.createQuery(sql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuarios.get(i).getUsrNombre().equalsIgnoreCase(usr.getUsrNombre())) {
				u = usuarios.get(i);
				break;
			} else {
			
			}
		}
		return u;
	}
	
	
	
	

   

	
	
	
}