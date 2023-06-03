package sarefx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


public class PrincipalController implements Initializable {

    @FXML
    private Menu m_rh;
    @FXML
    private MenuItem mi_personal;
    @FXML
    private Menu m_administracion;
    @FXML
    private MenuItem mi_usuarios;
    @FXML
    private MenuItem mi_categoris;
    @FXML
    private BorderPane pnl_principal;
    @FXML
    private Menu m_inicio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(HashMap<String, Object> context) {
        System.out.println(context);
    }

    @FXML
    private void abrirCategorias(ActionEvent event) {
        try {
            //Se carga la nueva interfaz fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sarefx/gui/view/Categorias2FXML.fxml"));

            //Se obtiene el nodo padre que contienes los nodos secundarios de la interfaz fxml
            Parent principal = loader.load();

            pnl_principal.setCenter(principal);

        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void inicio(ActionEvent event) {
    }

}
