package com.kosenkovps.clicker.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Url {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String defaultUrl;
    @Column(nullable = false)
    private Date createdDate;
}
