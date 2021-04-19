package controller;

import static controller.MainController.filteredParts;
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
public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    private TextField idTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField stockTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    
    @FXML
    private TextField searchPartTxt;
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, Integer> partIdCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Integer> partStockCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    
    @FXML
    private TableView<Part> associatedPartTableView;
    @FXML
    private TableColumn<Part, Integer> associatedPartIdCol;
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;
    @FXML
    private TableColumn<Part, Integer> associatedPartStockCol;
    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;
    
    private ObservableList<Part> list = FXCollections.observableArrayList();
    
    /** Method gets information from a product and fills in all necessary information for ModifyProduct Form.
     * @param selectedProduct The product to retrieve information from.
     */
    void sendProduct (Product selectedProduct) {        
        idTxt.setText(String.valueOf(selectedProduct.getId()));
        nameTxt.setText(selectedProduct.getName());
        stockTxt.setText(String.valueOf(selectedProduct.getStock()));
        priceTxt.setText(String.valueOf(selectedProduct.getPrice()));
        maxTxt.setText(String.valueOf(selectedProduct.getMax()));
        minTxt.setText(String.valueOf(selectedProduct.getMin()));
        
        for (Part part : selectedProduct.getAllAssociatedParts()) {
            list.add(part);
        }
        
    }
    
    /** RUNTIME ERROR:
     * If part is not searched by ID, the code will assume it's searched by name.
     * It's corrected by utilizing the try-catch statement,
     * and the error will be detected within the first line of code inside the statement
     * @param event Action to delete selected Product
     */
    @FXML
    void onActionSearchPart(ActionEvent event) {
        //Resets filteredProducts list, otherwise filteredProducts will show all searched parts
        filteredParts.clear();
        //Assume product is searched by name
        boolean isString = true;
        
        //If product can be converted to an int, set isString to false
        try {
            int partId = Integer.parseInt(searchPartTxt.getText());
            isString = false;
            Inventory.lookupPart(partId);
        //Catch error
        } catch (NumberFormatException e) {
            //Product can't be searched by ID
        }
        
        //Executes only if the catch statement was used, since isString wasn't switched to false
        if (isString) {
            String partName = searchPartTxt.getText();
            Inventory.lookupPart(partName);
            System.out.println("Is String" + partName);
        }
        
        partTableView.setItems(filteredParts);
        
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    @FXML
    void onActionAddAssociatedPart(ActionEvent event) {
        Part selectedPart = (partTableView.getSelectionModel().getSelectedItem());
        
        //If part is selected, input to temp list
        for (Part part : Inventory.getAllParts()) {
            if (selectedPart == part){
                list.add(selectedPart);
                return;
            }
        }
        
        //No part was selected
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("No part selected");
        alert.setContentText("Please select a part to associate with product.");
        alert.showAndWait(); 
    }

    @FXML
    void onActionRemoveAssociatedPart(ActionEvent event) {
        Part part = associatedPartTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting " + part.getName() + ", click OK to confirm.");
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            list.remove(part);
        }              
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Min must be lower than Max");
        alert.setTitle("Invalid Min and Max values");
        int id = Integer.parseInt(idTxt.getText());
        int index = id - 1;
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(stockTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        
        if (min >= max) {
            alert.showAndWait();
        } else {
            Product product = new Product(id, name, price, stock, min, max);

            for (Part part : list) {
                product.addAssociatedPart(part);
            }

            Inventory.updateProduct(index, product);

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partTableView.setItems(Inventory.getAllParts()); 
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        associatedPartTableView.setItems(list);
        associatedPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }   
}
