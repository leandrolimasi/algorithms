package com.github.leandrolimasi.algorithms;

import java.util.List;

public class TwoStrings {

  /*
   * Complete the 'commonSubstring' function below.
   *
   * The function accepts following parameters:
   *  1. STRING_ARRAY a
   *  2. STRING_ARRAY b
   */

  public void commonSubstring(List<String> a, List<String> b) {
    // Write your code here
    for (int i = 0; i < a.size(); i++) {
      String strA = a.get(i);
      String strB = b.get(i);
      boolean contains = false;
      for (int j = 0; j < strA.length(); j++) {
        if (strA.contains(Character.toString(strB.charAt(j)))) {
          contains = true;
          break;
        }
      }
      if (contains) {
        System.out.println("TRUE");
      } else {
        System.out.println("FALSE");
      }
    }
  }
}
