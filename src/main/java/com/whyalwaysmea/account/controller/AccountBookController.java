package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.service.AccountBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Long
 * @Date: Create in 16:07 2018/4/13
 * @Description:
 */
@Api(description = "账本相关")
@RestController
@RequestMapping("/accountbook")
public class AccountBookController extends BaseController{

    @Autowired
    private AccountBookService accountBookService;

    @ApiOperation("新增账本")
    @PostMapping()
    public ExecuteResult<AccountBook> addAccountBook(@RequestBody AccountBookParam accountBookParam) {
        AccountBook accountBook = accountBookService.addAccountBook(accountBookParam);
        return ExecuteResult.ok(accountBook);
    }

    @ApiOperation("获取账本详情")
    @GetMapping("/{id}")
    public ExecuteResult<AccountBook> getAccountBook(@PathVariable("id") long id) {
        AccountBook accountBook = accountBookService.getAccountBook(id);
        return ExecuteResult.ok(accountBook);
    }
}
