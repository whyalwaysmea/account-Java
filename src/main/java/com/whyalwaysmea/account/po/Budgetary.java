package com.whyalwaysmea.account.po;

import lombok.Data;

import javax.persistence.*;

@Table(name = "budgetary")
@Data
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


}