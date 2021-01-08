package oopHomeWork8.stack;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class BlackStack implements Stack {
    private static final int DEFAULT_CAPACITY = 4;

    private Object[] elms = new Object[DEFAULT_CAPACITY];
    private int topIndex = -1;
    private BlackList blackList = new BlackList();

    public BlackStack(BlackList blackList) {
        this.blackList = blackList;
    }

    public BlackStack() {
    }

    @Override
    public Object pop() {
        if (isEmpty())
            return null;
        Object result = elms[topIndex];
        elms[topIndex] = null;
        topIndex--;
        return result;
    }

    @Override
    public Object element() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException("Stack is empty!");
        return elms[topIndex];
    }

    @Override
    public Object peek() {
        if (isEmpty())
            return null;
        return elms[topIndex];
    }

    @Override
    public void push(Object o) {
        if (blackList.isInBlackList(o))
            throw new IllegalArgumentException(
                    o.getClass() + " is in black list! Impossible to add it to this stack");
        ensureCapacity();
        elms[++topIndex] = o;
    }

    private void ensureCapacity() {
        if (topIndex == elms.length - 1)
            grow();
    }

    private void grow() {
        int newCapacity = elms.length * 2;
        elms = Arrays.copyOf(elms, newCapacity);
    }

    @Override
    public boolean isEmpty() {
        return topIndex == -1;
    }

    @Override
    public int size() {
        return topIndex + 1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < topIndex + 1; i++) {
            elms[i] = null;
        }
        topIndex = -1;
    }

    public BlackList getBlackList() {
        return blackList;
    }

    public void setBlackList(BlackList bl) {
        this.blackList = bl;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "elements=" + Arrays.toString(elms) +
                '}';
    }
}
