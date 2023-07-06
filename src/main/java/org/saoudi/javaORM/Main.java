package org.saoudi.javaORM;

import java.sql.*;
import static com.saoudi.javaUtils.ConsoleColor.*;
import static com.saoudi.javaUtils.ConsoleColor.printFromArray;

public class Main {
    public static void main(String[] args) throws SQLException {

        printProjectName();

//        userModelExamples();

        printSignature();
    }

//    public static void userModelExamples() throws SQLException {
//        /////////////////////////////////////////////////////////////////////////////////////
//        // Exemples d'utilisation de notre Model User
//        /////////////////////////////////////////////////////////////////////////////////////
//        printBigTitleBox("Exemples d'utilisation de notre Model User");
//
//
//        /////////////////////////////////////////////////////////////////////////////////////
//        printTitleBox("█ create");
//        // create UserModel instance
//        System.out.println("\n//Create UserModel instance");
//        printCode(
//                "         UserModel model = new UserModel();");
//        UserModel model = new UserModel("user");
//
//        // Creation of three Users
//        System.out.println("\n// Creation of three Users");
//        printCode(
//                "        User celine = new User(-1, \"celine\",\"celine@yahoo.fr\", \"dion\", User.Role.USER);\n" +
//                        "        User robert = new User(-1, \"robert\",\"robert@gmail.com\", \"1991\", User.Role.USER);\n" +
//                        "        User ducky = new User(-1, \"ducky\",\"ducky@hotmail.com\", \"azerty\", User.Role.ADMIN);"
//        );
//
//        User celine = new User(-1, "celine","celine@yahoo.fr", "dion", User.Role.USER);
//        User robert = new User(-1, "robert","robert@gmail.com", "1991", User.Role.USER);
//        User ducky = new User(-1, "ducky","ducky@hotmail.com", "azerty", User.Role.ADMIN);
//
//        System.out.println(celine);
//        System.out.println(robert);
//        System.out.println(ducky);
//
//        //Save users in the database
//        System.out.println("\n//Save users in the database");
//        printCode(
//                """
//                                celine = model.create(celine);
//                                robert = model.create(robert);
//                                ducky = model.create(ducky);\
//                        """);
//        celine = model.create(celine);
//        robert = model.create(robert);
//        ducky = model.create(ducky);
//
//        /////////////////////////////////////////////////////////////////////////////////////
//        printTitleBox("█ update");
//        // Update users
//        System.out.println("\n// Update users");
//        printCode(
//                "        celine.setUserName(\"Lara\");\n" +
//                        "        robert.setEmail(\"rober1991@gmail.com\");\n" +
//                        "        ducky.setRole(User.Role.USER);");
//        celine.setUserName("Lara");
//        robert.setEmail("rober1991@gmail.com");
//        ducky.setRole(User.Role.USER);
//
//        System.out.println(celine);
//        System.out.println(robert);
//        System.out.println(ducky);
//
//
//        printCode(
//                "       model.update(celine);\n" +
//                        "       model.update(robert);\n" +
//                        "       model.update(ducky);");
//
//        model.update(celine);
//        model.update(robert);
//        model.update(ducky);
//
//        /////////////////////////////////////////////////////////////////////////////////////
//        printTitleBox("█ find");
//
//        // test user at id=0
//        System.out.println("\n// Find user at id=0,1,2 ");
//        printCode(
//                "        System.out.println(model.find(celine.getId()));\n" +
//                        "        System.out.println(model.find(robert.getEmail()));\n" +
//                        "        System.out.println(model.find(ducky.getId()));");
//        System.out.println(model.find(celine.getId()));
//        System.out.println(model.find(robert.getEmail()));
//        System.out.println(model.find(ducky.getId()));
//
//        printTitleBox("█ findAll");
//
//        // Find all users
//        System.out.println("\n// Find all users");
//        printCode(
//                "       User[] users = model.findAll();\n" +
//                        "       for(User u:users){\n" +
//                        "           System.out.println(u);\n" +
//                        "       }");
//        User[] users = model.findAll();
//        for (User u : users) {
//            System.out.println(u);
//        }
//
//        /////////////////////////////////////////////////////////////////////////////////////
//        printTitleBox("█ save");
//
//
//        // save new user and update others
//
//        System.out.println("\n// save new user and update others");
//        printCode(
//                "       User jojo = new User(-1, \"bizarre\",\"jojo@biz.jp\", \"adventure\", User.Role.USER);"
//        );
//        User jojo = new User(-1, "bizarre","jojo@biz.jp", "adventure", User.Role.USER);
//
//        printCode(
//                "        System.out.println(model.save(jojo));\n" +
//                        "        System.out.println(model.save(celine));\n" +
//                        "        System.out.println(model.save(robert));\n" +
//                        "        System.out.println(model.save(ducky));");
//
//        System.out.println(model.save(jojo));
//        System.out.println(model.save(celine));
//        System.out.println(model.save(robert));
//        System.out.println(model.save(ducky));
//
//
//        /////////////////////////////////////////////////////////////////////////////////////
//        printTitleBox("█ count");
//
//
//        // delete one user
//        System.out.println("\n// count user in database");
//        printCode("System.out.println(model.count());");
//        System.out.println(model.count());
//
//        /////////////////////////////////////////////////////////////////////////////////////
//        printTitleBox("█ exists");
//
//
//        // check if user exists
//        System.out.println("\n// check if user exists");
//        printCode(
//                "        System.out.println(model.exists(celine.getId()));\n" +
//                        "        System.out.println(model.exists(robert.getEmail()));"
//        );
//        System.out.println(model.exists(celine.getId()));
//        System.out.println(model.exists(robert.getEmail()));
//
//        /////////////////////////////////////////////////////////////////////////////////////
//        printTitleBox("█ delete");
//
//
//        // delete one user
//        System.out.println("\n// delete one user");
//        printCode(
//                "        System.out.println(model.delete(celine));\n" +
//                        "        System.out.println(model.delete(robert));\n" +
//                        "        System.out.println(model.delete(ducky));\n" +
//                        "        System.out.println(model.delete(jojo));"
//        );
//        System.out.println(model.delete(celine));
//        System.out.println(model.delete(robert));
//        System.out.println(model.delete(ducky));
//        System.out.println(model.delete(jojo));
//
//    }

