package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.Kunde;
import application.model.Salgslinje;
import application.model.Udlejning;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AfleverUdlejningWindow extends Stage {
    private final ControllerInterface controller;
    private TextField txfNavn, txfEmail, txfTelefon, txfAdresse;
    private Label lblNavn, lblEmail, lblTelefon, lblAdresse;
    private Label lblError;
    private Button btnOpretKunde;
    private Udlejning valgteUdlejning;
    private ListView<Salgslinje>produkter;

    public AfleverUdlejningWindow(String aflever_udlejning, Udlejning valgteUdlejning){
        controller = new Controller(Storage.getInstance());

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle("Aflever udlejning");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

        this.valgteUdlejning = valgteUdlejning;
    }


    public void initContent(GridPane pane){
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);




        lblError = new Label();
        pane.add(lblError,1,6);



        //----------- - Dankort kontant, klippekort, mobilpay, regnning - --------------------

        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> afleverUdlejning());

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> afleverUdlejning());

        Button btnMobilpay = new Button("Mobilpay");
        btnMobilpay.setOnAction(event -> afleverUdlejning());

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> afleverUdlejning());

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnMobilpay,btnRegning);
        pane.add(hboxBetaling,3,5);


    }



    //Metoder:

    public void afleverUdlejning() {


        if (false) {
            lblError.setText("Udfyld alle felter");
        }else {
            controller.setUdlejningAfleveret(valgteUdlejning);

            this.hide();

        }
    }
}
