package GUI;

import application.Controller.Controller;
import application.model.Arrangement;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class SalgsPane extends GridPane {

    private ComboBox<Arrangement> cbbArrangementer;

    public SalgsPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblArrangement = new Label("Arrangement:");
        this.add(lblArrangement, 1, 1);

        cbbArrangementer = new ComboBox<>();
        this.add(cbbArrangementer, 2, 1);
        cbbArrangementer.getItems().addAll(Controller.getArrangementer());
        cbbArrangementer.getSelectionModel().selectFirst();

    }

    public void updateControls() {
    }
}