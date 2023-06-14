package org.fw.utils;

public class StringUtils {
    public static String upperCaseFirstLetter(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        str = str.replaceAll("_", " ");
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    public static String upperCaseAllLetters(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split("_");
        StringBuilder strBuilder = new StringBuilder();
        for (String word : words) {
            String upperWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
            strBuilder.append(upperWord).append(" ");
        }
        return strBuilder.toString().trim();
    }
}
