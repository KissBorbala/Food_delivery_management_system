package Presentation;

import Business.MenuItem;
import Business.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

public class EmployeeView extends JFrame implements Observer{

    private JPanel pane = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JLabel title = new JLabel("employee:");
    private JLabel username = new JLabel("Username:");
    private JLabel password = new JLabel("password");
    private JTextField userField = new JTextField(20);
    private JTextField passField = new JTextField(20);
    private JButton login = new JButton("Log in");
    private JButton register = new JButton("Register");

    private JTable table;
    private DefaultTableModel model;

    Controller controller;

    public EmployeeView(String name, Controller controller) {

        super(name);

        model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        model.addColumn("Order id");
        model.addColumn("Date");
        model.addColumn("Time");

        pane.setLayout(new BorderLayout());
        pane.add(scrollPane, BorderLayout.CENTER);

        this.controller = controller;
        this.add(pane);
    }


    @Override
    public void update(Order order) {

        model.addRow(new Object[]{order.getId(), order.getDate(), order.getTime()});

    }
}






