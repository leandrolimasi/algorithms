package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Pattern: <b>Two-pointer merge, back to front</b>.
 *
 * <p>{@link MergeSortedArray#merge(int[], int, int[], int)} merges two sorted arrays into one
 * without any extra storage by writing from the highest index down, instead of the more familiar
 * front-to-back merge used in merge sort. Writing from the back guarantees the write pointer never
 * overtakes a value that still needs to be read, since it only ever lands on slots that are either
 * padding or already-copied values.
 *
 * <p>Learning point: an in-place merge into a single array with a fixed size only works safely
 * back-to-front, not front-to-back &mdash; whenever "merge in place" and "the destination has
 * pre-allocated trailing space" show up together, that's the signal to reach for this trick.
 */
public class MergeSortedArrayTest {

  private MergeSortedArray mergeSortedArray = new MergeSortedArray();

  @Test
  @DisplayName("Given example 1: interleaved merge of two non-trivial arrays")
  public void testCase1() {
    int[] nums1 = {1, 2, 3, 0, 0, 0};

    mergeSortedArray.merge(nums1, 3, new int[] {2, 5, 6}, 3);

    assertArrayEquals(new int[] {1, 2, 2, 3, 5, 6}, nums1);
  }

  @Test
  @DisplayName("Given example 2: nums2 is empty, so nums1 is left untouched")
  public void testCase2() {
    int[] nums1 = {1};

    mergeSortedArray.merge(nums1, 1, new int[] {}, 0);

    assertArrayEquals(new int[] {1}, nums1);
  }

  @Test
  @DisplayName("nums1 starts empty (m = 0): nums2 entirely fills the result")
  public void testCase3() {
    int[] nums1 = {0};

    mergeSortedArray.merge(nums1, 0, new int[] {1}, 1);

    assertArrayEquals(new int[] {1}, nums1);
  }

  @Test
  @DisplayName("Every nums2 element is smaller than every nums1 element")
  public void testCase4() {
    int[] nums1 = {4, 5, 6, 0, 0, 0};

    mergeSortedArray.merge(nums1, 3, new int[] {1, 2, 3}, 3);

    assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6}, nums1);
  }

  @Test
  @DisplayName("Duplicate values across both arrays merge in stable, non-decreasing order")
  public void testCase5() {
    int[] nums1 = {2, 2, 0, 0};

    mergeSortedArray.merge(nums1, 2, new int[] {2, 2}, 2);

    assertArrayEquals(new int[] {2, 2, 2, 2}, nums1);
  }
}
