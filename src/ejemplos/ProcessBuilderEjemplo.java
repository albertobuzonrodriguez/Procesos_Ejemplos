package ejemplos;

import java.io.*;

public class ProcessBuilderEjemplo {
    public static void main(String[] args) throws InterruptedException {
        try {
            String url = "https://www.youtube.com";
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", url);
            Process proceso = pb.start();
            int exitcode = proceso.waitFor();
            System.out.println("El proceso terminó con código: " + exitcode);
        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso " + e.getMessage());
        }
    }
}
