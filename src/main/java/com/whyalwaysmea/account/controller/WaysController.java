package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.parameters.IncomeAndExpenditureTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.service.WaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Long
 * @Date: Create in 17:29 2018/4/10
 * @Description:
 */
@RestController
@Api(description = "收支分类，途径")
public class WaysController {

    @Autowired
    private WaysService incomeAndExpenditureTypeService;

    @ApiOperation("添加新的支出分类")
    @PostMapping("/expenditure")
    public ExecuteResult<ExpenditureType> addExpenditureType(@RequestBody IncomeAndExpenditureTypeParam param) {
        ExpenditureType expenditureType = incomeAndExpenditureTypeService.addExpenditureType(param);
        return ExecuteResult.ok(expenditureType);
    }
}
