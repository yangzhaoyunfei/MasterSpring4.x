package com.smart.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 * PO 类都是一堆属性的集合,通过 JPA 注解来配置 PO 类与数据表的映射关系
 * post(普通回复帖子) PO类(持久化类),post/mainpost,是一张表,区别: post_type: 1,MainPost; 2,Post
 * Inheritance 注解指定 PO 映射继承关系: TABLE_PER_CLASS,每个类一张表;JOINED,连接的子类;SINGLE_TABLE,多个类层次结构对应一张表.
 * DiscriminatorColumn 注解定义了辨别符列
 * DiscriminatorValue 在多个类对应一张表时,通过该值辨别这个类.
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "t_post")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "post_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("1")
public class Post extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_text")
    private String postText;

    @Column(name = "board_id")
    private int boardId;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * ManyToOne 注解定义了多对一关系
     * JoinColumn 注解定义了多对一的关联规则;没有该注解则自动处理,在系统中创建连接列
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "topic_id")
    private Topic topic;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

