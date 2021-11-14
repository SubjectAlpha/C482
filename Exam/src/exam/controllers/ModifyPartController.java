package exam.controllers;

import exam.Main;
import exam.objects.InHouse;
import exam.objects.Outsourced;
import exam.objects.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ModifyPartController extends Controller {

    public TextField idField;
    public TextField inventoryCount;
    public TextField partName;
    public TextField maxCount;
    public TextField price;
    public Label machineIdLabel;
    public TextField machineId;
    public RadioButton outsourcedBtn;
    public RadioButton inHouseBtn;
    public TextField minCount;
    public Label errorLabel;

    private boolean inHouse = false;

    /**
     * Sets the object to be used, must check if its InHouse or Outsourced/
     * @param o Object to be used on the page.
     */
    @Override
    public void setDataObject(Object o){
        this.dataObject = o;

        if(o instanceof InHouse){
            InHouse p = (InHouse) this.dataObject;

            inHouse = true;
            inHouseBtn.setSelected(true);
            idField.setText(Integer.toString(p.getId()));
            inventoryCount.setText(Integer.toString(p.getStock()));
            partName.setText(p.getName());
            maxCount.setText(Integer.toString(p.getMax()));
            minCount.setText(Integer.toString(p.getMin()));
            price.setText(Double.toString(p.getPrice()));
            machineId.setText(Integer.toString(p.getMachineId()));
        }else if(o instanceof Outsourced){
            Outsourced p = (Outsourced) this.dataObject;
            inHouse = false;
            outsourcedBtn.setSelected(true);
            idField.setText(Integer.toString(p.getId()));
            inventoryCount.setText(Integer.toString(p.getStock()));
            partName.setText(p.getName());
            maxCount.setText(Integer.toString(p.getMax()));
            minCount.setText(Integer.toString(p.getMin()));
            price.setText(Double.toString(p.getPrice()));
            machineId.setText(p.getCompanyName());

            machineIdLabel.setText("Company Name");
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again later.");
            a.show();
        }


    }

    /**
     * Parse data and update part of requirements are met, if not display error dialog.
     * @param e
     */
    public void editPart(ActionEvent e) {
        try{
            int id = parseInt(idField.getText());
            double ppu = parseDouble(price.getText());
            int stock = parseInt(inventoryCount.getText());
            int max = parseInt(maxCount.getText());
            int min = parseInt(minCount.getText());

            if(max > min && max >= stock && stock >= min){
                if(inHouse){
                    InHouse updatePart = (InHouse) this.dataObject;
                    updatePart.setPrice(ppu);
                    updatePart.setStock(stock);
                    updatePart.setMax(max);
                    updatePart.setMin(min);
                    updatePart.setMachineId(parseInt(machineId.getText()));
                    Main.mainInventory.updatePart(id - 1, updatePart);
                } else {
                    Outsourced updatePart = (Outsourced) this.dataObject;
                    updatePart.setPrice(ppu);
                    updatePart.setStock(stock);
                    updatePart.setMax(max);
                    updatePart.setMin(min);
                    updatePart.setCompanyName(machineId.getText());
                    Main.mainInventory.updatePart(id - 1, updatePart);
                }

                openWindow("main");
                closeWindow(e);
            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Max must be more than min. Stock must be less than max, but more than min.");
                a.show();
            }

        }catch(Exception ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again later.");
            a.show();
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
