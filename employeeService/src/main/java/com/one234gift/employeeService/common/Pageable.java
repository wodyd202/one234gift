package com.one234gift.employeeService.common;

public class Pageable {
    private int page;
    private int size;

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getPage() {
        return page;
    }
}
