package ir.ac.kntu.sort;

import ir.ac.kntu.Department;

import java.util.Comparator;

public class DepartmentCompareHighComments implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {
        return o2.getComments().size() - o1.getComments().size();
    }
}
