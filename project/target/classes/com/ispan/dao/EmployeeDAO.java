package com.ispan.dao;

import javax.naming.NamingException;
import javax.sql.DataSource;
import com.ispan.bean.EmpBean;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.*;

public class EmployeeDAO {
    private DataSource dataSource;

    // **初始化連線池**
    public EmployeeDAO() throws NamingException {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/servdb");
            System.out.println("✅ DAO 初始化成功！");
        } catch (NamingException e) {
            System.out.println("❌ DAO 初始化失敗：" + e.getMessage());
            e.printStackTrace(); // 🔥 這行可以印出完整錯誤資訊
            throw e;
        }
    }

    // **新增員工**
    public boolean insertEmployee(EmpBean emp) {
        String sql = "INSERT INTO Employee (empno, ename, hiredate, salary, deptno, title) VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println("有進來 insertEmployee");
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getEmpno());
            stmt.setString(2, emp.getEname());
            stmt.setDate(3, Date.valueOf(emp.getHiredate()));
            stmt.setBigDecimal(4, new BigDecimal(emp.getSalary()));
            stmt.setString(5, emp.getDeptno());
            stmt.setString(6, emp.getTitle());

            int rows = stmt.executeUpdate();
            System.out.println("✅ 新增成功，影響行數：" + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("❌ 新增員工失敗：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // **刪除員工**
    public boolean deleteEmployee(String empno) {
        String sql = "DELETE FROM Employee WHERE empno = ?";
        System.out.println("有進來 deleteEmployee");
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empno);

            int rows = stmt.executeUpdate();
            System.out.println("✅ 刪除成功，影響行數：" + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("❌ 刪除員工失敗：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // **更新員工資料**
    public boolean updateEmployee(EmpBean emp) {
        String sql = "UPDATE Employee SET ename = ?, hiredate = ?, salary = ?, deptno = ?, title = ? WHERE empno = ?";
        System.out.println("有進來 updateEmployee");
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getEname());
            stmt.setDate(2, Date.valueOf(emp.getHiredate()));
            stmt.setBigDecimal(3, new java.math.BigDecimal(emp.getSalary()));
            stmt.setInt(4, Integer.parseInt(emp.getDeptno()));
            stmt.setString(5, emp.getTitle());
            stmt.setString(6, emp.getEmpno());

            int rows = stmt.executeUpdate();
            System.out.println("✅ 更新成功，影響行數：" + rows);
            return rows > 0;
        } catch (SQLException e) {
            System.out.println("❌ 更新員工失敗：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // **查詢單筆員工資料**
    public EmpBean getEmployeeByEmpno(String empno) {
        String sql = "SELECT * FROM Employee WHERE empno = ?";
        System.out.println("有進來 getEmployeeByEmpno");
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, empno);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("✅ 查詢成功：" + empno);
                return new EmpBean(
                    rs.getString("empno"),
                    rs.getString("ename"),
                    rs.getString("hiredate"),
                    rs.getString("salary"),
                    rs.getString("deptno"),
                    rs.getString("title")
                );
            }
            System.out.println("❌ 找不到員工：" + empno);
        } catch (SQLException e) {
            System.out.println("❌ 查詢員工失敗：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

 // **查詢所有員工**
    public List<EmpBean> getAllEmployees() {
        List<EmpBean> emps = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        System.out.println("有進來 getAllEmployees");
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                emps.add(new EmpBean(
                    rs.getString("empno"),
                    rs.getString("ename"),
                    rs.getString("hiredate"),
                    rs.getString("salary"),
                    rs.getString("deptno"),
                    rs.getString("title")
                ));
            }
            System.out.println("✅ 查詢成功，共 " + emps.size() + " 筆資料");
        } catch (SQLException e) {
            System.out.println("❌ 查詢所有員工失敗：" + e.getMessage());
            e.printStackTrace();
        }
        return emps;
    }
}
    
