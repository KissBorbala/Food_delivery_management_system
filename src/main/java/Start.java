import Business.DeliveryService;
import Data.Serializator;
import Presentation.Controller;
import Presentation.LoginView;

import javax.swing.*;
import java.sql.SQLException;

public class Start {

    public static void main(String[] args) throws SQLException {


        Controller controller = new Controller();
        JFrame loginFrame = new LoginView("Login", controller);
        controller.setLoginView((LoginView) loginFrame);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);

    }
}
