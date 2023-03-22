import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {

    public static int add(String numbers) throws NegativesNotAllowedException {
        int sum = 0;
        ArrayList<String> delimiters = new ArrayList<>(List.of(",", "\n"));
        String delimiters_pattern = ",|\n";
        StringBuilder negativeNumbers = new StringBuilder();

        if (numbers.equals("")) {
            return 0;
        }

        if (numbers.startsWith("//")) {
            String[] elements = numbers.substring(2).split("\n", 2);

            if (!elements[0].startsWith("[") && elements[0].length() > 1) {
                throw new InvalidDelimitersInputException("Incorrect registration of long delimiter (length > 1)\n" +
                        "Wrap the separator with square brackets: [" + elements[0] + "]");
            }

            if(elements[1].length() == 0) {
                return 0;
            }

            expandDelimiters(delimiters, elements[0]);

            delimiters_pattern = toDelimitersStringPattern(delimiters);

            numbers = elements[1];
        }

        checkInvalidDelimitersInput(numbers, delimiters);

        String[] arrayOfNumbers = numbers.split(delimiters_pattern);
        for (String number: arrayOfNumbers) {
            int n = Integer.parseInt(number);
            if (n < 0) {
                negativeNumbers.append(", ").append(n);
            } else if (n > 1000)  {
                sum += 0;
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

    private static void expandDelimiters(ArrayList<String> delimitersList, String delimiters)
    {
        Arrays.stream(delimiters.split("]"))
                        .map(i -> i.contains("[") ? i.substring(1) : i)
                        .forEach(delimitersList::add);
    }

    private static String toDelimitersStringPattern(ArrayList<String> delimitersList) {
        return delimitersList.stream()
                .map(StringCalculator::metaCharacterToSimpleCharacter)
                .collect(Collectors.joining("|"));
    }

    private static void checkInvalidDelimitersInput(String numbers, ArrayList<String> delimiters) {
        int i = 0;
        StringBuilder cur = new StringBuilder();
        while (i != numbers.length()) {
            while (i != numbers.length()
                    && !Character.isDigit(numbers.charAt(i))
                    && numbers.charAt(i) != '-') {
                cur.append(numbers.charAt(i));
                i++;
            }
            if (cur.length() != 0) {
                if (delimiters.contains(cur.toString())) {
                    if (i - cur.length() == 0) {
                        throw new InvalidDelimitersInputException("Input start with delimiter: " + cur);
                    }
                    if (i == numbers.length()) {
                        throw new InvalidDelimitersInputException("Input end with delimiter: " + cur);
                    }
                    cur.delete(0, cur.length());
                } else {
                    if (i - cur.length() == 0) {
                        throw new InvalidDelimitersInputException("Input start with unknown delimiter: " + cur);
                    }
                    if (i == numbers.length()) {
                        throw new InvalidDelimitersInputException("Input end with unknown delimiter: " + cur);
                    }
                    throw new InvalidDelimitersInputException("Input contain unknown delimiter (start at index = "
                            + (i - cur.length()) + " end at index = " + (i - 1) + "): " + cur);
                }
            }

            i++;
        }
    }

    private static String metaCharacterToSimpleCharacter(String characters) {
        StringBuilder builder = new StringBuilder();
        for (char c: characters.toCharArray()) {
            builder.append("\\").append(c);
        }
        return builder.toString();
    }
}
