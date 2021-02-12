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

@Entity
@Table(name="tbl_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@Column(length = 80)//, nullable = false, unique = true)
	@NotEmpty(message="Ingrese Nombre, no debe ser nulo")
   @Pattern(regexp = "[^0-9]*", message = "Nombre no debe contener letras")
	private String nombre;
	
	@Column(length = 80)//, nullable = false, unique = true)
	@NotEmpty(message="Ingrese Apellido, no debe ser nulo")
	 @Pattern(regexp = "[^0-9]*", message = "Apellido no debe contener letras")
	private String apellido;

	@Column(length = 80)//, nullable = false, unique = true)
    @NotEmpty(message="Ingrese Correo, no debe ser nulo")
    
	private String usrCorreo;
	
	@Column(length = 80)//, nullable = false, unique = true)
	@Size(min=5,max=15,message = "Clave incorecta debe contener de 5 a 15 caracteres")
	@NotEmpty(message="Ingrese Password, no debe ser nulo")
	private String usrPassword;

	@Column(length = 80)//, nullable = false, unique = true)
	@NotEmpty(message="Ingrese User, no debe ser nulo")
    private String usrNombre;
	
	
	
	@Column(length = 80)//, nullable = false, unique = true)
	@Size(min=10,max=10,message = "El telefono debe contener 10 digitos")
	@NotEmpty(message="Ingrese Telefono, no debe ser nulo")
	private String telefono;
	
	@Column(nullable = true)
	private String usrRol;



	public int getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getUsrNombre() {
		return usrNombre;
	}



	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}



	public String getUsrCorreo() {
		return usrCorreo;
	}



	public void setUsrCorreo(String usrCorreo) {
		this.usrCorreo = usrCorreo;
	}



	public String getUsrPassword() {
		return usrPassword;
	}



	public void setUsrPassword(String usrPassword) {
		this.usrPassword = usrPassword;
	}



	

	public String getUsrRol() {
		return usrRol;
	}



	public void setUsrRol(String usrRol) {
		this.usrRol = usrRol;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", usrNombre=" + usrNombre + ", usrCorreo=" + usrCorreo
				+ ", usrPassword=" + usrPassword + ", usrRol=" + usrRol + "]";
	}

	
}
