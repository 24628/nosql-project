package app.helpers;

import javafx.scene.control.ComboBox;
import org.bson.Document;

import java.util.List;

public class documentHandling {

    public Document generateDocument(List<String> data, String[] columnNames){
        // new document
        Document document = new Document();

        // create document
        for (int i = 0; i < data.size(); i++) {
            document.append(columnNames[i], data.get(i));
        }

        // return document
        return document;
    }

    public int getCMBIndex(ComboBox<String> box, String value){
        int index = 0;
        for (String s:box.getItems()) {
            if (s.equalsIgnoreCase(value))
                break;
            index++;
        }
        return index;
    }
}
