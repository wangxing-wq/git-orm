package com.wx;

import com.wx.entity.Student;
import com.wx.orm.annotation.Delete;
import com.wx.orm.annotation.Insert;
import com.wx.orm.annotation.Select;
import com.wx.orm.annotation.Update;

import java.util.List;

public interface StudentDao {

    @Insert("insert into student values(#{id},#{name})")
    Boolean addStudent(Student student);

    @Delete("delete from student where id = #{id}")
    Boolean removeStudent(Integer id);

    @Update("update student set name = #{name} where id = #{id}")
    Boolean updateStudent(Student student);

    @Select("select * from student where id = #{id}")
    Student selectOne(Integer id);

    List<Student> selectList();
}
