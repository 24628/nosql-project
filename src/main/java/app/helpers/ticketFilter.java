package app.helpers;

import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;

public class ticketFilter {
    public Bson filterTickets(String newValue){
        Bson filter = Filters.or(Filters.regex("incident", ".*" + newValue + ".*", "i")
                , Filters.regex("description", ".*" + newValue + ".*", "i")
                , Filters.regex("status", ".*" + newValue + ".*", "i"));
        return filter;
    }
}
