package utilidades;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

	@Produces
	@PersistenceContext
	
	/**
     * Objeto del tipo Entity manager 
     */
	private EntityManager em;
	   
	 /*
	  * Logger, API para manejar el debug (niveles de depuracion)
	  *  * @return objeto del tipo Logger
	  */
	   @Produces
	   public Logger produceLog(InjectionPoint injectionPoint) {
	      return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	   }
	 
	  /*
	   * Manipulacion de la vista (modificacion y actulizacion)
	   * @return objeto del tipo FacesContext
	   */
	   @Produces
	   @RequestScoped
	   public FacesContext produceFacesContext() {
	      return FacesContext.getCurrentInstance();
	   }
	   
	
}