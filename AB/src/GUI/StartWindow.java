package GUI;

import application.Controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartWindow extends Application {


    @Override
    public void init() {
        Controller.init();
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


        Tab tabProdukt = new Tab("Opret Produkt");
        tabPane.getTabs().add(tabProdukt);

        OpretProduktPane opretProduktPane = new OpretProduktPane();
        tabProdukt.setContent(opretProduktPane);
        tabProdukt.setOnSelectionChanged(event -> opretProduktPane.updateControls());

        Tab tabArrangement = new Tab("Opret Arrangement");
        tabPane.getTabs().add(tabArrangement);

        OpretArrangementPane opretArrangementPane = new OpretArrangementPane();
        tabArrangement.setContent(opretArrangementPane);
        tabArrangement.setOnSelectionChanged(event -> opretArrangementPane.updateControls());
//
//        Tab tabUdflugter = new Tab("Opret Udflugter");
//        tabPane.getTabs().add(tabUdflugter);
//
//        UdflugtPane udflugtPane = new UdflugtPane();
//        tabUdflugter.setContent(udflugtPane);
//        tabUdflugter.setOnSelectionChanged(event -> udflugtPane.updateControls());
//
//        Tab tabTilmeldinger = new Tab("Opret tilmeldinger");
//        tabPane.getTabs().add(tabTilmeldinger);
//
//        TilmeldingPane tilmeldingPane = new TilmeldingPane();
//        tabTilmeldinger.setContent(tilmeldingPane);
//        tabTilmeldinger.setOnSelectionChanged(event -> tilmeldingPane.updateControls());
    }

}
