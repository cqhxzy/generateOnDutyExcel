package com.hxzy.bean;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DutyItem {
    private List<String> names;
    private String dutyDate;

    @Override
    public String toString() {
        return "DutyItem{" +
                "names=" + names +
                ", dutyDate=" + dutyDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DutyItem dutyItem = (DutyItem) o;
        return names.equals(dutyItem.names) &&
                dutyDate.equals(dutyItem.dutyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(names, dutyDate);
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public String getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(String dutyDate) {
        this.dutyDate = dutyDate;
    }
}
