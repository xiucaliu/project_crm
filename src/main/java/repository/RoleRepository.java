package repository;

import model.Roles;
import config.MysqlConfig;
import model.Roles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<Roles> findAllRole(){
        Connection connection = null;
        List<Roles> roleList = new ArrayList<>();
        String sql = "select * from roles";
        try{
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setString(1,email);
            //statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                Roles role = new Roles();
                //Lấy giá trị của cột chỉ định (vd: id)
                role.setId(resultSet.getInt("id"));
                role.setName(resultSet.getString("name"));
                role.setDesc(resultSet.getString("description"));
                roleList.add(role);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query Roles " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối roles ");
                    throw new RuntimeException(e);
                }
            }

        }
        return roleList;
    }

    public boolean insertRole(String name, String desc) {
        Connection connection = null;
        String sql = "insert into roles (name,description) values(?,?)";
        boolean inserted = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, desc);
            inserted = statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi thực thi query insertRole " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối insertRole ");
                    throw new RuntimeException(e);
                }
            }
        }
        return inserted;
    }

    public boolean deleteRole(int id) {
        Connection connection = null;
        String sql = "delete from roles r where r.id = ?";
        boolean deleted = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            deleted = statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi thực thi query delete role " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối delete role ");
                    throw new RuntimeException(e);
                }
            }
        }
        return deleted;
    }
    public Roles findById(int id) {
        Connection connection = null;
        List<Roles> roleDTBList = new ArrayList<>();
        String sql = "select * from roles r where r.id = ?";
        Roles role_dtb = null;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                role_dtb = new Roles();
                role_dtb.setName(resultSet.getString("name"));
                role_dtb.setDesc(resultSet.getString("description"));
                roleDTBList.add(role_dtb);
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
        return roleDTBList.get(0);
    }

    public boolean updateRole(String name, String desc, int id) {
        Connection connection = null;
        String sql = "update roles r set r.name = ?, r.description = ? where r.id = ?";
        boolean updated = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, desc);
            statement.setInt(3, id);
            updated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query update role" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối update role");
                    throw new RuntimeException(e);
                }
            }
        }
        return updated;
    }
}
