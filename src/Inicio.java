import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextArea textArea;
    public Inicio() {
        bSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.jpSettings();
            }
        });

        bGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.jpGroups();
            }
        });
    }
}
