package ir.ac.kntu.sort;

import ir.ac.kntu.Department;

import java.util.Comparator;

public class DepartmentCompareRising implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {
        if (o1.getOrders().size() >= o2.getOrders().size() && o2.getAverageRate() >=3 ){
            return 1;
        } else {
            return -1;
        }
    }
}
