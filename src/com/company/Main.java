package com.company;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        String[] str = {"1", "2", null, "3"};
        /*int[] arraySource = {0, 1, 2, 3, 4, 5};
        int[] arrayDestination = new int[arraySource.length - 1];
        int indexToDel = 5;
        System.out.println("arraySource: " + Arrays.toString(arraySource));
        System.out.println("arrayDestination: "
                + Arrays.toString(arrayDestination));

        System.arraycopy(arraySource, 0, arrayDestination, 0, indexToDel);
        System.arraycopy(arraySource, indexToDel+1, arrayDestination, indexToDel, arraySource.length - indexToDel - 1);
        System.out.println("arrayDestination after arrayCopy: "
                + Arrays.toString(arrayDestination));*/
        /*RoArrayList<String> roArrayList = new RoArrayList<String>(str);
        //str[0] = "hello";
        //roArrayList.remove("1");
        System.out.println(roArrayList.toString());
        System.out.println(roArrayList.size());
        roArrayList.add(3, "один");
        System.out.println(roArrayList.toString());
        System.out.println(roArrayList.indexOf(null));
        System.out.println(roArrayList.contains("8"));
        System.out.println();*/

        RoLinkedList<String> roLinkedList = new RoLinkedList<String>(str);
        System.out.println(roLinkedList.toString());
        roLinkedList.add(0,null);
        System.out.println(roLinkedList.toString());
        System.out.println(roLinkedList.size());
        roLinkedList.remove(null);

        roLinkedList.remove("1");
        System.out.println(roLinkedList.toString());
        System.out.println(roLinkedList.indexOf(null));
        System.out.println(roLinkedList.contains(null));
        roLinkedList.clear();
        System.out.println(roLinkedList.isEmpty());

    }
}
