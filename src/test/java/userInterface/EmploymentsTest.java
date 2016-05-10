/*
    Author: Markus Lyconhold
    Date: 06/05/2016
    Description: JUnit tests for the class Employments in the Model package. The tests are being created
    in order to achive as much code coverage as possible.
 */
package userInterface;
import Model.Employment;
import Model.FileRepo.*;
import static org.junit.Assert.*;

import View.DemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class EmploymentsTest {

    private String companyId = "3251x5";
    private String personId = "9512352";
    private int rowID = 12312313;
    private String EmploymentId = "0123Cs4";
    private String lastName = "Lyconhold";
    String FirstName = "Markus";

    private Employment employments = new Employment(companyId, personId, EmploymentId, rowID, lastName, FirstName);
	@Test
	public void CompanyIdTest() {
            Assert.assertTrue(employments.getCompanyID().equals(companyId));

	}
    @Test
    public void FirstNameTest() {
         String firstName = "Markus";

        Assert.assertTrue(employments.getFirstName().equals(firstName));
    }
    @Test
    public void LastNameTest() {
        Assert.assertTrue(employments.getLastName().equals(lastName));
    }



}
