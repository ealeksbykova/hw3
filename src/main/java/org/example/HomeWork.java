package org.example;

import java.util.*;


public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Метод возвращает подстроку максимальной длины в которой не повторяются символы
     * Если найдено несколько подстрок одинаковой длины - вернуть первую.
     *
     * Пример 1 вход= abcddcba, выход = abcd
     * Так как найдены две подстроки с неповторяющимися значениями:
     * 1) abcd
     * 2) dcba
     * Они динаковой длины, по этому возвращаем 1 строку
     *
     * Пример 2 вход= abcddcbaX, выход = dcbaX
     * Так как эта подстрока самая большая
     *
     * @param str входная не пустая строка
     * @return максимальная подстрока из уникальных значений
     * Сигнатуру метода не меняем
     */
    public String findMaxSubstring(String str) {
        if (str == null) {
            throw new NullPointerException("str must not be null");
        }

        ArrayList<String> subStrings = getSubstrings(str);

        return getLongestString(subStrings);
    }

    private ArrayList<String> getSubstrings(String str) {
        ArrayList<String> subStrings = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            String item = getUniqueSubstr(str.substring(0, str.length() - i - 1));
            if (item != null) {
                subStrings.add(item);
            }
        }

        for (int i = 0; i < str.length(); i++) {
            String item = getUniqueSubstr(str.substring(i));
            if (item != null) {
                subStrings.add(item);
            }
        }

        return subStrings;
    }

    private String getUniqueSubstr(String substr) {
        LinkedHashSet<Character> unique = new LinkedHashSet<>();

        for(char c : substr.toCharArray()) {
            unique.add(c);
        }

        StringBuilder result = new StringBuilder();
        for(char c : unique) {
            result.append(c);
        }

        if (substr.contentEquals(result)) {
            return result.toString();
        }

        return null;
    }

    private String getLongestString(ArrayList<String> strings) {
        TreeSet<Integer> sizes = new TreeSet<>();

        for (String s : strings) {
            sizes.add(s.length());
        }

        int maxSize = sizes.last();
        for (int i = 0; i < strings.size(); i++) {
            if (strings.get(i).length() == maxSize) {
                return strings.get(i);
            }
        }

        return null;
    }


    /**
     * Задача со зведочкой (опционально)
     * <br/>
     * Можно решать так же для английского алфавита, проверять что присутствуют буквы от A до Z хотя бы по одному разу.
     * <br/>
     * A pangram is a sentence that contains every single letter of the alphabet at least once. For example,
     * the sentence "The quick brown fox jumps over the lazy dog" is a pangram, because it uses the letters A-Z
     * at least once (case is irrelevant).
     *
     * Given a string, detect whether or not it is a pangram. Return True if it is, False if not. Ignore numbers and punctuation.
     * @see <a href="https://www.codewars.com/kata/545cedaa9943f7fe7b000048">https://www.codewars.com/kata/545cedaa9943f7fe7b000048</a>
     */
    public boolean check(String sentence){
        Map<Character, Integer> alphabeticCharSet = getUniqueSet();

        for (char c : sentence.toLowerCase().toCharArray()) {
            Integer i = alphabeticCharSet.get(c);
            if (i != null) {
                alphabeticCharSet.put(c, ++i);
            }
        }

        return !alphabeticCharSet.containsValue(0);
    }

    private Map<Character, Integer> getUniqueSet() {
        HashMap<Character, Integer> charSet = new HashMap<>();

        for (char ch = 'a'; ch <= 'z'; ++ch) {
            charSet.put(ch, 0);
        }

        return charSet;
    }
}
