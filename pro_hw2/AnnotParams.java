package pro_hw2;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotParams {

    public static void main(String[] args) {
//        1. Создать аннотацию, которая принимает параметры для метода. Написать код, который
//вызовет метод, помеченный этой аннотацией, и передаст параметры аннотации в
//вызываемый метод.
//@Test(a=2, b=5)
//public void test(int a, int b) {…}

        Class<?> cls = AnnotParams.class;
        try {
            Method print = Arrays.stream(cls.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(Nofx.class))
                    .findFirst()
                    .orElseThrow(ReflectiveOperationException::new);;
            Nofx annotation = print.getAnnotation(Nofx.class);
            print.invoke(null, annotation.drublic(), annotation.linoleum());
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    @Nofx(drublic = "Fat Mike + ", linoleum = "Lori Meyers")
    public static void print(String drublic, String linoleum){
        System.out.println(drublic + linoleum);
    }

    public static void print(int drublic, int linoleum){
        System.out.println(drublic + linoleum);
    }

}
