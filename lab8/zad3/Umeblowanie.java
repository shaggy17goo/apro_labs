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
