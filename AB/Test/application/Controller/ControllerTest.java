package application.Controller;

import GUI.ControllerInterface;
import application.model.EnumArrangementVisning;
import application.model.Produktgruppe;
import application.model.Rundvisning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    public Controller cont;

    @BeforeEach
    void setUp() {
        cont = Controller.getTestController();
    }

    @Test
    void TC1_getRundvisning_1betalt() {
        //Arrange
        Rundvisning r1 = cont.createRundvisning();
        r1.setErBetalt(true);
        int forventet = 0;

        //Act
        int resultat = cont.getRundvisning(false).size();

        //Assert
        assertEquals(forventet,resultat);
    }

    @Test
    void TC2_getRundvisning_0betalt() {
        //Arrange
        Rundvisning r1 = cont.createRundvisning();
        r1.setErBetalt(false);
        int forventet = 1;

        //Act
        int resultat = cont.getRundvisning(false).size();

        //Assert
        assertEquals(forventet,resultat);
    }

    @Test
    void TC3_getRundvisning_0rundvisninger() {
        //Arrange
        int forventet = 0;

        //Act
        int resultat = cont.getRundvisning(false).size();

        //Assert
        assertEquals(forventet,resultat);
    }


    @Test
    void TC1_getProduktgrupper() {
        //Arrange
        Produktgruppe pg1 = cont.createProduktgruppe("Øl");
        pg1.setVisning(EnumArrangementVisning.SALG);
        int forventet = 1;

        //Act
        int resultat = cont.getProduktgrupper(EnumArrangementVisning.SALG).size();

        //Assert
        assertEquals(forventet,resultat);
    }

    @Test
    void TC2_getProduktgrupper() {
        //Arrange
        Produktgruppe pg1 = cont.createProduktgruppe("Øl");
        int forventet = 0;

        //Act
        int resultat = cont.getProduktgrupper(EnumArrangementVisning.SALG).size();

        //Assert
        assertEquals(forventet,resultat);
    }




}