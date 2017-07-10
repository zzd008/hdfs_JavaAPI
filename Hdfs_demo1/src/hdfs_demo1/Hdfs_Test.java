package hdfs_demo1;
//hdfs所需的jar包　已放在Ｈdfs用户jar包里，以后用maven更方便
//javaAPI对应hdfs　shell命令
import java.net.URI;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
//判断文件是否存在
public class Hdfs_Test {
	public static void main(String args[]){
		try{
			//hdfs分布式文件系统上的文件路径
			String filename="hdfs://localhost:9000/Dir1/a.txt";
			//加载配置文件 会读取工程下bin目录下的core-site.xml和hdfs-site.xml，通过这两个配置文件来找到hdfs分布文件系统
			//所以要将hadoop/etc/hadoop下的core-site.xml和hdfs-site.xml复制到项目的bin/目录下
			Configuration conf=new Configuration();//要将core-site.xml和hdfs.ite.xml放到该项目的bin目录下　不然要设置配置信息conf.set("fs.defaultFS", "hdfs:localhost:9000");
			conf.set("fs.defaultFS", "hdfs://localhost:9000");
			conf.set("my name", "zzd");//(设置)加载配置项（键－值）
			conf.addResource("a.xml");//在configration注册资源（加载一个ｘｍｌ配置文件）要将ａ.ｘｍｌ放到ｂｉｎ目录下，因为默认不会读取a.xml文档
			System.out.println(conf.get("fs.defaultFS"));//读取配置项中value的值
			System.out.println(conf.get("my name"));
			System.out.println(conf.get("my age"));
			//FileSystem一个是通用文件系统的抽象基类 通过get()方法(参数为一个配置对象)来获取hdfs文件系统对象
			//DistributedFileSystem类是FileSystem类的实现类
			//FileSystem封装了DistributedFileSystem对象(分布式文件系统的对象)，以后对hdfs的操作其实都是这个对象来完成的
			FileSystem fs=FileSystem.get(conf);//加载配置来获取对应的文件系统　若conf什么都没配置文件都加载，则默认为本地文件系统
			//若bin/目录下没有那两个配置文件，通过设置ｕｒｉ来访问
			//FileSystem fs=FileSystem.get(URI.create("hdfs://localhost:9000"),conf);
			//通过文件的路径名获取该文件对象
			Path p=new Path(filename);
			//判断文件是存在
			if(fs.exists(p)){
				System.out.println("hdfs分布式文件系统中存在该文件！");
			}else{
				System.out.println("hdfs分布式文件系统中不存在该文件！");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
