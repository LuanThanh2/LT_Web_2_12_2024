package vn.iotstart.services.impl;

import vn.iotstart.models.UserModel;
import vn.iotstart.services.IUserService;
import vn.iotstart.dao.IUserDao;
import vn.iotstart.dao.UserDaoImpl;

public class UserService implements IUserService{
    // Lấy toàn bộ hàm trong tầng Dao của user
    IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel FindByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.FindByUserName(username);

        // Thêm log để kiểm tra giá trị username và password từ database
        if (user != null) {
            System.out.println("Username từ database: " + user.getUsername());
            System.out.println("Password từ database: " + user.getPassword());
        }

        // Kiểm tra nếu mật khẩu khớp
        if(user != null && password.equals(user.getPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public void insert(UserModel user) {
        userDao.insert(user);
    }

    public void updatePassword(String email, String newPassword) {
        // Không mã hóa mật khẩu
        UserModel user = findByEmail(email);
        if (user != null) {
            user.setPassword(newPassword); // Lưu trực tiếp mật khẩu mới
            userDao.update(user); // Cập nhật lại thông tin người dùng trong cơ sở dữ liệu
        }
    }


    public UserModel findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
