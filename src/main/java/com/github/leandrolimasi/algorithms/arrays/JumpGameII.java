package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given a 0-indexed array of integers {@code nums} of length {@code n}, where {@code nums[i]} is
 * the maximum forward jump length from index {@code i}, starting at index 0, return the minimum
 * number of jumps needed to reach index {@code n - 1}. The input is guaranteed to make that index
 * reachable.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> this builds on {@code JumpGame}'s reachability question by asking "in how
 * few jumps," not just "is it possible." Every index reachable within a given number of jumps
 * forms a widening "frontier"; the answer is the number of frontiers needed before that frontier
 * includes the last index.
 *
 * <p><b>Match:</b> trying every jump-length combination is exponential -&gt; <b>greedy, level-by-
 * level frontier expansion</b> (implicitly a breadth-first search, without ever building a graph
 * or queue). Within "jump count so far = k," there's a whole range of indices reachable - the
 * <i>boundary</i> of that range is what matters, not which specific index within it was actually
 * used, because every index in the current range is equally one jump away from expanding the
 * frontier further.
 *
 * <p><b>Plan:</b> track three values: {@code jumps} (the answer so far), {@code currentEnd} (the
 * farthest index reachable using {@code jumps} jumps - the current frontier's boundary), and
 * {@code farthest} (the farthest index reachable using one <i>additional</i> jump from anywhere
 * at or before {@code currentEnd}). Walk {@code i} from 0 up to (but not including) the last
 * index - once the last index itself is in view there's nothing left to decide. At every {@code
 * i}, extend {@code farthest} to {@code max(farthest, i + nums[i])}, exactly as in the
 * reachability-only version. Whenever {@code i} catches up to {@code currentEnd}, every index in
 * the current frontier has now been scanned, so reaching anything beyond it requires spending one
 * more jump: increment {@code jumps} and advance {@code currentEnd} to {@code farthest}.
 *
 * <p><b>Implement:</b> see {@link #jump(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code JumpGameIITest}, covering both given examples, a
 * single-element array (already at the last index, zero jumps needed), a two-element array
 * (exactly one jump), and a longer array that requires several successive frontier expansions to
 * confirm the jump counter advances correctly across more than two levels.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over {@code nums}. Space O(1) extra.
 */
public class JumpGameII {

  public int jump(int[] nums) {
    int jumps = 0;
    int currentEnd = 0;
    int farthest = 0;

    for (int i = 0; i < nums.length - 1; i++) {
      farthest = Math.max(farthest, i + nums[i]);

      if (i == currentEnd) {
        jumps++;
        currentEnd = farthest;
      }
    }

    return jumps;
  }
}
