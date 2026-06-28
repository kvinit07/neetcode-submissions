class Solution:
    def minCostClimbingStairs(self, cost):
        n = len(cost)
        prev2, prev1 = 0, 0
        for i in range(2, n + 1):
            from_prev = prev1 + cost[i - 1]
            from_prev2 = prev2 + cost[i - 2]
            curr = min(from_prev, from_prev2)
            prev2, prev1 = prev1, curr
        return prev1