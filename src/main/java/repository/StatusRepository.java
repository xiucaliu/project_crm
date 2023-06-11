package repository;

import config.MysqlConfig;
import model.Status;
import model.Tasks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusRepository {
    public List<Status> findAllStatus(){
        Connection connection = null;
        List<Status> statusList = new ArrayList<>();
        String sql = "select * from status";
        try{
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                Status status = new Status();
                //Lấy giá trị của cột chỉ định (vd: id)
                status.setId(resultSet.getInt("id"));
                status.setName(resultSet.getString("name"));
                statusList.add(status);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query status " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối status");
                    throw new RuntimeException(e);
                }
            }

        }
        return statusList;
    }
    public Status findById(int id) {
        Connection connection = null;
        List<Status> statusDTBList = new ArrayList<>();
        String sql = "select * from status s where s.id = ?";
        Status status_dtb = null;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                status_dtb = new Status();
                status_dtb.setName(resultSet.getString("name"));
                statusDTBList.add(status_dtb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query status findById " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối status findById ");
                    throw new RuntimeException(e);
                }
            }
        }
        return statusDTBList.get(0);
    }
}
