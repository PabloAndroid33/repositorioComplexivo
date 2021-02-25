package control;

import java.util.ResourceBundle;

import javax.faces.FactoryFinder;   
import javax.faces.application.Application;   
import javax.faces.application.ApplicationFactory;   
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;   
import javax.faces.el.ValueBinding;   
import javax.faces.webapp.UIComponentTag;   
import javax.servlet.ServletContext;   
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;   
 
/** 
 * Esta clase define el directorio
 * y configura para que un archivo pdf
 * se guarde en 
 
 */
public class FaceUtils {   
    /**  
     * Get servlet context.  
     *   
     * @return objeto Servlet context respecto a subida de archivos 
     */   
    public static ServletContext getServletContext() {   
        return (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();   
    }   
       
    public static ExternalContext getExternalContext() {   
    	FacesContext fc=FacesContext.getCurrentInstance();
		return fc.getExternalContext();   
    } 
    
    public static HttpSession getHttpSession(boolean create) {   
    	
		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(create);   
    } 
    /**  
     * Obtenga managed bean .según el nombre  
     *   
     * @param beanName the nombre del bean 
     * @return el managed bean asociado con el bean nombre  
     */   
    public static Object getManagedBean(String beanName) {   
        return getValueBinding(getJsfEl(beanName)).getValue(FacesContext.getCurrentInstance());   
           
        //return o;   
    }    
    
   
    
    
       
    /**  
     * Remueve el managed bean basado en el nombre del bean.  
     *   
     * @param beanName el nombre del bean a ser eliminado  
     */   
    public static void resetManagedBean(String beanName) {   
        getValueBinding(getJsfEl(beanName)).setValue(FacesContext.getCurrentInstance(), null);   
    }   
       
    /**  
     * Almacene el bean administrado dentro del alcance de la sesión.  
     *   
     * @param beanName el nombre del bean administrado que se almacenará
     * @param managedBean the managed bean que se almacenará 
     */   
    public static void setManagedBeanInSession(String beanName, Object managedBean) {   
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(beanName, managedBean);   
    }   
       
    /**  
     * Obtiene el valor del parámetro del alcance de la solicitud.  
     *   
     * @param name el nombre de el Parametro  
     * @return el valor del Parametro 
     */   
    public static String getRequestParameter(String name) {   
        return (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);   
    }   
       
    /**  
     * Agrega mensaje de informacion.  
     *   
     * @param msg the information message  
     */   
    public static void addInfoMessage(String msg) {   
        addInfoMessage(null, msg);   
    }   
       
    /**  
     * Agregue un mensaje de información a un cliente específico. 
     *   
     * @param clientId el cliente id   
     * @param msg el mensaje de información 
     */   
    public static void addInfoMessage(String clientId, String msg) {   
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));   
    }   
       
    /**  
     * Agrega un mensaje de error  
     *   
     * @param msg el mensaje error 
     */   
    public static void addErrorMessage(String msg) {   
        addErrorMessage(null, msg);   
    }   
       
    /**  
     * Agregue un mensaje de error a un cliente específico.  
     *   
     * @param clientId el cliente id   
     * @param msg el mensaje de error 
     */    
    public static void addErrorMessage(String clientId, String msg) {   
        FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));   
    }   
       
    /**  
     * Evalúe el valor entero de una expresión JSF.  
     *   
     * @param el el JSF expresion 
     * @return el valor entero asociado con la expresión JSF 
     */   
    public static Integer evalInt(String el) {   
        if (el == null) {   
            return null;   
        }   
           
        if (UIComponentTag.isValueReference(el)) {   
            Object value = getElValue(el);   
               
            if (value == null) {   
                return null;   
            }   
            else if (value instanceof Integer) {   
                return (Integer)value;   
            }   
            else {   
                return new Integer(value.toString());   
            }   
        }   
        else {   
            return new Integer(el);   
        }   
    }   
       
    private static Application getApplication() {   
        ApplicationFactory appFactory = (ApplicationFactory)FactoryFinder.getFactory(FactoryFinder.APPLICATION_FACTORY);   
        return appFactory.getApplication();    
    }   
       
    private static ValueBinding getValueBinding(String el) {   
        return getApplication().createValueBinding(el);   
    }   
       
    private static HttpServletRequest getServletRequest() {   
        return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();   
    }   
       
    private static Object getElValue(String el) {   
        return getValueBinding(el).getValue(FacesContext.getCurrentInstance());   
    }   
       
    private static String getJsfEl(String value) {   
        return "#{" + value + "}";   
    }   
    
    
    /*private static ResourceBundle getBundle(String key) {   
         
        return FacesContext.getCurrentInstance().getApplication();
     } */
    
    /**  
     * Captura el Path .  
     * @return devuelve String del Path
     */ 
    static String getPath() {   
       try {
    	   ServletContext ctx=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
    	   return ctx.getRealPath("/"); 
       }
       catch(Exception e) {
    	   addErrorMessage("getPath() "+e.getLocalizedMessage());
    	   
       }  
       return null;
    } 
    
    
}   