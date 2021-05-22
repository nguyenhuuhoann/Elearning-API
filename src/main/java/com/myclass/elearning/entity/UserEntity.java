package com.myclass.elearning.entity;

import lombok.*;

import javax.management.relation.Role;
import javax.persistence.*;

@Entity
@Table(name = "users")
@Setter
@Getter
public class UserEntity extends BaseAuditEntity<String> {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "is_enabled")
    private Boolean enabled = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private RoleEntity roles;

    @Builder
    public UserEntity(Long id, String userName, String email, String password, String phoneNumber, RoleEntity roles) {
        super(id);
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles=roles;
    }

    public UserEntity() {
        super();
    }
}