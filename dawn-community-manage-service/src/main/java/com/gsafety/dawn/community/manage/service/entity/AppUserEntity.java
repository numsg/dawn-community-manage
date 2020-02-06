package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.*;

/**
 * Created by numsg on 2017/3/3.
 */
@Entity
@Table(name="TEST_USER")
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    public AppUserEntity() {
        // Do nothing
    }

    public AppUserEntity(String username) {
        this.username = username;
    }

    public AppUserEntity(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
