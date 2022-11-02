package GUI;

import application.Controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class OpretRundvisningPane extends GridPane{

    private Rundvisning rundvisning;
    private ComboBox<Kunde> cbbKunde;
    private Button btnOpretKunde, btnOpretRundvisning;
    private Label lblvaelgKunde, lblDato, lblTid, lblAntalDeltagere, lblError;
    private TextField txfDato, txfTid, txfAntalDeltagere;
    private DatePicker dpDato;
    private ListView<Rundvisning> lvwRundvisninger;



    public OpretRundvisningPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lblvaelgKunde = new Label("Vælg kunde");
        add(lblvaelgKunde,0,1);

        cbbKunde = new ComboBox<>();
        add(cbbKunde,0,2);
        cbbKunde.getItems().addAll(Controller.getKunder());

        btnOpretKunde = new Button("Opret kunde");
        add(btnOpretKunde,1,2);
        btnOpretKunde.setOnAction(event -> this.popUpOpretKunde());

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

        HBox hboxDato = new HBox(50, lblDato, dpDato);
        this.add(hboxDato,0,4 );

        HBox hboxTid = new HBox(59, lblTid, txfTid);
        add(hboxTid,0,5);

        HBox hboxAntalDeltagere = new HBox(47, lblAntalDeltagere, txfAntalDeltagere);
        add(hboxAntalDeltagere,0,6);

        btnOpretRundvisning = new Button("Opret rundvisning");
        add(btnOpretRundvisning,1,7);
        btnOpretRundvisning.setOnAction(event -> createRundvisningOgSalgslinje());

        lblError = new Label();
        add(lblError,1,8);

//        ChangeListener<Kunde> listener = (ov, oldValue, newValue) -> createRundvisning();
//        cbbKunde.getSelectionModel().selectedItemProperty().addListener(listener);
        //cbbKunde.setOn(event -> createRundvisning());

        lvwRundvisninger = new ListView<>();
        lvwRundvisninger.getItems().setAll(Controller.getRundvisning());
        HBox hboxRundvisninger = new HBox(lvwRundvisninger);
        add(lvwRundvisninger,3,7);

        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> Payment(Controller.getBetalingsmetoder().get(0)));

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> Payment(Controller.getBetalingsmetoder().get(1)));

        Button btnKlippekort = new Button("Klippekort");
        btnKlippekort.setOnAction(event -> Payment(Controller.getBetalingsmetoder().get(2)));

        Button btnMobilpay = new Button("Mobilpay");
        btnMobilpay.setOnAction(event -> Payment(Controller.getBetalingsmetoder().get(3)));

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> Payment(Controller.getBetalingsmetoder().get(4)));

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnKlippekort,btnMobilpay,btnRegning);
        this.add(hboxBetaling,3,5);




    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
        updateControls();
    }

    public void createRundvisning(){
        if(rundvisning == null){
            System.out.println("Rundvisning created");
            Rundvisning rundvisning = Controller.createRundvisning();
            Controller.setErBetalt(rundvisning,false);
        }
    }
    //      Pris pris = new Pris(100, Controller.getProdukter().get(73), Controller.getArrangementer().get(1))
    public void createRundvisningOgSalgslinje(){
        LocalDate rundvisningDato = dpDato.getValue();
        LocalTime rundvisningTid = LocalTime.parse(txfTid.getText().trim());
        int rundvisningAntal = Integer.parseInt(txfAntalDeltagere.getText().trim());
        LocalDate now = LocalDate.now();
        Kunde kunde = cbbKunde.getSelectionModel().getSelectedItem();
        Pris pris = Controller.getPriser().get(9);
        //Betalingsmetode rundvisningBetalingsmetode =
       //
        if(rundvisningDato.compareTo(now) < 1 || rundvisningTid == null || rundvisningAntal < 1 || kunde==null){
            lblError.setText("Udfyld felter korrekt");
        }
        else{
            Rundvisning rundvisning = Controller.createRundvisning();
            Controller.setErBetalt(rundvisning,false);
            Controller.createSalgsLinje(rundvisning, rundvisningAntal, pris);
            Controller.setAntalDeltagere(rundvisning, rundvisningAntal);
            rundvisning.setDato(rundvisningDato);
            rundvisning.setModetidspunkt(rundvisningTid);
            rundvisning.setKunde(kunde);
            // this.rundvisning.setBetalingsmetode();
            lvwRundvisninger.getItems().setAll(Controller.getRundvisning());
            lblError.setStyle("-fx-text-fill: green");
            lblError.setText("Rundvisning opretettet");
        }
    }

    public void Payment(Betalingsmetode betalingsmetode){
        Rundvisning rundvisning = lvwRundvisninger.getSelectionModel().getSelectedItem();
        Controller.setBetalingsmetode(rundvisning,betalingsmetode);
        Controller.setErBetalt(rundvisning,true);
        updateControls();

    }

    public void updateControls() {
        cbbKunde.getItems().clear();
        cbbKunde.getItems().addAll(Controller.getKunder());
        lvwRundvisninger.getItems().clear();
        lvwRundvisninger.getItems().addAll(Controller.getRundvisning());
    }
}
