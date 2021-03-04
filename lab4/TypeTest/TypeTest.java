public class TypeTest {
    public static void main(String[] args) {
        short daysInYear = 365;
        char x = (char) daysInYear; // przyjmuje wartość ŭ, tak jest konieczne
        int someNumber = 555;
        short castSomeNumber = (short) someNumber; // przyjmuje wartość 555, tak jest konieczne
        short reallyBig = (short) 1_111_111;// przyjmuje wartość -3001, tak jest konieczne
        char letter = 'a' + 1;
        int value = 1 + (int) 'a'; // przyjmuje wawrtość 98, NIE JEST KONIECZNE
        System.out.println(daysInYear);
        System.out.println(x);
        System.out.println(someNumber);
        System.out.println(castSomeNumber);
        System.out.println(reallyBig);
        System.out.println(letter);
        System.out.println(value);
        float firstFloat = 1.443543322f;
        int castFloat = (int) firstFloat; // przyjmuje wartość 1, tak jest konieczne, traci część ułamkową
        float first = 1.44f;
        float second = 4.33f;
        float additionResult = first + second;
        float division = first / second; // zostaje zmniejszona dokładność, zaokrągla w górę
        float addBig = 55554889857.1f + 0.00000001f; // liczba traci dokładność
        float divideBig = 555414.0f / 0.0000000001f;
        System.out.println(firstFloat);
        System.out.println(castFloat);
        System.out.println(first);
        System.out.println(second);
        System.out.println(additionResult);
        System.out.println(division);
        System.out.println(addBig);
        System.out.println(divideBig);
    }
}