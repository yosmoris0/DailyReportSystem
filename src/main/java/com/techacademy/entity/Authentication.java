package com.techacademy.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;


@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** 権限の列挙型 */
    public static enum Role {
        管理者, 一般
    }

    /** ログインID */
    @Id
    @Column(length = 20, nullable = false)
    @NotEmpty
    @Length(max=20)
    private String code;

    /** パスワード */
    @Column(length = 255, nullable = false)
    @Length(max=255)
    private String password;

    /** 権限　列挙型（文字列） */
    @Column(length = 3)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    /** id */
    @OneToOne
    @JoinColumn(name="employee_id", referencedColumnName="id")
    private Employee employee;
}