package com.github.leandrolimasi.algorithms;

import org.springframework.stereotype.Component;

/**
 * Given a time in 12-hour AM/PM format, convert it to military (24-hour) time. Note: Midnight is
 * 12:00:00AM on a 12-hour clock, and 00:00:00 on a 24-hour clock. Noon is 12:00:00PM on a 12-hour
 * clock, and 12:00:00 on a 24-hour clock.
 *
 * <p>Function Description
 *
 * <p>Complete the timeConversion function in the editor below. It should return a new string
 * representing the input time in 24 hour format.
 *
 * <p>timeConversion has the following parameter(s):
 *
 * <p>s: a string representing time in hour format Input Format
 *
 * <p>A single string containing a time in -hour clock format (i.e.: or ), where and .
 *
 * <p>Constraints
 *
 * <p>All input times are valid Output Format
 *
 * <p>Convert and print the given time in -hour format, where .
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> convert a fixed-format 12-hour "hh:mm:ssAM/PM" string into a 24-hour
 * "HH:mm:ss" string.
 *
 * <p><b>Match:</b> this doesn't need any of the pointer/graph/DP-greedy/binary-search patterns
 * &mdash; it's direct <b>string parsing plus boundary-case handling</b>. The only non-linear part
 * of the mapping is at hour 12, which behaves oppositely for AM (becomes 0) versus PM (stays 12
 * instead of becoming 24); every other hour is either unchanged (AM) or {@code +12} (PM).
 *
 * <p><b>Plan:</b> split on {@code ':'}, parse hour/minute/second and the AM/PM suffix; if AM and
 * hour is 12, set hour to 0; else if PM and hour is less than 12, add 12; otherwise leave the hour
 * unchanged; reformat as zero-padded {@code HH:mm:ss}.
 *
 * <p><b>Implement:</b> see {@link #convert(String)} below.
 *
 * <p><b>Review:</b> exercised by {@code TimeConversionTest}, covering an ordinary PM time, an
 * ordinary AM time, the midnight boundary (12:00:00AM -> 00:00:00), and the noon boundary
 * (12:00:00PM -> 12:00:00).
 *
 * <p><b>Evaluate:</b> Time O(1) &mdash; the input is a fixed-length string, so parsing and
 * formatting do a constant amount of work regardless of input. Space O(1) &mdash; only a
 * fixed-size array from {@code split} and a few local variables.
 */
@Component
public class TimeConversion {

  /*
   * Complete the timeConversion function below.
   */
  public String convert(String s) {
    /*
     * Write your code here.
     */
    String tArr[] = s.split(":");
    String AmPm = tArr[2].substring(2, 4);
    int hh, mm, ss;
    hh = Integer.parseInt(tArr[0]);
    mm = Integer.parseInt(tArr[1]);
    ss = Integer.parseInt(tArr[2].substring(0, 2));

    String checkPM = "PM", checkAM = "AM";
    int h = hh;
    if (AmPm.equals(checkAM) && hh == 12) {
      h = 0;
    } else if (AmPm.equals(checkPM) && hh < 12) {
      h += 12;
    }

    return String.format("%02d:%02d:%02d", h, mm, ss);
  }
}
