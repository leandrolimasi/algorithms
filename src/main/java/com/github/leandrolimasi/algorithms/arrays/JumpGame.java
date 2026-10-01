package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an integer array {@code nums} where {@code nums[i]} is the maximum jump length from
 * index {@code i}, starting at index 0, return whether it's possible to reach the last index.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> a jump from index {@code i} can land anywhere from {@code i + 1} up to
 * {@code i + nums[i]} (or nowhere new, if {@code nums[i]} is 0). Reachability only ever grows as
 * more of the array is explored - visiting a position never makes an already-reachable later
 * position unreachable.
 *
 * <p><b>Match:</b> trying every combination of jumps is exponential -&gt; <b>greedy, tracking the
 * farthest reachable index</b>. Because any position reachable from an earlier index is also
 * reachable via that earlier index's own best jump, there's no need to explore every path - only
 * the single farthest index reached so far matters, not how it was reached.
 *
 * <p><b>Plan:</b> keep {@code farthest}, the farthest index reachable so far, starting at 0 (the
 * start is trivially "reachable"). Walk the array left to right: if the current index {@code i}
 * is already beyond {@code farthest}, nothing explored so far can even stand on {@code i}, so the
 * end is unreachable - return {@code false} immediately. Otherwise, extend {@code farthest} to
 * {@code max(farthest, i + nums[i])}. If the scan completes without ever bailing out, every index
 * up to and including the last one was reachable, so return {@code true}.
 *
 * <p><b>Implement:</b> see {@link #canJump(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code JumpGameTest}, covering both given examples, a
 * single-element array (trivially already at the last index), a zero that strands every later
 * index, and a single first jump large enough to clear the rest of the array outright.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over {@code nums}. Space O(1) extra.
 */
public class JumpGame {

  public boolean canJump(int[] nums) {
    int farthest = 0;

    for (int i = 0; i < nums.length; i++) {
      if (i > farthest) {
        return false;
      }
      farthest = Math.max(farthest, i + nums[i]);
    }

    return true;
  }
}
