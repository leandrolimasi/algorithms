package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pattern: <b>postorder tree DFS with bottom-up aggregation</b>.
 *
 * <p>{@link CountNodesEqualToAverageOfSubtree#averageOfSubtree(TreeNode)} visits every node's
 * children before the node itself, so each node's {@code (sum, count)} pair is built directly
 * from its children's already-computed pairs instead of re-walking the whole subtree. That turns
 * an O(n) per-node check into a single O(n) pass over the whole tree.
 *
 * <p>Learning point: whenever a per-node check depends on an aggregate (sum, count, height, ...)
 * over that node's entire subtree, compute the aggregate bottom-up during a postorder traversal
 * and hand it up to the parent, rather than re-deriving it top-down for every node.
 */
public class CountNodesEqualToAverageOfSubtreeTest {

  private CountNodesEqualToAverageOfSubtree countNodesEqualToAverageOfSubtree =
      new CountNodesEqualToAverageOfSubtree();

  @Test
  @DisplayName("Given example: five of the seven nodes equal their own subtree's average")
  public void testCase1() {
    TreeNode zero = TreeNode.builder().val(0).build();
    TreeNode one = TreeNode.builder().val(1).build();
    TreeNode six = TreeNode.builder().val(6).build();
    TreeNode eight = TreeNode.builder().val(8).left(zero).right(one).build();
    TreeNode five = TreeNode.builder().val(5).right(six).build();
    TreeNode four = TreeNode.builder().val(4).left(eight).right(five).build();

    assertEquals(5, countNodesEqualToAverageOfSubtree.averageOfSubtree(four));
  }

  @Test
  @DisplayName("Single-node tree: its own value trivially equals the average of itself")
  public void testCase2() {
    TreeNode root = TreeNode.builder().val(1).build();

    assertEquals(1, countNodesEqualToAverageOfSubtree.averageOfSubtree(root));
  }

  @Test
  @DisplayName("Left-only chain: the leaf matches but the root's subtree average does not")
  public void testCase3() {
    // Root subtree: (10 + 5) / 2 = 7, which is not 10, so only the leaf (5) counts.
    TreeNode leaf = TreeNode.builder().val(5).build();
    TreeNode root = TreeNode.builder().val(10).left(leaf).build();

    assertEquals(1, countNodesEqualToAverageOfSubtree.averageOfSubtree(root));
  }

  @Test
  @DisplayName("Rounded-down average: a non-integer true average of 10/3 still counts as a match")
  public void testCase4() {
    // Root subtree: (3 + 2 + 5) / 3 = 10 / 3 = 3 after rounding down, which does equal the root's
    // own value (3), even though the true average (3.33...) is not an integer at all.
    TreeNode left = TreeNode.builder().val(2).build();
    TreeNode right = TreeNode.builder().val(5).build();
    TreeNode root = TreeNode.builder().val(3).left(left).right(right).build();

    assertEquals(3, countNodesEqualToAverageOfSubtree.averageOfSubtree(root));
  }
}
