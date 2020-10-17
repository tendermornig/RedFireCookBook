package com.hnqcgc.redfirecookbook.logic.model;

import retrofit2.http.Query;

public class QueryNumber {

    private int limit;

    private int offset;

    public QueryNumber(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

}
