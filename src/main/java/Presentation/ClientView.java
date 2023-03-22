package Presentation;

import Business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;


public class ClientView extends JFrame{

    private JPanel pane = new JPanel(new GridBagLayout());
    private JPanel pane2 = new JPanel(new GridBagLayout());
    private JPanel pane3 = new JPanel(new GridBagLayout());
    private JLabel search = new JLabel("Search:");
    private JLabel title = new JLabel("Title:");
    private JLabel rating = new JLabel("Rating:");
    private JLabel calories = new JLabel("Calories:");
    private JLabel protein = new JLabel("Protein:");
    private JLabel fat = new JLabel("Fat:");
    private JLabel sodium = new JLabel("Sodium:");
    private JLabel price = new JLabel("Price:");
    private JLabel order = new JLabel("Order:");
    private JLabel nameL = new JLabel("Name:");
    private JTextField titleField = new JTextField(30);
    private JTextField ratingField = new JTextField(5);
    private JTextField caloriesField = new JTextField(5);
    private JTextField proteinField = new JTextField(5);
    private JTextField fatField = new JTextField(5);
    private JTextField sodiumField = new JTextField(5);
    private JTextField priceField = new JTextField(5);
    private JTextField orderField = new JTextField(30);
    private JButton searchButton = new JButton("Search");
    private JButton addButton = new JButton("Add");
    private JButton orderButton = new JButton("Order");
    private JButton clearButton = new JButton("Clear");
    GridBagConstraints gc = new GridBagConstraints();

    private JTable table;
    private DefaultTableModel model;
    private JTable table2;
    private DefaultTableModel model2;
    Controller controller;

    public ClientView(String name, Controller controller) {

        super(name);
        this.controller = controller;
        this.setPreferredSize(new Dimension(1400, 1400));
        Container c = getContentPane();

        model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(table);

        model2 = new DefaultTableModel();
        JTable table2 = new JTable(model2);
        table2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        JScrollPane scrollPane2 = new JScrollPane(table2);

        model.addColumn("Title");
        model.addColumn("Rating");
        model.addColumn("Calories");
        model.addColumn("Protein");
        model.addColumn("Fat");
        model.addColumn("Sodium");
        model.addColumn("Price");
        model2.addColumn("Title");
        model2.addColumn("Rating");
        model2.addColumn("Calories");
        model2.addColumn("Protein");
        model2.addColumn("Fat");
        model2.addColumn("Sodium");
        model2.addColumn("Price");

        pane.setLayout(new BorderLayout());
        pane.add(scrollPane, BorderLayout.CENTER);
        pane3.setLayout(new BorderLayout());
        pane3.add(scrollPane2, BorderLayout.PAGE_END);

        gc.gridx = 1;
        gc.gridy = 1;
        pane2.add(search, gc);
        gc.gridx = 2;
        gc.gridy = 1;
        pane2.add(titleField, gc);
        gc.gridx = 3;
        gc.gridy = 1;
        pane2.add(ratingField, gc);
        gc.gridx = 4;
        gc.gridy = 1;
        pane2.add(caloriesField, gc);
        gc.gridx = 5;
        gc.gridy = 1;
        pane2.add(proteinField, gc);
        gc.gridx = 6;
        gc.gridy = 1;
        pane2.add(fatField, gc);
        gc.gridx = 7;
        gc.gridy = 1;
        pane2.add(sodiumField, gc);
        gc.gridx = 8;
        gc.gridy = 1;
        pane2.add(priceField, gc);
        gc.gridx = 9;
        gc.gridy = 1;
        searchButton.addActionListener(controller);
        pane2.add(searchButton, gc);

        gc.gridx = 2;
        gc.gridy = 0;
        pane2.add(title, gc);
        gc.gridx = 3;
        gc.gridy = 0;
        pane2.add(rating, gc);
        gc.gridx = 4;
        gc.gridy = 0;
        pane2.add(calories, gc);
        gc.gridx = 5;
        gc.gridy = 0;
        pane2.add(protein, gc);
        gc.gridx = 6;
        gc.gridy = 0;
        pane2.add(fat, gc);
        gc.gridx = 7;
        gc.gridy = 0;
        pane2.add(sodium, gc);
        gc.gridx = 8;
        gc.gridy = 0;
        pane2.add(price, gc);

        gc.gridx = 2;
        gc.gridy = 3;
        pane2.add(nameL, gc);
        gc.gridx = 1;
        gc.gridy = 4;
        pane2.add(order, gc);
        gc.gridx = 2;
        gc.gridy = 4;
        pane2.add(orderField, gc);
        gc.gridx = 3;
        gc.gridy = 4;
        addButton.addActionListener(controller);
        pane2.add(addButton, gc);
        gc.gridx = 4;
        gc.gridy = 4;
        orderButton.addActionListener(controller);
        pane2.add(orderButton, gc);
        gc.gridx = 5;
        gc.gridy = 4;
        clearButton.addActionListener(controller);
        pane2.add(clearButton, gc);

        pane.setBounds(10,10,1400,400);
        pane2.setBounds(100,500,1400,200);
        pane3.setBounds(700,10,1400,400);
        c.add(pane);
        c.add(pane2);
        c.add(pane3);
    }

    public void setTable(Set<MenuItem> products){

        int rowCount = model.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        for(MenuItem item: products) {
            model.addRow(new Object[]{item.getTitle(), String.valueOf(item.getRating()), String.valueOf(item.getCalories()), String.valueOf(item.getProtein()),
                    String.valueOf(item.getFat()), String.valueOf(item.getSodium()), String.valueOf(item.getPrice())});
        }
    }
    public void setTableOrder(Set<MenuItem> products){

        int rowCount = model2.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model2.removeRow(i);
        }
        for(MenuItem item: products) {
            model2.addRow(new Object[]{item.getTitle(), String.valueOf(item.getRating()), String.valueOf(item.getCalories()), String.valueOf(item.getProtein()),
                    String.valueOf(item.getFat()), String.valueOf(item.getSodium()), String.valueOf(item.getPrice())});
        }
    }

    public void resetTableOrder(){

        int rowCount = model2.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            model2.removeRow(i);
        }
    }

    public JButton getSearch(){
        return searchButton;
    }
    public JButton getAdd(){
        return addButton;
    }
    public JButton getOrderButton(){
        return orderButton;
    }
    public JButton getClear(){
        return clearButton;
    }

    public String getTitle() {
        return titleField.getText();
    }
    public double getRating() {
        if (ratingField.getText().equals("")) {
            return -1;
        }
        return Double.valueOf(ratingField.getText());
    }
    public int getCalories() {
        if (caloriesField.getText().equals("")) {
            return -1;
        }
        return Integer.valueOf(caloriesField.getText());
    }
    public int getProtein() {
        if (proteinField.getText().equals("")) {
            return -1;
        }
        return Integer.valueOf(proteinField.getText());
    }
    public int getFat() {
        if (fatField.getText().equals("")) {
            return -1;
        }
        return Integer.valueOf(fatField.getText());
    }
    public int getSodium() {
        if (sodiumField.getText().equals("")) {
            return -1;
        }
        return Integer.valueOf(sodiumField.getText());
    }
    public int getPrice() {
        if (priceField.getText().equals("")) {
            return -1;
        }
        return Integer.valueOf(priceField.getText());
    }
    public String getOrder() {
        return orderField.getText();
    }


}






