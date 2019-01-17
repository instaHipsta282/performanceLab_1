/*
1. Считать из файла последовательность целых чисел. Вычислить 90 персентиль, медиану, максимальное, минимальное и среднее значения.
На вход программа получает файл с числами. Каждое число в файле находится на новой строке. Вывод в консоль должен быть следующим:

90 percentile <значение>
median <значение>
average <значение>
max <значение>
min <значение>
C:\Users\Анна\IdeaProjects\PerformanceLab\src\main\java\task1\dataFor1task.txt
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class performanceLab_1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(args[0]));
            String s;
            while ((s = reader.readLine()) != null) {
                list.add(Integer.parseInt(s));
            }
        }
        catch (IOException e) { e.printStackTrace(); }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException ignore) {}
            }
        }
        Collections.sort(list);
        System.out.println("90 percentile <" + percentile(list, 90) + ">");
        System.out.println("median <" + median(list) + ">");
        System.out.println("average <" + avg(list) + ">");
        System.out.println("max <" + list.get(list.size() - 1) + ">");
        System.out.println("min <" + list.get(0) + ">");
    }
    public static double avg(List<Integer> sortedList) {
        double averageNumber = 0;
        for (int i = 0; i < sortedList.size(); i++) {
            averageNumber += sortedList.get(i);
        }
        averageNumber = averageNumber / sortedList.size();
        return averageNumber;
    }
    public static double median(List<Integer> sortedList) {
        int countOfNumbers = sortedList.size();
        int index1;
        int index2;
        double median;
        if (countOfNumbers % 2 == 0) {
            index1 = (countOfNumbers / 2) - 1;
            index2 = index1 + 1;
            median = (double) (sortedList.get(index1) + sortedList.get(index2)) / 2;
        }
        else {
            index1 = countOfNumbers / 2;
            median = sortedList.get(index1);
        }
        return median;
    }
    public static int percentile(List<Integer> sortedList, int percent) {
        int countOfNumbers = sortedList.size();
        double percentile = (double) (percent * countOfNumbers) / 100;
        double around = Math.ceil(percentile);
        int aroundInt = (int) around;
        int percentileNumber = sortedList.get(aroundInt - 1);
        return percentileNumber;
    }

}
