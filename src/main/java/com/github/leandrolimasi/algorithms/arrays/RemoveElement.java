package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an integer array {@code nums} and an integer {@code val}, remove every occurrence of
 * {@code val} from {@code nums} in place. The order of the remaining elements may change. Let
 * {@code k} be the number of elements not equal to {@code val}; the first {@code k} slots of
 * {@code nums} must hold exactly those elements (in any order), and the rest of the array is
 * ignored. Return {@code k}.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> the only thing that matters is which elements survive and that they end
 * up compacted into the front of the array; their relative order is explicitly not required, which
 * removes any need for careful shifting or gap-closing.
 *
 * <p><b>Match:</b> Arrays -&gt; <b>two-pointer in-place filtering</b> (slow write / fast read),
 * the same family as the partition step of quicksort or in-place duplicate removal: scan once with
 * a read pointer, and only advance a separate write pointer when the current element should be
 * kept.
 *
 * <p><b>Plan:</b> keep a write pointer {@code k}, starting at 0. Walk every element with a read
 * pointer; whenever the element is not {@code val}, copy it into {@code nums[k]} and increment
 * {@code k}. Elements equal to {@code val} are simply skipped - never copied anywhere - which is
 * exactly what "removing" them means once order doesn't matter. After the scan, {@code k} is both
 * the count of surviving elements and the index one past the last one written.
 *
 * <p><b>Implement:</b> see {@link #removeElement(int[], int)} below.
 *
 * <p><b>Review:</b> exercised by {@code RemoveElementTest}, covering both given examples (checked
 * the same way the problem's custom judge does: sort the returned prefix and compare), an array
 * where every element equals {@code val}, one where none do, and an empty array.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over {@code nums}. Space O(1) extra; the
 * filtering happens entirely within {@code nums}'s existing storage.
 */
public class RemoveElement {

  public int removeElement(int[] nums, int val) {
    int k = 0;

    for (int num : nums) {
      if (num != val) {
        nums[k] = num;
        k++;
      }
    }

    return k;
  }
}
