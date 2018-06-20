package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.parameters.IncomeParam;
import com.whyalwaysmea.account.po.IncomeType;
import com.whyalwaysmea.account.service.IncomeService;
import com.whyalwaysmea.account.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/15 19:37
 * @Description:
 */
@Api(description = "收入")
@RequestMapping("/income")
@RestController
public class IncomeController extends BaseController {

    @Autowired
    private IncomeService incomeService;

    @ApiOperation("新增收入分类")
    @PostMapping
    public ExecuteResult<IncomeType> addIncome(@RequestBody IncomeParam incomeParam) {
        return ExecuteResult.ok();
    }

    @ApiOperation("删除指定条收入分类")
    @DeleteMapping("/{id:\\d+}")
    public ExecuteResult<Boolean> delIncome(@PathVariable("id") long id) {
        return ExecuteResult.ok();
    }

    @ApiOperation("更新收入分类")
    @PutMapping("/{id:\\d+}")
    public ExecuteResult<IncomeType> updateIncome(@PathVariable("id") @ApiParam("条目id") long id,
                                                  @RequestBody IncomeParam incomeParam,
                                                  BindingResult bindingResult) {
        checkParam(bindingResult);
        incomeParam.setId(id);
        return ExecuteResult.ok();
    }

    @ApiOperation("获取所有的收入分类")
    @GetMapping("/list")
    public ExecuteResult<List<IncomeType>> getAllIncome() {
        List<IncomeType> allIncomeType = incomeService.getAllIncomeType(UserUtils.getCurrentUserId());
        return ExecuteResult.ok(allIncomeType);
    }

    @ApiOperation("获取收入分类的详情以及子类")
    @GetMapping("/{pid:\\d+}")
    public ExecuteResult<IncomeType> getIncome(@PathVariable("pid") @ApiParam("条目id") long id) {
        return ExecuteResult.ok();
    }


}
