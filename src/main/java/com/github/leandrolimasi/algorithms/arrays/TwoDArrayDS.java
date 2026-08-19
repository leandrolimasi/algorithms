package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given a 6x6 2D Array, arr:
 *
 * <p>1 1 1 0 0 0 0 1 0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 *
 * <p>We define an hourglass in A to be a subset of values with indices falling in this pattern in
 * arr's graphical representation:
 *
 * <p>a b c d e f g
 *
 * <p>There are 16 hourglasses in arr, and an hourglass sum is the sum of an hourglass' values.
 * Calculate the hourglass sum for every hourglass in arr, then print the maximum hourglass sum.
 *
 * <p>For example, given the 2D array:
 *
 * <p>-9 -9 -9 1 1 1 0 -9 0 4 3 2 -9 -9 -9 1 2 3 0 0 8 6 6 0 0 0 0 -2 0 0 0 0 1 2 4 0
 *
 * <p>We calculate the following 16 hourglass values:
 *
 * <p>-63, -34, -9, 12, -10, 0, 28, 23, -27, -11, -2, 10, 9, 17, 25, 18
 *
 * <p>Our highest hourglass value is 28 from the hourglass:
 *
 * <p>0 4 3 1 8 6 6
 *
 * <p>Note: If you have already solved the Java domain's Java 2D Array challenge, you may wish to
 * skip this challenge.
 *
 * <p>Function Description
 *
 * <p>Complete the function hourglassSum in the editor below. It should return an integer, the
 * maximum hourglass sum in the array.
 *
 * <p>hourglassSum has the following parameter(s):
 *
 * <p>arr: an array of integers
 *
 * <p>Output Format
 *
 * <p>Print the largest (maximum) hourglass sum found in arr.
 *
 * <p>Sample Input
 *
 * <p>1 1 1 0 0 0 0 1 0 0 0 0 1 1 1 0 0 0 0 0 2 4 4 0 0 0 0 2 0 0 0 0 1 2 4 0
 *
 * <p>Sample Output
 *
 * <p>19 Explanation
 *
 * <p>arr contains the following hourglasses: The hourglass with the maximum sum () is:
 *
 * <p>2 4 4 2 1 2 4
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> given a fixed 6x6 grid, find the maximum sum among all 3x3 "hourglass"
 * shapes (top row of 3, middle cell, bottom row of 3) that fit inside it.
 *
 * <p><b>Match:</b> Arrays/matrix -> <b>fixed-shape sliding window</b>, exactly the "Arrays:
 * sliding window" pattern &mdash; the hourglass mask slides across every valid top-left anchor and
 * each position's sum is computed directly from its 7 fixed offsets.
 *
 * <p><b>Plan:</b> for every anchor {@code (i, j)} with {@code i, j} in {@code 0..3}, sum
 * {@code arr[i][j..j+2]}, {@code arr[i+1][j+1]}, and {@code arr[i+2][j..j+2]}; keep the maximum
 * seen, starting the running maximum at {@code Integer.MIN_VALUE} so an all-negative grid is still
 * handled correctly.
 *
 * <p><b>Implement:</b> see {@link #hourglassSum(int[][])} below.
 *
 * <p><b>Review:</b> exercised by {@code TwoDArrayDSTest}, covering mixed positive values,
 * mixed positive/negative values, an all-negative grid (proving the max doesn't default to 0), and
 * an all-zero grid.
 *
 * <p><b>Evaluate:</b> Time O(1) for this fixed 6x6 input (16 fixed anchor positions, 7 fixed
 * additions each); more generally, for an N x N grid this pattern is O(N^2), since the number of
 * anchors grows with the grid but each hourglass sum stays a constant 7 additions. Space O(1)
 * extra &mdash; only the running maximum is kept.
 */
public class TwoDArrayDS {

  // Complete the hourglassSum function below.
  int hourglassSum(int[][] arr) {

    int sum = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length - 2; i++) {
      for (int j = 0; j < arr.length - 2; j++) {
        int calc =
            arr[i][j]
                + arr[i][j + 1]
                + arr[i][j + 2]
                + arr[i + 1][j + 1]
                + arr[i + 2][j]
                + arr[i + 2][j + 1]
                + arr[i + 2][j + 2];
        if (calc > sum) {
          sum = calc;
        }
      }
    }

    return sum;
  }
}
