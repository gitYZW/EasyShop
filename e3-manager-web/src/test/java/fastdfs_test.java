

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class fastdfs_test {
	
	
	@Test
	public void demo1() throws Exception{
		//加载fastdfs配置文件
		ClientGlobal.init("C:/Users/kewin/eclipse-workspace/e3-manager-web/src/main/resources/client.conf");
		
		TrackerClient trackerclient = new TrackerClient();
		TrackerServer trackerserver = trackerclient.getConnection();
		StorageServer storageserver = null;
		StorageClient storageclient = new StorageClient(trackerserver,storageserver);
		String str = "C:\\Users\\kewin\\Desktop\\11111111aaaaaa.jpg";
		String location = str.replaceAll("\\\\","/");
		String[] string = storageclient.upload_file(location,"jpg",null);
		for (String string2 : string) {
			System.out.println(string2);
		}
	}
	
	@Test
	public void demo2() throws Exception {
		FastDFSClient fastDFSClient = new FastDFSClient("C:/Users/kewin/eclipse-workspace/e3-manager-web/src/main/resources/client.conf");
		String string = fastDFSClient.uploadFile("D:/壁纸/e574aeff63edd84360460c2a1d1c7759.jpg");
		System.out.println(string);
	}
}
