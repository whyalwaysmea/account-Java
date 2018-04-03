package com.whyalwaysmea.account.po;

import java.util.Date;
import javax.persistence.*;

@Table(name = "account_record")
public class AccountRecord {
    /**
     * 账本记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账本id
     */
    @Column(name = "book_id")
    private Long bookId;

    /**
     * 金额
     */
    private Integer amount;

    /**
     * 收入(1)or支出(2)
     */
    @Column(name = "record_type")
    private Byte recordType;

    /**
     * 主要分类
     */
    @Column(name = "main_type")
    private Long mainType;

    /**
     * 次要分类
     */
    @Column(name = "secondary_type")
    private Long secondaryType;

    /**
     * 收支途径
     */
    @Column(name = "pay_income_way")
    private Long payIncomeWay;

    /**
     * 创建者
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 记录关联日期
     */
    @Column(name = "record_time")
    private Date recordTime;

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
     * 备注
     */
    private String remark;

    /**
     * 获取账本记录id
     *
     * @return id - 账本记录id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置账本记录id
     *
     * @param id 账本记录id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取账本id
     *
     * @return book_id - 账本id
     */
    public Long getBookId() {
        return bookId;
    }

    /**
     * 设置账本id
     *
     * @param bookId 账本id
     */
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    /**
     * 获取金额
     *
     * @return amount - 金额
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置金额
     *
     * @param amount 金额
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取收入(1)or支出(2)
     *
     * @return record_type - 收入(1)or支出(2)
     */
    public Byte getRecordType() {
        return recordType;
    }

    /**
     * 设置收入(1)or支出(2)
     *
     * @param recordType 收入(1)or支出(2)
     */
    public void setRecordType(Byte recordType) {
        this.recordType = recordType;
    }

    /**
     * 获取主要分类
     *
     * @return main_type - 主要分类
     */
    public Long getMainType() {
        return mainType;
    }

    /**
     * 设置主要分类
     *
     * @param mainType 主要分类
     */
    public void setMainType(Long mainType) {
        this.mainType = mainType;
    }

    /**
     * 获取次要分类
     *
     * @return secondary_type - 次要分类
     */
    public Long getSecondaryType() {
        return secondaryType;
    }

    /**
     * 设置次要分类
     *
     * @param secondaryType 次要分类
     */
    public void setSecondaryType(Long secondaryType) {
        this.secondaryType = secondaryType;
    }

    /**
     * 获取收支途径
     *
     * @return pay_income_way - 收支途径
     */
    public Long getPayIncomeWay() {
        return payIncomeWay;
    }

    /**
     * 设置收支途径
     *
     * @param payIncomeWay 收支途径
     */
    public void setPayIncomeWay(Long payIncomeWay) {
        this.payIncomeWay = payIncomeWay;
    }

    /**
     * 获取创建者
     *
     * @return creator_id - 创建者
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建者
     *
     * @param creatorId 创建者
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取记录关联日期
     *
     * @return record_time - 记录关联日期
     */
    public Date getRecordTime() {
        return recordTime;
    }

    /**
     * 设置记录关联日期
     *
     * @param recordTime 记录关联日期
     */
    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
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
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}