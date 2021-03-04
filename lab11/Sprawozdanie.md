# Sprawozdanie Lab11 Michal Wawrzyńczak

### Zad1

**(==>1)** 
a) W przypadku poprawnego wykonania programu wykonają się bloki try i finally.
b) W przypadku błędnego wczytania pliku wykona się część bloku try(do wystąpienia błędu), sekcja catch i sekcja finally

**(==>2)** 
Nie musimy tworzyć sekcji finally zastosowanie bloku `try with resources` powoduje, że w każdym przypadku poprawnegowykonania lub błędu 
`BufferReader` zamknie program i zwolni dostęp do pliku

**(==>3)** 
Modyfikacja metody `read` bez użycia bloków try i catch
```java
    public List<String[]> read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        while ((line = br.readLine()) != null) {
                rows.add(line.split(separator));
        }
        return rows;
    }
```

**(==>4)**
Sprawdzanie dla liczb zmiennoprzecinkowych nie ma sensu, ponieważ ze wzgledu na reprezentację dwójkową liczb w komputerze, liczby posiadające 
skończone rozwinięcie dziesiętne nie zawsze będą posiadać skończone rozwinięcie dwójkowe, taka sytuacja może skutkować niedokładnym obliczniem 
wyniku np (zamiast 1.32 będzie 1.319999999999999). Rozwiązaniem takiego problemu może być użycie formatera w metodach divide który będzie nam 
odpowiednio zaokrąglał wynik.
