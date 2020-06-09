package com.github.leandrolimasi.algorithms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CountingValleysTest {

  private CountingValleys countingValleys = new CountingValleys();

  @Test
  public void testCase1() {
    Assert.assertEquals(1, countingValleys.countingValleys(8, "UDDDUDUU"));
  }

  @Test
  public void testCase2() {
    Assert.assertEquals(2, countingValleys.countingValleys(12, "DDUUDDUDUUUD"));
  }

  @Test
  public void testCase3() {
    Assert.assertEquals(0, countingValleys.countingValleys(10, "UDUUUDUDDD"));
  }
}
