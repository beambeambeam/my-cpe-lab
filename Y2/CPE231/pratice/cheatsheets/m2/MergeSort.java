import java.util.Comparator;
import java.util.Arrays;

// ======= Generic MergeSort Implementation =======
class MergeSort {

  // Public method to sort using a custom comparator
  public static <T> void sort(T[] array, Comparator<? super T> comp) {
    if (array == null || array.length <= 1) {
      return;
    }
    // Create a temporary array for merging to avoid re-allocation in recursive
    // calls
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Object[array.length];
    mergeSort(array, temp, 0, array.length - 1, comp);
  }

  // Public method to sort using natural order (for Comparable types)
  public static <T extends Comparable<? super T>> void sort(T[] array) {
    sort(array, Comparator.naturalOrder());
  }

  // Recursive helper for MergeSort
  private static <T> void mergeSort(T[] array, T[] temp, int low, int high, Comparator<? super T> comp) {
    if (low < high) {
      int mid = low + (high - low) / 2;
      mergeSort(array, temp, low, mid, comp); // Sort left half
      mergeSort(array, temp, mid + 1, high, comp); // Sort right half
      merge(array, temp, low, mid, high, comp); // Merge them
    }
  }

  // Merge operation
  private static <T> void merge(T[] array, T[] temp, int low, int mid, int high, Comparator<? super T> comp) {
    // Copy both halves into the temporary array
    System.arraycopy(array, low, temp, low, high - low + 1);

    int i = low; // Pointer for the left half (in temp array)
    int j = mid + 1; // Pointer for the right half (in temp array)
    int k = low; // Pointer for the original array

    // Merge back into the original array
    while (i <= mid && j <= high) {
      if (comp.compare(temp[i], temp[j]) <= 0) {
        array[k++] = temp[i++];
      } else {
        array[k++] = temp[j++];
      }
    }

    // Copy remaining elements of the left half, if any
    while (i <= mid) {
      array[k++] = temp[i++];
    }
    // No need to copy remaining elements of the right half, as they are already in
    // place
    // in the original array if the right half was simply copied.
    // However, given the System.arraycopy above, it's correct even if they were
    // from the right half.
  }

  // Helper method for printing arrays in the demo
  private static <T> void printArray(String prefix, T[] array) {
    System.out.println(prefix + Arrays.toString(array));
  }

  public static void main(String[] args) {
    System.out.println("--- MergeSort Demo ---");

    // ---- 1. Integer Array (Natural Order) ----
    Integer[] intArr = { 9, 1, 5, 3, 7, 2, 8, 4, 6 };
    printArray("Original Integers: ", intArr);
    MergeSort.sort(intArr);
    printArray("Sorted Integers:   ", intArr);
    System.out.println();

    // ---- 2. String Array (Natural Order) ----
    String[] strArr = { "pear", "apple", "orange", "banana", "kiwi" };
    printArray("Original Strings:  ", strArr);
    MergeSort.sort(strArr);
    printArray("Sorted Strings:    ", strArr);
    System.out.println();

    // ---- 3. Person Objects (Sort by Age) ----
    Person[] people = {
        new Person("Alice", 30),
        new Person("Bob", 25),
        new Person("Diana", 28),
        new Person("Charlie", 35)
    };
    printArray("Original People:   ", people);
    MergeSort.sort(people, Comparator.comparingInt(p -> p.age));
    printArray("People by age:     ", people);
    System.out.println();

    // ---- 4. Book Objects (implements Comparable - sorted by year) ----
    Book[] books = {
        new Book("Dune", 1965),
        new Book("Foundation", 1951),
        new Book("Neuromancer", 1984),
        new Book("1984", 1949)
    };
    printArray("Original Books:    ", books);
    MergeSort.sort(books); // Uses Book's compareTo (by year)
    printArray("Books by year:     ", books);
    System.out.println();

    // ---- 5. Reversing Order (Strings) ----
    String[] strArrRev = { "pear", "apple", "orange", "banana" };
    printArray("Original Strings:  ", strArrRev);
    // Explicitly specify String for naturalOrder() to avoid ambiguity inside
    // generic class
    MergeSort.sort(strArrRev, Comparator.<String>naturalOrder().reversed());
    printArray("Strings (reversed):", strArrRev);
    System.out.println();

    // ---- 6. Multiple Criteria Sorting (Student by GPA desc, then ID asc) ----
    Student[] students = {
        new Student("Ken", 3.7, 1002),
        new Student("Anna", 3.9, 1001),
        new Student("Leo", 3.7, 1000),
        new Student("Zoe", 3.9, 1005)
    };
    printArray("Original Students: ", students);
    MergeSort.sort(students,
        Comparator.comparingDouble((Student s) -> s.gpa).reversed() // GPA descending
            .thenComparingInt(s -> s.id)); // then ID ascending
    printArray("Students (GPA desc then ID): ", students);
    System.out.println();
  }
}
