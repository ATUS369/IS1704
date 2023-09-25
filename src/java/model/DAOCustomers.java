/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author QUYNH TRANG
 */
public class DAOCustomers extends DBConnect {
    public int insert(Customer cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]\n" +
"           ([CustomerID]\n" +
"           ,[CompanyName]\n" +
"           ,[ContactName]\n" +
"           ,[ContactTitle]\n" +
"           ,[Address]\n" +
"           ,[City]\n" +
"           ,[Region]\n" +
"           ,[PostalCode]\n" +
"           ,[Country]\n" +
"           ,[Phone]\n" +
"           ,[Fax])\n" +
"      VALUES\n" +
"           ('"+cus.getCustomerID() +
"           ','"+cus.getCompanyName()+
"           ','"+cus.getContactName() +
"           ','"+cus.getContactTitle() +
"           ','"+ cus.getAddress() +
"           ','"+ cus.getCity() +
"           ','"+ cus.getRegion() +
"           ','"+ cus.getPostalCode() +
"           ','"+ cus.getCountry() +
"           ','"+ cus.getPhone() +
"           ','"+ cus.getFax()+"'),\n"+"('"+"HS123" +
"           ','"+cus.getCompanyName()+
"           ','"+cus.getContactName() +
"           ','"+cus.getContactTitle() +
"           ','"+ cus.getAddress() +
"           ','"+ cus.getCity() +
"           ','"+ cus.getRegion() +
"           ','"+ cus.getPostalCode() +
"           ','"+ cus.getCountry() +
"           ','"+ cus.getPhone() +
"           ','"+ cus.getFax()+"')";
        try {
            //Statement
            Statement state = conn.createStatement();
            //run
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }
    
    public int insertCustomerByPrepered (Customer cus) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Customers]\n" +
"           ([CustomerID]\n" +
"           ,[CompanyName]\n" +
"           ,[ContactName]\n" +
"           ,[ContactTitle]\n" +
"           ,[Address]\n" +
"           ,[City]\n" +
"           ,[Region]\n" +
"           ,[PostalCode]\n" +
"           ,[Country]\n" +
"           ,[Phone]\n" +
"           ,[Fax])\n" +
"           VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cus.getCustomerID());
            pre.setString(2, cus.getCompanyName());
            pre.setString(3, cus.getContactName());
            pre.setString(4, cus.getContactTitle());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getRegion());
            pre.setString(8, cus.getPostalCode());
            pre.setString(9, cus.getCountry());
            pre.setString(10, cus.getPhone());
            pre.setString(11, cus.getFax());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println(sql);
        return n;
    }
    
    public static void main(String[] args) {
        DAOCustomers dao = new DAOCustomers();
        int n = dao.insertCustomerByPrepered(new Customer("HE236"
           ,"FPT University"
           ,"trangllq"
           ,"sinh vien"
           ,"me linh"
           ,"ha noi"
           ,"sp"
           ,"hn1234"
           ,"viet nam"
           ,"098643156"
           ,"fax-abc"));
        if (n > 0) {
        System.out.println("inserted");
        }
    }
}
