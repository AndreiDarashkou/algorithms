import java.util.*;

public class Suntech {

    public static void main(String[] args) {
        // System.out.println(jumps(LAKE));
        //System.out.println(search(new int[]{21}, 10));
        //System.out.println(searchInsert(new int[]{1}, -1));
        //System.out.println(firstBadVersion(2126753390));

        //System.out.println(Arrays.toString(sortedSquares(new int[]{-2, -1, 1, 2, 3, 4, 4, 4, 4, 5, 7, 9})));

        //   int[] n = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        //   rotate3(n, 3);

        //   System.out.println(Arrays.toString(n));

        // System.out.println(reverseWords("abcd asdc mother father sister brother"));
        System.out.println(Arrays.toString(twoSum(new int[]{1, 2, 5, 1, 1, 11, 8, 2, 4, 3, 4, 9}, 14)));
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 1; i < nums.length; i++) {
            System.out.println("----------");
            for (int j = i; j < nums.length; j++) {
                System.out.println(i + ":" + j + "   " + nums[i] + ":" + nums[j - i]);
                if (nums[j] + nums[j - i] == target) {
                    return new int[]{j - i, j};
                }
            }
        }


//        for (int i = 0; i < nums.length - 1; i++) {
//            int toFind = target - nums[i];
//            for (int j = i + 1; j < nums.length; j++) {
//                counter++;
//                if (nums[j] == toFind) {
//                    System.out.println(counter);
//                    return new int[]{i, j};
//                }
//            }
//        }

        return new int[]{0};
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> toFindMap = new HashMap<>();
        Set<Integer> ignore = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (toFindMap.containsKey(nums[i])) {
                return new int[]{toFindMap.get(nums[i]), i};
            }
            int toFind = target - nums[i];
            toFindMap.put(toFind, i);
        }
        return new int[]{};
    }

    public static String reverseWords(String s) {
        int wordLength = 0;
        char[] text = s.toCharArray();

        for (int i = 0; i < text.length; i++) {
            if (text[i] == ' ') {
                rotateWord(text, i - wordLength, i - 1);
                wordLength = 0;
            } else {
                wordLength++;
            }
        }
        rotateWord(text, text.length - wordLength, text.length - 1);

        return new String(text);
    }

    private static void rotateWord(char[] chars, int st, int end) {
        while (st < end) {
            char t = chars[st];
            chars[st] = chars[end];
            chars[end] = t;
            st++;
            end--;
        }
    }

    public static void rotate(int[] nums, int shift) {
        if (shift == 0) {
            return;
        }

        int[] tail = new int[shift];
        int tailIdx = shift - 1;
        int tailIdx1 = shift - 1;

        for (int t = nums.length - 1; t >= 0; t--) {
            if (t >= nums.length - shift) {
                tail[tailIdx--] = nums[t];
            }
            if (t - shift >= 0) {
                nums[t] = nums[t - shift];
            } else {
                nums[t] = tail[tailIdx1--];
            }
        }
    }

    public static void rotate2(int[] nums, int k) {
        int size = nums.length;
        if (k == 0 || k == size || size < 2) {
            return;
        }
        k = k % size;
        int[] tail = new int[k];
        for (int i = size - k, t = 0; i < size; i++, t++) {
            tail[t] = nums[i];
        }
        for (int i = size - 1, t = k - 1; i >= 0; i--) {
            if (i - k >= 0) {
                nums[i] = nums[i - k];
            } else {
                nums[i] = tail[t--];
            }
        }
    }

    public static void rotate3(int[] nums, int k) {
        int size = nums.length;
        if (k == 0 || k == size || size < 2) {
            return;
        }
        k = k % size;

        int st = 0;
        int end = size - 1;
        while (st <= end) {
            int t = nums[st];
            nums[st] = nums[end];
            nums[end] = t;
            st++;
            end--;
        }

        st = k;
        end = size - 1;
        while (st <= end) {
            int t = nums[st];
            nums[st] = nums[end];
            nums[end] = t;
            st++;
            end--;
        }

        st = 0;
        end = k - 1;
        while (st <= end) {
            int t = nums[st];
            nums[st] = nums[end];
            nums[end] = t;
            st++;
            end--;
        }
    }

    public static int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int index = 0;
        for (int n : nums) {
            if (n < 0) {
                n = ~(n) + 1;
            }
            res[index++] = n * n;
        }
        Arrays.sort(res);

        return res;
    }

    private static void sort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int delimiter = delimiter(array, left, right);

        sort(array, left, delimiter - 1);
        sort(array, delimiter + 1, right);
    }

    private static int delimiter(int[] array, int left, int right) {
        int delimiter = array[left];

        while (true) {
            while (right > left && array[right] >= delimiter) {
                right--;
            }
            array[left] = array[right];

            while (left < right && array[left] < delimiter) {
                left++;
            }
            if (left >= right) {
                array[right] = delimiter;
                break;
            }
            array[right] = array[left];
        }

        return left;
    }

