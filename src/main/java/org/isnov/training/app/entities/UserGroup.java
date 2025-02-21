package org.isnov.training.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_group")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long userGroupId;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(length = 2000)
    private String description;
}