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

	
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getReferencia() {
		return referencia;
	}




	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}




	public String getUrlArchivo() {
		return urlArchivo;
	}




	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}




	public String getResumen() {
		return resumen;
	}




	public void setResumen(String resumen) {
		this.resumen = resumen;
	}




	public String getKeywords() {
		return keywords;
	}




	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}




	public Integer getCalificacion() {
		return Calificacion;
	}




	public void setCalificacion(Integer calificacion) {
		Calificacion = calificacion;
	}




	public int getNumCalifica() {
		return numCalifica;
	}




	public void setNumCalifica(int numCalifica) {
		this.numCalifica = numCalifica;
	}




	public int getSumatoria() {
		return sumatoria;
	}




	public void setSumatoria(int sumatoria) {
		this.sumatoria = sumatoria;
	}




	public float getCalificacionTotal() {
		return calificacionTotal;
	}




	public void setCalificacionTotal(float calificacionTotal) {
		this.calificacionTotal = calificacionTotal;
	}
	

	



	
	/*@ManyToOne
	private Director director;*/
	
	
	
	
	
	
	

	
	
	
   
	
	
	

	
	


	
	
	

	
	


	
	
}