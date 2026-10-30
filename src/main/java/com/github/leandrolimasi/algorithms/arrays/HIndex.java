package com.github.leandrolimasi.algorithms.arrays;

/**
 * Given an array of integers {@code citations} where {@code citations[i]} is the number of
 * citations received by a researcher's {@code i}-th paper, return the researcher's h-index: the
 * maximum value {@code h} such that the researcher has published at least {@code h} papers that
 * have each been cited at least {@code h} times.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> {@code h} trades off two competing counts against each other - a bigger
 * {@code h} demands more qualifying papers, but also a higher citation bar for each one to
 * qualify. Crucially, {@code h} can never exceed {@code n} (the total number of papers), since
 * there's no way to have more than {@code n} papers cited at least {@code h} times if {@code h >
 * n}.
 *
 * <p><b>Match:</b> sorting citations and scanning for the crossing point works but costs O(n log
 * n) -&gt; <b>counting sort via buckets capped at {@code n}</b>, the same family of trick as
 * {@code MajorityElement} exploiting a problem-specific bound to beat comparison-based sorting.
 * Because {@code h} is capped at {@code n}, any citation count of {@code n} or more is
 * indistinguishable from exactly {@code n} for this problem's purposes, so every citation can be
 * bucketed into one of only {@code n + 1} slots (0 through {@code n}) instead of needing a
 * general-purpose sort.
 *
 * <p><b>Plan:</b> build {@code buckets[0..n]} where {@code buckets[k]} counts how many papers
 * have exactly {@code k} citations, clamping any citation {@code >= n} into {@code buckets[n]}.
 * Then scan candidate values of {@code h} from {@code n} down to 0, maintaining a running {@code
 * count} of papers with at least {@code h} citations: at each step, add {@code buckets[h]} into
 * {@code count} before checking it, since a paper in {@code buckets[h]} has exactly {@code h}
 * citations and so also counts toward "at least {@code h}". The first (largest) {@code h} for
 * which {@code count >= h} is the h-index - larger candidates were already checked and failed, so
 * nothing bigger can work.
 *
 * <p><b>Implement:</b> see {@link #hIndex(int[])} below.
 *
 * <p><b>Review:</b> exercised by {@code HIndexTest}, covering both given examples, a researcher
 * with no citations at all (h-index 0), a single highly-cited paper (h-index capped by the paper
 * count, not the citation count), and every paper landing at exactly {@code n} citations (h-index
 * exactly {@code n}).
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; one pass to fill the buckets, one pass to scan them.
 * Space O(n) for the bucket array, versus O(n log n) time for a sort-based approach.
 */
public class HIndex {

  public int hIndex(int[] citations) {
    int n = citations.length;
    int[] buckets = new int[n + 1];

    for (int citation : citations) {
      buckets[Math.min(citation, n)]++;
    }

    int count = 0;
    for (int h = n; h >= 0; h--) {
      count += buckets[h];
      if (count >= h) {
        return h;
      }
    }

    return 0;
  }
}
