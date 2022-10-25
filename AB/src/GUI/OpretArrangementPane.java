package GUI;

import application.Controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class OpretArrangementPane extends GridPane {



    private TextField txfArrangementNavn;
    private Label lblError;

    public OpretArrangementPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        Label lblArrangementnavn = new Label("Arrangementnavn:");
        this.add(lblArrangementnavn, 1, 1);

        txfArrangementNavn = new TextField();
        this.add(txfArrangementNavn, 2, 1);
        txfArrangementNavn.setEditable(true);


        Button btnOpretArrangement = new Button("Opret arrangement");
        this.add(btnOpretArrangement, 2, 2);
        btnOpretArrangement.setOnAction(event -> this.opretArrangementPaneAction());


        lblError = new Label();
        this.add(lblError, 2, 6);
        lblError.setStyle("-fx-text-fill: red");

    }

    private void opretArrangementPaneAction() {
        String ArrangementNavn = txfArrangementNavn.getText().trim();
        if (ArrangementNavn.length() < 1){
            lblError.setText("Nogle felter mangle at blive udfyldt");
        }
        else {
            Controller.createArrangement(ArrangementNavn);
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Arrangement oprettet");
        }


    }


    public void updateControls() {  // updateControls kaldes hver gange der klikkes til og fra fanen
        lblError.setText(""); // fjerner den grønne eller røde besked
        txfArrangementNavn.clear(); //fjerner det man har skrevet i tekstfeltet


    }
}