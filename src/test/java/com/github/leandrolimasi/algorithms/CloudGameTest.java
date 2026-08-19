package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Greedy algorithm</b>.
 *
 * <p>At every cloud, {@link CloudGame#jumpingOnClouds(int[])} always takes the biggest jump that
 * is still safe (2 clouds ahead if it isn't a thunderhead, otherwise 1 cloud ahead). This works
 * because jumping farther is never worse than jumping shorter: a 2-step jump reaches the same or
 * a more advanced position in fewer total jumps, so there is never a reason to prefer the smaller
 * jump when the larger one is available. Making the locally-optimal choice at each step yields the
 * globally-optimal (minimum) number of jumps, which is the hallmark of a greedy algorithm.
 *
 * <p>Learning point: greedy only works when a local optimum is guaranteed to be part of a global
 * optimum. Here that's guaranteed by the problem's own rules (you may only ever move +1 or +2), so
 * there's no case where taking a shorter jump now enables a shorter path later.
 */
public class CloudGameTest {

  private CloudGame cloudGame = new CloudGame();

  @Test
  @DisplayName("Alternating single thunderheads force a mix of 1- and 2-step jumps")
  public void testCase1() {
    // c = 0 0 1 0 0 1 0 -> path 0->1->3->4->6 (jumps of 1,2,1,2)
    assertEquals(4, cloudGame.jumpingOnClouds(new int[] {0, 0, 1, 0, 0, 1, 0}));
  }

  @Test
  @DisplayName("A single thunderhead near the end still allows mostly 2-step jumps")
  public void testCase2() {
    // c = 0 0 0 1 0 0 -> path 0->2->4->5 (jumps of 2,2,1)
    assertEquals(3, cloudGame.jumpingOnClouds(new int[] {0, 0, 0, 1, 0, 0}));
  }

  @Test
  @DisplayName("No thunderheads at all: every jump can be a 2-step jump")
  public void testCase3() {
    // Best case for the greedy approach: c.length - 1 clouds covered two at a time.
    // c = 0 0 0 0 0 -> path 0->2->4 (jumps of 2,2)
    assertEquals(2, cloudGame.jumpingOnClouds(new int[] {0, 0, 0, 0, 0}));
  }

  @Test
  @DisplayName("Thunderheads on every odd index never block the 2-step greedy path")
  public void testCase4() {
    // c = 0 1 0 1 0 1 0 -> the greedy jump only ever lands on even indices (0,2,4,6), so the
    // thunderheads at the odd indices are never actually candidates and don't affect the result.
    // This illustrates that the algorithm doesn't need to reason about reachability globally: it
    // only ever has to check the single next candidate cloud.
    assertEquals(3, cloudGame.jumpingOnClouds(new int[] {0, 1, 0, 1, 0, 1, 0}));
  }

  @Test
  @DisplayName("Minimal game: only the starting and final cloud exist")
  public void testCase5() {
    // Smallest possible valid input: one mandatory jump from cloud 0 to cloud 1.
    assertEquals(1, cloudGame.jumpingOnClouds(new int[] {0, 0}));
  }
}
