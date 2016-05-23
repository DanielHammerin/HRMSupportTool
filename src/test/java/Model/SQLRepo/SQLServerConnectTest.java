package Model.SQLRepo;

import Model.SQlRepo.SQLServerConnection;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

/**
 * Created by Markus Lyconhold on 16/05/16.
 *
 * The purpose of this class is to test the class SQLServerConnect and achieve as much test coverage as possible.
 */
public class SQLServerConnectTest {

    private String connection;

    @Test
    public void getInstanceTest() {
        SQLServerConnection sql = new SQLServerConnection(connection);
        Connection connect = null;
        Assert.assertNotNull(sql.getInstance().equals(connect));
    }
}
