package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an integer array {@code nums} sorted in non-decreasing order, remove some duplicates in
 * place so that each unique element appears **at most twice**, keeping the relative order of the
 * elements the same. Let {@code k} be the number of elements left after removal; the first {@code
 * k} slots of {@code nums} must hold the final result, and everything beyond index {@code k - 1}
 * is ignored. Return {@code k}. Must be done in place with O(1) extra memory.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> this is {@code RemoveDuplicatesFromSortedArray} with the "keep count"
 * relaxed from one copy of each value to two. Sortedness still guarantees every occurrence of a
 * value forms one contiguous run, so deciding whether to keep an element still never needs to look
 * further back than a fixed, small window of already-written output.
 *
 * <p><b>Match:</b> Arrays -&gt; <b>two-pointer in-place filtering</b> (slow write / fast read),
 * generalizing the "compare against the last written value" rule from the single-copy version:
 * instead of comparing against {@code nums[k - 1]}, compare against {@code nums[k - 2]} - the
 * value written <i>two</i> slots back. If the current element equals that one, keeping it would
 * make a third copy, so it's dropped; otherwise there's room for it.
 *
 * <p><b>Plan:</b> write pointer {@code k} starts at 0. Walk a read pointer {@code i} over every
 * element of {@code nums}; keep {@code nums[i]} (copy it to {@code nums[k]} and increment {@code
 * k}) whenever {@code k < 2} (the output doesn't even have two elements yet, so a third-copy
 * conflict is impossible) or {@code nums[i] != nums[k - 2]} (this value hasn't already appeared
 * twice in a row at the end of the output). Otherwise skip it.
 *
 * <p><b>Implement:</b> see {@link #removeDuplicates(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code RemoveDuplicatesFromSortedArrayIITest}, covering both
 * given examples, an empty array, an array with no duplicates at all (left untouched), and an
 * array where one value repeats far more than twice.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over {@code nums}. Space O(1) extra; the
 * filtering happens entirely within {@code nums}'s existing storage.
 */
public class RemoveDuplicatesFromSortedArrayII {

  public int removeDuplicates(int[] nums) {
    int k = 0;

    for (int i = 0; i < nums.length; i++) {
      if (k < 2 || nums[i] != nums[k - 2]) {
        nums[k] = nums[i];
        k++;
      }
    }

    return k;
  }
}
