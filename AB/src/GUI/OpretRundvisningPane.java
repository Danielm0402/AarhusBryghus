package GUI;

import application.model.Kunde;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class OpretRundvisningPane extends GridPane{

    private ComboBox<Kunde> cbbKunder;


    public OpretRundvisningPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblvaelgKunde = new Label("Vælg kunde");
        add(lblvaelgKunde,0,1);

        Button btnOpretKunde = new Button("Opret kunde");
        add(btnOpretKunde,1,1);
        btnOpretKunde.setOnAction(event -> this.popUpOpretKunde());

    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
    }


    public void updateControls() {

    }
}
