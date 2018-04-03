package com.whyalwaysmea.account.po;

import javax.persistence.*;

public class Budgetary {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账本id
     */
    @Column(name = "account_book_id")
    private Long accountBookId;

    /**
     * 分类id
     */
    @Column(name = "expenditure_type_id")
    private Long expenditureTypeId;

    /**
     * 总预算金额（分）
     */
    @Column(name = "total_amount")
    private Integer totalAmount;

    /**
     * 剩余预算金额（分）
     */
    @Column(name = "surplus_amount")
    private Integer surplusAmount;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取账本id
     *
     * @return account_book_id - 账本id
     */
    public Long getAccountBookId() {
        return accountBookId;
    }

    /**
     * 设置账本id
     *
     * @param accountBookId 账本id
     */
    public void setAccountBookId(Long accountBookId) {
        this.accountBookId = accountBookId;
    }

    /**
     * 获取分类id
     *
     * @return expenditure_type_id - 分类id
     */
    public Long getExpenditureTypeId() {
        return expenditureTypeId;
    }

    /**
     * 设置分类id
     *
     * @param expenditureTypeId 分类id
     */
    public void setExpenditureTypeId(Long expenditureTypeId) {
        this.expenditureTypeId = expenditureTypeId;
    }

    /**
     * 获取总预算金额（分）
     *
     * @return total_amount - 总预算金额（分）
     */
    public Integer getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总预算金额（分）
     *
     * @param totalAmount 总预算金额（分）
     */
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取剩余预算金额（分）
     *
     * @return surplus_amount - 剩余预算金额（分）
     */
    public Integer getSurplusAmount() {
        return surplusAmount;
    }

    /**
     * 设置剩余预算金额（分）
     *
     * @param surplusAmount 剩余预算金额（分）
     */
    public void setSurplusAmount(Integer surplusAmount) {
        this.surplusAmount = surplusAmount;
    }
}