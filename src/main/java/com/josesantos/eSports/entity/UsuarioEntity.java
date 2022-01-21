package com.josesantos.eSports.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table (name="usuario")
@JsonIgnoreProperties ({"hibernateLazyInitialize", "handler"})

public class UsuarioEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String summonername;
    private String email;
    private String accountid;
    private String puuid;
    private Long profileiconid;
    private Long summonerlevel;
    private Long codigo;
    
    @ManyToOne
    @JoinColumn(name = "id_tipousuario")
    private TipoUsuarioEntity tipousuario;

    @ManyToOne
    @JoinColumn(name = "id_equipo")
    private EquipoEntity equipo;
    
//    @ManyToOne
//    @JoinColumn(name="id_file")
//    private FileEntity file;
    
    public UsuarioEntity(){
    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSummonername() {
        return summonername;
    }

    public void setSummonername(String summonername) {
        this.summonername = summonername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getPuuid() {
        return puuid;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public Long getProfileiconid() {
        return profileiconid;
    }

    public void setProfileiconid(Long profileiconid) {
        this.profileiconid = profileiconid;
    }

    public Long getSummonerlevel() {
        return summonerlevel;
    }

    public void setSummonerlevel(Long summonerlevel) {
        this.summonerlevel = summonerlevel;
    }

    public TipoUsuarioEntity getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(TipoUsuarioEntity tipousuario) {
        this.tipousuario = tipousuario;
    }

    public EquipoEntity getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntity equipo) {
        this.equipo = equipo;
    }

    //Poner el get y set del file lo hace fallar
//    public FileEntity getFile() {
//        return file;
//    }
//
//    public void setFile(FileEntity file) {
//        this.file = file;
//    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

}