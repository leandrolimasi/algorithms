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
