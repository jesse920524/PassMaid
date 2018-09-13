package dev.jessefu.module_main.biz.star.model;

import java.util.List;

import dev.jessefu.component_base.db.entity.AccountEntity;
import io.reactivex.Observable;

public interface IStarModel {

    Observable<List<AccountEntity>> getEntityList();

    void initTestData();

    void clearTestData();
}
