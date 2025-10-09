package sortingalgorythms;

import java.util.HashMap;

public class SlidingWindow {


        public String minWindow(String source, String target) {

            // --- 1️⃣ Edge Case: If target is longer than source, no window is possible ---
            if (target.length() > source.length()) {
                return "";
            }

            // --- 2️⃣ Build the target frequency map ---
            // This map holds how many of each character we must have.
            HashMap<Character, Integer> targetFrequency = new HashMap<>();

            for (int i = 0; i < target.length(); i++){
                Character targetCharacter = target.charAt(i);
                if (!targetFrequency.containsKey(targetCharacter)){
                    targetFrequency.put(targetCharacter, 1);
                }
                else {
                    targetFrequency.put(targetCharacter, targetFrequency.get(targetCharacter) + 1);
                }
            }

            // --- 3️⃣ Initialize window tracking structures ---
            // Keeps counts for current window.
            HashMap<Character, Integer> windowFrequency = new HashMap<>();

            // "formed" = number of characters that currently meet required frequency.
            int formed = 0;
            int required = targetFrequency.size();

            // Pointers for the sliding window.
            int left = 0;
            int right = 0;

            // Track the best (smallest) window seen so far.
            int minWindowLength = Integer.MAX_VALUE;
            int minWindowStart = 0;

            // --- 4️⃣ Expand the right pointer one character at a time ---
            while (right < source.length()) {
                char currentChar = source.charAt(right);
                windowFrequency.put(currentChar, windowFrequency.getOrDefault(currentChar, 0) + 1);

                // If this character’s frequency matches what we need, increment 'formed'.
                if (targetFrequency.containsKey(currentChar) &&
                        windowFrequency.get(currentChar).intValue() == targetFrequency.get(currentChar).intValue()) {
                    formed++;
                }

                // --- 5️⃣ When we have a valid window (all required chars matched) ---
                while (formed == required && left <= right) {

                    // Try to update our best window if it’s smaller than the previous one.
                    int currentWindowSize = right - left + 1;
                    if (currentWindowSize < minWindowLength) {
                        minWindowLength = currentWindowSize;
                        minWindowStart = left;
                    }

                    // Shrink from the left to see if we can make it smaller while still valid.
                    char leftChar = source.charAt(left);
                    windowFrequency.put(leftChar, windowFrequency.get(leftChar) - 1);

                    // If a character is now below required frequency → window no longer valid.
                    if (targetFrequency.containsKey(leftChar) &&
                            windowFrequency.get(leftChar).intValue() < targetFrequency.get(leftChar).intValue()) {
                        formed--;
                    }

                    left++; // move left pointer forward
                }

                // Continue expanding the right boundary
                right++;
            }

            // --- 6️⃣ Return the result ---
            if (minWindowLength == Integer.MAX_VALUE) {
                return ""; // no valid window found
            }

            return source.substring(minWindowStart, minWindowStart + minWindowLength);
        }


    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int leftWindow = 0;
        int rightWindow = 0;

        for (int i = 0; i < s.length(); i++) {
            int lenOdd = expandFromCenter(s, i, i);       // Odd-length palindrome
            int lenEven = expandFromCenter(s, i, i + 1);  // Even-length palindrome
            int currLen = Math.max(lenOdd, lenEven);

            // Update window if we found a longer palindrome
            if (currLen > rightWindow - leftWindow) {
                leftWindow = i - (currLen - 1) / 2;
                rightWindow = i + currLen / 2;
            }
        }

        return s.substring(leftWindow, rightWindow + 1);
    }

    private int expandFromCenter(String s, int left, int right) {
        // Expand outward while characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // Length of palindrome is the number of valid characters between boundaries
        return right - left - 1;
    }
        public static void main(String[] args) {
            SlidingWindow mimimumWindowSubstring = new SlidingWindow();

            System.out.println(mimimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC")); // BANC
            System.out.println(mimimumWindowSubstring.minWindow("a", "a"));               // a
            System.out.println(mimimumWindowSubstring.minWindow("a", "aa"));              // ""
            System.out.println(mimimumWindowSubstring.longestPalindrome("asabbabbajk"));

        }
    }



//https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/solutions/6900610/my-solution-using-a-modified-dynamic-sliding-window/