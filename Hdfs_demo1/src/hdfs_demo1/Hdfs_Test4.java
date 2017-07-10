package hdfs_demo1;

import java.io.IOException;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.hamcrest.core.Is;
//输入流的属性
public class Hdfs_Test4 {
public static void main(String[] args) {
	Configuration conf=new Configuration();
	conf.set("fs.defaultFS", "hdfs://localhost:9000");
	String filename="/Dir1/a.txt";//在hdfs文件系统下　所以可以省略前面的前缀hdfs://localhost:9000
	Path p=new Path(filename);
	try {
		FileSystem fs=FileSystem.get(conf);
		if(fs.exists(p)){//判断文件是否存在
			FSDataInputStream read = fs.open(p);
			System.out.print("从头读取：");
			IOUtils.copyBytes(read, System.out, 1024,false);//流复制 将字节流复制到标准输出流(控制台)
			System.out.print("从第三个位置读取：");
			read.seek(3);
			IOUtils.copyBytes(read, System.out, 1024,false);//形参：　流　流　复制的缓冲区大小　流用完后是否关闭
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	
}
}
