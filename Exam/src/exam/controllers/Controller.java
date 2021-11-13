package exam.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Objects;

public class Controller {

    public Object dataObject = null;

    public void setDataObject(Object o){
        dataObject = o;
    }

    public Object getDataObject(){
        return dataObject;
    }

    /**
     * Handle application exit.
     * @param e ActionEvent object.
     */
    @FXML
    public void handleExit(ActionEvent e) {
        Platform.exit();
    }

    /**
     * Close the Stage that the clicked button is in.
     * @param e ActionEvent object
     */
    @FXML
    public void closeWindow(ActionEvent e){
        Button btn = (Button) e.getTarget();
        Stage s = (Stage) btn.getScene().getWindow();
        s.close();
    }

    /**
     * Used for all cancel buttons as an easy way to get back to the main screen.
     * @param e ActionEvent object
     */
    @FXML
    public void cancelClick(ActionEvent e){
        closeWindow(e);
        openWindow("main");
    }

    /**
     * This method opens the request FXML file as a modal.
     * @param fileName the FXML file you'd like to open.
     */
    public void openWindow(String fileName){
        try{
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../" + fileName + ".fxml")));
            Stage newStage = new Stage();
            newStage.setTitle("C482 Exam - Jacob Starr");
            newStage.setScene(new Scene(newRoot));
            newStage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Overload method in order to open a new window with an object attached to it.
     * @param fileName name of fxml file for opening
     * @param o any object desired to attach to the window.
     */
    public void openWindow(String fileName, Object o){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../" + fileName + ".fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            Controller c = loader.getController();
            c.setDataObject(o);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
            //return null;
        }
    }
}
