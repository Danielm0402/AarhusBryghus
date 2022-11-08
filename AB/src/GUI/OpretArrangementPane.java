package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.Arrangement;
import application.model.Pris;
import application.model.Produkt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import java.awt.*;

public class OpretArrangementPane extends GridPane {


    private final ControllerInterface controller;
    private TextField txfArrangementNavn;
    private Label lblError;
    private ComboBox<Produkt> cbbAlleProdukter;
    private ComboBox<Arrangement> cbbArrangementer;
    private TextField txfPris;
    private ListView<Pris> lvwProdukterMedPriser;

    public OpretArrangementPane() {
        controller = new Controller(Storage.getInstance());

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

//       ---------------- tildel pris til arrangement ------------------

        Label lblArrangement = new Label("-------- Tildel pris til arrangement --------");
        this.add(lblArrangement, 2, 5);

        Label lblArrangement2 = new Label("Arrangement:");
        this.add(lblArrangement2, 1, 6);


        cbbArrangementer = new ComboBox<>();
        this.add(cbbArrangementer, 2, 6);
        cbbArrangementer.getItems().addAll(controller.getArrangementer());
        ChangeListener<Arrangement> listener = (ov, oldValue, newValue) -> selectedArrangementChanged(newValue);
        cbbArrangementer.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblArrangement3 = new Label("Produkt:");
        this.add(lblArrangement3, 1, 7);

        cbbAlleProdukter = new ComboBox<>();
        this.add(cbbAlleProdukter, 2, 7);
        cbbAlleProdukter.getItems().addAll(controller.getProdukter());



        Button btnOpretPris = new Button("Opret Pris");
        this.add(btnOpretPris,2,9);
        btnOpretPris.setOnAction(event -> this.opretPrisAction());

        Button btnSletPris = new Button("Slet valgte");
        this.add(btnSletPris,2,11);
        btnSletPris.setOnAction(event -> this.sletPrisAction());

        Label lblPris = new Label("Pris:");
        this.add(lblPris, 1, 8);

        txfPris = new TextField();
        this.add(txfPris, 2, 8);
        txfPris.setEditable(true);
        txfPris.setMaxWidth(100);

// ---------- list view over alle produkter der har priser ---------
        lvwProdukterMedPriser = new ListView<>();
        this.add(lvwProdukterMedPriser,2,10);
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        lvwProdukterMedPriser.getItems().setAll(controller.getPriserFromArrangement(arrangement));
        lvwProdukterMedPriser.setMaxHeight(200);




//  -------------- error message -------

        lblError = new Label();
        this.add(lblError, 2, 4);
        lblError.setStyle("-fx-text-fill: red");

    }

    private void selectedArrangementChanged(Arrangement newValue) {
        updateControls();
    }

    private void sletPrisAction() {
        Pris selectedPris = lvwProdukterMedPriser.getSelectionModel().getSelectedItem();
        controller.removePris(selectedPris);
        updateControls();
    }


    private void opretArrangementPaneAction() {
        String ArrangementNavn = txfArrangementNavn.getText().trim();
        if (ArrangementNavn.length() < 1){
            lblError.setText("Nogle felter mangle at blive udfyldt");
        }
        else {
            Arrangement arrangement = controller.createArrangement(ArrangementNavn);
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Arrangement oprettet");
//            opdaterer lige comboboxen når der oprettes et nyt arrangement:
            cbbArrangementer.getItems().clear();
            cbbArrangementer.getItems().addAll(controller.getArrangementer());
            // sørger for at det oprettede arrangement sættes i comboboxen:
            cbbArrangementer.getSelectionModel().select(arrangement);

        }
    }

    private void opretPrisAction() {
        Produkt produkt = cbbAlleProdukter.getSelectionModel().getSelectedItem();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        boolean e = true;
        if (arrangement != null || produkt != null){
            for (Pris pris : arrangement.getPriser()){
                if (pris.getProdukt() == produkt){
                    lblError.setStyle("-fx-text-fill: red");
                    lblError.setText("Pris på dette produkt findes allerede");
                    e=false;
                }
            }
            if (e && !txfPris.getText().isEmpty()) {
                int pris1 = Integer.parseInt((txfPris.getText().trim()));
                controller.createPris(pris1, produkt, arrangement);
                updateControls();
            }
        }

        }



    public void updateControls() {  // updateControls kaldes hver gange der klikkes til og fra fanen
        lblError.setText(""); // fjerner den grønne eller røde besked
        txfArrangementNavn.clear(); //fjerner det man har skrevet i tekstfeltet

//        Opdaterer produkt Combobox:
        cbbAlleProdukter.getItems().clear();
        cbbAlleProdukter.getItems().addAll(controller.getProdukter());

        //        opdaterer lige listview:
        lvwProdukterMedPriser.getItems().clear();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem(); // finder arrangement
        lvwProdukterMedPriser.getItems().setAll(controller.getPriserFromArrangement(arrangement));

    }
}