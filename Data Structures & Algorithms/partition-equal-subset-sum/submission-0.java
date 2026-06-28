class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int x : nums) sum += x;
        if ((sum & 1) == 1) return false;

        int target = sum / 2;
        BitSet dp = new BitSet(target + 1);
        dp.set(0);

        for (int num : nums) {
            BitSet s = new BitSet(target + 1);
            for (int i = dp.nextSetBit(0); i >= 0; i = dp.nextSetBit(i + 1)) {
                int ni = i + num;
                if (ni <= target) s.set(ni);
                else break;
            }
            dp.or(s);
            if (dp.get(target)) return true;
        }
        return dp.get(target);
    }
}