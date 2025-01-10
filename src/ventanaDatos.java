import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ventanaDatos extends ventanaLogin {
    public JPanel datosPanel;
    private JTextField textCedula;
    private JTextField textNombre;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JButton limpiarButton;
    private JButton ingresarButton;
    private JLabel lbMensaje;
    private JLabel lbConfirma;

    public ventanaDatos() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Conectar con la database
                String url = "jdbc:mysql://localhost:3306/prueba3";
                String user = "root";
                String password = "123456";

                Connection conn = null;
                PreparedStatement ps = null;

                //Obtener datos
                String cedula = textCedula.getText();
                String nombre = textNombre.getText();
                Double materia1 = Double.parseDouble(text1.getText());
                Double materia2 = Double.parseDouble(text2.getText());
                Double materia3 = Double.parseDouble(text3.getText());
                Double materia4 = Double.parseDouble(text4.getText());
                Double materia5 = Double.parseDouble(text5.getText());

                try{
                    conn = DriverManager.getConnection(url, user, password);
                    System.out.println("Se ha conectado correctamente a la base de datos");

                    // Insertar datos en la base
                    String sql = "INSERT INTO estudiantes (cedula,nombre,materia1,materia2,materia3,materia4,materia5) VALUES(?,?,?,?,?,?,?)";

                    ps = conn.prepareStatement(sql);
                    ps.setString(1, cedula);
                    ps.setString(2, nombre);
                    ps.setDouble(3, materia1);
                    ps.setDouble(4, materia2);
                    ps.setDouble(5, materia3);
                    ps.setDouble(6, materia4);
                    ps.setDouble(7, materia5);

                    lbConfirma.setText("Los datos se han insertado correctamente");

                }catch (SQLException esq){
                    esq.printStackTrace();
                }finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }


            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textCedula.setText("");
                textNombre.setText("");
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");
            }
        });
    }
}
