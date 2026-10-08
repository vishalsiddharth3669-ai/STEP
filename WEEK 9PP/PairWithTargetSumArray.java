import java.util.HashSet;

public class PairWithTargetSumArray {

    // Approach 1: Brute Force
    public static boolean bruteForce(
            int[] nums,
            int target) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    // Approach 2: HashSet
    public static boolean usingHashing(
            int[] nums,
            int target) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            int required = target - num;

            if (set.contains(required)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }

    public static void main(String[] args) {

        int[] nums1 = { 2, 7, 11, 15 };
        int[] nums2 = { 3, 4, 6 };

        System.out.println(
                bruteForce(nums1, 9));

        System.out.println(
                usingHashing(nums1, 9));

        System.out.println(
                usingHashing(nums2, 20));
    }
}