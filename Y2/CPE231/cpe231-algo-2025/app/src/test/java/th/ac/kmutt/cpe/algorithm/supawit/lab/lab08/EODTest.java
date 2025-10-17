package th.ac.kmutt.cpe.algorithm.supawit.lab.lab08;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for the Explosive Ordnance Disposal (EOD) problem (Lab 08, Problem
 * EOD).
 * Tests the solve method with the four provided examples.
 */
public class EODTest {

  /**
   * Test Example 1: 10 sections with blast radius [2, 1, 3, 2, 1, 4, 1, 4, 2, 1]
   * Expected: 11
   */
  @Test
  public void testExample1() {
    int n = 10;
    int[] blastRadius = { 2, 1, 3, 2, 1, 4, 1, 4, 2, 1 };
    int expected = 11;

    int result = Solution.EOD.solve(n, blastRadius);
    assertEquals("Example 1 should return maximum cleared area of 11", expected, result);
  }

  /**
   * Test Example 2: 8 sections with blast radius [1, 1, 1, 1, 1, 1, 1, 1]
   * Expected: 8
   */
  @Test
  public void testExample2() {
    int n = 8;
    int[] blastRadius = { 1, 1, 1, 1, 1, 1, 1, 1 };
    int expected = 8;

    int result = Solution.EOD.solve(n, blastRadius);
    assertEquals("Example 2 should return maximum cleared area of 8", expected, result);
  }

  /**
   * Test Example 3: 13 sections with blast radius [4, 5, 3, 6, 2, 7, 3, 5, 4, 6,
   * 2, 4, 1]
   * Expected: 14
   */
  @Test
  public void testExample3() {
    int n = 13;
    int[] blastRadius = { 4, 5, 3, 6, 2, 7, 3, 5, 4, 6, 2, 4, 1 };
    int expected = 14;

    int result = Solution.EOD.solve(n, blastRadius);
    assertEquals("Example 3 should return maximum cleared area of 14", expected, result);
  }

  /**
   * Test Example 4: 15 sections with blast radius [2, 3, 2, 4, 2, 2, 2, 5, 2, 6,
   * 2, 3, 2, 4, 2]
   * Expected: 17
   */
  @Test
  public void testExample4() {
    int n = 15;
    int[] blastRadius = { 2, 3, 2, 4, 2, 2, 2, 5, 2, 6, 2, 3, 2, 4, 2 };
    int expected = 17;

    int result = Solution.EOD.solve(n, blastRadius);
    assertEquals("Example 4 should return maximum cleared area of 17", expected, result);
  }
}
