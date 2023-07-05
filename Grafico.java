import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Grafico extends JFrame  implements ActionListener {
    JButton seleccionArchivo,analizar,guardar;
    JFileChooser busquedaArchivos = new JFileChooser();
    File archivo;
    JTextArea zonaCodigo;

    public Grafico(){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setSize(600, 500);
        agregarComponentes();
    }
    private void agregarComponentes(){
        guardar = new JButton("Guardar");
        guardar.setSize(200,30);
        guardar.setLocation(400,0);
        add(guardar);
        guardar.addActionListener(this);
        analizar = new JButton("Analizar Codigo");
        analizar.setSize(200,30);
        analizar.setLocation(200,0);
        add(analizar);
        seleccionArchivo = new JButton("buscar Archivos");
        seleccionArchivo.setSize(200,30);
        seleccionArchivo.addActionListener(this);
        analizar.addActionListener(this);
        add(seleccionArchivo);
        zonaCodigo = new JTextArea();
        zonaCodigo.setSize(500,300);
        zonaCodigo.setLocation(50,100);
        add(zonaCodigo);
        Border border = BorderFactory.createLineBorder(Color.BLACK); // Crear un borde de línea negra
        zonaCodigo.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Aplicar el borde y el espacio interno

    }

    public static void main(String[] args) {
        new Grafico();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==seleccionArchivo){
            busquedaArchivos.showOpenDialog(null);
            if (busquedaArchivos.getSelectedFile()!=null){//evitamos nullPointerEception por no selecicoanr archivos
                archivo = busquedaArchivos.getSelectedFile();
                lectura();
            }

        }else if(e.getSource()==analizar){
            new Parser(zonaCodigo.getText());

        } else if (e.getSource()==guardar) {
            guardar();

        }


    }
    private void lectura(){
        //leemos el programa metiendolo en el textArea
        /*
        * aunque ya tengas algo escrito, se limpia
        * automaticamente dejando el conetnido del archivo
        *
        * */

        try {
            String codigoObtenido = ObtenerCodigo(archivo);
            zonaCodigo.setText(codigoObtenido);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /*
    * este metodo es el mismo de la clase Codigo_Obtenido
    * decidi traerlo para aca para no tener complicaciones
    * en caso de modificar el metodo de esa clase, por si lo ocupamos en
    * alguna otra cosa o para no causar errores por accidente
    * de momento podriamos tenerlo asi y ya mas adelante
    * usar el metodo que ya tenemos solo cambiando que en vez
    * de que sea una url del explorador de archivos, sea ya
    * un archivo extraido de FileChooser
    * */
    public static String ObtenerCodigo(File archivoCodigo) throws IOException {
        BufferedReader buf = new BufferedReader(new FileReader(archivoCodigo));
        String lectura, Codigo = "";
        int contador = 0;
        while ((lectura = buf.readLine()) != null) {
            Codigo = Codigo + lectura + "\n";
            contador++;
        }
        return Codigo.toString();
    }
    private void guardar() {
        if (archivo == null) {
            busquedaArchivos.setMultiSelectionEnabled(false);
            busquedaArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int seleccion = busquedaArchivos.showSaveDialog(this.getContentPane());
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = busquedaArchivos.getSelectedFile();
            }
        }
        if (archivo != null) {
            try {
                FileWriter fileWriter = new FileWriter(archivo);
                fileWriter.write(zonaCodigo.getText());
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        JOptionPane.showMessageDialog(null,"se guardaron los cambios");
    }

}
