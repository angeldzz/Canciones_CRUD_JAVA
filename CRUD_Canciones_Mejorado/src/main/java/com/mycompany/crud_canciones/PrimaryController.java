package com.mycompany.crud_canciones;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class PrimaryController implements Initializable {

    @FXML
    private TextField tf_titulo;
    @FXML
    private TextField tf_artista;
    @FXML
    private TextField tf_album;
    @FXML
    private TextField tf_anio;
    @FXML
    private TextField tf_genero;
    @FXML
    private TableView<Cancion> tv_canciones;
    @FXML
    private TableColumn<Cancion, String> col_titulo;
    @FXML
    private TableColumn<Cancion, String> col_artista;
    @FXML
    private TableColumn<Cancion, String> col_album;
    @FXML
    private TableColumn<Cancion, Integer> col_anio;
    @FXML
    private TableColumn<Cancion, String> col_genero;

    //atributos propios
    //Modelo Base de datos
    ModeloBD mdBDCaciones;

    //Objeto para la cancion seleccionada
    Cancion cncSeleccionada = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //iniciar el modelo de Base de datos
        this.mdBDCaciones = new ModeloBD();

        //enlazar cell value
        col_titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        col_artista.setCellValueFactory(new PropertyValueFactory<>("artista"));
        col_album.setCellValueFactory(new PropertyValueFactory<>("album"));
        col_anio.setCellValueFactory(new PropertyValueFactory<>("anio"));
        col_genero.setCellValueFactory(new PropertyValueFactory<>("genero"));

        //Cargar las canciones que hay en la BD al iniciar
        //en la table view
        this.cargaCancionesBD();
    }

    @FXML
    private void btn_nuevo(ActionEvent event) {
        //validar campos de texto
        if (this.tf_titulo.getText().equals("")) {
            dialogoError("El titulo no puede estar vacio");
            return;
        }
        if (this.tf_anio.getText().equals("")) {
            dialogoError("El año no puede estar vacio");
            return;
        }
        if (this.tf_artista.getText().equals("")) {
            dialogoError("El artista no puede estar vacio");
            return;
        }
        if (this.tf_genero.getText().equals("")) {
            dialogoError("El genero no puede estar vacio");
            return;
        }
        Integer anio = 0;
        try {
            anio = Integer.valueOf(this.tf_anio.getText());
        } catch (NumberFormatException ex) {
            dialogoError("La edad tiene que ser un numero entero");
        }

        Cancion cnc = new Cancion(null,
                this.tf_titulo.getText(),
                this.tf_artista.getText(),
                this.tf_album.getText(),
                anio,
                this.tf_genero.getText()
        );
        Boolean resultado = this.mdBDCaciones.insertarBD(cnc);
        System.out.println("id: " + cnc.getId());
        if (resultado) {
            this.tv_canciones.getItems().add(cnc);
        } else {
            this.dialogoError("No se pudo insertar la cancion");
        }
    }

    @FXML
    private void btn_Limpiar(ActionEvent event) {
        this.tf_titulo.setText("");
        this.tf_artista.setText("");
        this.tf_album.setText("");
        this.tf_anio.setText("");
        this.tf_genero.setText("");
    }

    @FXML
    private void btn_borrar(ActionEvent event) {
        if (this.cncSeleccionada != null) {
            if (this.mdBDCaciones.borrarCancion(this.cncSeleccionada)) {
                this.dialogo("Fila borrada");
                this.tv_canciones.getItems().remove(this.cncSeleccionada);
            } else {
                this.dialogoError("No se pudo borrar la fila");
            }

        }
    }

    @FXML
    private void btn_modificar(ActionEvent event) {
        if (this.cncSeleccionada != null) {
            this.cncSeleccionada.setTitulo(this.tf_titulo.getText());
            this.cncSeleccionada.setArtista(this.tf_artista.getText());
            this.cncSeleccionada.setAlbum(this.tf_album.getText());
            this.cncSeleccionada.setAnio(Integer.valueOf(this.tf_anio.getText()));
            this.cncSeleccionada.setGenero(this.tf_genero.getText());

            if (this.mdBDCaciones.modificaCancion(this.cncSeleccionada)) {
                this.dialogo("Fila Modificada");
                this.btn_Limpiar(event);
                this.tv_canciones.refresh();
            } else {
                this.dialogoError("No se pudo borrar la fila");
            }
        }
    }

    @FXML
    private void filaSeleccionada(MouseEvent event) {

        if (event.getClickCount() == 2) {

            //guardar en cncSeleccionada la cancion que esta seleccionada en la tabla
            this.cncSeleccionada = this.tv_canciones.getSelectionModel().getSelectedItem();

            //ponemos los datos de la bbdd a los textfields
            this.tf_titulo.setText(this.cncSeleccionada.getTitulo());
            this.tf_artista.setText(this.cncSeleccionada.getArtista());
            this.tf_album.setText(this.cncSeleccionada.getAlbum());
            this.tf_anio.setText("" + this.cncSeleccionada.getAnio());
            this.tf_genero.setText(this.cncSeleccionada.getGenero());
        }
    }

    private void cargaCancionesBD() {
        ArrayList<Cancion> listaCancionesIniciales = this.mdBDCaciones.listaCanciones();
        for (Cancion cnc : listaCancionesIniciales) {
            this.tv_canciones.getItems().add(cnc);
        }
    }

    private void dialogoError(String error) {
        //crear una ventana para mostrar el error
        Alert nv = new Alert(Alert.AlertType.ERROR);

        //Caracteristicas de la ventana
        nv.setTitle("Error");
        nv.setContentText(error);

        //Mostrar la ventana
        nv.showAndWait();
    }

    private void dialogo(String info) {
        //crear una ventana para mostrar el error
        Alert nv = new Alert(Alert.AlertType.INFORMATION);

        //Caracteristicas de la ventana
        nv.setTitle("Informacion");
        nv.setContentText(info);

        //Mostrar la ventana
        nv.showAndWait();
    }

}
