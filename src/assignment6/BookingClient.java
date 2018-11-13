/* MULTITHREADING <MyClass.java>
 * EE422C Project 6 submission by
 * Replace <...> with your actual data.
 * Danika Widjaja
 * dew2532
 * 16345
 * Slip days used: <0>
 * Fall 2018
 */
package assignment6;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.lang.Thread;

public class BookingClient{
	private Map<String, Integer> office;
	private Theater theater;
  /*
   * @param office maps box office id to number of customers in line
   * @param theater the theater where the show is playing
   */
  public BookingClient(Map<String, Integer> office, Theater theater) {
    // TODO: Implement this constructor
	  this.office = office;
	  this.theater = theater;
  }

  /*
   * Starts the box office simulation by creating (and starting) threads
   * for each box office to sell tickets for the given theater
   *
   * @return list of threads used in the simulation,
   *         should have as many threads as there are box offices
   */
	public List<Thread> simulate() {
		//TODO: Implement this method
		ArrayList<Thread> listOfThread = new ArrayList<Thread>();
		for (String id: office.keySet()) {
			int numberOfCustomer = office.get(id);
			Runnable cashier = new Cashier(numberOfCustomer, id, theater);
			Thread t = new Thread(cashier);
			listOfThread.add(t);
		}
		for (Thread t: listOfThread) {
			t.start();
		}
		return listOfThread;
	}
	
	public static void main(String[] args) {
		// Init
		Map<String, Integer> office = new HashMap<String,Integer>();
		office.put("BX1", 3);
		office.put("BX3", 3);
		office.put("BX2", 4);
		office.put("BX5", 3);
		office.put("BX4", 3);
		
		Theater myTheater = new Theater(3,5,"Ouija");
		BookingClient bookingClient = new BookingClient(office, myTheater);
		bookingClient.simulate();
		
	}
}
