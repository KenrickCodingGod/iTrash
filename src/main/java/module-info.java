module com.itrash.itrash {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;


    opens com.itrash.itrash to javafx.fxml;
    exports com.itrash.itrash;
}