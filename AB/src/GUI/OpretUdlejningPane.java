package GUI;

import application.Controller.Controller;
import application.model.Arrangement;
import application.model.Kunde;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
        add(lblvaelgKunde,0,1);

        Button btnOpretKunde = new Button("Opret kunde");
        add(btnOpretKunde,2,1);
        btnOpretKunde.setOnAction(event -> this.popUpOpretKunde());

        cbbKunder = new ComboBox<>();
        this.add(cbbKunder,1,1);
        cbbKunder.getItems().addAll(Controller.getKunder());




        Label lblProduktgruppe = new Label("Vælg produktgruppe");
        add(lblProduktgruppe,0,3);
        cbbProduktgruppe = new ComboBox<>();
        this.add(cbbProduktgruppe,1,3);
        cbbProduktgruppe.getItems().addAll(Controller.getProduktgrupperWithUdlejningsattribut());
        ChangeListener<Produktgruppe> listener = (ov, oldValue, newValue) -> produktgruppeChanged(newValue);
        cbbProduktgruppe.getSelectionModel().selectedItemProperty().addListener(listener);

        Label lblProdukt = new Label("Vælg produkt");
        add(lblProdukt,0,4);
        cbbProdukt = new ComboBox<>();
        this.add(cbbProdukt,1,4);
    }

    private void produktgruppeChanged(Produktgruppe newValue) {
        Produktgruppe selectedproduktgruppe = cbbProduktgruppe.getSelectionModel().getSelectedItem();
        cbbProdukt.getItems().addAll(Controller.getProdukter(selectedproduktgruppe));
    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
        updateControls();
    }


    public void updateControls() {
        cbbKunder.getItems().clear();
        cbbKunder.getItems().addAll(Controller.getKunder());
        cbbProduktgruppe.getItems().clear();
        cbbProduktgruppe.getItems().addAll(Controller.getProduktgrupperWithUdlejningsattribut());
        cbbProdukt.getItems().clear();
    }
}
