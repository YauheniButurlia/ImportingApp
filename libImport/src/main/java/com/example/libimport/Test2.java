package com.example.libimport;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Test2 {
  public static void main(String[] args){

    Random rand = new Random();

    ArrayList<String> arrayList = new ArrayList<>();
    for(int i = 0; i<2000000;i++){
      arrayList.add(String.valueOf(rand.nextInt(50000)));
    }

    LinkedList<String> linkedList = new LinkedList<>();
    for(int i = 0; i<2000000;i++){
      linkedList.add(String.valueOf(rand.nextInt(50000)));
    }



    long start1 = System.currentTimeMillis();
    arrayList.get(1900000);
    long finish1 = System.currentTimeMillis() - start1;

    long start2 = System.currentTimeMillis();
    linkedList.get(999999);
    long finish2 = System.currentTimeMillis() - start2;

    System.out.println("ArrayList");
    System.out.println(finish1);
    System.out.println("LinkedList");
    System.out.println(finish2);
  }
}
