package com.string;

public class ReverseString {
    public void reverseString(char[] s) {
        char temp;
        int i = 0, j = s.length - 1;
        for (; i < j; i++, j--) {
            temp = s[j];
            s[j] = s[i];
            s[i] = temp;
        }

    }
}
