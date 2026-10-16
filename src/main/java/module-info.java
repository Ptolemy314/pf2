module com.example.pf_2_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.pf_2_1 to javafx.fxml;
    exports com.example.pf_2_1;
}