package vn.iotstart.services;

import vn.iotstart.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);
	UserModel FindByUserName (String username);
	void insert(UserModel user);
	void updatePassword(String email, String newPassword);
	UserModel findByEmail(String email);
}
