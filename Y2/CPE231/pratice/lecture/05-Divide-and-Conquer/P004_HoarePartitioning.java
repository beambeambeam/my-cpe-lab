public class P004_HoarePartitioning {
    private static int hoarePartition(int[] A, int l, int r) {
        int p = A[l];
        int i = l;
        int j = r + 1;
        
        do {
            do {
                i = i + 1;
            } while (i <= r && A[i] < p);
            
            do {
                j = j - 1;
            } while (j >= l && A[j] > p);
            
            if (i < j) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        } while (i < j);
        
        // Undo last swap when i >= j
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        
        // Swap pivot with j
        temp = A[l];
        A[l] = A[j];
        A[j] = temp;
        
        return j;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, 10, 8, 7, 12, 9, 2, 15};
        System.out.println("Original array: " + java.util.Arrays.toString(arr));
        
        int pivotIndex = hoarePartition(arr, 0, arr.length - 1);
        System.out.println("Pivot index: " + pivotIndex);
        System.out.println("Partitioned array: " + java.util.Arrays.toString(arr));
    }
}
