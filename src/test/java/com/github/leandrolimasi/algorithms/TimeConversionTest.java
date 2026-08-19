package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>String parsing with special-cased boundary values</b>.
 *
 * <p>{@link TimeConversion#convert(String)} splits the input on {@code :} and only needs to
 * special-case the two hours where 12-hour and 24-hour clocks disagree in a non-additive way:
 * 12 AM (midnight, which becomes hour 0) and 12 PM (noon, which stays hour 12 instead of becoming
 * 24). Every other PM hour is a simple {@code +12}, and every other AM hour is unchanged.
 *
 * <p>Learning point: date/time conversions are a classic source of off-by-one and boundary bugs.
 * When a mapping is "mostly linear", explicitly test the handful of values where it isn't (here:
 * exactly 12) rather than only testing the common case.
 */
public class TimeConversionTest {

  private TimeConversion timeConversion = new TimeConversion();

  @Test
  @DisplayName("Ordinary PM time: add 12 to the hour")
  public void testCase1() {
    String timeConverted = timeConversion.convert("07:05:45PM");
    assertEquals(timeConverted, "19:05:45");
  }

  @Test
  @DisplayName("Ordinary AM time: hour is unchanged")
  public void testCase2() {
    String timeConverted = timeConversion.convert("04:59:59AM");
    assertEquals(timeConverted, "04:59:59");
  }

  @Test
  @DisplayName("Midnight boundary: 12:00:00AM becomes hour 00, not 12")
  public void testCase3() {
    String timeConverted = timeConversion.convert("12:00:00AM");
    assertEquals(timeConverted, "00:00:00");
  }

  @Test
  @DisplayName("Noon boundary: 12:00:00PM stays hour 12, it is not 12+12=24")
  public void testCase4() {
    String timeConverted = timeConversion.convert("12:00:00PM");
    assertEquals(timeConverted, "12:00:00");
  }
}
