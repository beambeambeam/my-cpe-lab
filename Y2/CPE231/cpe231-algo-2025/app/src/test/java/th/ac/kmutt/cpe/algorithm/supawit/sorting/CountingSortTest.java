package th.ac.kmutt.cpe.algorithm.supawit.sorting;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Comprehensive test suite for CountingSort implementation.
 * Tests basic functionality, edge cases, and error conditions.
 */
public class CountingSortTest {

  private CountingSort<Integer> countingSort;

  @Before
  public void setUp() {
    countingSort = new CountingSort<>();
  }

  /**
   * Test sorting an empty list
   */
  @Test
  public void testSortEmptyList() {
    ArrayList<Integer> emptyList = new ArrayList<>();
    ArrayList<Integer> result = countingSort.sorting(emptyList);

    assertNotNull("Result should not be null", result);
    assertTrue("Result should be empty", result.isEmpty());
  }

  /**
   * Test sorting a null list
   */
  @Test
  public void testSortNullList() {
    ArrayList<Integer> result = countingSort.sorting(null);

    assertNotNull("Result should not be null", result);
    assertTrue("Result should be empty", result.isEmpty());
  }

  /**
   * Test sorting a single element list
   */
  @Test
  public void testSortSingleElement() {
    ArrayList<Integer> singleElement = new ArrayList<>(Arrays.asList(42));
    ArrayList<Integer> result = countingSort.sorting(singleElement);

    assertEquals("Result should have one element", 1, result.size());
    assertEquals("Element should be 42", Integer.valueOf(42), result.get(0));
  }

  /**
   * Test sorting a list with positive integers
   */
  @Test
  public void testSortPositiveIntegers() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(4, 2, 8, 1, 5));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 8));

    assertEquals("Sorted list should match expected", expected, result);
  }

  /**
   * Test sorting a list with negative integers
   */
  @Test
  public void testSortNegativeIntegers() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-3, -1, -5, -2));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-5, -3, -2, -1));

    assertEquals("Sorted negative integers should match expected", expected, result);
  }

  /**
   * Test sorting a list with mixed positive and negative integers
   */
  @Test
  public void testSortMixedIntegers() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, -1, 0, 5, -2));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-2, -1, 0, 3, 5));

    assertEquals("Sorted mixed integers should match expected", expected, result);
  }

  /**
   * Test sorting a list with duplicate elements
   */
  @Test
  public void testSortDuplicateElements() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 3, 2, 1, 2));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3));

    assertEquals("Sorted duplicates should match expected", expected, result);
  }

  /**
   * Test sorting a list with null elements
   */
  @Test
  public void testSortWithNullElements() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, null, 1, null, 2));
    ArrayList<Integer> result = countingSort.sorting(list);

    assertEquals("Result should have 5 elements", 5, result.size());
    // Null elements should come first
    assertNull("First element should be null", result.get(0));
    assertNull("Second element should be null", result.get(1));
    // Then sorted non-null elements
    assertEquals("Third element should be 1", Integer.valueOf(1), result.get(2));
    assertEquals("Fourth element should be 2", Integer.valueOf(2), result.get(3));
    assertEquals("Fifth element should be 3", Integer.valueOf(3), result.get(4));
  }

  /**
   * Test sorting a list with only null elements
   */
  @Test
  public void testSortOnlyNullElements() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(null, null, null));
    ArrayList<Integer> result = countingSort.sorting(list);

    assertEquals("Result should have 3 elements", 3, result.size());
    for (Integer element : result) {
      assertNull("All elements should be null", element);
    }
  }

  /**
   * Test sorting a large range of integers
   */
  @Test
  public void testSortLargeRange() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1000000, 1, 500000, 999999));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 500000, 999999, 1000000));

    assertEquals("Large range sorting should work", expected, result);
  }

  /**
   * Test sorting an already sorted list
   */
  @Test
  public void testSortAlreadySorted() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    assertEquals("Already sorted list should remain sorted", expected, result);
  }

  /**
   * Test sorting a reverse sorted list
   */
  @Test
  public void testSortReverseSorted() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    assertEquals("Reverse sorted list should be sorted correctly", expected, result);
  }

  /**
   * Test that CountingSort throws exception for non-integer types
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSortNonIntegerType() {
    CountingSort<String> stringSort = new CountingSort<>();
    ArrayList<String> stringList = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));
    stringSort.sorting(stringList);
  }

  /**
   * Test that CountingSort throws exception for mixed types (when using raw
   * types)
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSortMixedTypes() {
    // Create a list with mixed types by using Object and casting
    ArrayList<Integer> mixedList = new ArrayList<>();
    mixedList.add(1);
    // Simulate mixed type by adding a Double (not Integer)
    CountingSort<Double> doubleSort = new CountingSort<>();
    ArrayList<Double> doubleList = new ArrayList<>(Arrays.asList(1.5, 2.5, 3.5));
    doubleSort.sorting(doubleList);
  }

  /**
   * Test that original list is not modified (immutability)
   */
  @Test
  public void testOriginalListNotModified() {
    ArrayList<Integer> original = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));
    ArrayList<Integer> originalCopy = new ArrayList<>(original);

    countingSort.sorting(original);

    assertEquals("Original list should not be modified", originalCopy, original);
  }

  /**
   * Test sorting with zero values
   */
  @Test
  public void testSortWithZero() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 5, -3, 0, 2));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-3, 0, 0, 2, 5));

    assertEquals("Sorting with zeros should work correctly", expected, result);
  }

  /**
   * Test sorting with Integer.MAX_VALUE and Integer.MIN_VALUE
   * Should throw UnsupportedOperationException due to range overflow
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testSortExtremeValues() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(
        Integer.MAX_VALUE, 0, Integer.MIN_VALUE, 1));
    countingSort.sorting(list);
  }

  /**
   * Test sorting with moderately large values that should work
   */
  @Test
  public void testSortModeratelyLargeValues() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1000, -1000, 500, -500));
    ArrayList<Integer> result = countingSort.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-1000, -500, 500, 1000));

    assertEquals("Moderately large values should be sorted correctly", expected, result);
  }
}
