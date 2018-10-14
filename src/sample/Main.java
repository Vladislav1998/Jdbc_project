package sample;

import javax.swing.*;
import java.sql.*;
import java.util.Scanner;

public class Main {
    Connection conn;
    Scanner scanner = new Scanner(System.in);
    public  void open(){
        try {
            System.out.println("Consol application for select data from SQLite database");
            // 1. Регистрация драйвера jdbc для sqlite в данной сессии программы.
            // При отсутствии регистрации пути к драйверу генерируется Exception
            // ClassNotFoundException
            Class.forName("org.sqlite.JDBC");
           // System.out.println("Необходимый драйвер в наличии!");
            // 2. Создается подкулючение к базе данных. Полный путь к базе данных передается как параметр
           // System.out.println("Выполняется подключение к БД ...");
            conn = DriverManager.getConnection("jdbc:sqlite:D:\\Lab Java EE\\SQLite\\Lab_1_JavaEE.db");
            if (conn != null) {
                System.out.println("Успешное подключение!!!");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Драйвер для данного типа БД не найден");
        } catch (SQLException ex) {
            System.out.println("Подключение не создано. Проверьте имя БД");
        }
    }
    void close() {
        try{
        conn.close();
        System.out.println("Соединение прервано!!!");
        }catch (Exception e) {
        }
     }
    void  selectOrganization(){
         try {
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM  Organization");
             while (rs.next()) {
                 System.out.println("*" + rs.getString(1) + "           " + rs.getString(2) + "       " + rs.getString(3) + "         "+rs.getString(4));
             }
         }catch(SQLException e){
             System.out.println("Запрос не выполнен");
         }
     }
    void selectArtist(){
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM  Artist");
            while (rs.next()) {
                System.out.println("*" + rs.getString(1) + "      " + rs.getString(2) + "       " + rs.getString(3));
            }
            }catch(SQLException e){
                System.out.println("Запрос не выполнен");
            }

        }
    void selectCD(){
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM  CD");
            while (rs.next()){
                System.out.println("*"+rs.getString(1)+"      "+rs.getString(2)+"         "+rs.getString(3));
            }
        }catch (Exception e){
            System.out.println("Error");
        }
    }
    void Artist(){
        String select ="";
        operationWithDB();
        while (!select.equals("0")){
            System.out.println("Action Number:");
            select=scanner.nextLine();
            switch (select) {
                case "1":
                    open();
                    try {
                        System.out.println("\n Таблица \"Artist\"");
                        System.out.println("______________________________________________________________");
                        System.out.println("*Artist*"+"      "+"*Album*"+"         "+"*Number_Of_Albums*");
                        System.out.println("______________________________________________________________");
                        selectArtist();
                    } catch (Exception e) {
                        System.out.println("Запрос не ввполнен!!!");
                    }

                    close();
                    operationWithDB();
                    break;
                case "2":
                    open();
                    selectArtist();
                    System.out.println("\n Введите \" Artist_id\" для удаления нужного вам элемента,или -1 для возврата в главное меню!!!");
                    System.out.println("Artist_id:");
                    int art_id = scanner.nextInt();
                    if (art_id != -1) {
                        if(update("DELETE FROM  Artist WHERE  Artist_ID=" + art_id)==1) {
                            System.out.println("Запрос выполнен!!!");
                            art_id = -1;
                        }
                        else {
                            art_id=-1;
                        }
                        close();
                    }
                    operationWithDB();
                    break;
                case "3":
                    open();
                    selectArtist();
                    System.out.println("Введите Artist_id  для обновления данных в таблице:");
                    try {
                        Scanner scn = new Scanner(System.in);
                         int artist_id = Integer.parseInt(scanner.nextLine()) ;
                        System.out.println("Введите новое значение поля \"Name:\"");
                        String name = scn.nextLine();
                        System.out.println("Введите новое значение поля \"Number_of_Albums:\"");
                        String num_alb = scn.nextLine();
                        if (update("UPDATE Artist SET Name='" + name + "' ,Number_of_albums='" + num_alb + "' WHERE Artist_id=" + artist_id) == 1) {
                            System.out.println("Запрос выполнен!!!");
                        } else {
                            System.out.println("Error!!!");
                        }
                        close();
                    } catch (Exception e) {
                        System.out.println("Error!!!Не удалось обновить");
                    }
                    operationWithDB();
                    break;
                case "4":
                    open();
                    try {
                        System.out.println("Введите данные для поля Name:");
                        String name= scanner.nextLine();
                        System.out.println("Введите данные для поля Number_Of_Albums:");
                        String num_alb = scanner.nextLine();
                        if(update("INSERT  INTO Artist (Name, Number_of_albums) VALUES" +
                                "('"+name+"','"+num_alb+"')")==1){
                            System.out.println("Запрос выполнен!!!");
                        }
                        else{
                            System.out.println("Error with sql query");
                        }
                        close();
                    }catch (Exception e){
                        System.out.println("Error!!!");
                    }
                    operationWithDB();
                    break;

                case "0":
                  MainMenu();
                    break;
            }
        }
    }
    void Orzanization(){
        String select ="";
        operationWithDB();
        while (!select.equals("0")){
            System.out.println("Action Number:");
            select=scanner.nextLine();
            switch (select) {
                case "1":
                    open();
                    try {
                        System.out.println("\n Таблица \"Orzanization\"");
                        System.out.println("______________________________________________________________");
                        System.out.println("Org_ID___________Name_organization_____________Phone_______________E_mail__________");
                        System.out.println("______________________________________________________________");
                        selectOrganization();
                    } catch (Exception e) {
                        System.out.println("Запрос не ввполнен!!!");
                    }
                    close();
                    operationWithDB();
                    break;
                case "2":
                    open();
                    selectOrganization();
                    System.out.println("\n Введите \" Orzanization\" для удаления нужного вам элемента,или -1 для возврата в главное меню!!!");
                    System.out.println("Orzanization_id:");
                    int org_id = scanner.nextInt();
                    if (org_id != -1) {
                        if(update("DELETE FROM Organization WHERE  Organization_ID=" + org_id)==1) {
                            System.out.println("Запрос выполнен!!!");
                            org_id = -1;
                        }
                        else {
                            org_id=-1;
                        }
                        close();
                    }
                    operationWithDB();
                    break;
                case "3":
                    open();
                    selectOrganization();
                    System.out.println("Введите Orzanization_id  для обновления данных в таблице:");
                    try {
                        Scanner scn = new Scanner(System.in);
                        int organ_id = scanner.nextInt();
                        System.out.println("Введите новое значение поля \"Name_organization \"");
                        String name_org = scn.nextLine();
                        System.out.println("Введите новое значение поля \"Phone:\"");
                        String phone_org = scn.nextLine();
                        System.out.println("Введите новое значение поля \"E_mail:\"");
                        String email_org = scn.nextLine();
                        if (update("UPDATE  Organization SET Name_of_organization='" + name_org + "',Phone='" + phone_org + "' ,E_mail='" + email_org + "' WHERE  Organization_ID=" + organ_id) == 1) {
                            System.out.println("Запрос выполнен!!!");
                        } else {
                            System.out.println("Error!!!");
                        }
                        close();
                    } catch (Exception e) {
                        System.out.println("Error!!!Не удалось обновить");
                    }
                    operationWithDB();
                    break;
                case "4":
                    open();
                    try {
                        System.out.println("Ввод данных для таблицы Organization:");
                        System.out.println("Введите данные для поля Name_organization:");
                        String n_org = scanner.nextLine();
                        System.out.println("Введите данные для поля Phone:");
                        String phone = scanner.nextLine();
                        System.out.println("Введите данные для поля E_mail:");
                        String E_maile = scanner.nextLine();
                        if(update("INSERT  INTO Organization(Name_of_organization, Phone, E_mail) VALUES" +
                                "('"+n_org+"','"+phone+"','" +E_maile+"')")==1){
                            System.out.println("Запрос выполнен!!!");
                        }
                        else{
                            System.out.println("Error with sql query");
                        }
                        close();
                    }catch (Exception e){
                        System.out.println("Error!!!");
                    }
                    operationWithDB();
                    break;
                case "0":
                    MainMenu();
                    break;
                default:
                    break;
            }
        }
    }
    void  CD(){
            String select ="";
            operationWithDB();
            while (!select.equals("0")){
                System.out.println("Action Number:");
                select=scanner.nextLine();
                switch (select) {
                    case "1":
                        open();
                        try {
                            System.out.println("\n Таблица \"CD-disk\"");
                            System.out.println("_____________________");
                            System.out.println("CD_id___________Album_____________Ganre");
                            selectCD();
                        } catch (Exception e) {
                            System.out.println("Запрос не ввполнен!!!");
                        }

                        close();
                        operationWithDB();
                        break;
                    case "2":
                        open();
                        selectCD();
                        System.out.println("\n Введите \" СD_id\" для удаления нужного вам элемента,или -1 для возврата в главное меню!!!");
                        System.out.println("CD_id:");
                        int cd_id = scanner.nextInt();
                        if (cd_id != -1) {
                                if(update("DELETE FROM  CD WHERE  CD_ID=" + cd_id)==1) {
                                    System.out.println("Запрос выполнен!!!");
                                    cd_id = -1;
                                }
                                else {
                                    cd_id=-1;
                                }
                            close();
                        }
                        operationWithDB();
                        break;
                    case "3":
                        open();
                        selectCD();
                        System.out.println("Введите CD_id  для обновления данных в таблице:");
                        try {
                            Scanner scn = new Scanner(System.in);
                            int el_id = scanner.nextInt();
                            System.out.println("Введите новое значение поля \"Album: \"");
                            String album = scn.nextLine();
                            System.out.println("Введите новое значение поля \"Genre:\"");
                            String genre = scn.nextLine();
                            if (update("UPDATE CD SET Album='" + album + "' ,Genre='" + genre + "' WHERE CD_id=" + el_id) == 1) {
                                System.out.println("Запрос выполнен!!!");
                            } else {
                                System.out.println("Error!!!");
                            }
                            close();
                        } catch (Exception e) {
                            System.out.println("Error!!!Не удалось обновить");
                        }
                        operationWithDB();
                        break;
                    case "4":
                        open();
                        try {
                            System.out.println("Введите данные для поля Album:");
                            String albm = scanner.nextLine();
                            System.out.println("Введите данные для поля Ganre:");
                            String gnr = scanner.nextLine();
                            System.out.println("Введите данные для поля Artist_id:");
                            String arts_id = scanner.nextLine();
                            System.out.println("Введите данные для поля Oraganization_id:");
                            String org_id = scanner.nextLine();
                            if(update("INSERT  INTO CD (Album, Genre, Artist_ID, Organization_ID) VALUES" +
                                    "('"+albm+"','"+gnr+"','" +arts_id+"','"+ org_id+ "')")==1){
                                System.out.println("Запрос выполнен!!!");
                            }
                            else{
                                System.out.println("Error with sql query");
                            }
                            close();
                        }catch (Exception e){
                            System.out.println("Error!!!");
                        }
                        operationWithDB();
                        break;
                         case "0":
                            MainMenu();
                            break;
                            default:
                                break;
                }
            }

    }
     public  int update(String query){
        try {
            Statement statement = conn.createStatement();
            return  statement.executeUpdate(query);
        }catch ( Exception e){
            System.out.println("Error!!!!");
            return -1;
        }
    }
    void operationWithDB(){
        System.out.println("\n___________________________\n" +
                "Выберите дейсвтия, которое вы хотите выполнить:\n" +
                "1. Отобразить содержимое таблицы;\n" +
                "2. Удалить некоторые данные;\n" +
                "3. Отредактировать некоторые данные;\n" +
                "4. Добавить новые данные;\n" +
                "0. Выйти в главное меню.\n" +
                             "____________________________");
    }
    void MainMenu (){
        System.out.println("__________________________________________\n"+
                "Список таблиц БД:\n" +
                "1. Artist-музыкальные испольнители;\n" +
                "2. Orzanization - организация по выпуску CD-дисков;\n"+
                "3. CD - музыкальные диски;\n\n" +
                "Для завершения работы программы введите цифру 0.\n" +
                "Введите номер таблицы, с которой хотите продолжить работу (1,2,3).\n" +
                " ___________________________________________");
    }
        public static void main(String[] args)throws  SQLException {
            Main main = new Main();
            Scanner scanner = new Scanner(System.in);
            main.MainMenu();
            String numTable = "";
            while (numTable!="0"){
                System.out.println("Action Number:");
                 numTable = scanner.nextLine();
                switch (numTable)
                {
                    case "1":
                        System.out.println( "Вы работаете с таблицей Artist: \n");
                        main.Artist();
                        break;
                    case "2":
                        System.out.println( "Вы работаете с таблицей Orzanization: \n");
                        main.Orzanization();
                        break;
                    case "3":
                        System.out.println( "Вы работаете с таблицей CD: \n");
                        main.CD();
                        break;
                    case "0":
                        return;
                        default:
                            System.out.println("Некоректные данные,повторите ввод");
                            break;
                }
            }


       }
    }
