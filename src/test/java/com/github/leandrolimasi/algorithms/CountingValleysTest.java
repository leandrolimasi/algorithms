package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountingValleysTest {

  private CountingValleys countingValleys = new CountingValleys();

  @Test
  public void testCase1() {
    assertEquals(1, countingValleys.countingValleys(8, "UDDDUDUU"));
  }

  @Test
  public void testCase2() {
    assertEquals(2, countingValleys.countingValleys(12, "DDUUDDUDUUUD"));
  }

  @Test
  public void testCase3() {
    assertEquals(0, countingValleys.countingValleys(10, "UDUUUDUDDD"));
  }
}
