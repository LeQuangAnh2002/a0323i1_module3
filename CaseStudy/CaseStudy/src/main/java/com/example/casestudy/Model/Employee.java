package com.example.casestudy.Model;

import java.util.Date;

public class Employee {
    private int employeeID;
    private String employeeName;
    private Date employeeBirthday;
    private String employeeIDCard;
    private Double employeeSalary;
    private String employeePhone;
    private String employeeEmail;
    private String employeeAddress;
    private Position positionID;
    private EducationDegree educationDegreeID;
    private Division divisionID;

    public Employee() {
    }

    public Employee(int employeeID, String employeeName, Date employeeBirthday, String employeeIDCard, Double employeeSalary, String employeePhone, String employeeEmail, String employeeAddress, Position positionID, EducationDegree educationDegreeID, Division divisionID) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.employeeBirthday = employeeBirthday;
        this.employeeIDCard = employeeIDCard;
        this.employeeSalary = employeeSalary;
        this.employeePhone = employeePhone;
        this.employeeEmail = employeeEmail;
        this.employeeAddress = employeeAddress;
        this.positionID = positionID;
        this.educationDegreeID = educationDegreeID;
        this.divisionID = divisionID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getEmployeeBirthday() {
        return employeeBirthday;
    }

    public void setEmployeeBirthday(Date employeeBirthday) {
        this.employeeBirthday = employeeBirthday;
    }

    public String getEmployeeIDCard() {
        return employeeIDCard;
    }

    public void setEmployeeIDCard(String employeeIDCard) {
        this.employeeIDCard = employeeIDCard;
    }

    public Double getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Double employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(String employeePhone) {
        this.employeePhone = employeePhone;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Position getPositionID() {
        return positionID;
    }

    public void setPositionID(Position positionID) {
        this.positionID = positionID;
    }

    public EducationDegree getEducationDegreeID() {
        return educationDegreeID;
    }

    public void setEducationDegreeID(EducationDegree educationDegreeID) {
        this.educationDegreeID = educationDegreeID;
    }

    public Division getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Division divisionID) {
        this.divisionID = divisionID;
    }
}
