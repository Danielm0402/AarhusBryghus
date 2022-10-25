package GUI;

import application.Controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class OpretProduktgruppePane extends GridPane {


    private TextField txfProduktgruppenavn;
    private Label lblError;

    public OpretProduktgruppePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        Label lblProduktgruppenavn = new Label("Produktgruppenavn:");
        this.add(lblProduktgruppenavn, 1, 1);

        txfProduktgruppenavn = new TextField();
        this.add(txfProduktgruppenavn, 2, 1);
        txfProduktgruppenavn.setEditable(true);


        Button btnOpretProduktgruppe = new Button("Opret produktgruppe");
        this.add(btnOpretProduktgruppe,2,2);
        btnOpretProduktgruppe.setOnAction(event -> this.opretProduktgruppeAction());


        lblError = new Label();
        this.add(lblError, 2, 6);
        lblError.setStyle("-fx-text-fill: red");

    }



    private void opretProduktgruppeAction() {
        String produktgruppenavn = txfProduktgruppenavn.getText().trim();
        if (produktgruppenavn.length() < 1){
            lblError.setText("Nogle felter mangle at blive udfyldt");
        }
        else {
            Controller.createProduktgruppe(produktgruppenavn);
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Produktgruppe oprettet");
        }


    }


    public void updateControls() { // updateControls kaldes hver gange der klikkes til og fra fanen
        lblError.setText(""); // fjerner den grønne eller røde besked
        txfProduktgruppenavn.clear(); //fjerner det man har skrevet i tekstfeltet
    }
}
