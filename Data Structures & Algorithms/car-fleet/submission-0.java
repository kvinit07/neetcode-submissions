class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
     
     int n = position.length;
        if (n == 0) return 0;

        // Create an array of cars with their positions and times to reach the target
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = (double) (target - position[i]) / speed[i];
        }

        // Sort cars based on their starting positions
        Arrays.sort(cars, Comparator.comparingDouble(a -> a[0]));

        int fleets = 0;
        double timeToArrive = 0;

        // Traverse from the car closest to the target to the farthest
        for (int i = n - 1; i >= 0; i--) {
            if (cars[i][1] > timeToArrive) {
                fleets++;
                timeToArrive = cars[i][1];
            }
        }

        return fleets;
        
    }
}
