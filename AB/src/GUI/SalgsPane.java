package GUI;

import Storage.Storage;
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

    private final ControllerInterface controller;
    private ComboBox<Arrangement> cbbArrangementer;
    private ListView<Pris> lvwProduktPriser;
    private ListView<Produktgruppe> lvwProduktgrupper;
    private ListView<Salgslinje> lvwSalgslinjer;
    private TextField txfRabat, txfTotal, txfTotalKlip;
    private double totalPris = 0;
    private int totalKlip = 0;
    private Salg salg;
    private Salgslinje valgteSalgsLinje;



    public SalgsPane() {
        controller = new Controller(Storage.getInstance());

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblArrangement = new Label("Vælg arrangement");
        this.add(lblArrangement, 1, 0);

//        combobox over arrangementer
        cbbArrangementer = new ComboBox<>();
        this.add(cbbArrangementer, 1, 1);
        cbbArrangementer.getItems().addAll(controller.getArrangementer());
        cbbArrangementer.getSelectionModel().selectFirst();
        ChangeListener<Arrangement> listener = (ov, oldValue, newValue) -> selectedArrangementChanged(newValue);
        cbbArrangementer.getSelectionModel().selectedItemProperty().addListener(listener);

        //listview over produktgrupper
        lvwProduktgrupper = new ListView<>();
        this.add(lvwProduktgrupper,1,2);
        lvwProduktgrupper.getItems().setAll(controller.getProduktgrupper(EnumArrangementVisning.SALG));
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

                if (produktpris != null) {

                    if (salg == null) {
//                        try {
//                            salg =
//                        }
//                        catch (Exception e){
//                            System.out.println(e.getMessage());
//                        }
//                        finally  {
//
//                        }

                        salg = controller.createSalg();
                    }

//                incrementSalgslinje sætter existsAlready til false, medmindre produktprisen findes i salget allerede
                    boolean existsAlready = controller.incrementSalgslinje(salg, produktpris);

                    //Hvis produktet ikke allerede findes tilføjes en ny salgslinje
                    if (!existsAlready) {
                        if (produktpris.getKlip() != 0){
                            controller.createSalgsLinje(salg,1,produktpris,produktpris.getKlip());
                        }else {
                            controller.createSalgsLinje(salg, 1, produktpris);
                        }
                    }


//                total += produktpris.getEnhedspris();
                    totalPris = controller.udregnTotalPris(salg);
                    txfTotal.setText(String.valueOf(totalPris));
                    totalKlip = controller.udregnTotalKlip(salg);
                    txfTotalKlip.setText(String.valueOf(totalKlip));
                    lvwSalgslinjer.getItems().setAll(controller.getSalgslinjer(salg));
                }
            }
        });
//        ---------------- salgslinjer ------------------------------


        //listview over salgslinjer
        lvwSalgslinjer = new ListView<>();
        this.add(lvwSalgslinjer,3,2);
//        --------------- dette er for at lave et popup når man klikker på salgslinjen----------
        lvwSalgslinjer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                valgteSalgsLinje = lvwSalgslinjer.getSelectionModel().getSelectedItem();

                if (valgteSalgsLinje!=null){
                    popUpRedigerSalgslinje(valgteSalgsLinje);

                }

            }
        });




