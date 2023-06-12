package repository;

import model.Users;
import config.MysqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<Users> findByEmailAndPassword(String email, String password) {
        Connection connection = null;
        List<Users> userDTBList = new ArrayList<>();
        String sql = "select * from users u where u.email = ? and u.password = ?";
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                Users user_dtb = new Users();
                //Lấy giá trị của cột chỉ định (vd: id)
                user_dtb.setId(resultSet.getInt("id"));
                user_dtb.setEmail(resultSet.getString("email"));
                user_dtb.setPassword(resultSet.getString("password"));
                user_dtb.setFullname(resultSet.getString("fullname"));
                user_dtb.setRole_id(resultSet.getInt("role_id"));
                user_dtb.setAvatar(resultSet.getString("avatar"));
                userDTBList.add(user_dtb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query login " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối login ");
                    throw new RuntimeException(e);
                }
            }
        }
        return userDTBList;
    }

    public List<Users> findAllUser() {
        Connection connection = null;
        List<Users> userDTBList = new ArrayList<>();
        String sql = "select * from users u";
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setString(1,email);
            //statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                Users user_dtb = new Users();
                //Lấy giá trị của cột chỉ định (vd: id)
                user_dtb.setId(resultSet.getInt("id"));
                user_dtb.setEmail(resultSet.getString("email"));
                user_dtb.setFullname(resultSet.getString("fullname"));
                user_dtb.setRole_id(resultSet.getInt("role_id"));
                userDTBList.add(user_dtb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query login " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối login ");
                    throw new RuntimeException(e);
                }
            }
        }
        return userDTBList;
    }

    public boolean insertUser(String email, String password, String fullname, String avatar, int role_id) {
        Connection connection = null;
        //List<Users> userDTBList = new ArrayList<>();
        String sql = "insert into users (email,password,fullname, avatar, role_id) values(?,?,?,?,?)";
        boolean inserted = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setString(4,avatar);
            statement.setInt(5, role_id);
            inserted = statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi thực thi query login " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối login ");
                    throw new RuntimeException(e);
                }
            }
        }
        return inserted;
    }

    public boolean deleteUser(int id) {
        Connection connection = null;
        String sql = "delete from users u where u.id = ?";
        boolean deleted = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            deleted = statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi thực thi query delete " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối delete ");
                    throw new RuntimeException(e);
                }
            }
        }
        return deleted;
    }

    public boolean updateUser(String fullname, String email, String password, String avatar,int role_id, int id) {
        Connection connection = null;
        String sql = "update users u set u.fullname = ?, u.email= ?, u.password = ?, u.avatar = ?, u.role_id = ? where u.id = ?";
        boolean updated = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, fullname);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, avatar);
            statement.setInt(5, role_id);
            statement.setInt(6, id);

            updated = statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi thực thi query update " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối update ");
                    throw new RuntimeException(e);
                }
            }
        }
        return updated;
    }

    public Users findById(int id) {
        Connection connection = null;
        List<Users> userDTBList = new ArrayList<>();
        String sql = "select * from users u where u.id = ?";
        Users user_dtb;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            user_dtb = new Users();
            user_dtb.setId(resultSet.getInt("id"));
            user_dtb.setEmail(resultSet.getString("email"));
            user_dtb.setFullname(resultSet.getString("fullname"));
            user_dtb.setRole_id(resultSet.getInt("role_id"));
            user_dtb.setAvatar(resultSet.getString("avatar"));
            user_dtb.setPassword(resultSet.getString("password"));
            userDTBList.add(user_dtb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query findById " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findById ");
                    throw new RuntimeException(e);
                }
            }
        }
        //if() userDTBList
        return userDTBList.get(0);
    }
    public List<Users> findByRoleId(int role_id) {
        Connection connection = null;
        List<Users> userDTBList = new ArrayList<>();
        String sql = "select * from users u where u.role_id = ?";
        Users user_dtb;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, role_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user_dtb = new Users();
                user_dtb.setId(resultSet.getInt("id"));
                user_dtb.setEmail(resultSet.getString("email"));
                user_dtb.setFullname(resultSet.getString("fullname"));
                user_dtb.setRole_id(resultSet.getInt("role_id"));
                user_dtb.setAvatar(resultSet.getString("avatar"));
                user_dtb.setPassword(resultSet.getString("password"));
                userDTBList.add(user_dtb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query findById " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối findById ");
                    throw new RuntimeException(e);
                }
            }
        }
        //if() userDTBList
        return userDTBList;
    }

}
