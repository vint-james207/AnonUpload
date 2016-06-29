package com.james.entities;

import javax.persistence.*;

/**
 * Created by jamesyburr on 6/27/16.
 */

@Entity
@Table(name = "files")
public class AnonFile {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String originalFilename;

    @Column(nullable = false)
    String realFilename;

    @Column(nullable = false)
    boolean permfile;

    @Column(nullable = false)
    String comment;

    String deletePassword;

    public AnonFile(String originalFilename, String realFilename, boolean permfile, String comment) {
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
        this.permfile = permfile;
        this.comment = comment;
    }

    public AnonFile(String originalFilename, String realFilename, String comment) {
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
        this.comment = comment;
    }

    public AnonFile(String originalFilename, String realFilename, boolean permfile, String deletePassword, String comment) {
        this.originalFilename = originalFilename;
        this.realFilename = realFilename;
        this.permfile = permfile;
        this.deletePassword = deletePassword;
        this.comment = comment;
    }

    public AnonFile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getRealFilename() {
        return realFilename;
    }

    public void setRealFilename(String realFilename) {
        this.realFilename = realFilename;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPermfile() {
        return permfile;
    }

    public void setPermfile(boolean permfile) {
        this.permfile = permfile;
    }

    public String getDeletePassword() {
        return deletePassword;
    }

    public void setDeletePassword(String deletePassword) {
        this.deletePassword = deletePassword;
    }
}
