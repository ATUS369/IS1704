/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Products;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOProducts extends DBConnect {

    public int insert(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "     VALUES\n"
                + "           ('" + pro.getProductName() + "'," + pro.getSupplierID() + "," + pro.getCategoryID()
                + ",'" + pro.getQuantityPerUnit() + "'," + pro.getUnitPrice() + ","
                + pro.getUnitsInStock() + "," + pro.getUnitsOnOrder() + "," + pro.getReorderLevel() + ","
                + (pro.isDiscontinued() == true ? 1 : 0) + ")";
        System.out.println(sql);
        try {
            //Statement
            Statement state = conn.createStatement();
            //run
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;

    }

    public int insertProductByPrepered(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductName]\n"
                + "           ,[SupplierID]\n"
                + "           ,[CategoryID]\n"
                + "           ,[QuantityPerUnit]\n"
                + "           ,[UnitPrice]\n"
                + "           ,[UnitsInStock]\n"
                + "           ,[UnitsOnOrder]\n"
                + "           ,[ReorderLevel]\n"
                + "           ,[Discontinued])\n"
                + "           VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            //IndexOf? start 1;
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.isDiscontinued() == true ? 1 : 0);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sql);
        return n;
    }

    public int updateProduct(Products pro) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "       SET [ProductName] = ?\n"
                + "      ,[SupplierID] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[QuantityPerUnit] = ?\n"
                + "      ,[UnitPrice] = ?\n"
                + "      ,[UnitsInStock] = ?\n"
                + "      ,[UnitsOnOrder] = ?\n"
                + "      ,[ReorderLevel] = ?\n"
                + "      ,[Discontinued] = ?"
                + "       WHERE ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            //IndexOf? start 1;
            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierID());
            pre.setInt(3, pro.getCategoryID());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReorderLevel());
            pre.setInt(9, pro.isDiscontinued() == true ? 1 : 0);
            pre.setInt(10, pro.getProductID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sql);
        return n;
    }
    
    public int updateProductName (int id, String name) {
        int n = 0;
        String sql = "UPDATE [dbo].[Products]\n"
                + "       SET [ProductName] = ?\n"
                + "       WHERE ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, para);
            //IndexOf? start 1;
            pre.setString(1, name);
            pre.setInt(2, id);
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(sql);
        return n;
    }

    public int deleteProduct(int id) {
        int n = 0;
        String sql = "delete from Products where ProductID =" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Products> getAll(String sql) {
        Vector<Products> vector = new Vector<Products>();
        //sensitive co thread safe de dam bao an toan, giai quyet van de xung dot giong String Builder
        //executeUpdate: insert, update, delete
        //executeQuery: select
        //execute: create, drop, alter.
        try {
            Statement state = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql); 
            while (rs.next()) {
                int id = rs.getInt("ProductID"); //int id = rs.getInt(1);
                String pname = rs.getString(2); // String pname=RS.getString("ProductName");
                int supID = rs.getInt(3);
                int cateID = rs.getInt(4);
                String unit = rs.getString(5);
                double price = rs.getDouble(6);
                int unitIn = rs.getInt(7);
                int unitOut = rs.getInt(8);
                int reOrder = rs.getInt(9);
                int temp = rs.getInt(10);
                boolean dis = (temp == 1 ? true : false);
                Products pro = new Products(id, pname, supID, cateID, unit, price, unitIn, unitOut, reOrder, dis);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }

        return vector;
    }

    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
//        int n = dao.insert(new Products(0, "demoProduct", 1, 1, "abc", 100, 1, 1, 0, true));
//        int  n = dao.updateProduct(new Products(81, "demoProduct", 1, 1, "abcd", 100, 1, 1, 0, true));
        int n = dao.updateProductName(81, "ahihi");
//        n = dao.deleteProduct(80);
//        if (n > 0) {
//            System.out.println("inserted");
//        }
//        Vector<Products> vector = dao.getAll("select * from Products");
//        for (Products pro : vector) {
//            System.out.println(pro);
//        }
    }
}
