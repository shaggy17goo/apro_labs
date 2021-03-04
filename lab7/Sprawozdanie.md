# Sprawozdanie Lab7


## Zad 3.2

### ==> 1.

- **b1** wartości true połączone operatorem "&" obliczane są na zasadzie boolowskiej funkcji AND, możemy "endować" nie tylko wartości **true**/**false**,
ale także np. wartości liczbowe, wykonywana jest wtedy boolowska funkcja AND na bitowym zapisie obu liczb, zawsze bierzę pod uwagę obydwie wartości nawet jeśli pierwsza **==false**.
(w tym przypadku wykonają się oba wyrażenia)

- **b2** wartości true połączone operatorem "&&" obliczane na zasadzie logicznej funkcji AND, możemy porównywać jedynie wartości logiczne **true**/**false**,
jeśli pierwsza z wartości **==false** druga część nie jest sprawdzana.
(w tym przypadku wykonają się oba wyrażenia)

### ==> 2.

- **b3** wartości false połączone operatorem "&" obliczane są na zasadzie boolowskiej funkcji AND, możemy "endować" nie tylko wartości **true**/**false**,
ale także np. wartości liczbowe, wykonywana jest wtedy boolowska funkcja AND na bitowym zapisie obu liczb, zawsze bierzę pod uwagę obydwie wartości nawet jeśli pierwsza **==false** .
(w tym przypadku wykonają się oba wyrażenia)

- **b4** wartości false połączone operatorem "&&" obliczane na zasadzie logicznej funkcji AND, możemy porównywać jedynie wartości logiczne **true**/**false**,
jeśli pierwsza z wartości **==false** druga część nie jest sprawdzana.
(w tym przypadku wykona się tylko pierwsze wyrażenie)

### ==> 3.

- **b5** wartości true połączone operatorem "|" obliczane są na zasadzie boolowskiej funkcji OR, możemy "orować" nie tylko wartości **true**/**false**,
ale także np. wartości liczbowe, wykonywana jest wtedy boolowska funkcja OR na bitowym zapisie obu liczb, zawsze bierzę pod uwagę obydwie wartości.
(w tym przypadku wykonają się oba wyrażenia)

- **b6** wartości true połączone operatorem "||" obliczane na zasadzie logicznej funkcji OR, możemy porównywać jedynie wartości logiczne **true**/**false**,
jeśli pierwsza z wartości **==true** druga część nie jest sprawdzana.
(w tym przypadku wykona się tylko pierwsze wyrażenie)

### ==> 4.

- **b7** wartości false połączone operatorem "|" obliczane są na zasadzie boolowskiej funkcji OR, możemy "orować" nie tylko wartości **true**/**false**,
ale także np. wartości liczbowe, wykonywana jest wtedy boolowska funkcja OR na bitowym zapisie obu liczb, zawsze bierzę pod uwagę obydwie wartości.
(w tym przypadku wykonają się oba wyrażenia)

- **b8** wartości false połączone operatorem "||" obliczane na zasadzie logicznej funkcji OR, możemy porównywać jedynie wartości logiczne **true**/**false**,
jeśli pierwsza z wartości **==true** druga część nie jest sprawdzana.
(w tym przypadku wykonają się obydwa wyrażenie)

### ==> 5.

**Efekt uboczny (side-effect)** - dowolny efekt wyrażenia, który skutkuje czymś więcej niż zwróceniem wartości. Operacje które wykraczają poza lokalne środowisko działania programu,
dochodzi wtedy do komunikacji ze światem zewnętrznym. Możemy mówić o efekcie ubocznym np gdy modyfikujemy zmienną nielokalną.

### ==> 6. 

**Klasa Formatter** służy do formatowania tekstu, oraz popularnych złożeń liczbowych, np data/godzina. Przy pomocy objektów tej klasy możemy formatować tekst,
aby następnnie wyświetlić go w porządanej formie

### ==> 7/8/9.

Kod poniżej
```java
package apro1.lab7;
/**
 * @author kmi
 * @version 1.0.0 13 listopada 2019 04:34
 */
public class ShortCircuitOperators {
    /**
     * @return true always. It prints its name into standard
     * output as a side-effect.
     */
    private static boolean f1true() {
        System.out.format("f1true()%n");
        return true;
    }
    /**
     * @return false always. It prints its name into standard
     * output as a side-effect.
     */
    private static boolean f1false() {
        System.out.format("f1false()%n");
        return false;
    }
    /**
     * To test some Java operators.
     * @param args not used.
     */
    public static void main(String[] args) {
        boolean b1, b2, b3, b4, b5, b6, b7, b8;
        b1 = f1true() & f1true();
        b2 = f1true() && f1true();
        b3 = f1false() & f1false();
        b4 = f1false() && f1false();
        b5 = f1true() | f1true();
        b6 = f1true() || f1true();
        b7 = f1false() | f1false();
        b8 = f1false() || f1false();

        System.out.println("Przesuwanie jedynki w lewo:");
        byte one=1;
        for(int i=0; i<Byte.SIZE; i++){
            System.out.println(one);
            one<<=1;
        }

        System.out.println("Przesuwanie 0x8000000 w prawo, uzupełniane najstarszym bitem: ");
        int hex=0x80000000;
        for(int i=0; i<Integer.SIZE; i++){
            System.out.print(hex>>i);
            System.out.println(" ---> " + Integer.toBinaryString(hex>>i));
        }

        System.out.println("Przesuwanie 0x8000000 w prawo, uzupełniane zerami: ");
        for(int i=0; i<Integer.SIZE; i++){
            System.out.print(hex>>>i);
            System.out.println(" ---> " + Integer.toBinaryString(hex>>>i));
        }
    }
}

```


## Zad 3.3
### ==> 10.
Pliki w folderza PalindromeReadWrite

## Zad 3.4
### ==> 11.
Pliki w folderze CSV

Zawartosć pliku .java
```java
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Scanner;

/**
 * Some CSV file utilities.
 * @author kmi
8
 * @version 1.0.0 13 listopada 2019 3:14
 */
public class CSVFile {
    /**
     * The list of rows read from a file.
     */
    private static List<String[]> rows = new ArrayList<>();

    /**
     * Reads given file as CSV with given separator.
     *
     * @param fileName  the name of the CSV file to read.
     * @param separator the separator used in CSV file.
     */
    public CSVFile(String fileName, String separator) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                rows.add(line.split(separator));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String[] row : rows) {
            for (String value : row) {
                sb.append(value).append(":");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     *
     * @param rows List tab String with data of CSV file
     * @param sign new separator
     * @return file "plik.csv" with new separator
     */


    public static void writeCSV(List<String[]> rows, String sign)
    {


        ArrayList<String[]> changedRow=null;
        try {
            File file = new File("plik.csv");
            // create FileWriter object with file as parameter
            PrintWriter outputFile = new PrintWriter(file);
            for(int i=0;i<rows.size();i++) {
                String[] dataLines = rows.get(i);
                String lineRows="";
                for(int j=0; j<dataLines.length;j++) {
                    lineRows+=dataLines[j];
                    if(j<dataLines.length-1){
                        lineRows+=sign;
                    }
                }
                outputFile.println(lineRows);
            }
            outputFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("podaj nazwę pliku: ");
        String fileName=scan.nextLine();
        System.out.println("podaj separator: ");
        String separator=scan.nextLine();
        System.out.println(new CSVFile(fileName, separator));
        System.out.println("wprowadź zastępczy separator: ");
        String sign = scan.nextLine();
        writeCSV(rows, sign);
    }
}
```
