package com.whyalwaysmea.account.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "expenditure_type")
@Data
@ApiModel("支出分类")
public class ExpenditureType implements Serializable {

    @Transient
    private static final long serialVersionUID = -5409588016640623359L;

    /**
     * 分类id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("分类id")
    private Long id;

    /**
     * 分类父id
     */
    @ApiModelProperty("分类父id")
    private Long pid;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名字")
    private String name;

    /**
     * 分类创建者
     */
    @Column(name = "creator_id")
    @JsonIgnore
    private String creatorId;

    /**
     * 分类icon url
     */
    @Column(name = "icon_url")
    @ApiModelProperty("未选择时icon的url")
    private String iconUrl;

    /**
     * 排序id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonIgnore
    private Date createTime;

    @Transient
    @ApiModelProperty("子分类")
    private List<ExpenditureType> childExpenditure;
}