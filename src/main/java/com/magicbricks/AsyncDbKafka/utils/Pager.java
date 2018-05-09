package com.magicbricks.AsyncDbKafka.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

public class Pager {

    private int page;
    private int results;
    private String sortOrder;
    private String sortColumn;
    private long totalResults;
    private int maxPageLinks;

    public Pager() {
        this.page = 1;
        this.results = 10;
        this.maxPageLinks = 4;
        sortOrder = null;
        sortColumn = null;
    }

    public Pager(int results) {
        this();
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long maxResults) {
        this.totalResults = maxResults;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public int getStart() {
        return (page - 1) * results;
    }

    public String requestPrevPage() {
        return toRequestParam(page - 1);
    }

    public String requestNextPage() {
        return toRequestParam(page + 1);
    }

    private String toRequestParam(int toPage) {
        String requestStr = "page=" + toPage;
        if (sortColumn != null) {
            requestStr += "&sortColumn=" + sortColumn;
        }
        if (sortOrder != null) {
            requestStr += "&sortOrder=" + sortOrder;
        }
        return requestStr;
    }

    public void setPaginationLinks(String basePath, Map<String, Object> map, int maxpage, boolean includePage) {
        map.put("prevUrl", page == 1 ? "#" : basePath + "&" + requestPrevPage());
        map.put("nextUrl", page == maxpage ? "#" : basePath + "&" + requestNextPage());
        ArrayList<String> paginationLinks = new ArrayList<String>();
        int startIndex = (includePage ? page : (page + 1));
        for (int i = startIndex; i <= maxpage && paginationLinks.size() < this.maxPageLinks; i++) {
            paginationLinks.add(basePath + "&" + toRequestParam(i));
        }
        map.put("paginationLinks", paginationLinks);
        if (includePage) {
            LinkedList<String> previousPageLinks = new LinkedList<String>();
            for (int i = page - 1; i > 0 && (paginationLinks.size() + previousPageLinks.size() < this.maxPageLinks); i--) {
                previousPageLinks.addFirst(basePath + "&" + toRequestParam(i));
            }
            map.put("previousPageLinks", previousPageLinks);
        }

        map.put("maxpage", maxpage);
    }

    public static void setPaginationLinks(String basePath, Pager pager, Map<String, Object> map) {
        setPaginationLinks(basePath, pager, map, true);
    }

    public static void setPaginationLinks(String basePath, Pager pager, Map<String, Object> map, boolean includePage) {
        int maxpage = (int) Math.ceil(pager.totalResults / (double) pager.results);
        pager.setPaginationLinks(basePath.replaceAll("(&)?page=([^&])*(&)?", ""), map, maxpage, includePage);
    }
}
