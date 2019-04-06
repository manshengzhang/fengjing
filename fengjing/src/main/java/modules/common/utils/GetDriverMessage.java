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
	   Map<String, long[]> map = new HashMap<String, long[]>(15);
	   //当前文件系统类
	   FileSystemView fsv = FileSystemView.getFileSystemView();
       String sdpn = "";
       //列出所有windows磁盘
       File[] fs = File.listRoots();
       for (int i = 0; i < fs.length; i++) {
    	   sdpn = fsv.getSystemDisplayName(fs[i]);
    	   if(sdpn != null && !"".equals(sdpn)){
    		   // 磁盘盘符,剩余大小,总大小
    		   long[] dSize = new long[]{fs[i].getFreeSpace(),fs[i].getTotalSpace()};
    		   map.put(sdpn.substring(sdpn.lastIndexOf("(")+1, sdpn.lastIndexOf(":")),dSize);
    	   }
       }
       return map;
   }
   
   /**
    * 磁盘大小格式化输出
    * @param files
    * @return 返回带单位的磁盘空间值
    */
   public String formetfilesize(long files) {
       DecimalFormat df = new DecimalFormat("#.00");
       String filesizeString = "";
       long b = 1024,kb = 1048576L,mb = 1073741824L,gb = 1099511627776L,tb = 1125899906842624L;
       if(files <= 0){
    	   filesizeString = "0B";
       }else if (files < b) {
           filesizeString = df.format((double) files) + "B";
       } else if (files < kb) {
           filesizeString = df.format((double) files / 1024) + "K";
       } else if (files < mb) {
           filesizeString = df.format((double) files / 1048576L) + "M";
       } else if(files < gb) {
           filesizeString = df.format((double) files / 1073741824L) + "G";
       } else if(files < tb){
    	   filesizeString = df.format((double) files / 1099511627776L) + "T";
       }
       return filesizeString;
   }
   /*public static void main(String[] args) {
	   System.out.println(oneDriverFreeSpace("L"));
	   System.out.println(oneDriverTotalSpace("L"));
	   System.out.println(Formetfilesize(oneDriverFreeSpace("L")));
   }*/
}
