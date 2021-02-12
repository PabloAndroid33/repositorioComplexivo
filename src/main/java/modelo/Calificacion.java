
package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_calificacion")
public class Calificacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(length=6)
	private int id;

	@Column(length=6)
	private int valor;
	


	@ManyToOne
	private ReferenciaAPA pelicula;

	
	@ManyToOne
	private Usuario usuario;


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getValor() {
	return valor;
}

public void setValor(int valor) {
	this.valor = valor;
}

public ReferenciaAPA getPelicula() {
	return pelicula;
}

public void setPelicula(ReferenciaAPA pelicula) {
	this.pelicula = pelicula;
}

public Usuario getUsuario() {
	return usuario;
}

public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
}


	







}