//------------------- rabat ----------------------------------------------
        Label lblRabat = new Label("Rabat i %");

        //textfield for rabat
        txfRabat = new TextField();
        Button btnrabat = new Button("Udregn rabat");
        btnrabat.setOnAction(event -> discount());

        HBox hboxRabat = new HBox(10,lblRabat,txfRabat,btnrabat);
        this.add(hboxRabat,3,3);

        Label lblTotal = new Label("Total");
        Label lblTotalKlip = new Label("Total Klip");

        //textArea for total
        txfTotal = new TextField();
        txfTotal.setEditable(false);

        txfTotalKlip = new TextField();
        txfTotalKlip.setEditable(false);


        HBox hboxTotal = new HBox(33, lblTotal,txfTotal);
        this.add(hboxTotal,3,4);
        HBox hboxTotalKlip = new HBox(10,lblTotalKlip, txfTotalKlip);
        this.add(hboxTotalKlip,3,5);

        //----------- - Dankort kontant, klippekort, mobilpay, regnning - --------------------


        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> Payment(EnumBetalingsmetode.DANKORT));

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> Payment(EnumBetalingsmetode.KONTANT));

        Button btnKlippekort = new Button("Klippekort");
        btnKlippekort.setOnAction(event -> Payment(EnumBetalingsmetode.KLIPPEKORT));

        Button btnMobilpay = new Button("Mobilpay");
        btnMobilpay.setOnAction(event -> Payment(EnumBetalingsmetode.MOBILEPAY));

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> Payment(EnumBetalingsmetode.REGNING));

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnKlippekort,btnMobilpay,btnRegning);
        this.add(hboxBetaling,3,6);
    }

    public void Payment(EnumBetalingsmetode betalingsmetode){
        if (salg != null){
            Betalingsmetode b1 = controller.createBetalingsmetode(betalingsmetode);
            controller.setBetalingsmetode(salg, b1);
            controller.setTotalPris(salg, totalPris);
            salg = null; // sætter salg til null så den sletter salget
            lvwSalgslinjer.getItems().clear();
            totalPris = 0;
            txfTotal.clear();
            totalKlip = 0;
            txfTotalKlip.clear();
            txfRabat.clear();
        }
}


// ---------- popup til at ændre aftalt pris på salgslinjer  ------------------
    public void popUpRedigerSalgslinje(Salgslinje valgteSalgsLinje){
        RedigerSalgslinjeWindow redigerSalgsLinje = new RedigerSalgslinjeWindow("Rediger salgslinje", valgteSalgsLinje, salg);
        redigerSalgsLinje.showAndWait();
        lvwSalgslinjer.getItems().setAll(controller.getSalgslinjer(salg));
        totalPris = controller.udregnTotalPris(salg);
        txfTotal.setText(String.valueOf(totalPris));
    }



        //    opdaterer hver gang der er lavet en ændring på "Vælg arrangement" bomboboksen.
    private void selectedArrangementChanged(Arrangement newValue) {
        lvwProduktgrupper.getSelectionModel().selectFirst();
        Produktgruppe produktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        lvwProduktPriser.getItems().setAll(controller.getPriserFromArrangementWithinProduktgruppe(arrangement, produktgruppe));

    }

    //    opdaterer hver gang man vælger en ny produktgruppe
    private void selectedProduktgruppeChanged(Produktgruppe newValue) {
        Produktgruppe produktgruppe = lvwProduktgrupper.getSelectionModel().getSelectedItem();
        Arrangement arrangement = cbbArrangementer.getSelectionModel().getSelectedItem();
        lvwProduktPriser.getItems().setAll(controller.getPriserFromArrangementWithinProduktgruppe(arrangement, produktgruppe));
    }

    public void updateControls() {
        cbbArrangementer.getItems().clear(); //fjerner lige alle elementer i comboboxen, for at refreshe
        cbbArrangementer.getItems().addAll(controller.getArrangementer()); //tilføjer dem igen, for at refreshe
        cbbArrangementer.getSelectionModel().selectFirst(); // siger lige, at den skal vælge den første, så boksen ikke er tom

        lvwProduktPriser.getItems().clear();

//        opdater produktgruppe:
        lvwProduktgrupper.getItems().clear();
        lvwProduktgrupper.getItems().setAll(controller.getProduktgrupper(EnumArrangementVisning.SALG));
    }


    public void discount(){
        if (!txfRabat.getText().isEmpty()){
            double rabat = Double.parseDouble(txfRabat.getText());
            totalPris = controller.fraTrækRabatFraTotalPris(salg,totalPris,rabat);
            txfTotal.setText(String.valueOf(totalPris));
        }

    }
}