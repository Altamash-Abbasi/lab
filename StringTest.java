import java.util.*;

class StrOperation {
    String text;

    StrOperation(String text) {
        this.text = text;
    }

    String sentenceCase() {
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            line = line.trim();
            if (line.length() > 0)
                sb.append(Character.toUpperCase(line.charAt(0)))
                  .append(line.substring(1).toLowerCase());
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    String reverseWord(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    String reverseWords() {
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String w : words)
                sb.append(reverseWord(w)).append(" ");
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    String revSentence() {
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (int i = words.length - 1; i >= 0; i--)
                sb.append(reverseWord(words[i])).append(" ");
            sb.append("\n");
        }
        return sb.toString().trim();
    }

    int countWords() {
        String[] words = text.trim().split("\\s+");
        return text.trim().isEmpty() ? 0 : words.length;
    }

    int countChars() {
        return text.replace("\n", "").length();
    }

    int countLines() {
        return text.split("\n").length;
    }
}

public class StringTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text (type END in new line to stop):");
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.equals("END")) break;
            sb.append(line).append("\n");
        }
        String input = sb.toString().trim();
        StrOperation so = new StrOperation(input);
        System.out.println("\nOriginal String:\n" + input);
        System.out.println("\nEach Word Reversed:\n" + so.reverseWords());
        System.out.println("\nSentence Case:\n" + so.sentenceCase());
        System.out.println("\nSentences Reversed:\n" + so.revSentence());
        int ch;
        do {
            System.out.println("\n1. Count Words\n2. Count Characters\n3. Count Lines\n0. Exit");
            ch = sc.nextInt();
            if (ch == 1) System.out.println("Words: " + so.countWords());
            else if (ch == 2) System.out.println("Characters: " + so.countChars());
            else if (ch == 3) System.out.println("Lines: " + so.countLines());
        } while (ch != 0);
        sc.close();
    }
}