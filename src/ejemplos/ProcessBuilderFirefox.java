package ejemplos;

import java.io.*;

public class ProcessBuilderFirefox {
     public static void main(String[] args) throws InterruptedException {
        try {
            String dir = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
            String urlweb = "https://www.youtube.com";
            ProcessBuilder pb = new ProcessBuilder(dir, urlweb);
            Process proceso = pb.start(); 
            int exitcode = proceso.waitFor();
            System.out.println("El proceso terminó con código: "+ exitcode);
        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso "+e.getMessage());
        }
    }
}
