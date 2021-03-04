# Sprawozdanie Lab8 Michał Wawrzyńczak


## Zad 4.1

### ==> 1 Propozyzja klasy student.
```java
package com.company;

import java.time.LocalDate;

public class Student {

    Student(String imie, String nazwisko, String kierunek, LocalDate data) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrAlbumu = nastepnyNumerAlbumu++;
        this.kierunek = kierunek;
        dataImmatrykulacji = data;
    }
    
    static int nastepnyNumerAlbumu = 1;
    
    private String imie = "";
    private String nazwisko = "";
    private int nrAlbumu = 0;
    private String kierunek = "";
    private String mail = "";
    private LocalDate dataImmatrykulacji = LocalDate.now();
    
    public static void main(String[] args) {
        Student karol = new Student("Karol", "Wolski", "infa", LocalDate.now());
        System.out.println(karol.getDataImmatrykulacji());
        karol.setMail("karolw@gmail.com");
    }
    

    public void setMail(String adres) {
        mail = adres;
    }
    
    public LocalDate getDataImmatrykulacji() {
        return dataImmatrykulacji;
    }
    
    public String getImie() {
        return imie;
    }
    
    public String getNazwisko() {
        return nazwisko;
    }
    
    public int getNrAlbumu() {
        return nrAlbumu;
    }

    public String getKierunek() {
        return kierunek;
    }
    
    public String getMail() {
        return mail;
    }
}
```

### ==> 2 Propozyzja klasy nauczyciel.
```java
package com.company;

public class Nauczyciel {

    Nauczyciel(String im, String naz, int liczbaZajec) {
        imie = im;
        nazwisko = naz;
        zajecia = new String[liczbaZajec];
    }
        
    private String imie = "";
    private String nazwisko = "";
    private String mail = "";
    private String[] zajecia;
    
    public static void main(String[] args) {
        Nauczyciel stefan = new Nauczyciel("Andrzej", "Wawrzyn", 6);
        System.out.println(stefan.getNazwisko());
        stefan.setEmail("andwaw@gmail.com");
    }
    

    public void setEmail(String adres) {
        mail = adres;
    }
    
    public void setZajecia(String zaj, int i) {
        if (i<zajecia.length)
            zajecia[i] = zaj;
    }
    
    public String getImie() {
        return imie;
    }
    
    public String getNazwisko() {
        return nazwisko;
    }

    public String getEmail() {
        return mail;
    }
    
    public String getZajecia(int i) {
        if (i<zajecia.length) {
            return zajecia[i];
        } else
            return "";
    }
}
```

## Zad 4.2

### ==> 3 

```java

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

```


## Zad 4.3

### ==> 4

```java
package com.company;

import java.util.Objects;



class Mebel {
    Mebel(String opis) {
        this.opis = opis;
    }
    String opis = "";
}


class Krzeslo extends Mebel {
    Krzeslo(String opis) {
        super(opis);
    }
    
    @Override
    public String toString() {
        return "Krzesło "+opis;
    }
}

class Stol extends Mebel {

    Stol(int lNog, String opis) {
        super(opis);
        liczbaNog = lNog;
    }
    
    int liczbaNog = 0;
    
    @Override
    public String toString() {
        return "Stół "+opis;
    }
    
    @Override
    public boolean equals(Object stl) {
    
        if (this == stl) return true;
        if (stl == null) return false;

        return this.liczbaNog == ((Stol)stl).liczbaNog;
    }
    
}

class Kanapa extends Mebel {
    Kanapa(int lMiejsc, String op) {
        super(op);
        liczbaMiejsc = lMiejsc;
    }
    
    int liczbaMiejsc = 0;
    
    @Override
    public String toString() {
        return "Kanapa "+opis;
    }
}

public class Umeblowanie {
    public static void main(String... args) {
    
        Mebel   krzeslo = new Krzeslo("z oparciem"),
                k2 = new Krzeslo("z podłokietnikami"),
                stol = new Stol(4, "prostokątny z czterema nogami"),
                s2 = new Stol(1, "okrągły z jedną nogą"),
                s3 = new Stol(1, "kwadratowy z jedna nogą"),
                sofa = new Kanapa(2, "typu sofa"),
                l2 = new Kanapa(2, "typu wersalka");
        
        System.out.println("W pokoju stoi " + sofa + " i " + stol +". Jest tylko jedno " + krzeslo);
        System.out.println("W innym pokoju znajdują się: " + s2 +" i " + s3);
        if (s2.equals(s3)) {
            System.out.println("Te stoły mają tyle samo nóg");
        } else {
            System.out. println("Każdy ze stołów ma inną liczbę nóg");
        }
    }
}

```

