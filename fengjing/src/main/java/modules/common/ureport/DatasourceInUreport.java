package modules.common.ureport;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.bstek.ureport.definition.datasource.BuildinDatasource;

/**
 * 内置数据源
 * @author O_O
 *
 */
public class DatasourceInUreport implements BuildinDatasource {
	
	private DataSource dataSource;

	@Override
	public Connection getConnection() {
		if(null != dataSource){
			try {
				return dataSource.getConnection();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	@Override
	public String name() {
		return "内置数据源";
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}
