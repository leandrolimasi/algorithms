package com.github.leandrolimasi.algorithms;

/**
 * Emma is playing a new mobile game that starts with consecutively numbered clouds. Some of the
 * clouds are thunderheads and others are cumulus. She can jump on any cumulus cloud having a number
 * that is equal to the number of the current cloud plus 1 or 2. She must avoid the thunderheads.
 * Determine the minimum number of jumps it will take Emma to jump from her starting postion to the
 * last cloud. It is always possible to win the game.
 *
 * <p>For each game, Emma will get an array of clouds numbered 0 if they are safe or 1 if they must
 * be avoided. For example, c = [0,1,0,0,0,1,0] indexed from 0 .. 6 The number on each cloud is its
 * index in the list so she must avoid the clouds at indexes 1 and 5. She could follow the following
 * two paths: 0 -> 2 -> 4 -> 6 or 0 -> 2 -> 3 -> 4 -> 6 . The first path takes 3 jumps while the
 * second takes 4.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> starting at index 0, jump to index+1 or index+2 each move, never landing
 * on a 1 (thunderhead), and reach the last index in as few jumps as possible. The problem
 * guarantees a solution always exists.
 *
 * <p><b>Match:</b> Array traversal + optimization -> <b>greedy</b>. At every position only two
 * candidate moves exist (+1 or +2), and taking the longer one whenever it's safe never makes the
 * remaining path worse, so the locally best choice is always part of the globally best solution
 * &mdash; the textbook condition for greedy to be correct (as opposed to needing DP, which would
 * be required if a longer jump now could ever force a worse outcome later).
 *
 * <p><b>Plan:</b> walk an index i from 0; if i+2 is in bounds and not a thunderhead, jump 2,
 * otherwise jump 1; count every jump until i reaches the last index.
 *
 * <p><b>Implement:</b> see {@link #jumpingOnClouds(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code CloudGameTest}, covering alternating single thunderheads,
 * a trailing thunderhead, the all-safe best case, thunderheads placed where the greedy path never
 * reaches them, and the minimal 2-cloud game.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over the clouds, advancing the index by 1 or
 * 2 each iteration (n = c.length). Space O(1) &mdash; only the running index and jump counter are
 * kept, no auxiliary data structures.
 */
public class CloudGame {

  // Complete the jumpingOnClouds function below.
  public int jumpingOnClouds(int[] c) {
    int count = 0;
    int i = 0;
    while (i < c.length - 1) {
      if (i + 2 < c.length && c[i + 2] != 1) {
        i = i + 2;
      } else {
        i++;
      }
      count++;
    }
    return count;
  }
}
