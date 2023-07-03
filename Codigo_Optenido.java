import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Codigo_Optenido {
    public static String ObtenerCodigo() throws IOException {
        File doc = new File("C:\\Compilador_sencillo\\Archivos_Codigo\\prueba1_correcta.txt");
        BufferedReader buf = new BufferedReader(new FileReader(doc));
        String lectura, Codigo = "";
        int contador = 0;
        while ((lectura = buf.readLine()) != null) {
            Codigo = Codigo + lectura + "\n";
            contador++;
        }
        return Codigo.toString();
    }

}
