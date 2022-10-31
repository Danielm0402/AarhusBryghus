package GUI;

import application.Controller.Controller;
import application.model.Kunde;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretKundeWindow extends Stage {
    private TextField txfNavn, txfEmail, txfTelefon, txfAdresse;
    private Label lblNavn, lblEmail, lblTelefon, lblAdresse;
    private Label lblError;

    public OpretKundeWindow(String opretKunde){
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }


    public void initContent(GridPane pane){
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);


        txfNavn = new TextField();
       pane.add(txfNavn,1,1);
//        pane.add(txfTelefon,1,2);
//        pane.add(txfEmail,1,3);
//        pane.add(txfAdresse,1,4);



    }



    //Metoder:
    public void OpretKunde() {
        String kundeNavn = txfNavn.getText().trim();
        String kundeEmail = txfEmail.getText().trim();
        String kundeTelefon = txfTelefon.getText().trim();
        String kundeAdresse = txfAdresse.getText().trim();
        if (kundeNavn.length() < 1 || kundeEmail.length() < 1 || kundeTelefon.length() < 1 || kundeAdresse.length() < 1) {
            lblError.setText("Nogle felter mangler at blive udfyldt");
        } else {
            Kunde kunde = Controller.createKunde(kundeNavn, kundeTelefon, kundeEmail, kundeAdresse);
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Kunde oprettet");


        }

    }
}
