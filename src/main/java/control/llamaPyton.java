package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.hibernate.validator.constraints.URL;
import org.omg.CORBA.portable.InputStream;
//import org.python.core.PyObject;
//import org.python.util.PythonInterpreter;

import java.util.ArrayList;
import java.util.Properties;

public class llamaPyton {
//	Properties props = new Properties();

	static Thread sent;
    static Thread receive;
    static Socket socket;
    static String saludo;
    String rutaAbsoluta;
    static String texto;
    
    public static Socket getSocket() {
		return socket;
	}

    public static void setSocket(Socket socket) {
		llamaPyton.socket = socket;
	}
    
    
    
    
    
    
    
    public static String getSaludo() {
		return saludo;
	}

	public static void setSaludo(String saludo) {
		llamaPyton.saludo = saludo;
	}

	public String getRutaAbsoluta() {
		return rutaAbsoluta;
	}

	public void setRutaAbsoluta(String rutaAbsoluta) {
		this.rutaAbsoluta = rutaAbsoluta;
	}
	
	
	public  String rutaPython() 
    {

		//Runtime.getRuntime().exec("cmd /c \"cd C:\\TESIS REMIGIO FINAL\\Programas Desarollados\\SistemaWebSociedadLector\\ && python nltk_pdf.py\"");
		rutaAbsoluta = new File("").getAbsolutePath();
		return rutaAbsoluta;
		
		//Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" cd "+rutaAbsoluta+" && python nltk_pdf.py && exit");
		//String command = "C:\\TESIS REMIGIO FINAL\\Programas Desarollados\\SistemaWebSociedadLector\\comando.cmd";
		//Runtime.getRuntime().exec(command);
		
    }
	
	

	public  void conectaServidor() throws IOException, InterruptedException 
    {

		//Runtime.getRuntime().exec("cmd /c \"cd C:\\TESIS REMIGIO FINAL\\Programas Desarollados\\SistemaWebSociedadLector\\ && python nltk_pdf.py\"");
		rutaAbsoluta = new File("").getAbsolutePath();
		Runtime.getRuntime().exec("cmd /c start cmd.exe /K \" cd "+rutaAbsoluta+" && python nltk_pdf.py && exit");
		//String command = "C:\\TESIS REMIGIO FINAL\\Programas Desarollados\\SistemaWebSociedadLector\\comando.cmd";
		//Runtime.getRuntime().exec(command);
		
    }



public  void mensaFromServidor() throws IOException
{
	
	saludo="C:\\TESIS REMIGIO FINAL\\wildfly-20.0.0.Final\\wildfly-20.0.0.Final\\standalone\\deployments\\SistemaWebSociedadLector.war/resources/docs/4G tecnologias.pdf";
	 socket = new Socket("localhost",3035);
	 
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader stdIn =new BufferedReader(isr);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String in = stdIn.readLine();
            System.out.println(in);
            
            out.print(saludo);
            out.flush();
            System.out.println("Conectado ");
}







public static String leeArchivo() 
{
	ArrayList lineas = new ArrayList();
	String part1,part2,valor="";
	 //texto = "";
    try {
        /*Incio del fichero y creación de BufferedReader para poder
         ** lectura (disponer del metodo readLine())*/
        //File archivo = new File("C:\\TESIS REMIGIO FINAL\\wildfly-20.0.0.Final\\wildfly-20.0.0.Final\\standalone\\deployments\\SistemaWebSociedadLector.war/resources/docs/Protocolos_de_Enrutamiento_Para_la_Capa.pdf"+"KEYWORDS");
        FileReader entrada = new FileReader("C:\\TESIS REMIGIO FINAL\\wildfly-20.0.0.Final\\wildfly-20.0.0.Final\\standalone\\deployments\\SistemaWebSociedadLector.war/resources/docs/Protocolos_de_Enrutamiento_Para_la_Capa.pdf"+"KEYWORDS.txt");
        BufferedReader br = new BufferedReader(entrada);

        /*Leer Fichero*/
     while((texto=br.readLine())!=null){
       // texto=texto+", "+texto;
    	 
    	 String[] parts = texto.split(" ");
    	part1 = parts[0]; // 123
    	part2 = parts[1];
    	 
    	 lineas.add(part1);
        
    	 System.out.println(texto);
     }
     
        entrada.close();
      //  return lineas;

        } 
    catch (IOException e) {

        System.out.println("No se ha encontrado el archivo:" + e.getMessage());
   //     return "error";
        }
    
    
    for (int i = 0; i <= 15; i++){ //borde de arriba
        
		 valor=valor+lineas.get(i)+", ";
		 //System.out.print(lineas.get(i)); 
	   }
    
    
   // return texto;
	return valor;
	
}



	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		 float div = (float)12/5;
	        
