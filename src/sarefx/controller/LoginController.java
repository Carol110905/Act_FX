package sarefx.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.JSONException;

import sarefx.utils.Window;

import sarefx.api.requests.Requests;
import org.json.JSONObject;
import sarefx.model.beans.Usuario;
import sarefx.utils.JavaUtils;


public class LoginController implements Initializable {

    @FXML
    private AnchorPane pnl_principal;
    @FXML
    private ImageView img_banner;
    @FXML
    private ImageView img_logo;
    @FXML
    private Pane pnl_panelLogin;
    @FXML
    private Label lbl_usuario;
    @FXML
    private Label lbl_contrasena;
    @FXML
    private TextField txt_usuario;
    @FXML
    private PasswordField txt_contrasena;
    @FXML
    private Button btn_iniciar;
    @FXML
    private Button btn_cancelar;
    @FXML
    private Label lbl_mensaje;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void iniciarSesion2(KeyEvent event) {
    }

    @FXML
    private void iniciarSesion(ActionEvent event) {
        if (this.validar()) {
            try {
                String data = "";
                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("usuario", this.txt_usuario.getText());
                params.put("contrasena", this.txt_contrasena.getText());

                data = Requests.post("/sesion/login/", params);

                JSONObject dataJson = new JSONObject(data);

                if ((Boolean) dataJson.get("error") == false) {
                    Stage stage = Window.getStageByEvent(event);
                    Gson gson = new Gson();
                    Usuario user = gson.fromJson(dataJson.get("respuesta").toString(), Usuario.class);

                    HashMap<String, Object> context = new HashMap<String, Object>();
                    context.put("mac", JavaUtils.getMAC());
                    context.put("usuario", user);
                    
                    
                    //Se carga la nueva interfaz fxml
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sarefx/gui/view/PrincipalFXML.fxml"));

                    //Se obtiene el nodo padre que contienes los nodos secundarios de la interfaz fxml
                    Parent principal = loader.load();

                    //Se obtiene la instancia del controlador asociado a la interfaz fxml 
                    PrincipalController ctrl = loader.getController();

                    //setData es un metodo que esta declarado en la clase PrincipalController
                    //Al metodo setData se le pasa como parametro el contexto 
                    ctrl.setData(context);

                    //Se crea una nueva escena con la interfaz principal.fxml
                    Scene scene = new Scene(principal);

                    //Insertamos la nueva escena en el escenario actual
                    //Se puede crear otro escenario para insertar la nueva escena (opcional)
                    stage.setScene(scene);

                    //A la nueva escena se le agrega un titulo
                    stage.setTitle("SARE (Sistema de Administración de Recursos Empresariales)");
                    //Propiedad para no maximizar la escena
                    stage.setResizable(false);
                    //Se muestra la escena que previamente se creo
                    stage.show();
                } else {
                    this.lbl_mensaje.setText(dataJson.getString("mensaje"));
                }
            } catch (JSONException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            this.lbl_mensaje.setText("El usuario y la contraseña son requeridos...");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    private boolean validar() {
        boolean valido = false;
        if (!this.txt_usuario.getText().isEmpty() && !this.txt_contrasena.getText().isEmpty()) {
            valido = true;
        }
        return valido;
    }

}
