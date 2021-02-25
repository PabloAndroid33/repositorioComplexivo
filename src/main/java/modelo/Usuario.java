package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
/** 
 * Esta clase permite definir un usuario
 * y generar una basa de Datos tbl_usuario
 * por medio de JPA
 *@autor Pablo Siguenza
 
 */
@Entity
@Table(name="tbl_usuario")
public class Usuario {

	
	/**
     * Atributo entero de id del tipo prymary key
     * para implementarse en la tabla
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	
	/**
     * Atributo String nombre
     * para implementarse en la tabla
     */
	@Column(length = 80)//, nullable = false, unique = true)
	@NotEmpty(message="Ingrese Nombre, no debe ser nulo")
   @Pattern(regexp = "[^0-9]*", message = "Nombre no debe contener letras")
	private String nombre;
	
	/**
     * Atributo String apellido
     * para implementarse en la tabla
     */
	@Column(length = 80)//, nullable = false, unique = true)
	@NotEmpty(message="Ingrese Apellido, no debe ser nulo")
	 @Pattern(regexp = "[^0-9]*", message = "Apellido no debe contener letras")
	private String apellido;

	/**
     * Atributo String correo
     * para implementarse en la tabla
     */
	@Column(length = 80)//, nullable = false, unique = true)
    @NotEmpty(message="Ingrese Correo, no debe ser nulo")
   private String usrCorreo;
	
	/**
     * Atributo String password
     * para implementarse en la tabla
     */
	@Column(length = 80)//, nullable = false, unique = true)
	@Size(min=5,max=15,message = "Clave incorecta debe contener de 5 a 15 caracteres")
	@NotEmpty(message="Ingrese Password, no debe ser nulo")
	private String usrPassword;

	/**
     * Atributo String usrNombre
     * para implementarse en la tabla
     */
	@Column(length = 80)//, nullable = false, unique = true)
	@NotEmpty(message="Ingrese User, no debe ser nulo")
    private String usrNombre;
	
	
	/**
     * Atributo String telefono
     * para implementarse en la tabla
     */
	@Column(length = 80)//, nullable = false, unique = true)
	@Size(min=10,max=10,message = "El telefono debe contener 10 digitos")
	@NotEmpty(message="Ingrese Telefono, no debe ser nulo")
	private String telefono;
	
	@Column(nullable = true)
	private String usrRol;


	
	/**
	 * Recupera el valor id
	 * @return - devuelve el id "Entero"
	 **/
	public int getIdUsuario() {
		return idUsuario;
	}


/**
 * Este metodo estable el atributo idUsuario
 * @param idUsuario - valor id ingresado por usuario
 **/
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	/**
	 * Recupera el valor Nombre
	 * @return - devuelve el Nombre "String"
	 **/
	public String getNombre() {
		return nombre;
	}


	/**
	 * Este metodo estable el atributo nombre
	 * @param nombre - valor nombre ingresado por usuario
	 **/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Recupera el valor Apellido
	 * @return - devuelve el Apellido "String"
	 **/
	public String getApellido() {
		return apellido;
	}


	/**
	 * Este metodo estable el atributo apellido
	 * @param apellido - valor apellido ingresado por usuario
	 **/
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	/**
	 * Recupera el valor Nombre
	 * @return - devuelve el Nombre "String"
	 **/
	public String getUsrNombre() {
		return usrNombre;
	}


	/**
	 * Este metodo estable el atributo usrNombre
	 * @param usrNombre - valor apellido ingresado por usuario
	 **/
	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}


	/**
	 * Recupera el valor Correo
	 * @return - devuelve el Correo "String"
	 **/
	public String getUsrCorreo() {
		return usrCorreo;
	}


	/**
	 * Este metodo estable el atributo usrCorreo
	 * @param usrCorreo - valor de correo ingresado por usuario
	 **/
	public void setUsrCorreo(String usrCorreo) {
		this.usrCorreo = usrCorreo;
	}

	/**
	 * Recupera el valor Password
	 * @return - devuelve el Telefono "String"
	 **/

	public String getUsrPassword() {
		return usrPassword;
	}

	/**
	 * Este metodo estable el atributo usrPassword
	 * @param usrNombre - valor apellido ingresado por usuario
	 **/

	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}



	

	
	
	/**
	 * Recupera el valor Rol
	 * @return - devuelve el Rol "String"
	 **/
	public String getUsrRol() {
		return usrRol;
	}


	/**
	 * Este metodo estable el atributo usrRol
	 * @param usrRol - valor Rol que se utilizara para ver el tipo de usuario
	 **/
	public void setUsrRol(String usrRol) {
		this.usrRol = usrRol;
	}

	/**
	 * Recupera el valor Telefono
	 * @return - devuelve el Telefono "String"
	 **/

	public String getTelefono() {
		return telefono;
	}


	/**
	 * Este metodo estable el atributo telefono
	 * @param telefono - valor telefono ingresado por usuario
	 **/
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", usrNombre=" + usrNombre + ", usrCorreo=" + usrCorreo
				+ ", usrPassword=" + usrPassword + ", usrRol=" + usrRol + "]";
	}

	
}
