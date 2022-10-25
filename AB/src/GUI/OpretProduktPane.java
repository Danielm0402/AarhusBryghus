package GUI;

import application.Controller.Controller;
import application.model.Arrangement;
import application.model.Produktgruppe;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class OpretProduktPane extends GridPane {

    private ComboBox<Produktgruppe> cbbProduktgruppe;

    public OpretProduktPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblArrangement = new Label("Produktgruppe:");
        this.add(lblArrangement, 1, 1);

        cbbProduktgruppe = new ComboBox<>();
        this.add(cbbProduktgruppe, 2, 1);
        cbbProduktgruppe.getItems().addAll(Controller.getProduktgrupper());
        cbbProduktgruppe.getSelectionModel().selectFirst();

    }

    public void updateControls() {
    }
}