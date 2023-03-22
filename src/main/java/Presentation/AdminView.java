package Presentation;

import Business.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;


public class AdminView extends JFrame{

    private JPanel pane = new JPanel(new GridBagLayout());
    private JPanel pane2 = new JPanel(new GridBagLayout());
    private JPanel pane3 = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    private JLabel add = new JLabel("Add product:");
    private JLabel title = new JLabel("Title:");
    private JLabel rating = new JLabel("Rating:");
    private JLabel calories = new JLabel("Calories:");
    private JLabel protein = new JLabel("Protein:");
    private JLabel fat = new JLabel("Fat:");
    private JLabel sodium = new JLabel("Sodium:");
    private JLabel price = new JLabel("Price:");
    private JLabel composite = new JLabel("Composite:");
    private JLabel start = new JLabel("Start hour:");
    private JLabel end = new JLabel("End hour:");
    private JLabel moreThan = new JLabel("Ordered more than:");
    private JLabel client = new JLabel("Ordered more times than:");
    private JLabel amount = new JLabel("Higher than amount:");
    private JLabel date = new JLabel("Ordered on the date:");

    private JTextField titleField = new JTextField(30);
    private JTextField ratingField = new JTextField(5);
    private JTextField caloriesField = new JTextField(5);
    private JTextField proteinField = new JTextField(5);
    private JTextField fatField = new JTextField(5);
    private JTextField sodiumField = new JTextField(5);
    private JTextField priceField = new JTextField(5);
    private JTextField orderField = new JTextField(30);
    private JTextField compositeTitle = new JTextField(30);
    private JTextField startField = new JTextField(5);
    private JTextField endField = new JTextField(5);
    private JTextField moreField = new JTextField(5);
    private JTextField clientField = new JTextField(5);
    private JTextField amountField = new JTextField(5);
    private JTextField dateField = new JTextField(30);

    private JButton importButton = new JButton("Import from file");
    private JButton addButton = new JButton("Add product");
    private JButton editButton = new JButton("Edit product");
    private JButton deleteButton = new JButton("Delete product");
    private JButton addCompButton = new JButton("Add composite");
    private JButton createCompButton = new JButton("Create composite");
    private JButton timeIntervalButton = new JButton("Time interval report");
    private JButton moreButton = new JButton("Products report");
    private JButton clientButton = new JButton("Clients report");
    private JButton dateButton = new JButton("Date report");

    private JTable table;
    private DefaultTableModel model;
    private DefaultTableModel model2;
    Controller controller;


