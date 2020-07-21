package com.svirida.books.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

@ChangeLog
public class DatabaseChangelog {
    @ChangeSet(order = "001", id = "addBooks", author = "vsvirida")
    public void insertBook(DB db) {
        DBCollection myCollection = db.getCollection("book");
        BasicDBObject doc = new BasicDBObject().append("_id", 1);
        doc.append("title", "Book4");
        doc.append("description", "description4");
        doc.append("genre", "genre1");
        doc.append("writer", "writer1");
        myCollection.insert(doc);
//        myCollection=db.getCollection("genre");
        BasicDBObject doc1 = new BasicDBObject().append("_id", 2L);
        doc1.append("title", "Book2");
        doc1.append("description", "description2");
        doc1.append("genre", "genre2");
        doc1.append("writer", "writer2");
        myCollection.insert(doc1);

        BasicDBObject doc2 = new BasicDBObject().append("_id", 3L);
        doc2.append("title", "Book3");
        doc2.append("description", "description3");
        doc2.append("genre", "genre3");
        doc2.append("writer", "writer3");
        myCollection.insert(doc2);
    }
}
