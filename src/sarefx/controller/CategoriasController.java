package sarefx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sarefx.api.requests.Requests;
import sarefx.model.beans.Catalogo;
import sarefx.model.beans.Categoria;
import sarefx.utils.Window;


public class CategoriasController implements Initializable {

    @FXML
    private Pane pnl_busqueda;
    @FXML
    private Label lbl_idCategoria;
    @FXML
    private TextField txt_idcategoria;
    @FXML
    private Button btn_buscar;
    @FXML
    private Button btn_limpiar;
    @FXML
    private SplitPane pnl_tablas;
    @FXML
    private Text lbl_Categorias;
    @FXML
    private Pane pnl_accionesCategoria;
    @FXML
    private Button btn_nuevaCategoria;
    @FXML
    private Button btn_editarCategoria;
    @FXML
    private Button btn_activarCategoria;
    @FXML
    private Button btn_desactivarCategoria;
    @FXML
    private TableView<Categoria> tbl_categorias;
    @FXML
    private Text lbl_Catalogos;
    @FXML
    private Pane pnl_accionesCatalogo;
    @FXML
    private Button btn_nuevoCatalogo;
    @FXML
    private Button btn_editarCatalogo;
    @FXML
    private Button btn_activarCatalogo;
    @FXML
    private Button btn_desactivarCatalogo;

    @FXML
    private TableColumn<Categoria, Integer> tcl_categoriaIdCategoria;
    @FXML
    private TableColumn<Categoria, String> tcl_categoriaNombre;
    @FXML
    private TableColumn<Categoria, String> tcl_categoriaActivo;
    @FXML
    private TableColumn<Categoria, Integer> tcl_categoriaOrden;
    @FXML
    private TableColumn<Catalogo, Integer> tcl_catalogoIdCatlogo;
    @FXML
    private TableColumn<Catalogo, String> tcl_catalogoNombre;
    @FXML
    private TableColumn<Catalogo, String> tcl_catalogoActivo;
    @FXML
    private TableColumn<Catalogo, Integer> tcl_catalogoOrden;

    Categoria categoria = null;
    @FXML
    private TableView<Catalogo> tbl_catalogos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarCategorias();
    }

    @FXML
    private void buscarCategorias(ActionEvent event) {
        this.cargarCategorias();
    }

    @FXML
    private void nuevaCategoria(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sarefx/gui/view/FormCategoriaFXML.fxml"));
            Parent formCategoria = loader.load();
            FormCategoriaController ctrl = loader.getController();
            ctrl.setData(new Categoria(), true);
            Scene scene = new Scene(formCategoria);
            Stage stageCategorias = new Stage();
            stageCategorias.setTitle("Categorías");
            stageCategorias.setResizable(false);
            stageCategorias.setScene(scene);
            stageCategorias.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarCategoria(ActionEvent event) {
        if (this.categoria != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sarefx/gui/view/FormCategoriaFXML.fxml"));
                Parent formCategoria = loader.load();
                FormCategoriaController ctrl = loader.getController();
                ctrl.setData(this.categoria, false);
                Scene scene = new Scene(formCategoria);
                Stage stageCategorias = new Stage();
                stageCategorias.setTitle("Categorías");
                stageCategorias.setResizable(false);
                stageCategorias.setScene(scene);
                stageCategorias.showAndWait();
                this.cargarCategorias();
            } catch (IOException ex) {
                Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Window.showMessageWarning("Debe seleccionar una categoría...");
        }
    }

    @FXML
    private void clickTable(MouseEvent event) {
        String respuesta = "";
        if (tbl_categorias.getSelectionModel().getSelectedItem() != null) {
            categoria = tbl_categorias.getSelectionModel().getSelectedItem();

            tbl_catalogos.getItems().clear();
            respuesta = Requests.get("/catalogo/getCatalogosByCategoria/" + categoria.getIdCategoria());
            Gson gson = new Gson();

            //Definimos un TypeToken que representa una lista de objetos Categoria
            TypeToken<List<Catalogo>> token = new TypeToken<List<Catalogo>>() {
            };
            //Utilizamos el método fromJson() de la clase Gson para convertir el JSON en una lista de objetos Categoria
            List<Catalogo> listCatalogos = gson.fromJson(respuesta, token.getType());

            tcl_catalogoIdCatlogo.setCellValueFactory(new PropertyValueFactory<>("idCatalogo"));
            tcl_catalogoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tcl_catalogoActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
            tcl_catalogoOrden.setCellValueFactory(new PropertyValueFactory<>("orden"));

            listCatalogos.forEach(e -> {
                tbl_catalogos.getItems().add(e);
            });
        }
    }

    @FXML
    private void activarCategoria(ActionEvent event) {
        if (this.categoria != null) {
            if ("N".equals(categoria.getActivo())) {
                Boolean conf = Window.showMessageConfirmation("¿Quiere activar la categoría " + this.categoria.getNombre() + "?");
                if (conf) {
                    this.actualizarEstatusCategoria("S");
                }
            } else {
                Window.showMessageInformation("La categoría ya esta activa...");
            }
        } else {
            Window.showMessageWarning("Debe seleccionar una categoría...");
        }
    }

    @FXML
    private void DesactivarCategoria(ActionEvent event) {
        if (this.categoria != null) {
            if ("S".equals(categoria.getActivo())) {
                Boolean conf = Window.showMessageConfirmation("¿Quiere desactivar la categoría " + this.categoria.getNombre() + "?");
                if (conf) {
                    this.actualizarEstatusCategoria("N");
                }

            } else {
                Window.showMessageInformation("La categoría ya esta Inactiva");
            }
        } else {
            Window.showMessageWarning("Debe seleccionar una categoría...");
        }
    }

    public void cargarCategorias() {
        this.categoria=null;
        String respuesta = "";
        tbl_categorias.getItems().clear();

        respuesta = Requests.get("/categoria/getAllCategorias/");
        System.out.println(respuesta);
        Gson gson = new Gson();

        //Definimos un TypeToken que representa una lista de objetos Categoria
        TypeToken<List<Categoria>> token = new TypeToken<List<Categoria>>() {
        };
        //Utilizamos el método fromJson() de la clase Gson para convertir el JSON en una lista de objetos Categoria
        List<Categoria> listCategorias = gson.fromJson(respuesta, token.getType());

        tcl_categoriaIdCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        tcl_categoriaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_categoriaActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));
        tcl_categoriaOrden.setCellValueFactory(new PropertyValueFactory<>("orden"));

        listCategorias.forEach(e -> {
            tbl_categorias.getItems().add(e);
        });
        /* for(Categoria c: listCategorias){
            tbl_categorias.getItems().add(c);
        }*/
        //System.out.println(listCategorias.size());
    }

    public void actualizarEstatusCategoria(String activo) {
        try {
            String data = "";
            HashMap<String, Object> params = new LinkedHashMap<>();
            params.put("idCategoria", this.categoria.getIdCategoria());
            params.put("activo", activo);
            data = Requests.post("/categoria/actualizarEstatusCategoria/", params);

            JSONObject dataJson = new JSONObject(data);

            if ((Boolean) dataJson.get("error") == false) {
                Window.showMessageInformation(dataJson.get("mensaje").toString());
                this.cargarCategorias();
            } else {
                Window.showMessageError(dataJson.get("mensaje").toString());
            }
        } catch (JSONException ex) {
            Logger.getLogger(CategoriasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
