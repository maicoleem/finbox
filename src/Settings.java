import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class Settings {
    private JButton bCrear;
    private JButton bStart;

    public Settings() {
        bCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd = new String(pfPassword.getPassword());
                UserMethods.createUser(tfUser.getText(), pwd);
            }
        });
        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pwd = new String(pfPassword.getPassword());
                UserMethods.startUser(tfUser.getText(), pwd);
            }
        });

        bExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    UserMethods.exit(UserAdmin.userAdmin);
                    tfUser.setText("");
                    pfPassword.setText("");
                    UserAdmin.boo = false;
            }
        });

        bDeleted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAdmin.boo = false;
                UserMySQL.userDeleted(UserAdmin.userAdmin);
            }
        });

        tfUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int Max_Len = 20;
                char c = e.getKeyChar();
                int len = tfUser.getText().length();
                if (len < Max_Len){
                    return;
                }else{
                    System.out.println("Too Long");
                    String user = tfUser.getText();
                    user = user.substring(0, Max_Len-1);
                    tfUser.setText(user);
                }
                super.keyPressed(e);
            }
        });
        pfPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int Max_Len = 20;
                int len = pfPassword.getPassword().length;
                if (len < Max_Len){
                    return;
                }else{
                    System.out.println("Too Long");
                    String user = new String(pfPassword.getPassword());
                    user = user.substring(0, Max_Len-1);
                    pfPassword.setText(user);
                }
                super.keyPressed(e);
            }
        });

        jpSettings.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                bDeleted.setEnabled(UserAdmin.boo);
                bExit.setEnabled(UserAdmin.boo);
                lblUser.setText(UserAdmin.userEstado);
                super.mouseMoved(e);
            }
        });
    }

    private JTextField tfUser;
    private JLabel Usuario;
    private JLabel Password;
    public JButton bExit;
    public JButton bDeleted;
    private JPasswordField pfPassword;
    public JPanel jpSettings;
    private JLabel lblUser;

}
