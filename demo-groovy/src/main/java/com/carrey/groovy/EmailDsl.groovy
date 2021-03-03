package com.carrey.groovy

class EmailDsl {
    String toText
    String fromText
    String body

    /**
     * 该方法接受闭包，闭包本质上是DSL。将闭包方法委托给DSL类，以便处理调用
     * @param closure
     * @return
     */
    static def make(closure) {
        EmailDsl emailDsl = new EmailDsl()
        // 闭包中调用的任何方法都将委托给EmailDsl类
        closure.delegate = emailDsl
        // 执行闭包方法
        closure()
    }

    /**
     * Store the parameter as a variable and use it later to output a memo
     */

    def to(String toText) {
        this.toText = toText
    }

    def from(String fromText) {
        this.fromText = fromText
    }

    def body(String bodyText) {
        this.body = bodyText
    }

}

