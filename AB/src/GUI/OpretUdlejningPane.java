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
    private ListView<Udlejning> lvwIgangværendeUdlejninger;
    private ListView<Salgslinje> lvwUdlejning;
    private ListView<Salgslinje> lvwAlleUdlejedeProdukter;
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
        cbbKunder.setMaxWidth(100);

        HBox hbox = new HBox(10,lblvaelgKunde,cbbKunder,btnOpretKunde);

        lvwUdlejning = new ListView<>();
        lvwUdlejning.setMaxHeight(150);


        Label lblProduktgruppe = new Label("Vælg produktgruppe");
        cbbProduktgruppe = new ComboBox<>();
        cbbProduktgruppe.getItems().addAll(controller.getProduktgrupper(EnumArrangementVisning.UDLEJNING));
        cbbProduktgruppe.setPrefWidth(146);
        ChangeListener<Produktgruppe> listener = (ov, oldValue, newValue) -> produktgruppeChanged(newValue);
        cbbProduktgruppe.getSelectionModel().selectedItemProperty().addListener(listener);

        HBox hbox2 = new HBox(10,lblProduktgruppe,cbbProduktgruppe);

        Label lblProdukt = new Label("Vælg produkt");
        cbbProdukt = new ComboBox<>();
        cbbProdukt.setPrefWidth(146);

        HBox hbox3 = new HBox(48,lblProdukt,cbbProdukt);

        Button btnTilføjProduktPris = new Button("Tilføj Produkt");
        btnTilføjProduktPris.setOnAction(event -> tilføjProduktPris());

        lblTotalPant = new Label("Pant til afregning: "+total+",-");


//        ---------- knapper til afregning af pant ---------------------------
        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> Payment(EnumBetalingsmetode.DANKORT));

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> Payment(EnumBetalingsmetode.KONTANT));

        Button btnKlippekort = new Button("Klippekort");
        btnKlippekort.setOnAction(event -> Payment(EnumBetalingsmetode.KLIPPEKORT));

        Button btnMobilepay = new Button("Mobilepay");
        btnMobilepay.setOnAction(event -> Payment(EnumBetalingsmetode.MOBILEPAY));

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> Payment(EnumBetalingsmetode.REGNING));

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnMobilepay,btnRegning);
//        -------------------------------------------------------------------------
        lblError = new Label();

//        ---------- listview over igangværende udlejninger i midten--------------------

        Label lblIgangværendeUdlejninger = new Label("Igangværende udlejninger:");
        lvwIgangværendeUdlejninger = new ListView<>();
//------------------------------------------------------
        Button btnAfleverUdlejning = new Button("Aflever valgte udlejning");
        btnAfleverUdlejning.setOnAction(event -> AfleverUdlejning());

//        ---------- oversigt over alle udlejede produkter--------------------

        Label lblAlleUdlejedeProdukter = new Label("Oversigt over ALLE udlejede produkter:");
        lvwAlleUdlejedeProdukter = new ListView<>();
//---------------------------------------


        VBox vbox = new VBox(10,hbox,hbox2,hbox3,btnTilføjProduktPris,lblError,lvwUdlejning,lblTotalPant,hboxBetaling);
        this.add(vbox,0,1);

        VBox vbox2 = new VBox(10,lblIgangværendeUdlejninger,lvwIgangværendeUdlejninger,btnAfleverUdlejning);
        this.add(vbox2,1,1);

        VBox vbox3 = new VBox(10,lblAlleUdlejedeProdukter,lvwAlleUdlejedeProdukter);
        this.add(vbox3,2,1);

    }

    private void Payment(EnumBetalingsmetode betalingsmetode) {
        if (lvwUdlejning.getItems().isEmpty()){
            lblError.setText("Tilføj produkter før du udlejer");
        }
        else {
            Betalingsmetode b1 = new Betalingsmetode(betalingsmetode);
            controller.setBetalingsmetode(udlejning, b1);
            lvwIgangværendeUdlejninger.getItems().setAll(controller.getUdlejningerIkkeAfleveret());
            lvwIgangværendeUdlejninger.getSelectionModel().selectLast();
            udlejning = null; // sætter salg til null så den sletter salget
            lvwUdlejning.getItems().clear();
            total = 0;
            lblTotalPant.setText("Pant til afregning: " + total + ",-");
            updateAlleUdlejedeProdukterOversigt();
        }
    }

    private void AfleverUdlejning() {
        Udlejning valgteUdlejning = lvwIgangværendeUdlejninger.getSelectionModel().getSelectedItem();
        if(valgteUdlejning != null ) {
            AfleverUdlejningWindow afleverUdlejningWindow = new AfleverUdlejningWindow("Aflever Udlejning",valgteUdlejning);
            afleverUdlejningWindow.showAndWait();

            lvwIgangværendeUdlejninger.getItems().clear();
            lvwIgangværendeUdlejninger.getItems().setAll(controller.getUdlejningerIkkeAfleveret());
            updateAlleUdlejedeProdukterOversigt();
        }
        else {
            lblError.setText("Vælg en udlejning at aflevere");
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
            cbbProdukt.getItems().addAll(controller.getPriserFromArrangementWithinProduktgruppe(controller.getArrangementer().get(1),selectedproduktgruppe));
            cbbProdukt.getSelectionModel().selectFirst();
        }
    }


    public void popUpOpretKunde(){
        OpretKundeWindow opretKunde = new OpretKundeWindow("Opret kunde");
        opretKunde.showAndWait();
        updateControls();
        cbbKunder.getSelectionModel().selectLast();
    }

    public void updateAlleUdlejedeProdukterOversigt(){
        lvwAlleUdlejedeProdukter.getItems().setAll(controller.getAlleProdukterIkkeAfleveret());
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
