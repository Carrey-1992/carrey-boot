package com.carrey.carrey.domain;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Conway
 * @className MyTestImportRegistrar
 * @description
 * @date 2020/11/10 9:54 下午
 */
public class MyTestImportRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(MyTestImport3.class);
        registry.registerBeanDefinition("myTestImport3",rootBeanDefinition);
    }
}
