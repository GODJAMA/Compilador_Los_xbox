import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) throws IOException {
        //new Parser("var1 int ; var2 int ; var1 := var2 + var1 ; print var1 + var2 ;");
        Codigo_Optenido Codigo_Optenido = new Codigo_Optenido();
        String Codigo = Codigo_Optenido.ObtenerCodigo();
        System.out.println(Codigo);
    }
}