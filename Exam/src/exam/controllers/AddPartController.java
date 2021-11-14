package exam.controllers;

import exam.Main;
import exam.objects.InHouse;
import exam.objects.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class AddPartController extends Controller {
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

    /**
     * Parse and create a new part based on provided input.
     * LOGICAL ERROR: I used the wrong comparator and nothing would work. It took me a minute to figure out why.
     * @param e ActionEvent
     */
    @FXML
    public void addPart(ActionEvent e){
        try{
            double ppu = parseDouble(price.getText());
            int stock = parseInt(inventoryCount.getText());
            int max = parseInt(maximum.getText());
            int min = parseInt(minimum.getText());

            if(max > min && max >= stock && stock >= min){
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
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Max must be more than min. Stock must be less than max, but more than min.");
                a.show();
            }
        } catch (Exception ex){
            errorLabel.setText("An error occurred. Please try again later.");
        }
    }

    /**
     * Switch label text between machine id and company name based on selected button.
     * @param e ActionEvent to toggle on.
     */
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
