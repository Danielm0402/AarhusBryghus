package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.Salg;
import application.model.Salgslinje;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RedigerSalgslinjeWindow extends Stage {
    private final ControllerInterface controller;
    private Label lblError;
    private Button btnSletSalgslinje, btnGemAftaltPris;
    private Salgslinje valgteSalgsLinje;
    private Salg salg;
    private TextField txfAftaltPris;

    public RedigerSalgslinjeWindow(String navn, Salgslinje valgteSalgsLinje, Salg salg){
        controller = new Controller(Storage.getInstance());

        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.valgteSalgsLinje = valgteSalgsLinje;

        this.salg = salg;

        this.setTitle("Rediger salgslinje");
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public void initContent(GridPane pane){
        pane.setPadding(new Insets(20));
        pane.setHgap(20);
        pane.setVgap(10);


        Label lblAftaltPris = new Label("Aftalt pris:");
        pane.add(lblAftaltPris,1,1);

        txfAftaltPris = new TextField();
        pane.add(txfAftaltPris,2,1);
        txfAftaltPris.setText(String.valueOf(valgteSalgsLinje.getAftaltPris()));

        btnSletSalgslinje = new Button("Slet salgslinje");
        pane.add(btnSletSalgslinje,1,5);
        btnSletSalgslinje.setOnAction(event -> this.sletSalgslinje());

        btnGemAftaltPris = new Button("Gem pris");
        pane.add(btnGemAftaltPris,2,5);
        btnGemAftaltPris.setOnAction(event -> this.gemAftaltPris());

        lblError = new Label();
        pane.add(lblError,1,6);

    }

    private void gemAftaltPris() {
        int indtastetPris = Integer.parseInt(txfAftaltPris.getText());
        controller.setAftaltPris(valgteSalgsLinje,indtastetPris);
         this.hide();
    }

    private void sletSalgslinje() {
        controller.removeSalgsLinje(salg,valgteSalgsLinje);
        this.hide();
    }


    //Metoder:


}
