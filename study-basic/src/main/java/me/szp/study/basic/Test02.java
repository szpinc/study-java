package me.szp.study.basic;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @author sunzp
 * @since 2022/5/5 11:44
 */
public class Test02 {

    class Demo {

    }

    static <T> String getClassName (Class<T> tClass)  {
        Type type = tClass.getGenericSuperclass();
        for (TypeVariable<Class<T>> typeParameter : tClass.getTypeParameters()) {
            System.out.println(typeParameter.getName());
        }
        return type.getTypeName();
    }

    public static void main(String[] args) {
        System.out.println(getClassName(Demo.class));
    }


}
