/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.CustomerCustomerDemo;
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
public class DAORegion extends DBConnect{
    
    public int insert (Region rg) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Region]\n" +
"           ([RegionID]\n" +
"           ,[RegionDescription])\n" +
"     VALUES(?,?)";
        System.out.println(sql);
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, rg.getRegionID());
            pre.setString(2, rg.getRegionDescription());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int deleteByCude (int id) {
        int n = 0;
        String sql = "delete from Region WHERE RegionID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int updateRede (Region rg) {
        int n = 0;  
        String sql = "UPDATE [dbo].[Region]\n" +
"       SET [RegionDescription] = ?\n" +
"       WHERE [RegionID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, rg.getRegionDescription());
            pre.setInt(2, rg.getRegionID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public Vector<Region> getAll (String sql) {
        Vector<Region> vector = new Vector<Region>();
        try {
            Statement state = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String rede = rs.getString(2);
                Region reg =  new Region(id, rede);
                vector.add(reg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return vector;
    }
    
    public static void main(String[] args) {
        DAORegion dao = new DAORegion();
        int n;
        n = dao.insert(new Region(0, "abc"));
//        n = dao.deleteByCude(0);
        n = dao.updateRede(new Region(0, "Eastern"));
        if (n > 0) {
            System.out.println("inserted");
        }
        Vector<Region> vector = dao.getAll("select * from Region");
        for (Region region : vector) {
            System.out.println(region);
        }
    }
}
