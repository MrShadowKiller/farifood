package ir.ac.kntu.sort;

import ir.ac.kntu.Department;

import java.util.Comparator;

public class DepartmentCompareLowRate implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {
        if (o1.getAverageRate() - o2.getAverageRate() > 0){
            return 1;
        } else if (o1.getAverageRate() == o2.getAverageRate()){
            return 0;
        }
        return -1;
    }
}
