package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Pattern: <b>Direct single-pass construction over a fixed-layout input</b>.
 *
 * <p>{@link ShuffleTheArray#shuffle(int[], int)} never needs to search for which {@code y}
 * matches a given {@code x} - the input's own layout (first half all {@code x}s, second half all
 * {@code y}s) already fixes that pairing by index. A single pass just relocates each pair into
 * its interleaved destination.
 *
 * <p>Learning point: when an input is split into fixed, same-length sections whose elements pair
 * up by position, the output can be built directly from index arithmetic in one pass, with no
 * need to search, sort, or hash anything to recover the pairing.
 */
public class ShuffleTheArrayTest {

  private ShuffleTheArray shuffleTheArray = new ShuffleTheArray();

  @Test
  @DisplayName("Given example 1: distinct x and y values")
  public void testCase1() {
    assertArrayEquals(
        new int[] {2, 3, 5, 4, 1, 7}, shuffleTheArray.shuffle(new int[] {2, 5, 1, 3, 4, 7}, 3));
  }

  @Test
  @DisplayName("Given example 2: the y half is the x half in reverse")
  public void testCase2() {
    assertArrayEquals(
        new int[] {1, 4, 2, 3, 3, 2, 4, 1},
        shuffleTheArray.shuffle(new int[] {1, 2, 3, 4, 4, 3, 2, 1}, 4));
  }

  @Test
  @DisplayName("Given example 3: repeated values across both halves")
  public void testCase3() {
    assertArrayEquals(new int[] {1, 2, 1, 2}, shuffleTheArray.shuffle(new int[] {1, 1, 2, 2}, 2));
  }

  @Test
  @DisplayName("Smallest possible case: n = 1 is just a single pair, already interleaved")
  public void testCase4() {
    assertArrayEquals(new int[] {1, 2}, shuffleTheArray.shuffle(new int[] {1, 2}, 1));
  }

  @Test
  @DisplayName("Larger array with all distinct values confirms the index arithmetic generalizes")
  public void testCase5() {
    assertArrayEquals(
        new int[] {10, 40, 20, 50, 30, 60},
        shuffleTheArray.shuffle(new int[] {10, 20, 30, 40, 50, 60}, 3));
  }
}
