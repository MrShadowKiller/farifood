package ir.ac.kntu.sort;

import ir.ac.kntu.Department;

import java.util.Comparator;

public class DepartmentCompareLowComments implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {
        return o1.getComments().size() - o2.getComments().size();
    }
}
