package com.example.webtodolist;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ToDoListDBUtil {
    private DataSource dataSource;

    public ToDoListDBUtil(DataSource theDataSource) {
        dataSource = theDataSource;
    }

    public List<ToDo> getToDo() throws Exception {
        List<ToDo> ToDos= new ArrayList<ToDo>();
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        try {
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select * from toDo";
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                int id = myRs.getInt("id");
                String description=myRs.getString("description");
                ToDo tempToDo= new ToDo(id, description);
                ToDos.add(tempToDo);
            }
            return ToDos;
        } finally{
            close(myConn,myStmt,myRs);
        }
    }

    public ToDo fetchToDo(int id) {
        Connection myConn=null;
        Statement myStmt = null;
        ResultSet myRs= null;
        ToDo toDo=null;
        try {
            myConn = dataSource.getConnection();
            myStmt= myConn.createStatement();
            String sql= "select * from toDo where id="+id;
            myRs = myStmt.executeQuery(sql);
            while(myRs.next()){
                String description=myRs.getString("description");
                toDo = new ToDo(id,description);
            }
            return toDo;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        } finally{
            close(myConn,myStmt,myRs);
        }
    }

    public void updateToDo(ToDo toDo) {
        Connection myConn=null;
        PreparedStatement myStmt = null;
        try {
            myConn = dataSource.getConnection();
            String sql = "update toDo set description=? where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, toDo.getDescription());
            myStmt.setInt(2, toDo.getId());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,null);
        }
    }

    public void addToDo(ToDo toDo) {
        Connection myConn=null;
        PreparedStatement myStmt = null;
        try {
            myConn = dataSource.getConnection();
            String sql = "insert into toDo(description) value(?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, toDo.getDescription());
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,null);
        }
    }

    public void deleteToDo(int id) {
        Connection myConn=null;
        PreparedStatement myStmt = null;
        try {
            myConn = dataSource.getConnection();
            String sql = "delete from toDo where id=?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, id);
            myStmt.execute();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            close(myConn,myStmt,null);
        }
    }

    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try{
            if(myStmt!=null)
                myStmt.close();
            if(myRs!=null)
                myRs.close();
            if(myConn!=null)
                myConn.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}

