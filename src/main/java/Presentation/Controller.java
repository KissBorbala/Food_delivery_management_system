package Presentation;

import Business.DeliveryService;
import Business.MenuItem;

import Data.Serializator;
import Data.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Controller implements ActionListener {

    private LoginView loginView;
    private ClientView clientView;
    private AdminView adminView;
    private EmployeeView employeeView;

    private Set<MenuItem> products;
    private MenuItem order;
    private Set<MenuItem> finalOrder;
    private DeliveryService ds;
    private User current;

    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private int price;


    public Controller() {
        User user1 = new User("admin", "1234", 0);
        User user2 = new User("emp", "1234", 1);
        User user3 = new User("client", "1234", 2);
        finalOrder = new HashSet<>();

        ds = Serializator.deserializeDeliveryService();

        ds.addUser(user1);
        ds.addUser(user2);
        ds.addUser(user3);
    }

    public void setLoginView(LoginView v) {
        this.loginView = v;
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == loginView.getRegister()) {
            User user = new User(loginView.getUsername(), loginView.getPassword(), 2);
            if (ds.addUser(user)) {
                current = user;
                clientView = new ClientView("Client", this);
                ds.setClientId(user.getId());
                clientView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                clientView.pack();
                clientView.setVisible(true);
                clientView.setTable(ds.getProducts());
            }


        }
        else if (source == loginView.getLogin()) {
            Set<User> dsUser = ds.getUsers();
            for(User user: dsUser) {
                if (user.getUsername().equals(loginView.getUsername()))  {
                    if (user.getPassword().equals(loginView.getPassword())) {
                        System.out.println("login successfully\n");
                        if (user.getStatus() == 0) {
                            adminView = new AdminView("Admin", this);
                            adminView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            adminView.pack();
                            adminView.setVisible(true);
                            adminView.setTable(ds.getProducts());
                        }
                        else if (user.getStatus() == 1) {
                            employeeView = new EmployeeView("Employee", this);
                            employeeView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            employeeView.pack();
                            employeeView.setVisible(true);

                            ds.addObserver(employeeView);
                        }
                        else {
                            clientView = new ClientView("Client", this);
                            ds.setClientId(user.getId());
                            clientView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            clientView.pack();
                            clientView.setVisible(true);
                            current = user;
                            clientView.setTable(ds.getProducts());
                        }
                    }
                    else {
                        System.out.println("wrong password\n");
                    }
                }
            }
        }
        else if (adminView != null && source == adminView.getImport()) {
            products = ds.importProducts();
            adminView.setTable(products);
        }
        else if (adminView != null && source == adminView.getAdd()) {
            title = adminView.getTitle();
            rating = adminView.getRating();
            calories = adminView.getCalories();
            protein = adminView.getProtein();
            fat = adminView.getFat();
            sodium = adminView.getSodium();
            price = adminView.getPrice();

            products = ds.addProduct(title, rating, calories, protein, fat, sodium, price);
            if (clientView != null) {
                clientView.setTable(products);
            }
            clientView.setTable(products);
        }
        else if (adminView != null && source == adminView.getEdit()) {
            title = adminView.getTitle();
            rating = adminView.getRating();
            calories = adminView.getCalories();
            protein = adminView.getProtein();
            fat = adminView.getFat();
            sodium = adminView.getSodium();
            price = adminView.getPrice();

            products = ds.editProduct(title, rating, calories, protein, fat, sodium, price);
            if (clientView != null) {
                clientView.setTable(products);
            }
            adminView.setTable(products);
        }
        else if (adminView != null && source == adminView.getDelete()) {
            title = adminView.getTitle();

            products = ds.deleteProduct(title);
            if (clientView != null) {
                clientView.setTable(products);
            }
            adminView.setTable(products);
        }
        else if (adminView != null && source == adminView.getAddComp()) {
            title = adminView.getCompTitle();

            MenuItem item = ds.searchProduct(title);
            if (item != null) {
                ds.addComposite(item);
                adminView.setCompTable(item);
            }
            else {
                System.out.println("Product not found\n");
            }

        }
        else if (adminView != null && source == adminView.getCreateComp()) {
            title = adminView.getCompTitle();

            ds.createComposite(title);
            adminView.resetCompTable();
        }
        else if (adminView != null && source == adminView.getTimeIntervalButton()) {

            ds.reportTimeInterval(adminView.getStartHour(), adminView.getEndHour());
        }
        else if (adminView != null && source == adminView.getMoreButton()) {

            ds.reportMoreThan(adminView.getMoreThan());
        }
        else if (adminView != null && source == adminView.getClientButton()) {

            ds.reportClient(adminView.getClient(), adminView.getAmount());
        }
        else if (adminView != null && source == adminView.getDateButton()) {

            ds.reportDate(adminView.getDate());
        }
        else if (source == clientView.getSearch()) {
            title = clientView.getTitle();
            rating = clientView.getRating();
            calories = clientView.getCalories();
            protein = clientView.getProtein();
            fat = clientView.getFat();
            sodium = clientView.getSodium();
            price = clientView.getPrice();

            products = ds.searchProducts(title, rating, calories, protein, fat, sodium, price);
            if (products != null ) {
                clientView.setTable(products);
            }
            else {
                System.out.println("Product not found\n");
            }
        }
        else if (source == clientView.getAdd()) {
            title = clientView.getOrder();
            System.out.println(title);
            order = ds.searchProduct(title);
            if (order != null) {
                finalOrder.add(order);
                clientView.setTableOrder(finalOrder);
            }
            else {
                System.out.println("Product not found\n");
            }

        }
        else if (source == clientView.getClear()) {
            clientView.resetTableOrder();
            finalOrder.removeAll(finalOrder);
        }
        else if (source == clientView.getOrderButton()) {
            clientView.resetTableOrder();
            ds.addOrder(finalOrder, current);
            finalOrder.removeAll(finalOrder);
        }
    }

    public void serialize() {
        Serializator.serializeDeliveryService(ds);
    }
}
