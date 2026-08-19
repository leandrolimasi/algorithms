package com.github.leandrolimasi.algorithms;

/**
 * Delete every node whose value is greater than {@code x} from a singly linked list, keeping the
 * remaining nodes in their original order, and return the new head.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> given the head of a singly linked list and a threshold {@code x}, remove
 * every node with {@code data > x} while preserving the relative order of the surviving nodes.
 *
 * <p><b>Match:</b> Linked list -> <b>two-pointer (previous/current) traversal</b>. Removing an
 * arbitrary node from a singly linked list requires relinking {@code prev.next = curr.next}, which
 * needs both a trailing ({@code prev}) and a leading ({@code curr}) pointer walking together.
 *
 * <p><b>Plan (the matched pattern):</b> {@code prev = null}, {@code curr = head}; while
 * {@code curr != null}: if {@code curr.data > x}, unlink it ({@code if prev == null} advance
 * {@code head} instead, else {@code prev.next = curr.next}) and advance only {@code curr}; else
 * advance both {@code prev} and {@code curr}.
 *
 * <p><b>Implement:</b> the code below does <i>not</i> implement the plan above &mdash; there is no
 * {@code prev} pointer, and the loop stops at the <i>first</i> node greater than {@code x},
 * returning just that node's successor instead of continuing to filter the rest of the list. See
 * {@link DeleteNodesGreaterThanXTest} for exactly what it returns today.
 *
 * <p><b>Review:</b> {@link DeleteNodesGreaterThanXTest} pins down the current (incomplete)
 * behavior for a list with a qualifying node, a list with none, a single-node list, and a null
 * head, documenting in each case what the correct/intended result would be.
 *
 * <p><b>Evaluate:</b> Time O(n) worst case &mdash; a single pass that may scan the whole list
 * (n = list length) if no node exceeds {@code x}, or exit early on the first match. Space O(1)
 * extra. Note this Big-O matches what a <i>correct</i> two-pointer implementation would cost too
 * &mdash; the bug here is one of correctness, not of asymptotic complexity.
 */
public class DeleteNodesGreaterThanX {

  /*
   * For your reference:
   *
   * SinglyLinkedListNode {
   *     int data;
   *     SinglyLinkedListNode next;
   * }
   *
   */

  public SinglyLinkedListNode removeNodes(SinglyLinkedListNode listHead, int x) {
    // Write your code here
    SinglyLinkedListNode n = SinglyLinkedListNode.builder().build();
    if (listHead == null) {
      return n;
    }
    n.data = listHead.data;
    while (listHead.next != null) {
      if (listHead.data > x) {
        n = listHead.next;
        listHead.next = listHead.next.next;
        break;
      }
      listHead = listHead.next;
    }
    return n;
  }
}
