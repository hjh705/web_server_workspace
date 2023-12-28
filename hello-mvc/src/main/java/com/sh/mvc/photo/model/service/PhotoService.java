package com.sh.mvc.photo.model.service;

import com.sh.mvc.photo.model.dao.PhotoDao;
import com.sh.mvc.photo.model.entity.Photo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.mvc.common.SqlSessionTemplate.getSqlSession;

public class PhotoService {
    private PhotoDao photoDao = new PhotoDao();


    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = photoDao.getTotalCount(session);
        session.close();
        return totalCount;
    }

    public List<Photo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<Photo> photos = photoDao.findAll(session, param);
        session.close();
        return photos;
    }
}
