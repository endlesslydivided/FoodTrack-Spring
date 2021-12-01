package com.divided.foodtrack.myPage;

import java.util.List;

public class Page<T>{

    //region Fields
    private List<T> content;
    private int currentPage;
    private int wholeAmount;
    private int totalPages;
    //endregion

    //region Constructors
    public Page(List<T> content, int currentPage, int wholeAmount, int totalPages) {
        this.content = content;
        this.currentPage = currentPage;
        this.wholeAmount = wholeAmount;
        this.totalPages = totalPages;
    }
    //endregion

    //region Properties
    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getWholeAmount() {
        return wholeAmount;
    }

    public void setWholeAmount(int wholeAmount) {
        this.wholeAmount = wholeAmount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    //endregion
}
