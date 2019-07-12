package ru.neoflex.sender.Parser;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CsvParser {
    public static void main(String[] args) {
        CsvParser csvParser = new CsvParser();
        System.out.println(csvParser.getDate());
    }

    public static int getCurrentIndexYear(List<String[]> list,int year){
        Iterator iter = list.iterator();
        int index = 0;
        while(iter.hasNext()){
            String[] current = (String[]) iter.next();
            for(int i = 0;i < current.length-5;i++){
                if(current[i].equals(String.valueOf(year))){
                    return index;
                }
            }
            index++;
        }
        return 0;
    }
    public String getDate(){
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        try (CSVReader csvReader = new CSVReader(new FileReader("data.csv"))){
            List<String[]> values = csvReader.readAll();
            String[] strings=values.get(getCurrentIndexYear(values,year));
            String current = strings[month];
            String[] subStr = current.split(",");
            for(int i = 0; i < subStr.length;i++){
                if(String.valueOf(day).equals(subStr[i])){
                    String s = strings[i].substring(0,3);
                    return s;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

