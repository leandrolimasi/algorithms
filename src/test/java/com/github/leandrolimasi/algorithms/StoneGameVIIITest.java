package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Dynamic programming over a prefix-sum array (two-player minimax)</b>.
 *
 * <p>The key realization is that every "remove leftmost x stones, sum them, drop the merged
 * stone back" move is really just picking a cut point into the <i>original</i> array: whichever
 * player's move ends at original index {@code i} gains exactly {@code prefixSum[i]}, regardless
 * of how many earlier moves already merged part of that prefix into one stone. That collapses the
 * whole game into choosing an increasing sequence of cut points, alternating players, and {@link
 * StoneGameVIII#stoneGameVIII(int[])} solves it with a backward DP: at each position the mover
 * either takes the cut now (gaining {@code prefixSum[i]} and handing the rest of the game to the
 * opponent) or skips it and defers to the next position.
 *
 * <p>Learning point: when a problem lets a player "skip ahead" to any future position rather
 * than only the very next one, the DP transition usually needs an explicit "skip" branch ({@code
 * dp[i+1]}) alongside the "act now" branch, not just a step-by-step recurrence.
 */
public class StoneGameVIIITest {

  private StoneGameVIII stoneGameVIII = new StoneGameVIII();

  @Test
  @DisplayName("Mixed signs: the optimal play skips a bad cut point instead of taking it")
  public void testCase1() {
    assertEquals(5, stoneGameVIII.stoneGameVIII(new int[] {-1, 2, -3, 4, -5}));
   }

  @Test
  @DisplayName("Every later cut point is worse than the first: Alice takes all stones in one move")
  public void testCase2() {
    assertEquals(13, stoneGameVIII.stoneGameVIII(new int[] {7, -6, 5, 10, 5, -2, -6}));
  }

  @Test
  @DisplayName("Minimal 2-stone game: only a single forced move is possible")
  public void testCase3() {
    assertEquals(-22, stoneGameVIII.stoneGameVIII(new int[] {-10, -12}));
  }

  @Test
  @DisplayName("All-positive stones: grabbing everything immediately is always optimal")
  public void testCase4() {
    // prefixSum = [2,5,9]; taking everything on move 1 leaves Bob no turn at all.
    assertEquals(9, stoneGameVIII.stoneGameVIII(new int[] {2, 3, 4}));
  }

  @Test
  @DisplayName("All-negative stones: the DP can still land on a positive score difference")
  public void testCase5() {
    // Counterintuitive case worth studying: every stone is -1, yet forcing the opponent to be
    // the one who is stuck extending the (increasingly negative) prefix sum yields a +1 result.
    assertEquals(1, stoneGameVIII.stoneGameVIII(new int[] {-1, -1, -1, -1}));
  }
}
