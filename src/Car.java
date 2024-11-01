import java.util.Random;

public class Car implements Runnable {
	public static int DISTANCE = 200;
	public static int STEP = 2;

	private String name;

	public Car(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		int runDistance = 0;
		long startTime = System.currentTimeMillis();
		int speed = (new Random()).nextInt(20);

		while (runDistance < DISTANCE) {
			try {
				runDistance += speed;
				String log = "|";
				for (int i = 0; i < DISTANCE; i += STEP) {
					if (runDistance >= i + STEP) {
						log += "=";
					} else if (runDistance >= i && runDistance < i + STEP) {
						log += "o";
					} else {
						log += "-";
					}
				}
				log += "|";
				System.out.println("Car" + this.name + ": " + log + " " + Math.min(DISTANCE, runDistance) + "KM");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Car" + this.name + " broken...");
				break;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Car " + this.name + " finish in " + (endTime - startTime) / 1000 + "s");
	}

	public static void main(String[] args) {
		Car carA = new Car("A");
		Car carB = new Car("B");
		Car carC = new Car("C");

		Thread thread1 = new Thread(carA);
		Thread thread2 = new Thread(carB);
		Thread thread3 = new Thread(carC);

		System.out.println("Distance: 100KM");
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
