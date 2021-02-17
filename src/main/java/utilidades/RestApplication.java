package utilidades;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;


/** 
 * Esta clase llama al Directorio
 * para que se aplique el Web service dentro de 
 * el directorio /srv
 
 */
@ApplicationPath("/srv")
public class RestApplication extends Application{

}
