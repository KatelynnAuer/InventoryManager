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
import model.Part;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

/**
 * FXML Controller
 *
 * @author Katelynn Auer
 */
public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;
    
     @FXML
    private RadioButton inHouseBtn;
    @FXML
    private ToggleGroup Toggle;
    @FXML
    private RadioButton outsourcedBtn;
    @FXML
    private TextField partIdTxt;
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
   
    /** Method gets information from a part and fills in all necessary information for ModifyPart Form
     * @param selectedPart The part to retrieve information from
     */
    public void sendPart (Part selectedPart) {
        partIdTxt.setText(String.valueOf(selectedPart.getId()));
        nameTxt.setText(selectedPart.getName());
        invTxt.setText(String.valueOf(selectedPart.getStock()));
        priceTxt.setText(String.valueOf(selectedPart.getPrice()));
        maxTxt.setText(String.valueOf(selectedPart.getMax()));
        minTxt.setText(String.valueOf(selectedPart.getMin()));
        
        if (selectedPart instanceof InHouse) {
            Toggle.selectToggle(inHouseBtn);
            fieldLbl.setText("Machine ID");
            TBDTxt.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        } else {
            Toggle.selectToggle(outsourcedBtn);
            fieldLbl.setText("Company Name");
            TBDTxt.setText(((Outsourced) selectedPart).getCompanyName());
        }
        
    }

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
    void onActionSaveModifyPart(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Min must be lower than Max");
        alert.setTitle("Invalid Min and Max values");
        
        int id = Integer.parseInt(partIdTxt.getText());
        int index = id - 1;
        String name = nameTxt.getText();
        double price = Double.parseDouble(priceTxt.getText());
        int stock = Integer.parseInt(invTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        String field = TBDTxt.getText();
        
        if (min >= max) {
            alert.showAndWait();
        } else {
            if (inHouseBtn.isSelected()) {
                int machineId = Integer.parseInt(field);
                Inventory.updatePart(index, new InHouse(id, name, price, stock, min, max, machineId));
            } else {
                Inventory.updatePart(index, new Outsourced(id, name, price, stock, min, max, field));
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
