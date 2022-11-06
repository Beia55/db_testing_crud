package dbTest.insert;

import dbTest.DbHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertQ extends DbHelper {
    @Test(description = "Insert into db employee table", enabled = false)
    public void insertPayments() {
        try {
            String select = "select * from classicmodels.payments ;";
            ResultSet initialResults = stmt.executeQuery(select);
            int initialNoOfRows = 0;
            while (initialResults.next()) {
                initialNoOfRows++;
            }

            String insert = "insert into classicmodels.payments (`customerNumber`, `checkNumber`, `paymentDate`, `amount`) values ('103', 'No1', '2022-11-01', '12345');";
            stmt.executeUpdate(insert);

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

    @Test(description = "Insert into db productLines table", enabled = false)
    public void insertProductLine() {
        try {
            String insert = "insert into classicmodels.productLines (`productLine`, `textDescription`) values ('aircraft', 'The newest and the best range of aircraft');";
            stmt.executeUpdate(insert);
            String select = "select * from classicmodels.productLines ;";
            ResultSet result = stmt.executeQuery(select);
            int noOfRows = 0;
            while (result.next()) {
                noOfRows++;
            }
            Assert.assertEquals(noOfRows, 8);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Insert into db orderdetails table", enabled = false)
    public void insertOrderdetails() {
        try {
            String insert = "insert into classicmodels.orderdetails (`orderNumber`, `productCode`, `quantityOrdered`, `priceEach`, `orderLineNumber`) " +
                    "values ('10102', 'S10_1678', '10', '123.12', '10');";
            stmt.executeUpdate(insert);
            String select = "select * from classicmodels.orderdetails where orderNumber=10102 and productCode='S10_1678' and priceEach=123.12;";
            ResultSet result = stmt.executeQuery(select);
            while (result.next()) {
                Assert.assertEquals(result.getInt("orderLineNumber"),10);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Insert into db customers table", enabled = false)
    public void insertCustomers() {
        try {
            String insert = "insert into classicmodels.customers (`customerNumber`, `customerName`, `contactLastName`, `contactFirstName`, `phone`, `addressLine1`, `addressLine2`, `city`, `state`, `postalCode`, `country`, `salesRepEmployeeNumber`, `creditLimit`) " +
                    "values ('100', 'Filip', 'Andreea', 'Contract_name', '0770077007', 'address 1', 'address 2', 'City_name', 'State_name', '12456', 'Country_name', '1702', '20000');";
            stmt.executeUpdate(insert);

            String select = "select * from classicmodels.customers where customerNumber=100;";
            ResultSet result = stmt.executeQuery(select);
            Assert.assertNotNull(result);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Insert into db offices table", enabled = false)
    public void insertOffice() {
        try {
            String insert = "insert into classicmodels.offices (`officeCode`,`city`,`phone`,`addressLine1`,`addressLine2`,`state`,`country`,`postalCode`,`territory`) " +
                    "values ('8', 'New City Name', '0770077007', 'address 1', 'address 2', 'State name', 'Country Name', '12345', 'Name');";
            stmt.executeUpdate(insert);
            String select = "select * from classicmodels.offices;";
            ResultSet result = stmt.executeQuery(select);
            int noOfRows = 0;
            while (result.next()) {
                noOfRows++;
            }
            Assert.assertEquals(noOfRows,8);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
