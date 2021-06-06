package ir.ac.kntu.sort;

import ir.ac.kntu.Department;
import ir.ac.kntu.objects.Item;

import java.util.Comparator;

public class DepartmentCompareHighRate implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {
        if (o1.getAverageRate() - o2.getAverageRate() > 0.0){
            return -1;
        } else if (o1.getAverageRate() == o2.getAverageRate()){
            return 0;
        }
        return 1;
    }
}
