package com.carrey.consul.controller;

import com.carrey.consul.domain.IPUtil;
import com.carrey.consul.domain.IpModel;
import com.carrey.consul.domain.ResultModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Conway
 * @className ConsulServerRest
 * @description
 * @date 2021/4/7 5:57 下午
 */

@RestController
@RequestMapping("/consulApi")
public class ConsulServerRest {

    @GetMapping(value = "/ip")
    public ResultModel welCome(HttpServletRequest request) {
        IpModel ipModel = new IpModel();
        ipModel.setClientIpAddress(IPUtil.getIpAddr(request));
        ipModel.setServerIpAddress(IPUtil.localIp());
        return ResultModel.ok(ipModel);
    }

}
