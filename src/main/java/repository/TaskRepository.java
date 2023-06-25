package repository;

import model.Tasks;
import config.MysqlConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskRepository {
    public List<Tasks> findAllTask() {
        Connection connection = null;
        List<Tasks> taskList = new ArrayList<>();
        String sql = "select * from tasks";
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setString(1,email);
            //statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                Tasks task = new Tasks();
                task.setId(resultSet.getInt("id"));
                task.setName(resultSet.getString("name"));
                task.setStartDate(resultSet.getDate("start_date"));
                task.setEndDate(resultSet.getDate("end_date"));
                task.setUserId(resultSet.getInt("user_id"));
                task.setJobId(resultSet.getInt("job_id"));
                task.setStatusId(resultSet.getInt("status_id"));
                taskList.add(task);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query Tasks " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối Tasks ");
                    throw new RuntimeException(e);
                }
            }
        }
        return taskList;
    }

    public boolean insertTask(String name, Date start_date, Date end_date, int user_id, int job_id, int status_id) {
        Connection connection = null;
        //List<Tasks> userDTBList = new ArrayList<>();
        String sql = "insert into tasks (name, start_date, end_date, user_id, job_id, status_id) values(?,?,?,?,?,?)";
        boolean inserted = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, (java.sql.Date) start_date);
            statement.setDate(3, (java.sql.Date) end_date);
            statement.setInt(4,user_id);
            statement.setInt(5,job_id);
            statement.setInt(6,status_id);
            inserted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query insertTask " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối insertTask ");
                    throw new RuntimeException(e);
                }
            }
        }
        return inserted;
    }
    public boolean deleteTask(int id) {
        Connection connection = null;
        String sql = "delete from tasks t where t.id = ?";
        boolean deleted = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            deleted = statement.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi thực thi query delete task " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối delete task ");
                    throw new RuntimeException(e);
                }
            }
        }
        return deleted;
    }
    public Tasks findById(int id) {
        Connection connection = null;
        List<Tasks> taskDTBList = new ArrayList<>();
        String sql = "select * from tasks t where t.id = ?";
        Tasks task_dtb = null;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                task_dtb = new Tasks();
                task_dtb.setId(resultSet.getInt("id"));
                task_dtb.setName(resultSet.getString("name"));
                task_dtb.setStartDate(resultSet.getDate("start_date"));
                task_dtb.setEndDate(resultSet.getDate("end_date"));
                task_dtb.setUserId(resultSet.getInt("user_id"));
                task_dtb.setJobId(resultSet.getInt("job_id"));
                task_dtb.setStatusId(resultSet.getInt("status_id"));
                taskDTBList.add(task_dtb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query task findById " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối task findById ");
                    throw new RuntimeException(e);
                }
            }
        }
        return taskDTBList.get(0);
    }
    public List<Tasks> findByUserId(int userId) {
        Connection connection = null;
        List<Tasks> taskDTBList = new ArrayList<>();
        String sql = "select * from tasks t where t.user_id = ?";
        //Tasks task_dtb = null;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Tasks task_dtb = new Tasks();
                task_dtb.setId(resultSet.getInt("id"));
                task_dtb.setName(resultSet.getString("name"));
                task_dtb.setStartDate(resultSet.getDate("start_date"));
                task_dtb.setEndDate(resultSet.getDate("end_date"));
                //task_dtb.setUser_id(resultSet.getInt("user_id"));
                task_dtb.setJobId(resultSet.getInt("job_id"));
                task_dtb.setStatusId(resultSet.getInt("status_id"));
                taskDTBList.add(task_dtb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query task findById " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối task findById ");
                    throw new RuntimeException(e);
                }
            }
        }
        return taskDTBList;
    }

    public boolean updateTask(String name, Date startDate, Date endDate,int userId,int jobId, int id) {
        Connection connection = null;
        String sql = "update tasks t set t.name = ?, t.start_date = ?, t.end_date = ?, t.user_id = ?, t.job_id = ? where t.id = ?";
        boolean updated = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, (java.sql.Date) startDate);
            statement.setDate(3, (java.sql.Date) endDate);
            statement.setInt(4, userId);
            statement.setInt(5, jobId);
            statement.setInt(6, id);
            updated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query update task" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối update task");
                    throw new RuntimeException(e);
                }
            }
        }
        return updated;
    }

    public boolean updateTaskStatus(int statusId, int id) {
        Connection connection = null;
        String sql = "update tasks t set t.status_id = ? where t.id = ?";
        boolean updated = false;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, statusId);
            statement.setInt(2, id);
            updated = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query update task" + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối update task");
                    throw new RuntimeException(e);
                }
            }
        }
        return updated;
    }
    public List<Tasks> findByJobId(int jobId) {
        Connection connection = null;
        List<Tasks> taskDTBList = new ArrayList<>();
        String sql = "select * from tasks t where t.job_id = ?";
        Tasks task_dtb = null;
        try {
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, jobId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                task_dtb = new Tasks();
                task_dtb.setId(resultSet.getInt("id"));
                task_dtb.setName(resultSet.getString("name"));
                task_dtb.setStartDate(resultSet.getDate("start_date"));
                task_dtb.setEndDate(resultSet.getDate("end_date"));
                task_dtb.setUserId(resultSet.getInt("user_id"));
                task_dtb.setJobId(resultSet.getInt("job_id"));
                task_dtb.setStatusId(resultSet.getInt("status_id"));
                taskDTBList.add(task_dtb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query task findByJobId " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Lỗi đóng kết nối task findByJobId ");
                    throw new RuntimeException(e);
                }
            }
        }
        return taskDTBList;
    }
}
