package Model.Entry;

import Model.FileRepo.logFileRepository;
import View.DemoApplication;
import org.junit.Assert;
import org.junit.Test;
import Model.Entity.DeletionLog;

import java.util.Date;

/**
 * Created by Markus Lyconhold on 16/05/16.
 *
 * The purpes of this class is to test the class DeletionLog and achive as much
 * code coverage as possible.
 */
public class DeletionLogTest {

    private String whoDelete = "person123";
    private String deletedId = "id123";
    private String DeletedFirstName = "Markus";
    private String DeletedLastName = "LyconHold";
    private Date date;

    private DeletionLog log = new DeletionLog(whoDelete, deletedId, DeletedFirstName, DeletedLastName
    , date);

    @Test
    public void getDeletedLastNameTest(){

        Assert.assertTrue(log.getDeletedLastName().equals(DeletedLastName));

    }
    @Test
    public void getDeletedFirstNameTest(){

        Assert.assertTrue(log.getDeletedFirstName().equals(DeletedFirstName));

    }
    @Test
    public void getWhoDeleteTest(){

        Assert.assertTrue(log.getWhoDelete().equals(whoDelete));

    }
    @Test
    public void getDeletedIdTest(){

        Assert.assertTrue(log.getDeletedId().equals(deletedId));
    }
    @Test
    public void getDateTest(){

        Assert.assertTrue(log.getDate().equals(date));

    }
}
