package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Boyer-Moore majority vote</b>.
 *
 * <p>{@link MajorityElement#majorityElement(int[])} treats each non-matching element as
 * "cancelling out" one occurrence of the current candidate. Because the true majority element
 * appears more than half the time, it can never be fully cancelled by the rest of the array
 * combined, so whichever candidate survives to the end must be it - no frequency map required.
 *
 * <p>Learning point: whenever a problem guarantees an element makes up <i>strictly more than
 * half</i> of the input (not just "the most frequent"), that guarantee is a strong signal to reach
 * for Boyer-Moore voting instead of a frequency count, trading O(n) space for O(1).
 */
public class MajorityElementTest {

  private MajorityElement majorityElement = new MajorityElement();

  @Test
  @DisplayName("Given example 1: a length-3 array with a clear majority")
  public void testCase1() {
    assertEquals(3, majorityElement.majorityElement(new int[] {3, 2, 3}));
  }

  @Test
  @DisplayName("Given example 2: majority element requires the candidate to be discarded and reset")
  public void testCase2() {
    assertEquals(2, majorityElement.majorityElement(new int[] {2, 2, 1, 1, 1, 2, 2}));
  }

  @Test
  @DisplayName("Single-element array: that element is trivially the majority")
  public void testCase3() {
    assertEquals(5, majorityElement.majorityElement(new int[] {5}));
  }

  @Test
  @DisplayName("Negative-valued majority element")
  public void testCase4() {
    assertEquals(-1, majorityElement.majorityElement(new int[] {-1, -1, 2, -1}));
  }

  @Test
  @DisplayName("Majority element is not the first one seen, forcing a mid-scan candidate swap")
  public void testCase5() {
    assertEquals(3, majorityElement.majorityElement(new int[] {1, 3, 3, 3}));
  }
}
