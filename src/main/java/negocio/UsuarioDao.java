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

	/**
     * Objeto del tipo Entity manager para operaciones de "Ingreso, Lectura, Actualizacion y borrado"
     */
	@Inject
	private EntityManager em;
	
	/**
	 * Este metodo inserta un Usuario mediante JPA
	 * @param datos - objeto Usuario para ser guardado
	 **/
public void insertarUsuario(Usuario datos) {
		System.out.println("Insertado usuario");
		em.persist(datos);

	}
/**
 * Este metodo guarda un Usuario en caso que exista actualiza Usuario
 * @param u - objeto Usuario para ser guardado o actualizado
 **/
	public void save(Usuario u) {
		if(this.leer(u.getIdUsuario())!=null)
			this.actualizarUsuario(u);
		else
			this.insertarUsuario(u);
		
	}

	/**
	 * Este metodo actualiza un Usuario mediante JPA
	 * @param u - objeto Usuario para ser actualizado
	 **/
	public void actualizarUsuario(Usuario u){
		//System.out.println("Actualizando: " + u.getUsrNombre());
		em.merge(u);
	}
	
	/**
	 * Este metodo borra un Usuario mediante JPA
	 * @param id - entero id utilizado para borrar Usuario
	 **/
	public void borrar (int id){
		Usuario usuario = em.find(Usuario.class, id);
		em.remove(usuario);
	}
	/**
	 * Este metodo lee un Usuario mediante JPA
	 * @param id - entero id utilizado para leer Usuario
	 **/
	public Usuario leer (int id){
		Usuario usuario= em.find(Usuario.class, id);
		
		return usuario;
	}
	
	/**
	 * Este metodo extrae usuario de acuerdo a id 
	 * @param idUsr - entero id utilizado para extraer un usuario
	 * @return - devuelve una usuario de acuerdo a id
	 **/
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
	/**
	 * Este metodo lista todos los Usuario del tipo rol "biblio" mediante JPA
	 * @return - devuelve una lista de todos los Usuarios del tipo "biblio"
	 **/
	public List<Usuario> getUsuarios(){
		String sql = "SELECT u FROM Usuario u where usrRol='biblio' order by idusuario";
		Query q = em.createQuery(sql, Usuario.class);
		List<Usuario> usuario = q.getResultList();
		return usuario;
	}
	/**
	 * Este metodo lista todos los Usuario del tipo rol "Lector" mediante JPA
	 * @return - devuelve una lista de todos los Usuarios del tipo "lector"
	 **/
	public List<Usuario> getUsuariosLector(){
		
		String sql = "SELECT u FROM Usuario u where usrRol='lector' order by idusuario";
		Query q = em.createQuery(sql, Usuario.class);
		List<Usuario> usuario = q.getResultList();
		return usuario;
	}
	
	/**
	 * Este metodo obtiene el usuario de acuerdo al correo y password del tipo lector
	 * @param usr - objeto Usuario a utilizarse para buscar usuario de acuerdo a correo y password del tipo lector
	 * @return - devuelve una objeto usuario de acuerdo a usuario y password del tipo lector
	 **/
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
	
	/**
	 * Este metodo obtiene el usuario de acuerdo al correo y password del tipo admin
	 * @param usr - objeto Usuario a utilizarse para buscar usuario de acuerdo a correo y password del tipo admin
	 * @return - devuelve una objeto usuario de acuerdo a usuario y password del tipo admin
	 **/
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
/**
 * Este metodo obtiene el usuario de acuerdo al correo y password del tipo bibliotecario
 * @param usr - objeto Usuario a utilizarse para buscar usuario de acuerdo a correo y password del tipo bibliotecario
 * @return - devuelve una objeto usuario de acuerdo a usuario y password del tipo admin
 **/
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Este metodo obtiene el usuario de acuerdo al correo y password
	 * @param usr - objeto Usuario a utilizarse para buscar usuario de acuerdo a correo y password
	 * @return - devuelve una objeto usuario de acuerdo a usuario y password
	 **/
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