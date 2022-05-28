module com.example.kgraph {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.kgraph to javafx.fxml;
    exports com.example.kgraph;
    exports com.example.kgraph.assets;
    opens com.example.kgraph.assets to javafx.fxml;
}