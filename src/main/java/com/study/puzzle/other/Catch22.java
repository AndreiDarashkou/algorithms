package com.study.puzzle.other;

import javassist.*;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;


public class Catch22 {

    public static void main(String[] args) throws Throwable {
        Yossarian yossarian = Catch22.loophole();
        if (!yossarian.isCrazy()) {
            System.out.println("!!!!");
        } else {
            System.out.println("++++");
        }
    }

    public static Yossarian loophole() throws Throwable {

        class InnerYossarian extends Yossarian {

        }

//        Method m = Yossarian.class.getMethod("isCrazy");
//        m.setAccessible(true);
//
//
//        Class<?> userClass1 = Yossarian.class;
//        Class<?> userClass2 = new DynamicClassLoader("target/classes")
//                .load("qj.blog.classreloading.example1.StaticInt$User");

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath ccpath = new ClassClassPath(InnerYossarian.class);
        pool.insertClassPath(ccpath);
        CtClass ctClass = pool.get("com.study.puzzle.other.Catch22$1InnerYossarian");

        CtMethod originalMethod = ctClass.getMethods()[5];
        originalMethod.setModifiers(Modifier.PUBLIC);
        ctClass.toClass();
        //ctClass.writeFile();


//
//        ctClass.removeMethod(originalMethod);
//
//        CtMethod overrideMethod = CtNewMethod.make("public String isCrazy() { return true; }", ctClass);
////
//        ctClass.addMethod(overrideMethod);
//        ctClass.toClass();
//
        return new InnerYossarian();
    }

    public static class A {

    }
}
