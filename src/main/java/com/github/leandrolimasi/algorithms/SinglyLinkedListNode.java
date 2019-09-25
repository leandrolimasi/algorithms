package com.github.leandrolimasi.algorithms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SinglyLinkedListNode {

  int data;
  SinglyLinkedListNode next;
}
