package th.ac.kmutt.cpe.algorithm.supawit.searching;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Universal test suite for all SearchStrategy implementations.
 * Uses parameterized tests to test multiple algorithms with the same test
 * cases.
 * This approach eliminates code duplication and ensures all search algorithms
 * are tested consistently.
 */
@RunWith(Parameterized.class)
public class SearchStrategyTest {

  private final SearchStrategy<Integer> searchStrategy;
  private final String algorithmName;

  public SearchStrategyTest(SearchStrategy<Integer> searchStrategy, String algorithmName) {
    this.searchStrategy = searchStrategy;
    this.algorithmName = algorithmName;
  }

  /**
   * Provides test data for parameterized tests.
   * Each algorithm implementation is tested with the same test cases.
   */
  @Parameterized.Parameters(name = "{1}")
  public static Collection<Object[]> searchStrategies() {
    return Arrays.asList(new Object[][] {
        { new LinearSearch<Integer>(), "LinearSearch" },
        { new BinarySearch<Integer>(), "BinarySearch" }
    });
  }

  /**
   * Test searching in an empty list
   */
  @Test
  public void testSearchEmptyList() {
    ArrayList<Integer> emptyList = new ArrayList<>();
    int result = searchStrategy.search(emptyList, 5);

    assertEquals(algorithmName + ": Search in empty list should return -1", -1, result);
  }

  /**
   * Test searching in a single element list - element found
   */
  @Test
  public void testSearchSingleElementFound() {
    ArrayList<Integer> singleElement = new ArrayList<>(Arrays.asList(42));
    int result = searchStrategy.search(singleElement, 42);

    assertEquals(algorithmName + ": Search should find element at index 0", 0, result);
  }

  /**
   * Test searching in a single element list - element not found
   */
  @Test
  public void testSearchSingleElementNotFound() {
    ArrayList<Integer> singleElement = new ArrayList<>(Arrays.asList(42));
    int result = searchStrategy.search(singleElement, 10);

    assertEquals(algorithmName + ": Search should return -1 when element not found", -1, result);
  }

