package dev.jessefu.component_base.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import dev.jessefu.component_base.db.entity.AcountEntity;

import dev.jessefu.component_base.db.AcountEntityDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig acountEntityDaoConfig;

    private final AcountEntityDao acountEntityDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        acountEntityDaoConfig = daoConfigMap.get(AcountEntityDao.class).clone();
        acountEntityDaoConfig.initIdentityScope(type);

        acountEntityDao = new AcountEntityDao(acountEntityDaoConfig, this);

        registerDao(AcountEntity.class, acountEntityDao);
    }
    
    public void clear() {
        acountEntityDaoConfig.clearIdentityScope();
    }

    public AcountEntityDao getAcountEntityDao() {
        return acountEntityDao;
    }

}
