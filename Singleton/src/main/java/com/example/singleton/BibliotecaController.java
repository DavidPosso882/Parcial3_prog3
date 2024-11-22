package com.example.singleton;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;

public class BibliotecaController {

    @FXML private Label titleLabel;
    @FXML private Button changeLanguageBtn;
    @FXML private Button addBookBtn;
    @FXML private Button addMemberBtn;
    @FXML private Button saveDataBtn;
    @FXML private Button loadDataBtn;
    @FXML private TextArea logArea;

    private Utilidades util = Utilidades.getInstance();

    @FXML
    public void initialize() {
        updateLanguage();
    }

    @FXML
    private void changeLanguage() {
        Locale newLocale = util.getMessage("language").equals("English") ? new Locale("en", "US") : new Locale("es", "ES");
        util.setLocale(newLocale);
        updateLanguage();
        logMessage("language_changed");
    }

    @FXML
    private void addBook() {
        logMessage("book_added");
    }

    @FXML
    private void addMember() {
        logMessage("member_added");
    }

    @FXML
    private void saveData() {
        List<String> books = new ArrayList<>();
        List<String> members = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            books.add("Book " + i);
            members.add("Member " + i);
        }
        util.escribirArchivo("books.txt", books);
        util.escribirArchivo("members.txt", members);

        Book book = new Book("1984", "George Orwell");
        util.serializarXML(book, "book.xml");

        Member member = new Member("John Doe", 30);
        util.serializarBinario(member, "member.dat");

        logMessage("data_saved");
    }

    @FXML
    private void loadData() {
        Book loadedBook = util.deserializarXML(Book.class, "book.xml");
        Member loadedMember = (Member) util.deserializarBinario("member.dat");

        if (loadedBook != null && loadedMember != null) {
            logArea.appendText(util.getMessage("book_loaded") + ": " + loadedBook.getTitle() + " by " + loadedBook.getAuthor() + "\n");
            logArea.appendText(util.getMessage("member_loaded") + ": " + loadedMember.getName() + ", " + loadedMember.getAge() + " " + util.getMessage("years_old") + "\n");
        }
    }

    private void updateLanguage() {
        titleLabel.setText(util.getMessage("title"));
        changeLanguageBtn.setText(util.getMessage("change_language"));
        addBookBtn.setText(util.getMessage("add_book"));
        addMemberBtn.setText(util.getMessage("add_member"));
        saveDataBtn.setText(util.getMessage("save_data"));
        loadDataBtn.setText(util.getMessage("load_data"));
    }

    private void logMessage(String messageKey) {
        String message = util.getMessage(messageKey);
        logArea.appendText(message + "\n");
        util.escribirLog(message, Level.INFO);
    }
}

class Book {
    private String title;
    private String author;

    public Book() {}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}

class Member implements Serializable {
    private String name;
    private int age;

    public Member() {}

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}