package vn.iotstart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vn.iotstart.configs.DBConnectMySQL;
import vn.iotstart.models.UserModel;

public class UserDaoImpl extends DBConnectMySQL implements IUserDao {

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        List<UserModel> list = new ArrayList<>();
        
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new UserModel(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("fullname"),
                    rs.getString("images"),
                    rs.getString("password"),
                    rs.getString("phone"),
                    rs.getInt("roleid"),
                    rs.getDate("createDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list; // Trả về danh sách người dùng, nếu lỗi trả về danh sách rỗng
    }

    @Override
    public UserModel findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        UserModel user = null;

        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("fullname"),
                        rs.getString("images"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getInt("roleid"),
                        rs.getDate("createDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user; // Trả về null nếu không tìm thấy
    }

    @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO users (id, username, email, fullname, images, password, phone, roleid, createDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getFullname());
            ps.setString(5, user.getImages());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getPhone());
            ps.setInt(8, user.getRoleid());
            ps.setDate(9, user.getCreateDate());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserModel findById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        UserModel user = null;

        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("fullname"),
                        rs.getString("images"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getInt("roleid"),
                        rs.getDate("createDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user; // Trả về null nếu không tìm thấy
    }

    @Override
    public void update(UserModel user) {
        String sql = "UPDATE users SET password = ? WHERE id = ?";
        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getPassword());
            ps.setInt(2, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserModel findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        UserModel user = null;

        try (Connection conn = super.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new UserModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("fullname"),
                        rs.getString("images"),
                        rs.getString("password"),
                        rs.getString("phone"),
                        rs.getInt("roleid"),
                        rs.getDate("createDate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user; // Trả về null nếu không tìm thấy
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        // Test method insert
        userDao.insert(new UserModel(4, "Duyaka", "abc1@gmail.com", "abcdef", "Khánh Duy", "", "0901234567", 1, new java.sql.Date(System.currentTimeMillis())));

        // Test method findAll
        List<UserModel> list = userDao.findAll();
        for (UserModel user : list) {
            System.out.println(user);
        }

        // Test method findByUserName
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to find: ");
        String username = scanner.nextLine();
        UserModel user = userDao.findByUserName(username);
        if (user != null) {
            System.out.println("User found: " + user);
        } else {
            System.out.println("Không tồn tại username");
        }

        // Test method findByEmail
        System.out.print("Enter email to find: ");
        String email = scanner.nextLine();
        UserModel userByEmail = userDao.findByEmail(email);
        if (userByEmail != null) {
            System.out.println("User found by email: " + userByEmail);
        } else {
            System.out.println("Không tồn tại email");
        }

        scanner.close();
    }
}
