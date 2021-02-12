package utilidades;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/test")
public class PruebaWebService {
	
	@GET
	@Path("/saludo")
	@Produces("application/json")
	public String saludo(@QueryParam("n") String nombre) 
	{
		return "hola "+nombre;
	}
	
	@GET
	@Path("/suma")
	@Produces("application/json")
	public int suma(@QueryParam("a") int a, @QueryParam("b") int b) 
	{
		return a+b;
	}

}
