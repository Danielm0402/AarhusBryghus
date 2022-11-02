package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.Kunde;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretKundeWindow extends Stage {
    private final ControllerInterface controller;
    private TextField txfNavn, txfEmail, txfTelefon, txfAdresse;
    private Label lblNavn, lblEmail, lblTelefon, lblAdresse;
    private Label lblError;
    private Button btnOpretKunde;

    public OpretKundeWindow(String opretKunde){
        controller = new Controller(Storage.getInstance());

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        
        this.setTitle("Opret kunde");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }


    public void initContent(GridPane pane){
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);

        lblNavn = new Label("Navn:");
        txfNavn = new TextField();
        pane.add(lblNavn,0,1);
        pane.add(txfNavn,1,1);

        lblTelefon = new Label("Telefon:");
        txfTelefon = new TextField();
        pane.add(lblTelefon,0,2);
        pane.add(txfTelefon,1,2);

        lblEmail = new Label("Email:");
        txfEmail = new TextField();
        pane.add(lblEmail,0, 3);
        pane.add(txfEmail,1,3);

        lblAdresse = new Label("Adresse:");
        txfAdresse = new TextField();
        pane.add(lblAdresse,0,4);
        pane.add(txfAdresse,1,4);

        btnOpretKunde = new Button("Opret kunde");
        pane.add(btnOpretKunde,1,5);
        btnOpretKunde.setOnAction(event -> this.OpretKunde());

        lblError = new Label();
        pane.add(lblError,1,6);

    }



    //Metoder:

    public void OpretKunde() {
        String kundeNavn = txfNavn.getText().trim();
        String kundeTelefon = txfTelefon.getText().trim();
        String kundeEmail = txfEmail.getText().trim();
        String kundeAdresse = txfAdresse.getText().trim();
        if (kundeNavn.length() < 1 || kundeEmail.length() < 1 || kundeTelefon.length() < 1 || kundeAdresse.length() < 1) {
            lblError.setText("Udfyld alle felter");
        }else {
            Kunde kunde = controller.createKunde(kundeNavn, kundeTelefon, kundeEmail, kundeAdresse);
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Kunde oprettet");
            this.hide();

        }
    }
}
