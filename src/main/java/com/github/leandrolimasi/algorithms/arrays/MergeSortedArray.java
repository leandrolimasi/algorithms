package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given two integer arrays {@code nums1} and {@code nums2}, sorted in non-decreasing order, and
 * two integers {@code m} and {@code n} representing the number of elements in each, merge {@code
 * nums2} into {@code nums1} in place so that {@code nums1} ends up sorted in non-decreasing order.
 * {@code nums1} has length {@code m + n}: its first {@code m} elements are the values to merge and
 * its last {@code n} elements are padding (0) to be overwritten; {@code nums2} has length {@code
 * n}.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> both inputs are already sorted, and the result must be written into
 * {@code nums1} itself rather than returned. {@code nums1}'s real data only occupies its first
 * {@code m} slots; the remaining {@code n} slots exist purely to make room for the merge.
 *
 * <p><b>Match:</b> merging two sorted sequences -&gt; <b>two-pointer merge</b>, the same idea
 * behind the merge step of merge sort. The twist is the in-place, single-array constraint: merging
 * from the <i>front</i> forward would overwrite {@code nums1} elements before they've been read,
 * since the write position always leads the two read positions. Merging from the <i>back</i>
 * instead is safe, because the write position then always lands on a slot that has already been
 * fully consumed (originally padding, or an element already copied out).
 *
 * <p><b>Plan:</b> keep three pointers, all starting at the last valid index of their range: {@code
 * i = m - 1} (last real element of {@code nums1}), {@code j = n - 1} (last element of {@code
 * nums2}), {@code writeIndex = m + n - 1} (last slot of {@code nums1}). Repeatedly compare {@code
 * nums1[i]} and {@code nums2[j]}, copy the larger one into {@code nums1[writeIndex]}, and
 * decrement that source pointer along with {@code writeIndex}. Once {@code nums2} is exhausted
 * ({@code j < 0}), every remaining element is already in its correct final position in {@code
 * nums1}, so the loop can simply stop.
 *
 * <p><b>Implement:</b> see {@link #merge(int[], int, int[], int)} below.
 *
 * <p><b>Review:</b> exercised by {@code MergeSortedArrayTest}, covering the two given examples, an
 * empty {@code nums1} being entirely replaced by {@code nums2}, {@code nums2} whose elements all
 * land before {@code nums1}'s, and a case with duplicate values across both arrays.
 *
 * <p><b>Evaluate:</b> Time O(m + n) &mdash; each element of both arrays is visited exactly once.
 * Space O(1) extra &mdash; the merge happens entirely within {@code nums1}'s existing storage.
 */
public class MergeSortedArray {

  public void merge(int[] nums1, int m, int[] nums2, int n) {
    int i = m - 1;
    int j = n - 1;
    int writeIndex = m + n - 1;

    while (j >= 0) {
      if (i >= 0 && nums1[i] > nums2[j]) {
        nums1[writeIndex--] = nums1[i--];
      } else {
        nums1[writeIndex--] = nums2[j--];
      }
    }
  }
}
