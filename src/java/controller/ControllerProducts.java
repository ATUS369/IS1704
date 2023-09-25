/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProducts;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ControllerProducts", urlPatterns = {"/ProductURL"})
public class ControllerProducts extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOProducts dao = new DAOProducts();
            String service = request.getParameter("service");
            if (service == null) {
                service = "viewAllProduct";
            }
            if (service.equals("viewAllProduct")) {
                Vector<Products> vector = dao.getAll("select * from Products");
                out.print(" <Table>\n"
                        + "    <tr>\n"
                        + "        <th>ProductID</th>\n"
                        + "        <th>ProductName</th>\n"
                        + "        <th>SupplierID</th>\n"
                        + "        <th>CategoryID</th>\n"
                        + "        <th>QuantityPerUnit</th>\n"
                        + "        <th>UnitPrice</th>\n"
                        + "        <th>UnitsInStock</th>\n"
                        + "        <th>UnitsOnOrder</th>\n"
                        + "        <th>ReorderLevel</th>\n"
                        + "        <th>Discontinued</th>\n"
                        + "        <th>update</th>\n"
                        + "        <th>delete</th>\n"
                        + "    </tr>");
                for (Products products : vector) {
                    out.print(" <tr>\n"
                            + "        <td>"+products.getProductID()+"</td>\n"
                            + "        <td>"+products.getProductName()+"</td>\n"
                            + "        <td>"+products.getSupplierID()+"</td>\n"
                            + "        <td>"+products.getCategoryID()+"</td>\n"
                            + "        <td>"+products.getQuantityPerUnit()+"</td>\n"
                            + "        <td>"+products.getUnitPrice()+"</td>\n"
                            + "        <td>"+products.getUnitsInStock()+"</td>\n"
                            + "        <td>"+products.getUnitsOnOrder()+"</td>\n"
                            + "        <td>"+products.getReorderLevel()+"</td>\n"
                            + "        <td>"+products.isDiscontinued()+"</td>\n"
                            + "        <td>update</td>\n"
                            + "        <td>delete</td>\n"
                            + "    </tr>");
                }
                
                out.print("</Table>");
            }
            if (service.equals("deleteProduct")) {
                int pid=Integer.parseInt(request.getParameter("id"));
                //delete
                
                
            }
            
            if (service.equals("insertProduct")) {
                //get data from form
                String proName = request.getParameter("proName");
                String supID = request.getParameter("supID");
                String cateid = request.getParameter("cateid");
                String quan = request.getParameter("quan");
                String price = request.getParameter("price");
                String unitStock = request.getParameter("unitStock");
                String UnitsOnOrder = request.getParameter("UnitsOnOrder");
                String reOrder = request.getParameter("reOrder");
                String dis = request.getParameter("dis");
                //check
                if (proName == null) {
                }
                //convert
                int supid = Integer.parseInt(supID);
                int CateId = Integer.parseInt(cateid);
                double unitPrice = Double.parseDouble(price);
                int unitstock = Integer.parseInt(unitStock);
                int unitsOnOrder = Integer.parseInt(UnitsOnOrder);
                int reorder = Integer.parseInt(reOrder);
                boolean disC = dis.equals("1") ? true : false;
                //create object
                Products pro = new Products(0, proName, supid,
                        CateId, quan, unitPrice, unitstock,
                        unitsOnOrder, reorder, disC);
                int n = dao.insertProductByPrepered(pro);
                response.sendRedirect("ProductURL");
            }

//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ControllerProducts</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ControllerProducts at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
