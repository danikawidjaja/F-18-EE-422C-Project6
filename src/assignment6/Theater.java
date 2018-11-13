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

import java.util.ArrayList;
import java.util.List;

public class Theater {
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;
		private boolean taken =false;

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
			taken = true;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}
		
		private String convertToRow(int seatNumber) {
			if (seatNumber<26) {
				return Character.toString((char) (seatNumber+64));
			}
			else {
				String last = Character.toString((char)((seatNumber%26) + 64));
				String first = convertToRow(seatNumber/26);
				return first+last;
			}
		}
		@Override
		public String toString() {
			String seat = Integer.toString(seatNum);
			String row = convertToRow(rowNum);
			return row+seat;
			
		}
	}

  /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
		private int client;
		
		private int width = 31;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return a string that resembles a ticket
			String ticket ="";
			for (int i=0; i<width; i++) {
				ticket = ticket.concat("-");
			}
			ticket = ticket.concat("\n");
			
			String line1 = line ("Show: " + show);
			String line2 = line("Box Office ID: " + boxOfficeId);
			String line3 = line("Seat: " + seat);
			String line4 = line("Client: " + client);
			
			ticket = ticket + line1 + line2 + line3 + line4;
			
			for (int i=0; i<width; i++) {
				ticket = ticket.concat("-");
			}
			ticket = ticket.concat("\n");
			return ticket;
		}
		
		private String line (String message) {
			String l = "| "+ message ;
			if (l.length() <width) {
				int space = width - l.length()-1;
				String padding = spaces(space);
				l += padding;
			}
			l += "|\n";
			return l;
		}
		
		private String spaces(int n) {
			String s= "";
			for (int i=0; i<n; i++) {
				s+=" ";
			}
			return s;
		}
	}
	
	private int numRows;
	private int seatsPerRow;
	private String show;
	private int totalSeats;
	private int currentRowNumber;
	private int currentSeatNumber;
	private List<Ticket> ticketSold;
	
	public Theater(int numRows, int seatsPerRow, String show) {
		// TODO: Implement this constructor
		this.numRows = numRows;
		this.seatsPerRow = seatsPerRow;
		this.show = show;
		this.totalSeats = numRows*seatsPerRow;
		this.currentRowNumber = 1;
		this.currentSeatNumber = 1;
		this.ticketSold = new ArrayList<Ticket>();
	}

	/*
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
   */
	public Seat bestAvailableSeat() {
		//TODO: Implement this method
		if (getTransactionLog().size() == totalSeats) {
			return null;
		}
		Seat currentSeat = new Seat(currentRowNumber, currentSeatNumber);
		if (currentSeatNumber%(seatsPerRow) == 0) currentRowNumber++;
		currentSeatNumber = (currentSeatNumber+1)%(seatsPerRow);
		if (currentSeatNumber == 0) currentSeatNumber = seatsPerRow;
		return currentSeat;
		
	}

	/*
	 * Prints a ticket for the client after they reserve a seat
   * Also prints the ticket to the console
	 *
   * @param seat a particular seat in the theater
   * @return a ticket or null if a box office failed to reserve the seat
   */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		//TODO: Implement this method
		Ticket myTicket = new Ticket(this.show, boxOfficeId, seat, client);
		System.out.println(myTicket);
		ticketSold.add(myTicket);
		return myTicket;
	}

	/*
	 * Lists all tickets sold for this theater in order of purchase
	 *
   * @return list of tickets sold
   */
	public List<Ticket> getTransactionLog() {
		//TODO: Implement this method
		return ticketSold;
	}
}