	      // newReferencia.setCalificacion((int)Math.round (div));
		 
		 System.out.println("Ella me dijo \" ¡Hola! \".");
			
	        System.out.println( "VALEXXXX " + div);
		//String varleeArchivo();
		
		//String valor = "";
		//ArrayList lineas;
		//lineas=leeArchivo();
		// System.out.println("El Texto es " + leeArchivo() );
		 
		 
		 
		/* for (int i = 0; i <= 15; i++){ //borde de arriba
		        
			 valor=valor+lineas.get(i)+", ";
			 //System.out.print(lineas.get(i)); 
		   }*/
		
		 //System.out.println("El Texto es " + valor);
		 
		/*llamaPyton lp=new llamaPyton();
		lp.conectaServidor();
		
		for (int i = 1; i <= 1500000; i++){ //borde de arriba
	        System.out.print("#"); 
	   }
		lp.mensaFromServidor();
		*/
		
/*String rutaAbsoluta = new File("").getAbsolutePath();
		//System.out.println("la rutas absolutas "+rutaAbsoluta);
		
		//String cmd = "C:\\TESIS REMIGIO FINAL\\Programas Desarollados\\SistemaWebSociedadLector"+"\\\\";
		
		String cmd = rutaAbsoluta+"\\";
		System.out.println("la rutas absolutas "+cmd);
		
		//Process p = Runtime.getRuntime().exec(command);
		
		//String guarda=reemplazar(rutaAbsoluta,"\\","\\\\")+"\\";
		
		//System.out.println("la rutas absolutas "+guarda);
		
		String py="nltk_pdf";
		
		
		//Runtime.getRun
		 //* time().exec("python "+cmd+ ".py");
		
		
		
		ProcessBuilder builder = new ProcessBuilder("python", py + ".py");
		
		builder.directory(new File(cmd));
		//builder.redirectError();
	
		
		builder.start();
		
		
		//Runtime.getRuntime().exec("cmd /c \"cd C:\\TESIS REMIGIO FINAL\\Proyecto Phyton\\ejercicios\\ && socketpython.py\"");
	  
		      
		 socket = new Socket("localhost",3008
				 );
         
          //sent = new Thread(new Runnable() {

		 saludo="C:\\TESIS REMIGIO FINAL\\wildfly-20.0.0.Final\\wildfly-20.0.0.Final\\standalone\\deployments\\SistemaWebSociedadLector.war/resources/docs/4G tecnologias.pdf";
                
               
              //  BufferedReader stdIn =new BufferedReader(new InputStreamReader(socket.getInputStream()));
               // PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                      
                   
                //InputStream is = (InputStream) socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader stdIn =new BufferedReader(isr);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                
               // BufferedReader br = new BufferedReader(isr);
                
                
                //System.out.println("Enviando mensaje a Servidor Python...");
               
               
                String in = stdIn.readLine();
                System.out.println(in);
                
                out.print(saludo);
                out.flush();
                System.out.println("Conectado ");*/
                
                          
}}
		
		



