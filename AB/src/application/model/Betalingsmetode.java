package application.model;

public class Betalingsmetode {
    private EnumBetalingsmetode betalingstype;

    public Betalingsmetode (EnumBetalingsmetode enumBetalingsmetode){
        this.betalingstype=enumBetalingsmetode;
    }

    public EnumBetalingsmetode getBetalingstype() {
        return betalingstype;
    }

    public void setBetalingstype(EnumBetalingsmetode betalingstype) {
        this.betalingstype = betalingstype;
    }

    @Override
    public String toString() {
        return betalingstype.toString();
    }
}
