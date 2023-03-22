package Presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LoginView extends JFrame{

    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JLabel title = new JLabel("Log in:");
    private JLabel username = new JLabel("Username:");
    private JLabel password = new JLabel("password");
    private JTextField userField = new JTextField(20);
    private JTextField passField = new JTextField(20);
    private JButton login = new JButton("Log in");
    private JButton register = new JButton("Register");
    Controller controller;

    public LoginView(String name, Controller controller) {

        super(name);
        this.controller = controller;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(title, c);
        c.gridx = 0;
        c.gridy = 2;
        pane.add(username, c);
        c.gridx = 2;
        c.gridy = 2;
        pane.add(userField, c);
        c.gridx = 0;
        c.gridy = 3;
        pane.add(password, c);
        c.gridx = 2;
        c.gridy = 3;
        pane.add(passField, c);
        c.gridx = 1;
        c.gridy = 4;
        pane.add(login, c);
        login.addActionListener(controller);
        c.gridx = 2;
        c.gridy = 4;
        pane.add(register, c);
        register.addActionListener(controller);
        this.add(pane);

        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                controller.serialize();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public String getUsername() {
        return userField.getText();
    }
    public String getPassword() {
        return passField.getText();
    }

    public JButton getRegister(){
        return register;
    }
    public JButton getLogin(){
        return login;
    }


}






