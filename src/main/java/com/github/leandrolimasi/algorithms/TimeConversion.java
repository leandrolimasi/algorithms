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
