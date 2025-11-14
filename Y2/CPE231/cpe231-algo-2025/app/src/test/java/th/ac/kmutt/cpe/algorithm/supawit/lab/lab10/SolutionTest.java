package th.ac.kmutt.cpe.algorithm.supawit.lab.lab10;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class SolutionTest {

  private Solution solution;

  @Before
  public void setUp() {
    solution = new Solution();
  }

  @Test
  public void testExample1() {
    int n = 4;
    int[][] companyPreferences = {
      { 3, 1, 2, 0 },
      { 1, 0, 2, 3 },
      { 0, 1, 2, 3 },
      { 0, 1, 2, 3 }
    };
    int[][] studentPreferences = {
      { 0, 1, 2, 3 },
      { 0, 1, 2, 3 },
      { 0, 1, 2, 3 },
      { 0, 1, 2, 3 }
    };

    int[] result = solution.solve(n, companyPreferences, studentPreferences);

    assertEquals("Student 0 should be matched with company 2", 2, result[0]);
    assertEquals("Student 1 should be matched with company 1", 1, result[1]);
    assertEquals("Student 2 should be matched with company 3", 3, result[2]);
    assertEquals("Student 3 should be matched with company 0", 0, result[3]);
  }

  @Test
  public void testExample2() {
    int n = 5;
    int[][] companyPreferences = {
      { 1, 4, 2, 3, 0 },
      { 1, 0, 2, 4, 3 },
      { 0, 1, 2, 3, 4 },
      { 4, 3, 2, 0, 1 },
      { 0, 2, 4, 1, 3 }
    };
    int[][] studentPreferences = {
      { 0, 1, 2, 3, 4 },
      { 0, 3, 4, 2, 1 },
      { 3, 4, 2, 1, 0 },
      { 4, 1, 0, 2, 3 },
      { 3, 4, 1, 0, 2 }
    };

    int[] result = solution.solve(n, companyPreferences, studentPreferences);

    assertEquals("Student 0 should be matched with company 1", 1, result[0]);
    assertEquals("Student 1 should be matched with company 0", 0, result[1]);
    assertEquals("Student 2 should be matched with company 4", 4, result[2]);
    assertEquals("Student 3 should be matched with company 2", 2, result[3]);
    assertEquals("Student 4 should be matched with company 3", 3, result[4]);
  }

  @Test
  public void testSingleCompanyStudent() {
    int n = 1;
    int[][] companyPreferences = {
      { 0 }
    };
    int[][] studentPreferences = {
      { 0 }
    };

    int[] result = solution.solve(n, companyPreferences, studentPreferences);

    assertEquals("Student 0 should be matched with company 0", 0, result[0]);
  }

  @Test
  public void testStability() {
    int n = 3;
    int[][] companyPreferences = {
      { 0, 1, 2 },
      { 1, 0, 2 },
      { 0, 1, 2 }
    };
    int[][] studentPreferences = {
      { 0, 1, 2 },
      { 1, 0, 2 },
      { 0, 1, 2 }
    };

    int[] result = solution.solve(n, companyPreferences, studentPreferences);

    assertNotNull("Result should not be null", result);
    assertEquals("Result should have length n", n, result.length);

    boolean[] companyMatched = new boolean[n];
    for (int i = 0; i < n; i++) {
      int company = result[i];
      assertFalse("Each company should be matched exactly once", companyMatched[company]);
      companyMatched[company] = true;
      assertTrue("Company index should be valid", company >= 0 && company < n);
    }
  }
}
