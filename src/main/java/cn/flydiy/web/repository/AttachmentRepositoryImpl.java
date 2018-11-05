package cn.flydiy.web.repository;

import cn.flydiy.core.common.db.DBHelper;
import cn.flydiy.core.common.db.expression.Expression;
import cn.flydiy.core.repository.BaseRepositoryImpl;
import cn.flydiy.web.entity.Attachment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by flying on 16-11-26.
 */
@Repository
public class AttachmentRepositoryImpl extends BaseRepositoryImpl<Attachment> implements AttachmentRepository {
    @Override
    public void updateBizIdByIds(String[] ids,String bizId) {
        DBHelper<Attachment> dbHelper = getDbHelper();
        dbHelper.setUpdateColumn("bizId",bizId);
        dbHelper.add(Expression.in("id", ids));

        dbHelper.update();
    }

    @Override
    public List<Attachment> queryByBizTableAndBizId(String bizTable, String bizId) {
        DBHelper<Attachment> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("bizId", bizId));
        dbHelper.add(Expression.eq("bizTable", bizTable));

        return dbHelper.list();
    }

    @Override
    public void deleteByUrls(String[] urls) {
        DBHelper<Attachment> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("url", urls));

        dbHelper.delete();
    }

    @Override
    public List<Attachment> queryAttachByUrl(String url) {
        DBHelper<Attachment> dbHelper = getDbHelper();
        dbHelper.add(Expression.eq("url", url));

        return dbHelper.list();
    }

    @Override
    public List<Attachment> queryByUrls(String... urls) {
        DBHelper<Attachment> dbHelper = getDbHelper();
        dbHelper.add(Expression.in("url", urls));
        return dbHelper.list();
    }

}
