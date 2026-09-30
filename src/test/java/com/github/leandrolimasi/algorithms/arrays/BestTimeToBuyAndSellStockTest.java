package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Single-pass greedy tracking the best-so-far</b>.
 *
 * <p>{@link BestTimeToBuyAndSellStock#maxProfit(int[])} never needs to remember every price seen
 * so far, only the single lowest one - because for any given sell day, pairing it with the
 * cheapest earlier buy day is always at least as good as pairing it with any other. That collapses
 * an O(n^2) check of every buy/sell pair into one O(n) pass carrying two running values forward.
 *
 * <p>Learning point: when a problem asks for the best pairing between an earlier and a later
 * element under a single scan constraint (buy-before-sell, smaller-index-before-larger, etc.),
 * check whether only a single running extreme (min/max so far) needs to be carried forward,
 * rather than every value seen.
 */
public class BestTimeToBuyAndSellStockTest {

  private BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();

  @Test
  @DisplayName("Given example 1: buy at the lowest early price, sell at a later peak")
  public void testCase1() {
    assertEquals(5, bestTimeToBuyAndSellStock.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
  }

  @Test
  @DisplayName("Given example 2: strictly decreasing prices mean no profitable transaction")
  public void testCase2() {
    assertEquals(0, bestTimeToBuyAndSellStock.maxProfit(new int[] {7, 6, 4, 3, 1}));
  }

  @Test
  @DisplayName("Single-day array: there's no later day to sell on, so profit is 0")
  public void testCase3() {
    assertEquals(0, bestTimeToBuyAndSellStock.maxProfit(new int[] {5}));
  }

  @Test
  @DisplayName("Strictly increasing prices: buy on day one, sell on the last day")
  public void testCase4() {
    assertEquals(4, bestTimeToBuyAndSellStock.maxProfit(new int[] {1, 2, 3, 4, 5}));
  }

  @Test
  @DisplayName("A new low appears after an already-profitable peak, but doesn't erase that profit")
  public void testCase5() {
    // Best profit (4) comes from buying at 2 and selling at 6, both before the later dip to 0.
    assertEquals(4, bestTimeToBuyAndSellStock.maxProfit(new int[] {3, 2, 6, 5, 0, 3}));
  }
}
