import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class CalcSymbol {
    private final BlockingQueue<String> queue;
    private final static int ROW_COUNT = 100_000;

    private final char symbol;
    private String textMax;
    private String text;
    private int count;
    private int countMax;

    public CalcSymbol(BlockingQueue<String> queue, char symbol) {
        this.queue = queue;
        this.symbol = symbol;
    }

    public String maxLine() {
        for (int i = 0; i <= ROW_COUNT; i++) {
            try {
                text = queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            char[] charArray = text.toCharArray();
            for (char c : charArray) {
                if (c == symbol) {
                    count++;
                }
            }
            if (countMax < count) {
                countMax = count;
                textMax = Arrays.toString(charArray);
            }
            count = 0;
        }
        return textMax;
    }
}
