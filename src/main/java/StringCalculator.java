public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty())
            return 0;
        return sum(numbers.split(","));
    }

    private static int sum(String[] numbers) {
        int sum = 0;
        for (String num: numbers) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}
