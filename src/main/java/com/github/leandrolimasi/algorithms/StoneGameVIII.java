package com.github.leandrolimasi.algorithms;

/**
 * Alice and Bob take turns, Alice first, playing on a row of {@code n} stones. While more than
 * one stone remains, the player to move chooses an integer {@code x > 1}, removes the leftmost
 * {@code x} stones, adds their sum to their own score, and places a single new stone equal to
 * that sum back on the left. The game ends when only one stone remains. Alice plays to maximize
 * the final score difference (Alice's score - Bob's score); Bob plays to minimize it. Return that
 * optimal difference.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> both players see the whole row and play optimally (perfect information,
 * zero-sum). Alice moves first and every move must consume more than one stone from the current
 * left end, so the very first move must take at least 2 of the original stones.
 *
 * <p><b>Match:</b> Optimization / two-player minimax -> <b>Dynamic Programming</b>. The naive view
 * (simulate every possible {@code x} recursively) is exponential; the DP fit comes from noticing
 * that a move's score gain only depends on <i>where it ends</i> in the original array, not on how
 * many earlier moves already merged stones together.
 *
 * <p>Concretely: let {@code prefixSum[i] = stones[0] + ... + stones[i]}. A move that ends at
 * original index {@code i} gains exactly {@code prefixSum[i]}, because whatever single merged
 * stone is currently on the left already equals the prefix sum up to the previous cut point, so
 * absorbing more original stones on top of it just extends that prefix sum further. This means
 * the entire game is equivalent to picking an increasing sequence of cut points into {@code
 * prefixSum}, alternating players, starting no earlier than index 1 (since the first move must
 * take &ge; 2 stones) and always ending at index {@code n - 1} (all stones consumed).
 *
 * <p><b>Plan:</b> compute {@code prefixSum}. Then, walking backward from {@code n - 1} to 1, track
 * {@code dp[i]} = the best score difference (mover's score minus opponent's) achievable from a
 * subgame whose earliest available cut point is {@code i}:
 *
 * <ul>
 *   <li>Base case: {@code dp[n - 1] = prefixSum[n - 1]} (only one position left, it's forced).
 *   <li>Recurrence: {@code dp[i] = max(prefixSum[i] - dp[i + 1], dp[i + 1])} &mdash; either cut
 *       here now (gain {@code prefixSum[i]}, then the opponent optimally plays the rest, so
 *       subtract their advantage {@code dp[i + 1]}), or skip this cut point entirely and defer to
 *       the subgame starting at {@code i + 1}.
 * </ul>
 *
 * <p>The answer is {@code dp[1]}.
 *
 * <p><b>Implement:</b> see {@link #stoneGameVIII(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code StoneGameVIIITest}, covering a mix of take/skip
 * decisions, a case where taking everything in one move is optimal, the minimal 2-stone game, an
 * all-positive row, and a counterintuitive all-negative row.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; one forward pass to build {@code prefixSum}, one backward
 * pass to compute the DP (n = stones.length). Space O(n) for the {@code prefixSum} array; this
 * could be reduced to O(1) extra space by computing each {@code prefixSum[i]} on the fly from the
 * total sum minus a running suffix sum instead of storing the whole array, at the cost of
 * readability.
 */
public class StoneGameVIII {

  public int stoneGameVIII(int[] stones) {
    int n = stones.length;

    // Step 1: prefixSum[i] = stones[0] + ... + stones[i]. This is the score a player would gain
    // by ending their move at original index i, no matter how many earlier moves already merged
    // stones[0..i] together.
    int[] prefixSum = new int[n];
    prefixSum[0] = stones[0];
    for (int i = 1; i < n; i++) {
      prefixSum[i] = prefixSum[i - 1] + stones[i];
    }

    // Step 2: base case of the backward DP. With only index n-1 left to cut, the move is forced,
    // so the best (and only) score difference from here is simply prefixSum[n-1].
    int dp = prefixSum[n - 1];

    // Step 3: walk backward, turning each index i into dp[i] before moving on to i-1. At each
    // step "dp" going in is dp[i+1] (the subgame that starts one index later); "dp" coming out
    // becomes dp[i].
    for (int i = n - 2; i >= 1; i--) {
      // Option A: cut here. Gain prefixSum[i] now, then the opponent plays the remaining subgame
      // optimally and nets dp (their advantage), so from this player's view that's a cost of -dp.
      int cutHere = prefixSum[i] - dp;

      // Option B: skip this cut point entirely - defer to the subgame that starts at i+1, whose
      // best difference is already known: dp.
      int skip = dp;

      // The mover picks whichever option is better for them.
      dp = Math.max(cutHere, skip);
    }

    // Step 4: after the loop, dp holds dp[1] - the answer, since the very first move must take at
    // least 2 stones, meaning index 1 is the earliest cut point the game can ever start from.
    return dp;
  }
}
