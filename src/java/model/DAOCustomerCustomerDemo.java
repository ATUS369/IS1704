/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOCustomerCustomerDemo extends DBConnect {
    public int insert (CustomerCustomerDemo ccd) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n" +
"           ([CustomerID]\n" +
"           ,[CustomerTypeID])\n" +
"     VALUES\n" +
"           ('"+ccd.getCustomerID() + "','" +
            ccd.getCustomerTypeID()+"')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int insertByPrepered (CustomerCustomerDemo cude) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[CustomerCustomerDemo]\n" +
"           ([CustomerID]\n" +
"           ,[CustomerTypeID])\n" +
"           VALUES(?,?)";
        System.out.println(sql);
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cude.getCustomerID());
            pre.setString(2, cude.getCustomerTypeID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int deleteByCude (String id) {
        int n = 0;
        String sql = "delete from CustomerCustomerDemo WHERE CustomerID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int updateCude (CustomerCustomerDemo cude) {
        int n = 0;
        String sql = "UPDATE [dbo].[CustomerCustomerDemo]\n" +
"           SET [CustomerTypeID] = ?\n" +
"           WHERE CustomerID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cude.getCustomerTypeID());
            pre.setString(2, cude.getCustomerID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public Vector<CustomerCustomerDemo> getAll (String sql) {
        Vector<CustomerCustomerDemo> vector = new Vector<CustomerCustomerDemo>();
        try {
            Statement state = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String cutID = rs.getString(2);
                CustomerCustomerDemo cude = new CustomerCustomerDemo(id, cutID);
                vector.add(cude);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCustomerCustomerDemo dao = new DAOCustomerCustomerDemo();
        int n;
//        n = dao.insert(new CustomerCustomerDemo("HE234", "abc"));
//        n = dao.insertByPrepered(new CustomerCustomerDemo("HE234", "abc"));
//        n = dao.deleteByCude("'HE234'");
//        n = dao.updateCude(new CustomerCustomerDemo("HE234", "efg"));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        Vector<CustomerCustomerDemo> vector = dao.getAll("select * from CustomerCustomerDemo");
        for (CustomerCustomerDemo cus : vector) {
            System.out.println(cus);
        }
    }
}
