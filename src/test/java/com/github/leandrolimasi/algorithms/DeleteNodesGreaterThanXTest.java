package com.github.leandrolimasi.algorithms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Intended pattern: <b>two-pointer (previous/current) linked-list filtering</b>.
 *
 * <p>Removing nodes from a singly linked list while keeping the remaining ones in order normally
 * requires walking the list with both a {@code prev} and a {@code curr} pointer: when {@code
 * curr} should be removed you relink {@code prev.next = curr.next} and advance only {@code curr};
 * otherwise you advance both. That's what lets the rest of the list stay intact after a node in
 * the middle is dropped.
 *
 * <p><b>Important:</b> {@link DeleteNodesGreaterThanX#removeNodes(SinglyLinkedListNode, int)} does
 * not currently implement that pattern &mdash; it has no {@code prev} pointer, stops at the
 * <i>first</i> node greater than {@code x}, and returns just that node's successor instead of the
 * filtered list. In other words, it does not actually delete "nodes greater than X" from the list;
 * it discards everything up to and including the first offending node. The tests below intentionally
 * pin down this <i>actual, current</i> behavior (so a future refactor doesn't change it silently) and
 * document, in each case, what a correct implementation would be expected to return instead.
 */
public class DeleteNodesGreaterThanXTest {

  private DeleteNodesGreaterThanX deleteNodesGreaterThanX = new DeleteNodesGreaterThanX();

  private static SinglyLinkedListNode chain(int... values) {
    SinglyLinkedListNode head = null;
    SinglyLinkedListNode tail = null;
    for (int value : values) {
      SinglyLinkedListNode node = SinglyLinkedListNode.builder().data(value).build();
      if (head == null) {
        head = node;
      } else {
        tail.setNext(node);
      }
      tail = node;
    }
    return head;
  }

  @Test
  @DisplayName("Stops at the first node greater than x and returns only its successor")
  public void testCase1() {
    // Correct/intended result for 1->2->3->4->5 with x=3 would be 1->2->3 (nodes 4 and 5 removed).
    // Actual behavior: it walks 1,2,3 (all <= 3), finds 4 > 3, and returns node 4's successor (5)
    // as a single detached node, discarding 1, 2, 3 and 4 entirely instead of just 4.
    SinglyLinkedListNode result = deleteNodesGreaterThanX.removeNodes(chain(1, 2, 3, 4, 5), 3);

    assertEquals(5, result.data);
    assertNull(result.next);
  }

  @Test
  @DisplayName("When no node exceeds x, only the original head's value survives, detached")
  public void testCase2() {
    // Correct/intended result for 1->2->3 with x=10 would be the untouched list 1->2->3.
    // Actual behavior: the loop never finds a node > x, so it falls out of the loop and returns
    // the placeholder node that was pre-seeded with the head's data, with next left unset (null).
    SinglyLinkedListNode result = deleteNodesGreaterThanX.removeNodes(chain(1, 2, 3), 10);

    assertEquals(1, result.data);
    assertNull(result.next);
  }

  @Test
  @DisplayName("Single-node list is returned as an equivalent single detached node")
  public void testCase3() {
    SinglyLinkedListNode result = deleteNodesGreaterThanX.removeNodes(chain(7), 3);

    assertEquals(7, result.data);
    assertNull(result.next);
  }

  @Test
  @DisplayName("A null head returns a default zero-value node rather than null")
  public void testCase4() {
    // Correct/intended result for a null/empty list would typically be null.
    // Actual behavior: it returns SinglyLinkedListNode.builder().build(), i.e. data=0, next=null.
    SinglyLinkedListNode result = deleteNodesGreaterThanX.removeNodes(null, 3);

    assertEquals(0, result.data);
    assertNull(result.next);
  }
}
