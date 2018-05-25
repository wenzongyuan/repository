package com.wzy.shiro.util;

public class PagePagination {
    // 页大小 (每页显示记录条数)
    private Integer maxRows;

    // 页索引 (当前页数)
    private Integer pageIndex;

    // 总页数
    private Integer pageTotalCount;

    // 总条数
    private Integer totalCount;

    // 起始位置
    private Integer startIndex;

    public PagePagination(String maxRows, String pageIndex) {
        this.maxRows = (StringUtils.isEmpty(maxRows) ? null : Integer.parseInt(maxRows));
        this.pageIndex = (StringUtils.isEmpty(pageIndex) ? null : Integer.parseInt(pageIndex));
        if (this.maxRows != null && this.pageIndex != null) {
            Integer temp = (this.pageIndex - 1) * this.maxRows;
            if (temp < 0) {
                temp = 0;
            }
            this.startIndex = temp;
            this.maxRows = Integer.parseInt(maxRows);
            this.pageIndex = Integer.parseInt(pageIndex);
        }
    }

    public PagePagination(String maxRows, String pageIndex, int totalCount) {
        this.maxRows = (StringUtils.isEmpty(maxRows) ? null : Integer.parseInt(maxRows));
        this.pageIndex = (StringUtils.isEmpty(pageIndex) ? null : Integer.parseInt(pageIndex));
        if (this.maxRows != null && this.pageIndex != null) {
            Integer temp = (this.pageIndex - 1) * this.maxRows;
            if (temp < 0) {
                temp = 0;
            }
            this.startIndex = temp;
            this.maxRows = Integer.parseInt(maxRows);
            this.pageIndex = Integer.parseInt(pageIndex);
            this.totalCount = totalCount;
            this.pageTotalCount = totalCount % this.maxRows == 0 ? totalCount / this.maxRows : totalCount / this.maxRows + 1;
        }
    }

    public PagePagination(Integer maxRows, Integer pageIndex) {
        // 若全不为空，则计算出起始位置
        if (ParamNotNullJudge.judgeAllNotNull(maxRows, pageIndex)) {

            Integer temp = (pageIndex - 1) * maxRows;
            // 判断页码是否小于第一页
            if (temp < 0) {
                // 小于第一页一律设置为第一页
                temp = 0;
            } else {
                this.startIndex = temp;
                this.maxRows = maxRows;
            }
            // 否则将两个参数都置空
        } else {
            this.startIndex = null;
            this.maxRows = null;
        }
    }

    public Integer getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(Integer maxRows) {
        this.maxRows = maxRows;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

}
