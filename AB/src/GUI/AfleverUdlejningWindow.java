package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.Salgslinje;
import application.model.Udlejning;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class AfleverUdlejningWindow extends Stage {
    private final ControllerInterface controller;
    private Label lblBetaltPant, lblTilBetaling, lblReturIUbrudtEmballage, lblError, lblError2,lblUdlejetFor;
    private Button btnRetur, btnPant;
    private Udlejning valgteUdlejning;
    private ListView<Salgslinje>lvwprodukter, lvwRetur, lvwPant;
    private int retur,pant;
    private double totalPris;
    private ArrayList<Salgslinje> produkter = new ArrayList<>();
    private ArrayList<Salgslinje> returliste = new ArrayList<>();
    private ArrayList<Salgslinje> pantliste = new ArrayList<>();

    public AfleverUdlejningWindow(String aflever_udlejning, Udlejning valgteUdlejning){
        controller = new Controller(Storage.getInstance());

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.valgteUdlejning = valgteUdlejning;


        this.setTitle("Aflever udlejning");
        GridPane pane = new GridPane();


        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);

    }


    public void initContent(GridPane pane){
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);


        lvwprodukter = new ListView<>();
        pane.add(lvwprodukter,1,1);
        lvwprodukter.setMaxHeight(300);
        produkter = valgteUdlejning.getSalgsLinjer();
        lvwprodukter.getItems().setAll(produkter);

        lblError = new Label();
        lblError2 = new Label();
        btnPant = new Button("Pant ikke afleveret ->");
        btnPant.setOnAction(event -> bntPant());
        btnRetur = new Button("Retur i ubrudt emballage ->");
        btnRetur.setOnAction(event -> bntRetur());



        VBox vBox = new VBox(47, lblError,btnRetur, lblError2,btnPant);
        pane.add(vBox,2,1);


        lvwRetur = new ListView<>();
        lvwRetur.setMaxHeight(145);

        lvwPant = new ListView<>();
        lvwPant.setMaxHeight(145);

        VBox vBox2 = new VBox(10,lvwRetur,lvwPant);
        pane.add(vBox2,3,1);

//        ------labels til udregning----------
        pant = controller.getTotalPant(valgteUdlejning);
        double subtotal = controller.udregnTotalPris(valgteUdlejning);
        totalPris = subtotal - pant;

        lblUdlejetFor = new Label("Udlejet for: "+subtotal+",-");
        pane.add(lblUdlejetFor,1,2);

        lblBetaltPant = new Label("Pant kommet retur: "+pant+",-");
        pane.add(lblBetaltPant,1,3);

        lblReturIUbrudtEmballage = new Label("Retur i ubrudt emballage: "+ retur+",-");
        pane.add(lblReturIUbrudtEmballage, 1,4);

        lblTilBetaling = new Label("Til betaling: "+ totalPris+",-");
        pane.add(lblTilBetaling,1,5);

//        --------------------- Klik på linjen for at fjerne den--------------
        lvwPant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                Salgslinje valgteLinje = lvwPant.getSelectionModel().getSelectedItem();

                if (valgteLinje!=null){

                    int pantværdi = valgteLinje.getProdukt().getPant();

                        produkter.add(valgteLinje);
                        pantliste.remove(valgteLinje);

                        pant += pantværdi;

                        totalPris -= pantværdi;

                        updateControls();


                }

            }
        });

        lvwRetur.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Salgslinje valgteLinje = lvwRetur.getSelectionModel().getSelectedItem();

                if (valgteLinje!=null){
                    int enhedspris = valgteLinje.getEnhedspris();

                    produkter.add(valgteLinje);
                    returliste.remove(valgteLinje);

                    retur -= enhedspris;

                    totalPris += enhedspris;
                    updateControls();
                }
            }
        });


        //----------- - Dankort kontant, klippekort, mobilpay, regnning - --------------------

        Button btnDankort = new Button("Dankort");
        btnDankort.setOnAction(event -> afleverUdlejning());

        Button btnKontant = new Button("Kontant");
        btnKontant.setOnAction(event -> afleverUdlejning());

        Button btnMobilpay = new Button("Mobilpay");
        btnMobilpay.setOnAction(event -> afleverUdlejning());

        Button btnRegning = new Button("Regning");
        btnRegning.setOnAction(event -> afleverUdlejning());

        HBox hboxBetaling = new HBox(btnDankort,btnKontant,btnMobilpay,btnRegning);
        pane.add(hboxBetaling,1,8);
    }

    //        --------------------ovenstående er initcontent---------------------


    private void bntRetur() {
        Salgslinje valgteSalgslinje = lvwprodukter.getSelectionModel().getSelectedItem();
        lblError.setText("");
        lblError2.setText("");

        if (valgteSalgslinje !=null) {

//            går ud fra at alle produkter der kan komme retur ubrudt, har en pant. Derfor tjek på om pant er større end 0
            if (valgteSalgslinje.getProdukt().getPant() > 0){

                int enhedspris = valgteSalgslinje.getEnhedspris();

                produkter.remove(valgteSalgslinje);
                returliste.add(valgteSalgslinje);

                retur += enhedspris;

                totalPris -= enhedspris;
                updateControls();
        }
            else {
                lblError.setText("Vælg et uåbnet produkt");
            }
        }
    }

    private void bntPant() {
        Salgslinje valgteSalgslinje = lvwprodukter.getSelectionModel().getSelectedItem();
        lblError.setText("");
        lblError2.setText("");

        if (valgteSalgslinje !=null) {
            int pantværdi = valgteSalgslinje.getProdukt().getPant();

            if (pantværdi > 0) {
                produkter.remove(valgteSalgslinje);
                pantliste.add(valgteSalgslinje);

                pant -= pantværdi;

                totalPris += pantværdi;

                updateControls();

            } else {
                lblError2.setText("Vælg et produkt med pant");
            }
        }
    }


    private void updateControls(){
        lblTilBetaling.setText("Til betaling: "+ totalPris+",-");
        lblBetaltPant.setText("Pant kommet retur: " + pant + ",-");
        lblReturIUbrudtEmballage.setText("Retur i ubrudt emballage: "+ retur+",-");
        lvwprodukter.getItems().setAll(produkter);
        lvwPant.getItems().setAll(pantliste);
        lvwRetur.getItems().setAll(returliste);

    }

    public void afleverUdlejning() {
            valgteUdlejning.setErAfleveret(true);
            this.hide();

    }
}
