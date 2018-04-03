package com.whyalwaysmea.account.po;

import javax.persistence.*;

@Table(name = "income_type")
public class IncomeType {
    /**
     * 分类id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类父id
     */
    private Long pid;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类创建者， -1表示所有人都有
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 分类icon url
     */
    @Column(name = "icon_url")
    private String iconUrl;

    /**
     * 获取分类id
     *
     * @return id - 分类id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置分类id
     *
     * @param id 分类id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取分类父id
     *
     * @return pid - 分类父id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置分类父id
     *
     * @param pid 分类父id
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取分类创建者， -1表示所有人都有
     *
     * @return creator_id - 分类创建者， -1表示所有人都有
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置分类创建者， -1表示所有人都有
     *
     * @param creatorId 分类创建者， -1表示所有人都有
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取分类icon url
     *
     * @return icon_url - 分类icon url
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * 设置分类icon url
     *
     * @param iconUrl 分类icon url
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}