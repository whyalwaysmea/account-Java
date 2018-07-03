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

    private PageBean() {

    }

    public PageBean(List<T> list) {
        if(list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.total = page.getTotal();
            this.list = page.getResult();
        }
    }

    public static <T> PageBean<T> data(List<T> list) {
        if(list == null) {
            Page<T> objects = new Page<>();
            return new PageBean<T>(objects);
        }
        return new PageBean<>(list);
    }

    public static <T> PageBean<T> data(List<T> list, int count) {
        PageBean<T> pageBean = new PageBean<>();
        pageBean.setList(list);
        pageBean.setTotal(count);
        return pageBean;
    }
}
