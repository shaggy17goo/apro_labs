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

