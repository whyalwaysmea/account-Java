package com.whyalwaysmea.account.controller;

import com.whyalwaysmea.account.controller.common.BaseController;
import com.whyalwaysmea.account.dto.ExecuteResult;
import com.whyalwaysmea.account.enums.CommonError;
import com.whyalwaysmea.account.exception.MyException;
import com.whyalwaysmea.account.parameters.AccountBookParam;
import com.whyalwaysmea.account.po.AccountBook;
import com.whyalwaysmea.account.service.AccountBookService;
import com.whyalwaysmea.account.service.BookParterService;
import com.whyalwaysmea.account.vo.AccountBookDetails;
import com.whyalwaysmea.account.vo.AccountBookPartersVO;
import com.whyalwaysmea.account.vo.BookListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    private BookParterService bookParterService;

    @ApiOperation("新增账本")
    @PostMapping()
    public ExecuteResult<AccountBook> addAccountBook(@RequestBody AccountBookParam accountBookParam) {
        AccountBook accountBook = accountBookService.addAccountBook(accountBookParam);
        return ExecuteResult.ok(accountBook);
    }

    @ApiOperation("删除账本")
    @DeleteMapping("/{id:\\d+}")
    public ExecuteResult<Boolean> delAccountBook(@PathVariable("id") @ApiParam("账本id") long id) {
        boolean result = accountBookService.delAccountBook(id);
        return ExecuteResult.ok(result);
    }

    @ApiOperation("更新账本")
    @PutMapping("/{id:\\d+}")
    public ExecuteResult<AccountBook> updateAccountBook(@PathVariable("id") @ApiParam("账本id") long id,
                                                        @RequestBody @Validated AccountBookParam accountBookParam) {
        accountBookParam.setId(id);
        AccountBook accountBook = accountBookService.updateAccountBook(accountBookParam);
        return ExecuteResult.ok(accountBook);
    }


    @ApiOperation("获取账本详情")
    @GetMapping("/{id:\\d+}")
    public ExecuteResult<AccountBookDetails> getAccountBook(@PathVariable("id") @ApiParam("账本id") long id) {
        AccountBookDetails accountBook = accountBookService.getAccountBookDetails(id);
        return ExecuteResult.ok(accountBook);
    }

    @GetMapping("/all")
    @ApiOperation("获取所有的账本信息")
    public ExecuteResult<List<BookListVO>> getAllAccountBook() {
        List<BookListVO> allAccountBook = accountBookService.getAllAccountBook();
        return ExecuteResult.ok(allAccountBook);
    }

    @ApiOperation("加入账本")
    @PostMapping("/parter/{bookId:\\d+}")
    public ExecuteResult<Integer> joinAccountBook(@PathVariable("bookId") @ApiParam("账本id") long bookId) {
        int result = bookParterService.joinAccountBook(bookId);
        return ExecuteResult.ok(result);
    }

    @ApiOperation("移除账本参与者")
    @DeleteMapping("/parter/{bookId:\\d+}")
    public ExecuteResult<Boolean> removeParters(@RequestParam("ids") @ApiParam("要移除的用户id， 多人用,分割") String ids,
                                                @PathVariable("bookId") @ApiParam("账本id") long bookId) {
        String[] idList = ids.split(",");
        if(ArrayUtils.isEmpty(idList)) {
            throw new MyException(CommonError.ERROR_PARAMTER);
        }
        for (String id : idList) {
            bookParterService.removeParter(id, bookId);
        }


        return ExecuteResult.ok(true);
    }

    @ApiOperation("获取账本的参与者")
    @GetMapping("/parter/{bookId:\\d+}")
    public ExecuteResult<List<AccountBookPartersVO>> getBookParters(@PathVariable("bookId") @ApiParam("账本id") long bookId) {
        List<AccountBookPartersVO> parters = bookParterService.findAllByBookId(bookId);
        return ExecuteResult.ok(parters);
    }

}
