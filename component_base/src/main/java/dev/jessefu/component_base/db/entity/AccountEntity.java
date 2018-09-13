package dev.jessefu.component_base.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Objects;

@Entity
public class AccountEntity {

    @Id
    private Long id;

    @Index(unique = true)
    private String title;

    private String account;

    private String password;

    private String description;

    private boolean isStar;

    private String Category;


    @Generated(hash = 1783461976)
    public AccountEntity(Long id, String title, String account, String password,
            String description, boolean isStar, String Category) {
        this.id = id;
        this.title = title;
        this.account = account;
        this.password = password;
        this.description = description;
        this.isStar = isStar;
        this.Category = Category;
    }

    @Generated(hash = 40307897)
    public AccountEntity() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsStar() {
        return this.isStar;
    }

    public void setIsStar(boolean isStar) {
        this.isStar = isStar;
    }

    public String getCategory() {
        return this.Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountEntity)) return false;
        AccountEntity entity = (AccountEntity) o;
        return isStar == entity.isStar &&
                Objects.equals(id, entity.id) &&
                Objects.equals(title, entity.title) &&
                Objects.equals(account, entity.account) &&
                Objects.equals(password, entity.password) &&
                Objects.equals(description, entity.description) &&
                Objects.equals(Category, entity.Category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, account, password, description, isStar, Category);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountEntity{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", account='").append(account).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", isStar=").append(isStar);
        sb.append(", Category='").append(Category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
