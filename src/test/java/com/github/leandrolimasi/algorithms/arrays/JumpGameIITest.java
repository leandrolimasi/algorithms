package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Greedy level-by-level frontier expansion (implicit BFS)</b>.
 *
 * <p>{@link JumpGameII#jump(int[])} never tracks a specific path of jumps - only the boundary of
 * everything reachable with the current jump count ({@code currentEnd}) and the boundary of
 * everything reachable with one more jump ({@code farthest}). Crossing {@code currentEnd} is the
 * signal that the current "level" has been fully explored and a new jump must be spent, the same
 * way BFS advances from one distance level to the next without needing to know which specific
 * node in the current level led to which node in the next.
 *
 * <p>Learning point: "minimum number of steps/jumps to reach a target" over a frontier that only
 * ever grows is a strong signal for level-by-level greedy expansion - track the current level's
 * boundary and the next level's boundary, rather than searching every individual path.
 */
public class JumpGameIITest {

  private JumpGameII jumpGameII = new JumpGameII();

  @Test
  @DisplayName("Given example 1: one big jump from index 0, then a direct jump to the end")
  public void testCase1() {
    assertEquals(2, jumpGameII.jump(new int[] {2, 3, 1, 1, 4}));
  }

  @Test
  @DisplayName("Given example 2: a zero in the middle doesn't matter since it's skipped over")
  public void testCase2() {
    assertEquals(2, jumpGameII.jump(new int[] {2, 3, 0, 1, 4}));
  }

  @Test
  @DisplayName("Single-element array: already standing on the last index, zero jumps needed")
  public void testCase3() {
    assertEquals(0, jumpGameII.jump(new int[] {0}));
  }

  @Test
  @DisplayName("Two-element array: exactly one jump reaches the last index")
  public void testCase4() {
    assertEquals(1, jumpGameII.jump(new int[] {1, 2}));
  }

  @Test
  @DisplayName("Every jump only advances one step: the frontier expands across several levels")
  public void testCase5() {
    assertEquals(4, jumpGameII.jump(new int[] {1, 1, 1, 1, 1}));
  }
}
