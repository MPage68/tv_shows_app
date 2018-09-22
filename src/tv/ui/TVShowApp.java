package tv.ui;

import java.awt.DisplayMode;
import java.util.ArrayList;
import java.util.List;

import tv.business.Show;
import tv.business.ShowDB;
import tv.util.Console;

public class TVShowApp {
	private static ShowDB showDB = new ShowDB();

	public static void main(String[] args) {
		System.out.println("Welcome to the TV Show DB App/n");

		String command = "";
		List<Show> shows = new ArrayList<>();
		while (!command.equalsIgnoreCase("6")) {
			displayMenu();
			command = Console.getString("Enter command: ");
			if (command.equals("1")) {
				displayAllShowsInDB();
			} else if (command.equals("2")) {
				displayShowsByTypeOrLength();
			} else if (command.equalsIgnoreCase("3")) {
				addShow();
			} else if (command.equalsIgnoreCase("4")) {
				updateShow();
			} else if (command.equalsIgnoreCase("5")) {
				deleteShow();
			}

			System.out.println("Result of search");
			for (Show s : shows) {
				System.out.println(s);
			}
			if (!command.equals(" ")) {
				System.out.println("Invalid command. Try again.");
			}
		}
		System.out.println("bye");
	}

	public static void displayAllShowsInDB() {
		List<Show> shows;
		shows = showDB.getAll();
		printReportHeaders();
		for (Show s : shows) {
			printReportDetail(s);
		}
		
	}

	public static void displayShowsByTypeOrLength() {
		List<Show> shows;
		String criteria = Console.getString("Search by (g)enre or (l)ength?", "g", "l");
		shows = new ArrayList<>();
		if (criteria.equalsIgnoreCase("g")) {
			String genre = Console.getString("What type of genre?  Comedy, SciFi, Action, Drama");
			shows = showDB.get(genre);

		} else if (criteria.equalsIgnoreCase("l")) {
			int length = Console.getInt("Enter show length?  30, 60, 90");
			shows = showDB.get(length);
		}
		for (Show show : shows) {
			printReportDetail(show);
		}
		
	}

	private static void displayMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("COMMAND MENU\n");
		sb.append("1 - List TV Shows\n");
		sb.append("2 - List TV Shows by Genre or Length\n");
		sb.append("3 - Add TV Show\n");
		sb.append("4 - Update TV Show\n");
		sb.append("5 - Delete TV Show\n");
		sb.append("6 - Exit\n");
		System.out.println(sb);
	}

	private static void printReportHeaders() {
		StringBuilder sb = new StringBuilder();
		sb.append(padReportDetail("Name", ShowDB.NAME_LENGTH));
		sb.append("  ");
		sb.append(padReportDetail("Rating", ShowDB.RATING_LENGTH));
		sb.append("  ");
		sb.append(padReportDetail("Length", ShowDB.LENGTH_LENGTH));
		sb.append("  ");
		sb.append(padReportDetail("Genre", ShowDB.GENRE_LENGTH));
		sb.append("  ");
		sb.append(padReportDetail("Network", ShowDB.NETWORK_LENGTH));
		sb.append("\n");
		sb.append(padReportDetail("", ShowDB.NAME_LENGTH, "="));
		sb.append("  ");
		sb.append(padReportDetail("", ShowDB.RATING_LENGTH, "="));
		sb.append("  ");
		sb.append(padReportDetail("", ShowDB.LENGTH_LENGTH, "="));
		sb.append("  ");
		sb.append(padReportDetail("", ShowDB.GENRE_LENGTH, "="));
		sb.append("  ");
		sb.append(padReportDetail("", ShowDB.NETWORK_LENGTH, "="));
		sb.append("\n");
		System.out.println(sb.toString());
	}

	private static void printReportDetail(Show s) {
		// use string builder and add 2 spaces between
		StringBuilder sb = new StringBuilder(padReportDetail(s.getName(), ShowDB.NAME_LENGTH) + " ");
		sb.append(padReportDetail(s.getRating(), ShowDB.RATING_LENGTH) + " ");
		sb.append(padReportDetail(Integer.toString(s.getLength()), ShowDB.LENGTH_LENGTH) + " ");
		sb.append(padReportDetail(s.getGenre(), ShowDB.GENRE_LENGTH) + " ");
		sb.append(padReportDetail(s.getNetwork(), ShowDB.NETWORK_LENGTH));
		System.out.println(sb.toString());
	}

	public static void addShow() {
		String name = Console.getString("Enter show name: ");
		String rating = Console.getString("Enter show rating: ");
		int length = Console.getInt("Enter length: ");
		String genre = Console.getString("Enter genre: ");
		String network = Console.getString("Enter show network: ");

		Show show = new Show(name, rating, length, genre, network);

		boolean success = showDB.add(show);
		if (success) {
			System.out.println(show + " has been added to the database.\n");
		} else {
			System.out.println("Error! Unable to add show.\n");
		}
	}

	public static void updateShow() {
		int id = Console.getInt("Enter ID of show to update: ");
		String name = Console.getString("Enter show name to update: ");
		String rating = Console.getString("Enter show rating to update: ");
		int length = Console.getInt("Enter length to update: ");
		String genre = Console.getString("Enter genre to update: ");
		String network = Console.getString("Enter show network to update: ");

		Show show = new Show(id, name, rating, length, genre, network);

		boolean success = showDB.update(show);
		if (success) {
			System.out.println(show + " has been updated in the database.\n");
		} else {
			System.out.println("Error! Unable to update show.\n");
		}
	}

	public static void deleteShow() {
		int id = Console.getInt("Enter ID of show to delete: ");

		Show show = new Show();
		show.setId(id);

		boolean success = showDB.delete(show);
		if (success) {
			System.out.println(show + " has been deleted from the database.\n");
		} else {
			System.out.println("Error! Unable to delete show.\n");
		}
	}

	private static String getReportHeaders() {
		StringBuilder sb = new StringBuilder("Name");
		return sb.toString();

	}

	private static String padReportDetail(String val, int length) {
		return padReportDetail(val, length, " ");
	}

	private static String padReportDetail(String val, int length, String padChar) {
		StringBuilder sb = new StringBuilder(val);
		while (sb.length() <= length) {
			sb.append(padChar);
		}
		// sb.append(" ");
		return sb.toString();
	}
}
