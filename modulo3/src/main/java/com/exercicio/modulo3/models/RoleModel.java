package com.exercicio.modulo3.models;

import com.sun.xml.bind.v2.TODO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class RoleModel implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nomeRole;//Esta Strim contem as permições de acesso ao sistem...GERENTE,ADMINISTRADOR


    @Override
    public String getAuthority() {
        return this.nomeRole;
    }
}
