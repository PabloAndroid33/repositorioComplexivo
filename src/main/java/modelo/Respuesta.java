package modelo;

/** 
 * Esta clase Utilizada para respuesta
 * de web service si se ejecutan
 * o no los web service
 */
public class Respuesta {

	private int codigo;
	private String mensaje;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public String toString() {
		return "Respuesta [codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}
	
}