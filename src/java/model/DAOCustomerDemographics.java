/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerDemographics;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOCustomerDemographics extends DBConnect {

    public int insert(CustomerDemographics cdg) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerDemographics]\n" +
"           ([CustomerTypeID]\n" +
"           ,[CustomerDesc])\n" +
"     VALUES\n" +
"           ('" + cdg.getCustomerTypeID() + "','" +
                cdg.getCustomerDesc() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int insertByCustomerDemographics (CustomerDemographics cudeg) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerDemographics]\n" +
"           ([CustomerTypeID]\n" +
"           ,[CustomerDesc])\n" +
"       VALUES\n" +
"           (?,?)";
        System.out.println(sql);
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cudeg.getCustomerTypeID());
            pre.setString(2, cudeg.getCustomerDesc());
            n = pre.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int deleteByCustomerDemographics (String id) {
        int n = 0;
        String sql = "delete from CustomerDemographics WHERE CustomerTypeID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int update (CustomerDemographics cudg) {
        int n = 0;
        String sql = "UPDATE [dbo].[CustomerDemographics]\n" +
"       SET [CustomerDesc] = ?\n" +
"       WHERE [CustomerTypeID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cudg.getCustomerDesc());
            pre.setString(2, cudg.getCustomerTypeID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }
    
    public Vector<CustomerDemographics> getAll (String sql) {
        Vector<CustomerDemographics> vector = new Vector<CustomerDemographics>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String cutID = rs.getString(2);
                CustomerDemographics cudg = new CustomerDemographics(id, cutID);
                vector.add(cudg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCustomerDemographics dao = new DAOCustomerDemographics();
        int n;
//        n = dao.insert(new CustomerDemographics("abc", null));
//        n = dao.insertByCustomerDemographics(new CustomerDemographics("xyz", null));
//        n = dao.deleteByCustomerDemographics("'xyz'");
//        n = dao.update(new CustomerDemographics("abc", "ctrlc"));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        Vector<CustomerDemographics> vector = dao.getAll("select * from CustomerDemographics");
        for (CustomerDemographics cudg : vector) {
            System.out.println(cudg);
        }
    }
}
