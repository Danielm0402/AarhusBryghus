package GUI;

import application.Controller.Controller;
import application.model.EnumArrangementVisning;
import application.model.Produktgruppe;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class OpretProduktgruppePane extends GridPane {


    private TextField txfProduktgruppenavn;
    private Label lblError;
    private CheckBox checkBoxBrugesTilUdlejning;

    public OpretProduktgruppePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        Label lblProduktgruppenavn = new Label("Produktgruppenavn:");
        this.add(lblProduktgruppenavn, 1, 1);

        txfProduktgruppenavn = new TextField();
        this.add(txfProduktgruppenavn, 2, 1);
        txfProduktgruppenavn.setEditable(true);

        Label lblProduktgruppeBrugesTilUdlejning = new Label("Produktgruppe bruges til udlejning:");
        this.add(lblProduktgruppeBrugesTilUdlejning, 1, 2);

        checkBoxBrugesTilUdlejning = new CheckBox();
        this.add(checkBoxBrugesTilUdlejning,2,2);


        Button btnOpretProduktgruppe = new Button("Opret produktgruppe");
        this.add(btnOpretProduktgruppe,2,3);
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
            Produktgruppe produktgruppe = Controller.createProduktgruppe(produktgruppenavn);
            if(checkBoxBrugesTilUdlejning.isSelected()) {
//                Controller.setProduktgruppeSomUdlejning(produktgruppe);
                Controller.setVisning(produktgruppe,EnumArrangementVisning.UDLEJNING);
            }
            else{
                Controller.setVisning(produktgruppe, EnumArrangementVisning.SALG);
            }
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Produktgruppe oprettet");
        }


    }


    public void updateControls() { // updateControls kaldes hver gange der klikkes til og fra fanen
        lblError.setText(""); // fjerner den grønne eller røde besked
        txfProduktgruppenavn.clear(); //fjerner det man har skrevet i tekstfeltet
    }
}
