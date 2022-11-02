package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class OpretRundvisningPane extends GridPane{

    private final ControllerInterface controller;
    private Rundvisning rundvisning;
    private ComboBox<Kunde> cbbKunde;
    private Button btnOpretKunde, btnOpretRundvisning;
    private Label lblvaelgKunde, lblEmpty, lblDato, lblTid, lblAntalDeltagere, lblError, lblOpretRundvisning, lblIkkeBetaltRundvisning;
    private TextField txfDato, txfTid, txfAntalDeltagere;
    private DatePicker dpDato;
    private ListView<Rundvisning> lvwRundvisninger;



    public OpretRundvisningPane(){
        controller = new Controller(Storage.getInstance());

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lblvaelgKunde = new Label("Vælg kunde");
        //add(lblvaelgKunde,0,1);

        cbbKunde = new ComboBox<>();
        //add(cbbKunde,0,2);
        cbbKunde.getItems().addAll(controller.getKunder());

        btnOpretKunde = new Button("Opret kunde");
      //  add(btnOpretKunde,1,2);
        btnOpretKunde.setOnAction(event -> this.popUpOpretKunde());

        lblEmpty = new Label(" ");

        dpDato = new DatePicker();
        lblDato = new Label("Dato:");
        txfDato = new TextField();
//        add(txfDato,1,4);
//        add(lblDato,0,4);

        lblTid = new Label("Tid (hh:mm):");
        txfTid = new TextField();
//        add(txfTid,1,5);
//        add(lblTid,0,5);

        lblAntalDeltagere = new Label("Antal:");
        txfAntalDeltagere = new TextField();
//        add(txfAntalDeltagere,1,6);
//        add(lblAntalDeltagere,0,6);







        btnOpretRundvisning = new Button("Opret rundvisning");
      //  add(btnOpretRundvisning,1,7);
        btnOpretRundvisning.setOnAction(event -> createRundvisningOgSalgslinje());

        lblError = new Label();
        add(lblError,1,8);

//        ChangeListener<Kunde> listener = (ov, oldValue, newValue) -> createRundvisning();
//        cbbKunde.getSelectionModel().selectedItemProperty().addListener(listener);
        //cbbKunde.setOn(event -> createRundvisning());

        lblIkkeBetaltRundvisning = new Label("Ikke betalte rundvisninger:");

        lblOpretRundvisning = new Label("Opret rundvisning");

        lvwRundvisninger = new ListView<>();
        lvwRundvisninger.getItems().setAll(controller.getRundvisning());


        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(0)));

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(1)));

        Button btnKlippekort = new Button("Klippekort");
        btnKlippekort.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(2)));

        Button btnMobilpay = new Button("Mobilpay");
        btnMobilpay.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(3)));

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(4)));



//        VBox vboxLbl = new VBox(10, lblDato, lblTid, lblAntalDeltagere);
//        add(vboxLbl,0,4);
//
//        VBox vboxTxf = new VBox(10, txfDato, txfTid, txfAntalDeltagere, btnOpretRundvisning);
//        add(vboxTxf,1,4);

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnMobilpay,btnRegning);
        //this.add(hboxBetaling,3,9);

        VBox vboxRundvisninger = new VBox(10, lblIkkeBetaltRundvisning, lblEmpty, lvwRundvisninger, hboxBetaling);
        add(vboxRundvisninger,3,4);



        HBox hboxDato = new HBox(65, lblDato, dpDato);
        this.add(hboxDato,0,4 );

        HBox hboxTid = new HBox(19, lblTid, txfTid);
        //add(hboxTid,0,5);

        HBox hboxAntalDeltagere = new HBox(62, lblAntalDeltagere, txfAntalDeltagere);
        //add(hboxAntalDeltagere,0,6);

        HBox hboxKunde = new HBox(10, cbbKunde, btnOpretKunde);
        //add(hboxKunde, 0,2);

        VBox vboxKunde = new VBox(10,lblvaelgKunde, hboxKunde);
        add(vboxKunde, 1, 2);

        VBox vboxInfo = new VBox(10, lblOpretRundvisning, lblEmpty, hboxDato, hboxTid, hboxAntalDeltagere, btnOpretRundvisning);
        //add(vboxInfo,1,5);

        HBox hboxAltRundvisning = new HBox(65, vboxInfo, vboxRundvisninger);
        add(hboxAltRundvisning, 1,6);




    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
        updateControls();
        cbbKunde.getSelectionModel().selectLast();
    }

    public void createRundvisning(){
        if(rundvisning == null){
            System.out.println("Rundvisning created");
            Rundvisning rundvisning = controller.createRundvisning();
            controller.setErBetalt(rundvisning,false);
        }
    }
    //      Pris pris = new Pris(100, Controller.getProdukter().get(73), Controller.getArrangementer().get(1))
    public void createRundvisningOgSalgslinje(){
        LocalDate rundvisningDato = dpDato.getValue();
        LocalTime rundvisningTid = LocalTime.parse(txfTid.getText().trim());
        int rundvisningAntal = Integer.parseInt(txfAntalDeltagere.getText().trim());
        LocalDate now = LocalDate.now();
        Kunde kunde = cbbKunde.getSelectionModel().getSelectedItem();
        //Betalingsmetode rundvisningBetalingsmetode =
       //
        Pris pris = controller.getRundvisningsPris();
        if(rundvisningDato.compareTo(now) < 1 || rundvisningTid == null || rundvisningAntal < 1 || kunde==null){
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
            System.out.println(rundvisning.getSalgsLinjer());

        }
    }

    public void Payment(Betalingsmetode betalingsmetode){
        Rundvisning rundvisning = lvwRundvisninger.getSelectionModel().getSelectedItem();
        controller.setBetalingsmetode(rundvisning,betalingsmetode);
        controller.setErBetalt(rundvisning,true);
        updateControls();

    }

    public void updateControls() {
        cbbKunde.getItems().clear();
        cbbKunde.getItems().addAll(controller.getKunder());
        lvwRundvisninger.getItems().clear();
        lvwRundvisninger.getItems().addAll(controller.getRundvisning());
    }
}
