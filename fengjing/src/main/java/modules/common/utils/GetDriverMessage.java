package modules.common.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

/**
 * 获取系统 磁盘信息
 * 盘符 盘符对应的剩余空间  盘符对应的总空间大小 单位 B
 * 只针对windows测试过 linux没有测试
 * @author O_O
 *
 */
public class GetDriverMessage {
	
	/**
	 * 获取单个磁盘剩余空间大小
	 * @param driverName 盘符名
	 * @return 盘符不存在 返回0 单位 B
	 */
	public long oneDriverFreeSpace(String driverName){
		if(!"".equals(driverName) && driverName != null){
			try{
				return driver().get(driverName)[0];
			}catch(Exception e){
				return 0L;
			}
		}
		return 0L;
	}
	
	/**
	 * 获取单个磁盘总空间大小
	 * @param driverName 盘符名
	 * @return 盘符不存在 返回0 单位 B
	 */
	public long oneDriverTotalSpace(String driverName){
		if(!"".equals(driverName) && driverName != null){
			try{
				return driver().get(driverName)[1];
			}catch(Exception e){
				return 0L;
			}
		}
		return 0L;
	}
	
	/**
    * 获取硬盘的每个盘符
	 * @return 返回所有存在磁盘的盘符 剩余空间大小 总空间大小,单位 B 程序可以执行 肯定有盘符存在的
    */
   public Map<String, long[]> driver(){
	   Map<String, long[]> map = new HashMap<String, long[]>();
       FileSystemView fsv = FileSystemView.getFileSystemView();//当前文件系统类
       String sdpn = "";
       File[] fs = File.listRoots();//列出所有windows 磁盘
       for (int i = 0; i < fs.length; i++) {
    	   sdpn = fsv.getSystemDisplayName(fs[i]);
    	   if(sdpn != null && !"".equals(sdpn)){
    		   long[] dSize = new long[]{fs[i].getFreeSpace(),fs[i].getTotalSpace()};// 磁盘盘符,剩余大小,总大小
    		   map.put(sdpn.substring(sdpn.lastIndexOf("(")+1, sdpn.lastIndexOf(":")),dSize);
    	   }
       }
       return map;
   }
   
   /**
    * 磁盘大小格式化输出
    * @param fileS
    * @return 返回带单位的磁盘空间值
    */
   public String FormetFileSize(long fileS) {
       DecimalFormat df = new DecimalFormat("#.00");
       String fileSizeString = "";
       if(fileS <= 0){
    	   fileSizeString = "0B";
       }else if (fileS < 1024) {
           fileSizeString = df.format((double) fileS) + "B";
       } else if (fileS < 1048576L) {
           fileSizeString = df.format((double) fileS / 1024) + "K";
       } else if (fileS < 1073741824L) {
           fileSizeString = df.format((double) fileS / 1048576L) + "M";
       } else if(fileS < 1099511627776L) {
           fileSizeString = df.format((double) fileS / 1073741824L) + "G";
       } else if(fileS < 1125899906842624L){
    	   fileSizeString = df.format((double) fileS / 1099511627776L) + "T";
       }
       return fileSizeString;
   }
   /*public static void main(String[] args) {
	   System.out.println(oneDriverFreeSpace("L"));
	   System.out.println(oneDriverTotalSpace("L"));
	   System.out.println(FormetFileSize(oneDriverFreeSpace("L")));
   }*/
}
