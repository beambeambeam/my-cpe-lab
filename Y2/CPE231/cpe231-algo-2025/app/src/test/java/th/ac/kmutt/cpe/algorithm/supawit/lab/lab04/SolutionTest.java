package th.ac.kmutt.cpe.algorithm.supawit.lab.lab04;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test suite for the Solution class - Base cases only.
 * Tests the countValidSubstrings method with the provided test cases.
 */
public class SolutionTest {

  private Solution solution;

  @Before
  public void setUp() {
    solution = new Solution();
  }

  /**
   * Test base case 1
   */
  @Test
  public void testBasecase1() {
    String beads = "CABAAXBYA";
    char A = 'A', B = 'B';
    int p = 3;
    int expected = 3;
    int result = solution.countValidSubstrings(beads, A, B, p);
    assertEquals("Base case 1 should return 3", expected, result);
  }

  /**
   * Test base case 2
   */
  @Test
  public void testBasecase2() {
    String beads = "TAWEECHAINUNTAWISUTTIWONGCPEKMUTT";
    char A = 'E', B = 'T';
    int p = 5;
    int expected = 12;
    int result = solution.countValidSubstrings(beads, A, B, p);
    assertEquals("Base case 2 should return 12", expected, result);
  }

  /**
   * Test base case 3
   */
  @Test
  public void testBasecase3() {
    String beads = "IAMVERYHANDSOMEANDBEAUTIFULANDVERYGOODATCODING";
    char A = 'S', B = 'G';
    int p = 10;
    int expected = 2;
    int result = solution.countValidSubstrings(beads, A, B, p);
    assertEquals("Base case 3 should return 2", expected, result);
  }

  /**
   * Test base case 4
   */
  @Test
  public void testBasecase4() {
    String beads = "AAEWA";
    char A = 'A', B = 'A';
    int p = 1;
    int expected = 6;
    int result = solution.countValidSubstrings(beads, A, B, p);
    assertEquals("Base case 4 should return 6", expected, result);
  }
}
