package vendingmachine.client;

public class ConsoleOutputClient implements OutputClient {
	@Override
	public void output(String message) {
		System.out.println(message);
	}
}
