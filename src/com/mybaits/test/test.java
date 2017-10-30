package com.mybaits.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mybaits.pojo.Person;
import com.mybaits.pojo.Student;
import com.mybaits.pojo.StudentMapper;

public class test {
	
	SqlSession session = null;
	
	@Before
	public void setup(){
		//加载sqlMapConfig.xml
        InputStream is = null;
		try {
			is = Resources.getResourceAsStream("sqlMapConfig.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //解析sqlMapConfig.xml，创建SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //通过factory创建session对象
        session = factory.openSession();
	}
	
	@Test
	public void addTest(){
        Person person = new Person("mb",24,"China");
        int count = session.insert("pojo.Person.addPerson",person);
        System.out.println(count>1?"添加成功！":"添加失败！");  
	}
	
	@Test
	public void deleteTest(){
        int count = session.delete("pojo.Person.deletePerson",3);
        System.out.println(count>1?"删除成功！":"删除失败！"); 
	}
	
	@Test
	public void updateTest(){
		Person person = new Person(4,"mb",18,"hainan");
        int count = session.update("pojo.Person.updatePerson",person);
        System.out.println(count>1?"更新成功！":"更新失败！"); 
	}
	
	@Test
	public void selectPersonAllTest(){
        List<Person> list = session.selectList("pojo.Person.selectPersonAll");
        for(Person person : list){
        	System.out.println(person.getName()); 
        }
	}
	
	@Test
	public void selectPersonByIdTest(){
        List<Person> list = session.selectList("pojo.Person.selectPersonById",4);
        for(Person person : list){
        	System.out.println(person.getName()); 
        }
	}
	
	@Test
	public void getAllStudentTest(){
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        List<Student> list = studentMapper.selectStudentAll();
        for(Student student : list){
        	System.out.println(student.getName()); 
        }
	}
	
	@After
	public void reardown(){
		session.commit();
        if(session!=null){
        	session.close();
        }
	}
}
