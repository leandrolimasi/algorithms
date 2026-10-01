package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pattern: <b>Greedy farthest-reachable-index tracking</b>.
 *
 * <p>{@link JumpGame#canJump(int[])} never needs to reconstruct an actual sequence of jumps - it
 * only tracks the single farthest index reachable so far. If the scan ever reaches an index
 * beyond that frontier, no earlier jump could have landed there, so the end is unreachable;
 * otherwise the frontier only ever grows, and reaching the last index becomes a simple comparison
 * at the end of the scan.
 *
 * <p>Learning point: when a problem asks "can some sequence of local choices reach a target" and
 * the choices only ever extend reachability (never retract it), check whether tracking a single
 * running frontier replaces the need to explore every possible path.
 */
public class JumpGameTest {

  private JumpGame jumpGame = new JumpGame();

  @Test
  @DisplayName("Given example 1: a jump early on reaches far enough to clear the rest")
  public void testCase1() {
    assertTrue(jumpGame.canJump(new int[] {2, 3, 1, 1, 4}));
  }

  @Test
  @DisplayName("Given example 2: every path is funneled through a zero with no way past it")
  public void testCase2() {
    assertFalse(jumpGame.canJump(new int[] {3, 2, 1, 0, 4}));
  }

  @Test
  @DisplayName("Single-element array: already standing on the last index")
  public void testCase3() {
    assertTrue(jumpGame.canJump(new int[] {0}));
  }

  @Test
  @DisplayName("A zero strands every index that comes after it")
  public void testCase4() {
    assertFalse(jumpGame.canJump(new int[] {1, 0, 0, 0}));
  }

  @Test
  @DisplayName("A single large first jump clears the entire rest of the array at once")
  public void testCase5() {
    assertTrue(jumpGame.canJump(new int[] {5, 0, 0, 0, 0}));
  }
}
