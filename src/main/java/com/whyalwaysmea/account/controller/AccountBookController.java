package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.dto.PageBean;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.parameters.PageParam;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.service.AccountBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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

    @ApiOperation("更新账本")
    @PutMapping("/{id:\\d+}")
    public ExecuteResult<AccountBook> updateAccountBook(@PathVariable("id") @ApiParam("账本id") long id,
                                                        @RequestBody AccountBookParam accountBookParam,
                                                        BindingResult bindResult) {
        checkParam(bindResult);
        accountBookParam.setId(id);
        AccountBook accountBook = accountBookService.updateAccountBook(accountBookParam);
        return ExecuteResult.ok(accountBook);
    }


    @ApiOperation("获取账本详情")
    @GetMapping("/{id:\\d+}")
    public ExecuteResult<AccountBook> getAccountBook(@PathVariable("id") @ApiParam("账本id") long id) {
        AccountBook accountBook = accountBookService.getAccountBook(id);
        return ExecuteResult.ok(accountBook);
    }

    @GetMapping("/all")
    @ApiOperation("获取所有的账本信息")
    public ExecuteResult<PageBean<AccountBook>> getAllAccountBook(PageParam pageParam) {
        PageBean<AccountBook> allAccountBook = accountBookService.getAllAccountBook(pageParam);
        return ExecuteResult.ok(allAccountBook);
    }

    @ApiOperation("加入账本")
    @PostMapping("/parter/{id:\\d+}")
    public ExecuteResult<Boolean> joinAccountBook(@PathVariable("id") @ApiParam("账本id") long id) {
        Boolean result = accountBookService.joinAccountBook(id);
        return ExecuteResult.ok(result);
    }

}
