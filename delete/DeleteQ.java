package dbTest.delete;

import dbTest.DbHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteQ extends DbHelper {
    @Test(description = "Delete from db payments table", enabled = false)
    public void deletePayment() {
        try {
            String select = "select * from classicmodels.payments ;";
            ResultSet initialResults = stmt.executeQuery(select);
            int initialNoOfRows = 0;
            while (initialResults.next()) {
                initialNoOfRows++;
            }

            String deleted = "delete from classicmodels.payments where checkNumber='EU531600';";
            stmt.executeUpdate(deleted);

            ResultSet finalResults = stmt.executeQuery(select);
            int finalNoOfRows = 0;
            while (finalResults.next()) {
                finalNoOfRows++;
            }

            Assert.assertNotEquals(initialNoOfRows, finalNoOfRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Delete from db customers table", enabled = false)
    public void deletePaymentsV2() {
        try {
            String deleted = "delete from classicmodels.payments where checkNumber='MB342426';";
            stmt.executeUpdate(deleted);
            String select = "select * from classicmodels.payments where checkNumber='MB342426';";
            ResultSet result = stmt.executeQuery(select);
            int noOfRows = 0;
            while (result.next()) {
                noOfRows++;
            }
            Assert.assertEquals(noOfRows, 0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Delete from db orderdetails table", enabled = false)
    public void deleteOrderdetail() {
        try {
            String insert = "delete from classicmodels.orderdetails where productCode='S72_3212';";
            boolean isRow = stmt.execute(insert);
            Assert.assertTrue(!isRow);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Delete from db orderdetails table", enabled = false)
    public void deleteOrderdetailV2() {
        try {
            String deleted = "delete from classicmodels.orderdetails where productCode='S72_1253';";
            boolean isRow = stmt.execute(deleted);
            String select = "select * from classicmodels.orderdetails;";
            ResultSet result = stmt.executeQuery(select);
            while (result.next()) {
                Assert.assertNotEquals(result.getString("productCode"), "S72_1253");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Delete from db employees table", enabled = false)
    public void deleteEmployees() {
        try {
            String deleted = "delete from classicmodels.employees where employeeNumber='1625';";
            stmt.executeUpdate(deleted);
            String select = "select * from classicmodels.employees ;";
            ResultSet result = stmt.executeQuery(select);
            while (result.next()) {
                Assert.assertTrue(!(result.getString("employeeNumber").contains("1625")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
