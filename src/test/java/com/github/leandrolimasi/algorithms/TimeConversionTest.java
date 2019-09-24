package com.github.leandrolimasi.algorithms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TimeConversion.class})
public class TimeConversionTest {

  @Autowired private TimeConversion timeConversion;

  @Test
  public void testCase1() {
    String timeConverted = timeConversion.convert("07:05:45PM");
    assertEquals(timeConverted, "19:05:45");
  }

  @Test
  public void testCase2() {
    String timeConverted = timeConversion.convert("04:59:59AM");
    assertEquals(timeConverted, "04:59:59");
  }

  @Test
  public void testCase3() {
    String timeConverted = timeConversion.convert("12:00:00AM");
    assertEquals(timeConverted, "00:00:00");
  }
}
