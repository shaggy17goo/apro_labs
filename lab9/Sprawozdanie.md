# Michał Wawrzyńczak
# Sprawozdanie lab9


### Zadanie 1
(==>1) Po analizie kodu stwierdziłem że stosunek liczb lowConter/highCounter=2/1, co po uruchomieniu programu okazało się słuszne.
(==>2) Użycie tablicy nie było konieczne. Można ją zastąpić ArrayList'ą przechowujacą wartość Integer, choć według mnie ten zabieg jest bezsensowny gdzyż program w tym
przypadku będzie wykonywał się wolniej(chyba że zdefinujemy przewidywną wartość ArrayListy), Jeśli zależy nam tylko na wartościach lowCounter i highCounter, możemy po
prostu losować kolejne 1000000 nie zapisując ich nigdzie.

**Realizacja z ArrayList**
import java.util.Random;
```java
class TestRandom {
    static Random randomGenerator = new Random();

    static int generateBoundedRandomInt(int bound) {
        return Math.abs(randomGenerator.nextInt()) % bound;
    }

    static ArrayList<Integer> listOfRandoms =new ArrayList<>(1);

    public static void main(String[] args) {
        int max = 2 * (Integer.MAX_VALUE/ 3);
        for (int i = 0; i < 1_000_000_0; i++) {
            listOfRandoms.add(i,generateBoundedRandomInt(max));
        }
        int lowCounter = 0, highCounter = 0, halfMax = max >> 1;
        for (int a : listOfRandoms) {
            if (a < halfMax) {
                lowCounter++;
            } else {
                highCounter++;
            }
        }
        System.out.println("max= " + max +
                " max/2= " + halfMax);
        System.out.println("lowCounter= " + lowCounter +
                " highCounter= " + highCounter);
    }
}
```
**Realizacja bez zapisania danych**

```java
class TestRandom2 {
    static Random randomGenerator = new Random();

    static int generateBoundedRandomInt(int bound) {
        return Math.abs(randomGenerator.nextInt()) % bound;
    }

    public static void main(String[] args) {
        int max = 2 * (Integer.MAX_VALUE / 3);
        int lowCounter = 0, highCounter = 0, halfMax = max >> 1;
        int a;
        for (int i = 0; i < 1_000_000; i++) {
            a= generateBoundedRandomInt(max);
            if (a < halfMax) {
                lowCounter++;
            } else {
                highCounter++;
            }
        }
        System.out.println("max= " + max +
                " max/2= " + halfMax);
        System.out.println("lowCounter= " + lowCounter +
                " highCounter= " + highCounter);
    }
}
```
(==>3)
Wyniki kilku przykładowych uruchomień programu:
* lowCounter= 6669780 highCounter= 3330220
* lowCounter= 6665379 highCounter= 3334621
* lowCounter= 6665130 highCounter= 3334870
* lowCounter= 6667528 highCounter= 3332472
* lowCounter= 6669334 highCounter= 3330666

### Zadanie 2

