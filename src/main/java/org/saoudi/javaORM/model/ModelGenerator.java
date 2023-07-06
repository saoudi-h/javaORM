package org.saoudi.javaORM.model;

import org.saoudi.javaORM.utils.AbstractGenerator;


public class ModelGenerator extends AbstractGenerator<Model> {

    public ModelGenerator(Model obj, boolean force) {
        super(obj, force);
    }

    public ModelGenerator(Model obj) {
        super(obj);
    }

    @Override
    protected void generate() {
        Model model = obj;

        // Générer le code source de la classe
        generatePackageName();
        generateClassDeclaration(model);
        generateFields(model);
        generateConstructors(model);
        generateGettersAndSetters(model);
        generateToString(model);
        generateEquals(model);
        code.append("}");
    }

    private void generateClassDeclaration(Model model) {
        code.append("public class ").append(model.getName()).append(" extends AbstractModel {\n");
    }

    private void generateFields(Model model) {
        for (Field field : model.getFields()) {
            code.append("    private ").append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";\n");
        }
    }

    private void generateConstructors(Model model) {
        generateConstructorWithAllAttributes(model);
        code.append("\n");
        generateConstructorWithoutId(model);
    }

    private void generateConstructorWithAllAttributes(Model model) {
        code.append("    public ").append(model.getName()).append("(");

        // id field
        code.append("int id");
        // Paramètres
        for (int i = 0; i < model.getFields().size(); i++) {
            Field field = model.getFields().get(i);
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();

            code.append(", ").append(fieldType).append(" ").append(fieldName);
        }

        code.append(") {\n");

        // id field
        code.append("         this.id = id;\n");

        for (Field field : model.getFields()) {
            String fieldName = field.getName();
            code.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
        }
        code.append("    }\n");
    }

    private void generateConstructorWithoutId(Model model) {
        code.append("    public ").append(model.getName()).append("(");

        // Paramètres
        for (int i = 0; i < model.getFields().size(); i++) {
            Field field = model.getFields().get(i);
            String fieldName = field.getName();
            String fieldType = field.getType().getSimpleName();

            code.append(fieldType).append(" ").append(fieldName);
            if (i < model.getFields().size() - 1) {
                code.append(", ");
            }
        }

        code.append(") {\n");

        for (Field field : model.getFields()) {
            String fieldName = field.getName();
            if (!fieldName.equals("id")) {
                code.append("        this.").append(fieldName).append(" = ").append(fieldName).append(";\n");
            }
        }

        code.append("        this.id = 0; // Valeur par défaut pour l'ID\n");
        code.append("    }\n");
    }

    private void generateGettersAndSetters(Model model) {
        for (Field field : model.getFields()) {
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

    private void generateToString(Model model) {
        code.append("    @Override\n");
        code.append("    public String toString() {\n");
        code.append("        return \"").append(model.getName()).append("{\" +\n");

        for (Field field : model.getFields()) {
            String fieldName = field.getName();
            code.append("                \"").append(fieldName).append("='\" + ").append(fieldName).append(" + '\\'' +\n");
        }

        code.append("                \"id='\" + id + '\\'' +\n");
        code.append("                '}';\n");
        code.append("    }\n");
    }

    private void generateEquals(Model model) {
        code.append("    @Override\n");
        code.append("    public boolean equals(Object obj) {\n");
        code.append("        if (this == obj) return true;\n");
        code.append("        if (obj == null || getClass() != obj.getClass()) return false;\n");
        code.append("        ").append(model.getName()).append(" other = (").append(model.getName()).append(") obj;\n");

        for (Field field : model.getFields()) {
            String fieldName = field.getName();
            code.append("        if (").append(fieldName).append(" != null ? !").append(fieldName).append(".equals(other.").append(fieldName).append(") : other.").append(fieldName).append(" != null) return false;\n");
        }

        code.append("        return id == other.id;\n");
        code.append("    }\n");
    }

    public static void main(String[] args) {
        Model model = new Model("Voiture");
        model.add(new Field("marque", String.class, false, true));
        model.add(new Field("modele", String.class, false, true));
        ModelGenerator generator = new ModelGenerator(model,true);
        generator.generateAndSave();
    }
}