package exam.controllers;

import exam.Main;
import exam.objects.Part;
import exam.objects.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModifyProductController extends Controller implements Initializable {
    private final ObservableList<Part> relatedPartsMain = FXCollections.observableList(new ArrayList<>());

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

    public TextField productId;
    public TextField productName;
    public TextField productStock;
    public TextField productPrice;
    public TextField productMax;
    public TextField productMin;
    public Label errorLabel;

    public void removeAssociatedPart(ActionEvent e) {
        Part selected =  relatedParts.getSelectionModel().getSelectedItem();
        relatedPartsMain.remove(selected);
        relatedParts.getItems().setAll(relatedPartsMain);
    }

    public void addAssociatedPart(ActionEvent e){
        relatedPartsMain.add(unrelatedParts.getSelectionModel().getSelectedItem());
        relatedParts.getItems().setAll(relatedPartsMain);
    }

    public void modifyProduct(ActionEvent e) {
        try{
            int max = Integer.parseInt(productMax.getText());
            int min = Integer.parseInt(productMin.getText());

            if(max > min){
                Product editProduct = (Product) this.dataObject;
                editProduct.setMax(max);
                editProduct.setName(productName.getText());
                editProduct.setPrice(Double.parseDouble(productPrice.getText()));
                editProduct.setMin(min);
                editProduct.setStock(Integer.parseInt(productStock.getText()));

                Main.mainInventory.updateProduct(editProduct.getId() - 1, editProduct);

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

    @Override
    public void setDataObject(Object o){

        this.dataObject = o;
        Product p = (Product) this.dataObject;
        relatedParts.getItems().setAll(p.getAssociatedParts());

        relatedPartsMain.addAll(p.getAssociatedParts());

        productId.setText(Integer.toString(p.getId()));
        productName.setText(p.getName());
        productStock.setText(Integer.toString(p.getStock()));
        productPrice.setText(Double.toString(p.getPrice()));
        productMax.setText(Integer.toString(p.getMax()));
        productMin.setText(Integer.toString(p.getMin()));
    }
}
