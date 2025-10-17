package th.ac.kmutt.cpe.algorithm.supawit.lab.lab08;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for the The Spirit Merchant problem (Lab 08, Problem
 * TheSpiritMerchant).
 * Tests the solve method with the two provided examples.
 */
public class TheSpiritMerchantTest {

  /**
   * Test Example 1: 5 brands, 180 minutes time limit
   * times = [30, 60, 90, 120, 50]
   * revenues = [100, 300, 400, 600, 200]
   * Expected: 900
   */
  @Test
  public void testExample1() {
    int n = 5;
    int T = 180;
    int[] times = { 30, 60, 90, 120, 50 };
    int[] revenues = { 100, 300, 400, 600, 200 };
    int expected = 900;

    int result = Solution.TheSpiritMerchant.solve(n, T, times, revenues);
    assertEquals("Example 1 should return maximum revenue of 900", expected, result);
  }

  /**
   * Test Example 2: 5 brands, 65 minutes time limit
   * times = [4, 5, 9, 10, 7]
   * revenues = [20, 9, 21, 18, 26]
   * Expected: 94
   */
  @Test
  public void testExample2() {
    int n = 5;
    int T = 65;
    int[] times = { 4, 5, 9, 10, 7 };
    int[] revenues = { 20, 9, 21, 18, 26 };
    int expected = 94;

    int result = Solution.TheSpiritMerchant.solve(n, T, times, revenues);
    assertEquals("Example 2 should return maximum revenue of 94", expected, result);
  }
}
