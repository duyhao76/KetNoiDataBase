package vn.DuyHao.dao;

import java.util.List;

import vn.DuyHao.models.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
}
