package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.util.Person.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) throws IOException {
        final FileReader in = new FileReader("input.txt");
        String[] array = LoadAndSort(in);
        for (String s:array) {
            new Person("Aleksey", 30);
            System.out.println(s);
        }
    }

    private static String[] LoadAndSort(FileReader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        ArrayList<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        String[] array = lines.toArray(new String[lines.size()]);
        Arrays.sort(array);
        return array;
    }
}
