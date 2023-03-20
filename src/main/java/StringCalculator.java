public class StringCalculator {


    public static int add(String numbers) {
        if (numbers.equals("")) {
            return 0;
        }
        return sum(numbers.split(",|\n"));
    }

    private static int sum(String[] numbers) {
        int sum = 0;
        for (String num: numbers) {
            sum += Integer.parseInt(num.equals("") ? "0" : num);
        }
        return sum;
    }
}
