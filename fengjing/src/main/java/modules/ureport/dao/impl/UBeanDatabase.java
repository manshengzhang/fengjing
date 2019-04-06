package modules.ureport.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modules.common.ureport.BeanDatabase;

/**
 * Bean数据源 针对业务的
 * 实现BeanDatabases 加载自己的模板数据
 * @author O_O
 */
public class UBeanDatabase extends BeanDatabase{
	/**
	 * Bean需要序列化的
	 */
	private static final long serialVersionUID = 3141702018531433944L;

	@Override
	public List<Map<String, String>> loadReportData(String dsName, String datasetName, Map<String, Object> pts) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		for(int i = 1; i < 300; i++){
			map = new HashMap<String, String>();
			map.put("ORD",(i-1)+"");
			map.put("GRP", (i/16 + 1)+"");
			map.put("AAA", "A_"+(i/9 + 1)+"_A");
			map.put("BBB", "B_"+(i/6 + 1)+"_B");
			map.put("CCC", "C_"+(i/3 + 1)+"_C");
			map.put("DDD", "D_"+i+"_D");
			map.put("EEE", i+"");
			list.add(map);
		}
		//new java.lang.reflect.Method().getParameters();
		return list;
	}

	@Override
	public List<Map<String, Object>> buildReport(String dsName, String datasetName, Map<String, Object> pts) {
		return null;
	}
}
