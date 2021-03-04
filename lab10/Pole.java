package com.company;

import java.util.ArrayList;

public class Pole {
    String atrybut;         //Jeden z 3      L(ląd)/     W(woda)/     K(krawędź)
    int sasiadGoraX;
    int sasiadGoraY;
    int sasiadDolX;
    int sasiadDolY;
    int sasiadLewoX;
    int sasiadLewoY;
    int sasiadPrawoX;
    int sasiadPrawoY;
    int wyspaNr;            //Numer wyspy do której należy pole
    ArrayList<String> cechy=new ArrayList<String>();    //Potencjalne przyyszłe cechy Pól, np. na 0 pozycji "rzeźba terenu"
    //Dla przejrzystości mapy podajemy w formacie "ABCD"

    public Pole(String atrybut,int i, int l){ //konstruktor dla pól
        this.atrybut=atrybut;
        this.sasiadGoraY=i-1;
        this.sasiadGoraX=l;
        this.sasiadDolY=i+1;
        this.sasiadDolX=l;
        this.sasiadLewoY=i;
        this.sasiadLewoX=l-1;
        this.sasiadPrawoY=i;
        this.sasiadPrawoX=l+1;
        this.wyspaNr=0;
    }

    public Pole(String atrybut){ //Konstruktor używany do stworzenia pól krawędzi
        this.atrybut=atrybut;
        this.wyspaNr=0;
    }

    public Pole getSasiadGora(Pole[][] mapa) {
        return mapa[sasiadGoraY][sasiadGoraX];
    }
    public Pole getSasiadDol(Pole[][] mapa) {
        return mapa[sasiadDolY][sasiadDolX];
    }
    public Pole getSasiadLewo(Pole[][] mapa) {
        return mapa[sasiadLewoY][sasiadLewoX];
    }
    public Pole getSasiadPrawo(Pole[][] mapa) {
        return mapa[sasiadPrawoY][sasiadPrawoX];
    }
}
