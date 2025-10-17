package th.ac.kmutt.cpe.algorithm.supawit.lab.lab08;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for the Zigzag Subsequence problem (Lab 08, Problem Z).
 * Tests the solve method with the three provided examples.
 */
public class SolutionTest {

  /**
   * Test Example 1: Sequence [13, 93, 86, 50, 63, 4]
   * Expected dpAsc: [1, 2, 2, 2, 4, 1]
   * Expected dpDesc: [1, 1, 3, 3, 3, 5]
   */
  @Test
  public void testExample1() {
    int[] sequence = { 13, 93, 86, 50, 63, 4 };
    int[] expectedDpAsc = { 1, 2, 2, 2, 4, 1 };
    int[] expectedDpDesc = { 1, 1, 3, 3, 3, 5 };

    int[][] result = Solution.Z.solve(sequence);
    int[] dpAsc = result[0];
    int[] dpDesc = result[1];

    assertArrayEquals("Example 1 dpAsc should match expected values", expectedDpAsc, dpAsc);
    assertArrayEquals("Example 1 dpDesc should match expected values", expectedDpDesc, dpDesc);
  }

  /**
   * Test Example 2: Sequence [1, 45, 2, 44, 3, 43, 4, 42, 5, 41]
   * Expected dpAsc: [1, 2, 2, 4, 4, 6, 6, 8, 8, 10]
   * Expected dpDesc: [1, 1, 3, 3, 5, 5, 7, 7, 9, 9]
   */
  @Test
  public void testExample2() {
    int[] sequence = { 1, 45, 2, 44, 3, 43, 4, 42, 5, 41 };
    int[] expectedDpAsc = { 1, 2, 2, 4, 4, 6, 6, 8, 8, 10 };
    int[] expectedDpDesc = { 1, 1, 3, 3, 5, 5, 7, 7, 9, 9 };

    int[][] result = Solution.Z.solve(sequence);
    int[] dpAsc = result[0];
    int[] dpDesc = result[1];

    assertArrayEquals("Example 2 dpAsc should match expected values", expectedDpAsc, dpAsc);
    assertArrayEquals("Example 2 dpDesc should match expected values", expectedDpDesc, dpDesc);
  }

  /**
   * Test Example 3: Sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]
   * Expected dpAsc: [1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2]
   * Expected dpDesc: [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
   */
  @Test
  public void testExample3() {
    int[] sequence = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
    int[] expectedDpAsc = { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 };
    int[] expectedDpDesc = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

    int[][] result = Solution.Z.solve(sequence);
    int[] dpAsc = result[0];
    int[] dpDesc = result[1];

    assertArrayEquals("Example 3 dpAsc should match expected values", expectedDpAsc, dpAsc);
    assertArrayEquals("Example 3 dpDesc should match expected values", expectedDpDesc, dpDesc);
  }
}
