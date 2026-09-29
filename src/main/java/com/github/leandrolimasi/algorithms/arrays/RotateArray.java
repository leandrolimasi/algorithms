package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an integer array {@code nums}, rotate the array to the right by {@code k} steps, where
 * {@code k} is non-negative, modifying {@code nums} in place.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> a right rotation by {@code k} moves the last {@code k} elements to the
 * front (in their original relative order) and shifts everything else back by {@code k}. {@code k}
 * can exceed the array's length, in which case only {@code k mod n} actually changes anything - a
 * full lap of length {@code n} is a no-op.
 *
 * <p><b>Match:</b> rotating in place without O(n) extra space rules out building a brand-new
 * result array -&gt; <b>the three-reversal trick</b>. Reversing the whole array puts every element
 * exactly {@code n - 1} positions away from where it needs to end up relative to the others, but
 * flips the internal order of both the "should stay in place" and "should wrap around" segments;
 * reversing each of those two segments individually flips them back, leaving only the rotation
 * itself.
 *
 * <p><b>Plan:</b> reduce {@code k} to {@code k % n} up front, so a rotation count larger than (or
 * equal to) the array length needs no special case. Then: (1) reverse the entire array; (2)
 * reverse just its first {@code k} elements; (3) reverse the remaining {@code n - k} elements.
 * Concretely for {@code [1,2,3,4,5]} with {@code k=2}: reverse all -&gt; {@code [5,4,3,2,1]};
 * reverse the first 2 -&gt; {@code [4,5,3,2,1]}; reverse the last 3 -&gt; {@code [4,5,1,2,3]},
 * which is exactly a right rotation by 2.
 *
 * <p><b>Implement:</b> see {@link #rotate(int[], int)} below.
 *
 * <p><b>Review:</b> exercised by {@code RotateArrayTest}, covering both given examples, {@code
 * k=0} as a no-op, a rotation count larger than the array length wrapping via modulo, and a
 * single-element array.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; the whole-array reversal plus the two segment reversals
 * together still only touch each element a constant number of times. Space O(1) extra; the
 * rotation happens entirely within {@code nums}'s existing storage.
 */
public class RotateArray {

  public void rotate(int[] nums, int k) {
    int n = nums.length;
    k %= n;

    reverse(nums, 0, n - 1);
    reverse(nums, 0, k - 1);
    reverse(nums, k, n - 1);
  }

  private void reverse(int[] nums, int start, int end) {
    while (start < end) {
      int temp = nums[start];
      nums[start] = nums[end];
      nums[end] = temp;
      start++;
      end--;
    }
  }
}
