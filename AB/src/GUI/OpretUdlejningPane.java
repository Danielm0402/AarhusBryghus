package GUI;

import Storage.Storage;
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

public class OpretUdlejningPane extends GridPane {

    private final ControllerInterface controller;
    private ComboBox<Kunde> cbbKunder;
    private ComboBox<Produktgruppe> cbbProduktgruppe;
    private ComboBox<Pris> cbbProdukt;
    private Udlejning udlejning;
    ListView<Udlejning> lvwIgangværendeUdlejninger;
    ListView<Salgslinje> lvwUdlejning;
    private Label lblError,lblTotalPant;
    private int total =0;



    public OpretUdlejningPane(){
        controller = new Controller(Storage.getInstance());

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblvaelgKunde = new Label("Vælg kunde");

        Button btnOpretKunde = new Button("Opret kunde");
        btnOpretKunde.setOnAction(event -> this.popUpOpretKunde());


        cbbKunder = new ComboBox<>();
        cbbKunder.getItems().addAll(controller.getKunder());

        HBox hbox = new HBox(10,lblvaelgKunde,cbbKunder,btnOpretKunde);

        lvwUdlejning = new ListView<>();
        lvwUdlejning.setMaxHeight(150);


        Label lblProduktgruppe = new Label("Vælg produktgruppe");
        cbbProduktgruppe = new ComboBox<>();
        cbbProduktgruppe.getItems().addAll(controller.getProduktgrupper(EnumArrangementVisning.UDLEJNING));
        ChangeListener<Produktgruppe> listener = (ov, oldValue, newValue) -> produktgruppeChanged(newValue);
        cbbProduktgruppe.getSelectionModel().selectedItemProperty().addListener(listener);

        HBox hbox2 = new HBox(10,lblProduktgruppe,cbbProduktgruppe);

        Label lblProdukt = new Label("Vælg produkt");
        cbbProdukt = new ComboBox<>();

        HBox hbox3 = new HBox(10,lblProdukt,cbbProdukt);

        Button btnTilføjProduktPris = new Button("Tilføj Produkt");
        btnTilføjProduktPris.setOnAction(event -> tilføjProduktPris());

        lblTotalPant = new Label("Pant til afregning: "+total+",-");


//        ---------- knapper til afregning af pant ---------------------------
        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(0)));

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(1)));

        Button btnMobilpay = new Button("Mobilpay");
        btnMobilpay.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(3)));

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> Payment(controller.getBetalingsmetoder().get(4)));

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnMobilpay,btnRegning);
//        -------------------------------------------------------------------------
        lblError = new Label();

//        ---------- listview over igangværende udlejninger ude i højre--------------------

        Label lblIgangværendeUdlejninger = new Label("Igangværende udlejninger:");
        lvwIgangværendeUdlejninger = new ListView<>();
//------------------------------------------------------
        Button btnAfleverUdlejning = new Button("Aflever valgte udlejning");
        btnAfleverUdlejning.setOnAction(event -> AfleverUdlejning());





        VBox vbox = new VBox(10,hbox,hbox2,hbox3,btnTilføjProduktPris,lblError,lvwUdlejning,lblTotalPant,hboxBetaling);
        this.add(vbox,0,1);

        VBox vbox2 = new VBox(10,lblIgangværendeUdlejninger,lvwIgangværendeUdlejninger,btnAfleverUdlejning);
        this.add(vbox2,1,1);

    }

    private void AfleverUdlejning() {
        Udlejning valgteUdlejning = lvwIgangværendeUdlejninger.getSelectionModel().getSelectedItem();
        controller.setUdlejningAfleveret(valgteUdlejning);
        lvwIgangværendeUdlejninger.getItems().clear();
        lvwIgangværendeUdlejninger.getItems().setAll(controller.getUdlejningerIkkeAfleveret());

    }

    private void Payment(Betalingsmetode betalingsmetode) {
        if (lvwUdlejning.getItems().isEmpty()){
            lblError.setText("Tilføj produkter før du udlejer");
        }
        else {
            controller.setBetalingsmetode(udlejning, betalingsmetode);
            lvwIgangværendeUdlejninger.getItems().setAll(controller.getUdlejningerIkkeAfleveret());
            udlejning = null; // sætter salg til null så den sletter salget
            lvwUdlejning.getItems().clear();
            total = 0;
            lblTotalPant.setText("Pant til afregning: " + total + ",-");
        }

    }

    private void tilføjProduktPris() {
        if (cbbKunder.getSelectionModel().getSelectedItem() == null|| cbbProdukt.getSelectionModel().getSelectedItem() == null){
            lblError.setText("Nogle felter mangler at blive udfyldt");
        }
        else {
            Pris pris = cbbProdukt.getSelectionModel().getSelectedItem();
            if (udlejning == null) {
                udlejning = controller.createUdlejning(cbbKunder.getSelectionModel().getSelectedItem());
                udlejning.setKunde(cbbKunder.getSelectionModel().getSelectedItem());
            }
            controller.createSalgsLinje(udlejning, 1, pris);
            lvwUdlejning.getItems().setAll(udlejning.getSalgsLinjer());
            total+=pris.getProdukt().getPant();
            lblTotalPant.setText("Pant til afregning: "+total+",-");
            lblError.setText("");
        }
    }

    private void produktgruppeChanged(Produktgruppe newValue) {
        Produktgruppe selectedproduktgruppe = cbbProduktgruppe.getSelectionModel().getSelectedItem();
        cbbProdukt.getItems().clear();
        if (selectedproduktgruppe!=null){

//            Magic number "1" nedenfor betyder, at udlejning altid bare skal bruge arrangementet "daglig butikssalg".
//            Der er ikke andre salgssituationer, hvor der skal udlejes.
            cbbProdukt.getItems().addAll(controller.getPriserFromArrangementWithinProduktgruppe(controller.getArrangementer().get(1),selectedproduktgruppe)); //todo skal nok gette Daglig Butiks Priser indenfor denne produktgruppe -.-
        }
    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
        updateControls();
        cbbKunder.getSelectionModel().selectLast();
    }


    public void updateControls() {
        lblError.setText("");
        cbbKunder.getItems().clear();
        lvwUdlejning.getItems().clear();
        cbbKunder.getItems().addAll(controller.getKunder());
        cbbProduktgruppe.getItems().clear();
        cbbProduktgruppe.getItems().addAll(controller.getProduktgrupper(EnumArrangementVisning.UDLEJNING));
        cbbProdukt.getItems().clear();
    }
}
