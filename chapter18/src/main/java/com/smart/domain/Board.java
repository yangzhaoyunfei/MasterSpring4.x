package com.smart.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Description:
 * 每个持久化PO类都是一个实体bean,通过@Entity进行声明;
 * PO 类都是一堆属性的集合,通过 JPA 注解来配置 PO 类与数据表的映射关系
 * Table 为PO类指定对应的数据表\目录\schema的名字
 * Cache 为PO类设置缓存策略(分别是NONE, READ_ONLY, READ_WRITE, NONSTRICT_READ_WRITE, TRANSACTIONAL)
 *
 * @author tangzhongwei tangzw@zjbdos.com
 * @date 2018/4/20
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_board")
public class Board extends BaseDomain {

    /**
     * Id 注解将该字段定义为主键
     * GeneratedValue 注解定义主键值的生成策略(分别是AUTO, TABLE, IDENTITY, SEQUENCE)
     * Column 注解将PO类的各个字段映射到数据库表的对应列
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private int boardId;


    @Column(name = "board_name")
    private String boardName;

    @Column(name = "board_desc")
    private String boardDesc;

    @Column(name = "topic_num")
    private int topicNum;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "manBoards", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<User>();

    public int getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(int topicNum) {
        this.topicNum = topicNum;
    }

    public String getBoardDesc() {
        return boardDesc;
    }

    public void setBoardDesc(String boardDesc) {
        this.boardDesc = boardDesc;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
