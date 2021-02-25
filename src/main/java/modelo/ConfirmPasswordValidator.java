package modelo;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/** 
 * Esta clase permite definir una Clase comfirmPasswordValidator
 * con lo cual sera urilizado para que una password no se repita
 *@autor Pablo Siguenza
 
 */
@FacesValidator("confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator {
	/**  
     * Metodo para validar que dos campos de password sean igualles 
     */ 
       @Override
       
       
       public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
           String password = (String) value;
           String confirm = (String) component.getAttributes().get("confirm");

           if (password == null || confirm == null) {
               return; // Just ignore and let required="true" do its job.
           }

           if (!password.equals(confirm)) {
               throw new ValidatorException(new FacesMessage("Las passwords no coinciden."));
           }
       }

}