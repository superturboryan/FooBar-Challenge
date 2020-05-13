public class Solution {

    public static void main(String[] args) {
        System.out.println(reId(0));
    }

    public static String reId(int n) {
        String primeNums = getPrimeString();
        return primeNums.substring(n, n+5);
    }

    public static String getPrimeString() {
        StringBuilder s = new StringBuilder();
        for (int i = 2; i < 100000; i++) {
            if (isPrime(i)) {
                s.append(Integer.toString(i));
            }
        }
        return s.toString();
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

}