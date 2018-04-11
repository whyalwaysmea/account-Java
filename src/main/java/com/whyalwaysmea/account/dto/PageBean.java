package com.whyalwaysmea.account.dto;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Long
 * @Date: Create in 17:00 2018/4/11
 * @Description:
 */
@ApiModel("分页模型")
@Data
public class PageBean<T> implements Serializable {

    @ApiModelProperty("总的数据条数")
    private long total;

    @ApiModelProperty("数据结果集")
    private List<T> list;

    @ApiModelProperty("是否是最后一页")
    private boolean isLastPage;

    @ApiModelProperty("总的页数")
    private int pages;

    @ApiModelProperty("当前页码")
    private int pageNum;

    @ApiModelProperty("当前每页条数")
    private int pageSize;

    public PageBean(List<T> list) {
        if(list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.total = page.getTotal();
            this.pages = page.getPages();
            this.list = page.getResult();
            if(pageNum < pages) {
                this.isLastPage = false;
            } else {
                this.isLastPage = true;
            }
        }
    }

    public static <T> PageBean<T> data(List<T> list) {
        return new PageBean<>(list);
    }
}
