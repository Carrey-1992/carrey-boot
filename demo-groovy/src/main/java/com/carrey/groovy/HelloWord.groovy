package com.carrey.groovy

class HelloWord {
    static void main(String[] args) {
        HelloWord helloWord = new HelloWord()
        def x = helloWord.getX()
        def yIntergerList = []
        yIntergerList.add(1)
        for (def yInterger in yIntergerList) {
            println yInterger
        }

        def zMap = [:]
        zMap.put(1,"a")
        def values = zMap.values()

        for (def i in values) {
            println i
        }

        def iterator = values.iterator()

        if (iterator.hasNext()) {
            println iterator.next()
        }

        def set = zMap.entrySet()
        def iterator1 = set.iterator()


        def AMap = new HashMap();
        def values1 = AMap.values()

        println("Hello " + displayName("carrey",5))

        def z = {println "Hello World"}
        z.call();

        def m = {param -> println "Hello ${param}" + zMap}
        m.call("carrey")

        def lst = [1,2,3,4]
        println lst

        println EmailDsl.make {
            to "Nirav Assar"
            body "How are things? We are doing well. Take care"
            from "Barack Obama"
        }
    }

    static def displayName(a,b = 1,c = 2){
         b + c
    }

    private int x;

    int getX() {
        return x
    }

    abstract class B implements C {

    }

    trait C {

    }
}
