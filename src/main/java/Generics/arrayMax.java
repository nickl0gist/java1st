package Generics;

import java.util.List;

public class arrayMax {

    /**Zad. 1 (generyki)
    *Napisać generyczną metodę, służącą do wyszukania największego elementu listy w podanym zakresie.
    *Podpowiedź: Sygnatura metody powinna wyglądać tak:
    *public <E extends Comparable<E>> E max(List<E> data, int begin, int end)*/

    public <E extends Comparable<E>> E max(List<E> data, int begin, int end) throws Exception {
        if (begin > end){
            begin = changeVars(begin, end)[0];
            end = changeVars(begin, end)[1];
        }
        E max = data.get(begin);
        for (int i = begin + 1; i < end ; i++){
            if(data.get(i).compareTo(max) > 0){
                max = data.get(i);
            }
        }
        return max;
    }

    private int[] changeVars(int a, int b){
        a = a- b;
        b = b + a;
        a = b - a;
        return new int[]{a, b};
    }
}
