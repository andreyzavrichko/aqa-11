package com.example.aqa.models;

public class CustomJson {

    public String query;

    public Integer page;

    public Integer size;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CustomJson{" +
                "query='" + query + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public CustomJson(String query, Integer page, Integer size) {
        this.query = query;
        this.page = page;
        this.size = size;
    }
}
