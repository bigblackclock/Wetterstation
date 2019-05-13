package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {


        int[] arr = new int[30];
        int[] data = {1, 2, 3, 4, 5, 3, 2, 1, 9, 10, 6, 2, 1};
        for (int i = 0; i < arr.length; i++) {
            arr[i] = zufalligenPositiveNegativWerte(-12, 12);


        }
        System.out.println("Temp Werte");
        System.out.println(Arrays.toString(arr));


        //  int[] wert = {1,1,0,1,6,1,12,3,17,8,5,6,6,6,7,14,2,2,3,5,6,4,8};

        System.out.println("Tage mit Frost    " + tagenMitFrost(arr));
        System.out.println("Minimum Temp      " + minimumWert(arr));
        System.out.println("Maximum Temp      " + maximumWert(arr));
        System.out.println("Temp Schwankung   " + tempSchwankung(arr));
        System.out.println("Cel to Fahr       " + Arrays.toString(celsiusArrayZoFahrenheit(arr)));
        System.out.println("locales Maxium");
        localMax(arr);

    }


    public static int zufalligenPositiveNegativWerte(int min, int max) {
        return (int) Math.round(Math.random() * (max - min) + min);
    }

    public static int tagenMitFrost(int[] array) {
        int tagen = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                tagen++;

            }
        }
        return tagen;
    }

    public static int maximumWert(int[] array) {
        int maximumWert = 0;
        for (int i = 0; i < array.length; i++) {
            if (maximumWert < array[i]) {
                maximumWert = array[i];
            }
        }


        return maximumWert;
    }


    public static int minimumWert(int[] array) {
        int minimumWert = 0;
        for (int i = 0; i < array.length; i++) {
            if (minimumWert > array[i]) {
                minimumWert = array[i];
            }
        }

        return minimumWert;
    }

    public static double[] celsiusArrayZoFahrenheit(int[] array) {
        double[] fahrenheitArray = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            fahrenheitArray[i] = (array[i] * (9.0 / 5.0) + 32.0);
        }
        return fahrenheitArray;
    }

    public static int tempSchwankung(int[] array) {
        int min = minimumWert(array);
        int max = maximumWert(array);
        return (max - min);
    }

    public static void localMax(int[] wert) {
        if (wert.length >= 3) {
            List stelle = new ArrayList();
            List werte = new ArrayList();
            int befor = wert[0];
            int pointer = 0;
            int next = 0;
            //  System.out.println(wert.length);
            for (int i = 1; i < wert.length; i++) {
                //System.out.println("Befor : " + befor);
                pointer = wert[i];
                //System.out.println(i + " pointer " + pointer);

                if (wert.length > (i + 1)) {
                    next = wert[i + 1];
                    //System.out.println("next : " + next);
                } else {
                    // System.out.println("last one .... no next  " + next);
                }
                if (pointer >= befor) {
                    //System.out.println("the is wert is bigger go next");


                    if (pointer > next) {
                        // System.out.println("yooo we go a locales maximum " + pointer);
                        werte.add(pointer);
                        stelle.add(i);
                        System.out.println("Stelle " + i + "    Wert " + pointer);
                    }
                }
                befor = pointer;

            }
            System.out.println("Stell " + Arrays.toString(stelle.toArray()));
            System.out.println("Werte " + Arrays.toString(werte.toArray()));


        } else {
            System.out.println("this function require 3 values minimum ");
        }


    }
}