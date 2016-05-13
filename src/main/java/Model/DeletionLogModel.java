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
 */
public class DeletionLogModel {

    private logFileRepository repo ;

    public DeletionLogModel() throws IOException {
        repo = new logFileRepository();
    }

    public boolean createLog (String whoDelete , Employment employment , Date date){
        DeletionLog log = new DeletionLog(whoDelete , employment.getPersonID(), employment.getFirstName(),employment.getLastName() ,date);
        return repo.createDeletionLog(log);
    }

    public List<DeletionLog> readLogs (){
        return repo.readDeletionLogs();
    }
}
