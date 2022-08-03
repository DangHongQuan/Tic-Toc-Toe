module com.example.tictactoeapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tictactoeapp to javafx.fxml;
    exports com.example.tictactoeapp;
}