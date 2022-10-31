package GUI;

import application.Controller.Controller;
import application.model.*;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SalgsPane extends GridPane {

    private ComboBox<Arrangement> cbbArrangementer;
    private ListView<Pris> lvwProduktPriser;
    private ListView<Produktgruppe> lvwProduktgrupper;
    private ListView<Salgslinje> lvwSalgslinjer;
    private TextField txfRabat;
    private TextField txfTotal;
    private double total = 0;
    private Salg salg;


    public SalgsPane() {

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblArrangement = new Label("Vælg arrangement");
        this.add(lblArrangement, 1, 0);

//        combobox over arrangementer
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


        //listview over produkt priser
        lvwProduktPriser = new ListView<>();
        this.add(lvwProduktPriser,2,2);
//        ----nedenstående er så der kan klikkes på en ProduktPris og så kommer den ind i salgslinje---
        lvwProduktPriser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Pris produktpris = lvwProduktPriser.getSelectionModel().getSelectedItem();
                boolean existsAlready = false;

                if (salg ==null){
                    salg = Controller.createSalg();
                }

                //For at kunne opdatere antallet på den enkelte salgslinje

                for (Salgslinje s : salg.getSalgsLinjer())
                    if (s.getPris() == produktpris){
                        Controller.incrementSalgslinje(salg,s);
                        existsAlready = true;
                    }

                //Hvis produktet ikke allerede findes tilføjes en ny salgslinje
                if (!existsAlready){
                    Controller.createSalgsLinje(salg, 1, produktpris.getEnhedspris(),produktpris);
                }
                total += produktpris.getEnhedspris();
                txfTotal.setText(String.valueOf(total));
                lvwSalgslinjer.getItems().setAll(salg.getSalgsLinjer());
            }
        });
//        ---------------- salgslinjer ------------------------------


        //listview over salgslinjer
        lvwSalgslinjer = new ListView<>();
        this.add(lvwSalgslinjer,3,2);

//------------------- rabat ----------------------------------------------
        Label lblRabat = new Label("Rabat i %");

        //textfield for rabat
        txfRabat = new TextField();
        Button btnrabat = new Button("Udregn rabat");
        btnrabat.setOnAction(event -> discount());

        //        txfRabat.textProperty().addListener((observable, oldValue, newValue) -> discount());


        HBox hboxRabat = new HBox(10,lblRabat,txfRabat,btnrabat);
        this.add(hboxRabat,3,3);

        Label lblTotal = new Label("Total");

        //textArea for total
        txfTotal = new TextField();
        txfTotal.setEditable(false);


        HBox hboxTotal = new HBox(10, lblTotal,txfTotal);
        this.add(hboxTotal,3,4);

        //Dankort kontant, klippekort, mobilpay, regnning

        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> Payment());

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> Payment());

        Button btnKlippekort = new Button("Klippekort");
        btnKlippekort.setOnAction(event -> Payment());

        Button btnMobilpay = new Button("Mobilpay");
        btnMobilpay.setOnAction(event -> Payment());

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> Payment());

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnKlippekort,btnMobilpay,btnRegning);
        this.add(hboxBetaling,3,5);
    }

    //    opdaterer hver gang der er lavet en ændring på "Vælg arrangement" bomboboksen.
    private void selectedArrangementChanged(Arrangement newValue) {
        lvwProduktgrupper.getSelectionModel().selectFirst();
        Produktgruppe produktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        lvwProduktPriser.getItems().setAll(Controller.getPriserFromArrangementWithinProduktgruppe(arrangement, produktgruppe));

    }

    //    opdaterer hver gang man vælger en ny produktgruppe
    private void selectedProduktgruppeChanged(Produktgruppe newValue) {
        Produktgruppe produktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        lvwProduktPriser.getItems().setAll(Controller.getPriserFromArrangementWithinProduktgruppe(arrangement, produktgruppe));
    }

    public void updateControls() {
        cbbArrangementer.getItems().clear(); //fjerner lige alle elementer i comboboxen, for at refreshe
        cbbArrangementer.getItems().addAll(Controller.getArrangementer()); //tilføjer dem igen, for at refreshe
        cbbArrangementer.getSelectionModel().selectFirst(); // siger lige, at den skal vælge den første, så boksen ikke er tom

        lvwProduktPriser.getItems().clear();

//        opdater produktgruppe:
        lvwProduktgrupper.getItems().clear();
        lvwProduktgrupper.getItems().setAll(Controller.getProduktgrupper());
    }

    public void Payment(){
        salg = null; // sætter salg til null så den sletter salget
        lvwSalgslinjer.getItems().clear();
        total = 0;
        txfTotal.setText(String.valueOf(total));
        txfRabat.clear();
    }

    public void discount(){
        double rabat = Double.parseDouble(txfRabat.getText());
        total = total * (1-(rabat/100));
        txfTotal.setText(String.valueOf(total));
    }
}