import java.time.LocalDate;

public class Osoba {

    public Osoba (String imie, String nazwisko) {
        imie = imie;
        nazwisko = nazwisko;
    }
    
    private String imie = "";
    private String nazwisko = "";
    private String mail = "";


    public void setEmail(String adres) {
        if (adres != null)
            mail = adres;
    }

    public String getImie() {
        return imie;
    }
    
    public String getNazwisko() {
        return nazwisko;
    }

    public String getMail() {
        return mail;
    }
    
}

class Nauczyciel extends Osoba {

    public Nauczyciel() {
        super("", "");
    }

    public Nauczyciel(String im, String naz) {
        super(im, naz);
    }

    public Nauczyciel(String im, String naz, int liczbaZajec) {
        super(im, naz);
        zajecia = new String[liczbaZajec];
    }
     
    private String[] zajecia;
  
    public void setZajecia(String zaj, int i) {
        if (zaj != null)
            if (i<zajecia.length)
                zajecia[i] = zaj;
    }
        
    public String getZajecia(int i) {
        if (i<zajecia.length) {
            return zajecia[i];
        } else
            return "";
    }
}

class Student extends Osoba{

    Student(String im, String naz, String kier, LocalDate data) {
        super(im, naz);
        nrAlbumu = nastepnyNumerAlbumu++;
        kierunek = kier;
        
        dataImmatrykulacji = data;
    }
    
    static int nastepnyNumerAlbumu = 1;
    
    private int nrAlbumu = 0;
    private String kierunek = "";
    private LocalDate dataImmatrykulacji = LocalDate.now();
    
    public static void main(String[] args) {
        Student karol = new Student("Karol", "Wolski", "infa", LocalDate.now());
        karol.setEmail("karolw@gmail.com");
        Student michal = new Student("Michał","Wawrzyńczak","Cyber",LocalDate.now());
        System.out.println(michal.getKierunek());
    }

    public LocalDate getDataImmatrykulacji() {
        return dataImmatrykulacji;
    }

    public int getNrAlbumu() {
        return nrAlbumu;
    }

    public String getKierunek() {
        return kierunek;
    }

}