//    public static int firstBadVersion(int n) {
//        return findBadVersion(1, n);
//    }
//
//    public static int findBadVersion(long min, long max) {
//        if (min == max || max - min == 1) {
//            if (isBadVersion((int) min)) return (int) min;
//            return (int) max;
//        }
//        long pivot = (min + max) / 2;
//        System.out.println("pivot:" + pivot + " " + min + ":" + max);
//
//        if (isBadVersion((int) pivot)) {
//            return findBadVersion(min, pivot);
//        }
//        return findBadVersion(pivot, max);
//    }
//
//    private static boolean isBadVersion(int pivot) {
//        return pivot > 333333333;
//    }

//    public static int searchInsert(int[] nums, int target) {
//        if (nums[0] > target) return 0;
//        if (nums[nums.length - 1] < target) return nums.length;
//
//        return searchInsert(nums, target, 0, nums.length - 1);
//    }
//
//    private static int searchInsert(int[] nums, int target, int min, int max) {
//        if (min == max || max - min == 1) {
//            if (nums[min] == target) return min;
//            if (nums[max] == target) return max;
//
//            if (nums[min] < target) return min + 1;
//            if (nums[max] > target) return max - 1;
//        }
//        int pivot = (min + max) / 2;
//        if (nums[pivot] > target) {
//            return searchInsert(nums, target, min, pivot);
//        }
//        if (nums[pivot] < target) {
//            return searchInsert(nums, target, pivot, max);
//        }
//        return pivot;
//    }
//
//    public static int search(int[] nums, int target) {
//        return search(nums, 0, nums.length - 1, target);
//    }
//
//    public static int search(int[] nums, int min, int max, int target) {
//        if (min == max || max - min == 1) {
//            if (nums[min] == target) return min;
//            if (nums[max] == target) return max;
//            return -1;
//        }
//        int pivot = (min + max) / 2;
//
//        if (nums[pivot] > target) {
//            return search(nums, min, pivot, target);
//        }
//        return search(nums, pivot, max, target);
//    }
//
//    private static int jumps(String lake) {
//        char[] arr = lake.toCharArray();
//        int jumps = 0;
//        int position = -1;
//        while (position < arr.length - 1) {
//            System.out.println("Current position: " + position);
//
//            int maxJump = position + 8 >= arr.length ? arr.length - position - 1 : 8;
//            int jump = tryJump(arr, position, maxJump);
//            System.out.println("jump: " + jump);
//
//            position += jump;
//            jumps++;
//        }
//        jumps++;
//
//        return jumps;
//    }
//
//    private static int tryJump(char[] arr, int currentPosition, int jump) {
//        char next = arr[currentPosition + jump];
//        if (next == '*') {
//            return jump;
//        } else {
//            return tryJump(arr, currentPosition, jump - 1);
//        }
//    }
}




