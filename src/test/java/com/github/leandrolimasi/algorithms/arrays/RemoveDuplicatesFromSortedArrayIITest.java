package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Two-pointer in-place filtering with a fixed look-back window</b>.
 *
 * <p>{@link RemoveDuplicatesFromSortedArrayII#removeDuplicates(int[])} generalizes the
 * single-copy dedup trick by comparing each candidate element against {@code nums[k - 2]} instead
 * of {@code nums[k - 1]}: since the input is sorted, a value can only ever conflict with the copy
 * written two slots earlier, never with anything further back. That keeps the same O(n)/O(1)
 * single-pass shape while allowing up to two copies of each value to survive.
 *
 * <p>Learning point: "keep at most m copies" over sorted input generalizes cleanly to comparing
 * against {@code nums[k - m]} - the look-back distance is exactly the allowed copy count, not
 * something that needs a counter or a map to track per value.
 */
public class RemoveDuplicatesFromSortedArrayIITest {

  private RemoveDuplicatesFromSortedArrayII removeDuplicatesFromSortedArrayII =
      new RemoveDuplicatesFromSortedArrayII();

  @Test
  @DisplayName("Given example 1: a value appearing three times is trimmed down to two")
  public void testCase1() {
    int[] nums = {1, 1, 1, 2, 2, 3};

    int k = removeDuplicatesFromSortedArrayII.removeDuplicates(nums);

    assertEquals(5, k);
    assertArrayEquals(new int[] {1, 1, 2, 2, 3}, Arrays.copyOfRange(nums, 0, k));
  }

  @Test
  @DisplayName("Given example 2: multiple values with varying run lengths, some over the cap")
  public void testCase2() {
    int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};

    int k = removeDuplicatesFromSortedArrayII.removeDuplicates(nums);

    assertEquals(7, k);
    assertArrayEquals(new int[] {0, 0, 1, 1, 2, 3, 3}, Arrays.copyOfRange(nums, 0, k));
  }

  @Test
  @DisplayName("Empty array: nothing to keep")
  public void testCase3() {
    int[] nums = {};

    int k = removeDuplicatesFromSortedArrayII.removeDuplicates(nums);

    assertEquals(0, k);
  }

  @Test
  @DisplayName("No value repeats at all: the array is left exactly as is")
  public void testCase4() {
    int[] nums = {1, 2, 3};

    int k = removeDuplicatesFromSortedArrayII.removeDuplicates(nums);

    assertEquals(3, k);
    assertArrayEquals(new int[] {1, 2, 3}, Arrays.copyOfRange(nums, 0, k));
  }

  @Test
  @DisplayName("A single value repeats far more than twice: only two copies survive")
  public void testCase5() {
    int[] nums = {5, 5, 5, 5, 5};

    int k = removeDuplicatesFromSortedArrayII.removeDuplicates(nums);

    assertEquals(2, k);
    assertArrayEquals(new int[] {5, 5}, Arrays.copyOfRange(nums, 0, k));
  }
}
