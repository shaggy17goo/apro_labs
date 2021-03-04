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
