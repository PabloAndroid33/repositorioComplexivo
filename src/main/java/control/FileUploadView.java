package control;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;


/** 
 * Esta clase la Utilizara
 * FileUploadView donde define los metodos
 * de carga de archivo
 
 */
@ManagedBean
@SessionScoped
public class FileUploadView {
	
	/**
     * Atributo tipo Uploaded declarado
     */
	UploadedFile file;

	/**
	 * Recupera el valor id
	 * @return - devuelve el objeto tipo UploadedFile
	 **/
	public UploadedFile getFile() {
		return file;
	}

	/**
	 * Este metodo estable el atributo file
	 * @param file - valor uploaded a ser cargado
	 **/
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	  /**  
     * Metodo que carga el archivo hacia el servidor      
     * @param e el Carga un archivo del tipo FileUploadEvent
     */  
	public void fileUploadListener(FileUploadEvent e){
		// Get uploaded file from the FileUploadEvent
		this.file = e.getFile();
		// Print out the information of the file
		System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
		// Add message
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("File Uploaded Successfully"));
	}
}
