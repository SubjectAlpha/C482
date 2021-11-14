package exam.controllers;

import exam.Main;
import exam.objects.InHouse;
import exam.objects.Part;
import exam.objects.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    /**
     * Open the add part window.
     * @param e ActionEvent
     */
    @FXML
    public void openAddPart(ActionEvent e){
        try{
            openWindow("addpart");
            closeWindow(e);
        } catch (Exception ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again.");
            a.show();
        }
    }

    /**
     * Open the add product window.
     * @param e ActionEvent
     */
    @FXML
    public void openAddProduct(ActionEvent e){
        try{
            openWindow("addproduct");
            closeWindow(e);
        } catch (Exception ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again.");
            a.show();
        }

    }

    /**
     * Open and populate the modify part window.
     * @param e ActionEvent
     */
    @FXML
    public void openModifyPart(ActionEvent e){
        try{
            Part selected = partTable.getSelectionModel().getSelectedItem();
            if(selected != null)
            {
                openWindow("modifypart", selected);
                closeWindow(e);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("A part was not selected. Please select one and try again.");
                a.show();
            }
        } catch (Exception ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again.");
            a.show();
        }
    }

    /**
     * Open and populate the modify product window.
     * @param e ActionEvent
     */
    @FXML
    public void openModifyProduct(ActionEvent e){
        try{
            Product selected = productTable.getSelectionModel().getSelectedItem();
            if(selected != null){
                openWindow("modifyproduct", selected);
                closeWindow(e);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("A product was not selected. Please select one and try again.");
                a.show();
            }
        } catch(Exception ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again.");
            a.show();
        }


    }

    /**
     * Prompt user for confirmation and then delete part if confirmed.
     * @param e ActionEvent
     */
    @FXML
    public void deletePart(ActionEvent e){
        try{
            var selected = partTable.getSelectionModel().getSelectedItem();
            if(selected != null){
                Alert confirmation = confirmDelete(selected);
                confirmation.showAndWait();

                if(confirmation.getResult() == ButtonType.YES){
                    Main.mainInventory.deletePart(selected);
                    partTable.getItems().setAll(Main.mainInventory.getAllParts());
                } else {
                    confirmation.close();
                }

            }else{
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("A part was not selected. Please select one and try again.");
                a.show();
            }
        } catch (Exception ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again.");
            a.show();
        }
    }

    /**
     * Prompt user for confirmation and then delete product if confirmed.
     * @param e ActionEvent
     */
    @FXML
    public void deleteProduct(ActionEvent e){
        try{
            var selected = productTable.getSelectionModel().getSelectedItem();
            if(selected != null && selected.getAssociatedParts()
                    .size() == 0){
                Alert confirmation = confirmDelete(selected);
                confirmation.showAndWait();

                if(confirmation.getResult() == ButtonType.YES){
                    Main.mainInventory.deleteProduct(selected);
                    productTable.getItems().setAll(Main.mainInventory.getAllProducts());
                } else {
                   confirmation.close();
                }
            } else if(selected != null && selected.getAssociatedParts().size() != 0) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("This product has parts associated with it. To delete this product you must remove its parts.");
                a.show();
            }
            else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("A product was not selected. Please select one and try again.");
                a.show();
            }
        } catch (Exception ex){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("An error occurred. Please try again.");
            a.show();
        }

    }

    /**
     * Get the table ready to track data.
     * @param location URL
     * @param resources ResourceBundle
     */
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
