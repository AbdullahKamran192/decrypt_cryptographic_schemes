import java.util.*;

public class all_combinations {

    // Helper function to find all combinations
    // of size r in an array of size n
    static void combinationUtil(int ind, int r, List<Integer> data, 
        List<List<Integer>> result, int[] arr) {
        int n = arr.length;

        // If size of current combination is r
        if (data.size() == r) {
            result.add(new ArrayList<>(data));
            return;
        }

        // Replace index with all possible elements
        for (int i = ind; i < n; i++) {

            // Current element is included
            data.add(arr[i]);

            // Recur for next elements
            combinationUtil(i + 1, r, data, result, arr);

            // Backtrack to find other combinations
            data.remove(data.size() - 1);
        }
    }

    // Function to find all combinations of size r
    // in an array of size n
    static List<List<Integer>> findCombination(int[] arr, int r) {
        int n = arr.length;

        // to store the result
        List<List<Integer>> result = new ArrayList<>();

        // Temporary array to store current combination
        List<Integer> data = new ArrayList<>();
        combinationUtil(0, r, data, result, arr);
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int r = 2;
        List<List<Integer>> res = findCombination(arr, r);
        for (List<Integer> comb : res) {
            for (int num : comb) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}