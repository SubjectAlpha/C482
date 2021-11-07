package exam;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public void handleExit(ActionEvent e) {
        Platform.exit();
    }

    @FXML
    public void closeWindow(ActionEvent e){
        Button btn = (Button) e.getTarget();
        Stage s = (Stage) btn.getScene().getWindow();
        s.close();
    }

    @FXML
    public void openAddPart(ActionEvent e){
        try{
            Parent addPartRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("addpart.fxml")));
            Stage addPartStage = new Stage();
            addPartStage.setTitle("C482 Exam - Jacob Starr");
            addPartStage.initModality(Modality.APPLICATION_MODAL);
            addPartStage.setScene(new Scene(addPartRoot));
            addPartStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