## Zad 4.4

### ==> 5

Konto Bankowe
```java

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

```
Dane Osobowe
```java

class DaneOsobowe {
     private  String name;
     private  String surName;
     private  String bDay;
     private  long PESEL;

    public DaneOsobowe(String name, String surName, String bDay, long PESEL){
        this.name=name;
        this.surName=surName;
        this.bDay=bDay;
        this.PESEL=PESEL;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getbDay() {
        return bDay;
    }

    public long getPESEL() {
        return PESEL;
    }
}

```

Klient Banku
```java

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

```

Testy
```java

import java.sql.SQLOutput;
import java.util.Scanner;

public class Testing {


    public static void main(String[] args) {

        KlientBanku jkowal = new KlientBanku
                (new DaneOsobowe("Jan","Kowal","10.10.2000",99776655712L),"12.04.2018");
                jkowal.dodajKonto(new KontoBankowe("oszczędnościowe",3123123312123L,1234.05));

        KlientBanku mszpak = new KlientBanku
                (new DaneOsobowe("Marcin","Szpak","15.04.1998",88776655725L),"15.07,2017");
                mszpak.dodajKonto(new KontoBankowe ("oszczędnościowe",1724671201L,136112.87));


        KlientBanku kmarciniak = new KlientBanku
                (new DaneOsobowe("Krzysztof","Marciniak","10.10.2000",99776655712L),"12.04.2018");
                 kmarciniak.dodajKonto(new KontoBankowe("oszczędnościowe",3123123123L,79531.73));


        jkowal.dodajKonto(new KontoBankowe("wielowalutowe", 7545250832925L, 54351.53));
        jkowal.dodajKonto(new KontoBankowe("wielowalutowe", 2845045704350L, 54351.53));

        System.out.println("konta "+jkowal.getDaneOsobowe().getName()+" "+jkowal.getDaneOsobowe().getSurName()+": ");
        for(int i=0; i<jkowal.getKonta().size();i++)
            System.out.println("Konto nr"+(i+1)+": "+jkowal.getKonta().get(i).getNrKonta());


        System.out.println();
        System.out.println("Saldo Jana Kowalskiego przed wpłatą: "+jkowal.getKonta().get(0).getSaldo());
        jkowal.getKonta().get(0).wplac(1412.81);



        System.out.println("Saldo Marciniaka: "+kmarciniak.getKonta().get(0).getSaldo());

        System.out.println();


        jkowal.getKonta().get(0).wplac(6542.81);
        jkowal.getKonta().get(0).wyplac(4322.81);
        jkowal.getKonta().get(0).wplac(852.81);
        
        
        
        System.out.println();
        System.out.println("Historia Operacji: ");
        for(int i=0; i<jkowal.getKonta().get(0).getHistoria().size();i++)
            System.out.println("Transakcja nr."+(i+1)+": "+jkowal.getKonta().get(0).getHistoria().get(i));
    }
}


```
Konsola:
```
konta Jan Kowal: 
Konto nr1: 3123123312123
Konto nr2: 7545250832925
Konto nr3: 2845045704350

Saldo Jana Kowalskiego przed wpłatą: 1234.05
wpłacono 1412.81 saldo po wplacie: 2646.8599999999997
Saldo Marciniaka: 79531.73

wpłacono 6542.81 saldo po wplacie: 9189.67
wyplata 4322.81 saldo po wyplacie: 4866.86
wpłacono 852.81 saldo po wplacie: 5719.67
Transakcja nr.1: Wpłacono: 1412.81 zł - saldo: 2646.8599999999997
Transakcja nr.2: Wpłacono: 6542.81 zł - saldo: 9189.67
Transakcja nr.3: Wypłacono: 4322.81 zł - saldo: 4866.86
Transakcja nr.4: Wpłacono: 852.81 zł - saldo: 5719.67
```