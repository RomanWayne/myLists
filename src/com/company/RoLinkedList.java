package com.company;

import java.util.Objects;

public class RoLinkedList<T> implements List<T>{

    private static class Node<T>{
        private T element;
        private Node<T> prev;
        private Node<T> next;

        Node(T element, Node<T> prev, Node<T> next){
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            //if (o == null || getClass() != o.getClass()) return false;
            //Node<?> node = (Node<?>) o;

            if (this.element == null) {
                return o == null;
            }
            if (o == null || this.element.getClass() != o.getClass()) return false;
            return element.equals(o);
        }

        @Override
        public int hashCode() {
            return Objects.hash(element);
        }

        public String toString(){
            if (this.element != null) {
                return element.toString();
            } else {
                return "null";
            }
        }
    }

    private int size;
    private Node<T> first;
    private Node<T> last;

    public RoLinkedList(){

    }

    public RoLinkedList(T[] array) {
        Node<T> prev = null;
        for (int i = 0; i< array.length; i++){
            if (prev == null){
                Node<T> node = new Node<>(array[i], null, null);
                prev = node;
                first = node;
                size++;

            } else {
                Node<T> node = new Node<>(array[i], prev, null);
                prev.next = node;
                prev = node;
                size++;
                if (i == array.length - 1) {
                    last = node;
                }
            }
        }

    }


    @Override
    public T get(int index) {
        return getNode(index).element;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1){
            return false;
        }
        if (index==0){
            return removeFirst();
        }
        if (index == size - 1){
            return removeLast();
        }
        Node<T> node = getNode(index);
        Node<T> prevNode = node.prev;
        Node<T> nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
        node = null;
        return true;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Запрашиваемый индекс выходит за пределы списка");
        }
        Node<T> node = first;
        int i = 0;
        while (i != index) {
            node = node.next;
            i++;
        }
        return node;
    }

    private boolean removeFirst(){
        if (size == 1){
            clear();
            return true;
        }
        Node<T> node = getNode(1);
        node.prev = null;
        first = node;
        size--;
        node = null;
        return true;
    }

    private boolean removeLast(){
        Node<T> node = getNode(size-2); //предпоследний элемент
        node.next = null;
        last = node;
        size--;
        node = null;
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, Object element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Добавление возможно по индексу от 0 до " + size);
        }
        if (index==0){
            addFirst((T) element);
        } else if(index==size-1){
            addLast((T) element);
        } else {
            Node<T> node = getNode(index);
            Node<T> nodePrev = getNode(index-1);
            Node<T> newNode = new Node<T>((T) element, node.prev, node);
            node.prev = newNode;
            nodePrev.next = newNode;
            size++;
        }
    }

    private void addLast(T element){
        Node<T> node = new Node<>(element, last, null);
        last.next = node;
        last = node;
        size++;
    }

    private void addFirst(T element){
        Node<T> node = new Node<>(element, null, null);
        if(size == 0){
            first = node;
            last = node;
            size++;
        }else{
            node.next = first;
            first.prev = node;
            first = node;
            size++;
        }
    }

    @Override
    public void clear() {
        if (size == 0) return;
        Node<T> node = first;
        Node<T> next;
        do {
            next = node.next;
            node.element = null;
            node.prev = null;
            node.next = null;
        }while (next != null);
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        int i = 0;
        //Node<T> nodeCheck = new Node<T>((T)o, null, null);
        Node<T> node = first;
        while (node != null){
            if (node.equals(o)){
                return i;
            }
            node = node.next;
            i++;
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

    public String toString(){
        if(size > 0){
            StringBuilder sb = new StringBuilder();
            Node<T> node = first;
            do {
                sb.append(node.toString()).append("<-->");
                node = node.next;
            }while (node != null);
            return sb.toString();
        }
        return "";
    }
}
