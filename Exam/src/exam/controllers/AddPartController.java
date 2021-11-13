package exam.controllers;

import exam.Main;
import exam.objects.InHouse;
import exam.objects.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddPartController extends Controller implements Initializable {
    public TextField machineId;
    public TextField name;
    public TextField minimum;
    public TextField inventoryCount;
    public TextField maximum;
    public TextField price;
    public Label machineIdLabel;
    public RadioButton inHouseBtn;
    public RadioButton outsourcedBtn;
    public Label errorLabel;

    private boolean inHouse = true;

    @FXML
    public void addPart(ActionEvent e){
        try{
            double ppu = parseDouble(price.getText());
            int stock = parseInt(inventoryCount.getText());
            int max = parseInt(maximum.getText());
            int min = parseInt(minimum.getText());

            if(max > min){
                if(inHouse){
                    InHouse newPart = new InHouse(0, name.getText(), ppu, stock, min, max);
                    newPart.setMachineId(parseInt(machineId.getText()));

                    Main.mainInventory.addPart(newPart);
                } else {
                    Outsourced newPart = new Outsourced(0, name.getText(), ppu, stock, min, max);
                    newPart.setCompanyName(machineId.getText());

                    Main.mainInventory.addPart(newPart);
                }
                openWindow("main");
                closeWindow(e);
            } else {
                errorLabel.setText("Maximum cannot be less than minimum.");
            }
        } catch (Exception ex){
            errorLabel.setText("An error occurred. Please try again later.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void toggleInHouse(ActionEvent e) {
        if(inHouseBtn.isSelected())
        {
            inHouse = true;
            machineIdLabel.setText("Machine ID");
        }

        if(outsourcedBtn.isSelected())
        {
            inHouse = false;
            machineIdLabel.setText("Company Name");
        }
    }
}
