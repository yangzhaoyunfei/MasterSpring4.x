package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.stereotype.Repository;

/**
 * Description: 登录日子的DAO类
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/4/24
 */
@Repository
public class LoginLogDao extends BaseDao<LoginLog> {
    @Override
    public void save(LoginLog loginLog) {
        this.getHibernateTemplate().save(loginLog);
    }

}
