package com.github.leandrolimasi.algorithms;

/**
 * Lilah has a string,(s) , of lowercase English letters that she repeated infinitely many times.
 *
 * <p>Given an integer, (n), find and print the number of letter a's in the first (n) letters of
 * Lilah's infinite string.
 *
 * <p>For example, if the string (s = 'abcac') and (n=10), the substring we consider is
 * (abcacabcac), the first 10 characters of her infinite string. There are (4) occurrences of a in
 * the substring.
 *
 * <p>Function Description
 *
 * <p>Complete the repeatedString function in the editor below. It should return an integer
 * representing the number of occurrences of a in the prefix of length in the infinitely repeating
 * string.
 *
 * <p>repeatedString has the following parameter(s):
 *
 * <p>s: a string to repeat
 *
 * <p>n: the number of characters to consider
 *
 * <p>Print a single integer denoting the number of letter a's in the first letters of the infinite
 * string created by repeating infinitely many times.
 *
 * <p>Sample Input 0 aba 10
 *
 * <p>Sample Output 0 7
 *
 * <p>Explanation 0 T he first n=10 letters of the infinite string are abaabaabaa. Because there are
 * 7 a's, we print 7 on a new line.
 *
 * <p>Sample Input 1 a 1000000000000
 *
 * <p>Sample Output 1 1000000000000
 *
 * <p>Explanation 1 Because all of the first n=1000000000000 letters of the infinite string are a,
 * we print 1000000000000 on a new line.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> {@code s} repeats infinitely; count the 'a's among the first {@code n}
 * characters of that infinite string, where {@code n} can be as large as 10^12.
 *
 * <p><b>Match:</b> Strings + optimization, but via <b>modular arithmetic / cycle exploitation</b>
 * rather than DP or greedy: since the string is just {@code s} repeated, one period's 'a' count
 * scales linearly with the number of full periods ({@code n / s.length()}), plus a leftover
 * partial period ({@code n % s.length()}) that must be counted directly.
 *
 * <p><b>Plan:</b> count 'a's in one copy of {@code s}; multiply by {@code n / s.length()} full
 * copies; separately count 'a's in the first {@code n % s.length()} characters of {@code s} for
 * the remainder; sum the two.
 *
 * <p><b>Implement:</b> see {@link #repeatedString(String, long)} below.
 *
 * <p><b>Review:</b> exercised by {@code RepeatedStringTest}, covering multiple full periods plus a
 * remainder, an astronomically large {@code n} (proving the solution doesn't loop n times), n
 * smaller than one period, a period with no 'a' at all, and a period made entirely of 'a'.
 *
 * <p><b>Evaluate:</b> Time O(m) where m = s.length() &mdash; completely independent of {@code n},
 * which is what makes n = 10^12 tractable. Space O(1) extra (only counters; charAt is used instead
 * of toCharArray, so no auxiliary array is allocated).
 */
public class RepeatedString {

  // Complete the repeatedString function below.
  public long repeatedString(String s, long n) {

    long count = 0;

    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == 'a') {
        count++;
      }
    }

    long div = n / s.length();
    long reminder = n % s.length();

    count = div * count;

    for (int i = 0; i < reminder; i++) {
      if (s.charAt(i) == 'a') {
        count++;
      }
    }

    return count;
  }
}
