package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.Salg;
import application.model.Salgslinje;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class OversigtsPane extends GridPane {
    private final ControllerInterface controller;
    private DatePicker dpDatoFra, dpDatoTil;
    private Label lblDatoFra, lblDatoTil, lblTotalPris;
    private Button btnSearch;
    private ListView<Salg> lvwSalg;
    private ListView<Salgslinje> lvwSalgslinjer;
    private double totalPris = 0;


    public OversigtsPane() {
        controller = new Controller(Storage.getInstance());

        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        Label lblArrangement = new Label("Vælg oversigt");
        this.add(lblArrangement, 1, 0);

//        -------------- combobox over hvad der skal vises ---------------
        ComboBox<Object> cbbVisning = new ComboBox<>();
        this.add(cbbVisning, 1, 1);
//        cbbVisning.getItems().addAll(controller.getArrangementer());
//        cbbVisning.getSelectionModel().selectFirst();
//        ChangeListener<Arrangement> listener = (ov, oldValue, newValue) -> selectedVisningChanged(newValue);
//        cbbVisning.getSelectionModel().selectedItemProperty().addListener(listener);

        dpDatoFra = new DatePicker();
        lblDatoFra = new Label("Fra Dato:");

        dpDatoTil = new DatePicker();
        lblDatoTil = new Label("Til Dato");

        HBox hboxDatoFra = new HBox(10,lblDatoFra,dpDatoFra);
        this.add(hboxDatoFra,2,0);

        HBox hboxDatoTil = new HBox(16,lblDatoTil,dpDatoTil);
        this.add(hboxDatoTil,2,1);

        btnSearch = new Button("Søg");
        this.add(btnSearch,2,2);
        btnSearch.setOnAction(event -> updateListViewSalg());

        lvwSalg = new ListView<>();
        this.add(lvwSalg,1,3);

        lvwSalgslinjer = new ListView<>();
        this.add(lvwSalgslinjer,2,3);

        lvwSalg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Salg salg = lvwSalg.getSelectionModel().getSelectedItem();
                lvwSalgslinjer.getItems().clear();
                lvwSalgslinjer.getItems().addAll(salg.getSalgsLinjer());
            }
        });

        lblTotalPris = new Label("Total pris: " + totalPris);
        this.add(lblTotalPris,1,4);



    }

    public ArrayList<Salg> getSalgFromGivenPeriod(){
        ArrayList<Salg> salgFromTheGivenPeriod = new ArrayList<>();
        if (dpDatoFra != null && dpDatoTil != null)
            for (int i = 0; i < controller.getSalg().size(); i++) {
                if (controller.getSalg().get(i).getDato().isEqual(dpDatoFra.getValue()) || controller.getSalg().get(i).getDato().isEqual(dpDatoTil.getValue())){
                    salgFromTheGivenPeriod.add(controller.getSalg().get(i));
                }else if (controller.getSalg().get(i).getDato().isAfter(dpDatoFra.getValue()) && controller.getSalg().get(i).getDato().isBefore(dpDatoTil.getValue())){
                    salgFromTheGivenPeriod.add(controller.getSalg().get(i));
                }
            }
        return new ArrayList<>(salgFromTheGivenPeriod);
        }

    public void updateListViewSalg(){
        if (dpDatoFra != null && dpDatoTil != null){
            totalPris = 0;
            lvwSalg.getItems().clear();
            lvwSalgslinjer.getItems().clear();
            lvwSalg.getItems().addAll(getSalgFromGivenPeriod());
            for (int i = 0; i < getSalgFromGivenPeriod().size(); i++) {
                totalPris += getSalgFromGivenPeriod().get(i).getTotalPris();
            }
            lblTotalPris.setText("Total pris: " + totalPris);
        }
    }

    private void selectedVisningChanged(DatePicker newValue) {
        System.out.println("visning ændret");
    }


    public void updateControls() {
        totalPris = 0;
        lblTotalPris.setText("Total pris: " + totalPris);
        lvwSalg.getItems().clear();
        lvwSalgslinjer.getItems().clear();
        dpDatoFra.getEditor().clear();
        dpDatoFra.setValue(null);
        dpDatoTil.getEditor().clear();
        dpDatoTil.setValue(null);

    }
}
