import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Race {
    public static void main(String[] arguments) {
        String[] names = {"Jan", "Piotr", "Michal", "Pawel", "Jakub", "Stefan", "Robert", "Mariusz", "Tadeusz", "Konrad"};
        int[] times = {56, 60, 51, 44, 66, 50, 49, 62, 43, 70};
        while (true) {
            try {
                System.out.println("Choose an option: 1 - calculate; 2 - exit program");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int option = Integer.parseInt(reader.readLine());
                switch (option) {
                    case 1:


                        int j;
                        int i;
                        for (j=1; j<times.length; j++) {
                            for(i=0; i<times.length-1-j; i++){
                                if (times[i]>times[i+1]){
                                    int z=times[i+1];
                                    String Z= names[i+1];
                                    times[i+1]=times[i];
                                    names[i+1]=names[i];
                                    times[i]=z;
                                    names[i]=Z;

                                }
                            }
                        }
                        Scanner scan=new Scanner(System.in);
                        System.out.println("ile pierwszych miejsc chcesz poznać?? (1-10)");
                        int N= scan.nextInt();
                        for(i=0;i<N;i++){
                            System.out.println(i+1 +". miejsce, z czasem "+times[i]+" zdobywa "+names[i]);
                        }
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Wrong option choose once again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("You should write a number!");
            }
        }
    }
}