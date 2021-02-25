package utilidades;


/** 
 * Da una respuesta a si es que
 * se ejecuta o no el web service
 
 */
public class Respuesta {

	/**
     * Atributo del tipo entero que guarda codigo
     */
	private int codigo;
	/**
     * Atributo del tipo String para alvergar mensaje
     */
	private String mensaje;
	
	/**
	 * Recupera el valor de codigo
	 * @return - devuelve el codigo "Entero"
	 **/
	public int getCodigo() {
		return codigo;
	}
	/**
	 * Este metodo estable el atributo codigo
	 * @param codigo - valor id del comentario
	 **/
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	/**
	 * Recupera el valor de mensaje
	 * @return - devuelve el mensaje "String"
	 **/
	public String getMensaje() {
		return mensaje;
	}
	/**
	 * Este metodo estable el atributo mensaje
	 * @param mensaje - valor mensaje de la respuesta
	 **/
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public String toString() {
		return "Respuesta [codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}
	
}