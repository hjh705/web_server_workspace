package com.sh.mvc.photo.model.dao;

import com.sh.mvc.photo.model.entity.Photo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class PhotoDao {
    public int getTotalCount(SqlSession session) {
        return session.selectOne("photo.getTotalCount");
    }

    public List<Photo> findAll(SqlSession session, Map<String, Object> param) {
        // Integer.parseInt -> String을 int로 바꿀때. 지금은 Object 라 int로 변환하면됨
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);

        return session.selectList("photo.findAll", null, rowBounds);
    }
}
