package exercise;

// BEGIN
public class ListThread implements Runnable {
    private final SafetyList list;

    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            list.add(i);
            try {
                Thread.sleep(1); // Спим 1 миллисекунду
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
// END
