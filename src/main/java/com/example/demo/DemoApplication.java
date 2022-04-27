
package com.example.demo;

import com.aspose.words.Document;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



class writing extends Thread{
    private final String fileName;
    public writing(String s){
        this.fileName = s;
    }


    public void run(){
        try {

            // generate conversion report in text file concurrently
            String dataDir = Utils.getDataDir(DemoApplication.class);
            FileWriter myWriter = new FileWriter(dataDir+"filename.txt");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            myWriter.write( dtf.format(now) +": User Convert "+this.fileName + " to .pdf " );
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter .docx file name without suffix");

        String fileN = myObj.nextLine(); // get the file name

        writing w = new writing(fileN);  // create report file in a thread
        w.start(); // start the thread

        String dataDir = Utils.getDataDir(DemoApplication.class); // get file directory


        Document doc = new Document(dataDir+ fileN + ".docx");
        doc.save(dataDir +fileN+ "_output.pdf"); // convert .docx file to .pdf



        // TEST CASE
        Utils.convToDoc(fileN+"_output.pdf");
        Document docA = new Document(dataDir+fileN+".docx");
        Document docB = new Document(dataDir+"PDFToDOC_out.doc");
        Test t = new Test();
        t.comp(docA,docB);
        // because of the Aspose signature in the converted pdf it returns false!


        SpringApplication.run(DemoApplication.class, args);


    }

}
