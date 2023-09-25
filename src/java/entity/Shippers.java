/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import model.DBConnect;

/**
 *
 * @author QUYNH TRANG
 */
public class Shippers {
    private int ShipperID;
    private String CompanyName;
    private String Phone;

    public Shippers() {
    }

    public Shippers(int ShipperID, String CompanyName, String Phone) {
        this.ShipperID = ShipperID;
        this.CompanyName = CompanyName;
        this.Phone = Phone;
    }

    public int getShipperID() {
        return ShipperID;
    }

    public void setShipperID(int ShipperID) {
        this.ShipperID = ShipperID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    @Override
    public String toString() {
        return "Shippers{" + "ShipperID=" + ShipperID + ", CompanyName=" + CompanyName + ", Phone=" + Phone + '}';
    }
    
    
}
