/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Suppliers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOSuppliers extends DBConnect {
    
    public int insertByPrepared (Suppliers supp) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Suppliers]\n" +
"           ([CompanyName]\n" +
"           ,[ContactName]\n" +
"           ,[ContactTitle]\n" +
"           ,[Address]\n" +
"           ,[City]\n" +
"           ,[Region]\n" +
"           ,[PostalCode]\n" +
"           ,[Country]\n" +
"           ,[Phone]\n" +
"           ,[Fax]\n" +
"           ,[HomePage])\n" +
"       VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, supp.getCompanyName());
            pre.setString(2, supp.getContactName());
            pre.setString(3, supp.getContactTitle());
            pre.setString(4, supp.getAddress());
            pre.setString(5, supp.getCity());
            pre.setString(6, supp.getRegion());
            pre.setString(7, supp.getPostalCode());
            pre.setString(8, supp.getCountry());
            pre.setString(9, supp.getPhone());
            pre.setString(10, supp.getFax());
            pre.setString(11, supp.getHomePage());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int delete (int id) {
        int n = 0;
        String sql = "delete from Suppliers WHERE SupplierID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int update (Suppliers supp) {
        int n = 0;
        String sql = "UPDATE [dbo].[Suppliers]\n" +
"       SET [CompanyName] = ?" +
    "      ,[ContactName] = ?" +
    "      ,[ContactTitle] = ?" +
    "      ,[Address] = ?" +
    "      ,[City] = ? " +
    "      ,[Region] = ? " +
    "      ,[PostalCode] = ? " +
    "      ,[Country] = ?" +
    "      ,[Phone] = ?" +
    "      ,[Fax] = ? " +
    "      ,[HomePage] = ?" +
"       WHERE SupplierID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, supp.getCompanyName());
            pre.setString(2, supp.getContactName());
            pre.setString(3, supp.getContactTitle());
            pre.setString(4, supp.getAddress());
            pre.setString(5, supp.getCity());
            pre.setString(6, supp.getRegion());
            pre.setString(7, supp.getPostalCode());
            pre.setString(8, supp.getCountry());
            pre.setString(9, supp.getPhone());
            pre.setString(10, supp.getFax());
            pre.setString(11, supp.getHomePage());
            pre.setInt(12, supp.getSupplierID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public Vector<Suppliers> getAll (String sql) {
        Vector<Suppliers> vector = new Vector<Suppliers>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String compa = rs.getString(2);
                String conta = rs.getString(3);
                String title = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String region = rs.getString(7);
                String pos = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String fax = rs.getString(11);
                String hopa = rs.getString(12);
                Suppliers supp = new Suppliers(id, compa, conta, title, address,
                        city, region, pos, country, phone, fax, hopa);
                vector.add(supp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOSuppliers dao = new DAOSuppliers();
        int n;
//        n = dao.insertByPrepared(new Suppliers(0, "FPT", "trang", "font-end", 
//               "abc", "efg", "xyz", "12345E", "vietnam", "015468", "HN1245", null));
//        n = dao.delete(0);
//        n = dao.update(new Suppliers(0, "Coca", "trang", "backend", "abc",  "efg",
//                "xyz", "12345E", "vietnam", "015468", "HN1245", null));       
//        if (n > 0) {
//            System.out.println("inserted");
//        }
        Vector<Suppliers> vector = dao.getAll("select * from Suppliers");
        for (Suppliers suppliers : vector) {
            System.out.println(suppliers);
        }
    }
}
