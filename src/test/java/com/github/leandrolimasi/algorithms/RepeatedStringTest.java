package com.github.leandrolimasi.algorithms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RepeatedStringTest {

  private RepeatedString repeatedString = new RepeatedString();

  @Test
  public void testCase1() {
    Assert.assertEquals(7, repeatedString.repeatedString("aba", 10));
  }

  @Test
  public void testCase2() {
    Assert.assertEquals(1000000000000L, repeatedString.repeatedString("a", 1000000000000L));
  }
}
