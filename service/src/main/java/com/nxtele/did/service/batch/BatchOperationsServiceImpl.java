package com.nxtele.did.service.batch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchOperationsServiceImpl<T> implements BatchOperationsService<T> {

    // 默认批次大小
    private final static int BATCH_SIZE_DEF = 1000;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public void batchInsert(List<T> objectList, String fullClassName,int batchSize){
        if (batchSize == 0){
            batchSize = BATCH_SIZE_DEF;
        }
        int endNum = batchSize - 1;
        // 第二个参数为false：关闭自动提交
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            Class mapperClass = Class.forName(fullClassName);
            BaseMapper mapper = (BaseMapper) sqlSession.getMapper(mapperClass);
            for (int i = 0; i < objectList.size(); i++){
                mapper.insert(objectList.get(i));
                // 1000条数据为一个批次
                if( i % batchSize == endNum || i == objectList.size() - 1) {
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }

    @Override
    public void batchUpdate(List<T> objectList, String fullClassName,int batchSize) {
        if (batchSize == 0){
            batchSize = BATCH_SIZE_DEF;
        }
        int endNum = batchSize - 1;
        // 第二个参数为false：关闭自动提交
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            Class mapperClass = Class.forName(fullClassName);
            BaseMapper mapper = (BaseMapper) sqlSession.getMapper(mapperClass);
            for (int i = 0; i < objectList.size(); i++){
                mapper.updateById(objectList.get(i));
                // 1000条数据为一个批次
                if( i % batchSize == endNum || i == objectList.size() - 1) {
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
            sqlSession.rollback();
        }finally {
            sqlSession.close();
        }
    }

}
