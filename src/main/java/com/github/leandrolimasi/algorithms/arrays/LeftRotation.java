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
