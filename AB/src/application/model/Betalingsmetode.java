package application.model;

public class Betalingsmetode {
    private String betalingstype;

    public Betalingsmetode (String betalingstype){
        this.betalingstype=betalingstype;
    }

    public String getBetalingstype() {
        return betalingstype;
    }

    public void setBetalingstype(String betalingstype) {
        this.betalingstype = betalingstype;
    }

    @Override
    public String toString() {
        return betalingstype;
    }
}
