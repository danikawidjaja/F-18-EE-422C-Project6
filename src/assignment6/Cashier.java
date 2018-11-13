package assignment6;

public class Cashier implements Runnable{
	private int numberOfCustomer;
	private String boxOfficeId;
	private Theater theater;
	private static int client = 1;
	static Object lock = new Object(); //should be static
	
	public Cashier(int numberOfCustomer, String boxOfficeId, Theater theater) {
		this.numberOfCustomer = numberOfCustomer;
		this.boxOfficeId = boxOfficeId;
		this.theater = theater;
	}

	@Override
	public void run() {
		while (numberOfCustomer!=0) {
			synchronized (lock) {
				Theater.Seat seat = theater.bestAvailableSeat();
				if (seat == null) {
					System.out.println("Sorry, we are sold out!");
					return;
				}
				Theater.Ticket ticket = theater.printTicket(boxOfficeId, seat, client);
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				client++;
				numberOfCustomer--;
			}
			
		}
		
	}

}