  /**
   * Test searching for the first element in a sorted list
   */
  @Test
  public void testSearchFirstElement() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11));
    int result = searchStrategy.search(list, 1);

    assertEquals(algorithmName + ": Search should find first element at index 0", 0, result);
  }

  /**
   * Test searching for the last element in a sorted list
   */
  @Test
  public void testSearchLastElement() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11));
    int result = searchStrategy.search(list, 11);

    assertEquals(algorithmName + ": Search should find last element at index 5", 5, result);
  }

  /**
   * Test searching for a middle element in a sorted list
   */
  @Test
  public void testSearchMiddleElement() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11));
    int result = searchStrategy.search(list, 7);

    assertEquals(algorithmName + ": Search should find middle element at index 3", 3, result);
  }

  /**
   * Test searching for an element that doesn't exist - too small
   */
  @Test
  public void testSearchElementTooSmall() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11));
    int result = searchStrategy.search(list, 0);

    assertEquals(algorithmName + ": Search should return -1 for element smaller than all", -1, result);
  }

  /**
   * Test searching for an element that doesn't exist - too large
   */
  @Test
  public void testSearchElementTooLarge() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11));
    int result = searchStrategy.search(list, 15);

    assertEquals(algorithmName + ": Search should return -1 for element larger than all", -1, result);
  }

  /**
   * Test searching for an element that doesn't exist - in between existing
   * elements
   */
  @Test
  public void testSearchElementInBetween() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9, 11));
    int result = searchStrategy.search(list, 4);

    assertEquals(algorithmName + ": Search should return -1 for element between existing elements", -1, result);
  }

  /**
   * Test searching with duplicate elements
   */
  @Test
  public void testSearchWithDuplicates() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 2, 2, 5, 7));
    int result = searchStrategy.search(list, 2);

    // Both algorithms should find one of the duplicate elements
    assertTrue(algorithmName + ": Search should find one of the duplicate elements",
        result >= 1 && result <= 3);
    assertEquals(algorithmName + ": Found element should be the target", Integer.valueOf(2), list.get(result));
  }

  /**
   * Test searching with negative numbers
   */
  @Test
  public void testSearchNegativeNumbers() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-10, -5, -1, 0, 3, 7));
    int result = searchStrategy.search(list, -5);

    assertEquals(algorithmName + ": Search should find negative number at index 1", 1, result);
  }

  /**
   * Test searching for zero
   */
  @Test
  public void testSearchZero() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(-5, -1, 0, 3, 7));
    int result = searchStrategy.search(list, 0);

    assertEquals(algorithmName + ": Search should find zero at index 2", 2, result);
  }

  /**
   * Test searching with large numbers
   */
  @Test
  public void testSearchLargeNumbers() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1000000, 2000000, 3000000, 4000000));
    int result = searchStrategy.search(list, 3000000);

    assertEquals(algorithmName + ": Search should find large number at index 2", 2, result);
  }

  /**
   * Test searching in a two-element list - first element
   */
  @Test
  public void testSearchTwoElementsFirst() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 10));
    int result = searchStrategy.search(list, 5);

    assertEquals(algorithmName + ": Search should find first element at index 0", 0, result);
  }

  /**
   * Test searching in a two-element list - second element
   */
  @Test
  public void testSearchTwoElementsSecond() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 10));
    int result = searchStrategy.search(list, 10);

    assertEquals(algorithmName + ": Search should find second element at index 1", 1, result);
  }

  /**
   * Test searching in a two-element list - element not found
   */
  @Test
  public void testSearchTwoElementsNotFound() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 10));
    int result = searchStrategy.search(list, 7);

    assertEquals(algorithmName + ": Search should return -1 when element not found", -1, result);
  }

  /**
   * Test searching with null list (LinearSearch specific behavior)
   */
  @Test
  public void testSearchNullList() {
    if (searchStrategy instanceof LinearSearch) {
      int result = searchStrategy.search(null, 5);
      assertEquals(algorithmName + ": Search should return -1 for null list", -1, result);
    } else {
      // BinarySearch will throw NullPointerException for null list, which is expected
      try {
        searchStrategy.search(null, 5);
        fail(algorithmName + ": Should throw NullPointerException for null list");
      } catch (NullPointerException e) {
        // This is expected behavior for BinarySearch
      }
    }
  }

  /**
   * Test searching with null elements (LinearSearch specific behavior)
   */
  @Test
  public void testSearchWithNullElements() {
    if (searchStrategy instanceof LinearSearch) {
      ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, null, 3, null, 5));
      int result = searchStrategy.search(list, null);

      assertEquals(algorithmName + ": Search should find first null at index 1", 1, result);
    }
    // BinarySearch doesn't handle null elements gracefully, so we skip this test
  }

  /**
   * Test searching for null target in list with null elements (LinearSearch
   * specific)
   */
  @Test
  public void testSearchNullTarget() {
    if (searchStrategy instanceof LinearSearch) {
      ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, null, 4, 5));
      int result = searchStrategy.search(list, null);

      assertEquals(algorithmName + ": Search should find null element at index 2", 2, result);
    }
    // BinarySearch will throw NullPointerException for null target, which is
    // expected
  }

  /**
   * Test boundary conditions with extreme values
   */
  @Test
  public void testSearchExtremeValues() {
    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE));

    int minResult = searchStrategy.search(list, Integer.MIN_VALUE);
    assertEquals(algorithmName + ": Search should find MIN_VALUE at index 0", 0, minResult);

    int maxResult = searchStrategy.search(list, Integer.MAX_VALUE);
    assertEquals(algorithmName + ": Search should find MAX_VALUE at index 4", 4, maxResult);
  }

  /**
   * Test performance with a moderately large list
   */
  @Test
  public void testSearchLargeList() {
    ArrayList<Integer> largeList = new ArrayList<>();
    for (int i = 0; i < 1000; i += 2) {
      largeList.add(i);
    }

    int result = searchStrategy.search(largeList, 500);
    assertEquals(algorithmName + ": Search should find element in large list", 250, result);

    int notFoundResult = searchStrategy.search(largeList, 501);
    assertEquals(algorithmName + ": Search should return -1 for odd number not in list", -1, notFoundResult);
  }

  /**
   * Test with unsorted list (LinearSearch should work, BinarySearch may not)
   */
  @Test
  public void testSearchUnsortedList() {
    ArrayList<Integer> unsortedList = new ArrayList<>(Arrays.asList(5, 1, 3, 9, 2));
    int result = searchStrategy.search(unsortedList, 3);

    if (searchStrategy instanceof LinearSearch) {
      assertEquals(algorithmName + ": LinearSearch should find element in unsorted list at index 2", 2, result);
    } else {
      // BinarySearch may not work correctly with unsorted data, but should not crash
      assertTrue(algorithmName + ": BinarySearch should complete without error", result >= -1);
    }
  }

  /**
   * Test that the search strategy implements the interface correctly
   */
  @Test
  public void testImplementsInterface() {
    assertTrue(algorithmName + ": Should implement SearchStrategy interface",
        searchStrategy instanceof SearchStrategy);

    ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
    int result = searchStrategy.search(list, 5);
    assertEquals(algorithmName + ": Interface method should work correctly", 2, result);
  }
}
