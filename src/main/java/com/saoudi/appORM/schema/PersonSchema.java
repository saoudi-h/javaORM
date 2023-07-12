package com.saoudi.appORM.schema;
import com.saoudi.ORM.generator.Field;
import com.saoudi.ORM.generator.Schema;
import com.saoudi.ORM.generator.Schematable;

import java.sql.Timestamp;

public class PersonSchema implements Schematable {

    @Override
    public  Schema export() {
        Schema schemaPerson = new Schema();
        schemaPerson.setName("Person")
                .add(new Field("name",String.class,false,false))
                .add(new Field("email",String.class,true,false))
                .add(new Field("password",String.class,false,false))
                .add(new Field("createdAt", Timestamp.class,false,true));
        return schemaPerson;
    }
}
