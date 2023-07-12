package com.saoudi.ORM.generator;


import java.util.Set;
import java.util.TreeSet;

public class EntityGenerator extends AbstractGenerator<Schema> {

    protected  String generatorName = "entity";
    protected String packageName;

    public EntityGenerator(Schema obj, boolean force) {

        super(obj, force);
        this.packageName = setPackageName();

    }

    public EntityGenerator(Schema obj) {

        super(obj);
        this.packageName = setPackageName();

    }

    @Override
    protected void generate() {

        // Générer le code source de la classe
        generatePackageName();
        generateImports();
        generateClassDeclaration();
        generateIdFieldsGettersSetters();
        generateFields();
        generateConstructors();
        generateGettersAndSetters();
        generateToString();
        generateEquals();
        code.append("}");
    }

    @Override
    protected void generateImports() {
        code.append("import com.saoudi.ORM.util.Identifiable;")
            .append("\n\n");

        // type fields import
        TreeSet<String> importsTab = new TreeSet<>();
        for (Field field : obj.getFields()) {
            if(importClass(field.getType())){
                importsTab.add(field.getType().getName());
            }
        }
        for (String importLine : importsTab) {
            code.append("import ").append(importLine).append(";\n");
        }
        code.append("\n\n");
    }

    @Override
    protected String getClassName() {
        return getShemaClassName()+"Entity";
    }

    @Override
    protected String getGeneratorName() {
        return generatorName;
    }

    @Override
    protected String getPackageName() {
        return packageName;
    }


    private void generateIdFieldsGettersSetters(){
        code.append("    private int id;")
            .append("    public int getId() { return id;}")
            .append("\n\n")
            .append("    public void setId(int id) { this.id = id;}")
            .append("\n\n");
    }
    private void generateClassDeclaration() {
        code.append("public class ").append(getClassName()).append(" implements Identifiable {\n");
    }

    private void generateFields() {
        for (Field field : obj.getFields()) {
            code.append("    private ").append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";\n");
        }
    }

    private void generateConstructors() {
        generateConstructorWithAllAttributes();
        code.append("\n");
        generateConstructorWithoutId();
    }

    private void generateConstructorWithAllAttributes() {
        code.append("    public ").append(getClassName()).append("(");

        // id field
        code.append("int id");
        // Paramètres
        for (int i = 0; i < obj.getFields().size(); i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();

            code.append(", ").append(fieldType).append(" ").append(fieldName);
        }

        code.append(") {\n");

        // id field
        code.append("         this.id = id;\n");

        for (Field field : obj.getFields()) {
            String fieldName = field.getName();
            code.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
        }
        code.append("    }\n");
    }

    private void generateConstructorWithoutId() {
        code.append("    public ").append(getClassName()).append("(");

        // Paramètres
        for (int i = 0; i < obj.getFields().size(); i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();

            code.append(fieldType).append(" ").append(fieldName);
            if (i < obj.getFields().size() - 1) {
                code.append(", ");
            }
        }

        code.append(") {\n");

        for (Field field : obj.getFields()) {
            String fieldName = field.getName();
            if (!fieldName.equals("id")) {
                code.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
            }
        }

        code.append("        this.id = 0; // Valeur par défaut pour l'ID\n");
        code.append("    }\n");
    }

    private void generateGettersAndSetters() {
        for (Field field : obj.getFields()) {
            String fieldName = field.getName();
            String capitalizedFieldName = capitalize(field.getName());
            String fieldType = field.getType().getSimpleName();

            // Getter
            code.append("    public ").append(fieldType).append(" get").append(capitalizedFieldName).append("() {\n");
            code.append("        return ").append(fieldName).append(";\n");
            code.append("    }\n\n");

            // Setter
            code.append("    public void set").append(capitalizedFieldName).append("(").append(fieldType).append(" ").append(fieldName).append(") {\n");
            code.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
            code.append("    }\n\n");
        }
    }

    private void generateToString() {
        code.append("    @Override\n");
        code.append("    public String toString() {\n");
        code.append("        return \"").append(getName()).append("{\" +\n");

        for (Field field : obj.getFields()) {
            String fieldName = field.getName();
            code.append("                \"").append(fieldName).append("='\" + ").append(fieldName).append(" + '\\'' +\n");
        }

        code.append("                \"id='\" + id + '\\'' +\n");
        code.append("                '}';\n");
        code.append("    }\n");
    }

    private void generateEquals() {
        code.append("    @Override\n");
        code.append("    public boolean equals(Object obj) {\n");
        code.append("        if (this == obj) return true;\n");
        code.append("        if (obj == null || getClass() != obj.getClass()) return false;\n");
        code.append("        ").append(getClassName()).append(" other = (").append(getClassName()).append(") obj;\n");

        for (Field field : obj.getFields()) {
            String fieldName = field.getName();
            code.append("        if (").append(fieldName).append(" != null ? !").append(fieldName).append(".equals(other.").append(fieldName).append(") : other.").append(fieldName).append(" != null) return false;\n");
        }

        code.append("        return id == other.id;\n");
        code.append("    }\n");
    }
}