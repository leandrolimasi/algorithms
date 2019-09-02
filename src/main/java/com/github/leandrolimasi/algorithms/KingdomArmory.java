package com.github.leandrolimasi.algorithms;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by leandrolimadasilva on 25/08/17.
 * Here I need to calculate the maximum beauty value out of all possible
 * equipment arrangements in the lowest possible order.
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
