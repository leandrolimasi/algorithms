package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedStringTest {

  private RepeatedString repeatedString = new RepeatedString();

  @Test
  public void testCase1() {
    assertEquals(7, repeatedString.repeatedString("aba", 10));
  }

  @Test
  public void testCase2() {
    assertEquals(1000000000000L, repeatedString.repeatedString("a", 1000000000000L));
  }
}
