import java.io.*;
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
