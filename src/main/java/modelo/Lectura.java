package modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_lectura")
public class Lectura {
	  
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)

		@Column(name="lec_id",length=6)
		private int id;
		
	
		
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
