import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args){
        GeneratorKolokwiow generator=new GeneratorKolokwiow();
        generator.generujKolokwia(10,2,"zadania.txt", "kolokiwum.txt");
    }
}
