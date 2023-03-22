import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        char c = '*';
        if (Character.isWhitespace(c) || Character.isLetterOrDigit(c)) {
            System.out.println(c + " is not a meta character");
        } else {
            System.out.println(c + " is a meta character");
        }
    }
}