(==>4)
**ListaZGłową**
```java
/**
 *
 */
package apro1.lab9;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Lista wiązana z głową. Głowa jest pierwszym elementem  listy. Dostęp
 * do kolejnych elementów listy odbywa się przez pole <code>następny</code>
 * w węźle.
 * @author kmi
 * @version 1.0.0  28 listopada 2019
 */
class ListaZGłową {
    /**
     * Głowa, tj. pierwszy element listy.
     */
    private Węzeł głowa = null;

    /**
     * Bieżący rozmiar listy.
     */
    private int rozmiar = 0;

    /**
     * Tworzy pustą listę.
     */
    public ListaZGłową() {
    }

    /**
     * Dodaje dane, zawsze na początku listy.
     * @param dane
     */
    public void dodaj(int dane) {
        głowa = new Węzeł(dane, głowa);
        rozmiar++;
    }

    /**
     * Usuwa dane, zawsze z początku listy.
     * @return dane, które były w pierwszym elemencie listy.
     */
    public int usuń() {
        Węzeł g = Objects.requireNonNull(głowa, "Lista jest pusta.");
        int rv = g.dane();
        głowa = g.następny();
        rozmiar--;
        return rv;
    }

    /**
     * Usuwa podane dane, tj. wyszukuje je w liście i usuwa węzeł,
     * który zawiera te dane. Jeśli lista nie zawiera tego elementu
     * - nic nie usuwa z listy.
     * @param dane do usunięcia.
     * @return <code>true</code> jeśli lista zawierała podane dane
     * 		   i zostały one usunięte, <code>false</code> w przeciwnym
     * 		   przypadku.
     */
    public boolean usuń(int dane) {
        int licznik=0;
        while(głowa.dane()==dane){
            głowa = głowa.następny();
            licznik++;
            rozmiar--;
        }
        Węzeł temp1=głowa;
        int i;
        for(i=0; i<rozmiar-1;i++) {
            Węzeł temp2=temp1.następny();
            while (temp2.dane() == dane) {
                temp1.setNastępny(temp2.następny());
                licznik++;
                rozmiar--;
                temp2=temp2.następny();
                if(i==rozmiar-1)
                    break;
            }
            temp1=temp1.następny();
        }
        System.out.println(dane+" występowało "+licznik+" razy");
            if (licznik > 0)
                return true;
            else
                return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("rozmiar:" + rozmiar + ":");
        Węzeł p = głowa;
        while (p != null) {
            sb.append(p.dane()).append("->");
            p = p.następny();
        }
        sb.append("|");
        return sb.toString();
    }

    /**
     * Test klasy.
     * @param args nieużywane.
     */
    public static void main(String[] args) {
        ListaZGłową l = new ListaZGłową();
        System.out.println(l);

        l.dodaj(23);
        l.dodaj(8);
        l.dodaj(23);
        for (int i = 0; i < 5; i++) {
            l.dodaj(ThreadLocalRandom.current().nextInt(100));
        }
        l.dodaj(23); //Wstawiam 23 na sztywno żeby zawsze było, 2 obok siebie, żeby sprawdzić czy nie ominie;
        l.dodaj(23); //Jak nie ma to też działa poprawnie;
        for (int i = 0; i < 5; i++) {
            l.dodaj(ThreadLocalRandom.current().nextInt(100));
        }
        l.dodaj(23); // i tu też jeszcze jedno
        for (int i = 0; i < 5; i++) {
            l.dodaj(ThreadLocalRandom.current().nextInt(100));
        }
        l.dodaj(23);
        l.dodaj(23);

        System.out.println(l);
        /*
        l.usuń();
        System.out.println(l);
        l.usuń();
        System.out.println(l);
        l.usuń();
        System.out.println(l);
        l.usuń();
        System.out.println(l);*/
        int doUsunięcia = 23;
        boolean usunięto = l.usuń(doUsunięcia);
        System.out.println("Lista " + (usunięto ? "" : "nie ") + "zawierała liczb" +
                (usunięto ? "ę " : "y ") + doUsunięcia +
                (usunięto ? " i została ona usunięta." : ". Lista nie została zmodyfikowana."));
        System.out.println(l);
    }
}
```

**Węzeł**
```java
/**
 *
 */
package apro1.lab9;

/**
 * Węzeł listy przechowującej dane w typie <code>int</code>.
 * @author kmi
 * @version 1.0.0  30 listopada 2019
 */
class Węzeł {
    /**
     * Liczba przechowywana w tym węźle.
     */
    private int dane;

    /**
     * Następnik: referencja na kolejny węzeł - następny na liście.
     * Jeśli ma wartość <code>null</code> to ten węzeł jest ostatni
     * w liście.
     */
    private Węzeł następny;

    /**
     * @return dane zawierane w tym węźle.
     */
    public int dane() {
        return dane;
    }

    /**
     * @return następnik tego węzła.
     */
    public Węzeł następny() {
        return następny;
    }

    public void setNastępny(Węzeł następny) {
        this.następny = następny;
    }

    /**
     * Tworzy węzeł z podanymi danymi. Utworzony węzeł nie ma
     * następnika.
     * @dane tego węzła.
     */
    public Węzeł(int dane) {
        this.dane = dane;
        następny = null;
    }

    /**
     * TWorzy węzeł z danymi i następnikiem.
     * @param dane tego węzła.
     * @param następny następnik tego węzła.
     */
    public Węzeł(int dane, Węzeł następny) {
        this.dane = dane;
        this.następny = następny;
    }

    @Override
    public String toString() {
        return "" + dane;
    }

    /**
     * Test klasy.
     * @param args nieużywane.
     */
    public static void main(String[] args) {
        Węzeł w = new Węzeł(2);
        System.out.println(w);
    }
}
```
(==>5)
Biorąc pod uwagę tylko operatoty przypisania i porównania (zakładam, że nie musimy w żaden sposób przeliczać przesówania elementów listy, ani nic z tych rzeczy):
* Metoda dodaj(int dane) należy do klasy algorytmów O(1)
* Metoda usuń należy do klasy algorytmów O(1)
* Metoda dodaj(int dane) należy do klasy algorytmów O(n)


### Zadanie 3
(==>6)

**Test**
test generatora
```java
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args){
        GeneratorKolokwiow generator=new GeneratorKolokwiow();
        generator.generujKolokwia(10,2,"zadania.txt", "kolokiwum.txt");
    }
}
```


