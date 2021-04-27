package java0181.lesson7;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookDao {
    public static void main(String[] args) {
               // TODO Auto-generated method stub
               try {
                   Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动
                   System.out.println("加载数据库驱动成功");
                   String url="jdbc:mysql://localhost:3306/mydata?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";//声明自己的数据库test的url
                   String user="root";//自己的数据库用户名
                   String pass="123456";//自己的数据库密码
                        //建立数据库连接，获得连接的对象conn
                   Connection conn=DriverManager.getConnection(url,user,pass);
                   System.out.println("连接数据库驱动成功");
                   Statement stmt=conn.createStatement();//创建一个Statement对象
                   String sql="select * from book where id like '1' ";//生成sql语句
                   ResultSet rs=stmt.executeQuery(sql);//执行sql语句
                   int id;
                   float price;
                   String book_name,author;
                   System.out.println("id\t book_name\t author\t price");
                   while(rs.next()){
                       id=rs.getInt("id");
                       book_name=rs.getString("book_name");
                       author=rs.getString("author");
                       price=rs.getFloat(4);
                       System.out.println(id+"\t"+book_name+"\t"+author+"\t" +price);//输出查询结果
                        }
                       System.out.println("模糊查询成功");
                       conn.close();//关闭数据库连接
                       System.out.println("关闭数据库连接成功");
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                         e.printStackTrace();
                     } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

            }

}
