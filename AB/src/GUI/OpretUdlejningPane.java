package GUI;

import application.Controller.Controller;
import application.model.Kunde;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class OpretUdlejningPane extends GridPane {

    private ComboBox<Kunde> cbbKunder;


    public OpretUdlejningPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblvaelgKunde = new Label("Vælg kunde");
        add(lblvaelgKunde,0,1);

        Button btnOpretKunde = new Button("Opret kunde");
        add(btnOpretKunde,2,1);
        btnOpretKunde.setOnAction(event -> this.popUpOpretKunde());

        cbbKunder = new ComboBox<>();
        this.add(cbbKunder,1,1);
        cbbKunder.getItems().addAll(Controller.getKunder());

    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
    }


    public void updateControls() {

    }
}