    public AdminView(String name, Controller controller) {

        super(name);
        this.controller = controller;
        this.setPreferredSize(new Dimension(1300, 750));
        Container gc = getContentPane();

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

        pane2.setLayout(new BorderLayout());
        pane2.add(scrollPane, BorderLayout.PAGE_START);
        pane3.setLayout(new BorderLayout());
        pane3.add(scrollPane2, BorderLayout.PAGE_END);


        c.gridx = 4;
        c.gridy = 0;
        pane.add(importButton, c);
        importButton.addActionListener(controller);
        c.gridx = 2;
        c.gridy = 2;
        pane.add(titleField, c);
        c.gridx = 3;
        c.gridy = 2;
        pane.add(ratingField, c);
        c.gridx = 4;
        c.gridy = 2;
        pane.add(caloriesField, c);
        c.gridx = 5;
        c.gridy = 2;
        pane.add(proteinField, c);
        c.gridx = 6;
        c.gridy = 2;
        pane.add(fatField, c);
        c.gridx = 7;
        c.gridy = 2;
        pane.add(sodiumField, c);
        c.gridx = 8;
        c.gridy = 2;
        pane.add(priceField, c);
        c.gridx = 3;
        c.gridy = 3;
        addButton.addActionListener(controller);
        pane.add(addButton, c);
        c.gridx = 4;
        c.gridy = 3;
        editButton.addActionListener(controller);
        pane.add(editButton, c);
        c.gridx = 5;
        c.gridy = 3;
        deleteButton.addActionListener(controller);
        pane.add(deleteButton, c);

        c.gridx = 2;
        c.gridy = 1;
        pane.add(title, c);
        c.gridx = 3;
        c.gridy = 1;
        pane.add(rating, c);
        c.gridx = 4;
        c.gridy = 1;
        pane.add(calories, c);
        c.gridx = 5;
        c.gridy = 1;
        pane.add(protein, c);
        c.gridx = 6;
        c.gridy = 1;
        pane.add(fat, c);
        c.gridx = 7;
        c.gridy = 1;
        pane.add(sodium, c);
        c.gridx = 8;
        c.gridy = 1;
        pane.add(price, c);

        c.gridx = 3;
        c.gridy = 4;
        pane.add(title, c);

        c.gridx = 2;
        c.gridy = 5;
        pane.add(composite, c);
        c.gridx = 3;
        c.gridy = 5;
        pane.add(compositeTitle, c);
        c.gridx = 4;
        c.gridy = 5;
        addCompButton.addActionListener(controller);
        pane.add(addCompButton, c);
        c.gridx = 5;
        c.gridy = 5;
        createCompButton.addActionListener(controller);
        pane.add(createCompButton, c);

        c.gridx = 2;
        c.gridy = 6;
        pane.add(start, c);
        c.gridx = 3;
        c.gridy = 6;
        pane.add(startField, c);
        c.gridx = 4;
        c.gridy = 6;
        pane.add(end, c);
        c.gridx = 5;
        c.gridy = 6;
        pane.add(endField, c);
        c.gridx = 6;
        c.gridy = 6;
        timeIntervalButton.addActionListener(controller);
        pane.add(timeIntervalButton, c);

        c.gridx = 2;
        c.gridy = 7;
        pane.add(moreThan, c);
        c.gridx = 3;
        c.gridy = 7;
        pane.add(moreField, c);
        c.gridx = 6;
        c.gridy = 7;
        moreButton.addActionListener(controller);
        pane.add(moreButton, c);

        c.gridx = 2;
        c.gridy = 8;
        pane.add(client, c);
        c.gridx = 3;
        c.gridy = 8;
        pane.add(clientField, c);
        c.gridx = 4;
        c.gridy = 8;
        pane.add(amount, c);
        c.gridx = 5;
        c.gridy = 8;
        pane.add(amountField, c);
        c.gridx = 6;
        c.gridy = 8;
        clientButton.addActionListener(controller);
        pane.add(clientButton, c);

        c.gridx = 2;
        c.gridy = 9;
        pane.add(date, c);
        c.gridx = 3;
        c.gridy = 9;
        pane.add(dateField, c);
        c.gridx = 6;
        c.gridy = 9;
        dateButton.addActionListener(controller);
        pane.add(dateButton, c);



        pane2.setBounds(10,10,1200,200);
        pane3.setBounds(10,215,1200,200);
        pane.setBounds(10,420,1200,300);

        gc.add(pane);
        gc.add(pane2);
        gc.add(pane3);

    }

    public JButton getImport(){
        return importButton;
    }
    public JButton getAdd(){
        return addButton;
    }
    public JButton getEdit(){
        return editButton;
    }
    public JButton getDelete(){
        return deleteButton;
    }
    public JButton getAddComp(){
        return addCompButton;
    }
    public JButton getCreateComp(){
        return createCompButton;
    }
    public JButton getTimeIntervalButton(){
        return timeIntervalButton;
    }
    public JButton getMoreButton(){
        return moreButton;
    }
    public JButton getClientButton(){
        return clientButton;
    }
    public JButton getDateButton(){
        return dateButton;
    }
    public double getRating() {
        return Double.valueOf(ratingField.getText());
    }
    public int getCalories() {
        return Integer.valueOf(caloriesField.getText());
    }
    public int getProtein() {
        return Integer.valueOf(proteinField.getText());
    }
    public int getFat() {
        return Integer.valueOf(fatField.getText());
    }
    public int getSodium() {
        return Integer.valueOf(sodiumField.getText());
    }
    public int getPrice() {
        return Integer.valueOf(priceField.getText());
    }
    public String getTitle() {
        return titleField.getText();
    }
    public String getCompTitle() { return compositeTitle.getText(); }
    public String getDate() { return dateField.getText(); }
    public int getStartHour() { return Integer.valueOf(startField.getText()); }
    public int getEndHour() { return Integer.valueOf(endField.getText()); }
    public int getMoreThan() { return Integer.valueOf(moreField.getText()); }
    public int getClient() { return Integer.valueOf(clientField.getText()); }
    public int getAmount() { return Integer.valueOf(amountField.getText()); }


    public void setTable(Set<Business.MenuItem> products){

        model.setRowCount(0);
        for(MenuItem item: products) {
            model.addRow(new Object[]{item.getTitle(), String.valueOf(item.getRating()), String.valueOf(item.getCalories()), String.valueOf(item.getProtein()),
                    String.valueOf(item.getFat()), String.valueOf(item.getSodium()), String.valueOf(item.getPrice())});
        }
    }

    public void setCompTable(MenuItem item){

        model2.addRow(new Object[]{item.getTitle(), String.valueOf(item.getRating()), String.valueOf(item.getCalories()), String.valueOf(item.getProtein()),
                    String.valueOf(item.getFat()), String.valueOf(item.getSodium()), String.valueOf(item.getPrice())});
    }

    public void resetCompTable(){

        model2.setRowCount(0);
    }



}






