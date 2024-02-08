package com.example.testingsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //Считать с консоли предложение, в мапу положить слово, а длину в значение и отсортировать, после чего вывести
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();
        String[] firstWord = word.split(" ");
        for (int i = 0; i < firstWord.length; i++) {
            map.put(firstWord[i], firstWord[i].length());
        }
        Set<Map.Entry<String, Integer>> setMap = map.entrySet();
        List<Map.Entry<String, Integer>> setList = new ArrayList<>(setMap);
        setList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        Map<String, Integer> newMap = new LinkedHashMap<>();
        for (int i = 0; i < setList.size(); i++) {
            newMap.put(setList.get(i).getKey(), setList.get(i).getValue());
        }
        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
