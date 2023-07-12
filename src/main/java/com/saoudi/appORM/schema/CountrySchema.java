package com.saoudi.appORM.schema;

import com.saoudi.ORM.generator.Field;
import com.saoudi.ORM.generator.Schema;
import com.saoudi.ORM.generator.Schematable;

import java.sql.Date;

public class CountrySchema implements Schematable {

    @Override
    public Schema export() {
        Schema countrySchema = new Schema("country");
        countrySchema.addAll(new Field[]{
                new Field("name",String.class,false,false),
                new Field("continent",String.class,false,false),
                new Field("population",Integer.class,false,false),
                new Field("area",Float.class,false,false),
                new Field("createdAt", Date.class,false,false)
        });
        return countrySchema;
    }
}
