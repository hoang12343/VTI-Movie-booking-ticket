package vti_galaxy.modal.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import vti_galaxy.modal.constant.StatusActive;

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper=true)
public class Account extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String fullName;

    @Column
    private String avatar;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusActive status;

    public enum Position {
        ADMIN,
        MANAGER,
        USER
    }
}
