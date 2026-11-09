package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given the array {@code nums} consisting of {@code 2n} elements in the form {@code [x1, x2, ...,
 * xn, y1, y2, ..., yn]}, return the array in the interleaved form {@code [x1, y1, x2, y2, ...,
 * xn, yn]}.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> {@code nums} is really two separate halves placed back to back - the
 * {@code x} values occupy indices {@code 0} through {@code n - 1}, and the {@code y} values
 * occupy indices {@code n} through {@code 2n - 1}. The output just re-pairs each {@code x_i} with
 * its corresponding {@code y_i}; no searching or matching is needed to find that pairing, since
 * the index that identifies each pair is already fixed by the input's own layout.
 *
 * <p><b>Match:</b> Arrays -&gt; <b>direct single-pass construction</b>, the same family as {@code
 * ConcatenationOfArray}: because every output slot's source index is already known ({@code x_i}
 * always lives at {@code nums[i]}, {@code y_i} always at {@code nums[i + n]}), a single loop over
 * {@code i} can place both halves of each pair directly into their final destination.
 *
 * <p><b>Plan:</b> allocate a result array of length {@code 2n}. Walk {@code i} from 0 to {@code n
 * - 1}; for each {@code i}, write {@code nums[i]} (that pair's {@code x} value) to {@code
 * result[2 * i]} and {@code nums[i + n]} (that pair's {@code y} value) to {@code result[2 * i +
 * 1]} - the factor of 2 accounts for each pair consuming two slots in the interleaved output.
 *
 * <p><b>Implement:</b> see {@link #shuffle(int[], int)} below.
 *
 * <p><b>Review:</b> exercised by {@code ShuffleTheArrayTest}, covering all three given examples,
 * the smallest possible case ({@code n = 1}), and a larger array with distinct values to confirm
 * the index arithmetic generalizes.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; one pass over the first half of {@code nums}, writing two
 * output slots per iteration. Space O(n) extra for the returned result array.
 */
public class ShuffleTheArray {

  public int[] shuffle(int[] nums, int n) {
    int[] result = new int[2 * n];

    for (int i = 0; i < n; i++) {
      result[2 * i] = nums[i];
      result[2 * i + 1] = nums[i + n];
    }

    return result;
  }
}
