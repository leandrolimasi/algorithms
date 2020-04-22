package com.github.leandrolimasi.algorithms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
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

  @Test(expected = NullPointerException.class)
  public void testCaseUnboxingNull() {
    Integer a = null;
    int b = a;
  }
}
