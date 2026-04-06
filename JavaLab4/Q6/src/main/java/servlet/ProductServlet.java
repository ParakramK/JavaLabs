package servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import servlet.Product;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if(action == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else if(action.equals("add")) {
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
        }
        else if(action.equals("list")) {
            displayProducts(request, response);
        }
        else if(action.equals("search")) {
            searchProduct(request, response);
        }
        else if(action.equals("edit")) {
            editProduct(request, response);
        }
        else if(action.equals("delete")) {
            deleteProduct(request, response);
        }
        else if(action.equals("deleteform")) {
            request.getRequestDispatcher("deleteProduct.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if(action.equals("add")) {
            addProduct(request, response);
        }
        else if(action.equals("update")) {
            updateProduct(request, response);
        }
        else if(action.equals("search")) {
            searchProduct(request, response);
        }
    }
    
    
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/javadb", "root", "root");
    }
    
    // 1. ADD PRODUCT
    private void addProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String qty = request.getParameter("quantity");
        
        try {
            Connection con = getConnection();
            String sql = "INSERT INTO product (product_name, price, quantity) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, qty);
            
            ps.executeUpdate();
            con.close();
            
            response.sendRedirect("index.jsp?msg=added");
            
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("addProduct.jsp?error=1");
        }
    }
    
    // 2. DISPLAY ALL PRODUCTS
    private void displayProducts(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Product> productList = new ArrayList<>();
        
        try {
            Connection con = getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM product");
            
            while(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                productList.add(p);
            }
            
            con.close();
            
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("displayProducts.jsp").forward(request, response);
            
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=1");
        }
    }
    
    // 3. SEARCH PRODUCT
    private void searchProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE product_id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                
                request.setAttribute("product", p);
                request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
            } else {
                response.sendRedirect("searchProduct.jsp?notfound=1");
            }
            
            con.close();
            
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("searchProduct.jsp?error=1");
        }
    }
    
    // 4. EDIT PRODUCT (GET PRODUCT FOR EDITING)
    private void editProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM product WHERE product_id=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("product_id"));
                p.setName(rs.getString("product_name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                
                request.setAttribute("product", p);
                request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
            }
            
            con.close();
            
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("ProductServlet?action=list");
        }
    }
    
    // 5. UPDATE PRODUCT
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String qty = request.getParameter("quantity");
        
        try {
            Connection con = getConnection();
            String sql = "UPDATE product SET product_name=?, price=?, quantity=? WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, price);
            ps.setString(3, qty);
            ps.setString(4, id);
            
            ps.executeUpdate();
            con.close();
            
            response.sendRedirect("index.jsp?msg=updated");
            
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("ProductServlet?action=edit&id=" + id + "&error=1");
        }
    }
    
    // 6. DELETE PRODUCT
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM product WHERE product_id=?");
            ps.setString(1, id);
            ps.executeUpdate();
            
            con.close();
            response.sendRedirect("index.jsp?msg=deleted");
            
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("ProductServlet?action=list");
        }
    }
}