package application.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SalgTest {

    Salg salg;

    @BeforeEach
    void setUp() {
        salg = new Salg();
    }


    //createSalgslinje(antal, pris)
    @Test
    void TC1_createSalgslinje_antal_og_pris_1oprettet(){
        //Arrange
        Pris mockPris = mock(Pris.class);

        int forventet = 1;

        //Act
        salg.createSalgslinje(1,mockPris);
        int faktisk = salg.getSalgsLinjer().size();

        //Assert
        assertEquals(forventet, faktisk);
    }

    @Test
    void TC2_createSalgslinje_antal_og_pris_2oprettet(){
        //Arrange
        Pris mockPris = mock(Pris.class);

        int forventet = 2;

        //Act
        salg.createSalgslinje(1,mockPris);
        salg.createSalgslinje(1,mockPris);
        int faktisk = salg.getSalgsLinjer().size();

        //Assert
        assertEquals(forventet, faktisk);
    }

    @Test
    void TC3_createSalgslinje_antal_og_pris_noInput(){
        //Arrange
        Pris mockPris = mock(Pris.class);

        int forventet = 0;

        //Act
        int faktisk = salg.getSalgsLinjer().size();

        //Assert
        assertEquals(forventet, faktisk);
    }


    //createSalgslinje(antal, pris, klip)
    @Test
    void TC4_createSalgslinje_antal_og_pris_og_klip_1oprettet(){
        //Arrange
        Pris mockPris = mock(Pris.class);

        int forventet = 1;

        //Act
        salg.createSalgslinje(1,mockPris, 1);
        int faktisk = salg.getSalgsLinjer().size();

        //Assert
        assertEquals(forventet, faktisk);
    }

    @Test
    void TC5_createSalgslinje_antal_og_pris_og_klip_2oprettet(){
        //Arrange
        Pris mockPris = mock(Pris.class);

        int forventet = 2;

        //Act
        salg.createSalgslinje(1,mockPris, 1);
        salg.createSalgslinje(1,mockPris, 1);
        int faktisk = salg.getSalgsLinjer().size();

        //Assert
        assertEquals(forventet, faktisk);
    }

    @Test
    void TC6_createSalgslinje_antal_og_pris_og_klip_noInput(){
        //Arrange
        Pris mockPris = mock(Pris.class);

        int forventet = 0;

        //Act
        int faktisk = salg.getSalgsLinjer().size();

        //Assert
        assertEquals(forventet, faktisk);
    }



    //incrementSalgslinje()
    @Test
    void TC7_incrementSalgslinje_true() {
        //Arrange
        Pris mockPris = mock(Pris.class);
        salg.createSalgslinje(2,mockPris);

        //Act
        boolean erTrue = salg.incrementSalgslinje(mockPris);

        //Assert
        assertEquals(true,erTrue);
    }

    @Test
    void TC8_incrementSalgslinje_false() {
        //Arrange
        Pris mockPris = mock(Pris.class);
        Pris DifferentMockPris = mock(Pris.class);
        salg.createSalgslinje(2,mockPris);

        //Act
        boolean erTrue = salg.incrementSalgslinje(DifferentMockPris);

        //Assert
        assertEquals(false,erTrue);
    }

    @Test
    void TC9_incrementSalgslinje_false() {
        //Arrange
        Pris mockPris = mock(Pris.class);

        //Act
        boolean erTrue = salg.incrementSalgslinje(mockPris);

        //Assert
        assertEquals(false,erTrue);
    }



    //getTotalPris()
    @Test
    void TC10_getTotalPris_10dkk_dankort(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Betalingsmetode b1 = new Betalingsmetode(EnumBetalingsmetode.DANKORT);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1, pris);
        salg.setBetalingsmetode(b1);

        double forventetTotalPris = 10;

        //Act
        double faktiskTotalPris = salg.getTotalPris();

        //Assert
        assertEquals(forventetTotalPris,faktiskTotalPris);
    }

    @Test
    void TC11_getTotalPris_20dkk_dankort(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Betalingsmetode dankort = new Betalingsmetode(EnumBetalingsmetode.DANKORT);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1, pris);
        salg.createSalgslinje(1, pris);
        salg.setBetalingsmetode(dankort);

        double forventetTotalPris = 20;

        //Act
        double faktiskTotalPris = salg.getTotalPris();

        //Assert
        assertEquals(forventetTotalPris,faktiskTotalPris);
    }

    @Test
    void TC12_getTotalPris_0dkk_dankort(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Betalingsmetode dankort = new Betalingsmetode(EnumBetalingsmetode.DANKORT);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.setBetalingsmetode(dankort);

        double forventetTotalPris = 0;

        //Act
        double faktiskTotalPris = salg.getTotalPris();

        //Assert
        assertEquals(forventetTotalPris,faktiskTotalPris);
    }

    @Test
    void TC13_getTotalPris_0dkk_klippekort(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Betalingsmetode klippekort = new Betalingsmetode(EnumBetalingsmetode.KLIPPEKORT);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1, pris);
        salg.setBetalingsmetode(klippekort);

        double forventetTotalPris = 0;

        //Act
        double faktiskTotalPris = salg.getTotalPris();

        //Assert
        assertEquals(forventetTotalPris,faktiskTotalPris);
    }



    //getTotalKlip()
    @Test
    void TC14_getTotalKlip_2klip_klippekort(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Betalingsmetode klippekort = new Betalingsmetode(EnumBetalingsmetode.KLIPPEKORT);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1, pris, 2);
        salg.setBetalingsmetode(klippekort);

        double forventetTotalKlip = 2;

        //Act
        double faktiskTotalKlip = salg.getTotalKlip();

        //Assert
        assertEquals(forventetTotalKlip,faktiskTotalKlip);
    }

    @Test
    void TC15_getTotalKlip_4klip_klippekort(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Betalingsmetode klippekort = new Betalingsmetode(EnumBetalingsmetode.KLIPPEKORT);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1, pris, 2);
        salg.createSalgslinje(1, pris, 2);
        salg.setBetalingsmetode(klippekort);

        double forventetTotalKlip = 4;

        //Act
        double faktiskTotalKlip = salg.getTotalKlip();

        //Assert
        assertEquals(forventetTotalKlip,faktiskTotalKlip);
    }

    @Test
    void TC16_getTotalKlip_0klip_klippekort(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Betalingsmetode klippekort = new Betalingsmetode(EnumBetalingsmetode.KLIPPEKORT);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.setBetalingsmetode(klippekort);

        double forventetTotalKlip = 0;

        //Act
        double faktiskTotalKlip = salg.getTotalKlip();

        //Assert
        assertEquals(forventetTotalKlip,faktiskTotalKlip);
    }

    @Test
    void TC17_getTotalKlip_0klip_dankort(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Betalingsmetode dankort = new Betalingsmetode(EnumBetalingsmetode.DANKORT);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1,pris,2);
        salg.setBetalingsmetode(dankort);

        double forventetTotalKlip = 0;

        //Act
        double faktiskTotalKlip = salg.getTotalKlip();

        //Assert
        assertEquals(forventetTotalKlip,faktiskTotalKlip);
    }


    //udregnTotalPris()
    @Test
    void TC18_udregnTotalPris_10(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1,pris,2);

        double forventetTotalPris = 10;

        //act
        double faktiskTotalPris = salg.udregnTotalPris();

        //Assert
        assertEquals(forventetTotalPris,faktiskTotalPris);
    }

    @Test
    void TC19_udregnTotalPris_20(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1,pris,2);
        salg.createSalgslinje(1,pris,2);

        double forventetTotalPris = 20;

        //act
        double faktiskTotalPris = salg.udregnTotalPris();

        //Assert
        assertEquals(forventetTotalPris,faktiskTotalPris);
    }

    @Test
    void TC20_udregnTotalPris_noInput(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        double forventetTotalPris = 0;

        //act
        double faktiskTotalPris = salg.udregnTotalPris();

        //Assert
        assertEquals(forventetTotalPris,faktiskTotalPris);
    }


    //udregnTotalKlip()
    @Test
    void TC21_udregnTotalKlip_2klip(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1,pris,2);

        double forventetTotalKlip = 2;

        //act
        double faktiskTotalKlip = salg.udregnTotalKlip();

        //Assert
        assertEquals(forventetTotalKlip,faktiskTotalKlip);
    }

    @Test
    void TC22_udregnTotalKlip_4(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        salg.createSalgslinje(1,pris,2);
        salg.createSalgslinje(1,pris,2);

        double forventetTotalKlip = 4;

        //act
        double faktiskTotalKlip = salg.udregnTotalKlip();

        //Assert
        assertEquals(forventetTotalKlip,faktiskTotalKlip);
    }

    @Test
    void TC23_udregnTotalKlip_noInput(){
        //Arrange
        Produkt mockedProdukt = mock(Produkt.class);
        Arrangement mockedArrangement = mock(Arrangement.class);
        Pris pris = new Pris(10, mockedProdukt, mockedArrangement);

        double forventetTotalKlip = 0;

        //act
        double faktiskTotalKlip = salg.udregnTotalKlip();

        //Assert
        assertEquals(forventetTotalKlip,faktiskTotalKlip);
    }


    //



}