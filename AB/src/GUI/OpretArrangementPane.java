package GUI;

import application.Controller.Controller;
import application.model.Arrangement;
import application.model.Pris;
import application.model.Produkt;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class OpretArrangementPane extends GridPane {



    private TextField txfArrangementNavn;
    private Label lblError;
    private ComboBox<Produkt> cbbAlleProdukter;
    private ComboBox<Arrangement> cbbArrangementer;
    private TextField txfPris;
    private ListView<Pris> lvwProdukterMedPriser;

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

//       ---------------- tildel pris til arrangement ------------------

        Label lblArrangement = new Label("-------- Tildel pris til arrangement --------");
        this.add(lblArrangement, 2, 5);

        Label lblArrangement2 = new Label("Arrangement:");
        this.add(lblArrangement2, 1, 6);


        cbbArrangementer = new ComboBox<>();
        this.add(cbbArrangementer, 2, 6);
        cbbArrangementer.getItems().addAll(Controller.getArrangementer());

        Label lblArrangement3 = new Label("Produkt:");
        this.add(lblArrangement3, 1, 7);

        cbbAlleProdukter = new ComboBox<>();
        this.add(cbbAlleProdukter, 2, 7);
        cbbAlleProdukter.getItems().addAll(Controller.getProdukter());


        Button btnOpretPris = new Button("Opret Pris");
        this.add(btnOpretPris,2,9);
        btnOpretPris.setOnAction(event -> this.opretPrisAction());

        Label lblPris = new Label("Pris:");
        this.add(lblPris, 1, 8);

        txfPris = new TextField();
        this.add(txfPris, 2, 8);
        txfPris.setEditable(true);
        txfPris.setMaxWidth(100);

// ---------- list view over alle produkter der har priser ---------
        lvwProdukterMedPriser = new ListView<>();
        this.add(lvwProdukterMedPriser,2,10);
        lvwProdukterMedPriser.getItems().setAll(Controller.getPriser());
        lvwProdukterMedPriser.setMaxHeight(200);




//  -------------- error message -------

        lblError = new Label();
        this.add(lblError, 2, 4);
        lblError.setStyle("-fx-text-fill: red");

    }


    private void opretArrangementPaneAction() {
        String ArrangementNavn = txfArrangementNavn.getText().trim();
        if (ArrangementNavn.length() < 1){
            lblError.setText("Nogle felter mangle at blive udfyldt");
        }
        else {
            Arrangement arrangement = Controller.createArrangement(ArrangementNavn);
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Arrangement oprettet");
//            opdaterer lige comboboxen når der oprettes et nyt arrangement:
            cbbArrangementer.getItems().clear();
            cbbArrangementer.getItems().addAll(Controller.getArrangementer());
            // sørger for at det oprettede arrangement sættes i comboboxen:
            cbbArrangementer.getSelectionModel().select(arrangement);

        }
    }

    private void opretPrisAction() {
        int pris = Integer.parseInt((txfPris.getText().trim()));
        Produkt produkt = cbbAlleProdukter.getSelectionModel().getSelectedItem();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        Controller.createPris(pris, produkt, arrangement);

//        opdaterer lige listview:
        lvwProdukterMedPriser.getItems().clear();
        //        Nedenstående skal udfyldes med getpriser(arrangement) i parentesen
        lvwProdukterMedPriser.getItems().setAll(Controller.getPriser());

    }


    public void updateControls() {  // updateControls kaldes hver gange der klikkes til og fra fanen
        lblError.setText(""); // fjerner den grønne eller røde besked
        txfArrangementNavn.clear(); //fjerner det man har skrevet i tekstfeltet


    }
}