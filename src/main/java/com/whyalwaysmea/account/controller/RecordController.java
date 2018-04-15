package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.parameters.PageParam;
import com.whyalwaysmea.account.parameters.RecordParam;
import com.whyalwaysmea.account.po.AccountRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: whyalwaysmea
 * @Date: Create in 2018/4/15 20:20
 * @Description:
 */
@Api(description = "收支记录")
@RestController
@RequestMapping("/record")
public class RecordController {

    @ApiOperation("新增收支记录")
    @PostMapping
    public ExecuteResult<AccountRecord> addRecord(@RequestBody RecordParam recordParam) {
        return ExecuteResult.ok();
    }

    @ApiOperation("更新收支记录")
    @PutMapping("/id:\\d+")
    public ExecuteResult<AccountRecord> updateRecord(@PathVariable("id") @ApiParam("记录id") long id, @RequestBody RecordParam recordParam) {
        return ExecuteResult.ok();
    }

    @ApiOperation("更新收支记录")
    @DeleteMapping("/id:\\d+")
    public ExecuteResult<Boolean> delRecord(@PathVariable("id") @ApiParam("记录id") long id) {
        return ExecuteResult.ok();
    }

    @ApiOperation("获取收支记录列表")
    @GetMapping("/list")
    public ExecuteResult<PageBean<AccountRecord>> getRecords(PageParam pageParam) {
        return ExecuteResult.ok();
    }

}
