package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import static javafx.scene.control.ButtonType.OK;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller
 *
 * @author Katelynn Auer
 */
public class MainController implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TextField searchPartTxt;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partInvLevelCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TextField searchProductTxt;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Integer> productInvLevelCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    
    public static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    public static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    
    @FXML
    void onActionSearchPart(ActionEvent event) {
        //Resets filteredParts list, otherwise filteredParts will show all searched parts
        filteredParts.clear();
        //Assume part is searched by name
        boolean isString = true;
        
        //If part can be converted to an int, set isString to false
        try {
            int partId = Integer.parseInt(searchPartTxt.getText());
            isString = false;
            Inventory.lookupPart(partId);   
        //Catch error
        } catch (NumberFormatException e) {
            //Part can't be searched by ID
        }
        
        //Executes only if the catch statement was used, since isString wasn't switched to false
        if (isString) {
            String partName = searchPartTxt.getText();
            Inventory.lookupPart(partName);
            System.out.println("Is String" + partName);
        }

        //Show filteredParts
        partTableView.setItems(filteredParts);
        
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }
    
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
            loader.load();

            ModifyPartController ModPartController = loader.getController();
            ModPartController.sendPart(partTableView.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Part not selected");
            errorAlert.setContentText("Please select a Part to modify.");
            errorAlert.showAndWait();
        }
        
    }

    @FXML
    void onActionDeletePart(ActionEvent event) {
        try {
            Part deletePart = partTableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Part Confirmation");
            alert.setContentText("Deleting " + deletePart.getName() + ", click OK to confirm.");
            Optional<ButtonType> result = alert.showAndWait();
        
            if (result.isPresent() && result.get() == OK) {
                //Before deletion, get part's id
                int newId = deletePart.getId();
                Inventory.deletePart(partTableView.getSelectionModel().getSelectedItem());
                //Variable to check if ids are continuos
                int idCheck = 1;

                //For loop to update parts that are out of order from part's deletion
                for (Part part : Inventory.getAllParts()) {
                    /*The If statement starts applying new ids when it has found the first
                    * part's id that is out of order.
                    */
                    if (idCheck != part.getId()) {
                        part.setId(newId);
                        newId++;
                    }
                    idCheck++;
                }
            }
        } catch (NullPointerException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Part not selected");
            errorAlert.setContentText("Please select a Part to delete.");
            errorAlert.showAndWait();
        }
    }

    @FXML
    void onActionSearchProduct(ActionEvent event) {
        //Resets filteredProducts list, otherwise filteredProducts will show all searched parts
        filteredProducts.clear();
        //Assume product is searched by name
        boolean isString = true;
        
        //If product can be converted to an int, set isString to false
        try {
            int productId = Integer.parseInt(searchProductTxt.getText());
            isString = false;
            Inventory.lookupProduct(productId);
        //Catch error
        } catch (NumberFormatException e) {
            //Product can't be searched by ID
        }
        
        //Executes only if the catch statement was used, since isString wasn't switched to false
        if (isString) {
            String productName = searchProductTxt.getText();
            Inventory.lookupProduct(productName);
        }
        
        //Show filteredParts
        productTableView.setItems(filteredProducts);
        
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        
        //Checks to see if any product is selected
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
            loader.load();

            ModifyProductController ModProductController = loader.getController();
            //Will give error if nothing is selected
            ModProductController.sendProduct(productTableView.getSelectionModel().getSelectedItem());

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product not selected");
            alert.setContentText("Please select a Product to modify.");
            alert.showAndWait();
        }
    }

    /** RUNTIME ERROR:
     * If no Product is selected for deletion, prompt error message.
     * It's corrected by utilizing the try-catch statement,
     * and the error will be detected within the first line of code inside the statement
     * @param event Action to delete selected Product
     */
    @FXML
    public void onActionDeleteProduct(ActionEvent event) {
        Product productDelete = productTableView.getSelectionModel().getSelectedItem();
        
        //First line of code in the try statement will indicate user didn't select a product
        try {
            //Verify there's no associated parts
            if (productDelete.getAllAssociatedParts().isEmpty()) {
                
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Delete product confirmation");
                confirmationAlert.setContentText("Deleting " + productDelete.getName() + ", click OK to confirm.");
                Optional<ButtonType> result = confirmationAlert.showAndWait();
                
                //If user clicks OK, product will be deleted
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    //Before deletion, get product's id
                    int newId = productDelete.getId();
                    Inventory.deleteProduct(productDelete);
                    //Variable to check if ids are continuos
                    int idCheck = 1;

                    //For loop to update parts that are out of order from part's deletion
                    for (Product product : Inventory.getAllProducts()) {
                        /*The If statement starts applying new ids when it has found the first
                        * part's id that is out of order.
                        */
                        if (idCheck != product.getId()) {
                            product.setId(newId);
                            newId++;
                        }
                        idCheck++;
                    }
                } else {
                    //User cancelled deletion.
                }  
            } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Associated part(s) detected");
            errorAlert.setContentText("Can't delete product. Please remove all associated parts.");
            errorAlert.showAndWait();
            }
        } catch (NullPointerException e) {
            Alert selectionErrorAlert = new Alert(Alert.AlertType.ERROR);
            selectionErrorAlert.setTitle("Product not selected");
            selectionErrorAlert.setContentText("Please select a Product to delete.");
            selectionErrorAlert.showAndWait();
        }
    }
    
    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partTableView.setItems(Inventory.getAllParts());
        
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productTableView.setItems(Inventory.getAllProducts());
        
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }       
}
