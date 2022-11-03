package GUI;

import Storage.Storage;
import application.Controller.Controller;
import application.model.Arrangement;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class OversigtsPane extends GridPane {
    private final ControllerInterface controller;

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




    }

    private void selectedVisningChanged(Arrangement newValue) {
        System.out.println("visning ændret");
    }


    public void updateControls() {


    }
}
