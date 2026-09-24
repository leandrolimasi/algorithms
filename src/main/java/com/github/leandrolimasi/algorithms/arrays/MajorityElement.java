package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an array {@code nums} of size {@code n}, return the majority element: the element that
 * appears more than {@code floor(n / 2)} times. The input is guaranteed to always contain a
 * majority element.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> "majority" here means strictly more than half the array, which is a much
 * stronger guarantee than "most frequent" - it means the majority element alone outnumbers every
 * other element combined.
 *
 * <p><b>Match:</b> counting frequencies with a hash map is O(n) time but O(n) extra space ->
 * <b>Boyer-Moore majority vote</b>, which gets the same O(n) time down to O(1) space by exploiting
 * that "more than half" guarantee: if every occurrence of the majority element is paired off
 * against one occurrence of some other element, the majority element still has leftovers once
 * everything else is exhausted, because it outnumbers the rest combined.
 *
 * <p><b>Plan:</b> keep a running {@code candidate} and a {@code count} of its net "votes",
 * starting at 0. For each number: if {@code count} is 0, adopt it as the new {@code candidate}
 * (with {@code count = 1}); otherwise increment {@code count} if it matches {@code candidate}, or
 * decrement {@code count} if it doesn't (this is the "pairing off" cancellation). Because the true
 * majority element can never be fully cancelled out by the rest of the array combined, whatever
 * {@code candidate} holds at the end is guaranteed to be it.
 *
 * <p><b>Implement:</b> see {@link #majorityElement(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code MajorityElementTest}, covering both given examples, a
 * single-element array, a negative-valued majority element, and a case where the majority element
 * is not the first one seen, forcing the candidate to be discarded and replaced mid-scan.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; a single pass over {@code nums}. Space O(1) extra, versus
 * O(n) for a frequency-map approach.
 */
public class MajorityElement {

  public int majorityElement(int[] nums) {
    int candidate = nums[0];
    int count = 0;

    for (int num : nums) {
      if (count == 0) {
        candidate = num;
        count = 1;
      } else if (num == candidate) {
        count++;
      } else {
        count--;
      }
    }

    return candidate;
  }
}
