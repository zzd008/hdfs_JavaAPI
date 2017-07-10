package hdfs_demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
//读取文件
public class Hdfs_Test2 {

	public static void main(String[] args) {
		try {
			String filename="hdfs://localhost:9000/Dir1/a.txt";
			Path p=new Path(filename);
			Configuration conf=new Configuration();
			conf.set("fs.defaultFS", "hdfs://localhost:9000");
			FileSystem fs=FileSystem.get(conf);
			//获取文件输入流(字节流)  
			//该对象封装了DFSinputstream对象 后续操作其实是由它来完成的
			FSDataInputStream read = fs.open(p);
			//将字节流包装成字符流
			/*这样比较原始
			int ch;
			while((b=read.read())!=-1){
				System.out.print((char)ch);
			}*/
			InputStreamReader in=new InputStreamReader(read);
			//将字符流再包装成字符缓冲流
			BufferedReader b=new BufferedReader(in);
			//读取内容
			String content=b.readLine();
			System.out.println(content);
			//关闭资源
			b.close();
			fs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
