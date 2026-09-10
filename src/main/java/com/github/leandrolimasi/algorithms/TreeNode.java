package com.github.leandrolimasi.algorithms;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;
}
