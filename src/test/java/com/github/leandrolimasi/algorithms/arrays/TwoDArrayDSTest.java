package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Fixed-shape sliding window over a matrix (brute force)</b>.
 *
 * <p>{@link TwoDArrayDS#hourglassSum(int[][])} slides a fixed 3x3 "hourglass" mask (top row, one
 * middle cell, bottom row) across every valid top-left anchor in the 6x6 grid, sums the 7 cells
 * the mask covers, and keeps the maximum. Because the input size is fixed at 6x6, this brute-force
 * double loop is already effectively O(1); the interesting part is the fixed index arithmetic that
 * describes the hourglass shape ({@code [i][j..j+2]}, {@code [i+1][j+1]}, {@code [i+2][j..j+2]}).
 *
 * <p>Learning point: not every matrix problem needs a clever algorithm &mdash; when the window
 * shape and the grid size are both small/fixed, direct enumeration is the simplest correct
 * solution, and prematurely optimizing it would only add complexity.
 */
public class TwoDArrayDSTest {

  private TwoDArrayDS twoDArrayDS = new TwoDArrayDS();

  @Test
  @DisplayName("Mixed positive values: the maximum hourglass sits in the bottom-right region")
  public void testCase1() {
    int[][] hourglass = {
      {1, 1, 1, 0, 0, 0},
      {0, 1, 0, 0, 0, 0},
      {1, 1, 1, 0, 0, 0},
      {0, 0, 2, 4, 4, 0},
      {0, 0, 0, 2, 0, 0},
      {0, 0, 1, 2, 4, 0}
    };

    assertEquals(19, twoDArrayDS.hourglassSum(hourglass));
  }

  @Test
  @DisplayName("Mix of positive and negative values: the max is still found among positive regions")
  public void testCase2() {
    int[][] hourglass = {
      {1, 1, 1, 0, 0, 0},
      {0, 1, 0, 0, 0, 0},
      {1, 1, 1, 0, 0, 0},
      {0, 9, 2, -4, -4, 0},
      {0, 0, 0, -2, 0, 0},
      {0, 0, -1, -2, -4, 0}
    };

    assertEquals(13, twoDArrayDS.hourglassSum(hourglass));
  }

  @Test
  @DisplayName("Maximum hourglass is in the top-right region while the top-left region is deeply negative")
  public void testCase3() {
    int[][] hourglass = {
      {-9, -9, -9, 1, 1, 1},
      {0, -9, 0, 4, 3, 2},
      {-9, -9, -9, 1, 2, 3},
      {0, 0, 8, 6, 6, 0},
      {0, 0, 0, -2, 0, 0},
      {0, 0, 1, 2, 4, 0}
    };

    assertEquals(28, twoDArrayDS.hourglassSum(hourglass));
  }

  @Test
  @DisplayName("All values negative: proves the running max starts at Integer.MIN_VALUE, not 0")
  public void testCase4() {
    // If the running maximum had instead started at 0, this all-negative grid would incorrectly
    // report 0 as the answer since no hourglass sum ever exceeds it.
    int[][] hourglass = {
      {0, -4, -6, 0, -7, -6},
      {-1, -2, -6, -8, -3, -1},
      {-8, -4, -2, -8, -8, -6},
      {-3, -1, -2, -5, -7, -4},
      {-3, -5, -3, -6, -6, -6},
      {-3, -6, 0, -8, -6, -7}
    };

    assertEquals(-19, twoDArrayDS.hourglassSum(hourglass));
  }

  @Test
  @DisplayName("All-zero grid: every hourglass sums to zero")
  public void testCase5() {
    int[][] hourglass = new int[6][6];

    assertEquals(0, twoDArrayDS.hourglassSum(hourglass));
  }
}
