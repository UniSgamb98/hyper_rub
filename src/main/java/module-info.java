module orodent.hyper_rub {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens orodent.hyper_rub to javafx.fxml;
    exports orodent.hyper_rub;
    exports orodent.hyper_rub.utils;
    opens orodent.hyper_rub.utils to javafx.fxml;
}