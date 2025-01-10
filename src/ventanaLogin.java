import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ventanaLogin {
    private JTextField textUsuario;
    private JTextField textPassword;
    private JButton ingresarButton;
    public JPanel loginPanel;
    private JLabel lbBienvenido;
    private JLabel lbUsuario;
    private JLabel lbPassword;


    public ventanaLogin() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Conectar con la database
                String url = "jdbc:mysql://localhost:3306/prueba3";
                String user = "root";
                String password = "123456";

                Connection conn = null;
                PreparedStatement ps = null;

                String usuarioLogin = textUsuario.getText();
                String passwordLogin = textPassword.getText();

                try{

                    conn = DriverManager.getConnection(url, user, password);
                    System.out.println("Se ha conectado correctamente a la base de datos");

//                    String query = "SELECT * FROM usuarios WHERE username = ?";
//                    PreparedStatement pstmt = conn.prepareStatement(query);
//                    ResultSet userTrue = pstmt.executeQuery();
//
//                    String query1 = "SELECT * FROM usuarios WHERE password = ?";
//                    PreparedStatement pstmt1 = conn.prepareStatement(query1);
//                    ResultSet paswwordTrue = pstmt1.executeQuery();


                    if (usuarioLogin.equals(user) && passwordLogin.equals(password)) {
                        JFrame ventana = new JFrame("Registrar datos");
                        ventana.setContentPane(new ventanaDatos().datosPanel);
                        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        ventana.setSize(600,480);
                        ventana.setPreferredSize(new Dimension(600,480));
                        ventana.pack();
                        ventana.setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "Acceso denegado. Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                    }


                } catch (SQLException ep) {
                    ep.printStackTrace();
                } finally {
                    try {
                        if (conn != null) {
                            conn.close();
                        }
                        if (ps != null) {
                            ps.close();
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }


            }
        });
    }

}
