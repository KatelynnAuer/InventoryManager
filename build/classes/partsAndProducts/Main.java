package partsAndProducts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Inventory;
import model.Product;
import model.InHouse;
import model.Outsourced;

/** FUTURE ENHANCEMENT:
 * 1) Allow the system to provide real-time inventory count.
 * 2) Provide verbal error feedback.
 * 3) Allow auto-order of parts when inventory falls to a certain threshold.
 * @author Katelynn Auer
 */
public class Main extends Application {

    @Override
    public void start (Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
    
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {

       Inventory.addPart(new Outsourced(1, "CPU", 2.50, 22, 43, 10, "Kate's Electronics"));
       Inventory.addPart(new InHouse(2, "Hard Drive", 89.99, 36, 25, 100, 100)); 
       Inventory.addPart(new Outsourced(3, "GPU", 3.80, 27, 25, 100, "Kate's Electronics"));
       Inventory.addPart(new InHouse(4, "Motherboard", 175.00, 45, 100, 200, 101));
       Inventory.addProduct(new Product(1, "Headset", 129.99, 93, 1, 10)); 
       Inventory.addProduct(new Product(2, "Mouse", 69.99, 71, 2, 10)); 
       Inventory.addProduct(new Product(3, "Keyboard", 139.99, 79, 5, 50)); 
       Inventory.addProduct(new Product(4, "Monitor", 299.99, 18, 2, 10)); 
       
       Application.launch(args);
    }
    
}
