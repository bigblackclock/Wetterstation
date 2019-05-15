package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy-HHmmss");
        Date date = new Date();

        int[] arr = new int[30];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = zufalligenPositiveNegativWerte(9, 25);

        }

        JFreeChart charto = ChartFactory.createXYLineChart("Temp", "Days", "Temp in C`", theSet(arr));
        try {
            ChartUtilities.saveChartAsJPEG(new File("/home/fam/Desktop/'" + formatter.format(date) + ".jpeg"), charto, 700, 700);

        } catch (Exception e) {

            e.printStackTrace();
        }
        System.out.println("Temp Werte");
        System.out.println(Arrays.toString(arr));


        System.out.println("Tage mit Frost    " + tagenMitFrost(arr));
        System.out.println("Minimum Temp      " + minimumWert(arr));
        System.out.println("Maximum Temp      " + maximumWert(arr));
        System.out.println("Temp Schwankung   " + tempSchwankung(arr));
        System.out.println("Cel to Fahr       " + Arrays.toString(celsiusArrayZoFahrenheit(arr)));
        System.out.println("locales Maxium");
        System.out.println();
        localMax(arr);
        System.out.println(gleitenderMittelwert(arr, 5));


    }

    public static XYDataset theSet(int[] data) {
        List gleitenderMittelwert = new ArrayList();
        gleitenderMittelwert = gleitenderMittelwert(data, 5);
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("TEMP DATA");
        XYSeries series2 = new XYSeries("GMW DATA");
        for (int i = 0; i < data.length; i++) {
            series1.add(i, data[i]);
        }

        dataset.addSeries(series1);
        for (int i = 0; i < gleitenderMittelwert.size(); i++) {
            series2.add(Double.valueOf(i), (Number) gleitenderMittelwert.get(i));
        }
        dataset.addSeries(series2);
        return dataset;
    }

    public static List gleitenderMittelwert(int[] werte, int fenster) {
        List data = new ArrayList();
        double resualt = 0;
        for (int i = 0; i < werte.length; i++) {
            for (int o = 0; o < fenster; o++) {

                if (o + i < werte.length) {
                    resualt = resualt + werte[i + o];
                }
            }


            resualt = Math.round(resualt / fenster * 100.00) / 100.00;
            data.add(resualt);

        }

        return data;
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