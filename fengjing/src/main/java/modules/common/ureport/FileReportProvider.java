package modules.common.ureport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import modules.common.utils.GetDriverMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.bstek.ureport.exception.ReportException;
import com.bstek.ureport.provider.report.ReportFile;
import com.bstek.ureport.provider.report.ReportProvider;

/**
 * 自定义报表存储器
 * 这样就可以修改报表的存储位置
 * 修改报表的前缀
 * 修改报表的加载方式
 * @author O_O
 *
 */
public class FileReportProvider implements ReportProvider,ApplicationContextAware{
	/**文件前缀*/
	private String prefix="file:";
	/**文件存储路径*/
    private String fileStoreDir;
    /**期望存储报表的盘符空间剩余大小*/
    private long diskSpace;
    /**是否禁用*/
    private boolean disabled;
    
    /**
     * 根据报表名加载报表文件
     * @param file 报表名称
     * @return 返回的InputStream
     */
    @Override
    public InputStream loadReport(String file) {
        if(file.startsWith(prefix)){
            file=file.substring(prefix.length(),file.length());
        }
        String fullPath=fileStoreDir+"/"+file;
        try {
            return new FileInputStream(fullPath);
        } catch (FileNotFoundException e) {
            throw new ReportException(e);
        }
    }
     
    /**
     * 根据报表名，删除指定的报表文件
     * @param file 报表名称
     */
    @Override
    public void deleteReport(String file) {
        if(file.startsWith(prefix)){
            file=file.substring(prefix.length(),file.length());
        }
        String fullPath=fileStoreDir+"/"+file;
        File f=new File(fullPath);
        if(f.exists()){
            f.delete();
        }
    }
    
    /**
     * 获取所有的报表文件
     * @return 返回报表文件列表
     */
    @Override
    public List<ReportFile> getReportFiles() {
        File file=new File(fileStoreDir);
        List<ReportFile> list=new ArrayList<ReportFile>();
        for(File f:file.listFiles()){
            Calendar calendar=Calendar.getInstance();
            calendar.setTimeInMillis(f.lastModified());
            list.add(new ReportFile(f.getName(),calendar.getTime()));
        }
        Collections.sort(list, new Comparator<ReportFile>(){
            @Override
            public int compare(ReportFile f1, ReportFile f2) {
                return f2.getUpdateDate().compareTo(f1.getUpdateDate());
            }
        });
        return list;
    }
    
    /**
     * @return 返回存储器名称
     */
    @Override
    public String getName() {
        return "默认存储位置";
    }
     
    /**
     * 保存报表文件
     * @param file 报表名称
     * @param content 报表的XML内容
     */
    @Override
    public void saveReport(String file,String content) {
        if(file.startsWith(prefix)){
            file=file.substring(prefix.length(),file.length());
        }
        String fullPath=fileStoreDir+"/"+file;
        FileOutputStream outStream=null;
        try{
            outStream=new FileOutputStream(new File(fullPath));
            IOUtils.write(content, outStream,"utf-8");
        }catch(Exception ex){
            throw new ReportException(ex);
        }finally{
            if(outStream!=null){
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } 
    }
    
    /**
     * @return 返回是否禁用
     */
    @Override
    public boolean disabled() {
        return disabled;
    }
     
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    	//没有指定 保存路径
    	if("".equals(fileStoreDir) || fileStoreDir == null){
    		return;
    	}
    	//判断系统服务器是否存在该盘符和盘符剩余空间大小是否足够
    	if(diskSpace > new GetDriverMessage().oneDriverFreeSpace(fileStoreDir.substring(0,1))){
    		System.err.println(fileStoreDir.substring(0,1)+"盘 剩余空间:"+ 
    	                       new GetDriverMessage().formetfilesize(new GetDriverMessage().oneDriverFreeSpace(fileStoreDir.substring(0,1)))+
    	                       " 小于期望空间:" + new GetDriverMessage().formetfilesize(diskSpace)+
    	                       " 请计划备份报表模板！");
    	}
    	
        File file=new File(fileStoreDir);
        if(!file.exists()){
        	file.mkdirs();//创建多层目录
        }
        
        //下面代码是将保存路径指定到 tomcat下面  如果用这个代码块 fileStoreDir的值就是一个文件夹名字
        /*if(applicationContext instanceof WebApplicationContext){
            WebApplicationContext context=(WebApplicationContext)applicationContext;
            ServletContext servletContext=context.getServletContext();
            String basePath=servletContext.getRealPath("/");
            fileStoreDir=basePath+fileStoreDir;
            file=new File(fileStoreDir);
            if(!file.exists()){
                file.mkdirs();//创建多层目录
            }
        }*/
    }
    
    /**
     * @return 返回报表文件名前缀
     */
    @Override
    public String getPrefix() {
        return prefix;
    }
    
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
     
    public void setFileStoreDir(String fileStoreDir) {
        this.fileStoreDir = fileStoreDir;
    }
    
	public long getDiskSpace() {
		return diskSpace;
	}
	
	/**
	 * spring 传进来的是 string数字
	 * @param diskSpace
	 */
	public void setDiskSpace(String diskSpace) {
		this.diskSpace = Long.valueOf(diskSpace).longValue();
	}
}
