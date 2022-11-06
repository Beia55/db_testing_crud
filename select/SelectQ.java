package dbTest.select;

import dbTest.DbHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectQ extends DbHelper{
    @Test(description = "Select from db orders table", enabled = false)
    public void selectOrders() {
        try {
            String query = "select * from classicmodels.orders where orderNumber between 10100 and 10200;";
            ResultSet results = stmt.executeQuery(query);
            int orderNo=10100;
            while (results.next()) {
                Assert.assertEquals(results.getInt("orderNumber"), orderNo);
                orderNo++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test(description = "Select from db offices table", enabled = false)
    public void selectOffices() {
        try {
            String query = "select * from classicmodels.offices where officeCode=7;";
            ResultSet results = stmt.executeQuery(query);
            while (results.next()) {
                Assert.assertEquals(results.getString("city"), "London");
                Assert.assertNull(results.getString("state"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test(description = "Select from db payments table",  enabled = false)
    public void selectPayments() {
        try {
            String query = "select count(customerNumber) from classicmodels.payments where customerNumber=112 group by customerNumber ;";
            ResultSet results = stmt.executeQuery(query);
            Assert.assertEquals(results.getInt(1), 3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Select from db employees table", enabled = false)
    public void selectEmployees() {
        try {
            String query = "select email FROM classicmodels.employees;";
            ResultSet results = stmt.executeQuery(query);
            while (results.next()) {
                Assert.assertTrue(results.getString(1).contains("@classicmodelcars.com"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Select from db orders table", enabled = false)
    public void selectOrdersStatus() {
        try {
            String query = "select status, shippedDate from classicmodels.orders where status=\"In Process\";";
            ResultSet results = stmt.executeQuery(query);
            int currentNo = 0;
            while (results.next()) {
                Assert.assertNull(results.getString(2));
                currentNo++;
            }
            Assert.assertEquals(currentNo, 6);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
