package study.blog.codingnojam.algorithm;

public class CdoingTest {

    public static void main(String[] args) {

        int[] truck = {1, 4, 5, 2, 4};
        int[] weight = {2, 4, 4, 3, 2};

        int[] result = new int[weight.length];

        prc(truck, weight, result);

        for (int i = 0; i < result.length; i++) {
            System.out.println(i + " = " + result[i]);
        }

    }

    static void prc(int[] truck, int[] weight, int[] result) {

        int truckIndex = 0;
        for (int i = 0; i < weight.length; i++) {
            if (i != 0) {
                if (weight[i] >= weight[i - 1]) {
                    if (result[i - 1] == -1) {
                        result[i] = -1;
                        continue;
                    }
                    for (int j = truckIndex; j < truck.length; j++) {
                        if (truck[j] - weight[i] >= 0) {
                            truck[j] = truck[j] - weight[i];
                            result[i] = j + 1;
                            truckIndex = j;
                            break;
                        }

                        System.out.println(j);
                        if (j == truck.length - 1) {
                            result[i] = -1;
                        }
                    }
                } else {
                    for (int j = 0; j < truck.length; j++) {
                        if (truck[j] - weight[i] >= 0) {
                            truck[j] = truck[j] - weight[i];
                            result[i] = j + 1;
                            truckIndex = j;
                            break;
                        }

                        if (j == truck.length - 1) {
                            result[i] = -1;
                        }
                    }
                }

            } else {
                for (int j = truckIndex; j < truck.length; j++) {
                    if (truck[j] - weight[i] >= 0) {
                        truck[j] = truck[j] - weight[i];
                        result[i] = j + 1;
                        truckIndex = j;
                        break;
                    }

                    if (j == truck.length - 1) {
                        result[i] = -1;
                        truckIndex = j;
                    }
                }

            }
        }

    }
}
