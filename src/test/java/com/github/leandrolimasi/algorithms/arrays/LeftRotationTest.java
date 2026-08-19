package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Pattern: <b>Modulo-index remapping</b>.
 *
 * <p>Instead of physically shifting elements one position at a time {@code d} times (which would
 * be O(n*d)), {@link LeftRotation#rotLeft(int[], int)} computes, for every output position {@code
 * i}, which original index feeds it directly: {@code a[(i + d) % a.length]}. Reducing {@code d}
 * modulo the array length up front also means a rotation count larger than the array (or even
 * exactly equal to it) is handled for free, without a special case.
 *
 * <p>Learning point: whenever an operation is defined as "repeat a shift/rotation d times", check
 * whether the final positions can be computed directly with modulo arithmetic in a single pass
 * instead &mdash; this is the same trick used in circular buffers and clock-arithmetic problems.
 */
public class LeftRotationTest {

  private LeftRotation leftRotation = new LeftRotation();

  @Test
  @DisplayName("Typical rotation smaller than the array length")
  public void testCase1() {
    int[] hourglass = {1, 2, 3, 4, 5};

    assertArrayEquals(new int[] {5, 1, 2, 3, 4}, leftRotation.rotLeft(hourglass, 4));
  }

  @Test
  @DisplayName("Larger array with a mid-range rotation count")
  public void testCase2() {
    int[] hourglass = {41, 73, 89, 7, 10, 1, 59, 58, 84, 77, 77, 97, 58, 1, 86, 58, 26, 10, 86, 51};

    assertArrayEquals(
        new int[] {77, 97, 58, 1, 86, 58, 26, 10, 86, 51, 41, 73, 89, 7, 10, 1, 59, 58, 84, 77},
        leftRotation.rotLeft(hourglass, 10));
  }

  @Test
  @DisplayName("Rotation count close to the full array length")
  public void testCase3() {
    int[] hourglass = {33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60, 87, 97};

    assertArrayEquals(
        new int[] {87, 97, 33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60},
        leftRotation.rotLeft(hourglass, 13));
  }

  @Test
  @DisplayName("d = 0 is a no-op rotation: the array comes back unchanged")
  public void testCase4() {
    int[] array = {1, 2, 3, 4, 5};

    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, leftRotation.rotLeft(array, 0));
  }

  @Test
  @DisplayName("d equal to the array length is also a no-op, thanks to the modulo")
  public void testCase5() {
    int[] array = {1, 2, 3, 4, 5};

    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, leftRotation.rotLeft(array, 5));
  }

  @Test
  @DisplayName("d greater than the array length wraps around via modulo, same as d % length")
  public void testCase6() {
    int[] array = {1, 2, 3, 4, 5};

    // d=7 on a length-5 array behaves exactly like d=2 (7 % 5 == 2).
    assertArrayEquals(new int[] {3, 4, 5, 1, 2}, leftRotation.rotLeft(array, 7));
  }
}
