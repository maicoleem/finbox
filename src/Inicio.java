import jdk.jfr.Enabled;

import javax.swing.*;
import java.awt.event.*;

public class Inicio {
    public JPanel panelInit;
    private JButton message_1;
    private JButton message_2;
    private JButton message_3;
    private JButton message_4;
    private JButton message_5;

    private JButton Message_6;
    private JButton bDownload;
    private JButton bUpload;
    private JButton bGroup;
    private JButton bSetting;
    private JButton bNotifications;
    private JButton bNext;
    private JTextArea taMessages;
    private JLabel lblStart;

    private String message = "Message 1";
    public Inicio() {
        bSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.jpSettings();
                UserAdmin.startEstado = "Settings";
            }
        });

        bGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.jpGroups();
                UserAdmin.startEstado = "Group";
            }
        });
        bNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tM = message_1.getText();
                String next = new String("Message 7");
                showMessages();
                if(tM.equals(next)){
                    message_1.setText("Message 1");
                    message_2.setText("Message 2");
                    message_3.setText("Message 3");
                    message_4.setText("Message 4");
                    message_5.setText("Message 5");
                    Message_6.setText("Message 6");
                }else{
                    message_1.setText("Message 7");
                    message_2.setText("Message 8");
                    message_3.setText("Message 9");
                    message_4.setText("Message 10");
                    message_5.setText("Message 11");
                    Message_6.setText("Message 12");
                }
            }
        });
        bNotifications.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taMessages.setEnabled(!taMessages.isEnabled());
                String tf = Boolean.valueOf(taMessages.isEnabled()).toString();
                UserAdmin.startEstado = "Editable "+tf ;
                messagesUA();
            }
        });
        taMessages.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                bDownload.setEnabled(UserAdmin.booOther);
                bUpload.setEnabled(UserAdmin.booAdmin);
                bNotifications.setEnabled(UserAdmin.booAdmin);
                bGroup.setEnabled(UserAdmin.userAdmin != null);

                message_1.setEnabled(UserAdmin.booOther);
                message_2.setEnabled(UserAdmin.booOther);
                message_3.setEnabled(UserAdmin.booOther);
                message_4.setEnabled(UserAdmin.booOther);
                message_5.setEnabled(UserAdmin.booOther);
                Message_6.setEnabled(UserAdmin.booOther);
                lblStart.setText(UserAdmin.startEstado);
                super.mouseMoved(e);
            }
        });

        message_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taMessages.isEnabled()){
                    messagesUA();
                }
                message = message_1.getText();
                //muestra mensaje
                showMessages();
            }
        });
        message_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taMessages.isEnabled()){
                    messagesUA();
                }
                message = message_2.getText();
                //muestra mensaje
                showMessages();
            }
        });
        message_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taMessages.isEnabled()){
                    messagesUA();
                }
                message = message_3.getText();
                //muestra mensaje
                showMessages();
            }
        });
        message_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taMessages.isEnabled()){
                    messagesUA();
                }
                message = message_4.getText();
                //muestra mensaje
                showMessages();
            }
        });
        message_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taMessages.isEnabled()){
                    messagesUA();
                }
                message = message_5.getText();
                //muestra mensaje
                showMessages();
            }
        });
        Message_6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(taMessages.isEnabled()){
                    messagesUA();
                }
                message = Message_6.getText();
                //muestra mensaje
                showMessages();
            }
        });
        bDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAdmin.upDown = "download";
                UserAdmin.startEstado = "Download";
                GroupsMySQL.messageMySQL();
            }
        });
        bUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAdmin.upDown = "upload";
                UserAdmin.startEstado = "Upload";
                messagesUA();
                GroupsMySQL.messageMySQL();
            }
        });
        taMessages.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int Max_Len = 500;
                char c = e.getKeyChar();
                int len = taMessages.getText().length();
                if (len < Max_Len){
                    return;
                }else{
                    UserAdmin.groupsEstado = "Too Long";
                    String user = new String(taMessages.getText());
                    user = user.substring(0, Max_Len-1);
                    taMessages.setText(user);
                }
                super.keyPressed(e);
            }
        });
    }

    private void showMessages(){
        lblStart.setText(message);
        switch (message) {
            case "Message 1":
                taMessages.setText(UserAdmin.message_1);
                break;
            case "Message 2":
                taMessages.setText(UserAdmin.message_2);
                break;
            case "Message 3":
                taMessages.setText(UserAdmin.message_3);
                break;
            case "Message 4":
                taMessages.setText(UserAdmin.message_4);
                break;
            case "Message 5":
                taMessages.setText(UserAdmin.message_5);
                break;
            case "Message 6":
                taMessages.setText(UserAdmin.message_6);
                break;
            case "Message 7":
                taMessages.setText(UserAdmin.message_7);
                break;
            case "Message 8":
                taMessages.setText(UserAdmin.message_8);
                break;
            case "Message 9":
                taMessages.setText(UserAdmin.message_9);
                break;
            case "Message 10":
                taMessages.setText(UserAdmin.message_10);
                break;
            case "Message 11":
                taMessages.setText(UserAdmin.message_11);
                break;
            case "Message 12":
                taMessages.setText(UserAdmin.message_12);
                break;
            default:
                System.out.println("Error en boton");
                break;
        }
    }

    private void messagesUA(){

        String a = taMessages.getText();
        String b = taMessages.getSelectedText();

        switch (message){
            case "Message 1":
                UserAdmin.message_1 = a;
                break;
            case "Message 2":
                UserAdmin.message_2 = a;
                break;
            case "Message 3":
                UserAdmin.message_3 = a;
                break;
            case "Message 4":
                UserAdmin.message_4 = a;
                break;
            case "Message 5":
                UserAdmin.message_5 = a;
                break;
            case "Message 6":
                UserAdmin.message_6 = a;
                break;
            case "Message 7":
                UserAdmin.message_7 = a;
                break;
            case "Message 8":
                UserAdmin.message_8 = a;
                break;
            case "Message 9":
                UserAdmin.message_9 = a;
                break;
            case "Message 10":
                UserAdmin.message_10 = a;
                break;
            case "Message 11":
                UserAdmin.message_11 = a;
                break;
            case "Message 12":
                UserAdmin.message_12 = a;
                break;
            default:
                System.out.println("error al cargar");
                break;
        }

    }
}
