package sarefx.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sarefx.api.requests.Requests;
import sarefx.model.beans.Categoria;
import sarefx.utils.Window;


public class FormCategoriaController implements Initializable {

    @FXML
    private TextField txt_idCategoria;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_orden;
    @FXML
    private Button btn_guardar;
    @FXML
    private Button btn_cerrar;
    @FXML
    private CheckBox chb_estatus;

    Categoria categoria = null;
    Boolean isNew = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Categoria categoria, Boolean isNew) {
        System.out.println(isNew);
        System.out.println(categoria.getNombre());
        this.categoria = categoria;
        this.isNew = isNew;
        if (!isNew) {
            this.cargarCategoria();
        }
    }

    public void cargarCategoria() {
        this.txt_idCategoria.setText(categoria.getIdCategoria().toString());
        this.txt_nombre.setText(categoria.getNombre());
        if ("S".equals(categoria.getActivo())) {
            this.chb_estatus.setText("Activo");
            this.chb_estatus.setSelected(true);
        } else {
            this.chb_estatus.setText("Inaactivo");
            this.chb_estatus.setSelected(false);
        }
        this.txt_orden.setText(categoria.getOrden().toString());
    }

    @FXML
    private void guardar(ActionEvent event) {
        if (validarDatos()) {
            try {
                String data = "";
                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("idCategoria", this.txt_idCategoria.getText());
                params.put("nombre", this.txt_nombre.getText());
                if (this.chb_estatus.isSelected()) {
                    params.put("activo", "S");
                } else {
                    params.put("activo", "N");
                }
                params.put("orden", this.txt_orden.getText());
                if(this.isNew){
                    data = Requests.post("/categoria/registrarCategoria/", params);
                }else{
                    data = Requests.post("/categoria/actualizarCategoria/", params);
                }
                JSONObject dataJson = new JSONObject(data);

                if ((Boolean) dataJson.get("error") == false) {
                    Window.close(event);
                    Window.showMessageInformation(dataJson.get("mensaje").toString());
                    
                } else {
                    Window.close(event);
                    Window.showMessageError(dataJson.get("mensaje").toString());
                }
            } catch (JSONException ex) {
                Logger.getLogger(FormCategoriaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cerrar(ActionEvent event) {
        Window.close(event);
    }

    @FXML
    private void checkEstatus(ActionEvent event) {
        if (this.chb_estatus.isSelected()) {
            this.chb_estatus.setText("Activo");
        } else {
            this.chb_estatus.setText("Inactivo");
        }
    }

    private Boolean validarDatos() {
        Boolean valido = true;
        return valido;
    }
}
