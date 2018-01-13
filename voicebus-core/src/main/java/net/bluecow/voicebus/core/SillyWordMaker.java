package net.bluecow.voicebus.core;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SillyWordMaker {

    static List<String> prefixes = Arrays.asList("fl", "br", "snuf", "mur", "mi", "bli", "sit", "rit");
    static List<String> middles = Arrays.asList("aggle", "iggle", "opitu", "umble", "yiggle", "aggsi", "ee", "uppel", "ickle", "busstop", "chicken");
    static List<String> suffixes = Arrays.asList("pus", "biggle", "ticky", "zicky", "poo", "yang", "bumble", "donk");

    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println(makeSillyWord(r));
        }
    }

    public static String makeSillyWord(Random r) {
        StringBuilder sb = new StringBuilder();
        sb.append(randomEntry(r, prefixes));
        for (int i = 0; i < r.nextInt(3) + 1; i++) {
            sb.append(randomEntry(r, middles));
        }
        sb.append(randomEntry(r, suffixes));
        String s = sb.toString();
        return s;
    }

    private static String randomEntry(Random r, List<String> list) {
        return list.get(r.nextInt(list.size()));
    }
}
