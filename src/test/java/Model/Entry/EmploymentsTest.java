/*
    Author: Markus Lyconhold
    Date: 06/05/2016
    Description: JUnit tests for the class Employments in the Model package. The tests are being created
    in order to achive as much code coverage as possible.
 */
package Model.Entry;

import Model.Entity.Employment;
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

/*
* Created by Markus Lyconhold on 12/05/16.
 *
 * The purpose of this class is to test the class Employments and achieve as much
 * code coverage as possible.
 */
public class EmploymentsTest {

    private String companyId = "3251x5";
    private String personId = "9512352";
    private int rowID = 12312313;
    private String EmploymentId = "0123Cs4";
    private String lastName = "Lyconhold";
    private String firstName ="Markus";
    private String startDate ="2000/10/10";
    private String endDate ="-";
    private Employment employments = new Employment(companyId, personId, EmploymentId, rowID, lastName, firstName, startDate, endDate);
	@Test
	public void CompanyIdTest() {
            Assert.assertTrue(employments.getCompanyID().equals(companyId));

	}
    @Test
    public void PersonIDTest() {

        Assert.assertTrue(employments.getPersonID().equals(personId));
    }
    @Test
    public void EmploymentIDTest() {
        Assert.assertTrue(employments.getEmploymentID().equals(EmploymentId));
    }
  /*  @Test
    public void RowIDTest(){
        Assert.assertTrue(employments.getRowID().equals(rowID));
    }
    */
    @Test
    public void FirstNameTest() {

        Assert.assertTrue(employments.getFirstName().equals(firstName));
    }
    @Test
    public void LastNameTest() {

        Assert.assertTrue(employments.getLastName().equals(lastName));
    }

    @Test
    public void ToStringTest()
    {
        String expected = ""; // put the expected value here
        Assert.assertEquals(expected, employments.toString());
    }



}
