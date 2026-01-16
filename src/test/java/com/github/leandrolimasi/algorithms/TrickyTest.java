package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrickyTest {

  @Test
  public void testCaseStringPoolAndObjects() {
    String a = "test";
    String b = "test";
    String c = new String("test");
    String d = new String("test");
    assertTrue(a == b);
    assertTrue(a.equals(b));
    assertFalse(c == a);
    assertTrue(c.equals(a));
    assertFalse(c == d);
    assertTrue(c.equals(d));
  }

  @Test
  public void testCaseUnboxingNull() {
    Integer a = null;
    assertThrows(NullPointerException.class, () -> {
      int b = a;
    });
  }
}
