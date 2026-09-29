package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Pattern: <b>Three-reversal in-place rotation</b>.
 *
 * <p>{@link RotateArray#rotate(int[], int)} achieves an in-place right rotation without any
 * extra array by reversing the whole array once, then reversing each of the two resulting
 * segments (the first {@code k} elements and the rest) individually. The whole-array reversal
 * gets every element the right relative distance from its final position; reversing each segment
 * afterward undoes the internal-order flip that step introduced, leaving exactly a rotation.
 *
 * <p>Learning point: whenever an in-place array transformation needs O(1) extra space and looks
 * like "two blocks need to swap places while each block keeps its own internal order," reversing
 * the whole thing and then reversing each block back is a reusable trick - the same idea used to
 * left-rotate (reverse the blocks in the opposite order) or to swap two unequal-length subarrays.
 */
public class RotateArrayTest {

  private RotateArray rotateArray = new RotateArray();

  @Test
  @DisplayName("Given example 1: k smaller than the array length")
  public void testCase1() {
    int[] nums = {1, 2, 3, 4, 5, 6, 7};

    rotateArray.rotate(nums, 3);

    assertArrayEquals(new int[] {5, 6, 7, 1, 2, 3, 4}, nums);
  }

  @Test
  @DisplayName("Given example 2: array containing negative values")
  public void testCase2() {
    int[] nums = {-1, -100, 3, 99};

    rotateArray.rotate(nums, 2);

    assertArrayEquals(new int[] {3, 99, -1, -100}, nums);
  }

  @Test
  @DisplayName("k = 0 is a no-op rotation: the array comes back unchanged")
  public void testCase3() {
    int[] nums = {1, 2, 3};

    rotateArray.rotate(nums, 0);

    assertArrayEquals(new int[] {1, 2, 3}, nums);
  }

  @Test
  @DisplayName("k greater than the array length wraps around via modulo, same as k % length")
  public void testCase4() {
    int[] nums = {1, 2, 3, 4, 5};

    // k=7 on a length-5 array behaves exactly like k=2 (7 % 5 == 2).
    rotateArray.rotate(nums, 7);

    assertArrayEquals(new int[] {4, 5, 1, 2, 3}, nums);
  }

  @Test
  @DisplayName("Single-element array: any rotation count is a no-op")
  public void testCase5() {
    int[] nums = {1};

    rotateArray.rotate(nums, 5);

    assertArrayEquals(new int[] {1}, nums);
  }
}
