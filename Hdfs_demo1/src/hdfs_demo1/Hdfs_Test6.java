package hdfs_demo1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
//流操作
public class Hdfs_Test6 {
	public static void main(String[] args) {
		/*运行参数
		 * /Dir1/a.txt
		 * /Dir1/zzd.txt
		 */
		Configuration conf=new Configuration();
		conf.set("fs.defaultFS", "hdfs://localhost:9000");
		try {
			String filepath=args[0];
			FileSystem fs=FileSystem.get(conf);//如果没做配置　默认访问本地文件系统
			InputStream in=fs.open(new Path(filepath));//打开一个文件　//用顶级父类来接收
			OutputStream out=fs.create(new Path(args[1]));//写入一个文件　
			//OutputStream out=new FileOutputStream(args[1].toString());//读不出去　fileoutputstream操作的是本地文件
			byte []b=new byte[2048];
			int c=1;
			while((c=in.read(b))!=-1){
				System.out.println(c);//读取的字节个数
				out.write(b, 0, c);
			}
			/*while((c=in.read())!=-1){  //一个一个字节读
				System.out.println(c);//一个utf-8的汉字占三个字节，gbk的占两个字节
			}*/
			in.close();
			IOUtils.closeStream(out);//关闭流　IOUtils是一个流工具类
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
