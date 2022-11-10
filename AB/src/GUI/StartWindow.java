package GUI;

import Storage.Storage;
import application.Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {
    private ControllerInterface controller;
    @Override
    public void init() {
        controller = new Controller(Storage.getInstance());
        controller.init();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Århus Bryghus");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabSalg = new Tab("--- Salg ---");
        tabPane.getTabs().add(tabSalg);

        SalgsPane salgsPane = new SalgsPane();
        tabSalg.setContent(salgsPane);
        tabSalg.setOnSelectionChanged(event -> salgsPane.updateControls());
        // --------------------------------------------------------------------------

        Tab tabProdukt = new Tab("Opret Produkt");
        tabPane.getTabs().add(tabProdukt);

        OpretProduktPane opretProduktPane = new OpretProduktPane();
        tabProdukt.setContent(opretProduktPane);
        tabProdukt.setOnSelectionChanged(event -> opretProduktPane.updateControls());

        // --------------------------------------------------------------------------

        Tab tabProduktgruppe = new Tab("Opret Produktgruppe");
        tabPane.getTabs().add(tabProduktgruppe);

        OpretProduktgruppePane opretProduktgruppePane = new OpretProduktgruppePane();
        tabProduktgruppe.setContent(opretProduktgruppePane);
        tabProduktgruppe.setOnSelectionChanged(event -> opretProduktgruppePane.updateControls());

        // --------------------------------------------------------------------------


        Tab tabArrangement = new Tab("Opret Arrangement");
        tabPane.getTabs().add(tabArrangement);

        OpretArrangementPane opretArrangementPane = new OpretArrangementPane();
        tabArrangement.setContent(opretArrangementPane);
        tabArrangement.setOnSelectionChanged(event -> opretArrangementPane.updateControls());
        // --------------------------------------------------------------------------

        Tab tabRundsvisning = new Tab("Rundvisning");
        tabPane.getTabs().add(tabRundsvisning);

        rundvisningPane rundvisningPane = new rundvisningPane();
        tabRundsvisning.setContent(rundvisningPane);
        tabRundsvisning.setOnSelectionChanged(event -> rundvisningPane.updateControls());

        // --------------------------------------------------------------------------
        Tab tabUdlejning = new Tab("Udlejning");
        tabPane.getTabs().add(tabUdlejning);

        udlejningPane udlejningPane = new udlejningPane();
        tabUdlejning.setContent(udlejningPane);
        tabUdlejning.setOnSelectionChanged(event -> udlejningPane.updateControls());

        // --------------------------------------------------------------------------
        Tab tabOversigt = new Tab("Oversigt");
        tabPane.getTabs().add(tabOversigt);

        OversigtsPane oversigtsPane = new OversigtsPane();
        tabOversigt.setContent(oversigtsPane);
        tabOversigt.setOnSelectionChanged(event -> oversigtsPane.updateControls());
    }
}
