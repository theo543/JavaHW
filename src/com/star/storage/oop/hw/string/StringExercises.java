package com.star.storage.oop.hw.string;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class StringExercises {
    public static int find_length_of_a_string(String s) {
        return s.length();
    }

    public static void copy_one_string_to_another_string(String from, String to) {
        //impossible, strings are immutable
    }

    public static String concatenate_two_strings(String a, String b) {
        return a + b;
    }

    public static int compare_two_strings(String a, String b) {
        return a.compareTo(b);
    }

    public static String convert_lowercase_string_to_uppercase(String s) {
        return s.toUpperCase();
    }

    public static String convert_uppercase_string_to_lowercase(String s) {
        return s.toLowerCase();
    }

    public static String toggle_case_of_each_character_of_a_string(String s) {
        StringBuilder j = new StringBuilder();
        for (int x = 0; x < s.length(); x++) {
            if (Character.isUpperCase(s.charAt(x)))
                j.append(Character.toLowerCase(s.charAt(x)));
            else if (Character.isLowerCase(s.charAt(x)))
                j.append(Character.toUpperCase(s.charAt(x)));
            else j.append(s.charAt(x));
        }
        return j.toString();
    }

    public static long find_total_number_of_alphabets_digits_or_special_character_in_a_string(String s) {
        return s.length() - Pattern.compile(" ").matcher(s).results().count(); //if it's not a space it's either a letter digit or special character
    }

    public static long[] count_total_number_of_vowels_and_consonants_in_a_string(String s) {
        long letters = Pattern.compile("[a-zA-Z]").matcher(s).results().count();
        long vowels = Pattern.compile("[aeiouAEIOU]").matcher(s).results().count();
        return new long[]{vowels, letters - vowels};
    }

    public static long count_total_number_of_words_in_a_string(String s) {
        return Pattern.compile("[^ ]*").matcher(s).results().count();
    }

    public static String find_reverse_of_a_string(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    public static boolean check_whether_a_string_is_palindrome_or_not(String s) {
        if (s.isEmpty())
            return true;
        int l, r;
        if (s.length() % 2 == 1) {
            l = s.length() / 2;
            r = l;
        } else {
            l = s.length() / 2;
            r = l + 1;
        }
        while (l >= 0) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            l--;
            r++;
        }
        return true;
    }

    public static String reverse_order_of_words_in_a_given_string(String s) {
        var a = s.split(" ");
        Collections.reverse(Arrays.asList(a));
        return String.join(" ", a);
    }

    public static int find_first_occurrence_of_a_character_in_a_given_string(String s, char c) {
        return s.indexOf(c);
    }

    public static int find_last_occurrence_of_a_character_in_a_given_string(String s, char c) {
        return s.lastIndexOf(c);
    }

    public static List<Integer> search_all_occurrences_of_a_character_in_given_string(String s, char c) {
        List<Integer> l = new ArrayList<>();
        for (int x = 0; x < s.length(); x++) {
            if (s.charAt(x) == c)
                l.add(x);
        }
        return l;
    }

    public static long count_occurrences_of_a_character_in_given_string(String s, char c) {
        return Pattern.compile(String.valueOf(c)).matcher(s).results().count();
    }


    public static char find_highest_frequency_character_in_a_string(String s) {
        final char[] c = {0};
        int f = -1;
        count_frequency_of_each_character_in_a_string(s).forEach((chr, frq) -> {
            if (frq > f) {
                c[0] = chr;
            }
        });
        return c[0];
    }

    public static char find_lowest_frequency_character_in_a_string(String s) {
        final char[] c = {0};
        int f = s.length() + 1;
        count_frequency_of_each_character_in_a_string(s).forEach((chr, frq) -> {
            if (frq < f) {
                c[0] = chr;
            }
        });
        return c[0];
    }

    public static HashMap<Character, Integer> count_frequency_of_each_character_in_a_string(String s) {
        var m = new HashMap<Character, Integer>();
        for (int x = 0; x < s.length(); x++)
            m.merge(s.charAt(x), 1, (a, b) -> b + 1);
        return m;
    }

    public static String remove_first_occurrence_of_a_character_from_string(String s, char c) {
        int i = s.indexOf(c);
        if (i == -1)
            return s;
        return s.substring(0, i) + s.substring(i + 1);
    }

    public static String remove_last_occurrence_of_a_character_from_string(String s, char c) {
        int i = s.lastIndexOf(c);
        if (i == -1)
            return s;
        return s.substring(0, i) + s.substring(i + 1);
    }

    public static String remove_all_occurrences_of_a_character_from_string(String s, char c) {
        return s.replace(String.valueOf(c), "");
    }

    public static String remove_all_repeated_characters_from_a_given_string(String s) {
        HashSet<Character> c = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < s.length(); x++) {
            if (!c.contains(s.charAt(x))) {
                c.add(s.charAt(x));
                sb.append(s.charAt(x));
            }
        }
        return sb.toString();
    }

    public static String replace_first_occurrence_of_a_character_with_another_in_a_string(String s, char c, char r) {
        int i = s.indexOf(c);
        if (i == -1)
            return s;
        return s.substring(0, i) + r + s.substring(i + 1);
    }

    public static String replace_last_occurrence_of_a_character_with_another_in_a_string(String s, char c, char r) {
        int i = s.lastIndexOf(c);
        if (i == -1)
            return s;
        return s.substring(0, i) + r + s.substring(i + 1);
    }

    public static String replace_all_occurrences_of_a_character_with_another_in_a_string(String s, char c, char r) {
        return s.replace(c, r);
    }

    public static int find_first_occurrence_of_a_word_in_a_given_string(String s, String ss) {
        return s.indexOf(ss);
    }

    public static int find_last_occurrence_of_a_word_in_a_given_string(String s, String ss) {
        return s.lastIndexOf(ss);
    }

    public static List<Integer> search_all_occurrences_of_a_word_in_given_string(String s, String ss) {
        return Pattern.compile(ss, Pattern.LITERAL).matcher(s).results().map(MatchResult::start).toList();
    }

    public static long count_occurrences_of_a_word_in_a_given_string(String s, String ss) {
        return Pattern.compile(ss, Pattern.LITERAL).matcher(s).results().count();
    }

    public static String remove_first_occurrence_of_a_word_from_string(String s, String ss) {
        int i = s.indexOf(ss);
        if (i == -1)
            return s;
        return s.substring(0, i) + s.substring(i + ss.length());
    }

    public static String remove_last_occurrence_of_a_word_in_given_string(String s, String ss) {
        int i = s.lastIndexOf(ss);
        if (i == -1)
            return s;
        return s.substring(0, i) + s.substring(i + ss.length());

    }

    public static String remove_all_occurrence_of_a_word_in_given_string(String s, String ss) {
        return s.replaceAll(Pattern.quote(ss), "");
    }

    public static String trim_both_leading_and_trailing_white_space_characters_from_given_string(String s) {
        return s.strip();
    }

    public static String remove_all_extra_blank_spaces_from_given_string(String s) {
        return s.replaceAll(" {2,}", " ");
    }
}
