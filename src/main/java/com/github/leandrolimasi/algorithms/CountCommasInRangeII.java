package com.github.leandrolimasi.algorithms;

/**
 * Given an integer {@code n}, return the total number of commas used when writing every integer
 * from 1 to {@code n} (inclusive) in standard number formatting: a comma is inserted after every
 * three digits counted from the right, and numbers with fewer than four digits get no comma.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> {@code n} can be large (hence the {@code long} return type), so writing
 * out every number from 1 to {@code n} and literally counting commas is out of the question - the
 * answer has to come from a closed-form count over digit lengths.
 *
 * <p><b>Match:</b> Brute force / simulation is infeasible for large {@code n} -&gt;
 * <b>counting by digit-length groups</b>. Every number with exactly {@code d} digits contributes
 * the same number of commas: {@code (d - 1) / 3}. That's because a comma sits at every group
 * boundary beyond the first three digits, and there are exactly {@code (d - 1) / 3} such
 * boundaries in a {@code d}-digit number.
 *
 * <p><b>Plan:</b> walk digit lengths {@code d = 1, 2, 3, ...} For every digit length strictly
 * below the digit length of {@code n}, the full range {@code [10^(d-1), 10^d - 1]} is included, so
 * it contributes {@code commas(d) * 9 * 10^(d-1)} numbers' worth of commas (there are exactly
 * {@code 9 * 10^(d-1)} d-digit numbers). The digit length equal to {@code n}'s own digit length is
 * only partially covered, from {@code 10^(d-1)} up to {@code n} itself, so it contributes {@code
 * commas(d) * (n - 10^(d-1) + 1)}. Summing these contributions gives the answer.
 *
 * <p><b>Implement:</b> see {@link #countCommas(long)} below.
 *
 * <p><b>Review:</b> exercised by {@code CountCommasInRangeIITest}, covering the single-digit
 * case (no commas possible), the boundary just below and at the four-digit threshold where the
 * first comma appears, and larger values that accumulate one and then two commas per number.
 *
 * <p><b>Evaluate:</b> Time O(D) and space O(1), where {@code D} is the number of digits in {@code
 * n} (at most ~19 for a {@code long}) - independent of the magnitude of {@code n} itself.
 */
public class CountCommasInRangeII {

  public long countCommas(long n) {
    String digits = Long.toString(n);
    int totalDigitLength = digits.length();

    long total = 0;
    // pow tracks 10^(d-1), the first number with exactly d digits.
    long pow = 1;

    // Step 1: every digit length strictly shorter than n's own is fully covered by [1, n].
    for (int d = 1; d < totalDigitLength; d++) {
      long countOfDDigitNumbers = 9L * pow;
      long commasPerNumber = (d - 1) / 3;
      total += commasPerNumber * countOfDDigitNumbers;
      pow *= 10;
    }

    // Step 2: n's own digit length is only partially covered, from pow (= 10^(D-1)) up to n.
    long countInLastGroup = n - pow + 1;
    long commasPerNumberInLastGroup = (totalDigitLength - 1) / 3;
    total += commasPerNumberInLastGroup * countInLastGroup;

    return total;
  }
}
