package com.myclass.elearning.entity;

import com.myclass.elearning.enumeration.RoleName;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "roles")
@Setter
@Getter
public class RoleEntity extends BaseAuditEntity<String> {


    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName name;

    @Column(name = "description")
    private String description;


    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<UserEntity> users;

    public RoleEntity() {
        super();
    }
}
