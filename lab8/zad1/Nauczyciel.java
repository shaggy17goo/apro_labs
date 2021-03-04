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
