package com.github.leandrolimasi.algorithms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DeleteNodesGreaterThanX.class})
public class DeleteNodesGreaterThanXTest {

  @Autowired private DeleteNodesGreaterThanX deleteNodesGreaterThanX;

  @Test
  public void testCase1() {
    SinglyLinkedListNode s1 = SinglyLinkedListNode.builder().data(1).build();
    SinglyLinkedListNode s2 = SinglyLinkedListNode.builder().data(2).build();
    SinglyLinkedListNode s3 = SinglyLinkedListNode.builder().data(3).build();
    SinglyLinkedListNode s4 = SinglyLinkedListNode.builder().data(4).build();
    SinglyLinkedListNode s5 = SinglyLinkedListNode.builder().data(5).build();
    s1.setNext(s2);
    s2.setNext(s3);
    s3.setNext(s4);
    s4.setNext(s5);
    deleteNodesGreaterThanX.removeNodes(s1, 3);
  }
}
