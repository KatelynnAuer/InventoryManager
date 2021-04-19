package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Inventory;
import model.InHouse;
import model.Outsourced;

/**
 * FXML Controller
 *
 * @author Katelynn Auer
 */
public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;
    
    @FXML
    private RadioButton inHouseBtn;
    @FXML
    private ToggleGroup Toggle;
    @FXML
    private RadioButton outsourcedBtn;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField TBDTxt;
    @FXML
    private TextField minTxt; 
    @FXML
    private Label fieldLbl;
    
    @FXML
    void onActionFieldMachineID(ActionEvent event) {
        fieldLbl.setText("Machine ID");
    }

    @FXML
    void onActionFieldCompanyName(ActionEvent event) {
        fieldLbl.setText("Company Name");
    }

    /** RUNTIME ERROR:
     * If min is not lower than max, prompt error message.
     * It's corrected by utilizing the if-else statement,
     * and if min is not lower than max, the Part won't save
     * @param event Action to save Part
     */
    @FXML
    public void onActionSavePart(ActionEvent event) throws IOException {
        int index = Inventory.getAllParts().size();
        int id = index + 1;
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        String field = TBDTxt.getText();
        
        if (min >= max) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid values. Min must be lower than Max");
            alert.setTitle("Invalid min and max values");
            alert.showAndWait();
        } else {
            if (inHouseBtn.isSelected()) {
                int machineId = Integer.parseInt(field);
                Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
            } else {
                Inventory.addPart(new Outsourced(id, name, price, stock, min, max, field));
            }

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
        
    }   
    
}
