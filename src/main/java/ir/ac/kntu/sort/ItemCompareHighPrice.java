package ir.ac.kntu.sort;

import ir.ac.kntu.objects.Item;

import java.util.Comparator;

public class ItemCompareHighPrice implements Comparator<Item> {
    @Override
    public int compare(Item o1, Item o2) {
        if (o1.getPrice() - o2.getPrice() > 0.0){
            return -1;
        } else if (o1.getPrice() == o2.getPrice()){
            return 0;
        }
        return 1;
    }
}
