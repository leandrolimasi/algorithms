package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Pattern: <b>Direct single-pass construction</b>.
 *
 * <p>{@link ConcatenationOfArray#getConcatenation(int[])} has no search or decision to make -
 * the output's shape is fully specified by the problem itself, so the only job is filling both
 * halves of the result array in one pass over the input rather than two.
 *
 * <p>Learning point: not every array problem hides a clever trick; when the transformation is
 * already fully determined by the problem statement, the right move is simply the most direct,
 * single-pass construction - resist the urge to look for a non-existent optimization.
 */
public class ConcatenationOfArrayTest {

  private ConcatenationOfArray concatenationOfArray = new ConcatenationOfArray();

  @Test
  @DisplayName("Given example 1: a short array with a repeated value")
  public void testCase1() {
    assertArrayEquals(
        new int[] {1, 2, 1, 1, 2, 1}, concatenationOfArray.getConcatenation(new int[] {1, 2, 1}));
  }

  @Test
  @DisplayName("Given example 2: all distinct values")
  public void testCase2() {
    assertArrayEquals(
        new int[] {1, 3, 2, 1, 1, 3, 2, 1},
        concatenationOfArray.getConcatenation(new int[] {1, 3, 2, 1}));
  }

  @Test
  @DisplayName("Single-element array: the result is just that value twice")
  public void testCase3() {
    assertArrayEquals(new int[] {5, 5}, concatenationOfArray.getConcatenation(new int[] {5}));
  }

  @Test
  @DisplayName("Every element is identical: the result is one long run of that value")
  public void testCase4() {
    assertArrayEquals(
        new int[] {7, 7, 7, 7, 7, 7}, concatenationOfArray.getConcatenation(new int[] {7, 7, 7}));
  }

  @Test
  @DisplayName("A larger array confirms the pattern generalizes beyond the small examples")
  public void testCase5() {
    assertArrayEquals(
        new int[] {4, 5, 6, 7, 8, 4, 5, 6, 7, 8},
        concatenationOfArray.getConcatenation(new int[] {4, 5, 6, 7, 8}));
  }
}
