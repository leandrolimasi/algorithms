package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an array {@code prices} where {@code prices[i]} is the price of a stock on day {@code i},
 * choose a single day to buy and a later day to sell to maximize profit. Return the maximum
 * profit achievable, or 0 if no transaction can be profitable.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> only one buy and one sell are allowed, the sell must happen strictly
 * after the buy, and a loss is never forced - if every later price is lower than every earlier
 * one, the answer is 0 rather than a negative number.
 *
 * <p><b>Match:</b> checking every buy/sell pair is O(n^2) -&gt; <b>single-pass greedy, tracking
 * the best-so-far</b>. For a fixed sell day, the most profitable buy day is always whichever
 * earlier day had the lowest price - so there's no need to remember every past price, only the
 * minimum one seen up to (and not including) today.
 *
 * <p><b>Plan:</b> walk the prices once, keeping {@code minPriceSoFar} (the lowest price seen
 * among all days strictly before the current one) and {@code maxProfit} (the best profit found
 * so far). For each day's price: if it's a new low, it becomes the new {@code minPriceSoFar} -
 * a lower buy price can never make today a worse sell day than before, so there's nothing to
 * compare yet. Otherwise, treat today as a candidate sell day against {@code minPriceSoFar} and
 * keep {@code maxProfit} if that's better than what's already been found.
 *
 * <p><b>Implement:</b> see {@link #maxProfit(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code BestTimeToBuyAndSellStockTest}, covering both given
 * examples, a single-day array (no valid transaction exists at all), strictly increasing prices,
 * and prices that dip to a new low <i>after</i> an already-profitable peak, verifying that later
 * low doesn't erase the better profit already found.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over {@code prices}. Space O(1) extra.
 */
public class BestTimeToBuyAndSellStock {

  public int maxProfit(int[] prices) {
    int minPriceSoFar = Integer.MAX_VALUE;
    int maxProfit = 0;

    for (int price : prices) {
      if (price < minPriceSoFar) {
        minPriceSoFar = price;
      } else if (price - minPriceSoFar > maxProfit) {
        maxProfit = price - minPriceSoFar;
      }
    }

    return maxProfit;
  }
}
