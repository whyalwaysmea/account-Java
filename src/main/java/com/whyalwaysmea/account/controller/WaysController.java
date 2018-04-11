package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.parameters.IncomeAndExpenditureTypeParam;
import com.whyalwaysmea.account.po.ExpenditureType;
import com.whyalwaysmea.account.service.WaysService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Long
 * @Date: Create in 17:29 2018/4/10
 * @Description:
 */
@RestController
@Api(description = "收支分类，途径")
public class WaysController extends BaseController {

    @Autowired
    private WaysService incomeAndExpenditureTypeService;

    @ApiOperation("添加新的支出分类")
    @PostMapping("/expenditure")
    public ExecuteResult<ExpenditureType> addExpenditureType(@RequestBody IncomeAndExpenditureTypeParam param, BindingResult result) {
        checkParam(result);
        ExpenditureType expenditureType = incomeAndExpenditureTypeService.addExpenditureType(param);
        return ExecuteResult.ok(expenditureType);
    }

    @ApiOperation("更新支出分类")
    @PutMapping("/expenditure")
    public ExecuteResult<ExpenditureType> updateExpenditureType(@RequestBody IncomeAndExpenditureTypeParam param, BindingResult result) {
        checkParam(result);
        ExpenditureType expenditureType = incomeAndExpenditureTypeService.updateExpenditureType(param);
        return ExecuteResult.ok(expenditureType);
    }

    @ApiOperation("删除支出分类条目")
    @DeleteMapping("/expenditure/{id}")
    public ExecuteResult<Boolean> deleteExpenditureType(@PathVariable("id") @ApiParam("条目id") Integer id) {
        boolean result = incomeAndExpenditureTypeService.deleteExpenditureType(id);
        return ExecuteResult.ok(result);
    }
}
