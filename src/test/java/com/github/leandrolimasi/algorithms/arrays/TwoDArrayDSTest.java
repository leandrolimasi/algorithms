package com.github.leandrolimasi.algorithms.arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TwoDArrayDSTest {

  private TwoDArrayDS twoDArrayDS = new TwoDArrayDS();

  @Test
  public void testCase1() {
    int[][] hourglass = {
      {1, 1, 1, 0, 0, 0},
      {0, 1, 0, 0, 0, 0},
      {1, 1, 1, 0, 0, 0},
      {0, 0, 2, 4, 4, 0},
      {0, 0, 0, 2, 0, 0},
      {0, 0, 1, 2, 4, 0}
    };

    Assert.assertEquals(19, twoDArrayDS.hourglassSum(hourglass));
  }

  @Test
  public void testCase2() {
    int[][] hourglass = {
      {1, 1, 1, 0, 0, 0},
      {0, 1, 0, 0, 0, 0},
      {1, 1, 1, 0, 0, 0},
      {0, 9, 2, -4, -4, 0},
      {0, 0, 0, -2, 0, 0},
      {0, 0, -1, -2, -4, 0}
    };

    Assert.assertEquals(13, twoDArrayDS.hourglassSum(hourglass));
  }

  @Test
  public void testCase3() {
    int[][] hourglass = {
      {-9, -9, -9, 1, 1, 1},
      {0, -9, 0, 4, 3, 2},
      {-9, -9, -9, 1, 2, 3},
      {0, 0, 8, 6, 6, 0},
      {0, 0, 0, -2, 0, 0},
      {0, 0, 1, 2, 4, 0}
    };

    Assert.assertEquals(28, twoDArrayDS.hourglassSum(hourglass));
  }

  @Test
  public void testCase4() {
    int[][] hourglass = {
      {0, -4, -6, 0, -7, -6},
      {-1, -2, -6, -8, -3, -1},
      {-8, -4, -2, -8, -8, -6},
      {-3, -1, -2, -5, -7, -4},
      {-3, -5, -3, -6, -6, -6},
      {-3, -6, 0, -8, -6, -7}
    };

    Assert.assertEquals(-19, twoDArrayDS.hourglassSum(hourglass));
  }
}
