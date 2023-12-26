package game;

public class Launcher {
	public static void main(String[] args) {			
		Producer producer = new Producer("Producer");
        producer.start();

		Consumer consumer = new Consumer("Consumer",producer);
		consumer.start();
	}
}
