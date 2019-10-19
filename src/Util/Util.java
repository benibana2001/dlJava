package Util;

public class Util {
    public static String paddingZero(int digit, int i) {
            return pad(digit, i, "0");
    }

    public static String pad(int digit, int n, String pre) {
        String s = String.valueOf(n);
        while (s.length() < digit) {
            s = pre + s;
        }
        return s;
    }

}
