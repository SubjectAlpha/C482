package exam.controllers;

import exam.Main;
import exam.objects.InHouse;
import exam.objects.Part;
import exam.objects.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends Controller implements Initializable {

    @FXML private TableView<Part> partTable;
    @FXML private TableColumn<Part, String> PartId;
    @FXML private TableColumn<Part, String> PartName;
    @FXML private TableColumn<Part, String> PartAmount;
    @FXML private TableColumn<Part, String> PartCost;

    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> ProductId;
    @FXML private TableColumn<Product, String> ProductName;
    @FXML private TableColumn<Product, String> ProductAmount;
    @FXML private TableColumn<Product, String> ProductCost;

    @FXML
    public void openAddPart(ActionEvent e){
        openWindow("addpart");
        closeWindow(e);
    }

    @FXML
    public void openAddProduct(ActionEvent e){
        openWindow("addproduct");
        closeWindow(e);
    }

    @FXML
    public void openModifyPart(ActionEvent e){
        Part selected = partTable.getSelectionModel().getSelectedItem();
        if(selected != null)
        {
            openWindow("modifypart", selected);
            closeWindow(e);
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("A part was not selected. Please select one and try again.");
            a.show();
        }

    }

    @FXML
    public void openModifyProduct(ActionEvent e){
        openWindow("modifyproduct");
        closeWindow(e);
    }

    @FXML
    public void deletePart(ActionEvent e){
        Main.mainInventory.deletePart(partTable.getSelectionModel().getSelectedItem());
        partTable.getItems().setAll(Main.mainInventory.getAllParts());
    }

    @FXML
    public void deleteProduct(ActionEvent e){
        Main.mainInventory.deleteProduct(productTable.getSelectionModel().getSelectedItem());
        productTable.getItems().setAll(Main.mainInventory.getAllProducts());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        PartId.setCellValueFactory(new PropertyValueFactory<Part, String>("id"));
        PartName.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        PartAmount.setCellValueFactory(new PropertyValueFactory<Part, String>("stock"));
        PartCost.setCellValueFactory(new PropertyValueFactory<Part, String>("price"));

        ProductId.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
        ProductName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        ProductAmount.setCellValueFactory(new PropertyValueFactory<Product, String>("stock"));
        ProductCost.setCellValueFactory(new PropertyValueFactory<Product, String>("price"));

        partTable.getItems().setAll(Main.mainInventory.getAllParts());
        productTable.getItems().setAll(Main.mainInventory.getAllProducts());
    }
}
