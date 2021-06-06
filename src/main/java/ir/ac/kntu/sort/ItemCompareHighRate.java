package ir.ac.kntu.sort;

import ir.ac.kntu.objects.Item;

import java.util.Comparator;

public class ItemCompareHighRate implements Comparator<Item> {

    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getAverageRate() - o2.getAverageRate() > 0.0){
            return -1;
        } else if (o1.getAverageRate() == o2.getAverageRate()){
            return 0;
        }
        return 1;
    }
}
