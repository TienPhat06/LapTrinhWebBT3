package murach.email;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.util.Calendar;
import java.util.GregorianCalendar;

import murach.business.User;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = "/email.jsp";

        // Bước 6: Lấy năm hiện tại và gán vào request attribute
        GregorianCalendar currentDate = new GregorianCalendar();
        int currentYear = currentDate.get(Calendar.YEAR);
        request.setAttribute("currentYear", currentYear);

        // Lấy tham số action
        String action = request.getParameter("action");
        if (action == null) {
            action = "join";
        }

        // Bước 7: In thông báo debug ra Tomcat Console
        System.out.println("EmailListServlet - action (Console): " + action);

        // Bước 9: Ghi thông báo debug vào Tomcat Log File
        log("EmailListServlet - action (Log File): " + action);

        if (action.equals("join")) {
            url = "/email.jsp";
        }
        else if (action.equals("add")) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");

            // Khởi tạo User bằng đúng constructor 4 tham số có sẵn
            User user = new User(firstName, lastName, email, "");

            // Bước 4: Kiểm tra tính hợp lệ (Validation) phía Server
            String message;
            if (firstName == null || lastName == null || email == null ||
                    firstName.trim().isEmpty() || lastName.trim().isEmpty() || email.trim().isEmpty()) {
                message = "Please fill out all three text boxes.";
                url = "/email.jsp";
            } else {
                message = "";
                url = "/thanks_email.jsp";
            }

            request.setAttribute("user", user);
            request.setAttribute("message", message);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // Bước 6: Hỗ trợ HTTP GET gọi sang doPost
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}