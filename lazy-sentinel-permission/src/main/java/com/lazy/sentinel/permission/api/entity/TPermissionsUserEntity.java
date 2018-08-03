package com.lazy.sentinel.permission.api.entity;



import com.lazy.cheetah.core.entity.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>系统权限-普通用户实体类</p>
 *
 * @author laizhiyuan
 * @date 2018/03/23
 */
@Entity
@Table(
        name = "t_permissions_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_login_name", columnNames = {"loginName"})
        },
        schema = "lazy-sentinel", catalog = ""
)
public class TPermissionsUserEntity extends BaseEntity {
    private static final long serialVersionUID = 9578673566521456L;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 联系方式
     */
    private String contact;

    @Basic
    @Column(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "salt")
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Basic
    @Column(name = "contact")
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TPermissionsUserEntity that = (TPermissionsUserEntity) o;
        return id == that.id &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(password, that.password) &&
                Objects.equals(salt, that.salt) &&
                Objects.equals(loginName, that.loginName) &&
                Objects.equals(validStatus, that.validStatus) &&
                Objects.equals(contact, that.contact) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdateTime, that.lastUpdateTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nickname, password, salt, loginName, validStatus, contact, createTime, lastUpdateTime);
    }
}
