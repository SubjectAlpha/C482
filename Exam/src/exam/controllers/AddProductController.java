package exam.controllers;

import exam.Main;
import exam.objects.Part;
import exam.objects.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddProductController extends Controller implements Initializable {

    private final ObservableList<Part> relatedPartsMain = FXCollections.observableList(new ArrayList<Part>());

    public TableView<Part> unrelatedParts;
    public TableColumn<Part, String> unrelatedPartId;
    public TableColumn<Part, String> unrelatedPartName;
    public TableColumn<Part, String> unrelatedPartLevel;
    public TableColumn<Part, String> unrelatedPartPrice;

    public TableView<Part> relatedParts;
    public TableColumn<Part, String> relatedPartId;
    public TableColumn<Part, String> relatedPartName;
    public TableColumn<Part, String> relatedPartLevel;
    public TableColumn<Part, String> relatedPartPrice;

    public TextField productName;
    public TextField productStock;
    public TextField productPrice;
    public TextField productMax;
    public TextField productMin;

    /**
     * Remove associated part from list.
     * @param e ActionEvent
     */
    public void removeAssociatedPart(ActionEvent e) {
        var selected = relatedParts.getSelectionModel().getSelectedItem();
        Alert confirmation = confirmDelete(selected);
        confirmation.showAndWait();

        if(confirmation.getResult() == ButtonType.YES){
            relatedPartsMain.remove(selected);
            relatedParts.getItems().setAll(relatedPartsMain);
        } else {
            confirmation.close();
        }
    }

    /**
     * Add selected part to list of related parts.
     * @param e ActionEvent
     */
    public void addAssociatedPart(ActionEvent e){
        relatedPartsMain.add(unrelatedParts.getSelectionModel().getSelectedItem());
        relatedParts.getItems().setAll(relatedPartsMain);
    }

    /**
     * Parse data and create the new product.
     * @param e ActionEvent
     */
    public void addProduct(ActionEvent e) {
        try{
            double ppu = Double.parseDouble(productPrice.getText());
            int stock = Integer.parseInt(productStock.getText());
            int max = Integer.parseInt(productMax.getText());
            int min = Integer.parseInt(productMin.getText());

            if(max > min && max >= stock && stock >= min){
                Product newProduct = new Product(0, productName.getText(), ppu, stock, max, min);

                for (Part p: relatedPartsMain) {
                    newProduct.addAssociatedPart(p);
                }
                Main.mainInventory.addProduct(newProduct);

                openWindow("main");
                closeWindow(e);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Max must be more than min. Stock must be less than max, but more than min.");
                a.show();
            }
        } catch (Exception ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again.");
            a.show();
        }
    }

    /**
     * Setup the tables to track data.
     * @param location URL
     * @param resources Resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        unrelatedPartId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        unrelatedPartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        unrelatedPartLevel.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        unrelatedPartPrice.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));

        relatedPartId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        relatedPartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        relatedPartLevel.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        relatedPartPrice.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));

        unrelatedParts.getItems().setAll(Main.mainInventory.getAllParts());
        relatedParts.getItems().setAll(relatedPartsMain);
    }
}
