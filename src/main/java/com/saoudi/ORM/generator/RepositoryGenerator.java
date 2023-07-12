package com.saoudi.ORM.generator;

import java.util.HashMap;
import java.util.Map;

public class RepositoryGenerator extends AbstractGenerator<Schema> {

    protected String generatorName = "repository";
    protected String packageName;

    private static final Map<String, String> TYPE_METHODS = new HashMap<>();

    static {
        TYPE_METHODS.put("byte", "Byte");
        TYPE_METHODS.put("Byte", "Byte");
        TYPE_METHODS.put("short", "Short");
        TYPE_METHODS.put("Short", "Short");
        TYPE_METHODS.put("int", "Int");
        TYPE_METHODS.put("Integer", "Int");
        TYPE_METHODS.put("long", "Long");
        TYPE_METHODS.put("Long", "Long");
        TYPE_METHODS.put("float", "Float");
        TYPE_METHODS.put("Float", "Float");
        TYPE_METHODS.put("double", "Double");
        TYPE_METHODS.put("Double", "Double");
        TYPE_METHODS.put("boolean", "Boolean");
        TYPE_METHODS.put("Boolean", "Boolean");
        TYPE_METHODS.put("string", "String");
        TYPE_METHODS.put("String", "String");
        TYPE_METHODS.put("Date", "Date");
        TYPE_METHODS.put("Time", "Time");
        TYPE_METHODS.put("Timestamp", "Timestamp");
    }
    public RepositoryGenerator(Schema schema, boolean force) {

        super(schema, force);
        this.packageName = setPackageName();

    }

    public RepositoryGenerator(Schema schema) {

        super(schema);
        this.packageName = setPackageName();
    }

    @Override
    protected void generate() {
        generatePackageName();
        generateImports();
        generateClassDeclaration();
        generateConstructor();
        generateFindMethod();
        generateFindAllMethod();
        generateCreateMethod();
        generateUpdateMethod();
        generateSaveMethod();
        code.append("}");
    }

    @Override
    protected String getClassName() {
        return super.getShemaClassName() + capitalize(generatorName);
    }

    @Override
    protected String getGeneratorName() {
        return generatorName;
    }

    @Override
    protected String getPackageName() {
        return packageName;
    }

    protected void generateImports() {
        code.append("import java.sql.*;\n");
        code.append("import java.util.ArrayList;\n");
        code.append("import com.saoudi.ORM.util.AbstractRepository;\n");
        code.append("import ").append(getPackageName().replace("repository","entity")).append(".").append(capitalize(getName())).append("Entity;\n\n");
    }

    private void generateClassDeclaration() {
        code.append("public class " + getClassName() + " extends AbstractRepository<" + getEntityClassName() + "> {\n\n");
    }

    private void generateConstructor() {
        code.append("    public ").append(getClassName()).append("(String tableName) {\n");
        code.append("        super(tableName);\n");
        code.append("    }\n\n");
    }

    private void generateFindMethod() {

        code.append("    @Override\n");
        code.append("    public ").append(getEntityClassName()).append(" find(String by, String value) throws SQLException {\n");
        code.append("        String sql = \"SELECT * FROM \" + tableName + \" WHERE \" + by + \" = ?\";\n");
        code.append("        PreparedStatement statement = connection.prepareStatement(sql);\n");
        code.append("        statement.setString(1, value);\n");
        code.append("        ResultSet resultSet = statement.executeQuery();\n");
        code.append("        if (resultSet.next()) {\n");
        code.append("            int ").append("id = resultSet.getInt(\"id\");\n");

        for (Field field : obj.getFields()) {
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();
            code.append("            ").append(fieldType).append(" ").append(fieldName).append(" = resultSet.get").append(getSetGetMethod(fieldType)).append("(\"").append(fieldName).append("\");\n");
        }

        code.append("return new ").append(getEntityClassName()).append("(").append("id, ");
        for (int i = 0; i < obj.getFields().size(); i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            code.append(fieldName);
            if (i < obj.getFields().size() - 1) {
                code.append(", ");
            }
        }
        code.append(");\n");
        code.append(" } else {\n");
        code.append(" return null;\n");
        code.append(" }\n");
        code.append(" }\n\n");
    }

    private void generateFindAllMethod() {
        code.append("    @Override\n");
        code.append("    public ").append(getEntityClassName()).append("[] findAll() throws SQLException {\n");
        code.append("        ArrayList<").append(getEntityClassName()).append("> ").append(getName()).append("s = new ArrayList<>();\n");
        code.append("        String sql = \"SELECT * FROM \" + tableName + \" WHERE 1\";\n");
        code.append("        PreparedStatement statement = connection.prepareStatement(sql);\n");
        code.append("        ResultSet resultSet = statement.executeQuery();\n");
        code.append("        while (resultSet.next()) {\n");
        code.append("            int ").append("id = resultSet.getInt(\"id\");\n");

        for (Field field : obj.getFields()) {
            String fieldName = field.getName();
            String capitalizedFieldName = capitalize(fieldName);
            String fieldType = field.getType().getSimpleName();
            code.append("            ").append(fieldType).append(" ").append(fieldName).append(" = resultSet.get").append(getSetGetMethod(fieldType)).append("(\"").append(fieldName).append("\");\n");
        }

        code.append("            ").append(getName()).append("s.add(new ").append(getEntityClassName()).append("(");
        code.append("id, ");
        for (int i = 0; i < obj.getFields().size(); i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            code.append(fieldName);
            if (i < obj.getFields().size() - 1) {
                code.append(", ");
            }
        }
        code.append("));\n");
        code.append("        }\n");
        code.append("        return ").append(getName()).append("s.toArray(new ").append(getEntityClassName()).append("[0]);\n");
        code.append("    }\n\n");

    }

