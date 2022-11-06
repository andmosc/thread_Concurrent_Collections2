import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Generate {
    private final static int STRING_LENGTH = 10_000;
    private final static int ROW_COUNT = 100_000;
    private final static String LETTERS = "abc";

    private final BlockingQueue<String> queueA;
    private final BlockingQueue<String> queueB;
    private final BlockingQueue<String> queueC;

    private final Random random = new Random();

    public Generate(BlockingQueue<String> queueA, BlockingQueue<String> queueB, BlockingQueue<String> queueC) {
        this.queueA = queueA;
        this.queueB = queueB;
        this.queueC = queueC;
    }

    private String generateText() {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < STRING_LENGTH; i++) {
            text.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }
        return text.toString();
    }

    public void addGeneratedText() {
        String text;
        for (int i = 0; i <= ROW_COUNT; i++) {
            try {
                text = generateText();
                queueA.put(text);
                queueB.put(text);
                queueC.put(text);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
