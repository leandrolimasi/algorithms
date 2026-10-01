package com.github.leandrolimasi.algorithms.arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>Greedy sum of every single-day gain</b>.
 *
 * <p>{@link BestTimeToBuyAndSellStockII#maxProfit(int[])} never needs to decide where a
 * "transaction" begins or ends: because buying and selling on the same day is free, any rising
 * streak's profit is identical whether it's captured as one big transaction or as a chain of
 * one-day transactions. That means the optimal total is simply the sum of every positive
 * day-over-day price change.
 *
 * <p>Learning point: when a problem removes transaction-count limits and same-day buy/sell is
 * allowed, check whether the "optimal strategy" decomposes into independent local decisions
 * (here, each day's price change) rather than needing to track open positions or match up
 * specific buy/sell days.
 */
public class BestTimeToBuyAndSellStockIITest {

  private BestTimeToBuyAndSellStockII bestTimeToBuyAndSellStockII =
      new BestTimeToBuyAndSellStockII();

  @Test
  @DisplayName("Given example 1: two separate rising streaks, each captured as its own profit")
  public void testCase1() {
    assertEquals(7, bestTimeToBuyAndSellStockII.maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
  }

  @Test
  @DisplayName("Given example 2: one long rising streak, summed day by day")
  public void testCase2() {
    assertEquals(4, bestTimeToBuyAndSellStockII.maxProfit(new int[] {1, 2, 3, 4, 5}));
  }

  @Test
  @DisplayName("Strictly decreasing prices: no day-over-day gain exists anywhere")
  public void testCase3() {
    assertEquals(0, bestTimeToBuyAndSellStockII.maxProfit(new int[] {7, 6, 4, 3, 1}));
  }

  @Test
  @DisplayName("Single-day array: there's no next day to compare against")
  public void testCase4() {
    assertEquals(0, bestTimeToBuyAndSellStockII.maxProfit(new int[] {5}));
  }

  @Test
  @DisplayName("Zigzag prices: multiple separate rising streaks are each captured independently")
  public void testCase5() {
    // Rises: 1->7 (6) and 2->8 (6); the falls (7->2) contribute nothing.
    assertEquals(12, bestTimeToBuyAndSellStockII.maxProfit(new int[] {1, 7, 2, 8}));
  }
}
