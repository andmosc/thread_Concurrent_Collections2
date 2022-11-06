import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private final static int CAPACITY_QUEUE = 100;

    private final static BlockingQueue<String> queueA = new ArrayBlockingQueue<>(CAPACITY_QUEUE);
    private final static BlockingQueue<String> queueB = new ArrayBlockingQueue<>(CAPACITY_QUEUE);
    private final static BlockingQueue<String> queueC = new ArrayBlockingQueue<>(CAPACITY_QUEUE);

    public static void main(String[] args) throws InterruptedException {
        Generate generate = new Generate(queueA, queueB, queueC);
        CalcSymbol symbolA = new CalcSymbol(queueA, 'a');
        CalcSymbol symbolB = new CalcSymbol(queueB, 'b');
        CalcSymbol symbolC = new CalcSymbol(queueC, 'c');

        new Thread(generate::addGeneratedText).start();


        new Thread(() -> System.out.println("Максимальная строка с символом а: " + symbolA.maxLine())).start();
        new Thread(() -> System.out.println("Максимальная строка с символом b: " + symbolB.maxLine())).start();
        new Thread(() -> System.out.println("Максимальная строка с символом c: " + symbolC.maxLine())).start();
    }
}





