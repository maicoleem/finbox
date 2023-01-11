import javax.jws.soap.SOAPBinding;
import javax.swing.*;
import java.awt.event.*;

public class GroupsForms {

    public JPanel jpGroups;
    private JPasswordField pfPasswordAdmin;
    private JPasswordField pfPasswordOther;
    private JTextField tfGroupName;
    private JButton bCreateG;
    private JButton bStartG;
    private JButton bDeletedG;
    private JLabel lbGroup;
    private JLabel lbPA;
    private JLabel lbPN;
    private JButton bExitG;
    private JLabel lblGroups;

    public GroupsForms() {
        bCreateG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  groupName = tfGroupName.getText();
                String pAdmin = new String(pfPasswordAdmin.getPassword());
                String pOther = new String(pfPasswordOther.getPassword());
                GroupsMethods.groupCreated(groupName, pAdmin, pOther);
            }
        });
        bStartG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  groupName = tfGroupName.getText();
                String pAdmin = new String(pfPasswordAdmin.getPassword());
                String pOther = new String(pfPasswordOther.getPassword());
                String p;
                if(!pAdmin.equals("")){
                    p = pAdmin;
                }else{
                    p = pOther;
                }
                GroupsMethods.groupLogin(groupName, p);
            }
        });
        bExitG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupsMethods.groupExit();
            }
        });
        bDeletedG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GroupsMethods.groupDelete(UserAdmin.groupAdmin);
            }
        });
        jpGroups.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                bDeletedG.setEnabled(UserAdmin.booAdmin);
                bExitG.setEnabled(UserAdmin.booOther);
                lblGroups.setText(UserAdmin.groupsEstado);
                super.mouseMoved(e);
            }
        });
        tfGroupName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int Max_Len = 10;
                char c = e.getKeyChar();
                int len = tfGroupName.getText().length();
                if (len < Max_Len){
                    return;
                }else{
                    UserAdmin.groupsEstado = "Too Long";
                    String user = tfGroupName.getText();
                    user = user.substring(0, Max_Len-1);
                    tfGroupName.setText(user);
                }
                super.keyPressed(e);
            }
        });
        pfPasswordAdmin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int Max_Len = 10;
                char c = e.getKeyChar();
                int len = pfPasswordAdmin.getPassword().length;
                if (len < Max_Len){
                    return;
                }else{
                    UserAdmin.groupsEstado = "Too Long";
                    String user = new String(pfPasswordAdmin.getPassword());
                    user = user.substring(0, Max_Len-1);
                    pfPasswordAdmin.setText(user);
                }
                super.keyPressed(e);
            }
        });
        pfPasswordOther.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                    int Max_Len = 10;
                    char c = e.getKeyChar();
                    int len = pfPasswordOther.getPassword().length;
                    if (len < Max_Len){
                        return;
                    }else{
                        UserAdmin.groupsEstado = "Too Long";
                        String user = new String(pfPasswordOther.getPassword());
                        user = user.substring(0, Max_Len-1);
                        pfPasswordOther.setText(user);
                    }
                super.keyPressed(e);
            }
        });
    }
}
