/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Employees;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOEmployees extends DBConnect {
    
    public int insert (Employees emp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employees]\n" +
"           ([LastName]\n" +
"           ,[FirstName]\n" +
"           ,[Title]\n" +
"           ,[TitleOfCourtesy]\n" +
"           ,[BirthDate]\n" +
"           ,[HireDate]\n" +
"           ,[Address]\n" +
"           ,[City]\n" +
"           ,[Region]\n" +
"           ,[PostalCode]\n" +
"           ,[Country]\n" +
"           ,[HomePhone]\n" +
"           ,[Extension]\n" +
"           ,[Photo]\n" +
"           ,[Notes]\n" +
"           ,[ReportsTo]\n" +
"           ,[PhotoPath])\n" +
"       VALUES\n" +
"           ('"+emp.getLastName()+"','"+emp.getFirstName()+"','"+emp.getTitle()
                +"','"+emp.getTitleOfCourtesy()+"','"+emp.getBirthDate()
                +"','"+emp.getHireDate()+"','"+emp.getAddress()+"','"+
                emp.getCity()+"','"+emp.getRegion()+"','"+emp.getPostalCode()+
                "','"+emp.getCountry()+"','"+emp.getHomePhone()+"','"+emp.getExtension()
                +"','"+emp.getPhoto()+"','"+emp.getNotes()+"',"+emp.getReportsTo()
                +",'"+emp.getPhotoPath()+"')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int insertByPrepared (Employees emp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Employees]\n" +
"           ([LastName]\n" +
"           ,[FirstName]\n" +
"           ,[Title]\n" +
"           ,[TitleOfCourtesy]\n" +
"           ,[BirthDate]\n" +
"           ,[HireDate]\n" +
"           ,[Address]\n" +
"           ,[City]\n" +
"           ,[Region]\n" +
"           ,[PostalCode]\n" +
"           ,[Country]\n" +
"           ,[HomePhone]\n" +
"           ,[Extension]\n" +
"           ,[Photo]\n" +
"           ,[Notes]\n" +
"           ,[ReportsTo]\n" +
"           ,[PhotoPath])\n" +
"       VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setString(5, emp.getBirthDate());
            pre.setString(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8,emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setString(14, emp.getPhoto());
            pre.setString(15, emp.getNotes());
            pre.setInt(16, emp.getReportsTo());
            pre.setString(17, emp.getPhotoPath());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }
    
    public int deleteByTeri (int id) {
        int n = 0;
        String sql = "delete from Employees WHERE EmployeeID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int updateEmp (Employees emp) {
        int n = 0;  
        String sql = "UPDATE [dbo].[Employees]\n" +
"       SET [LastName] = ?" +
"           ,[FirstName] = ?" +
"           ,[Title] = ?" +
"           ,[TitleOfCourtesy] = ?" +
"           ,[BirthDate] = ?" +
"           ,[HireDate] = ?" +
"           ,[Address] = ?" +
"           ,[City] = ?" +
"           ,[Region] = ?" +
"           ,[PostalCode] = ?" +
"           ,[Country] = ?" +
"           ,[HomePhone] = ?" +
"           ,[Extension] = ?" +
"           ,[Photo] = ?" +
"           ,[Notes] = ?" +
"           ,[ReportsTo] = ?" +
"           ,[PhotoPath] = ?" +
"       WHERE EmployeeID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, emp.getLastName());
            pre.setString(2, emp.getFirstName());
            pre.setString(3, emp.getTitle());
            pre.setString(4, emp.getTitleOfCourtesy());
            pre.setString(5, emp.getBirthDate());
            pre.setString(6, emp.getHireDate());
            pre.setString(7, emp.getAddress());
            pre.setString(8, emp.getCity());
            pre.setString(9, emp.getRegion());
            pre.setString(10, emp.getPostalCode());
            pre.setString(11, emp.getCountry());
            pre.setString(12, emp.getHomePhone());
            pre.setString(13, emp.getExtension());
            pre.setString(14, emp.getPhoto());
            pre.setString(15, emp.getNotes());
            pre.setInt(16, emp.getReportsTo());
            pre.setString(17, emp.getPhotoPath());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public Vector<Employees> getAll (String sql) {
        Vector<Employees> vector = new Vector<Employees>();
        try {
            Statement state = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String lname = rs.getString(2);
                String fname = rs.getString(3);
                String title = rs.getString(4);
                String titOfCours = rs.getString(5);
                String bdate = rs.getString(6);
                String hdate = rs.getString(7);
                String address = rs.getString(8);
                String city = rs.getString(9);
                String region = rs.getString(10);
                String poco = rs.getString(11);
                String country = rs.getString(12);
                String hophone = rs.getString(13);
                String exten = rs.getString(14);
                String photo = rs.getString(15);
                String notes = rs.getString(16);
                int repo = rs.getInt(17);
                String phopath = rs.getString(18);
                Employees emp = new Employees(id, lname, fname, title, 
                        titOfCours, bdate, hdate, address, city, region, 
                        poco, country, hophone, exten, photo, notes, repo, phopath);
                vector.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return vector;
    }
    
    public static void main(String[] args) {
        DAOEmployees dao = new DAOEmployees();
        int n;
//        n = dao.insert(new Employees(10, "hoa", "hoa", "management", "mrs", 
//                "2003-05-25", "2021-09-05", "abc", "hcl", null, "hn2930", "vietnam", 
//                "0987312", "1458", null, "xyzt", 3, null));
//        n = dao.deleteByTeri(10);
//        n = dao.updateEmp(new Employees(12, "trang", "luu", "management", "mrs", 
//                "2003-05-25", "2021-09-05", "abc", "hcl", null, "hn2930", "vietnam", 
//                "0987312", "1458", null, "xyzt", 3, null));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        Vector<Employees> vector = dao.getAll("select * from Employees");
        for (Employees emp : vector) {
            System.out.println(emp);
        }
    }
}
