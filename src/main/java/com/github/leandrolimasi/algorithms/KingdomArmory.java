package com.github.leandrolimasi.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by leandrolimadasilva on 25/08/17.
 * Here I need to calculate the maximum beauty value out of all possible
 * equipment arrangements in the lowest possible order.
 *
 * <h2>UMPIRE</h2>
 *
 * <p><b>Understand:</b> given a list of equipment values, arrange them (any permutation) so that
 * an alternating add/divide/multiply "beauty" formula (add the 1st, divide by the next even-index
 * item, multiply by the next odd-index item, ...) is maximized; among arrangements that tie for
 * the maximum beauty, return the lexicographically lowest one.
 *
 * <p><b>Match:</b> Optimization -> this is implemented as <b>brute-force permutation
 * enumeration</b> (generate every permutation, score each, pick the best), not Dynamic Programming
 * or Greedy. Greedy doesn't obviously apply because the formula's effect on a value (add vs.
 * divide vs. multiply) depends on its <i>position's parity</i>, so a locally-attractive value can
 * become a poor choice once its position is fixed; a DP formulation would need to track which
 * subset of items has been used so far as part of the state, which only helps if the beauty
 * contribution can be decomposed independently of the exact arrangement -- not attempted here.
 *
 * <p><b>Plan:</b> {@link #generatePerm(List)} recursively builds every permutation of the input;
 * for each permutation {@link #calcBeauty(List)} computes its beauty; the permutations are
 * collected into a {@code TreeMap<String, Double>} keyed by the comma-joined permutation (which
 * sorts keys lexicographically) so that, after sorting entries by beauty descending, the first
 * entry with the maximum beauty is also the lexicographically smallest such permutation.
 *
 * <p><b>Implement:</b> see {@link #arrangeEquipments(List)} below.
 *
 * <p><b>Review:</b> there is currently no dedicated test class for this algorithm (unlike the
 * other classes in this package) -- worth adding one, ideally with small inputs (3-6 items) given
 * the cost noted below.
 *
 * <p><b>Evaluate:</b> Time and space are both <b>O(n! * n)</b> -- {@code n!} permutations are
 * generated and stored (each permutation costs O(n) to build/score/stringify), which is factorial,
 * not polynomial, growth. Concretely: this is only practical for very small {@code n} (roughly up
 * to 8-10). The {@link #main(String[])} example below calls this with 13 equipments, which means
 * 13! &asymp; 6.2 billion permutations &mdash; that call would not complete in any reasonable time
 * or memory and should not be run as-is; it's left here as a reminder of the algorithm's limits
 * rather than a safe example invocation.
 */
public class KingdomArmory {

    public static void main(String[] args) {
        arrangeEquipments(Arrays.asList(8,8,8,8,8,8,8,8,8,8,8,8,8));
    }

  /*
   * Complete the 'arrangeEquipments' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts INTEGER_ARRAY equipments as parameter.
   */

    public static List<Integer> arrangeEquipments(List<Integer> equipments) {
        List<List<Integer>> arrangeEquipmentsMap = generatePerm(new LinkedList<>(equipments));
        Map<String, Double> map = new TreeMap<>();

        arrangeEquipmentsMap.forEach(
                e -> {
                    map.put(
                            e.stream()
                            .map(n -> String.valueOf(n))
                            .collect(Collectors.joining(",")),
                            calcBeauty(e));
                });

        Map.Entry<String, Double> result  = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .findFirst().get();

        int[] ints = Arrays.stream(result.getKey().split(",")).mapToInt(Integer::parseInt).toArray();
        return Arrays.stream(ints).boxed().collect(Collectors.toList());
    }

    public static List<List<Integer>> generatePerm(List<Integer> original) {
        if (original.size() == 0) {
            List<List<Integer>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        Integer firstElement = original.remove(0);
        List<List<Integer>> returnValue = new ArrayList<>();
        List<List<Integer>> permutations = generatePerm(original);
        for (List<Integer> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<Integer> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }

    public static Double calcBeauty(List<Integer> equipments) {
        BigDecimal beauty = BigDecimal.ZERO.setScale(2);

        for (int i = 0; i < equipments.size(); i++) {
            if (i == 0) {
                beauty = beauty.add(BigDecimal.valueOf(equipments.get(i)));
            } else if (i % 2 == 0) {
                beauty = beauty.divide(BigDecimal.valueOf((equipments.get(i))), 2, RoundingMode.HALF_UP);
            } else if (i % 2 == 1) {
                beauty = beauty.multiply(BigDecimal.valueOf(equipments.get(i)));
            }
        }

        return beauty.doubleValue();
    }

}
