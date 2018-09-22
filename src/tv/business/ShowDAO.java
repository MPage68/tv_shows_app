package tv.business;

import java.util.List;

public interface ShowDAO extends ShowConstants {
	List<Show> get(String genre);

	List<Show> get(int length);

	List<Show> getAll();

	boolean add(Show s);

	boolean update(Show s);

	boolean delete(Show s);
}