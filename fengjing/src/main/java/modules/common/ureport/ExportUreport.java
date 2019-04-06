package modules.common.ureport;

import java.util.Map;

import com.bstek.ureport.export.ExportConfigure;
import com.bstek.ureport.export.ExportManager;
import com.bstek.ureport.export.html.HtmlReport;

/**
 * 报表导出 暂时不用
 * @author O_O
 *
 */
public class ExportUreport implements ExportManager {

	@Override
	public void exportExcel(ExportConfigure arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportExcel97(ExportConfigure arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportExcel97WithPaging(ExportConfigure arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportExcel97WithPagingSheet(ExportConfigure arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportExcelWithPaging(ExportConfigure arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportExcelWithPagingSheet(ExportConfigure arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HtmlReport exportHtml(String arg0, String arg1,
			Map<String, Object> arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HtmlReport exportHtml(String arg0, String arg1,
			Map<String, Object> arg2, int arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exportPdf(ExportConfigure arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportWord(ExportConfigure arg0) {
		// TODO Auto-generated method stub
		
	}

}
