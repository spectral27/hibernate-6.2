package spec;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        processor.setModel("i7-1165G7");
        processor.setPrice("9.99");

        ProcessorService service = new ProcessorService();
        service.insertProcessor(processor);

        Thread thread1 = new Thread(new ProcessorServiceThread("19.99"));
        Thread thread2 = new Thread(new ProcessorServiceThread("29.99"));

        thread1.start();
        thread2.start();
    }

}
