package hdfs_demo1;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
//创建、删除文件夹
public class Hdfs_Test5 {

	public static void main(String[] args) {
		/*第一组运行参数
		 * file:///home/　					args[0]
			/home/hadoop/aaaad.txt　　　　	args[1]
			file:///home/hadoop/aaaad.txt　　args[2]
		 */
		/*第二组运行参数
		 * hdfs://localhost:9000　args[0]
			/test				　args[1]
			/home				　args[2]
		 */
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://localhost:9000");
		String hdfspath=args[0];//指定运行参数 
		String filepath=args[1];
		try {			//若访问本地文件系统，则运行参数指定为：file:///home/ｘｘｘｘ　访问hdfs则指定为hdfs://localhost:9000/xxx
			FileSystem fs=FileSystem.get(URI.create(hdfspath),conf);//运行参数可以指定文件系统时本地的还是hdfs　可以对两者进行相互操作（文件复制等等）
			boolean mk = fs.mkdirs(new Path(filepath));//创建文件夹
			if(mk){
				System.out.println("mkdir sucess!");
			}else{
				System.out.println("mkdir failed!");
			}
			boolean del=fs.delete(new Path(args[2]), true);//true 递归删除
			if(del){			//因为前面指定的文件系统是本地文件系统，只能访问本地文件，所以这个运行参数不能是hdfs://localhost:9000/home
								//同理，若filesystem指定的uri是hdfs的，则也无法访问本地文件系统
				System.out.println("delete dir sucess!");
			}else{
				System.out.println("delete dir failed!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
