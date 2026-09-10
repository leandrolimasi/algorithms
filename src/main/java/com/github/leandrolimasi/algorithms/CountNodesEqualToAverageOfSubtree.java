package com.github.leandrolimasi.algorithms;

/**
 * Given the {@code root} of a binary tree, return the number of nodes where the node's value
 * equals the average of all values in its subtree (itself plus every descendant), with the
 * average of {@code n} elements defined as their sum divided by {@code n} and rounded down.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> every node needs its own subtree's sum and node count to check the
 * average condition. A subtree's sum/count only depends on the subtree itself, not on anything
 * outside it, so each node's check is independent of its ancestors and siblings.
 *
 * <p><b>Match:</b> checking every node by re-walking its whole subtree from scratch is O(n) per
 * node, O(n^2) overall on a skewed tree -&gt; <b>postorder tree DFS with bottom-up aggregation</b>.
 * A node's subtree sum/count is just its own value combined with its children's already-computed
 * subtree sum/count, so each subtree only needs to be summed once, by the recursive call that
 * owns it.
 *
 * <p><b>Plan:</b> a recursive helper visits a node's children first (postorder), then combines
 * their {@code (sum, count)} pairs with the node's own value: {@code sum = leftSum + rightSum +
 * node.val}, {@code count = leftCount + rightCount + 1}. If {@code sum / count} (integer
 * division, which truncates toward zero and so already matches the problem's "rounded down" rule
 * since sums are non-negative) equals {@code node.val}, this node counts as a match. The helper
 * returns its own {@code (sum, count)} pair so the parent can do the same. A running counter,
 * incremented on every match, is the final answer.
 *
 * <p><b>Implement:</b> see {@link #averageOfSubtree(TreeNode)} below.
 *
 * <p><b>Review:</b> exercised by {@code CountNodesEqualToAverageOfSubtreeTest}, covering the given
 * example, the single-node base case, an asymmetric left-only chain (null-child handling), and a
 * case where a non-integer true average still counts as a match because of the "rounded down"
 * rule.
 *
 * <p><b>Evaluate:</b> Time O(n) &mdash; one postorder pass visiting every node exactly once. Space
 * O(h) for the recursion stack, where {@code h} is the tree height (O(n) worst case on a skewed
 * tree, O(log n) on a balanced one).
 */
public class CountNodesEqualToAverageOfSubtree {

  private int matchCount;

  public int averageOfSubtree(TreeNode root) {
    matchCount = 0;
    sumAndCount(root);
    return matchCount;
  }

  /**
   * Returns {sum, count} for the subtree rooted at {@code node}, and along the way increments
   * {@link #matchCount} whenever {@code node}'s own value equals its subtree's average.
   */
  private int[] sumAndCount(TreeNode node) {
    if (node == null) {
      return new int[] {0, 0};
    }

    int[] left = sumAndCount(node.left);
    int[] right = sumAndCount(node.right);

    int sum = left[0] + right[0] + node.val;
    int count = left[1] + right[1] + 1;

    if (sum / count == node.val) {
      matchCount++;
    }

    return new int[] {sum, count};
  }
}
