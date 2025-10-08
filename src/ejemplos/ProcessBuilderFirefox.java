package ejemplos;

import java.io.*;

public class ProcessBuilderFirefox {
     public static void main(String[] args) throws InterruptedException {
        try {
            String url = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
            ProcessBuilder pb = new ProcessBuilder(url);
            Process proceso = pb.start();
            int exitcode = proceso.waitFor();
            System.out.println("El proceso terminó con código: "+ exitcode);
        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso "+e.getMessage());
        }
    }
}
