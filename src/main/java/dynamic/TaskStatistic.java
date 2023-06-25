package dynamic;
import model.Status;
import model.Tasks;
import model.Users;
import service.TaskStatisticService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class TaskStatistic extends HttpServlet {
    TaskStatisticService taskStatisticService = new TaskStatisticService();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession sessionUser = req.getSession();
        Users user = (Users) sessionUser.getAttribute("user");
        List<Tasks> tasksList;
        if (user.getRoleId() == 1) {
            tasksList = taskStatisticService.tasksList();
        } else if (user.getRoleId() == 2) {
            tasksList = taskStatisticService.findTaskListByLeaderId(user.getId());
        } else {
            tasksList = taskStatisticService.findTaskListByUserId(user.getId());
        }
        List<Status> statusList = taskStatisticService.findAllStatus();
        req.setAttribute("statusList", statusList);
        taskStatisticService.countTaskStatus(statusList, tasksList);
        //req.setAttribute("tasksList", tasksList);
    }
}
