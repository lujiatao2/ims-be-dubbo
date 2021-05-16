package com.lujiatao.ims.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页器
 *
 * @author lujiatao
 */
public class Paginationer {

    private int pageSize;

    public Paginationer() {
        this(10);
    }

    public Paginationer(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取目标页数据
     *
     * @param totalDatas 总数据
     * @param targetPage 目标页码
     * @return 当前页码、总页码和目标数据
     */
    public Object[] getTargetDatas(List<?> totalDatas, int targetPage) {
        if (totalDatas == null || totalDatas.size() == 0) {
            return new Object[]{0, 0, new ArrayList<>()};
        }
        int currentPage;
        int totalPage = totalDatas.size() % pageSize == 0 ? totalDatas.size() / pageSize : totalDatas.size() / pageSize + 1;
        List<?> targetDatas;
        if (targetPage < 1) {
            currentPage = 1;
            targetDatas = totalDatas.subList(0, currentPage == totalPage ? totalDatas.size() : pageSize);
        } else if (targetPage > totalPage) {
            currentPage = totalPage;
            targetDatas = totalDatas.subList((currentPage - 1) * pageSize, totalDatas.size());
        } else {
            currentPage = targetPage;
            targetDatas = totalDatas.subList((currentPage - 1) * pageSize, currentPage == totalPage ? totalDatas.size() : currentPage * pageSize);
        }
        return new Object[]{currentPage, totalPage, targetDatas};
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
