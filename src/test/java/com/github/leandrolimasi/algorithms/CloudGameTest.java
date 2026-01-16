package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {CloudGame.class})
public class CloudGameTest {

  @Autowired private CloudGame cloudGame;

  @Test
  public void testCase1() {
    assertEquals(4, cloudGame.jumpingOnClouds(new int[] {0, 0, 1, 0, 0, 1, 0}));
  }

  @Test
  public void testCase2() {
    assertEquals(3, cloudGame.jumpingOnClouds(new int[] {0, 0, 0, 1, 0, 0}));
  }
}
