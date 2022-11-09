package application.Controller;

import GUI.ControllerInterface;
import application.model.EnumArrangementVisning;
import application.model.Produktgruppe;
import application.model.Rundvisning;
import application.model.Udlejning;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerTest {

    public Controller cont;

    @BeforeEach
    void setUp() {
        cont = Controller.getTestController();
    }


    //getProduktgrupper tests
    @Test
    void TC1_getProduktgrupper_3SALG() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Produktgruppe> alleproduktgrupper = new ArrayList<>();
        when(storageMock.getProduktgrupper()).thenReturn(alleproduktgrupper);

        Produktgruppe produktgruppeMock = mock(Produktgruppe.class);

        alleproduktgrupper.add(produktgruppeMock);
        alleproduktgrupper.add(produktgruppeMock);
        alleproduktgrupper.add(produktgruppeMock);
        when(produktgruppeMock.getVisning()).thenReturn(EnumArrangementVisning.SALG, EnumArrangementVisning.SALG, EnumArrangementVisning.SALG);

        int forventetProduktgrupperUnderSALG = 3;

        //Act
        int faktiskProduktgrupperUnderSALG = controller.getProduktgrupper(EnumArrangementVisning.SALG).size();

        //Assert
        assertEquals(forventetProduktgrupperUnderSALG, faktiskProduktgrupperUnderSALG);
    }

    @Test
    void TC2_getProduktgrupper_2SALG() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Produktgruppe> alleproduktgrupper = new ArrayList<>();
        when(storageMock.getProduktgrupper()).thenReturn(alleproduktgrupper);

        Produktgruppe produktgruppeMock = mock(Produktgruppe.class);

        alleproduktgrupper.add(produktgruppeMock);
        alleproduktgrupper.add(produktgruppeMock);
        alleproduktgrupper.add(produktgruppeMock);
        when(produktgruppeMock.getVisning()).thenReturn(EnumArrangementVisning.SALG, EnumArrangementVisning.RUNDVISNING, EnumArrangementVisning.SALG);

        int forventetProduktgrupperUnderSALG = 2;

        //Act
        int faktiskProduktgrupperUnderSALG = controller.getProduktgrupper(EnumArrangementVisning.SALG).size();

        //Assert
        assertEquals(forventetProduktgrupperUnderSALG, faktiskProduktgrupperUnderSALG);
    }

    @Test
    void TC3_getProduktgrupper_0SALG() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Produktgruppe> alleproduktgrupper = new ArrayList<>();
        when(storageMock.getProduktgrupper()).thenReturn(alleproduktgrupper);

        Produktgruppe produktgruppeMock = mock(Produktgruppe.class);

        alleproduktgrupper.add(produktgruppeMock);
        alleproduktgrupper.add(produktgruppeMock);
        alleproduktgrupper.add(produktgruppeMock);
        when(produktgruppeMock.getVisning()).thenReturn(EnumArrangementVisning.UDLEJNING, EnumArrangementVisning.RUNDVISNING, EnumArrangementVisning.RUNDVISNING);

        int forventetProduktgrupperUnderSALG = 0;

        //Act
        int faktiskProduktgrupperUnderSALG = controller.getProduktgrupper(EnumArrangementVisning.SALG).size();

        //Assert
        assertEquals(forventetProduktgrupperUnderSALG, faktiskProduktgrupperUnderSALG);
    }

    @Test
    void TC4_getProduktgrupper_noInput() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Produktgruppe> alleproduktgrupper = new ArrayList<>();
        when(storageMock.getProduktgrupper()).thenReturn(alleproduktgrupper);

        Produktgruppe produktgruppeMock = mock(Produktgruppe.class);

        alleproduktgrupper.add(produktgruppeMock);
        alleproduktgrupper.add(produktgruppeMock);
        alleproduktgrupper.add(produktgruppeMock);

        int forventetProduktgrupperUnderSALG = 0;

        //Act
        int faktiskProduktgrupperUnderSALG = controller.getProduktgrupper(EnumArrangementVisning.SALG).size();

        //Assert
        assertEquals(forventetProduktgrupperUnderSALG, faktiskProduktgrupperUnderSALG);
    }



    //getRundvisning tests
    @Test
    void TC1_getRundvisning_2ikkeBetalt() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Rundvisning> rundvisninger = new ArrayList<>();
        when(storageMock.getRundvisninger()).thenReturn(rundvisninger);

        Rundvisning rundvisningMock = mock(Rundvisning.class);
        rundvisninger.add(rundvisningMock);
        rundvisninger.add(rundvisningMock);
        rundvisninger.add(rundvisningMock);
        when(rundvisningMock.isErBetalt()).thenReturn(false, true, false);

        int forventetIkkeBetalt = 2;

        //Act
        int faktiskIkkeBetalt = controller.getRundvisning(false).size();

        //Assert
        assertEquals(forventetIkkeBetalt, faktiskIkkeBetalt);
    }

    @Test
    void TC2_getRundvisning_3ikkeBetalt() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Rundvisning> rundvisninger = new ArrayList<>();
        when(storageMock.getRundvisninger()).thenReturn(rundvisninger);

        Rundvisning rundvisningMock = mock(Rundvisning.class);
        rundvisninger.add(rundvisningMock);
        rundvisninger.add(rundvisningMock);
        rundvisninger.add(rundvisningMock);
        when(rundvisningMock.isErBetalt()).thenReturn(false, false, false);

        int forventetIkkeBetalt = 3;

        //Act
        int faktiskIkkeBetalt = controller.getRundvisning(false).size();

        //Assert
        assertEquals(forventetIkkeBetalt, faktiskIkkeBetalt);
    }

    @Test
    void TC3_getRundvisning_0ikkeBetalt() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Rundvisning> rundvisninger = new ArrayList<>();
        when(storageMock.getRundvisninger()).thenReturn(rundvisninger);

        Rundvisning rundvisningMock = mock(Rundvisning.class);
        rundvisninger.add(rundvisningMock);
        rundvisninger.add(rundvisningMock);
        rundvisninger.add(rundvisningMock);
        when(rundvisningMock.isErBetalt()).thenReturn(true, true, true);

        int forventetIkkeBetalt = 0;

        //Act
        int faktiskIkkeBetalt = controller.getRundvisning(false).size();

        //Assert
        assertEquals(forventetIkkeBetalt, faktiskIkkeBetalt);
    }

    @Test
    void TC4_getRundvisning_noInput() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Rundvisning> rundvisninger = new ArrayList<>();
        when(storageMock.getRundvisninger()).thenReturn(rundvisninger);

        Rundvisning rundvisningMock = mock(Rundvisning.class);
        rundvisninger.add(rundvisningMock);
        rundvisninger.add(rundvisningMock);
        rundvisninger.add(rundvisningMock);

        int forventetIkkeBetalt = 3;

        //Act
        int faktiskIkkeBetalt = controller.getRundvisning(false).size();

        //Assert
        assertEquals(forventetIkkeBetalt, faktiskIkkeBetalt);
    }



    //getUdlejningerIkkeAflevert tests
    @Test
    void TC1_getUdlejningerIkkeAfleveret_1ikkeAfleveret() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Udlejning> udlejninger = new ArrayList<>();
        when(storageMock.getUdlejninger()).thenReturn(udlejninger);

        Udlejning udlejningsMock = mock(Udlejning.class);
        udlejninger.add(udlejningsMock);
        udlejninger.add(udlejningsMock);
        udlejninger.add(udlejningsMock);
        when(udlejningsMock.erAfleveret()).thenReturn(true, false, true);

        int forventetIkkeAfleveret = 1;

        //Act
        int faktiskIkkeAfleveret = controller.getUdlejningerIkkeAfleveret().size();

        //Assert
        assertEquals(forventetIkkeAfleveret, faktiskIkkeAfleveret);
    }

    @Test
    void TC2_getUdlejningerIkkeAfleveret_0ikkeAfleveret() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Udlejning> udlejninger = new ArrayList<>();
        when(storageMock.getUdlejninger()).thenReturn(udlejninger);

        Udlejning udlejningsMock = mock(Udlejning.class);
        udlejninger.add(udlejningsMock);
        udlejninger.add(udlejningsMock);
        udlejninger.add(udlejningsMock);
        when(udlejningsMock.erAfleveret()).thenReturn(true, true, true);

        int forventetIkkeAfleveret = 0;

        //Act
        int faktiskIkkeAfleveret = controller.getUdlejningerIkkeAfleveret().size();

        //Assert
        assertEquals(forventetIkkeAfleveret, faktiskIkkeAfleveret);
    }

    @Test
    void TC3_getUdlejningerIkkeAfleveret_3ikkeAfleveret() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Udlejning> udlejninger = new ArrayList<>();
        when(storageMock.getUdlejninger()).thenReturn(udlejninger);

        Udlejning udlejningsMock = mock(Udlejning.class);
        udlejninger.add(udlejningsMock);
        udlejninger.add(udlejningsMock);
        udlejninger.add(udlejningsMock);
        when(udlejningsMock.erAfleveret()).thenReturn(false, false, false);

        int forventetIkkeAfleveret = 3;

        //Act
        int faktiskIkkeAfleveret = controller.getUdlejningerIkkeAfleveret().size();

        //Assert
        assertEquals(forventetIkkeAfleveret, faktiskIkkeAfleveret);
    }

    @Test
    void TC4_getUdlejningerIkkeAfleveret_noInput() {
        //Arrange
        StorageInterface storageMock = mock(StorageInterface.class);
        Controller controller = new Controller((storageMock));

        ArrayList<Udlejning> udlejninger = new ArrayList<>();
        when(storageMock.getUdlejninger()).thenReturn(udlejninger);

        Udlejning udlejningsMock = mock(Udlejning.class);
        udlejninger.add(udlejningsMock);
        udlejninger.add(udlejningsMock);
        udlejninger.add(udlejningsMock);

        int forventetIkkeAfleveret = 3;

        //Act
        int faktiskIkkeAfleveret = controller.getUdlejningerIkkeAfleveret().size();

        //Assert
        assertEquals(forventetIkkeAfleveret, faktiskIkkeAfleveret);
    }


}