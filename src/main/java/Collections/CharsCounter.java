package Collections;

import java.util.*;

public class CharsCounter {
    /**
     * Zad. 2 (kolekcje)
     * Napisz program, który pokaże pozycje (indeksy) poszczególnych liter w napisie i wydrukuje ich zestawienie. Przykładowy wynik dla napisu "Hello World":
     * {d=[9], o=[4, 6], r=[7], W=[5], H=[0], l=[2, 3, 8], e=[1]}
     */

    public String charCount (String str){
        Map<Character, Set<Integer>> charMap = new HashMap <>();
        char[] stringToCharArray = str.replace(" ","").toCharArray();

        for (int i = 0; i < stringToCharArray.length; i++) {
            if(!charMap.containsKey(stringToCharArray[i])){
                charMap.put(stringToCharArray[i], new HashSet<Integer>());
                charMap.get(stringToCharArray[i]).add(i);
            } else {
                charMap.get(stringToCharArray[i]).add(i);
            }
        }
        return charMap.toString();
    }

    public static void main(String[] args) {
        System.out.println(new CharsCounter().charCount("Hello World"));
    }
}
