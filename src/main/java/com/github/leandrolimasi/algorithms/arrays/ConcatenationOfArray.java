package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an integer array {@code nums} of length {@code n}, create an array {@code ans} of length
 * {@code 2n} where {@code ans[i] == nums[i]} and {@code ans[i + n] == nums[i]} for {@code 0 <= i
 * < n} (0-indexed). In other words, {@code ans} is the concatenation of two copies of {@code
 * nums}. Return {@code ans}.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> the problem statement already describes the result precisely - {@code
 * ans} is just {@code nums} written out twice, back to back. There's no search or optimization
 * decision to make here; the only question is how to build it in one clean pass.
 *
 * <p><b>Match:</b> Arrays -&gt; <b>direct single-pass construction</b>. Since every output slot's
 * source is already known ({@code ans[i]} and {@code ans[i + n]} both always come from {@code
 * nums[i]}), both halves of the result can be filled from the very same loop over {@code nums},
 * with no separate "second copy" pass needed.
 *
 * <p><b>Plan:</b> allocate {@code ans} with length {@code 2n}. Walk {@code i} from 0 to {@code n
 * - 1}, writing {@code nums[i]} into both {@code ans[i]} (the first copy) and {@code ans[i + n]}
 * (the second copy) during that same iteration.
 *
 * <p><b>Implement:</b> see {@link #getConcatenation(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code ConcatenationOfArrayTest}, covering both given examples,
 * a single-element array, an array of all identical values, and a larger array to confirm the
 * pattern generalizes.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; one pass over {@code nums}, writing two output slots per
 * element. Space O(n) extra for the returned {@code ans} array (unavoidable, since the result
 * must be a new array of length {@code 2n}).
 */
public class ConcatenationOfArray {

  public int[] getConcatenation(int[] nums) {
    int n = nums.length;
    int[] ans = new int[2 * n];

    for (int i = 0; i < n; i++) {
      ans[i] = nums[i];
      ans[i + n] = nums[i];
    }

    return ans;
  }
}
