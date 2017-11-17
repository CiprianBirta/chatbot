package com.ciprian;

import java.sql.*;

public class Main {

    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Ciprian\\Desktop\\eee\\aaaaaaa\\" + DB_NAME;

    public static final String TABEL_INFORMATII = "informatii";

    public static final String COL_NUME = "name";
    public static final String COL_AN_NASTERE = "anNastere";
    public static final String COL_AN_DECES = "anDeces";
    public static final String COL_AN_INCEPUT = "anInceput";
    public static final String COL_AN_SFARSIT = "anSfarsit";

    public static void main(String[] args) {
        try {

            String nume = null;
            String nume2 = null;
            String numeTot ="";
            String anNastere = null;
            String anDeces = null;
            String anInceput = null;
            String anSfarsit = null;
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABEL_INFORMATII);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABEL_INFORMATII +
                    " (" + COL_NUME + " text, " +
                    COL_AN_NASTERE + " text, " +
                    COL_AN_DECES + " text, " +
                    COL_AN_INCEPUT + " text, " +
                    COL_AN_SFARSIT + " text" +
                    ")");

            String text = "Vlad s-a nascut in 41234";
            if (text.toLowerCase().contains("nascut")) {
                String substr = " s-a nascut in ";
                String[] parts = text.split(substr);

                nume2 = nume;
                nume = parts[0];



                if (nume.equals(nume2) || numeTot.contains(nume)) {
                    updateAnNastere(statement, nume, parts[1], anDeces, anInceput, anSfarsit);
                } else {
                    insertContact(statement, nume, parts[1], anDeces, anInceput, anSfarsit);
                    numeTot += nume;
                }
            }

            text = "Mircea a murit in 141";
            String[] deces = {"murit", "decedat"};

            for (int i = 0; i < deces.length; i++) {
                if (text.toLowerCase().contains(deces[i])) {
                    String substr = " a " + deces[i] + " in ";
                    String[] parts = text.split(substr);

                    nume = parts[0];

                    if (nume.equals(nume2) || numeTot.contains(nume)) {
                        updateAnDeces(statement, nume, anNastere, parts[1], anInceput, anSfarsit);
                    } else {

                        insertContact(statement, nume, anNastere, parts[1], anInceput, anSfarsit);
                        numeTot += nume;
                    }
                }
            }

            text = "Radu a domnit din 100 pana in 200";
            if (text.toLowerCase().contains("domnit")) {
                String substr = " a domnit din ";
                String[] parts = text.split(substr);

                nume = parts[0];

                String substr2 = " pana in ";
                parts = parts[1].split(substr2);

                if (nume.equals(nume2) || numeTot.contains(nume)) {
                    updateAniDomnie(statement, nume, anNastere, anDeces, parts[0], parts[1]);
                } else {
                    insertContact(statement, nume, anSfarsit, anDeces, parts[0], parts[1]);
                    numeTot += nume;
                }

            }

            ResultSet results = statement.executeQuery("SELECT * FROM " + TABEL_INFORMATII);

            while (results.next()) {
                System.out.println(results.getString(COL_NUME) + " " +
                        results.getInt(COL_AN_NASTERE) + " " +
                        results.getInt(COL_AN_DECES) + " " +
                        results.getInt(COL_AN_INCEPUT) + " " +
                        results.getInt(COL_AN_SFARSIT)
                );
            }

            results.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Eroare! " + e.getMessage());
        }
    }

    private static void insertContact(Statement statement, String nume, String anNastere, String anDeces, String anInceput, String anSfarsit) throws SQLException {
        statement.execute("INSERT INTO " + TABEL_INFORMATII +
                " (" + COL_NUME + ", " +
                COL_AN_NASTERE + ", " +
                COL_AN_DECES + ", " +
                COL_AN_INCEPUT + ", " +
                COL_AN_SFARSIT +
                " ) " +
                "VALUES('" + nume + "', " + anNastere + ", " + anDeces + ", " + anInceput + ", " + anSfarsit + ")");
    }

    private static void updateAnNastere(Statement statement, String nume, String anNastere, String anDeces, String anInceput, String anSfarsit) throws SQLException {
        statement.execute("UPDATE " + TABEL_INFORMATII + " SET " +
                COL_AN_NASTERE + "=" + anNastere + " WHERE " + COL_NUME + " = '" + nume + "'");
    }

    private static void updateAnDeces(Statement statement, String nume, String anNastere, String anDeces, String anInceput, String anSfarsit) throws SQLException {
        statement.execute("UPDATE " + TABEL_INFORMATII + " SET " +
                COL_AN_DECES + "=" + anDeces + " WHERE " + COL_NUME + " = '" + nume + "'");
    }

    private static void updateAniDomnie(Statement statement, String nume, String anNastere, String anDeces, String anInceput, String anSfarsit) throws SQLException {
        statement.execute("UPDATE " + TABEL_INFORMATII + " SET " +
                COL_AN_INCEPUT + "=" + anInceput + ", " + COL_AN_SFARSIT + "=" + anSfarsit + " WHERE " + COL_NUME + " = '" + nume + "'");
    }

}
