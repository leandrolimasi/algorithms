package com.github.leandrolimasi.algorithms.arrays;

/**
 * A left rotation operation on an array shifts each of the array's elements 1 unit to the left. For
 * example, if 2 left rotations are performed on array [1,2,3,4,5] , then the array would become
 * [3,4,5,1,2] .
 *
 * <p>Given an array a of n integers and a number, d, perform d left rotations on the array. Return
 * the updated array to be printed as a single line of space-separated integers.
 *
 * <p>Function Description
 *
 * <p>Complete the function rotLeft in the editor below. It should return the resulting array of
 * integers.
 *
 * <p>rotLeft has the following parameter(s):
 *
 * <p>An array of integers a. An integer d, the number of rotations. Input Format
 *
 * <p>The first line contains two space-separated integers n and d , the size of and the number of
 * left rotations you must perform. The second line contains n space-separated integers a[i].
 *
 * <p>Output Format
 *
 * <p>Print a single line of n space-separated integers denoting the final state of the array after
 * performing d left rotations.
 *
 * <p>Sample Input
 *
 * <p>5 4 - 1 2 3 4 5 Sample Output
 *
 * <p>5 1 2 3 4 Explanation
 *
 * <p>When we perform d=4 left rotations, the array undergoes the following sequence of changes
 * [1,2,3,4,5] -> [2,3,4,5,1] -> [3,4,5,1,2] -> [4,5,1,2,3] -> [5,1,2,3,4]:
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> shift every element of the array {@code d} positions to the left, with
 * elements that fall off the front wrapping around to the back.
 *
 * <p><b>Match:</b> Arrays -> <b>index/pointer arithmetic via modulo</b>, closely related to the
 * "pointers" family the problem statement calls out, but here it's a single-pass index remapping
 * rather than two indices moving toward/past each other: for every output position {@code i}, the
 * source index is {@code (i + d) % a.length}. Reducing {@code d} modulo the array length up front
 * also makes {@code d >= a.length} a non-issue, with no special case needed.
 *
 * <p><b>Plan:</b> {@code n = d % a.length}; build a new array where {@code result[i] = a[(i + n) %
 * a.length]} for every {@code i}.
 *
 * <p><b>Implement:</b> see {@link #rotLeft(int[], int)} below.
 *
 * <p><b>Review:</b> exercised by {@code LeftRotationTest}, covering a typical rotation, a larger
 * array with a mid-range rotation count, a rotation count close to the array length, {@code d=0},
 * {@code d} equal to the array length, and {@code d} greater than the array length.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; one pass filling the result array (n = a.length). Space
 * O(n) for the new result array. Note: an in-place "three reversals" trick (reverse the whole
 * array, then reverse each of the two rotated segments) achieves the same O(n) time with O(1)
 * extra space if returning a new array isn't required.
 */
public class LeftRotation {

  // Complete the rotLeft function below.
  int[] rotLeft(int[] a, int d) {
    int n = d % a.length;
    int[] ret = new int[a.length];
    for (int i = 0; i < a.length; ++i) {
      ret[i] = a[(i + n) % a.length];
    }
    return ret;
  }
}
