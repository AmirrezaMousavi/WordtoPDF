package com.example.demo;

import com.aspose.words.Document;
import java.util.Date;

public class Test {
    public void comp(Document docA,Document docB) throws Exception {
        docA.compare(docB, "user", new Date()); // compare two docx file with each other
        if (docA.getRevisions().getCount() == 0)
            System.out.println("Documents are equal");
        else
            System.out.println("Documents are not equal");
    }
}
