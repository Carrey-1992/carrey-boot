package com.example.carrey.domain;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Conway
 * @className MyTestImportSelector
 * @description
 * @date 2020/11/10 9:53 下午
 */
public class MyTestImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.example.carrey.domain.MyTestImport2"};
    }
}
