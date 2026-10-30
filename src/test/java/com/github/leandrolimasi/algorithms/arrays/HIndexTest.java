package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Counting sort via a bound-capped bucket array</b>.
 *
 * <p>{@link HIndex#hIndex(int[])} exploits the fact that the h-index can never exceed the total
 * number of papers {@code n}: every citation count, no matter how large, is clamped into one of
 * only {@code n + 1} buckets. Scanning those buckets from the top down and accumulating a running
 * "at least this many citations" count turns an O(n log n) sort-and-scan into an O(n) counting
 * pass.
 *
 * <p>Learning point: whenever a problem's answer is provably bounded by some small quantity
 * (here, the array's own length), that bound is a signal to try bucketing/counting instead of a
 * general comparison-based sort - values beyond the bound can all be safely merged into one
 * "overflow" bucket without losing any information the problem actually needs.
 */
public class HIndexTest {

  private HIndex hIndex = new HIndex();

  @Test
  @DisplayName("Given example 1: 3 papers with at least 3 citations each")
  public void testCase1() {
    assertEquals(3, hIndex.hIndex(new int[] {3, 0, 6, 1, 5}));
  }

  @Test
  @DisplayName("Given example 2: only 1 paper clears its own citation bar")
  public void testCase2() {
    assertEquals(1, hIndex.hIndex(new int[] {1, 3, 1}));
  }

  @Test
  @DisplayName("No citations at all: the h-index is 0")
  public void testCase3() {
    assertEquals(0, hIndex.hIndex(new int[] {0, 0, 0}));
  }

  @Test
  @DisplayName("A single highly-cited paper: h-index is capped by the paper count, not citations")
  public void testCase4() {
    assertEquals(1, hIndex.hIndex(new int[] {100}));
  }

  @Test
  @DisplayName("Every paper lands exactly at the n-citation cap: h-index equals n")
  public void testCase5() {
    assertEquals(5, hIndex.hIndex(new int[] {5, 5, 5, 5, 5}));
  }
}
