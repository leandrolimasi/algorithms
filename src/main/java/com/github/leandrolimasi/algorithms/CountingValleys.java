package com.github.leandrolimasi.algorithms;

import org.springframework.stereotype.Component;

/*
Gary is an avid hiker. He tracks his hikes meticulously, paying close attention to small details like topography.
During his last hike he took exactly (N) steps. For every step he took, he noted if it was an uphill, U , or a downhill, D step.
Gary's hikes start and end at sea level and each step up or down represents a 1 unit change in altitude.

We define the following terms:

- A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending
 with a step down to sea level.
- A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending
 with a step up to sea level.

Given Gary's sequence of up and down steps during his last hike, find and print the number of valleys he walked through.

For example, if Gary's path is s = [DDUUUUDD] , he first enters a valley 2 units deep.
Then he climbs out an up onto a mountain  units high. Finally, he returns to sea level and ends his hike.

Function Description

Complete the countingValleys function in the editor below. It must return an integer that denotes the number of valleys Gary traversed.

countingValleys has the following parameter(s):

n: the number of steps Gary takes
s: a string describing his path
Input Format

The first line contains an integer , the number of steps in Gary's hike.
The second line contains a single string , of  characters that describe his path.

Sample Input
8
UDDDUDUU

Sample Output
1

Explanation
If we represent _ as sea level, a step up as /, and a step down as \, Gary's hike can be drawn as:

_/\      _
   \    /
    \/\/
He enters and leaves one valley.

UMPIRE
------
Understand: given a sequence of U/D steps starting and ending at sea level, count how many
times the hiker completes a valley, i.e. rises back to sea level via a 'U' step after having
dipped below it.

Match: this is neither a pointer/sliding-window problem, a graph traversal, a DP/greedy
optimization, nor a sorted-data search -- it's the simpler "running state / single-pass
accumulator" pattern (the same shape used for balanced-parentheses or high-water-mark problems):
one scalar (the current altitude) fully captures everything needed to make a decision at each
step, so a single linear scan suffices.

Plan: keep a running "level" counter; ++level on 'U', --level on 'D'; whenever a 'U' step brings
level back to exactly 0, increment the valley count.

Implement: see countingValleys(int, String) below.

Review: exercised by CountingValleysTest, covering one valley, two valleys separated by a
mountain, zero valleys, a pure mountain (never dips), the minimal single-step valley, and two
consecutive separate valleys.

Evaluate: Time O(n) where n = s.length(), one pass over the characters. Space: the counters
themselves are O(1), but s.toCharArray() allocates an extra O(n) char array up front; iterating
with s.charAt(i) instead would make this a true O(1)-extra-space solution.
*/
@Component
public class CountingValleys {

  // Complete the countingValleys function below.
  public int countingValleys(int n, String s) {

    int valleys = 0;
    int level = 0;
    for (char c : s.toCharArray()) {
      if (c == 'U') {
        ++level;
      } else if (c == 'D') {
        --level;
      }

      if (level == 0 && c == 'U') {
        ++valleys;
      }
    }

    return valleys;
  }
}
