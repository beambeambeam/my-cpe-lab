import java.util.Comparator;
import java.util.Arrays;

// ======= Generic BinarySearch Implementation =======
class BinarySearch {

  public static <T extends Comparable<? super T>> int binarySearch(T[] array, T key) {
    return binarySearch(array, key, Comparator.naturalOrder());
  }

  public static <T> int binarySearch(T[] array, T key, Comparator<? super T> comp) {
    if (array == null) {
      throw new IllegalArgumentException("Array cannot be null.");
    }

    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
      int mid = low + (high - low) / 2;
      int cmp = comp.compare(array[mid], key);

      if (cmp < 0) {
        low = mid + 1;
      } else if (cmp > 0) {
        high = mid - 1; // Corrected this line: was 'high = mid - mid - 1;'
      } else {
        return mid;
      }
    }
    return -(low + 1);
  }

  private static <T> void printSearchResult(String testName, int index, T key, T[] array) {
    System.out.print(testName + " | Searching for " + key + " in " + Arrays.toString(array) + ": ");
    if (index >= 0) {
      System.out.println("Found at index " + index + " (Value: " + array[index] + ")");
    } else {
      System.out.println("Not found. Insertion point: " + (-(index + 1)));
    }
  }

  public static void main(String[] args) {
    // --- 1. Integers (Natural Order) ---
    Integer[] intArr = { 1, 3, 5, 7, 9 };
    printSearchResult("Int found", BinarySearch.binarySearch(intArr, 5), 5, intArr);
    printSearchResult("Int not found", BinarySearch.binarySearch(intArr, 4), 4, intArr);
    printSearchResult("Int not found (end)", BinarySearch.binarySearch(intArr, 10), 10, intArr);
    printSearchResult("Int not found (start)", BinarySearch.binarySearch(intArr, 0), 0, intArr);
    System.out.println();

    // --- 2. Strings (Natural Order) ---
    String[] strArr = { "apple", "banana", "orange", "pear" };
    printSearchResult("String found", BinarySearch.binarySearch(strArr, "orange"), "orange", strArr);
    printSearchResult("String not found", BinarySearch.binarySearch(strArr, "grape"), "grape", strArr);
    System.out.println();

    // --- 3. Custom Object with Comparable (Book by year) ---
    class Book implements Comparable<Book> {
      String title;
      int year;

      Book(String title, int year) {
        this.title = title;
        this.year = year;
      }

      @Override
      public int compareTo(Book other) {
        return Integer.compare(this.year, other.year);
      }

      @Override
      public String toString() {
        return title + "(" + year + ")";
      }
    }

    Book[] books = {
        new Book("Foundation", 1951),
        new Book("Dune", 1965),
        new Book("Neuromancer", 1984)
    };
    printSearchResult("Book found", BinarySearch.binarySearch(books, new Book("Dune", 1965)), new Book("Dune", 1965),
        books);
    printSearchResult("Book not found", BinarySearch.binarySearch(books, new Book("1984", 1949)),
        new Book("1984", 1949), books);
    System.out.println();

    // --- 4. Custom Object with Comparator (Person by age) ---
    class Person {
      String name;
      int age;

      Person(String name, int age) {
        this.name = name;
        this.age = age;
      }

      @Override
      public String toString() {
        return name + "(" + age + ")";
      }
    }

    Person[] people = {
        new Person("Bob", 25),
        new Person("Diana", 28),
        new Person("Alice", 30)
    };
    Comparator<Person> ageComparator = Comparator.comparingInt(p -> p.age);

    printSearchResult("Person found", BinarySearch.binarySearch(people, new Person("Alice", 30), ageComparator),
        new Person("Alice", 30), people);
    printSearchResult("Person not found", BinarySearch.binarySearch(people, new Person("Charlie", 27), ageComparator),
        new Person("Charlie", 27), people);
    System.out.println();

    // --- 5. Searching in Reverse Order (requires sorting in reverse first) ---
    String[] strArrRev = { "pear", "orange", "banana", "apple" };
    // FIX: Explicitly specify the type parameter for naturalOrder()
    Comparator<String> reverseStringComp = Comparator.<String>naturalOrder().reversed();

    printSearchResult("String (rev) found", BinarySearch.binarySearch(strArrRev, "orange", reverseStringComp), "orange",
        strArrRev);
    printSearchResult("String (rev) not found", BinarySearch.binarySearch(strArrRev, "grape", reverseStringComp),
        "grape", strArrRev);
    System.out.println();

    // --- 6. Multiple Criteria (Student by GPA desc, then ID asc) ---
    class Student {
      String name;
      double gpa;
      int id;

      Student(String name, double gpa, int id) {
        this.name = name;
        this.gpa = gpa;
        this.id = id;
      }

      @Override
      public String toString() {
        return name + "(GPA:" + gpa + ",ID:" + id + ")";
      }
    }

    Student[] students = {
        new Student("Leo", 3.7, 1000),
        new Student("Ken", 3.7, 1002),
        new Student("Anna", 3.9, 1001)
    };
    Arrays.sort(students, Comparator.comparingDouble((Student s) -> s.gpa).reversed().thenComparingInt(s -> s.id));
    System.out.println("Students array (sorted): " + Arrays.toString(students));

    Comparator<Student> studentSearchComp = Comparator.comparingDouble((Student s) -> s.gpa).reversed()
        .thenComparingInt(s -> s.id);

    printSearchResult("Student found (exact)",
        BinarySearch.binarySearch(students, new Student("Ken", 3.7, 1002), studentSearchComp),
        new Student("Ken", 3.7, 1002), students);
    printSearchResult("Student found (diff name)",
        BinarySearch.binarySearch(students, new Student("UNKNOWN", 3.7, 1000), studentSearchComp),
        new Student("UNKNOWN", 3.7, 1000), students);
    printSearchResult("Student not found",
        BinarySearch.binarySearch(students, new Student("Mike", 3.8, 1005), studentSearchComp),
        new Student("Mike", 3.8, 1005), students);
  }
}
