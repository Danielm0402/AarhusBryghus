package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.Arrangement;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.GridPane;

import java.io.Console;

public class OpretProduktPane extends GridPane {

    private final ControllerInterface controller;
    private ComboBox<Produktgruppe> cbbProduktgruppe;
    private TextField txfProduktnavn, txfPant;
    private Label lblError,lblHarPant,lblPant;



    public OpretProduktPane() {
        controller = new Controller(Storage.getInstance());

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);

        Label lblArrangement = new Label("Produktgruppe:");
        this.add(lblArrangement, 1, 1);

        cbbProduktgruppe = new ComboBox<>();
        this.add(cbbProduktgruppe, 2, 1);
        cbbProduktgruppe.getItems().addAll(controller.getProduktgrupper());
        cbbProduktgruppe.getSelectionModel().selectFirst();



        Label lblProduktnavn = new Label("Produktnavn:");
        this.add(lblProduktnavn, 1, 2);

        txfProduktnavn = new TextField();
        this.add(txfProduktnavn, 2, 2);
        txfProduktnavn.setEditable(true);

        lblHarPant = new Label("Produktet har pant ved udlejning:");
        this.add(lblHarPant, 1, 3);

        CheckBox chbHarPant = new CheckBox();
        this.add(chbHarPant,2,3);
        ChangeListener<Boolean> listener = (ov, oldValue, newValue) -> chbHarPantChanged(newValue);
        chbHarPant.selectedProperty().addListener(listener);

        lblPant = new Label("Pant i kroner:");
        this.add(lblPant, 1, 4);
        lblPant.setDisable(true);
        txfPant = new TextField();
        this.add(txfPant, 2, 4);
        txfPant.setDisable(true);

        Button btnOpretProdukt = new Button("Opret produkt");
        this.add(btnOpretProdukt,2,5);
        btnOpretProdukt.setOnAction(event -> this.opretProduktAction());


        lblError = new Label();
        this.add(lblError, 2, 6);
        lblError.setStyle("-fx-text-fill: red");

    }

    private void chbHarPantChanged(Boolean newValue) {
        lblPant.setDisable(!newValue);
        txfPant.setDisable(!newValue);
    }

    private void opretProduktAction() {
        String produktnavn = txfProduktnavn.getText().trim();
        Produktgruppe produktgruppe = cbbProduktgruppe.getSelectionModel().getSelectedItem();
        if (produktnavn.length() < 1 || produktgruppe ==null){
            lblError.setText("Nogle felter mangle at blive udfyldt");
        }
        else {
            Produkt produkt = controller.createProdukt(produktnavn,produktgruppe);
//            tjekker om produktet skal oprettes med pant
            if (txfPant.getText().length()>0){
                controller.setPant(produkt,Integer.parseInt(txfPant.getText()));
            }

            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Produkt oprettet");
        }


    }



        public void updateControls() { // updateControls kaldes hver gange der klikkes til og fra fanen
            cbbProduktgruppe.getItems().clear(); //fjerner lige alle elementer i comboboxen, for at refreshe
            cbbProduktgruppe.getItems().addAll(controller.getProduktgrupper()); //tilføjer dem igen, for at refreshe
            lblError.setText(""); // fjerner den grønne eller røde besked
            txfProduktnavn.clear(); //fjerner det man har skrevet i tekstfeltet

        }
}