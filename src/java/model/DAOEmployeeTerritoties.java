/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.EmployeeTerritories;
import entity.Region;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOEmployeeTerritoties extends DBConnect{
    public int insert (EmployeeTerritories emte) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[EmployeeTerritories]\n" +
"           ([EmployeeID]\n" +
"           ,[TerritoryID])\n" +
"     VALUES(?,?)";
        System.out.println(sql);
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, emte.getEmployeeID());
            pre.setString(2, emte.getTerritoryID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int deleteByEmte (int id) {
        int n = 0;
        String sql = "delete from EmployeeTerritories WHERE EmployeeID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int updateEmte (EmployeeTerritories emte) {
        int n = 0;  
        String sql = "UPDATE [dbo].[EmployeeTerritories]\n" +
"       SET [TerritoryID] = ?" +
"       WHERE EmployeeID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, emte.getTerritoryID());
            pre.setInt(2, emte.getEmployeeID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public Vector<EmployeeTerritories> getAll (String sql) {
        Vector<EmployeeTerritories> vector = new Vector<EmployeeTerritories>();
        try {
            Statement state = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String teID = rs.getString(2);
                EmployeeTerritories emte = new EmployeeTerritories(id, teID);
                vector.add(emte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return vector;
    }
    
    public static void main(String[] args) {
        DAOEmployeeTerritoties dao = new DAOEmployeeTerritoties();
        int n;
        n = dao.insert(new EmployeeTerritories(1, "12345"));
        if (n > 0) {
            System.out.println("inserted");
        }
    }
}
