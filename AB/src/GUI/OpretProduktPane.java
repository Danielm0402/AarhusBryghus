package GUI;

import application.Controller.Controller;
import application.model.Arrangement;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class OpretProduktPane extends GridPane {

    private ComboBox<Produktgruppe> cbbProduktgruppe;
    private TextField txfProduktnavn;
    private Label lblError;



    public OpretProduktPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        Label lblArrangement = new Label("Produktgruppe:");
        this.add(lblArrangement, 1, 1);

        cbbProduktgruppe = new ComboBox<>();
        this.add(cbbProduktgruppe, 2, 1);
        cbbProduktgruppe.getItems().addAll(Controller.getProduktgrupper());
        cbbProduktgruppe.getSelectionModel().selectFirst();



        Label lblProduktnavn = new Label("Produktnavn:");
        this.add(lblProduktnavn, 1, 2);

        txfProduktnavn = new TextField();
        this.add(txfProduktnavn, 2, 2);
        txfProduktnavn.setEditable(true);


        Button btnOpretProdukt = new Button("Opret produkt");
        this.add(btnOpretProdukt,2,3);
        btnOpretProdukt.setOnAction(event -> this.opretProduktAction());


        lblError = new Label();
        this.add(lblError, 2, 6);
        lblError.setStyle("-fx-text-fill: red");

    }

    private void opretProduktAction() {
        String produktnavn = txfProduktnavn.getText().trim();
        Produktgruppe produktgruppe = cbbProduktgruppe.getSelectionModel().getSelectedItem();
        if (produktnavn.length() < 1){
            lblError.setText("Nogle felter mangle at blive udfyldt");
        }
        else {

            Controller.createProdukt(produktnavn,produktgruppe);
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Produkt oprettet");
        }


    }



        public void updateControls() { // updateControls kaldes hver gange der klikkes til og fra fanen
            cbbProduktgruppe.getItems().clear(); //fjerner lige alle elementer i comboboxen, for at refreshe
            cbbProduktgruppe.getItems().addAll(Controller.getProduktgrupper()); //tilføjer dem igen, for at refreshe
            lblError.setText(""); // fjerner den grønne eller røde besked
            txfProduktnavn.clear(); //fjerner det man har skrevet i tekstfeltet

        }
}