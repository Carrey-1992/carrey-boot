package com.carrey.carrey.domain.bean;

import com.carrey.carrey.enums.BaseStringCodeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Carrey
 * @className AnyEnum
 * @description AnyEnum
 * @date 2021/9/16 4:16 下午
 */
public enum AnyStrategy implements ITest<Object>, BaseStringCodeEnum {
    A("a","策略a","ATest"){
        @Override
        public int getCount() {
            return AnyStrategyInstance.staticTestMap.get(this.getServiceBeanName()).getCount();
        }

        @Override
        public List<Object> getList() {
            return AnyStrategyInstance.staticTestMap.get(this.getServiceBeanName()).getList();
        }
    },
    B("b","策略b","BTest"){
        @Override
        public int getCount() {
            return AnyStrategyInstance.staticTestMap.get(this.getServiceBeanName()).getCount();
        }

        @Override
        public List<Object> getList() {
            return AnyStrategyInstance.staticTestMap.get(this.getServiceBeanName()).getList();
        }
    },
    C("c","策略c","CTest"){
        @Override
        public int getCount() {
            return AnyStrategyInstance.staticTestMap.get(this.getServiceBeanName()).getCount();
        }

        @Override
        public List<Object> getList() {
            return AnyStrategyInstance.staticTestMap.get(this.getServiceBeanName()).getList();
        }
    };

    private String strCode;

    private String desc;

    private String serviceBeanName;

    AnyStrategy(String strCode, String desc, String serviceBeanName) {
        this.strCode = strCode;
        this.desc = desc;
        this.serviceBeanName = serviceBeanName;
    }

    public String getServiceBeanName() {
        return serviceBeanName;
    }

    @Override
    public String getStrCode() {
        return strCode;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public abstract int getCount();

    @Override
    public abstract List<Object> getList();



    @Component
    public static class AnyStrategyInstance implements InitializingBean {

        @Autowired
        private Map<String,ITest> testMap;

        private static Map<String,ITest> staticTestMap;

        @Override
        public void afterPropertiesSet() throws Exception {
            staticTestMap = testMap;
        }

    }
}
