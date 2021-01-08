package oopHomeWork8.stack;

import java.util.Arrays;
import java.util.Objects;

public class BlackList {
    private static final int DEFAULT_CAPACITY = 4;
    private Class<?>[] clss = new Class<?>[DEFAULT_CAPACITY];
    private int size = 0;

    void add(Class<?> cls){
        if (!isInBlackList(cls)){
            ensureCapacity();
            clss[size++] = cls;
        }
    }

    void add(Object o){
        add(o.getClass());
    }

    boolean isInBlackList(Object o){
        return isInBlackList(o.getClass());
    }

    boolean isInBlackList(Class<?> cls){
        for (int i = 0; i < size; i++) {
            if (Objects.equals(cls, clss[i]))
                return true;
        }
        return false;
    }

    void remove(Class<?> cls){
        for (int i = 0; i < size; i++) {
            if (Objects.equals(cls, clss[i])) {
                int j = i;
                for (; j < size - 1; j++) {
                    clss[j] = clss[j + 1];
                }
                clss[j] = null;
                size--;
                break;
            }
        }
    }

    void remove(Object o ){
        remove(o.getClass());
    }

    int getSize(){
        return size;
    }

    void clear(){
        for (int i = 0; i < size; i++) {
            clss[i] = null;
        }
        size = 0;
    }
    private void ensureCapacity() {
        if (size == clss.length)
            grow();
    }

    private void grow() {
        int newCapacity = clss.length * 2;
        clss = Arrays.copyOf(clss, newCapacity);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(clss[i]).append("\n");
        }
        return "BlackList{" +
                "classes in black list:\n" + sb +
                '}';
    }
}
