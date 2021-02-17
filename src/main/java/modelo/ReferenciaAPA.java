package modelo;


import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/** 
 * Esta clase permite definir una Referencia
 * y generar una basa de Datos tbl_referenciaApa
 * por medio de JPA
 *@autor Pablo Siguenza
 
 */

@Entity
@Table(name="tbl_referenciaApa")
public class ReferenciaAPA {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="ref_id",length=6)
	private int id;
	@Column(length=50)
	private String nombre;
	
	
	@Column(length=200)
	private String referencia;
	
	
	@Column(length=200)
	private String urlArchivo;
	
	

	
	@Column(length=500)
	private String resumen;
	
	
	@Column(length=500)
	private String keywords;
	
	@Column
	private Integer Calificacion;
	
	
	@Column
	private int numCalifica;
	
	@Column
	private int sumatoria;


	@Column
	private float calificacionTotal;

	
	/**
	 * Recupera el valor id
	 * @return - devuelve el id "Entero"
	 **/
	public int getId() {
		return id;
	}



	/**
	 * Este metodo estable el atributo id
	 * @param id - valor id de Referencia 
	 **/
	public void setId(int id) {
		this.id = id;
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
	 * @param nombre - valor nombre de la Referencia 
	 **/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	/**
	 * Recupera el valor Referencia APA
	 * @return - devuelve la Referencia APA "String"
	 **/
	public String getReferencia() {
		return referencia;
	}


	/**
	 * Este metodo estable el atributo referencia
	 * @param referencia - valor referencia en formato APA de la Referencia 
	 **/

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}


	/**
	 * Recupera el valor de la URL
	 * @return - devuelve la URL "String"
	 **/

	public String getUrlArchivo() {
		return urlArchivo;
	}



	/**
	 * Este metodo estable el atributo urlArchivo
	 * @param urlArchivo - valor URL-->C://Archivo//archivo.pdf... de la Referencia 
	 **/
	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}




	public String getResumen() {
		return resumen;
	}




	public void setResumen(String resumen) {
		this.resumen = resumen;
	}



	/**
	 * Recupera el valor de las Keywords
	 * @return - devuelve las Keywords "String"
	 **/
	public String getKeywords() {
		return keywords;
	}


	/**
	 * Este metodo estable el atributo de las Keywords
	 * @param keywords - valor de todas las Keywords de la Referencia 
	 **/

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	/**
	 * Recupera el valor de la 	Calificacion
	 * @return - devuelve el Nombre "String"
	 **/

	public Integer getCalificacion() {
		return Calificacion;
	}



	/**
	 * Este metodo estable el atributo Calificacion
	 * @param calificacion - valor de la Calificacion de la Referencia 
	 **/
	public void setCalificacion(Integer calificacion) {
		Calificacion = calificacion;
	}


	/**
	 * Recupera el valor de la cantidad de calificaciones 
	 * @return - devuelve la cantidad de calificaciones "Entero"
	 **/

	public int getNumCalifica() {
		return numCalifica;
	}



	/**
	 * Este metodo estable el atributo numCalifica
	 * @param numCalifica - valor de la cantidad de todas las Calificacion de la Referencia 
	 **/
	public void setNumCalifica(int numCalifica) {
		this.numCalifica = numCalifica;
	}


	/**
	 * Recupera el valor de la Sumatoria de todas las Calificaciones
	 * @return - devuelve la suma de todas las calificaciones "Entero"
	 **/

	public int getSumatoria() {
		return sumatoria;
	}



	/**
	 * Este metodo estable el atributo sumatoria
	 * @param sumatoria - valor de la suma de todas las Calificacion de la Referencia 
	 **/
	public void setSumatoria(int sumatoria) {
		this.sumatoria = sumatoria;
	}


	/**
	 * Recupera el valor de la Calificacion total--> la suma total promediada
	 * @return - devuelve la Calificacion Total "Entero"
	 **/

	public float getCalificacionTotal() {
		return calificacionTotal;
	}



	/**
	 * Este metodo estable el atributo calificacionTotal
	 * @param calificacionTotal - valor de el promedio de todas las Calificaciones de la Referencia 
	 **/
	public void setCalificacionTotal(float calificacionTotal) {
		this.calificacionTotal = calificacionTotal;
	}
	

	



	
	/*@ManyToOne
	private Director director;*/
	
	
	
	
	
	
	

	
	
	
   
	
	
	

	
	


	
	
	

	
	


	
	
}