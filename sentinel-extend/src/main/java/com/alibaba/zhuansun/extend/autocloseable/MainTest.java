package com.alibaba.zhuansun.extend.autocloseable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author : zhuansun
 * @date : 2020-08-17 20:09
 **/
public class MainTest {
    public static void main(String[] args) {


        //test1();


        test2();



    }

    private static void test2() {


        //jdk1.7之前关闭流的方式
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("");
            fileInputStream.read();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if (Objects.nonNull(fileInputStream)){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        //jdk1.7之后关闭流的方式
        try (FileInputStream fileInputStream1 = new FileInputStream("")){
            fileInputStream1.read();
        } catch (Exception e) {

            //只要抛异常，流会自动管理，因为FileInputStream实现了AutoCloseable

        }
    }

    public static void  test1(){
        //可以看到try中的代码一旦有异常，会自动关闭资源
        // 但是必须要使用jdk7这种的try方式;普通的try是不生效的
        try(MyAutoClose myAutoClose = new MyAutoClose();) {
            int a = 3/0;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            System.out.println("11111");
        }

    }

}
