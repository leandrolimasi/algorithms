package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Cycle exploitation via modular arithmetic</b>.
 *
 * <p>Instead of materializing the (potentially astronomically long, up to 10^12 characters)
 * repeated string, {@link RepeatedString#repeatedString(String, long)} notices that the infinite
 * string is just {@code s} repeated over and over. It counts the 'a's in one copy of {@code s},
 * multiplies that by the number of <i>full</i> copies that fit in {@code n} ({@code n / s.length()}),
 * and then separately counts the 'a's in the leftover partial copy ({@code n % s.length()} characters).
 *
 * <p>Learning point: whenever a problem describes an infinitely (or very largely) repeating unit,
 * look for a way to solve it for one period and scale with multiplication/division/modulo instead
 * of a loop over the full length &mdash; this turns an O(n) or worse solution into O(period length).
 */
public class RepeatedStringTest {

  private RepeatedString repeatedString = new RepeatedString();

  @Test
  @DisplayName("n spans multiple full copies plus a partial trailing copy")
  public void testCase1() {
    // "aba" repeated gives "abaabaabaa" (10 chars): 7 a's.
    assertEquals(7, repeatedString.repeatedString("aba", 10));
  }

  @Test
  @DisplayName("Single-character string repeated a huge number of times (tests the O(1) scaling, not looping n times)")
  public void testCase2() {
    assertEquals(1000000000000L, repeatedString.repeatedString("a", 1000000000000L));
  }

  @Test
  @DisplayName("n is smaller than one copy of s: only the partial-copy loop runs, div is 0")
  public void testCase3() {
    // First 3 characters of "abcac" are "abc": exactly one 'a'.
    assertEquals(1, repeatedString.repeatedString("abcac", 3));
  }

  @Test
  @DisplayName("s has no 'a' at all: the count stays zero regardless of n")
  public void testCase4() {
    assertEquals(0, repeatedString.repeatedString("bcd", 100));
  }

  @Test
  @DisplayName("s is entirely 'a': the result simply equals n")
  public void testCase5() {
    assertEquals(7, repeatedString.repeatedString("aaa", 7));
  }
}
