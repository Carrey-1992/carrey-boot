package com.carrey.nacosribbon.config;

import com.carrey.nacosribbon.ribbonconfig.GlobalRibbonConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

/**
 * Created by smlz on 2019/11/20.
 */
/*@Configuration
@RibbonClients(value = {
        @RibbonClient(name = "product-center",configuration = ProductCenterRibbonConfig.class),
        @RibbonClient(name = "pay-center",configuration = PayCenterRibbonConfig.class)
})*/
/**
 * ribbon的全局配置
 */
@RibbonClients(defaultConfiguration = GlobalRibbonConfig.class)

public class CustomRibbonConfig {

}
