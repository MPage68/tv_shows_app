package tv.business;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import tv.util.DBUtil;

public class ShowDB implements ShowDAO {	

	@Override
	public List<Show> get(String genre) {
		String sql = "select * from tvshow where genre = ?";
		List<Show> shows = new ArrayList<>();
		Show s = null;
		try (Connection connection = DBUtil.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, genre);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String rating = rs.getString(3);
				int length = rs.getInt(4);
				String g = rs.getString(5);
				String network = rs.getString(6);
				s = new Show(id, name, rating, length, g, network);
				shows.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return shows;
	}

	@Override
	public List<Show> get(int length) {
		String sql = "select * from tvshow where genre = ?";
		List<Show> shows = new ArrayList<>();
		Show s = null;
		try (Connection connection = DBUtil.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setInt(1, length);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String rating = rs.getString(3);
				int l = rs.getInt(4);
				String genre = rs.getString(5);
				String network = rs.getString(6);
				s = new Show(name, rating, l, genre, network);
				shows.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Show> getAll() {
		String sql = "SELECT * FROM tvshow";
		List<Show> shows = new ArrayList<>();
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String rating = rs.getString(3);
				int length = rs.getInt(4);
				String genre = rs.getString(5);
				String network = rs.getString(6);
				Show s = new Show(id, name, rating, length, genre, network);
				shows.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shows;
	}

	@Override
	public boolean add(Show s) {
		String sql = "INSERT INTO tvshow (Name, Rating, Length, Genre, Network) " + "VALUES (?, ?, ?, ?, ?)";
		try (Connection connection = DBUtil.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, s.getName());
			ps.setString(2, s.getRating());
			ps.setInt(3, s.getLength());
			ps.setString(4, s.getGenre());
			ps.setString(5, s.getNetwork());
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean update(Show s) {
		String sql = "UPDATE tvshow SET " + "  Name = ?, " + "  Rating = ?," + "Length = ?," + "Genre = ?," + "Network = ?"
				+ "WHERE ID = ?";
		try (Connection connection = DBUtil.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, s.getName());
			ps.setString(2, s.getRating());
			ps.setInt(3, s.getLength());
			ps.setString(4, s.getGenre());
			ps.setString(5, s.getNetwork());
			ps.setInt(6, s.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	@Override
	public boolean delete(Show s) {
		String sql = "DELETE FROM tvshow " + "WHERE ID = ?";
		try (Connection connection = DBUtil.getConnection(); 
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, s.getId());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}

	}
}
