package com.AutoInc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AutoIncrement {
    public static void main(String[] args) throws ClassNotFoundException {
    	
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://localhost;user=Anemoi;password=Anemoi@123";
        String sql = "INSERT INTO InvestorDB.dbo.Employee21 (EmployeeID, OrderID, Name, Salary, Age) VALUES (?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             PreparedStatement statement = conn.prepareStatement(sql)) {

            // get the last value of EmployeeID and OrderID
            int lastEmployeeID = getLastValue("EmployeeID");
            int lastOrderID = getLastValue("OrderID");
        
            statement.setInt(1, lastEmployeeID + 1);
            statement.setInt(2, lastOrderID + 1);
            statement.setString(3, "Amol");
            statement.setInt(4, 50000);
            statement.setInt(5, 25);

            statement.executeUpdate();

            System.out.println("Inserted successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getLastValue(String column) throws ClassNotFoundException {
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://localhost;user=Anemoi;password=Anemoi@123";
      
        String sql = "SELECT MAX(" + column + ") FROM InvestorDB.dbo.Employee21";
        int lastValue = 0;

        try (Connection conn = DriverManager.getConnection(connectionUrl);
             Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                lastValue = result.getInt(1);
                System.out.println(lastValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastValue;
    }

}
