package com.exercicio.modulo3.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;
    private String senha;

    private String nome;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TelefoneModel> telefoneModels = new ArrayList<TelefoneModel>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_role", uniqueConstraints = @UniqueConstraint(
            columnNames = {"usuario_id", "role_id"}, name = "unique_role_usuario"),
            joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id", table = "usuario",
                    foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id", table = "role",
                    updatable = false,
                    foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)))
    private List<RoleModel> roleModels;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleModels;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
