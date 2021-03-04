import java.util.ArrayList;
import java.util.List;

class KlientBanku {

    private int liczbaKont=0;
    private DaneOsobowe daneOsobowe;
    private String dataDolaczenia;
    private List<KontoBankowe> konta= new ArrayList<KontoBankowe>();


    public KlientBanku(DaneOsobowe dane, String data){
        this.daneOsobowe= dane;
        this.dataDolaczenia=data;
    }

    public void dodajKonto(KontoBankowe konto){
        konta.add(konto);
        liczbaKont++;
    }

    public void usunKonto(int a){
        konta.remove(a);
        liczbaKont--;
    }

    public int getLiczbaKont() {
        return liczbaKont;
    }

    public DaneOsobowe getDaneOsobowe() {
        return daneOsobowe;
    }

    public String getDataDolaczenia() {
        return dataDolaczenia;
    }

    public List<KontoBankowe> getKonta() {
        return konta;
    }

}