import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int n = 10000;
        int[] input1 = new int[n];
        int[] input2 = new int[n];
        int[] result = new int[n];
        Arrays.fill(input1, (int) (Math.random() * 100));
        Arrays.fill(input2, (int) (Math.random() * 100));
        int sleep = 0; // set to 1 for slower execution

        // synchronous version
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            result[i] = input1[i] * input2[i];
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.printf("Synchronous: %s\n", System.currentTimeMillis() - time1);

        // parallel stream version
        long time2 = System.currentTimeMillis();
        Arrays.parallelSetAll(result, i -> input1[i] * input2[i]);
        Arrays.stream(result).forEach(i -> {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.printf("Parallel: %s\n", System.currentTimeMillis() - time2);
    }
}