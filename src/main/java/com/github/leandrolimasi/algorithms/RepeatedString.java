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
