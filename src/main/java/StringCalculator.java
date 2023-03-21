import Exception.NegativesNotAllowedException;

public class StringCalculator {

    public static int add(String numbers) throws NegativesNotAllowedException {
        int sum = 0;
        StringBuilder delimiters = new StringBuilder(",|\n");
        StringBuilder negativeNumbers = new StringBuilder();

        if (numbers.equals("")) {
            return 0;
        }
        if (numbers.startsWith("//")) {
            String[] delimitersAndNumbers = numbers.split("\n", 2);
            delimiters.append("|").append("\\").append(delimitersAndNumbers[0].substring(2));
            numbers = delimitersAndNumbers[1];
        }

        String[] arrayOfNumbers = numbers.split(delimiters.toString());
        for (String number: arrayOfNumbers) {
            int n = Integer.parseInt(number);
            if (n < 0) {
                negativeNumbers.append(", ").append(n);
            } else {
                sum += n;
            }
        }
        if (negativeNumbers.length() > 0) {
            throw new NegativesNotAllowedException("Not allowed to add negative numbers:"
                    + negativeNumbers.substring(1));
        }
        return sum;
    }
}
