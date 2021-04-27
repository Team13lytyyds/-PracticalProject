import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Test extends JFrame{
    // 定义组件
    private JScrollPane scpDemo;
    private JTableHeader jth;
    private JTable tabDemo;
    private JButton btnShow;
    // 构造方法
    public Test(){
// 窗体的相关属性的定义
        super("JTable数据绑定示例");
        this.setSize(500,500);
        this.setLayout(null);
        this.setLocation(10,50);
// 创建组件
        this.scpDemo = new JScrollPane();
        this.scpDemo.setBounds(20,60,450,300);
        this.btnShow = new JButton("显示数据");
        this.btnShow.setBounds(50,10,300,40);
// 给按钮注册监听
        this.btnShow.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                btnShow_ActionPerformed(ae);
            }
        });
// 将组件加入到窗体中
        add(this.scpDemo);
        add(this.btnShow);
// 显示窗体
        this.setVisible(true);
    }
    // 点击按钮时的事件处理
    public void btnShow_ActionPerformed(ActionEvent ae){
// 以下是连接数据源和显示数据的具体处理方法，请注意下
        try{
// 获得连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/softwork?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","123456");
// 建立查询条件
            String sql = "select * from booksinfo";
            Statement pstm = conn.createStatement();
// 执行查询
            ResultSet rs =pstm.executeQuery(sql);
// 计算有多少条记录
            int count = 0;
            while(rs.next()){
                count++;
            }
            rs = pstm.executeQuery(sql);
// 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][3];
            count = 0;
            while(rs.next()){
                info[count][0] = rs.getString("bookname");
                info[count][1] = rs.getString("editor");
                info[count][2] = rs.getDouble("price");
                //info[count][3] = rs.getString("sex");
                count++;
            }//
// 定义表头
            String[] title = {"书名","出版社","价格"};
// 创建JTable
            this.tabDemo = new JTable(info,title);
// 显示表头
            this.jth = this.tabDemo.getTableHeader();
// 将JTable加入到带滚动条的面板中
            this.scpDemo.getViewport().add(tabDemo);
        }catch(ClassNotFoundException cnfe){
            JOptionPane.showMessageDialog(null,"数据源错误","错误",JOptionPane.ERROR_MESSAGE);
        }catch(SQLException sqle){
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args){
        new Test();
    }
}

