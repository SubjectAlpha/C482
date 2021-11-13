package exam;

import exam.objects.InHouse;
import exam.objects.Inventory;
import exam.objects.Outsourced;
import exam.objects.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

//JDK USED: https://bell-sw.com/pages/downloads/#/java-11-lts

public class Main extends Application {
    public static final Inventory mainInventory = new Inventory();
    @Override
    public void start(Stage primaryStage) throws Exception{
        var tire = new InHouse(0, "Tire", 9.99, 10, 0, 20);
        var handlebar = new InHouse(0, "Handlebar", 120.00, 2, 0, 5);
        var body = new Outsourced(0, "Body", 500.00, 3, 1, 10);
        var engine = new Outsourced(0, "Engine", 250.00, 1, 0, 2);
        mainInventory.addPart(tire);
        mainInventory.addPart(handlebar);
        mainInventory.addPart(body);
        mainInventory.addPart(engine);

        Product initialProduct = new Product(0, "E-Bike", 800, 10, 2, 10);
        initialProduct.addAssociatedPart(tire);
        initialProduct.addAssociatedPart(handlebar);
        initialProduct.addAssociatedPart(body);
        initialProduct.addAssociatedPart(engine);

        mainInventory.addProduct(initialProduct);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        primaryStage.setTitle("C482 Exam - Jacob Starr");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
