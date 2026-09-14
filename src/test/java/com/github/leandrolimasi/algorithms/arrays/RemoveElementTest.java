package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Two-pointer in-place filtering (slow write / fast read)</b>.
 *
 * <p>{@link RemoveElement#removeElement(int[], int)} scans {@code nums} once with an implicit
 * read pointer and only advances a separate write pointer {@code k} when the current element
 * should survive, overwriting {@code nums[k]} with it. Because the problem doesn't require the
 * surviving elements to keep their original relative order, there's no need to shift the array or
 * close gaps &mdash; skipping a matching element is enough to "remove" it.
 *
 * <p>Learning point: whenever a problem says the order of survivors doesn't matter, that's a
 * strong signal that a single-pass overwrite (rather than shifting or a second pass to compact
 * gaps) is the simplest correct approach.
 */
public class RemoveElementTest {

  private RemoveElement removeElement = new RemoveElement();

  /** Mirrors the problem's own custom judge: sort just the first k elements, then compare. */
  private static int[] sortedPrefix(int[] nums, int k) {
    int[] prefix = Arrays.copyOfRange(nums, 0, k);
    Arrays.sort(prefix);
    return prefix;
  }

  @Test
  @DisplayName("Given example 1: every occurrence of val is removed")
  public void testCase1() {
    int[] nums = {3, 2, 2, 3};

    int k = removeElement.removeElement(nums, 3);

    assertEquals(2, k);
    assertArrayEquals(new int[] {2, 2}, sortedPrefix(nums, k));
  }

  @Test
  @DisplayName("Given example 2: val appears multiple times, interleaved with survivors")
  public void testCase2() {
    int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};

    int k = removeElement.removeElement(nums, 2);

    assertEquals(5, k);
    assertArrayEquals(new int[] {0, 0, 1, 3, 4}, sortedPrefix(nums, k));
  }

  @Test
  @DisplayName("Every element equals val: nothing survives")
  public void testCase3() {
    int[] nums = {2, 2, 2};

    int k = removeElement.removeElement(nums, 2);

    assertEquals(0, k);
  }

  @Test
  @DisplayName("No element equals val: every element survives, untouched")
  public void testCase4() {
    int[] nums = {1, 2, 3};

    int k = removeElement.removeElement(nums, 4);

    assertEquals(3, k);
    assertArrayEquals(new int[] {1, 2, 3}, sortedPrefix(nums, k));
  }

  @Test
  @DisplayName("Empty array: no elements to remove, k is 0")
  public void testCase5() {
    int[] nums = {};

    int k = removeElement.removeElement(nums, 1);

    assertEquals(0, k);
  }
}
