package Model;

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

    public boolean createLog (String whoDelete , String whoWasDeleted , Date date){
        DeletionLog log = new DeletionLog(whoDelete , whoWasDeleted ,date);
        return repo.createDeletionLog(log);
    }

    public List<DeletionLog> readLogs (){
        return repo.readDeletionLogs();
    }
}
