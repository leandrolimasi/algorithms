package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Two-pointer in-place filtering over a sorted array</b>.
 *
 * <p>{@link RemoveDuplicatesFromSortedArray#removeDuplicates(int[])} relies on {@code nums}
 * already being sorted: every duplicate of a value is adjacent to it, so a read pointer only ever
 * needs to compare the current element against the single most recently written one to know
 * whether it's a duplicate. That lets a slow write pointer compact the unique values into the
 * front of the array in one pass, with no set or map needed to remember "values seen so far".
 *
 * <p>Learning point: sortedness turns "have I seen this value before?" (normally requiring a hash
 * set) into "does this differ from the immediately previous value?", which is why in-place
 * dedup-on-sorted-input is O(n)/O(1) while general dedup needs O(n) extra space.
 */
public class RemoveDuplicatesFromSortedArrayTest {

  private RemoveDuplicatesFromSortedArray removeDuplicatesFromSortedArray =
      new RemoveDuplicatesFromSortedArray();

  @Test
  @DisplayName("Given example 1: a single value repeated once")
  public void testCase1() {
    int[] nums = {1, 1, 2};

    int k = removeDuplicatesFromSortedArray.removeDuplicates(nums);

    assertEquals(2, k);
    assertArrayEquals(new int[] {1, 2}, Arrays.copyOfRange(nums, 0, k));
  }

  @Test
  @DisplayName("Given example 2: multiple values with varying run lengths")
  public void testCase2() {
    int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};

    int k = removeDuplicatesFromSortedArray.removeDuplicates(nums);

    assertEquals(5, k);
    assertArrayEquals(new int[] {0, 1, 2, 3, 4}, Arrays.copyOfRange(nums, 0, k));
  }

  @Test
  @DisplayName("Empty array: there are no unique elements to keep")
  public void testCase3() {
    int[] nums = {};

    int k = removeDuplicatesFromSortedArray.removeDuplicates(nums);

    assertEquals(0, k);
  }

  @Test
  @DisplayName("Single-element array: already has no duplicates")
  public void testCase4() {
    int[] nums = {5};

    int k = removeDuplicatesFromSortedArray.removeDuplicates(nums);

    assertEquals(1, k);
    assertArrayEquals(new int[] {5}, Arrays.copyOfRange(nums, 0, k));
  }

  @Test
  @DisplayName("Every element is the same value: only one survives")
  public void testCase5() {
    int[] nums = {7, 7, 7, 7};

    int k = removeDuplicatesFromSortedArray.removeDuplicates(nums);

    assertEquals(1, k);
    assertArrayEquals(new int[] {7}, Arrays.copyOfRange(nums, 0, k));
  }
}
