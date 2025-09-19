package th.ac.kmutt.cpe.algorithm.supawit.sorting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Universal test suite for all SortingStrategy implementations.
 * Uses parameterized tests to test multiple algorithms with the same test
 * cases.
 * This approach eliminates code duplication and ensures all sorting algorithms
 * are tested consistently.
 */
@RunWith(Parameterized.class)
public class SortingStrategyTest {

  private final SortingStrategy<Integer> sortingStrategy;
  private final String algorithmName;

  public SortingStrategyTest(SortingStrategy<Integer> sortingStrategy, String algorithmName) {
    this.sortingStrategy = sortingStrategy;
    this.algorithmName = algorithmName;
  }

  /**
   * Provides test data for parameterized tests.
   * Each algorithm implementation is tested with the same test cases.
   */
  @Parameterized.Parameters(name = "{1}")
  public static Collection<Object[]> sortingStrategies() {
    return Arrays.asList(new Object[][] {
        { new BubbleSort<Integer>(), "BubbleSort" },
        { new QuickSort<Integer>(), "QuickSort" },
        { new CountingSort<Integer>(), "CountingSort" }
    });
  }

  /**
   * Test sorting an empty list
   */
  @Test
  public void testSortEmptyList() {
    ArrayList<Integer> emptyList = new ArrayList<>();
    ArrayList<Integer> result = sortingStrategy.sorting(emptyList);

    assertNotNull(algorithmName + ": Result should not be null", result);
    assertTrue(algorithmName + ": Result should be empty", result.isEmpty());
  }

  /**
   * Test sorting a null list
   */
  @Test
  public void testSortNullList() {
    ArrayList<Integer> result = sortingStrategy.sorting(null);

    assertNotNull(algorithmName + ": Result should not be null", result);
    assertTrue(algorithmName + ": Result should be empty", result.isEmpty());
  }

  /**
   * Test sorting a single element list
   */
  @Test
  public void testSortSingleElement() {
    ArrayList<Integer> singleElement = new ArrayList<>(Arrays.asList(42));
    ArrayList<Integer> result = sortingStrategy.sorting(singleElement);

    assertEquals(algorithmName + ": Result should have one element", 1, result.size());
    assertEquals(algorithmName + ": Element should be 42", Integer.valueOf(42), result.get(0));
  }

  /**
   * Test sorting a list with positive integers
   */
  @Test
  public void testSortPositiveIntegers() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(4, 2, 8, 1, 5));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 8));

    assertEquals(algorithmName + ": Sorted list should match expected", expected, result);
  }

  /**
   * Test sorting a list with negative integers
   */
  @Test
  public void testSortNegativeIntegers() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-3, -1, -5, -2));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-5, -3, -2, -1));

    assertEquals(algorithmName + ": Sorted negative integers should match expected", expected, result);
  }

  /**
   * Test sorting a list with mixed positive and negative integers
   */
  @Test
  public void testSortMixedIntegers() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, -1, 0, 5, -2));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-2, -1, 0, 3, 5));

    assertEquals(algorithmName + ": Sorted mixed integers should match expected", expected, result);
  }

  /**
   * Test sorting a list with duplicate elements
   */
  @Test
  public void testSortDuplicateElements() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 3, 2, 1, 2));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3));

    assertEquals(algorithmName + ": Sorted duplicates should match expected", expected, result);
  }

  /**
   * Test sorting a list with null elements
   */
  @Test
  public void testSortWithNullElements() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, null, 1, null, 2));
    ArrayList<Integer> result = sortingStrategy.sorting(list);

    assertEquals(algorithmName + ": Result should have 5 elements", 5, result.size());
    // Null elements should come first
    assertNull(algorithmName + ": First element should be null", result.get(0));
    assertNull(algorithmName + ": Second element should be null", result.get(1));
    // Then sorted non-null elements
    assertEquals(algorithmName + ": Third element should be 1", Integer.valueOf(1), result.get(2));
    assertEquals(algorithmName + ": Fourth element should be 2", Integer.valueOf(2), result.get(3));
    assertEquals(algorithmName + ": Fifth element should be 3", Integer.valueOf(3), result.get(4));
  }

  /**
   * Test sorting a list with only null elements
   */
  @Test
  public void testSortOnlyNullElements() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(null, null, null));
    ArrayList<Integer> result = sortingStrategy.sorting(list);

    assertEquals(algorithmName + ": Result should have 3 elements", 3, result.size());
    for (Integer element : result) {
      assertNull(algorithmName + ": All elements should be null", element);
    }
  }

  /**
   * Test sorting an already sorted list
   */
  @Test
  public void testSortAlreadySorted() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    assertEquals(algorithmName + ": Already sorted list should remain sorted", expected, result);
  }

  /**
   * Test sorting a reverse sorted list
   */
  @Test
  public void testSortReverseSorted() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    assertEquals(algorithmName + ": Reverse sorted list should be sorted correctly", expected, result);
  }

  /**
   * Test that original list is not modified (immutability)
   */
  @Test
  public void testOriginalListNotModified() {
    ArrayList<Integer> original = new ArrayList<>(Arrays.asList(3, 1, 4, 1, 5));
    ArrayList<Integer> originalCopy = new ArrayList<>(original);

    sortingStrategy.sorting(original);

    assertEquals(algorithmName + ": Original list should not be modified", originalCopy, original);
  }

  /**
   * Test sorting with zero values
   */
  @Test
  public void testSortWithZero() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0, 5, -3, 0, 2));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-3, 0, 0, 2, 5));

    assertEquals(algorithmName + ": Sorting with zeros should work correctly", expected, result);
  }

  /**
   * Test sorting with moderately large values
   */
  @Test
  public void testSortModeratelyLargeValues() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1000, -1000, 500, -500));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-1000, -500, 500, 1000));

    assertEquals(algorithmName + ": Moderately large values should be sorted correctly", expected, result);
  }

  /**
   * Test sorting with a larger dataset
   */
  @Test
  public void testSortLargeDataset() {
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 50; i >= 1; i--) {
      list.add(i);
    }

    ArrayList<Integer> result = sortingStrategy.sorting(list);

    assertEquals(algorithmName + ": Large dataset should be sorted", 50, result.size());
    for (int i = 0; i < 50; i++) {
      assertEquals(algorithmName + ": Element at index " + i + " should be " + (i + 1),
          Integer.valueOf(i + 1), result.get(i));
    }
  }

  /**
   * Test sorting with duplicate values at boundaries
   */
  @Test
  public void testSortBoundaryDuplicates() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 1, 5, 5, 3, 3));
    ArrayList<Integer> result = sortingStrategy.sorting(list);
    ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1, 1, 3, 3, 5, 5));

    assertEquals(algorithmName + ": Boundary duplicates should be sorted correctly", expected, result);
  }

  /**
   * Test that the sorting strategy implements the interface correctly
   */
  @Test
  public void testImplementsInterface() {
    assertNotNull(algorithmName + ": Strategy should not be null", sortingStrategy);
    assertTrue(algorithmName + ": Strategy should implement SortingStrategy",
        sortingStrategy instanceof SortingStrategy);
  }
}
