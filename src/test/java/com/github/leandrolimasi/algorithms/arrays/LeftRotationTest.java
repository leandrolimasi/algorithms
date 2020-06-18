package com.github.leandrolimasi.algorithms.arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertArrayEquals;

@RunWith(SpringRunner.class)
public class LeftRotationTest {

  private LeftRotation leftRotation = new LeftRotation();

  @Test
  public void testCase1() {
    int[] hourglass = {1, 2, 3, 4, 5};

    assertArrayEquals(new int[] {5, 1, 2, 3, 4}, leftRotation.rotLeft(hourglass, 4));
  }

  @Test
  public void testCase2() {
    int[] hourglass = {41, 73, 89, 7, 10, 1, 59, 58, 84, 77, 77, 97, 58, 1, 86, 58, 26, 10, 86, 51};

    assertArrayEquals(
        new int[] {77, 97, 58, 1, 86, 58, 26, 10, 86, 51, 41, 73, 89, 7, 10, 1, 59, 58, 84, 77},
        leftRotation.rotLeft(hourglass, 10));
  }

  @Test
  public void testCase3() {
    int[] hourglass = {33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60, 87, 97};

    assertArrayEquals(
        new int[] {87, 97, 33, 47, 70, 37, 8, 53, 13, 93, 71, 72, 51, 100, 60},
        leftRotation.rotLeft(hourglass, 13));
  }
}
