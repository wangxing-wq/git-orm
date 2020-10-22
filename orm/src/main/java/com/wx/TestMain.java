package com.wx;

import com.wx.orm.SqlSession;

public class TestMain {

    public static void main(String[] args) {
        SqlSession session = new SqlSession();
        StudentDao dao = session.getMapper(StudentDao.class);
        dao.removeStudent(1);
    }

}
