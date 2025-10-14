package ejemplos;

import java.io.*;

public class Python {
    
    public static void ejecutarScript() throws InterruptedException{
         try {
            ProcessBuilder pb = new ProcessBuilder("python", "C:\\Users\\AlbertoBuzónRodrígue\\Desktop\\Script.py");
            Process proceso = pb.start();
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String linea;
            
            while ((linea = lector.readLine()) != null){
               System.out.println(linea);
            }

            int exitCode = proceso.waitFor();
            
            if (exitCode != 0) {
                System.err.println("El comando terminó con código de error: " + exitCode);
            }

        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso "+e.getMessage());
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        ejecutarScript();
    }
}
