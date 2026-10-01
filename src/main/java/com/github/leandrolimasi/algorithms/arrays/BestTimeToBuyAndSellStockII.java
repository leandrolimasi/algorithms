package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an integer array {@code prices} where {@code prices[i]} is the price of a stock on day
 * {@code i}, you may buy and/or sell on any day, but may never hold more than one share at a
 * time (buying and selling on the same day is allowed). Unlike the single-transaction version of
 * this problem, there is no limit on the number of transactions. Return the maximum total profit
 * achievable.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> with no cap on transactions and no cost or cooldown between them, every
 * profitable price movement can be captured independently - there's no benefit to "saving up"
 * capital across multiple days' gains, since buying and selling same-day is free.
 *
 * <p><b>Match:</b> Greedy -&gt; <b>sum every single-day gain</b>. Any multi-day rising streak
 * (say prices 1, 4, 7 over three days) can be bought low and sold high as one transaction for a
 * profit of 6, or split into one-day legs (1-&gt;4 for 3, then 4-&gt;7 for 3) for the exact same
 * total of 6 - the day-by-day gains always telescope to the same sum as the whole streak. So the
 * overall maximum is just the sum of every day-over-day increase, with decreasing days
 * contributing nothing (never a forced loss).
 *
 * <p><b>Plan:</b> walk the prices once; whenever a day's price is higher than the previous day's,
 * add that difference to a running total. Falling or flat days are simply skipped.
 *
 * <p><b>Implement:</b> see {@link #maxProfit(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code BestTimeToBuyAndSellStockIITest}, covering both given
 * examples, strictly decreasing prices (no profitable day exists), a single-day array, and a
 * zigzag with multiple separate rising streaks to confirm each is captured independently.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over {@code prices}. Space O(1) extra.
 */
public class BestTimeToBuyAndSellStockII {

  public int maxProfit(int[] prices) {
    int maxProfit = 0;

    for (int i = 1; i < prices.length; i++) {
      if (prices[i] > prices[i - 1]) {
        maxProfit += prices[i] - prices[i - 1];
      }
    }

    return maxProfit;
  }
}
