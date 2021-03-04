import java.util.Arrays;

public class Sorting {
    public int[] bubbleSort(int[] input) { //pesymistyczny n^2, typowy n^2, optymistyczny n
        int[] copy = new int[input.length];
        System.arraycopy(input, 0, copy, 0, copy.length);
        for (int i = 0; i < copy.length; ++i) {
            for (int j = 1; j < input.length -1-i; ++j) { //-i dzięki temu algorytm nie sprawdza już posortowaniej części tablicy
                if (copy[j] > copy[j + 1]) {
                    int tmp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = tmp;
                }
            }
        }
        return copy;
    }

    private void merge(int[] input, int left, int middle, int right) { //pesymistyczny nlogn, typowy nlogn, optymistyczny nlogn
        int n1 = middle - left + 1;
        int n2 = right - middle;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        System.arraycopy(input, left, leftArray, 0, n1);
        System.arraycopy(input, middle + 1, rightArray, 0, n2);
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                input[k] = leftArray[i];
                i++;
            } else {
                input[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            input[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < n2) {
            input[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public void mergeSort(int[] input, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(input, left, middle);
            mergeSort(input, middle + 1, right);
            merge(input, left, middle, right);
        }
    }

    public static void main(String[] args) {
        int[] input = {1, 5, 2, 6, 4, 5, 2, 8, 7, 33, 25, 88, 6, 105, 115124, 3};
        System.out.println("Sorting input:");
        System.out.println(Arrays.toString(input));
        Sorting sorting = new Sorting();
        int[] bubbleSortResult = sorting.bubbleSort(input);
        System.out.println("Bublle sort result: " + Arrays.toString(bubbleSortResult));
        int[] mergeSortResult = new int[input.length];
        System.arraycopy(input, 0, mergeSortResult, 0, mergeSortResult.length);
        sorting.mergeSort(mergeSortResult, 0, input.length - 1);
        System.out.println("Merge sort result: mergeSortResult)" + Arrays.toString(mergeSortResult));
    }
}