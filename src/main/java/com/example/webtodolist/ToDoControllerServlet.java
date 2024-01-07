package com.example.webtodolist;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/ToDoControllerServlet")
public class ToDoControllerServlet extends HttpServlet {

    private ToDoListDBUtil toDoListDBUtil;
    private DataSource dataSource;

    public ToDoControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private DataSource getDataSource() throws NamingException {
        String jndi="java:comp/env/jdbc/toDoList" ;
        Context context = new InitialContext();
        DataSource dataSource = (DataSource) context.lookup(jndi);
        return dataSource;
    }
    @Override
    public void init() throws ServletException {
        // TODO Auto-generated method stub
        super.init();
        try {
            dataSource= getDataSource();
            toDoListDBUtil = new ToDoListDBUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            session.setAttribute("username", request.getRemoteUser());
            Cookie cookie = new Cookie("usernameCookie", request.getRemoteUser());
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            listToDo(request,response);
        } catch (Exception e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void listToDo(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        List<ToDo> toDos = toDoListDBUtil.getToDo();
        request.setAttribute("TODO_LIST", toDos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-toDo.jsp");
        dispatcher.forward(request, response);
    }

}
