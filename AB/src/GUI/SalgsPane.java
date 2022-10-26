package GUI;

import application.Controller.Controller;
import application.model.Arrangement;
import application.model.Pris;
import application.model.Produkt;
import application.model.Produktgruppe;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

import java.net.http.WebSocket;

public class SalgsPane extends GridPane {

    private ComboBox<Arrangement> cbbArrangementer;
    private ListView<Pris> lvwProdukter;
    private ListView<Produktgruppe> lvwProduktgrupper;

    public SalgsPane() {

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblArrangement = new Label("Vælg arrangement");
        this.add(lblArrangement, 1, 0);

        cbbArrangementer = new ComboBox<>();
        this.add(cbbArrangementer, 1, 1);
        cbbArrangementer.getItems().addAll(Controller.getArrangementer());
        cbbArrangementer.getSelectionModel().selectFirst();
        ChangeListener<Arrangement> listener = (ov, oldValue, newValue) -> selectedArrangementChanged(newValue);
        cbbArrangementer.getSelectionModel().selectedItemProperty().addListener(listener);

        //listview over produktgrupper
        lvwProduktgrupper = new ListView<>();
        this.add(lvwProduktgrupper,1,2);
        lvwProduktgrupper.getItems().setAll(Controller.getProduktgrupper());
        ChangeListener<Produktgruppe> listener2 = (ov, oldValue, newValue) -> selectedProduktgruppeChanged(newValue);
        lvwProduktgrupper.getSelectionModel().selectedItemProperty().addListener(listener2);


        //listview over produkter
        lvwProdukter = new ListView<>();
        this.add(lvwProdukter,2,2);






    }

//    opdaterer hver gang der er lavet en ændring på "Vælg arrangement" bomboboksen.
    private void selectedArrangementChanged(Arrangement newValue) {
        lvwProduktgrupper.getSelectionModel().selectFirst();
        Produktgruppe produktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        lvwProdukter.getItems().setAll(Controller.getPriserFromArrangementWithinProduktgruppe(arrangement, produktgruppe));

    }

    //    opdaterer hver gang man vælger en ny produktgruppe
    private void selectedProduktgruppeChanged(Produktgruppe newValue) {
        Produktgruppe produktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        lvwProdukter.getItems().setAll(Controller.getPriserFromArrangementWithinProduktgruppe(arrangement, produktgruppe));
    }

    public void updateControls() {
        cbbArrangementer.getItems().clear(); //fjerner lige alle elementer i comboboxen, for at refreshe
        cbbArrangementer.getItems().addAll(Controller.getArrangementer()); //tilføjer dem igen, for at refreshe
        lvwProdukter.getItems().clear();
    }
}