package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.parameters.WaysParam;
import com.whyalwaysmea.account.po.PayIncomeWays;
import com.whyalwaysmea.account.service.WaysService;
import com.whyalwaysmea.account.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/15 20:13
 * @Description:
 */
@Api(description = "收支途径")
@RestController
@RequestMapping("/way")
public class WaysController extends BaseController {

    @Autowired
    private WaysService waysService;

    @ApiOperation("新增收支途径")
    @PostMapping
    public ExecuteResult<PayIncomeWays> addWays(@RequestBody WaysParam waysParam) {
        return ExecuteResult.ok();
    }

    @ApiModelProperty("更新收支途径")
    @PutMapping("/{id:\\d+}")
    public ExecuteResult<PayIncomeWays> updateWays(@PathVariable("id") @ApiParam("条目id") long id, @RequestBody WaysParam waysParam) {
        return ExecuteResult.ok();
    }

    @ApiModelProperty("删除收支途径")
    @DeleteMapping("/{id:\\d+}")
    public ExecuteResult<Boolean> delWays(@PathVariable("id") @ApiParam("条目id") long id) {
        return ExecuteResult.ok();
    }


    @ApiModelProperty("获取所有的收支途径")
    @GetMapping("/list")
    public ExecuteResult<List<PayIncomeWays>> getAllWays() {
        String userId = UserUtils.getCurrentUserId();

        return ExecuteResult.ok(waysService.getUserAllWays(userId));
    }


}
