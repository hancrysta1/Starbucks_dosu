package com.multi.spring2.board.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@NoArgsConstructor
@Setter @Getter @ToString
public class Page<T> {
    private List<T> list; //페이지별 보여줄 목록
    private int pageSize; //페이지별 보여줄 목록수
    private int currentPage; //현재페이지
    private int totalCnt;   //총목록수
    private int totalPages; //총페이지수

    private boolean first; //현재페이지가 첫페이지인 경우 true
    private boolean last; //현재페이지가 마지막페이지인 경우 true
    private boolean hasNext;
    private boolean hasPrevious;

    public Page(List<T> list, int pageSize, int currentPage, int totalCnt) {
        this.list = list;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalCnt = totalCnt;
        this.totalPages = (int) Math.ceil((double) totalCnt / pageSize);
        if(currentPage == 1){
            this.first = true;
        }
        if(currentPage == totalPages){
            this.last = true;
        }
        if(currentPage < totalPages){
            this.hasNext = true;
        }
        if(currentPage > 1){
            this.hasPrevious = true;
        }
    }
}
