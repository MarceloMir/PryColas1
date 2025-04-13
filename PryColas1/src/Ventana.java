import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel principal;
    private JComboBox cboModelo;
    private JTextField txtAnio;
    private JButton btnAgregar;
    private JButton btnDesencolar;
    private JTextArea txtListarAutos;
    private JLabel lblMensaje;
    private ColaAutos listaAutos = new ColaAutos();

    public Ventana() {
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String modelo = cboModelo.getSelectedItem().toString();
                    String anioTexto = txtAnio.getText().trim();
                    if (anioTexto.isEmpty()) {
                        throw new Exception("El campo año no puede estar vacío.");
                    }
                    int anio = Integer.parseInt(anioTexto);

                    if (anio < 0) {
                        throw new Exception("El año no puede ser negativo.");
                    }

                    if (anio > 2025) {
                        throw new Exception("El año no puede ser mayor a 2025.");
                    }

                    listaAutos.encolar(new Auto(modelo, anio));
                    txtListarAutos.setText(listaAutos.listarAutos());
                    lblMensaje.setText("Auto agregado correctamente");
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null, "Ingrese un año valido");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        btnDesencolar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Auto autox = listaAutos.desencolar();
                    txtListarAutos.setText(listaAutos.listarAutos());
                    int anioActual = 2025;
                    int antiguedad = anioActual - autox.getAnio();
                    if(antiguedad < 0) antiguedad = 0;

                    int monto = antiguedad * 50;
                    lblMensaje.setText("Auto atendido " + autox + "Debe pagar $" + monto);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.pack();
        frame.setSize(600,400);
        frame.setVisible(true);
    }
}
