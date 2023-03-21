public class StringCalculator {

    public static int add(String numbers) {
        StringBuilder delimiters = new StringBuilder(",|\n");
        if (numbers.equals("")) {
            return 0;
        }
        if (numbers.startsWith("//")) {
            String[] delimitersAndNumbers = numbers.split("\n", 2);
            delimiters.append("|").append("\\").append(delimitersAndNumbers[0].substring(2));
            numbers = delimitersAndNumbers[1];
        }
        return sum(numbers.split(delimiters.toString()));
    }

    private static int sum(String[] numbers) {
        int sum = 0;
        for (String num: numbers) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }
}
