package GUI;

import application.Controller.Controller;
import application.model.Kunde;
import application.model.Rundvisning;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Date;

public class OpretRundvisningPane extends GridPane{

    private Rundvisning rundvisning;
    private ComboBox<Kunde> cbbKunde;
    private Button btnOpretKunde, btnOpretRundvisning;
    private Label lblvaelgKunde, lblDato, lblTid, lblAntalDeltagere;
    private TextField txfDato, txfTid, txfAntalDeltagere;
    private DatePicker dpDato;



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

        cbbKunde.setOnAction(event -> createRundvisning());
    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
        updateControls();
    }

    public void createRundvisning(){
        if(rundvisning == null){
//            Rundvisning(); gfd = Controller.createRundvisning();
//            Controller.seterb(gfd);
            //rundvisning.setErBetalt(false);
        }
    }

//    public void RundvisningSalgslinje(){
//        Date rundvisningDato = dpDato.getValue();
//    }

    public void updateControls() {
        cbbKunde.getItems().clear();
        cbbKunde.getItems().addAll(Controller.getKunder());
    }
}
