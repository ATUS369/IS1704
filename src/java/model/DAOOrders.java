/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOOrders extends DBConnect{
    public int insertByPrepared (Orders ord) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Orders]\n" +
"           ([CustomerID]\n" +
"           ,[EmployeeID]\n" +
"           ,[OrderDate]\n" +
"           ,[RequiredDate]\n" +
"           ,[ShippedDate]\n" +
"           ,[ShipVia]\n" +
"           ,[Freight]\n" +
"           ,[ShipName]\n" +
"           ,[ShipAddress]\n" +
"           ,[ShipCity]\n" +
"           ,[ShipRegion]\n" +
"           ,[ShipPostalCode]\n" +
"           ,[ShipCountry])" +
"       VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getShipVia());
            pre.setDouble(7, ord.getFreight());
            pre.setString(8,ord.getShipName());
            pre.setString(9, ord.getShipAddress());
            pre.setString(10, ord.getShipCity());
            pre.setString(11, ord.getShipRegion());
            pre.setString(12, ord.getShipPostalCode());
            pre.setString(13, ord.getShipCountry());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }
    
    public int deleteByOrders (int id) {
        int n = 0;
        String sql = "delete from Orders WHERE OrderID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int updateOrders (Orders ord) {
        int n = 0;  
        String sql = "UPDATE [dbo].[Orders]\n" +
"   SET [CustomerID] = ?" +
"      ,[EmployeeID] = ?" +
"      ,[OrderDate] = ?" +
"      ,[RequiredDate] = ?" +
"      ,[ShippedDate] = ?" +
"      ,[ShipVia] = ?" +
"      ,[Freight] = ?" +
"      ,[ShipName] = ?" +
"      ,[ShipAddress] = ?" +
"      ,[ShipCity] = ?" +
"      ,[ShipRegion] = ?" +
"      ,[ShipPostalCode] = ?" +
"      ,[ShipCountry] = ?" +
" WHERE OrderID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ord.getCustomerID());
            pre.setInt(2, ord.getEmployeeID());
            pre.setString(3, ord.getOrderDate());
            pre.setString(4, ord.getRequiredDate());
            pre.setString(5, ord.getShippedDate());
            pre.setInt(6, ord.getShipVia());
            pre.setDouble(7, ord.getFreight());
            pre.setString(8, ord.getShipName());
            pre.setString(9, ord.getShipAddress());
            pre.setString(10, ord.getShipCity());
            pre.setString(11, ord.getShipRegion());
            pre.setString(12, ord.getShipPostalCode());
            pre.setString(13, ord.getShipCountry());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public Vector<Orders> getAll (String sql) {
        Vector<Orders> vector = new Vector<Orders>();
        try {
            Statement state = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String cusID = rs.getString(2);
                int emID = rs.getInt(3);
                String orDate = rs.getString(4);
                String reDate = rs.getString(5);
                String shDate = rs.getString(6);
                int shVia = rs.getInt(7);
                double freight = rs.getDouble(8);
                String shName = rs.getString(9);
                String shAdr = rs.getString(10);
                String shCity = rs.getString(11);
                String shRe = rs.getString(12);
                String shPo = rs.getString(13);
                String shCo = rs.getString(14);
                Orders ord = new Orders(id, cusID, emID, orDate, reDate, shDate,
                        shVia, freight, shName, shAdr, shCity, shRe, shPo, shCo);
                vector.add(ord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return vector;
    }
    
    public static void main(String[] args) {
        DAOOrders dao = new DAOOrders();
        int n;
//        n = dao.insertByPrepared(new Orders(10200, "HE234", 6, "1999-07-03", 
//                "1993-05-06", "1999-05-07", 2, 15.78, "anata shiri", "abc", 
//                "hanoi", "sp", "hn458", "vietnam"));
        n = dao.deleteByOrders(10200);
        n = dao.updateOrders(new Orders(10200, "HE235", 6, "1999-07-03", 
                "1993-05-06", "1999-05-07", 2, 40.3, "sakura", "abc", 
                "hanoi", "sp", "hn458", "vietnam"));
        if (n > 0) {
            System.out.println("inserted");
        }
        Vector<Orders> vector = dao.getAll("select * from Orders");
        for (Orders orders : vector) {
            System.out.println(orders);
        }
    }
}
