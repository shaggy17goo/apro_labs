import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

class KontoBankowe {
    private  String rodzajKonta;
    private  long nrKonta;
    private  double saldo;
    private List<String> historia= new ArrayList<String>();


    public KontoBankowe(String rodzajKonta, long nrKonta, double saldo){
        this.rodzajKonta=rodzajKonta;
        this.nrKonta=nrKonta;
        this.saldo=saldo;

    }

    public void wplac(double wplata){
        saldo=saldo+wplata;
        System.out.println("wpłacono "+ wplata+" saldo po wplacie: "+ saldo);
        historia.add("Wpłacono: "+wplata+" zł - saldo: "+saldo);
    }

    public void wyplac(double wyplata){
        saldo=saldo-wyplata;
        System.out.println("wyplata "+ wyplata+" saldo po wyplacie: "+ saldo);
        historia.add("Wypłacono: "+wyplata+" zł - saldo: "+saldo);

    }

    public String getRodzajKonta() {
        return rodzajKonta;
    }

    public long getNrKonta() {
        return nrKonta;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<String> getHistoria() {
        return historia;
    }
}
