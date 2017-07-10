package hdfs_demo1;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
//写文件
public class Hdfs_Test3 {
	public static void main(String[] args) {
		try {
			String filename="hdfs://127.0.0.1:9000/Dir1/b.txt";
			Path p=new Path(filename);
			Configuration conf=new Configuration();
			FileSystem fs=FileSystem.get(URI.create("hdfs://127.0.0.1:9000"),conf);
			//获取文件输出流(字节流)  
			//该对象封装了DFSoutputstream对象 后续操作由它来完成
			FSDataOutputStream write = fs.create(p);
			byte[]b="Hello hadoop!".getBytes();//每次是覆盖而不是追加
			//将内容写入文件
			write.write(b, 0, b.length);
			System.out.println("文件写入成功！");
			//也可以将字节输出流连续封装
			/*BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(write));
			bw.write("Hello hadoop!");*/
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
