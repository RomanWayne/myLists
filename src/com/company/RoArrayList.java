package com.company;


public class RoArrayList<T> implements List<T> {

    private Object[] arrayOfElements;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private final int FACTOR_GROW = 2;

    public RoArrayList() {
        arrayOfElements = new Object[DEFAULT_CAPACITY];
    }

    public RoArrayList(int capacity){
        if (capacity >= 0){
            arrayOfElements = new Object[capacity];
        } else {
            throw new IllegalArgumentException("Нельзя создать список с отрицательным размером");
        }
    }

    public RoArrayList(Object[] val) {
        arrayOfElements = new Object[val.length];
        System.arraycopy(val, 0, arrayOfElements, 0, val.length);
        size = val.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Запрашиваемый индекс выходит за пределы списка");
        } else {
            return (T) arrayOfElements[index]; //Непонятно, что делать если null. ведь это по сути такой же объект
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (o.equals(arrayOfElements[i])) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            System.arraycopy(arrayOfElements, index + 1, arrayOfElements, index, size - index - 1);
            size--;
            arrayOfElements[size - 1] = null;
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, Object element) {
        //правило: добавляем либо в к конец списка, либо вместо существущего со сдвигом
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Добавление возможно по индексу от 0 до " + size);
        }
        //проверка,что хватит массива
        if (size + 1 > arrayOfElements.length){
            Object[] tmpArray = new Object[arrayOfElements.length * FACTOR_GROW];
            System.arraycopy(arrayOfElements,0, tmpArray,0, arrayOfElements.length);
            arrayOfElements = tmpArray;
        }
        System.arraycopy(arrayOfElements, index, arrayOfElements, index + 1, size - index);
        arrayOfElements[index] = (T) element;
        size++;
    }

    @Override
    public void clear() {
        arrayOfElements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o != null){
            for (int i = 0; i < size; i++) {
                if (o.equals(arrayOfElements[i])) {
                    return i;
                }
            }
        } else{
            for (int i = 0; i < size; i++) {
                if (arrayOfElements[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayOfElements[i]).append(", ");
        }
        return sb.toString();
    }
}
