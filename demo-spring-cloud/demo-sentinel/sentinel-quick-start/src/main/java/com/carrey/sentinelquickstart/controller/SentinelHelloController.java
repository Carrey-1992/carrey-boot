package com.carrey.sentinelquickstart.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.carrey.sentinelquickstart.service.SentinelHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Carrey
 * @className SentinelHelloController
 * @description SentinelHelloController
 * @date 2021/4/24 7:59 下午
 */
@RestController
@RequestMapping("/sentinel/quick-start")
public class SentinelHelloController {

    @Autowired
    private SentinelHelloService sentinelHelloService;

    /**
     * 初始化流控规则
     */
    @PostConstruct
    public void init() {
        List<FlowRule> flowRules = new ArrayList<>();

        /**
         * 定义 helloSentinelV1 受保护的资源的规则
         */
        //创建流控规则对象
        FlowRule flowRule = new FlowRule();
        //设置流控规则 QPS
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源
        flowRule.setResource("helloSentinelV1");
        //设置受保护的资源的阈值
        flowRule.setCount(1);



        /**
         * 定义 helloSentinelV2 受保护的资源的规则
         */
        //创建流控规则对象
        FlowRule flowRule2 = new FlowRule();
        //设置流控规则 QPS
        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源
        flowRule2.setResource("helloSentinelV2");
        //设置受保护的资源的阈值
        flowRule2.setCount(1);


        /**
         * 定义 helloSentinelV3 受保护的资源的规则
         */
        //创建流控规则对象
        FlowRule flowRule3 = new FlowRule();
        //设置流控规则 QPS
        flowRule3.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源
        flowRule3.setResource("helloSentinelV3");
        //设置受保护的资源的阈值
        flowRule3.setCount(1);



        flowRules.add(flowRule);
        flowRules.add(flowRule2);
        flowRules.add(flowRule3);

        //加载配置好的规则
        FlowRuleManager.loadRules(flowRules);

        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        degradeRule.setCount(1);
        degradeRule.setTimeWindow(10);
        degradeRule.setResource("testRt");

        DegradeRuleManager.loadRules(Arrays.asList(degradeRule));
    }

    @GetMapping("/helloSentinelV1")
    public String helloSentinelV1() {
        Entry entry = null;
        //关联受保护的资源
        try {
            entry = SphU.entry("helloSentinelV1");
            //开始执行业务逻辑
            sentinelHelloService.doBusi();
        } catch (BlockException e) {
            return "helloSentinelV1被流控了";
        } finally {
            if (entry!=null) {
                entry.exit();
            }
        }
        return "hello sentinel";
    }

}
