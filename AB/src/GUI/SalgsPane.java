package GUI;

import application.Controller.Controller;
import application.model.Arrangement;
import application.model.Produkt;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class SalgsPane extends GridPane {

    private ComboBox<Arrangement> cbbArrangementer;
    private ListView<Produkt> lvwProdukter;

    public SalgsPane() {

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblArrangement = new Label("Arrangement:");
        this.add(lblArrangement, 1, 1);

        cbbArrangementer = new ComboBox<>();
        this.add(cbbArrangementer, 2, 1);
        cbbArrangementer.getItems().addAll(Controller.getArrangementer());
        cbbArrangementer.getSelectionModel().selectFirst();


        //listview over produkter
        lvwProdukter = new ListView<>();
        this.add(lvwProdukter,2,2);
        lvwProdukter.getItems().setAll(Controller.getProdukter());
//        ChangeListener<Hotel> listener = (ov, oldHotel, newHotel) -> this.selectedHotelChanged();
//        lvwHotels.getSelectionModel().selectedItemProperty().addListener(listener);




    }

    public void updateControls() {
        cbbArrangementer.getItems().clear(); //fjerner lige alle elementer i comboboxen, for at refreshe
        cbbArrangementer.getItems().addAll(Controller.getArrangementer()); //tilføjer dem igen, for at refreshe

    }
}