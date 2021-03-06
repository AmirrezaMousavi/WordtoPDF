package com.example.demo;

import com.aspose.pdf.SaveFormat;
import java.io.File;

public class Utils {

    public static String getDataDir(Class c) {
        File dir = new File(System.getProperty("user.dir"));
        dir = new File(dir, "src");
        dir = new File(dir, "main");
        dir = new File(dir, "resources");

        for (String s : c.getName().split("\\.")) {
            dir = new File(dir, s);
            if (dir.isDirectory() == false)
                dir.mkdir();
        }
        System.out.println("Using data directory: " + dir.toString());
        return dir.toString() + File.separator;
    }


    public static void convToDoc(String path){
        String dataDir = Utils.getDataDir(DemoApplication.class);
        com.aspose.pdf.Document docA = new com.aspose.pdf.Document(dataDir + path);
        docA.save(dataDir + "PDFToDOC_out.doc", SaveFormat.Doc);
    }



}
