package vn.DuyHao.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.DuyHao.configs.DBConnectSQL;
import vn.DuyHao.dao.IUserDao;
import vn.DuyHao.models.UserModel;

public class UserDaoImpl extends DBConnectSQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "select * from Table_1";

		List<UserModel> list = new ArrayList<>();

		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString(4), rs.getString(5)));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findById(int id) {
		String sql = "select * from Table_1 where id = ?";

		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new UserModel(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("images"), rs.getString("fullname"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "insert into Table_1 (id, username, password, images, fullname) values (?, ?, ?, ?, ?)";

		try {
			conn = super.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getImages());
			ps.setString(5, user.getFullname());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
	    UserDaoImpl userDao = new UserDaoImpl();
	    
	    int userId = 2;
	    
	    UserModel users = userDao.findById(userId);

	    if (users != null) {
	            System.out.println("ID: " + users.getId());
	            System.out.println("Username: " + users.getUsername());
	            System.out.println("Password: " + users.getPassword());
	            System.out.println("Images: " + users.getImages());
	            System.out.println("Fullname: " + users.getFullname());
	            System.out.println("----------------------------");
	     }
	    	else {
	    	System.out.println("User with ID " + userId + " not found.");
	    }
	}


}
