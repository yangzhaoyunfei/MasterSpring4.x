package com.smart.dao;

import com.smart.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

/**
 * Description: 通过扩展基类,定义Dao类
 * Dao类一般都要使用@Repository注解
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/4/24
 */

/**
 * 扩展Dao类,并确定泛型参数限定的类为Board
 */
@Repository
public class BoardDao extends BaseDao<Board> {
    private static final String GET_BOARD_NUM = "select count(f.boardId) from Board f";

    /**
     * 获取论坛版块的数目
     *
     * @return
     */
    public long getBoardNum() {
        //迭代器
        Iterator iter = getHibernateTemplate().iterate(GET_BOARD_NUM);
        return ((Long) iter.next());
    }
}
