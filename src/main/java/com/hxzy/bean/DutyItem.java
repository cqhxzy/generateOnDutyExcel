package com.hxzy.bean;
import java.util.List;
import java.util.Objects;

public class DutyItem {
    private List<String> names;

    @Override
    public String toString() {
        return "DutyItem{" +
                "names=" + names +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DutyItem dutyItem = (DutyItem) o;
        return names.equals(dutyItem.names);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names);
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
