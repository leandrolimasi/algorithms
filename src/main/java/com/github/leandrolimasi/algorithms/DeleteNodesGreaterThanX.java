package com.github.leandrolimasi.algorithms;

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
