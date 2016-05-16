package Model;

/**
 * Created by Markus Lyconhold on 15/05/16.
 */

import Model.SQlRepo.SQLServerConnection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class SQLServerConnectionTest {


    /* The purpes of this test is to check if the object connect actually
      passes thru the method getInstance if its not null.
     */
    @Test
    public void getInstanceTest () {
        SQLServerConnection sql = new SQLServerConnection();
        Connection connect = null;
        Assert.assertNotNull(sql.getInstance().equals(connect));
    }
}
