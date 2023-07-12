package com.saoudi.ORM.generator;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public abstract class AbstractGenerator<T extends Nameable> {


    protected final String PROPERTIES_OUTPUT_PATH = "output_path";

    protected StringBuilder code;
    protected String propertiesOutputPath;
    protected boolean force;
    protected T obj;
    protected String outputPath;

    public AbstractGenerator(T obj, boolean force) {
        this.obj = obj;
        this.force = force;
        this.code = new StringBuilder();
        this.outputPath = this.getOutputPath();
    }

    public AbstractGenerator(T obj) {
        this(obj, false);
    }

    protected abstract void generate();

    protected abstract void generateImports();
    protected void saveToFile(String code, String filePath) {
        createDirectories(filePath);
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(code);
            System.out.println("Le fichier a été enregistré avec succès : " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void createDirectories(String path) {
        File file = new File(path);

        if (!file.exists()) {
            if (file.getParentFile().mkdirs()) {
                System.out.println("Répertoires créés avec succès : " + file.getAbsolutePath());
            } else {
                System.out.println("Impossible de créer les répertoires : " + file.getAbsolutePath());
            }
        } else {
            System.out.println("Les répertoires existent déjà : " + file.getAbsolutePath());
        }
    }

    protected boolean shouldOverwriteFile(String filePath, boolean forceOverwrite) {
        File outputFile = new File(filePath);

        if (outputFile.exists()) {
            if (forceOverwrite) {
                return true; // Forcer l'écrasement du fichier existant
            }

            System.out.println("Le fichier existe déjà : " + filePath);
            System.out.print("Voulez-vous écraser le fichier ? (yes/no): ");

            try (Scanner scanner = new Scanner(System.in)) {
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("yes") || response.equals("y")) {
                    return true; // Écraser le fichier existant
                } else {
                    return false; // Annuler l'enregistrement du fichier
                }
            }
        }

        return true;
    }

    protected String getOutputPath() {
        Properties properties = new Properties();
        String outputPath = "";

        try (InputStream input = AbstractGenerator.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            outputPath = properties.getProperty(PROPERTIES_OUTPUT_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputPath;
    }

    protected String setPackageName() {
        String[] directories = outputPath.split("/");

        StringBuilder packageNameBuilder = new StringBuilder();
        for (int i=3;i<directories.length;i++) {
            packageNameBuilder.append(directories[i]).append(".");
        }
        packageNameBuilder.append(getGeneratorName());
        return packageNameBuilder.toString().replaceAll("\\.$", "");
    }

    public void generatePackageName(){
        code.append("package ").append(getPackageName()).append(";\n\n");
    }

    public void generateAndSave() {
        String fileName = getClassName() + ".java";
        String filePath = outputPath + "/" + getGeneratorName() + "/" + fileName;

        if (shouldOverwriteFile(filePath, force)) {
            generate();
            saveToFile(code.toString(), filePath);
        } else {
            System.out.println("Opération annulée. Aucun fichier n'a été modifié.");
        }
    }

    protected String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    protected String getModelPackageName() {
        return getName();
    }

    protected abstract String getClassName();

    protected String getName(){
        return obj.getName().toLowerCase();
    }
    protected String getShemaClassName(){
        return capitalize(obj.getName());
    }
    public static boolean importClass(Class<?> type){
        boolean res = false;
        try{
            type.getName();
            res = true;
        }catch(Exception e){
        }
        return res;
    }

    protected abstract String getGeneratorName();

    protected abstract String getPackageName();
}


