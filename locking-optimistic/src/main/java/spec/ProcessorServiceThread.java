package spec;

public class ProcessorServiceThread implements Runnable {

    private final ProcessorService processorService;
    private final String price;

    public ProcessorServiceThread(String price) {
        processorService = new ProcessorService();
        this.price = price;
    }

    @Override
    public void run() {
        processorService.changeProcessorPrice(1, price);
    }

}
