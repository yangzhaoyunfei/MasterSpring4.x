package com.smart.dao;

import com.smart.domain.Board;
import com.smart.test.dataset.util.XlsDataSetBeanFactory;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


/**
 * Description: 测试用例
 *
 * @author yangzhaoyunfei yangzhaoyunfei@qq.com
 * @date 2018/4/26
 */
@SpringApplicationContext({"xiaochun-dao.xml"})
public class BoardDaoTest extends UnitilsTestNG {

    /**
     * 1.从Spring容器加载BoardDao,命名为boardDao
     */
    @SpringBean("boardDao")
    private BoardDao boardDao;


    /**
     * 2.创建一个新的论坛版块
     *
     * @throws Exception
     */
    @Test//该注解将方法标记为测试方法
    @ExpectedDataSet("XiaoChun.ExpectedBoards.xls")//期望的验证数据,该注解从测试用例当前所在classpath加载验证数据集文件
    public void addBoard() throws Exception {
        //通过XlsDataSetBeanFactory数据集绑定工厂读取测试数据集文件,并创建测试实体
        List<Board> boards = XlsDataSetBeanFactory.createBeans(BoardDaoTest.class, "XiaoChun.SaveBoards.xls", "t_board", Board.class);
        //持久化实例
        for (Board board : boards) {
            boardDao.save(board);//对BoardDao#save()方法进行测试
        }
    }

    /**
     * 3.删除一个版块
     *
     * @param
     */
    @Test
    @DataSet(value = "XiaoChun.Boards.xls")//测试数据
    @ExpectedDataSet(value = "XiaoChun.ExpectedBoards.xls")//验证数据
    public void removeBoard() {
        //加载指定的版块
        Board board = boardDao.get(7);//对BoardDao#remove()方法进行测试
        //删除指定的版块
        boardDao.remove(board);
    }

    /**
     * 4.测试加载版块
     */
    @Test
    @DataSet("XiaoChun.Boards.xls")//测试数据
    public void getBoard() {
        //加载版块
        Board board = boardDao.load(1);//对BoardDao#load()方法进行测试
        //验证结果
        assertNotNull(board);
        assertEquals(board.getBoardName(), "育儿");
    }
}
