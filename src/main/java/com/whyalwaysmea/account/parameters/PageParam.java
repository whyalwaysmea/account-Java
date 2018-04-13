package com.whyalwaysmea.account.parameters;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Long
 * @Date: Create in 15:55 2018/4/13
 * @Description:    分页请求
 */
@ApiModel("分页请求")
@Getter
@Setter
public class PageParam {

    @ApiModelProperty("第几页")
    private int page;

    @ApiModelProperty("每页行数")
    private int limit;

    @ApiModelProperty("排序字段")
    private String order;

    @ApiModelProperty("排序方向，ASC从小到大 DESC(默认值)：从大到小")
    private String direction;
}
