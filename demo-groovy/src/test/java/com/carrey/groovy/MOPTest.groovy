package com.carrey.groovy

import org.junit.Test

class MOPTest{

    @Test
     void testInterceptedMethodCallonPOJO() {
        def val = new Integer(3)
        Integer.metaClass.toString = {-> 'intercepted'}

        assert val.toString(), "intercepted"
    }

    @Test
    void testInterceptableCalled() {
        def obj = new AnInterCeptable()

        assert 'intercepted',obj.existingMethod()
        assert 'intercepted',obj.nonExistingMethod()
    }

    @Test
    void testInterceptedExistingMethodCalled() {
        AGroovyObject.metaClass.existingMethod2 = {-> 'intercepted'}

        def obj = new AGroovyObject()

        assert 'intercepted',obj.existingMethod2()
    }

    @Test
    void testUnInterceptedExistingMethodCalled(){
        def obj = new AGroovyObject()
        assert 'existingMethod',obj.existingMethod()
    }

    @Test
    void testPropertyThatIsClosureCalled() {
        def obj = new AGroovyObject()
        assert  'existingMethod',obj.existingMethod()
    }

    @Test
    void testMethodMissingCalledOnlyForNonExistent() {
        def obj = new ClassWithInvokeAndMissingMethod()

        assert 'existingMethod',obj.existingMethod()
        assert 'missing called',obj.nonExistingMethod()
    }

    @Test
    void testInvokeMethodCalledForOnlyNonExistent() {
        def obj = new ClassWithInvokeOnly()
        assert 'existingMethod',obj.existingMethod()
        assert 'invoke called',obj.nonExistingMethod()
    }


    class AnInterCeptable implements GroovyInterceptable {
        def existingMethod() {}
        def invokeMethod(String name,args) {}
    }

    class AGroovyObject {
        def existingMethod() {'existingMethod'}
        def existingMethod2() {'existingMethod2'}
        def closureProp = {'closure called'}
    }

    class ClassWithInvokeAndMissingMethod {
        def existingMethod() {'existingMethod'}
        def invokemethod(String name,args) {'invoke called'}
        def methodMissing(String name,args) {'missing called'}
    }

    class ClassWithInvokeOnly {
        def existingMethod() {'existingMethod'}
        def invokeMethod(String name,args) {'invoke called'}
    }
}
