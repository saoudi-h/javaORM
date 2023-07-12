package com.saoudi.appORM.schema;

import com.saoudi.ORM.generator.EntityGenerator;
import com.saoudi.ORM.generator.Field;
import com.saoudi.ORM.generator.RepositoryGenerator;
import com.saoudi.ORM.generator.Schema;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args){

        // generate country
        Schema country = new CountrySchema().export();
        EntityGenerator entityGenerator = new EntityGenerator(country,true);
        RepositoryGenerator repoGen = new RepositoryGenerator(country,true);
        entityGenerator.generateAndSave();
        repoGen.generateAndSave();

        // generate Person
        Schema person = new PersonSchema().export();
        entityGenerator = new EntityGenerator(person ,true);
        repoGen = new RepositoryGenerator(person,true);
        entityGenerator.generateAndSave();
        repoGen.generateAndSave();

    }
}
