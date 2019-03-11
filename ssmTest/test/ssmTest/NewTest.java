import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.*;

import java.io.IOException;
import java.io.InputStream;

public class NewTest {

    private Logger logger = Logger.getLogger(NewTest.class);
    @Test
    public void test1(){
        String resource = "mybatis-config.xml";
        int count = 0;
        SqlSession sqlSession = null;

        try {
            //获取mybatis-config.xml的输入流
            InputStream inputStream = Resources.getResourceAsStream(resource);
            //创建SqlSessionFactory对象,完成对配置文件的读取
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            //创建sqlSession
            sqlSession = sqlSessionFactory.openSession();
            //调用mapper文件对数据进行操作
            count = sqlSession.selectOne("cn.SSM.dao.TestMapper.count");

            //使用mapper接口方式
            //count = sqlSession.getMapper(TestMapper.class).count();



            logger.debug("count is :"+count);
            System.out.println("count is :"+count);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(null != sqlSession)
                sqlSession.close();
        }



    }
}
