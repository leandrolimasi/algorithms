package com.github.leandrolimasi.algorithms;

import java.util.List;

/**
 * Implement an algorithm that receives two string lists and verify that one list contains at least
 * one letter from the other list.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> for each paired-up (a[i], b[i]) strings, determine whether they share at
 * least one character in common, regardless of position or order.
 *
 * <p><b>Match:</b> since character <i>order</i> doesn't matter (this is membership, not a
 * contiguous substring or a windowed comparison), the classic array patterns of sliding window or
 * two-pointer don't apply here. The better match is a <b>hash set membership check</b>: put one
 * string's characters into a {@code Set<Character>} and test the other string's characters against
 * it in O(1) per lookup. The current implementation instead does a nested brute-force scan
 * ({@code indexOf} inside a loop), which is correct but not the best-matched pattern.
 *
 * <p><b>Plan (better-matched):</b> build a {@code Set<Character>} from {@code strA}; walk
 * {@code strB} and return true as soon as a character is found in the set.
 *
 * <p><b>Implement:</b> see {@link #commonSubstring(List, List)} and {@link
 * #checkContainsString(String, String)} below (current brute-force version).
 *
 * <p><b>Review:</b> exercised by {@code TwoStringsTest}, covering a shared-character pair and a
 * disjoint pair, identical strings, completely disjoint alphabets, an empty first string, and a
 * pair of large strings resembling the original problem's input scale.
 *
 * <p><b>Evaluate:</b> Time O(n*m) per pair (n = strA.length(), m = strB.length()), since each of
 * the n characters triggers an O(m) {@code indexOf} scan; the hash-set alternative above would be
 * O(n+m) per pair instead. Space O(1) extra for the brute-force version (O(n) if the hash-set
 * alternative were used, for the set itself).
 */
public class TwoStrings {

  /*
   * Complete the 'commonSubstring' function below.
   *
   * The function accepts following parameters:
   *  1. STRING_ARRAY a
   *  2. STRING_ARRAY b
   */
  public boolean[] commonSubstring(List<String> a, List<String> b) {
    boolean[] result = new boolean[a.size()];
    for (int i = 0; i < a.size(); i++) {
      String strA = a.get(i);
      String strB = b.get(i);
      result[i] = checkContainsString(strA, strB);
    }
    return result;
  }

  private boolean checkContainsString(String strA, String strB) {
    boolean contains = false;
    for (int j = 0; j < strA.length(); j++) {
      if (strB.indexOf(strA.charAt(j)) > -1) {
        contains = true;
        break;
      }
    }
    return contains;
  }
}
