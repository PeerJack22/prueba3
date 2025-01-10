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
                ResultSet rs = null;

                String usuarioLogin = textUsuario.getText();
                String passwordLogin = textPassword.getText();

                try{

                    conn = DriverManager.getConnection(url, user, password);
                    System.out.println("Se ha conectado correctamente a la base de datos");


                    // Consulta para verificar usuario y contraseña
                    String query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, usuarioLogin);
                    ps.setString(2, passwordLogin);
                    rs = ps.executeQuery();


                    if (rs.next()) {
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
