package dev.jessefu.component_base.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

import io.reactivex.SingleSource;

/**代表帐户分类*/
@Entity
public class CategoryEntity {

    @Id
    private Long id;

    @Unique
    private String name;

    @Generated(hash = 134407582)
    public CategoryEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 725894750)
    public CategoryEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CategoryEntity{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
