package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an integer array {@code nums} sorted in non-decreasing order, remove the duplicates in
 * place so that each unique element appears only once, keeping the relative order of the elements
 * the same. Let {@code k} be the number of unique elements; the first {@code k} slots of {@code
 * nums} must hold those unique numbers in sorted order, and everything beyond index {@code k - 1}
 * is ignored. Return {@code k}.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> unlike a generic duplicate-removal problem, {@code nums} is already
 * sorted, so every occurrence of a given value is guaranteed to sit in one contiguous run. That
 * means "is this value a duplicate?" only ever needs to be answered by looking at the single most
 * recently kept value, never a whole set of previously seen values.
 *
 * <p><b>Match:</b> Arrays -&gt; <b>two-pointer in-place filtering</b> (slow write / fast read),
 * the same family as {@code RemoveElement}. The difference from that problem is the "keep" rule:
 * instead of comparing each element against a fixed target value, compare it against whatever was
 * last written to the output, which sortedness guarantees is enough to detect every duplicate.
 *
 * <p><b>Plan:</b> if {@code nums} is empty there are no unique elements at all, so return 0
 * immediately. Otherwise the first element is always kept, so start the write pointer {@code k} at
 * 1. Walk a read pointer {@code i} from 1 to the end; whenever {@code nums[i]} differs from {@code
 * nums[k - 1]} (the last value actually written), it's a new unique value, so copy it to {@code
 * nums[k]} and increment {@code k}. Values equal to {@code nums[k - 1]} are duplicates and are
 * simply skipped.
 *
 * <p><b>Implement:</b> see {@link #removeDuplicates(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code RemoveDuplicatesFromSortedArrayTest}, covering both given
 * examples, an empty array, a single-element array, and an array made entirely of one repeated
 * value.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over {@code nums}. Space O(1) extra; the
 * filtering happens entirely within {@code nums}'s existing storage.
 */
public class RemoveDuplicatesFromSortedArray {

  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }

    int k = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != nums[k - 1]) {
        nums[k] = nums[i];
        k++;
      }
    }

    return k;
  }
}
