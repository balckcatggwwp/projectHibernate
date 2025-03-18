package com.ispan.bean;

public class EmpBean {
    private String empno;
    private String ename;
    private String hiredate;
    private String salary;
    private String deptno;
    private String title;

    // **建構子（Constructor）**
    public EmpBean() {}

    public EmpBean(String empno, String ename, String hiredate, String salary, String deptno, String title) {
        this.empno = empno;
        this.ename = ename;
        this.hiredate = hiredate;
        this.salary = salary;
        this.deptno = deptno;
        this.title = title;
    }

    // **Getter & Setter 方法**
    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
