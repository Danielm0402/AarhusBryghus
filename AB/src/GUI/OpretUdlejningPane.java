package GUI;

import application.Controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Collections;

public class OpretUdlejningPane extends GridPane {

    private ComboBox<Kunde> cbbKunder;
    private ComboBox<Produktgruppe> cbbProduktgruppe;
    private ComboBox<Produkt> cbbProdukt;


    public OpretUdlejningPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblvaelgKunde = new Label("Vælg kunde");

        Button btnOpretKunde = new Button("Opret kunde");
        btnOpretKunde.setOnAction(event -> this.popUpOpretKunde());


        cbbKunder = new ComboBox<>();
        cbbKunder.getItems().addAll(Controller.getKunder());

        HBox hbox = new HBox(10,lblvaelgKunde,cbbKunder,btnOpretKunde);
//        this.add(hbox,0,1);

        ListView<Udlejning> lvwUdlejning = new ListView<>();
        this.add(lvwUdlejning, 2,1);




        Label lblProduktgruppe = new Label("Vælg produktgruppe");
//        add(lblProduktgruppe,0,3);
        cbbProduktgruppe = new ComboBox<>();
//        this.add(cbbProduktgruppe,1,3);
        cbbProduktgruppe.getItems().addAll(Controller.getProduktgrupperWithUdlejningsattribut());
        ChangeListener<Produktgruppe> listener = (ov, oldValue, newValue) -> produktgruppeChanged(newValue);
        cbbProduktgruppe.getSelectionModel().selectedItemProperty().addListener(listener);

        HBox hbox2 = new HBox(10,lblProduktgruppe,cbbProduktgruppe);
//        this.add(hbox2,0,3);

        Label lblProdukt = new Label("Vælg produktPRIS?!??!");
//        add(lblProdukt,0,4);
        cbbProdukt = new ComboBox<>();
//        this.add(cbbProdukt,1,4);

        HBox hbox3 = new HBox(10,lblProdukt,cbbProdukt);
//        this.add(hbox3,0,4);

        Button btnTilføjProduktPris = new Button("Tilføj Produkt");
        btnTilføjProduktPris.setOnAction(event -> tilføjProduktPris());

        VBox vbox = new VBox(10,hbox,hbox2,hbox3,btnTilføjProduktPris);
        this.add(vbox,0,1);




    }

    private void tilføjProduktPris() {
        System.out.println("Loooooooools");
    }

    private void produktgruppeChanged(Produktgruppe newValue) {
        Produktgruppe selectedproduktgruppe = cbbProduktgruppe.getSelectionModel().getSelectedItem();
        cbbProdukt.getItems().clear();
        if (selectedproduktgruppe!=null){
            cbbProdukt.getItems().addAll(Controller.getProdukter(selectedproduktgruppe)); //todo skal nok gette Daglig Butiks Priser indenfor denne produktgruppe -.-
        }
    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
        updateControls();
        cbbKunder.getSelectionModel().selectLast();
    }


    public void updateControls() {
        cbbKunder.getItems().clear();
        cbbKunder.getItems().addAll(Controller.getKunder());
        cbbProduktgruppe.getItems().clear();
        cbbProduktgruppe.getItems().addAll(Controller.getProduktgrupperWithUdlejningsattribut());
        cbbProdukt.getItems().clear();
    }
}
