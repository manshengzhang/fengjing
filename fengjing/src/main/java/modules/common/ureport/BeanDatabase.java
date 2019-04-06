package modules.common.ureport;

import java.util.List;
import java.util.Map;

/**
 * Bean数据源
 * 基类 这样增加了耦合
 * 使用抽象类，管理基本方法，暂时不使用接口
 * 定义抽象方法，让子类去实现
 * @author O_O
 */
public abstract class BeanDatabase implements java.io.Serializable{
	/**
	 * 抽象类 不需要序列化  应为它本身不能实例化，spring不能加载
	 */
	private static final long serialVersionUID = 7586987709990508118L;

	/**
	 * 加载报表数据
	 * @param dsName 数据源名称
	 * @param datasetName 数据集名称
	 * @param parameters 外部传入的参数Map
	 * @return
	 */
	public abstract List<Map<String, String>> loadReportData(String dsName,String datasetName,Map<String,Object> parameters);
	
	/**
	 * 创建报表
	 * @param dsName 数据源名称
	 * @param datasetName 数据集名称
	 * @param parameters 外部传入的参数Map
	 * @return
	 */
	public abstract List<Map<String,Object>> buildReport(String dsName,String datasetName,Map<String,Object> parameters);
}