    private static void printProjectName() {
        printFromArray(new String[]{

                "                                                                                                   ",
                "                      ██╗ █████╗ ██╗   ██╗ █████╗  ██████╗ ██████╗ ███╗   ███╗                     ",
                "                      ██║██╔══██╗██║   ██║██╔══██╗██╔═══██╗██╔══██╗████╗ ████║                     ",
                "                      ██║███████║██║   ██║███████║██║   ██║██████╔╝██╔████╔██║                     ",
                "                 ██   ██║██╔══██║╚██╗ ██╔╝██╔══██║██║   ██║██╔══██╗██║╚██╔╝██║                     ",
                "                 ╚█████╔╝██║  ██║ ╚████╔╝ ██║  ██║╚██████╔╝██║  ██║██║ ╚═╝ ██║                     ",
                "                  ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝                     ",
                "                                                                                                   ",
        });
    }

    private static void printSignature() {
        printFromArray(new String[]{
                "                                                                                                   ",
                "       ██╗  ██╗ █████╗ ██╗  ██╗██╗███╗   ███╗    ███████╗ █████╗  ██████╗ ██╗   ██╗██████╗ ██╗     ",
                "       ██║  ██║██╔══██╗██║ ██╔╝██║████╗ ████║    ██╔════╝██╔══██╗██╔═══██╗██║   ██║██╔══██╗██║     ",
                "       ███████║███████║█████╔╝ ██║██╔████╔██║    ███████╗███████║██║   ██║██║   ██║██║  ██║██║     ",
                "       ██╔══██║██╔══██║██╔═██╗ ██║██║╚██╔╝██║    ╚════██║██╔══██║██║   ██║██║   ██║██║  ██║██║     ",
                "       ██║  ██║██║  ██║██║  ██╗██║██║ ╚═╝ ██║    ███████║██║  ██║╚██████╔╝╚██████╔╝██████╔╝██║     ",
                "       ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝╚═╝     ╚═╝    ╚══════╝╚═╝  ╚═╝ ╚═════╝  ╚═════╝ ╚═════╝ ╚═╝     ",
                "                                                                                                   "}
        );
    }
}