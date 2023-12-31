package repository;

import model.Jobs;
import config.MysqlConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobRepository {
    public List<Jobs> findAllJob() {
        Connection connection = null;
        List<Jobs> jobList = new ArrayList<>();
        String sql = "select * from jobs";
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                Jobs job = new Jobs();
                //Lấy giá trị của cột chỉ định (vd: id)
                job.setId(resultSet.getInt("id"));
                job.setLeaderId(resultSet.getInt("leader_id"));
                job.setName(resultSet.getString("name"));
                job.setStartDate(resultSet.getDate("start_date"));
                job.setEndDate(resultSet.getDate("end_date"));
                jobList.add(job);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query jobs " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối jobs ");
                    throw new RuntimeException(e);
                }
            }

        }
        return jobList;
    }

    public boolean insertJob(String name, int leaderId, Date startDate, Date endDate) {
        Connection connection = null;
        String sql = "insert into jobs (name,leader_id, start_date, end_date) values(?,?,?,?)";
        boolean inserted = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, leaderId);
            statement.setDate(3, (java.sql.Date) startDate);
            statement.setDate(4, (java.sql.Date) endDate);
            inserted = statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi thực thi query insertJob " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối insertJob ");
                    throw new RuntimeException(e);
                }
            }
        }
        return inserted;
    }

    public boolean deleteJob(int id) {
        Connection connection = null;
        String sql = "delete from jobs j where j.id = ?";
        boolean deleted = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            deleted = statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi thực thi query delete job" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối delete job");
                    throw new RuntimeException(e);
                }
            }
        }
        return deleted;
    }

    public Jobs findById(int id) {
        Connection connection = null;
        List<Jobs> jobDTBList = new ArrayList<>();
        String sql = "select * from jobs u where u.id = ?";
        Jobs job_dtb = null;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                job_dtb = new Jobs();
                job_dtb.setId(resultSet.getInt("id"));
                job_dtb.setName(resultSet.getString("name"));
                job_dtb.setStartDate(resultSet.getDate("start_date"));
                job_dtb.setEndDate(resultSet.getDate("end_date"));
                job_dtb.setLeaderId(resultSet.getInt("leader_id"));
                jobDTBList.add(job_dtb);
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
        return jobDTBList.get(0);
    }

    public boolean updateJob(String name, Date startDate, Date endDate, int leaderId, int id) {
        Connection connection = null;
        String sql = "update jobs j set j.name = ?, j.start_date = ?, j.end_date = ?, j.leader_id = ? where j.id = ?";
        boolean updated = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, (java.sql.Date) startDate);
            statement.setDate(3, (java.sql.Date) endDate);
            statement.setInt(4, leaderId);
            statement.setInt(5, id);
            updated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query update job" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối update job");
                    throw new RuntimeException(e);
                }
            }
        }
        return updated;
    }

    public List<Jobs> findByLeaderId(int leaderId) {
        Connection connection = null;
        List<Jobs> jobDTBList = new ArrayList<>();
        String sql = "select * from jobs u where u.leader_id = ?";
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, leaderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Jobs job_dtb = new Jobs();
                job_dtb.setId(resultSet.getInt("id"));
                job_dtb.setName(resultSet.getString("name"));
                job_dtb.setStartDate(resultSet.getDate("start_date"));
                job_dtb.setEndDate(resultSet.getDate("end_date"));
                job_dtb.setLeaderId(resultSet.getInt("leader_id"));
                jobDTBList.add(job_dtb);
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
        return jobDTBList;
    }

}
