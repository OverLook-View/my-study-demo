package com.sy.springboot.demo2.springbootdemo2.service;

import com.github.pagehelper.PageInfo;
import com.sy.springboot.demo2.springbootdemo2.bean.BaseBean;

import java.util.List;

public interface BaseService<T extends BaseBean> {
    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    T queryById(int id);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<T> queryAll();

    /**
     * 根据条件查询一条数据
     *
     * @param record
     * @return
     */
    T queryOne(T record);

    /**
     * 根据条件查询数据列表
     *
     * @param record
     * @return
     */
    List<T> queryListByWhere(T record);

    /**
     * 分页查询数据列表
     *
     * @param page
     * @param rows
     * @param record
     * @return
     */
    PageInfo<T> queryPageListByWhere(Integer page, Integer rows, T record);

    /**
     * 新增数据
     *
     * @param t
     * @return
     */
    Integer insert(T t);

    /**
     * 有选择的保存，null的属性不会保存，会使用数据库默认值
     *
     * @param t
     * @return
     */
    Integer insertSelective(T t);

    /**
     * 更新数据
     *
     * @param t
     * @return
     */
    Integer update(T t);

    /**
     * 有选择的更新，选择不为null的字段作为插入字段
     *
     * @param t
     * @return
     */
    Integer updateSelective(T t);

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 批量删除
     *
     * @param clazz
     * @param property
     * @param values
     * @return
     */
    Integer deleteByIds(Class<T> clazz, String property, List<Object> values);

    /**
     * 根据条件删除数据
     *
     * @param record
     * @return
     */
    Integer deleteByWhere(T record);
}
