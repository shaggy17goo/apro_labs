public class Factorial {
    public static void main(String[] args) {
        short N = 4;
        int result = 1; // Nie dla każdego, maksymalna wartość zmiennej musi być większa równa oczakiwanemu wynikowi,
        // np. dla zmiennej typu byte możemy policzyć maksymalnie 6!
        while (N > 0) {
            result *= N;
            N--;
        }
        if (N>=0) {
            System.out.println(result);
        }
        else {
            System.out.println("Niepoprawne dane wejściowe");
        }
    }
}