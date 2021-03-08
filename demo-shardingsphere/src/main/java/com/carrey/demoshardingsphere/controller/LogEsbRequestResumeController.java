package com.carrey.demoshardingsphere.controller;


import com.carrey.demoshardingsphere.po.LogEsbRequestResume;
import com.carrey.demoshardingsphere.service.ILogEsbRequestResumeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author carrey
 * @since 2021-03-05
 */
@RestController
@RequestMapping("/logEsbRequestResume")
public class LogEsbRequestResumeController {

    @Autowired
    private ILogEsbRequestResumeService logEsbRequestResumeService;

    @GetMapping("/page")
    public Page<LogEsbRequestResume> queryPageList(@RequestParam("userId") String userId,
                                               @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("pageNum") Integer pageNum) {
       return PageHelper.startPage(pageNum,pageSize)
                .doSelectPage(() -> logEsbRequestResumeService.queryList(userId));
    }

}
