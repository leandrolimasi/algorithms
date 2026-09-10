package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Counting by digit-length groups</b>.
 *
 * <p>Rather than formatting every integer from 1 to {@code n} and counting commas character by
 * character, {@link CountCommasInRangeII#countCommas(long)} notices that every number sharing the
 * same digit length {@code d} contributes the same fixed number of commas, {@code (d - 1) / 3}.
 * Summing that per-number contribution across full digit-length ranges - plus one partial range
 * for {@code n}'s own digit length - gives the total in time proportional to the number of digits
 * in {@code n}, not to {@code n} itself.
 *
 * <p>Learning point: when a per-item property only depends on which "bucket" (here, digit length)
 * an item falls into, count bucket sizes instead of iterating every item - this turns an O(n)
 * scan into an O(log n) closed-form sum.
 */
public class CountCommasInRangeIITest {

  private CountCommasInRangeII countCommasInRangeII = new CountCommasInRangeII();

  @Test
  @DisplayName("Fewer than four digits throughout: no commas are ever inserted")
  public void testCase1() {
    assertEquals(0, countCommasInRangeII.countCommas(1));
  }

  @Test
  @DisplayName("Just below the four-digit threshold: still no commas anywhere in the range")
  public void testCase2() {
    assertEquals(0, countCommasInRangeII.countCommas(999));
  }

  @Test
  @DisplayName("Exactly at the threshold: \"1,000\" is the first number to ever need a comma")
  public void testCase3() {
    assertEquals(1, countCommasInRangeII.countCommas(1000));
  }

  @Test
  @DisplayName("Given example: partial coverage of the four-digit group")
  public void testCase4() {
    assertEquals(3, countCommasInRangeII.countCommas(1002));
  }

  @Test
  @DisplayName("Full four, five, and six-digit ranges: one comma per number throughout")
  public void testCase5() {
    assertEquals(999000, countCommasInRangeII.countCommas(999999));
  }

  @Test
  @DisplayName("Spills into seven digits: some numbers now carry two commas instead of one")
  public void testCase6() {
    assertEquals(1468136, countCommasInRangeII.countCommas(1234567));
  }
}
