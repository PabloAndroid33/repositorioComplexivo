package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/** 
 * Esta clase permite definir un Comentario
 * y generar una basa de Datos tbl_comentario
 * por medio de JPA
 *@autor Pablo Siguenza
 
 */
@Entity
@Table(name="tbl_comentario")
public class Comentario {
	 
	/**
     * Atributo entero de id del tipo prymary key
     * para implementarse en la tabla
     */
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		@Column(name="ref_id",length=6)
		private int id;
		
		/**
	     * Atributo String comentario
	     * para implementarse en la tabla
	     */
		@Column(length=200)
		private String comentario;
		
		/**
	     * Atributo objeto Usuario 
	     * con declaracion Muchos a uno
	     * para implementarse en la tabla
	     */
		@ManyToOne
		private Usuario usuario;
		
		/**
	     * Atributo objeto Referencia 
	     * con declaracion Muchos a uno
	     * para implementarse en la tabla
	     */
		@ManyToOne
		private ReferenciaAPA referencia;
		/**
		 * Recupera el valor id
		 * @return - devuelve el id "Entero"
		 **/
		public int getId() {
			return id;
		}

		/**
		 * Este metodo estable el atributo id
		 * @param id - valor id del comentario
		 **/
		public void setId(int id) {
			this.id = id;
		}
		/**
		 * Recupera el valor comentario
		 * @return - devuelve el comentario "String"
		 **/
		public String getComentario() {
			return comentario;
		}
		/**
		 * Este metodo estable el atributo comentario
		 * @param comentario - valor del comentario ingresado por usuario
		 **/
		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		/**
		 * Recupera el valor usuario
		 * @return - devuelve el usuario "object"
		 **/
		public Usuario getUsuario() {
			return usuario;
		}

		/**
		 * Este metodo estable el atributo usuario
		 * @param usuario - valor del objeto usuario-->usuario que va a comentar la referencia
		 **/
		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		/**
		 * Recupera el valor referencia
		 * @return - devuelve la referencia "object"
		 **/
		public ReferenciaAPA getReferencia() {
			return referencia;
		}
		/**
		 * Este metodo estable el atributo referencia
		 * @param referencia - valor del objeto referencia de la referencia a ser comentada
		 **/
		public void setReferencia(ReferenciaAPA referencia) {
			this.referencia = referencia;
		}

}
