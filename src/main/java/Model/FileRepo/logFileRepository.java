package Model.FileRepo;

import Model.Entity.DeletionLog;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Abeer on 4/14/2016.
 * Updated by Hatem Houssein 28/4/2016. (Changed the logFilePath to "demo\\LocalDatabase\\DeletionLog_DB.ser")
 */
public class logFileRepository {

    private final String logFilePath = "LocalDatabase" + File.separator + "DeletionLog_DB.ser";

    /**
     * Constructor of the log file repository
     * @throws IOException
     */
    public logFileRepository() throws IOException {
        try {
            Path path = Paths.get(logFilePath);
            Files.createDirectories(path.getParent());

            if (!isFileExist(path.toString())) {
                Files.createFile(path);
            }

        } catch (IOException ex) {
            Logger.getLogger(logFileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method to check if the log file exists
     * @param path the file path
     * @return true if the file exists, false otherwise
     */
    private boolean isFileExist(String path) {
        return new File(path).exists();
    }

    /**
     * Methdo to check if the log file is empty
     * @param path the file path
     * @return true if the file exists, false otherwise
     */
    private boolean isEmptyFile(String path) {
        return new File(path).length() == 0;
    }

    /**
     * Method to create a deletion log
     * @param log the log to registred
     * @return true if the creation has suceeded, false otherwise
     */
    public boolean createDeletionLog(DeletionLog log){
        List<DeletionLog> logs =readDeletionLogs();
        logs.add(log);
        return overwriteRecord(logs);
    }

    /**
     * Method to overwrite a list of logs
     * @param logs the list of logs to overwrite
     * @return true if the overwrite has suceeded, false otherwise
     */
    private boolean overwriteRecord(List<DeletionLog> logs) {
        if (isFileExist(logFilePath)) {
            try {
                FileOutputStream fileOut = new FileOutputStream(logFilePath);
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(logs);
                out.close();
                fileOut.close();
            } catch (IOException i) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Methdo to get all deletion logs
     * @return the list of all logs registred
     */
    public List<DeletionLog> readDeletionLogs() {
        List<DeletionLog> records = new ArrayList<>();

        if (isFileExist(logFilePath) && !isEmptyFile(logFilePath)) {
            try {
                FileInputStream fileIn = new FileInputStream(logFilePath);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                records = (ArrayList<DeletionLog>) in.readObject();
                in.close();
                fileIn.close();
            } catch (Exception i) {
            }
        }
        return records;
    }
}
