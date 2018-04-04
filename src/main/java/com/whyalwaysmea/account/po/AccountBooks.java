package com.whyalwaysmea.account.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "account_books")
public class AccountBooks {
    /**
     * 账本id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账本名称
     */
    private String name;

    /**
     * 账本封面url
     */
    @Column(name = "cover_img")
    private String coverImg;

    /**
     * 账本拥有者
     */
    @Column(name = "owner_id")
    private String ownerId;

    /**
     * 账本创建者
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 是否是默认账本
     */
    @Column(name = "default_book")
    private String defaultBook;

    /**
     * 预算金额（分）
     */
    @Column(name = "budgetary_amount")
    private Integer budgetaryAmount;

    /**
     * 剩余预算金额（分）
     */
    @Column(name = "surplus_budgetary_amount")
    private Integer surplusBudgetaryAmount;

    /**
     * 账本类型  1：个人账本 2：多人账本
     */
    @Column(name = "book_type")
    private Byte bookType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后记账时间
     */
    @Column(name = "last_account_time")
    private Date lastAccountTime;

    /**
     * 是否被删除， 1被删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    /**
     * 获取账本id
     *
     * @return id - 账本id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账本id
     *
     * @param id 账本id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取账本名称
     *
     * @return name - 账本名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置账本名称
     *
     * @param name 账本名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取账本封面url
     *
     * @return cover_img - 账本封面url
     */
    public String getCoverImg() {
        return coverImg;
    }

    /**
     * 设置账本封面url
     *
     * @param coverImg 账本封面url
     */
    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    /**
     * 获取账本拥有者
     *
     * @return owner_id - 账本拥有者
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * 设置账本拥有者
     *
     * @param ownerId 账本拥有者
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取账本创建者
     *
     * @return creator_id - 账本创建者
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置账本创建者
     *
     * @param creatorId 账本创建者
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取是否是默认账本
     *
     * @return default_book - 是否是默认账本
     */
    public String getDefaultBook() {
        return defaultBook;
    }

    /**
     * 设置是否是默认账本
     *
     * @param defaultBook 是否是默认账本
     */
    public void setDefaultBook(String defaultBook) {
        this.defaultBook = defaultBook;
    }

    /**
     * 获取预算金额（分）
     *
     * @return budgetary_amount - 预算金额（分）
     */
    public Integer getBudgetaryAmount() {
        return budgetaryAmount;
    }

    /**
     * 设置预算金额（分）
     *
     * @param budgetaryAmount 预算金额（分）
     */
    public void setBudgetaryAmount(Integer budgetaryAmount) {
        this.budgetaryAmount = budgetaryAmount;
    }

    /**
     * 获取剩余预算金额（分）
     *
     * @return surplus_budgetary_amount - 剩余预算金额（分）
     */
    public Integer getSurplusBudgetaryAmount() {
        return surplusBudgetaryAmount;
    }

    /**
     * 设置剩余预算金额（分）
     *
     * @param surplusBudgetaryAmount 剩余预算金额（分）
     */
    public void setSurplusBudgetaryAmount(Integer surplusBudgetaryAmount) {
        this.surplusBudgetaryAmount = surplusBudgetaryAmount;
    }

    /**
     * 获取账本类型  1：个人账本 2：多人账本
     *
     * @return book_type - 账本类型  1：个人账本 2：多人账本
     */
    public Byte getBookType() {
        return bookType;
    }

    /**
     * 设置账本类型  1：个人账本 2：多人账本
     *
     * @param bookType 账本类型  1：个人账本 2：多人账本
     */
    public void setBookType(Byte bookType) {
        this.bookType = bookType;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后记账时间
     *
     * @return last_account_time - 最后记账时间
     */
    public Date getLastAccountTime() {
        return lastAccountTime;
    }

    /**
     * 设置最后记账时间
     *
     * @param lastAccountTime 最后记账时间
     */
    public void setLastAccountTime(Date lastAccountTime) {
        this.lastAccountTime = lastAccountTime;
    }

    /**
     * 获取是否被删除， 1被删除
     *
     * @return is_delete - 是否被删除， 1被删除
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否被删除， 1被删除
     *
     * @param isDelete 是否被删除， 1被删除
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}