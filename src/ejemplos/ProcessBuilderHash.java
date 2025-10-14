package ejemplos;

import java.io.*;
import java.util.Scanner;

public class ProcessBuilderHash {
    final static String ARCHIVO_HASH = "ProcessBuilderEjemplo2.txt";

    public static String mostrarHash() throws InterruptedException{
        String resultadoLector [] = new String[3];
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "certutil -hashfile C:\\Users\\AlbertoBuzónRodrígue\\Desktop\\ProcessBuilderEjemplo.txt MD5");
            Process proceso = pb.start(); 
            BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            
            String linea;
            int contador = 0;
            
            while ((linea = lector.readLine()) != null){
                resultadoLector[contador] = linea;
                contador++;
            }

            int exitCode = proceso.waitFor();
            if (exitCode != 0) {
                // Capturar errores si el comando falló (ver nota)
                System.err.println("El comando terminó con código de error: " + exitCode);
            }

        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso "+e.getMessage());
        }
        return resultadoLector[1];
    }

    public static void guardarHash(String hash) throws InterruptedException{
        try {
            FileWriter fw = new FileWriter(ARCHIVO_HASH);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println(hash);
			salida.close();
            bw.close();
            fw.close();
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "certutil -hashfile C:\\Users\\AlbertoBuzónRodrígue\\Desktop\\ProcessBuilderEjemplo.txt MD5");
            pb.redirectOutput(new File (ARCHIVO_HASH));

        } catch (IOException e) {
            System.out.println("Error al ejecutar el proceso "+e.getMessage());
        }
    }

    public static String leerHashAlmacenado() {
        try {
            FileReader fr = new FileReader(ARCHIVO_HASH);
            BufferedReader br = new BufferedReader(fr);
            String hash = br.readLine();
            br.close();
            fr.close();
            return hash;
        } catch (IOException e) {
            System.out.println("Error al leer el hash almacenado: " + e.getMessage());
            return null;
        }
    }

    public static void verificarHash() throws InterruptedException{
        String hash = mostrarHash();
        String hashAlmacenado = leerHashAlmacenado();
        
        System.out.println("Hash actual: " + hash);
        System.out.println("Hash almacenado: " + hashAlmacenado);
        
        if (hash.equals(hashAlmacenado)) {
            System.out.println("Los hashes coinciden. El archivo no ha sido modificado.");
        } else {
            System.out.println("Los hashes no coinciden. El archivo ha sido modificado.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Pulsa 1 para mostrar el hash md5 del fichero indicado. \nPulsa 2 para guardar el valor del hash md5 en el fichero indicado.\nPulsa 3 para comprobar si el contenido del fichero actual coincide con el hash almacenado anteriormente.\nPulsa 4 para salir.");
            opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    String hash = mostrarHash();
                    System.out.println(hash);
                break;
    
                case 2:
                    guardarHash(mostrarHash());
                break;
    
                case 3:
                    verificarHash();
                break;
            }
        } while (opcion != 4);

        sc.close();
    }
}
