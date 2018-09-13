package dev.jessefu.component_base.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import dev.jessefu.component_base.db.entity.AccountEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ACCOUNT_ENTITY".
*/
public class AccountEntityDao extends AbstractDao<AccountEntity, Long> {

    public static final String TABLENAME = "ACCOUNT_ENTITY";

    /**
     * Properties of entity AccountEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Account = new Property(2, String.class, "account", false, "ACCOUNT");
        public final static Property Password = new Property(3, String.class, "password", false, "PASSWORD");
        public final static Property Description = new Property(4, String.class, "description", false, "DESCRIPTION");
        public final static Property IsStar = new Property(5, boolean.class, "isStar", false, "IS_STAR");
        public final static Property Category = new Property(6, String.class, "Category", false, "CATEGORY");
    }


    public AccountEntityDao(DaoConfig config) {
        super(config);
    }
    
    public AccountEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ACCOUNT_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TITLE\" TEXT," + // 1: title
                "\"ACCOUNT\" TEXT," + // 2: account
                "\"PASSWORD\" TEXT," + // 3: password
                "\"DESCRIPTION\" TEXT," + // 4: description
                "\"IS_STAR\" INTEGER NOT NULL ," + // 5: isStar
                "\"CATEGORY\" TEXT);"); // 6: Category
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_ACCOUNT_ENTITY_TITLE ON \"ACCOUNT_ENTITY\"" +
                " (\"TITLE\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ACCOUNT_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, AccountEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(3, account);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
        stmt.bindLong(6, entity.getIsStar() ? 1L: 0L);
 
        String Category = entity.getCategory();
        if (Category != null) {
            stmt.bindString(7, Category);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, AccountEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(2, title);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(3, account);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(4, password);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
        stmt.bindLong(6, entity.getIsStar() ? 1L: 0L);
 
        String Category = entity.getCategory();
        if (Category != null) {
            stmt.bindString(7, Category);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public AccountEntity readEntity(Cursor cursor, int offset) {
        AccountEntity entity = new AccountEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // account
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // password
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // description
            cursor.getShort(offset + 5) != 0, // isStar
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // Category
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, AccountEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAccount(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPassword(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDescription(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIsStar(cursor.getShort(offset + 5) != 0);
        entity.setCategory(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(AccountEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(AccountEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(AccountEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}