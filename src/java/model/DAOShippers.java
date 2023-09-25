/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Shippers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOShippers extends DBConnect {
    public int insert (Shippers ship) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]\n" +
"           ([CompanyName]\n" +
"           ,[Phone])\n" +
"       VALUES\n" +
"           ('"+ship.getCompanyName()+"','" + ship.getPhone() + "')";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int insertByPrepered (Shippers ship) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]\n" +
"           ([CompanyName]\n" +
"           ,[Phone])\n" +
"           VALUES(?,?)";
        System.out.println(sql);
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ship.getCompanyName());
            pre.setString(2, ship.getPhone());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int update (Shippers ship) {
        int n = 0;
        String sql = "UPDATE [dbo].[Shippers]\n" +
"       SET [CompanyName] = ?\n" +
"           ,[Phone] = ?\n" +
"       WHERE ShipperID = ? ";
        System.out.println(sql);
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ship.getCompanyName());
            pre.setString(2, ship.getPhone());
            pre.setInt(3, ship.getShipperID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int delete (int id) {
        int n = 0;
        String sql = "delete from Shippers WHERE ShipperID = " + id; 
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public Vector<Shippers> getAll (String sql) {
        Vector<Shippers> vector = new Vector<Shippers>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                Shippers ship = new Shippers(id, name, phone);
                vector.add(ship);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOShippers dao = new DAOShippers();
        int n;
//        n = dao.insert(new Shippers(0, "FPT Uni", "0123456"));
//        n = dao.insertByPrepered(new Shippers(0, "VinGroup", "04561387"));
//        n = dao.update(new Shippers(5, "Coca", "0154863"));
//        n = dao.delete(0);
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        Vector<Shippers> ship = dao.getAll("select * from Shippers");
        for (Shippers shipper : ship) {
            System.out.println(shipper);
        }
                
    }
}
