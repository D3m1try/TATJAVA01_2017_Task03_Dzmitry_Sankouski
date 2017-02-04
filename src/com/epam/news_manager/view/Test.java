package com.epam.news_manager.view;

import com.epam.news_manager.dao.impl.FileIdGenerator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dzmitry_Sankouski on 02-Feb-17.
 */
public class Test {
    public static void main(String[] args) throws ParseException {
        int i = 0;

        Pattern p = Pattern.compile("\\s\\-ISBN+\\s+[^\\s]+\\s");
        Matcher m = p.matcher(" -ISBN  asjk ");

        Pattern pattern = Pattern.compile("^(\\s*)(\\w+)(.+)");
        Matcher matcher = pattern.matcher("  book  title mybook");


        while (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
            i++;
        }

//        StringBuilder result = new StringBuilder();
//        result.append("sdfg").append("---");
//        System.out.println(result.toString());


    }
}
