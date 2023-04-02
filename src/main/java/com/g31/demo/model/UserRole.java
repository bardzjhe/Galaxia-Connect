package com.g31.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description: JPA API requires that entities have to be serializable.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private AuditUser auditUser;

    @ManyToOne
    @JoinColumn
    private Role role;

    public UserRole(AuditUser auditUser, Role role) {
        this.auditUser = auditUser;
        this.role = role;
    }
}
