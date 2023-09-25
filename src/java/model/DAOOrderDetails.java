/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOOrderDetails extends DBConnect{
    public int insertByPrepared (OrderDetails orde) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order Details]\n" +
"           ([OrderID]\n" +
"           ,[ProductID]\n" +
"           ,[UnitPrice]\n" +
"           ,[Quantity]\n" +
"           ,[Discount])\n" +
"     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, orde.getOrderID());
            pre.setInt(2, orde.getProductID());
            pre.setDouble(3, orde.getUnitPrice());
            pre.setInt(4, orde.getQuantity());
            pre.setDouble(5, orde.getDiscount());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int deleteOrderDetail (int id) {
        int n = 0;
        String sql = "delete from [Order Details] WHERE OrderID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int updateOrderDetail (OrderDetails orde) {
        int  n = 0;
        String sql = "UPDATE [dbo].[Order Details]\n" +
"       SET [ProductID] = ?" +
"           ,[UnitPrice] = ?" +
"           ,[Quantity] = ?" +
"           ,[Discount] = ?" +
"       WHERE [OrderID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, orde.getProductID());
            pre.setDouble(2, orde.getUnitPrice());
            pre.setInt(3, orde.getQuantity());
            pre.setDouble(4, orde.getDiscount());
            pre.setInt(5, orde.getOrderID());
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }
    
    public Vector<OrderDetails> getAll (String sql) {
        Vector<OrderDetails> vector = new Vector<OrderDetails>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                int proID = rs.getInt(2);
                double money = rs.getDouble(3);
                int quan = rs.getInt(4);
                double discount = rs.getInt(5);
                OrderDetails orde = new OrderDetails(id, proID, money, quan, discount);
                vector.add(orde);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOOrderDetails dao = new DAOOrderDetails();
        int n;
//        n = dao.insertByPrepared(new OrderDetails(10280, 11, 18.50, 5, 0.20));
//        n = dao.deleteOrderDetail(10280);
//        n = dao.updateOrderDetail(new OrderDetails(10280, 20, 20.15, 10, 0.15));
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        Vector<OrderDetails> vector = dao.getAll("select * from [Order Details]");
        for (OrderDetails orderDetails : vector) {
            System.out.println(orderDetails);
        }
    }
}
