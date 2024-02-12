package Unit3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock implements Runnable {
	private volatile String currentTime;

	public Clock() {
		updateTime();
	}

	// Method to update time
	private void updateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
		currentTime = formatter.format(new Date());
	}

	// Runnable method for thread
	public void run() {
		while (true) {
			updateTime();
			try {
				Thread.sleep(1000); // Update time every second
			} catch (InterruptedException e) {
				System.out.println("Clock thread interrupted: " + e.getMessage());
			}
		}
	}

	// Method to get current time
	public String getCurrentTime() {
		return currentTime;
	}

	public static void main(String[] args) {
		Clock clock = new Clock();
		Thread clockThread = new Thread(clock);
		clockThread.setPriority(Thread.MIN_PRIORITY); // Set lower priority for updating thread

		Thread displayThread = new Thread(() -> {
			while (true) {
				System.out.println(clock.getCurrentTime());
				try {
					Thread.sleep(1000); // Print time every second
				} catch (InterruptedException e) {
					System.out.println("Display thread interrupted: " + e.getMessage());
				}
			}
		});
		displayThread.setPriority(Thread.MAX_PRIORITY); // Set higher priority for display thread

		clockThread.start();
		displayThread.start();
	}
}
