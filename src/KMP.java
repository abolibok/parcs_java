import parcs.*;

import java.util.ArrayList;
import java.util.List;

public class KMP implements AM {


    // A pattern searching function that uses Bad Character Heuristic of Boyer Moore Algorithm
    public Result search(String text, String pattern) {
        Result result = new Result();

        // Base Case 1: Y is null or empty
        if (Y == null || Y.length() == 0)
        {
            System.out.println("Pattern occurs with shift 0");
            return result;
        }

        // Base Case 2: X is null or X's length is less than that of Y's
        if (X == null || Y.length() > X.length())
        {
            System.out.println("Pattern not found");
            return result;
        }

        char[] chars = Y.toCharArray();

        // next[i] stores the index of next best partial match
        int[] next = new int[Y.length() + 1];
        for (int i = 1; i < Y.length(); i++)
        {
            int j = next[i + 1];

            while (j > 0 && chars[j] != chars[i])
                j = next[j];

            if (j > 0 || chars[j] == chars[i])
                next[i + 1] = j + 1;
        }

        for (int i = 0, j = 0; i < X.length(); i++)
        {
            if (j < Y.length() && X.charAt(i) == Y.charAt(j))
            {
                if (++j == Y.length())
                {
                    System.out.println("Pattern occurs with shift " +
                            (i - j + 1));
                    result.addIndex(i - j + 1);
                }
            }
            else if (j > 0)
            {
                j = next[j];
                i--;	// since i will be incremented in next iteration
            }
        }

        return result;
    }

    public void run(AMInfo info) {
        Input input = (Input) info.parent.readObject();
        String text = input.getText();
        String pattern = input.getPattern();

        System.out.println("Input : text = " + text + ", pattern = " + pattern);

        info.parent.write(search(text, pattern));
    }
}
