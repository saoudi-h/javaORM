package org.saoudi.ORM.utils;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public abstract class AbstractGenerator<T extends Nameable> {

    protected StringBuilder code;
    protected String propertiesOutputPath;
    protected boolean force;
    protected T obj;
    protected String outputPath;
    protected String packageName;

    public AbstractGenerator(T obj, boolean force) {
        this.obj = obj;
        this.force = force;
        this.code = new StringBuilder();
        this.propertiesOutputPath = "output_path";
        this.outputPath = this.getOutputPath();
        setPackageName();
    }

    public AbstractGenerator(T obj) {
        this(obj, false);
    }

    protected abstract void generate();

    protected void saveToFile(String code, String filePath) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(code);
            System.out.println("Le fichier a été enregistré avec succès : " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
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

        return true; // Le fichier n'existe pas, aucun problème d'écrasement
    }

    protected String getOutputPath() {
        Properties properties = new Properties();
        String outputPath = "";

        try (InputStream input = AbstractGenerator.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            outputPath = properties.getProperty(propertiesOutputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputPath;
    }

    protected void setPackageName() {
        System.out.println(File.separator);
        String[] directories = outputPath.split("/");

        StringBuilder packageNameBuilder = new StringBuilder();
        for (int i=3;i<directories.length;i++) {
            packageNameBuilder.append(directories[i]).append(".");
        }

        this.packageName = packageNameBuilder.toString().replaceAll("\\.$", "");
    }

    public void generatePackageName(){
        code.append("package ").append(this.packageName).append(";\n\n");
    }

    public void generateAndSave() {
        String fileName = capitalize(obj.getName()) + ".java";
        String filePath = outputPath + File.separator + fileName;

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
}