    private void generateCreateMethod() {

        code.append("    @Override\n");
        code.append("    public ").append(getEntityClassName()).append(" create(").append(getEntityClassName()).append(" ").append(getName()).append(") throws SQLException {\n");
        code.append("        String sql = \"INSERT INTO \" + tableName + \" (");

        // Generate field names for SQL query
        for (int i = 0; i < obj.getFields().size(); i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            code.append(fieldName);
            if (i < obj.getFields().size() - 1) {
                code.append(", ");
            }
        }

        code.append(") VALUES (");

        // Generate placeholders for prepared statement
        for (int i = 0; i < obj.getFields().size(); i++) {
            code.append("?");
            if (i < obj.getFields().size() - 1) {
                code.append(", ");
            }
        }

        code.append(")\";\n");
        code.append("        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);\n");

        // Set values for prepared statement parameters
        for (int i = 0; i < obj.getFields().size(); i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            code.append("        statement.set").append(getSetGetMethod(field.getType().getSimpleName())).append("(").append(i + 1).append(", ").append(getName()).append(".get").append(capitalize(fieldName)).append("());\n");
        }

        code.append("        statement.executeUpdate();\n");
        code.append("        ResultSet generatedKeys = statement.getGeneratedKeys();\n");
        code.append("        if (generatedKeys.next()) {\n");
        code.append("            int generatedId = generatedKeys.getInt(1);\n");
        code.append("            ").append(getName()).append(".setId(generatedId);\n");
        code.append("        } else {\n");
        code.append("            throw new SQLException(\"La création de ").append(getShemaClassName()).append(" a échoué\");\n");
        code.append("        }\n");
        code.append("        return ").append(getName()).append(";\n");
        code.append("    }\n\n");
    }

    private void generateUpdateMethod() {
        code.append("    @Override\n");
        code.append("    public void update(").append(getEntityClassName()).append(" ").append(getName()).append(") throws SQLException {\n");
        code.append("        String sql = \"UPDATE \" + tableName + \" SET ");
        code.append(getFieldList("=?, ")).append("=? WHERE id=?\";\n");
        code.append("        PreparedStatement statement = connection.prepareStatement(sql);\n");

        int fieldCount = obj.getFields().size();
        for (int i = 0; i < fieldCount; i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            code.append("        statement.set").append(getSetGetMethod(field.getType().getSimpleName())).append("(").append(i + 1).append(", ").append(getName()).append(".get").append(capitalize(fieldName)).append("());\n");
        }

        code.append("        statement.setInt(").append(fieldCount + 1).append(", ").append(getName()).append(".getId());\n");
        code.append("        statement.executeUpdate();\n");
        code.append("    }\n\n");
    }

    private void generateSaveMethod() {
        code.append("    @Override\n");
        code.append("    public ").append(getEntityClassName()).append(" save(").append(getEntityClassName()).append(" ").append(getName()).append(") throws SQLException {\n");
        code.append("        String sql = \"INSERT INTO \" + tableName + \" (");
        code.append(getFieldList(", ")).append(") VALUES (");
        code.append(getFieldList("?, ")).append(") ON DUPLICATE KEY UPDATE ");
        code.append(getFieldList("=?, ")).append("=?\";\n");
        code.append("        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);\n");
        int fieldCount = obj.getFields().size();
        for (int i = 0; i < fieldCount; i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            code.append("        statement.set").append(getSetGetMethod(field.getType().getSimpleName())).append("(").append(i + 1).append(", ").append(getName()).append(".get").append(capitalize(fieldName)).append("());\n");
        }

        for (int i = 0; i < fieldCount; i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            code.append("        statement.set").append(getSetGetMethod(field.getType().getSimpleName())).append("(").append(fieldCount + i + 1).append(", ").append(getName()).append(".get").append(capitalize(fieldName)).append("());\n");
        }

        code.append("        statement.executeUpdate();\n");

        code.append("        ResultSet generatedKeys = statement.getGeneratedKeys();\n");
        code.append("        if (generatedKeys.next()) {\n");
        code.append("            int generatedId = generatedKeys.getInt(1);\n");
        code.append("            ").append(getName()).append(".setId(generatedId);\n");
        code.append("        }\n");

        code.append("        return ").append(getName()).append(";\n");
        code.append("    }\n\n");
    }

    private String getFieldList(String delimiter) {
        StringBuilder fieldList = new StringBuilder();

        for (int i = 0; i < obj.getFields().size(); i++) {
            Field field = obj.getFields().get(i);
            String fieldName = field.getName();
            fieldList.append(fieldName);
            if (i < obj.getFields().size() - 1) {
                fieldList.append(delimiter);
            }
        }

        return fieldList.toString();
    }


    private String getSetGetMethod(String fieldType) {
        String method = TYPE_METHODS.get(fieldType);
        if (method != null) {
            return method;
        } else {
            return "Object";
        }
    }


    private String getEntityClassName(){
        return getShemaClassName()+"Entity";
    }
}
