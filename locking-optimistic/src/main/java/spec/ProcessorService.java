package spec;

public class ProcessorService {

    private final ProcessorRepository processorRepository;

    public ProcessorService() {
        processorRepository = ProcessorRepository.getInstance();
    }

    public void insertProcessor(Processor processor) {
        processorRepository.insertProcessor(processor);
    }

    public void changeProcessorPrice(int id, String price) {
        processorRepository.changeProcessorPrice(id, price);
    }

}
