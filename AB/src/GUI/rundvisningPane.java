package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalTime;

public class rundvisningPane extends GridPane{

    private final ControllerInterface controller;
    private Rundvisning rundvisning;
    private ComboBox<Kunde> cbbKunde;
    private Button btnOpretKunde, btnOpretRundvisning;
    private Label lblvaelgKunde, lblEmpty, lblDato, lblTid, lblAntalDeltagere, lblError, lblOpretRundvisning, lblIkkeBetaltRundvisning;
    private TextField txfTid, txfAntalDeltagere;
    private DatePicker dpDato;
    private ListView<Rundvisning> lvwRundvisninger;


    public rundvisningPane(){
        controller = new Controller(Storage.getInstance());

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lblvaelgKunde = new Label("Vælg kunde");
        cbbKunde = new ComboBox<>();
        cbbKunde.getItems().addAll(controller.getKunder());
        //add(cbbKunde,0,2);
        cbbKunde.getItems().addAll(controller.getKunder());

        btnOpretKunde = new Button("Opret kunde");
        btnOpretKunde.setOnAction(event -> this.popUpOpretKunde());

        dpDato = new DatePicker();
        lblDato = new Label("Dato:");

        lblTid = new Label("Tid (hh:mm):");
        txfTid = new TextField();

        lblAntalDeltagere = new Label("Antal:");
        txfAntalDeltagere = new TextField();

        lblEmpty = new Label(" ");

        btnOpretRundvisning = new Button("Opret rundvisning");
        btnOpretRundvisning.setOnAction(event -> createRundvisningOgSalgslinje());

        lblIkkeBetaltRundvisning = new Label("Ikke betalte rundvisninger:");

        lblOpretRundvisning = new Label("Opret rundvisning");

        lvwRundvisninger = new ListView<>();
        lvwRundvisninger.getItems().setAll(controller.getRundvisning(false));



        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> Payment(EnumBetalingsmetode.DANKORT));

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> Payment(EnumBetalingsmetode.KONTANT));

        Button btnKlippekort = new Button("Klippekort");
        btnKlippekort.setOnAction(event -> Payment(EnumBetalingsmetode.KLIPPEKORT));

        Button btnMobilepay = new Button("Mobilepay");
        btnMobilepay.setOnAction(event -> Payment(EnumBetalingsmetode.MOBILEPAY));

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> Payment(EnumBetalingsmetode.REGNING));


        lblError = new Label();
        add(lblError,1,8);


        HBox hboxDato = new HBox(65, lblDato, dpDato);

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnMobilepay,btnRegning);

        VBox vboxRundvisninger = new VBox(10, lblIkkeBetaltRundvisning, lblEmpty, lvwRundvisninger, hboxBetaling);

        HBox hboxTid = new HBox(27, lblTid, txfTid);

        HBox hboxAntalDeltagere = new HBox(63, lblAntalDeltagere, txfAntalDeltagere);

        HBox hboxKunde = new HBox(10, cbbKunde, btnOpretKunde);

        VBox vboxInfo = new VBox(10, lblOpretRundvisning, lblEmpty, hboxDato, hboxTid, hboxAntalDeltagere, btnOpretRundvisning);


        VBox vboxKunde = new VBox(10,lblvaelgKunde, hboxKunde);
        add(vboxKunde, 1, 2);

        HBox hboxAltRundvisning = new HBox(65, vboxInfo, vboxRundvisninger);
        add(hboxAltRundvisning, 1,6);
    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
        updateControls();
        cbbKunde.getSelectionModel().selectLast();
    }

    public void createRundvisningOgSalgslinje(){
        if (!txfTid.getText().isEmpty() && !txfAntalDeltagere.getText().isEmpty() && !(dpDato.getValue() == null)){
            LocalDate rundvisningDato = dpDato.getValue();
            LocalTime rundvisningTid = LocalTime.parse(txfTid.getText().trim());
            int rundvisningAntal = Integer.parseInt(txfAntalDeltagere.getText().trim());
            LocalDate now = LocalDate.now();
            Kunde kunde = cbbKunde.getSelectionModel().getSelectedItem();
            Pris pris = controller.getRundvisningsPris();
            if((rundvisningDato.compareTo(now)+1) < 1 || rundvisningTid == null || rundvisningAntal < 1 || kunde==null){
                lblError.setText("Udfyld felter korrekt");
            }
            else{
                Rundvisning rundvisning = controller.createRundvisning();
                controller.setErBetalt(rundvisning,false);
                controller.createSalgsLinje(rundvisning, rundvisningAntal, pris);
                controller.setAntalDeltagere(rundvisning, rundvisningAntal);
                rundvisning.setDato(rundvisningDato);
                rundvisning.setModetidspunkt(rundvisningTid);
                rundvisning.setKunde(kunde);
                updateControls();
                lblError.setStyle("-fx-text-fill: green");
                lblError.setText("Rundvisning opretettet");
                dpDato.setValue(null);
                txfTid.clear();
                txfAntalDeltagere.clear();
            }
        }

    }

    public void Payment(EnumBetalingsmetode betalingsmetode){
        Rundvisning rundvisning = lvwRundvisninger.getSelectionModel().getSelectedItem();
        if (rundvisning != null){
            Betalingsmetode b1 = controller.createBetalingsmetode(betalingsmetode);
            controller.setBetalingsmetode(rundvisning,b1);
            controller.setErBetalt(rundvisning,true);
            updateControls();
        }
    }

    public void updateControls() {
        cbbKunde.getItems().clear();
        cbbKunde.getItems().addAll(controller.getKunder());
        lvwRundvisninger.getItems().clear();
        lvwRundvisninger.getItems().addAll(controller.getRundvisning(false));
        lblError.setText("");
    }
}
