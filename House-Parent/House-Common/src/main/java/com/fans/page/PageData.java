package com.fans.page;

import lombok.Data;

import java.util.List;

/**
 * @ClassName PageData
 * @Description:
 * @Author fan
 * @Date 2019-06-30 00:30
 * @Version 1.0
 **/
@Data
public class PageData<T> {

    private List<T> list;

    private Pagination pagination;

    public PageData(List<T> list, Pagination pagination) {
        this.list = list;
        this.pagination = pagination;
    }

    public static <T> PageData<T> buildPage(List<T> list, long count, Integer pageSize, Integer pageNum) {
        Pagination pagination = new Pagination(pageNum, pageSize, count);
        return new PageData<>(list, pagination);
    }
}
