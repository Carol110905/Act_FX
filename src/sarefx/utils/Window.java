package sarefx.utils;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class Window {

    public static void close(ActionEvent event) {
        //Me devuelve el elemento al que hice click
        Node source = (Node) event.getSource();
        //Me devuelve la ventana donde se encuentra el elemento
        Stage stage = (Stage) source.getScene().getWindow();

        stage.close();
    }

    public static Stage getStageByEvent(ActionEvent event) {
        Node source = (Node) event.getSource();
        return (Stage) source.getScene().getWindow();
    }

    public static Stage getStageByNode(Node node) {
        return (Stage) node.getScene().getWindow();
    }

    public static void showMessageInformation(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Diálogo de información...");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void showMessageWarning(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Diálogo de aviso...");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void showMessageError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Diálogo de error...");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static Boolean showMessageConfirmation(String mensaje) {
        Boolean aceptar=null;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de confirmación...");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.isPresent()) {
            if (resultado.get() == ButtonType.OK) {
                aceptar=true;
            } else {   
                aceptar=false;
            }
        }
        return aceptar;
    }
}
