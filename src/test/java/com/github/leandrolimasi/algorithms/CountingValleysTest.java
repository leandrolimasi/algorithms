package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Running state / level tracking (single linear scan)</b>.
 *
 * <p>{@link CountingValleys#countingValleys(int, String)} keeps a single running counter,
 * {@code level}, that goes up on 'U' and down on 'D'. A valley is only counted the instant the
 * hiker steps back up to sea level ({@code level == 0}) from below it. Because the current level
 * is all the state needed to know whether we're in a valley, the whole hike can be scored in one
 * O(n) pass without ever looking ahead or behind.
 *
 * <p>Learning point: this is the same "accumulator" shape used in many string/array streak
 * problems (balanced parentheses, running totals, high-water marks) &mdash; watch for a single
 * scalar that fully captures "where are we right now" and you can usually solve the problem in a
 * single pass.
 */
public class CountingValleysTest {

  private CountingValleys countingValleys = new CountingValleys();

  @Test
  @DisplayName("One valley: a single dip below sea level and back up")
  public void testCase1() {
    assertEquals(1, countingValleys.countingValleys(8, "UDDDUDUU"));
  }

  @Test
  @DisplayName("Two valleys with a mountain in between")
  public void testCase2() {
    assertEquals(2, countingValleys.countingValleys(12, "DDUUDDUDUUUD"));
  }

  @Test
  @DisplayName("Zero valleys: the hike stays at or above sea level throughout")
  public void testCase3() {
    assertEquals(0, countingValleys.countingValleys(10, "UDUUUDUDDD"));
  }

  @Test
  @DisplayName("Pure mountain: climbing only never counts as a valley")
  public void testCase4() {
    // level goes 1,2,3,4 and never returns to 0, so the level==0 && 'U' condition never fires.
    assertEquals(0, countingValleys.countingValleys(4, "UUUU"));
  }

  @Test
  @DisplayName("Minimal valley: the shortest possible dip below and back to sea level")
  public void testCase5() {
    assertEquals(1, countingValleys.countingValleys(2, "DU"));
  }

  @Test
  @DisplayName("Two consecutive separate valleys, each closed before the next opens")
  public void testCase6() {
    // "DUDU": step down into a valley, step up to close it, step down again, step up again.
    assertEquals(2, countingValleys.countingValleys(4, "DUDU"));
  }
}
