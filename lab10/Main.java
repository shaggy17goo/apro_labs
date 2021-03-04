package com.company;

import java.io.*;
import java.util.ArrayList;

class Mapa {
    static int szerokosc;
    static int wysokosc;
    static Pole[][] mapa;
    static int licznikRekurencji=0;
    static int liczbaKrokow=0;

    /**
     * (pierwsza współrzędna Pion,rośnie w dół mapy)
     * (druga współrzędna Poziom, rośnie w prawo)
     */


    /**
     * @param fileName nazwa pliku zawierającego mapę
     *Odczytanie z pliku wymiarów mapy
     *
     */
    public static void odczytWymiary(String fileName) {
        String wymiary=""; liczbaKrokow++;
        String[] wymiar; liczbaKrokow++;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            wymiary=br.readLine(); liczbaKrokow++;
            wymiar=wymiary.split(" "); liczbaKrokow++;
            szerokosc=Integer.parseInt(wymiar[0]); liczbaKrokow++;
            wysokosc=Integer.parseInt(wymiar[1]); liczbaKrokow++;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fileName- plik zawierający mapę
     *
     * @return tablicz 2-wyiarowa Pole z okreslonymi współrzędnymi sąsiadów
     * pola Posiadają jeden z 3 atrybutów, K-kres, W-woda, L-ląd
     *
     */
    public static void odczytMapa(String fileName) {
        mapa=new Pole[wysokosc+2][szerokosc+2]; liczbaKrokow++;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for(int j=0;j<=szerokosc+1;j++){ liczbaKrokow++;
                mapa[0][j]=new Pole("K");
            }

            String linia;
            String[] znaki;
            String znak;
            int l=0;
            linia=br.readLine();
            for(int i=1;i<=wysokosc;i++) { liczbaKrokow=+2;
                mapa[i][0]=new Pole("K"); liczbaKrokow++;
                mapa[i][szerokosc+1]=new Pole("K"); liczbaKrokow++;
                linia=br.readLine(); liczbaKrokow++;
                znaki=linia.split(" "); liczbaKrokow++;
                l=0; liczbaKrokow++;
                for (int j=1;j<=szerokosc;j++) { liczbaKrokow=+2;
                    znak=znaki[l]; liczbaKrokow++;
                    if(znak.equals("1")){ liczbaKrokow++;
                        mapa[i][j]=new Pole("L",i,j); liczbaKrokow++;
                    }
                    if(znak.equals("0")) { liczbaKrokow++;
                        mapa[i][j] = new Pole("W",i,j); liczbaKrokow++;
                    }
                    l++;
                }
            }

            for(int j=0;j<=szerokosc+1;j++){ liczbaKrokow=+2;
                mapa[wysokosc+1][j]=new Pole("K"); liczbaKrokow++;
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Metoda rekurencyjna
     * Po przekazaniu Współrzędnych Pola (y x) oraz licznika
     * ustawia grupaNr wszystkich pól Lądowych graniczących z polem [yx]
     * @param y
     * @param x
     * @param licznik
     */
    public static void oznaczanieWyspy(int y,int x, int licznik) { liczbaKrokow++;
        licznikRekurencji++;
        if(mapa[y][x].getSasiadGora(mapa).atrybut.equals("L")&&mapa[y][x].getSasiadGora(mapa).wyspaNr==0){
            mapa[y][x].getSasiadGora(mapa).wyspaNr=licznik; liczbaKrokow=+4;
            oznaczanieWyspy(mapa[y][x].sasiadGoraY,mapa[y][x].sasiadGoraX,licznik);}

        if(mapa[y][x].getSasiadLewo(mapa).atrybut.equals("L")&&mapa[y][x].getSasiadLewo(mapa).wyspaNr==0){
            mapa[y][x].getSasiadLewo(mapa).wyspaNr=licznik;liczbaKrokow=+4;
            oznaczanieWyspy(mapa[y][x].sasiadLewoY,mapa[y][x].sasiadLewoX,licznik);}

        if(mapa[y][x].getSasiadDol(mapa).atrybut.equals("L")&&mapa[y][x].getSasiadDol(mapa).wyspaNr==0){
            mapa[y][x].getSasiadDol(mapa).wyspaNr=licznik;liczbaKrokow=+4;
            oznaczanieWyspy(mapa[y][x].sasiadDolY,mapa[y][x].sasiadDolX,licznik);}

        if(mapa[y][x].getSasiadPrawo(mapa).atrybut.equals("L")&&mapa[y][x].getSasiadPrawo(mapa).wyspaNr==0){
            mapa[y][x].getSasiadPrawo(mapa).wyspaNr=licznik;liczbaKrokow=+4;
            oznaczanieWyspy(mapa[y][x].sasiadPrawoY,mapa[y][x].sasiadPrawoX,licznik);}
        else {mapa[y][x].wyspaNr=licznik;
            liczbaKrokow++;}
    }

    /**
     * metoda wykonuje metodę oznaczaniaWysp dla każdej grupy Pól
     * Wynikiem jest mapa o Polach posiadających Atrubut L/W/K oraz z nrGrupyPól należących do jednej wyspy
     */
    public static int grupowanieWysp() {
        int grupaPola;liczbaKrokow++;
        int licznik = 1; liczbaKrokow++;
        boolean is;
        ArrayList<Integer> numeryWysp = new ArrayList<Integer>();
        for (int i = 1; i <= wysokosc; i++) { liczbaKrokow+=2;
            for (int j = 1; j <= szerokosc; j++) { liczbaKrokow+=2;
                is = true; //wykonujemy??
                liczbaKrokow++;
                grupaPola = mapa[i][j].wyspaNr; liczbaKrokow++;
                if (mapa[i][j].atrybut.equals("L") && grupaPola == 0) { liczbaKrokow++;
                    for (int k=0; k<numeryWysp.size(); k++) { liczbaKrokow+=2;
                        if (grupaPola == numeryWysp.get(k)) { liczbaKrokow++;
                            is = false; liczbaKrokow++;
                        }
                    }
                    if(is) { liczbaKrokow++;
                        oznaczanieWyspy(i,j,licznik); liczbaKrokow++;
                        licznik++; liczbaKrokow++;
                    }
                }
            }
        }
        liczbaKrokow++;
        return licznik-1;
    }

    /**
     * Wyświetla mapę (Tylko atrybuty pól)
     */
    public  static void wyswietlMapeAtrubuty(){
        System.out.println();
        System.out.println("Mapa pokazująca atrybuty pola: L-Ląd, W-Woda, K-Krawedź mapy;");
        for(int i=0;i<wysokosc+2;i++){
            for (int j = 0; j <szerokosc + 2; j++) {
                System.out.print(mapa[i][j].atrybut + "  ");
            }
            System.out.println();
        }
    }

    /**
     * Wyświetla mapę (Tylko nrWysp Pol)
     */
    public  static void wyswietMapeWyspy(){
        System.out.println();
        System.out.println("Mapa pokazująca grupy Wysp, każda grupa oznaczona inną cyfrą; woda - 0");
        for(int i=1;i<wysokosc+2;i++){;
            for (int j = 1; j <szerokosc +2; j++) {
                System.out.print(mapa[i][j].wyspaNr+ "  ");
            }
            System.out.println();
        }
    }

    /**
     * Wyświetla mapę (Tylko nrWysp Pol)
     */
    public  static void wyswietMapeCechy(int cechaNr){
        System.out.println();
        System.out.println("Mapa pokazująca grupy chechy wysp(pewnie trochę nieczytelna)   ~~~~ -Woda");
        System.out.println();
        for(int i=1;i<wysokosc+1;i++){
            for (int j = 1; j <szerokosc +1; j++) {
                if(mapa[i][j].cechy.size()>=cechaNr+1) {
                    System.out.print(mapa[i][j].cechy.get(cechaNr) + " ");
                }
                else if(mapa[i][j].atrybut.equals("W")) {
                    System.out.print("~~~~ ");
                }
                else{
                    System.out.print("BRAK ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Przypisuje wyspie podaną cechę na danej pozycji
     * @param nrWyspy
     * @param cecha
     */
    public static void przypiszCeche(int nrWyspy, String cecha) {
        for (int i = 1; i < wysokosc + 1; i++) {
            for (int j = 1; j < szerokosc + 1; j++) {
                if (mapa[i][j].wyspaNr == nrWyspy) {
                    mapa[i][j].cechy.add(cecha);
                }
            }
        }
    }

    /**
     * Metoda zliczająca ile pól zawiera się w wyspie danegeo numery
     */
    public static void zliczRozmiar(int liczbaWysp) {
        int rozmiar;
        for (int k=1;k<=liczbaWysp;k++){liczbaKrokow+=2;
            rozmiar=0;liczbaKrokow++;
            int wyspaNr=k;liczbaKrokow++;
            for (int i = 1; i < wysokosc + 1; i++) {liczbaKrokow+=2;
                for (int j = 1; j < szerokosc + 1; j++) {liczbaKrokow+=2;
                    if (mapa[i][j].wyspaNr == wyspaNr) {liczbaKrokow++;
                        rozmiar++;liczbaKrokow++;
                    }
                }
            }
            System.out.println("Wyspa nr "+k+" posiada "+rozmiar+" pól.");liczbaKrokow++;
        }
    }


    /**
     * Wyświetla mapę przy pomocy czarno białych kwadratów
     */
    public static void wyswietlMapeBW(){
        System.out.println();
        System.out.println("Mapa pokazująca ląd i wodę:  \u2588 - ląd,  \u2591   -woda");
        for(int i=1;i<wysokosc+1;i++){
            for (int j = 1; j <szerokosc + 1; j++) {
                if(mapa[i][j].atrybut.equals("L"))
                    System.out.print("\u2588 ");
                else
                    System.out.print("\u2591 ");
            }
            System.out.println();
        }
    }

    /**
     * zapisuje mapę do bliku txt przy pomocy czarno białych kwadratów
     */
    public static void zapisMapyBW(){
        try {
            File file = new File("mapa.txt");
            // create FileWriter object with file as parameter
            PrintWriter outputFile = new PrintWriter(file);
            for(int i=1;i<=wysokosc+1;i++) {
                for(int j=1;j<=szerokosc+1;j++){
                    if(mapa[i][j].atrybut.equals("W")) {
                        outputFile.print("\u2588");
                    }
                    else {
                        outputFile.print("\u2591");
                    }
                }
                outputFile.println();
            }
            outputFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        /**
         * program funkcjonuje dla danych wejściowych o maksymalnych wymiarach 110x110
         * w przypadku większych danych wejściowych następuje przepełnienie wywołanych metod
         * aby program zadziałam dla większej ilości danych należy dodać w Opcjach WirtualnejMaszyny parametr "-Xss4m"
         */
        odczytWymiary("simple_input.txt");
        odczytMapa("simple_input.txt");
        wyswietlMapeAtrubuty();
        System.out.println();
        wyswietlMapeBW();
        zapisMapyBW();
        System.out.println();
        int liczbaWysp=grupowanieWysp();


        przypiszCeche(1,"PUST");    //Dla przejrzystości mapy podajemy w formacie "ABCD"
        przypiszCeche(2,"GÓRY");
        przypiszCeche(3,"SNOW");
        przypiszCeche(4,"LASS");
        przypiszCeche(5,"OAZA");
        przypiszCeche(6,"POLA");
        przypiszCeche(7,"LAWA");
        przypiszCeche(8, "????");

        wyswietMapeWyspy();
        wyswietMapeCechy(0);
        System.out.println();
        System.out.println("Rekurencja wykonała się: "+licznikRekurencji+" razy");
        System.out.println("Na mapie jest "+liczbaWysp+" wysp.");
        zliczRozmiar(liczbaWysp);
        System.out.println();
        System.out.println("Liczba krokow: "+liczbaKrokow);


        /**
         *simple => 167, large => 82873 (ilość pól na mapie będących 1(lądem))
         * wnajgorszym przypadku wykona się n razy, gdzie n=szerokość*wysokość danych wejściowych
         * złożnoność rekurencji pojedyńczej rekurecnji w najgorszym przypadku to 17
         * klasa algorytmu pozostałych metod => O(n)
         * Klasa algorytmu => O(n)
         */

    }
}
