package com.github.leandrolimasi.algorithms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CloudGame.class})
public class CloudGameTest {

  @Autowired private CloudGame cloudGame;

  @Test
  public void testCase1() {
    Assert.assertEquals(cloudGame.jumpingOnClouds(new int[] {0, 0, 1, 0, 0, 1, 0}), 4);
  }

  @Test
  public void testCase2() {
    Assert.assertEquals(cloudGame.jumpingOnClouds(new int[] {0, 0, 0, 1, 0, 0}), 3);
  }
}
