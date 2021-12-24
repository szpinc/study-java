import java.util.HashMap;
import java.util.Map;

/**
 * @author sunzp
 * @since 2021/12/9 11:00 上午
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }

    public static String longestPalindrome(String s) {
        String str = "";
        char[] chars = s.toCharArray();
        Map<Character, Integer> charIndexMap = new HashMap<>(16);
        for (int i = 0; i < chars.length; i++) {
            Character cha = chars[i];

            int startIndex = i;
            int endIndex = i + 1;

            Integer charIndex = charIndexMap.get(cha);

            if (charIndex != null) {
                startIndex = charIndex;
            } else {
                charIndexMap.put(cha, i);
            }
            String content = s.substring(startIndex, endIndex);
            if (content.length() > str.length() && check(content)) {
                str = content;
            }
        }
        return str;
    }

    private static boolean check(String str) {
        char[] chars = str.toCharArray();
        char[] newChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            newChars[chars.length - 1 - i] = chars[i];
        }
        return new String(newChars).equals(str);
    }
}
