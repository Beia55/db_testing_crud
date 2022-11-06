package dbTest.update;

import dbTest.DbHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateQ extends DbHelper {
    @Test(description = "Update from offices table", enabled = false)
    public void updateOffice() {
        try {
            String update = "update classicmodels.offices set `phone` = '+441234567891' where `officeCode` = 7;";
            stmt.executeUpdate(update);
            String select = "select * from classicmodels.offices where `officeCode` = 7;";
            ResultSet result = stmt.executeQuery(select);
            while (result.next()) {
                Assert.assertEquals(result.getString("phone"), "+441234567891");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Update from product table", enabled = false)
    public void updateProduct() {
        try {
            String select = "select * from classicmodels.products where `productCode` = 'S10_1949';";
            int quantity = 0;
            ResultSet initialResult = stmt.executeQuery(select);
            while (initialResult.next()) {
                quantity=initialResult.getInt("quantityInStock");
            }
            String update = "update classicmodels.products set `quantityInStock` = '7307' where `productCode` = 'S10_1949';";
            stmt.executeUpdate(update);
            ResultSet finalResult = stmt.executeQuery(select);
            while (finalResult.next()) {
                Assert.assertNotEquals(quantity, finalResult.getInt("quantityInStock"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Update from productlines table", enabled = false)
    public void updateProductline() {
        try {
            String update = "update classicmodels.productlines set `htmlDescription` = 'not null' where `productline` = 'Ships';";
            stmt.executeUpdate(update);
            String select = "select * from classicmodels.productlines where `productline` = 'Ships';";
            ResultSet result = stmt.executeQuery(select);
            while (result.next()) {
                Assert.assertNotNull(result.getString("htmlDescription"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Update from payments table", enabled = false)
    public void updatePayment() {
        try {
            String update = "update classicmodels.payments set `amount` = 7000, `paymentDate` = '2022-11-15'  where `checkNumber` = 'HQ336336';";
            stmt.executeUpdate(update);
            String select = "select * from classicmodels.payments where `checkNumber` = 'HQ336336';";
            ResultSet result = stmt.executeQuery(select);
            while (result.next()) {
                Assert.assertEquals(result.getString("paymentDate"), "2022-11-15");
                Assert.assertEquals(result.getInt("amount"), 7000);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Update from orders table", enabled = false)
    public void updateOrder() {
        try {
            String update = "update classicmodels.orders set `comments` = null  where `orderNumber` = '10101';";
            stmt.executeUpdate(update);
            String select = "select * from classicmodels.orders where `orderNumber` = '10101';";
            ResultSet result = stmt.executeQuery(select);
            while (result.next()) {
                Assert.assertNull(result.getString("comments"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


