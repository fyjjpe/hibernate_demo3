import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by yuanjie.fang on 2018/2/27.
 */
public class StudentTest {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init() {
        //创建服务配置对象
        Configuration config = new Configuration().configure();
        //创建服务注册对象
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        //创建会话工厂对象
        sessionFactory = config.buildSessionFactory(serviceRegistry);
        //创建会话
        session = sessionFactory.openSession();
        //开启事务
        transaction = session.beginTransaction();

    }

    @After
    public void destory() {
        transaction.commit();//提交事务
        session.close();//关闭会话
        sessionFactory.close();//关闭会话工厂
    }

    @Test
    public void testSaveStudent() {
//        Students s = new Students(1,"张三","男",new Date(),"苏州");
        Students s = new Students();
        s.setSid(100);
        s.setSname("李四");
        s.setGender("男");
        s.setBirthday(new Date());
        Address address = new Address("123456","011-122222","南京");
        s.setAddress(address);
        session.save(s);//保存对象进数据库
    }

    @Test
    public void testWriteBolb() throws Exception {
        Students s = new Students();
        s.setSid(100);
        s.setSname("李四");
        s.setGender("男");
        s.setBirthday(new Date());
        //先获取照片
        File f = new File("d:" + File.separator + "icon.jpg");
        //获取文件输入流
        InputStream input = new FileInputStream(f);
        //创建一个Bolb对象
        Blob image = Hibernate.getLobCreator(session).createBlob(input, input.available());
        //设置学生照片熟悉
        s.setPicture(image);
        session.save(s);
    }

    @Test
    public void readBolb() throws Exception {
        Students s = (Students) session.get(Students.class, 1);
        Blob image = s.getPicture();
        //获取输入流
        InputStream input = image.getBinaryStream();
        //创建输出流
        File f = new File("d:" + File.separator + "dest.jpg");
        OutputStream output = new FileOutputStream(f);
        byte[] buff = new byte[input.available()];
        input.read(buff);
        output.write(buff);
        input.close();
        output.close();
    }


}
