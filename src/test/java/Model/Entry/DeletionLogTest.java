package Model.Entry;

import Model.FileRepo.logFileRepository;
import View.DemoApplication;
import org.junit.Assert;
import org.junit.Test;
import Model.Entity.DeletionLog;

import java.util.Date;

/**
 * Created by Markus on 16/05/16.
 *
 * The purpes of this class is to test the class DeletionLog and achive as much
 * code coverage as possible.
 */
public class DeletionLogTest {

    private String whoDelete;
    private String deletedId;
    private String DeletedFirstName;
    private String DeletedLastName = "LyconHold";
    private Date date;
    private DeletionLog log = new DeletionLog(whoDelete, deletedId, DeletedFirstName, DeletedLastName
    , date);

    @Test
    public void getDeletedLastNameTest(){

        Assert.assertTrue(log.getDeletedLastName().equals(DeletedLastName));

    }
}
