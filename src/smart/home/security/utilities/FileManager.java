package smart.home.security.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A file manager that allows reading and persisting files.
 * @author archana
 */
public class FileManager {

    /**
     * Read the file from the given file path.
     * @param filePath - the String location of the file.
     * @return the list of string read from the file path.
     * @throws IOException 
     */
    public List<String> readFile(String filePath) throws IOException {
        // Create the messages to be read.
        List<String> messages = new ArrayList();
        
        // Initialize the file and the readers.
        File file = new File(filePath);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            // Open the file only if it exists.
            if (file.exists()) {
                // Create the readers from the file.
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String line;                
                // Read each line into messages.
                while ((line = bufferedReader.readLine()) != null) {
                    messages.add(line);
                }
            }
            return messages;

        } catch (Exception e) {
            // Print the stack trace if an exception is thrown.
            e.printStackTrace();
        } finally {            
            // Close the file reader if it is not null.
            if (fileReader != null) {
                fileReader.close();
            }
            // Close the buffered reader if it is not null.
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return messages;
    }

    public void saveFile(List<String> messages, String filePath) throws IOException {
        // Initialize the file and the writers.
        File file = new File(filePath);
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            // Create the writers with the given file.
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            
            // Write each message to the file.
            for (String message : messages) {
                bufferedWriter.append(message);
                bufferedWriter.append("\n");
            }
        } catch (IOException e) {
            // Print the stack trace if an exception is thrown.
            e.printStackTrace();
        } finally {
            // Close the file writer if it is not null.
            if (fileWriter != null) {
                fileWriter.close();
            }
            // Close the buffered writer if it is not null.
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }
}
