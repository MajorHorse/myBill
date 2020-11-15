package com.bill.dao;

import com.bill.entity.Record;
import com.bill.utils.DBUtil;
import com.bill.utils.DateUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Major Tom
 * @date 2020/11/13 21:21
 * @description 消费记录crud
 */
public class RecordDao {
    /**
     * 得到所有消费记录的总数
     * @return 总数
     */
    public int getTotalRecords(){
        int total=0;
        String sql="select count(*) from record";
        try {
            Connection connection= DBUtil.getConnection();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                total=resultSet.getInt(1);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return total;
    }

    /**
     * 增加消费记录
     * @param record 消费记录实体
     */
    public void addRecord(Record record){
        String sql="insert into record values(null,?,?,?,?)";
        try {
            Connection connection=DBUtil.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1,record.getSpend());
            ps.setInt(2,record.getCategoryId());
            ps.setString(3,record.getComment());
            ps.setDate(4, DateUtil.util2Sql(record.getDate()));
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 删除消费记录
     * @param id 记录id
     */
    public void deleteRecord(int id){
        String sql="delete from record where id ="+id;
        try {
            Connection connection=DBUtil.getConnection();
            Statement statement=connection.createStatement();
            statement.execute(sql);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 修改消费记录
     * @param record 消费记录
     */
    public void updateRecord(Record record){
        String sql="update record set spend=?,category_id=?,comment=?,date=? where id=?";
        try {
            Connection connection=DBUtil.getConnection();
            PreparedStatement ps=connection.prepareStatement(sql);
            ps.setInt(1,record.getSpend());
            ps.setInt(2,record.getCategoryId());
            ps.setString(3,record.getComment());
            ps.setDate(4,DateUtil.util2Sql(record.getDate()));
            ps.setInt(5,record.getId());
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 通过id查询消费记录
     * @param id 消费记录id
     * @return 消费记录实体
     */
    public Record getRecord(int id){
        String sql="select * from record where id ="+id;
        Record record=new Record();
        try {
            Connection connection=DBUtil.getConnection();
            Statement statement=connection.createStatement();
            ResultSet rs= statement.executeQuery(sql);
            if (rs.next()){
                int spend=rs.getInt("spend");
                int category_id=rs.getInt("category_id");
                String comment=rs.getString("comment");
                Date date=rs.getDate("date");
                record.setSpend(spend);
                record.setCategoryId(category_id);
                record.setComment(comment);
                record.setDate(date);
                record.setId(id);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return record;
    }

    /**
     *
     * @param start
     * @param count
     * @return
     */
    public List<Record> listRecord(int start,int count){
        return null;
    }
    /**
     * 测试用
     * @param args
     */
    public static void main(String[] args) {
        RecordDao dao=new RecordDao();
//        System.out.println(dao.getTotalRecords());
//        Record record=new Record();
//        record.setCategoryId(1);
//        record.setSpend(100);
//        record.setComment("买肉吃");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        record.setDate(new Date());
//        dao.addRecord(record);

        System.out.println(dao.getRecord(1).toString());
    }
}
