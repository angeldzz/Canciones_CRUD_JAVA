module com.mycompany.crud_canciones {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.mycompany.crud_canciones to javafx.fxml;
    exports com.mycompany.crud_canciones;
}
