package com.svirida.books.domain;


public class Writer {
    private final long id;
    private final String fullName;
    private final String birthday;

    public Writer(long id, String fullName, String birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
