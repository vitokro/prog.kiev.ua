package pro_hw2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;

public class Saver {

    public static void main(String[] args) {
//        2*. Написать класс TextContainer, который содержит в себе строку. С помощью механизма
//аннотаций указать 1) в какой файл должен сохраниться текст 2) метод, который выполнит
//сохранение. Написать класс Saver, который сохранит поле класса TextContainer в указанный
//файл.
//@SaveTo(path=“c:\\file.txt”)
//class Container {
//String text = “…”;
//@Saver
//public void save(..) {…}
//}
        try {
            Saver.save();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    public static void save() throws ReflectiveOperationException {
        Class<?> cls = TextContainer.class;
        SaveTo saveTo = cls.getAnnotation(SaveTo.class);
        Method saverMethod = Arrays.stream(cls.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(SaverMethod.class))
                .findFirst()
                .orElseThrow(ReflectiveOperationException::new);
        saverMethod.setAccessible(true);
        saverMethod.invoke(new TextContainer("хак!"), saveTo.path());
    }
}
