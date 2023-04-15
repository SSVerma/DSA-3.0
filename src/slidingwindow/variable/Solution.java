package slidingwindow.variable;

import java.util.HashMap;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }
    public static String minWindow(String s, String t) {
        int left = 0;
        int right = 0;

        Map<Character, Integer> map = buildFreqMap(t);
        int matches = map.size();

        String result = "";

        while (right < s.length()) {
            // build window untill constraints matches
            
            while(right < s.length()) {
                char ch = s.charAt(right);
                if (map.containsKey(ch)) {
                    int freq = map.get(ch) - 1;
                    map.put(ch, freq);
                    if (freq == 0) {
                        matches--;
                    }
                }
                if (matches == 0) {
                    break;
                }
                right++;
            }

            while (left < right && matches == 0) {
                int ws = right - left + 1;

                if (result.isEmpty() || ws < result.length()) {
                    result = s.substring(left, right + 1);
                }

                char leftChar = s.charAt(left);

                if (map.containsKey(leftChar)) {
                    int freq = map.get(leftChar) + 1;
                    map.put(leftChar, freq);
                    if (freq == 1) {
                        matches++;
                    }
                }
                
                left++;
            }

            right++;
        }

        return result;
    }

    private static Map<Character, Integer> buildFreqMap(String input) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            int freq = map.getOrDefault(ch, 0);
            map.put(ch, freq + 1);
        }

        return map;
    }
}