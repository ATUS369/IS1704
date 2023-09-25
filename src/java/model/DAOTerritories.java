/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Region;
import entity.Territories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOTerritories extends DBConnect{
    
    public int insert (Territories teri) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Territories]\n" +
"           ([TerritoryID]\n" +
"           ,[TerritoryDescription]\n" +
"           ,[RegionID])\n" +
"       VALUES(?,?,?)";
        System.out.println(sql);
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, teri.getTerritoryID());
            pre.setString(2, teri.getTerritoryDescription());
            pre.setInt(3, teri.getRegionID());
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int deleteByTeri (String id) {
        int n = 0;
        String sql = "delete from Territories WHERE TerritoryID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public int updateTeri (Territories teri) {
        int n = 0;  
        String sql = "UPDATE [dbo].[Territories]\n" +
"       SET [TerritoryDescription] = ?" +
"           ,[RegionID] = ?" +
"       WHERE [TerritoryID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, teri.getTerritoryDescription());
            pre.setInt(2, teri.getRegionID());
            pre.setString(3, teri.getTerritoryID());
            
            n = pre.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
    
    public Vector<Territories> getAll (String sql) {
        Vector<Territories> vector = new Vector<Territories>();
        try {
            Statement state = conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                String teID = rs.getString(1);
                String tede = rs.getString(2);
                int reID = rs.getInt(3);
                Territories teri = new Territories(teID, tede, reID);
                vector.add(teri);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    
        return vector;
    }
    
    public static void main(String[] args) {
        DAOTerritories dao = new DAOTerritories();
        int n;
//        n = dao.insert(new Territories("12356", "Seattle", 3));
//        n = dao.deleteByTeri("12356");
        n = dao.updateTeri(new Territories("12347", "Santa Cruz", 3));
        if (n > 0) {
            System.out.println("inserted");
        }
        Vector<Territories> vector = dao.getAll("select * from Territories");
        for (Territories ter : vector) {
            System.out.println(ter);
        }
    }
}
