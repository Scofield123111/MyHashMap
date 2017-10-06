package main;

import java.util.Arrays;

public class TestMyHashMap {
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        String[] strings = "qwertyuiopasdfghjklzxcvbnm".split("");
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                map.put(strings[i] + strings[j], (int) (Math.random() * 100));
            }
        }

        System.out.println(map);
        System.out.println(map.get("as"));
        System.out.println(map.entrySet());

        System.out.println(Arrays.toString(map.buckets));

    }

}