**GeneratorKolokwiow**
```javaimport java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GeneratorKolokwiow {
    public GeneratorKolokwiow(){

    }
    private List<ArrayList> zbiorKolokwiow=new ArrayList<ArrayList>();
    private List<String> trescZadan=new ArrayList<String>();
    private List<Zadanie> Zadania=new ArrayList<Zadanie>();
    private List<Zadanie> kolokwium=new ArrayList<Zadanie>();
    private Random randomGenerator = new Random();
    private void OdczytajIZapiszZadania(File file) throws FileNotFoundException {
        Scanner odczyt=new Scanner(file).useDelimiter(" ");
        String odczytanyTekst="";
        while(odczyt.hasNextLine()){
            odczytanyTekst+=odczyt.nextLine();

        }
        System.out.println(odczytanyTekst);

    }

    /**
     * Zapisuje w liście kolejne linijki tekstu wejściowego
     * @param fileName śceiżka do pliku wejściowego
     */
    private void odczyt(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                trescZadan.add(line);
                //System.out.println(line);//Test, do usunięcia
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Interpretuje dane wejściowe, usuwa niepotrzebne znaki, zapisuje oddzielne zadania do listy zadań
     * @param lista
     */
    private void zinterpretujOdczyt(List<String> lista){
        String linia="";
        for(int i=0;i<lista.size();i++){
            linia=lista.get(i);
            if(linia.substring(0,1).equals("#")){
                lista.remove(i);
                i--;
            }
            else if(linia.substring(0,1).equals("{")){
                lista.remove(i);
                i--;
            }
            else if(linia.substring(0,1).equals("}")){
                lista.remove(i);
                i--;
            }
            else{
                Zadania.add(new Zadanie(lista.get(i)));
            }


        }

    }

    /**
     * Wybiera z wejścia zadania mające w sumie podaną liczbę pkt
     * @param zadania Lista z zadaniami
     * @param liczbaPunktów Liczba punktów za kolokwium
     */
    private void dobierzZadania(List<Zadanie> zadania, int liczbaPunktów){
        while(liczbaPunktów>0) {
            int los = Math.abs(randomGenerator.nextInt())%zadania.size();
            if(liczbaPunktów<zadania.get(los).getLiczbaPkt())
                continue;
            kolokwium.add(zadania.get(los));
            liczbaPunktów=liczbaPunktów-zadania.get(los).getLiczbaPkt();
            zadania.remove(los);
        }
    }

    /**
     * Generuje gotowe kolokwium
     * @param liczbaPkt Maksymalna liczba pkt z kolokwium
     * @param zadaniaPath Ścieżka do pliku z zadaniami
     * @param kolokwiumPath Nazwa pliku do którego mają trafić kolokwia
     */
    public void generujKolokwia(int liczbaPkt, int liczbaKolokwiow,String zadaniaPath, String kolokwiumPath){
    int sumaKontrolna=0, nrKolokwium=1;
        odczyt(zadaniaPath);
        zinterpretujOdczyt(this.trescZadan);
        for(int a=0;a<liczbaKolokwiow;a++) {
            dobierzZadania(this.Zadania, liczbaPkt);
        }
            try {
                File file = new File(kolokwiumPath);
                // create FileWriter object with file as parameter
                PrintWriter outputFile = new PrintWriter(file);
                for (int i = 0; i < this.kolokwium.size(); i++) {
                    if(sumaKontrolna==10 || sumaKontrolna==0) {
                        outputFile.println("Kolokwium grupa nr " + (nrKolokwium++));
                        outputFile.println("");
                        sumaKontrolna=0;
                    }
                    outputFile.println(this.kolokwium.get(i).toString());
                    sumaKontrolna+=this.kolokwium.get(i).getLiczbaPkt();
                }
                outputFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


    }
}
```

**Zadanie**
klasa przechowująca zadania
```java
public class Zadanie {
    private int  liczbaPkt,liczbaPustychLinii;
    private String trescZadania;
    public Zadanie(int liczbaPkt,String trescZadania, int liczbaPustychLinii){
        this.liczbaPkt=liczbaPkt;
        this.trescZadania=trescZadania;
        this.liczbaPustychLinii=liczbaPustychLinii;

    }
    public Zadanie(String linia){
        this.liczbaPkt=Integer.parseInt(linia.substring(0,1));
        this.liczbaPustychLinii=Integer.parseInt(linia.substring(2,3));
        this.trescZadania=linia.substring(4);

    }

    public int getLiczbaPkt() {
        return liczbaPkt;
    }

    public int getLiczbaPustychLinii() {
        return liczbaPustychLinii;
    }

    public String getTrescZadania() {
        return trescZadania;
    }

    public void setLiczbaPkt(int liczbaPkt) {
        this.liczbaPkt = liczbaPkt;
    }

    public void setLiczbaPustychLinii(int liczbaPustychLinii) {
        this.liczbaPustychLinii = liczbaPustychLinii;
    }

    public void setTrescZadania(String trescZadania) {
        this.trescZadania = trescZadania;
    }
    private String pusteLinie(){
        String linie="";
        for(int  i=0;i<this.getLiczbaPustychLinii();i++){
            linie+=" \n";

        }
            return linie;
    }

    @Override
    public String toString() {
        return
            "("+this.getLiczbaPkt()+ "pkt) " +this.getTrescZadania() + pusteLinie();
    }
}
```
