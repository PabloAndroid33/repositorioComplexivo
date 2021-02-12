package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_comentario")
public class Comentario {
	  
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		@Column(name="ref_id",length=6)
		private int id;
		
		@Column(length=200)
		private String comentario;
		
		@ManyToOne
		private Usuario usuario;
		
		@ManyToOne
		private ReferenciaAPA referencia;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getComentario() {
			return comentario;
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

		public ReferenciaAPA getReferencia() {
			return referencia;
		}

		public void setReferencia(ReferenciaAPA referencia) {
			this.referencia = referencia;
		}

}
