/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOCategories extends DBConnect{
    public int insert (Categories cate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n" +
"           ([CategoryName]\n" +
"           ,[Description]\n" +
"           ,[Picture])\n" +
"     VALUES\n" +
"           ('"+
            cate.getCategoryName()+"','" +
            cate.getDescription()+"', '" +
            cate.getPicture()+"')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    public int insertByPrepered (Categories cate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n" +
"           ([CategoryName]\n" +
"           ,[Description]\n" +
"           ,[Picture])\n" +
"       VALUES(?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            pre.setString(3, cate.getPicture());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int deleteCategories (int id) {
        int n = 0;
        String sql = "delete from Categories WHERE CategoryID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int updateCatergories (Categories cate) {
        int  n = 0;
        String sql = "UPDATE [dbo].[Categories]\n" +
"           SET [CategoryName] = ?\n" +
"           ,[Description] = ?\n" +
"           ,[Picture] = ?\n" +
"           WHERE CategoryID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            pre.setString(3, cate.getPicture());
            pre.setInt(4, cate.getCategoryID());
            n = pre.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }
    
    public Vector<Categories> getAll (String sql) {
        Vector<Categories> vector = new Vector<Categories>();
        try {
            Statement state = conn.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String cname = rs.getString(2);
                String desp = rs.getString(3);
                String pic = rs.getString(4);
                Categories cate = new Categories(id, cname, desp, pic);
                vector.add(cate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOCategories dao = new DAOCategories();
        int n;
        //n = dao.insert(new Categories(0, "Shirt", "Short", null));
//        n = dao.insertByPrepered(new Categories( 0, "Dress", "Red", null));
//        //n = dao.deleteCategories(10);
//        if (n>0) {
//            System.out.println("inserted");
//        }
        //n = dao.updateCatergories(new Categories(10, "Dress", "Red", null));
        Vector<Categories> vector = dao.getAll("select * from Categories");
        for (Categories cate : vector) {
            System.out.println(cate);
        }
    }
}
