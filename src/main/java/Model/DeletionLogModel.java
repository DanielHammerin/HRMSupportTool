package Model;

import Model.Entity.DeletionLog;
import Model.Entity.Employment;
import Model.Entity.User;
import Model.FileRepo.logFileRepository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Abeer on 4/22/2016.
 * DeletionLogModel class for logic operation between DeletionLog and logFileRepository
 */
public class DeletionLogModel {

    private logFileRepository repo ;

    /**
     * Constructor of the deletion log model
     * @throws IOException
     */
    public DeletionLogModel() throws IOException {
        repo = new logFileRepository();
    }

    /**
     * Method to create a log
     * @param whoDelete which user delete the employment
     * @param employment the employment deleted
     * @param date the date of the deletion
     * @return true if the log creation has succeeded, false otherwise
     */
    public boolean createLog (String whoDelete , Employment employment , Date date){
        DeletionLog log = new DeletionLog(whoDelete , employment.getPersonID(), employment.getFirstName(),employment.getLastName() ,date);
        return repo.createDeletionLog(log);
    }

    /**
     * Method the get all teh logs
     * @return the list of logs
     */
    public List<DeletionLog> readLogs (){
        return repo.readDeletionLogs();
    }
}
