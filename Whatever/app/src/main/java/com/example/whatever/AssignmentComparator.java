package com.example.whatever;

import java.util.List;

public class AssignmentComparator {
    private char mode;
    public AssignmentComparator() {
        mode = 'D';
    }

    public void setMode(char m) {
        mode = m;
    }

    public int compare(AssignmentData a, AssignmentData b) {
        if (mode == 'D') {
            return a.getDueDate().compareTo(b.getDueDate());
        } else {
            return a.getAssociatedClass().compareTo(b.getAssociatedClass());
        }
    }

    public void sort(List<AssignmentData> list) {
        for (int i = 1; i < list.size(); i++) {
            for (int j = i; j > 0 && compare(list.get(j), list.get(j - 1)) < 0; j--) {
                swap(list, j, j - 1);
            }
        }
    }

    private void swap(List<AssignmentData> list, int a, int b) {
        AssignmentData aData = list.get(a);
        list.set(a, list.get(b));
        list.set(b, aData);
    }
}
