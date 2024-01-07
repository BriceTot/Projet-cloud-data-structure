package com.example.webtodolist;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet("/EditToDoServlet")
public class EditToDoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ToDoListDBUtil toDoListDbUtil;
    private DataSource dataSource;
    int id;
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
            this.dataSource= getDataSource();
            toDoListDbUtil = new ToDoListDBUtil(dataSource);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    public EditToDoServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id=Integer.parseInt(request.getParameter("toDoId"));
        ToDo toDo= toDoListDbUtil.fetchToDo(id);
        request.setAttribute("ToDo", toDo);
        request.getRequestDispatcher("edit-toDo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String description= request.getParameter("description");
        ToDo toDo = new ToDo(id, description);
        toDoListDbUtil.updateToDo(toDo);
        response.sendRedirect("ToDoControllerServlet");
    }
}
