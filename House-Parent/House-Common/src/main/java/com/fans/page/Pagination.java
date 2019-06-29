package com.fans.page;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Pagination
 * @Description:
 * @Author fan
 * @Date 2019-06-30 00:31
 * @Version 1.0
 **/
@Data
public class Pagination {
    private int pageNum;
    private int pageSize;
    private long totalCount;
    /**
     * 支持 1,2....10,11
     */
    private List<Integer> pages = Lists.newArrayList();

    public Pagination(int pageNum, int pageSize, long totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        for (int i = 0; i < pageNum; i++) {
            pages.add(i);
        }
        Long pageCount = totalCount / pageSize + ((totalCount % pageSize == 0) ? 0 : 1);
        if (pageCount > pageNum) {
            for (int i = pageNum + 1; i < pageCount; i++) {
                pages.add(i);
            }
        }
    }
}
