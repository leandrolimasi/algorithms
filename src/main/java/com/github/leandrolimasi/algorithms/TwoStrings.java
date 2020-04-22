package com.github.leandrolimasi.algorithms;

import java.util.List;

/**
 * Implement an algorithm that receives two string lists and verify that one list contains at least
 * one letter from the other list.
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
