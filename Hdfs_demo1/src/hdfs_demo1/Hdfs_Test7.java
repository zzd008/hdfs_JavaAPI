package hdfs_demo1;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FsStatus;
import org.apache.hadoop.fs.Path;
//获取文件的元数据信息
public class Hdfs_Test7 {
	public static void main(String[] args) {
		Configuration conf=new Configuration();
		try {
			FileSystem fs=FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
			String filepath=args[0];
			Path p=new Path(filepath);
			if(fs.isDirectory(p)){//是目录
				FileStatus[] ls = fs.listStatus(p);//得到文件对象数组
				System.out.println("文件的元数据信息：");
				for(FileStatus i : ls) {
					System.out.print(i.getBlockSize()+i.getOwner()+i.getPath()+i.getLen());
					System.out.println();
				}
			}else{
				FileStatus f=fs.getFileStatus(p);//得到一个文件，filestatus对象包含了文件的元数据信息
				System.out.println(f.toString()+f.getBlockSize()+f.getGroup());//输出文件名、块大小、所在组
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
