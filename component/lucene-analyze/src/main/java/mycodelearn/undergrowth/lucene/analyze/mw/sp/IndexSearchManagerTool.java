package mycodelearn.undergrowth.lucene.analyze.mw.sp;

/**
 * 
 * Description: TODO(这里用一句话描述这个类的作用)
 * 
 * @author <a href="undergrowth@126.com">undergrowth</a>
 * @date 2016年7月23日
 * @version 1.0.0
 */
public class IndexSearchManagerTool {

	private NRTManagerFileTool nrtManagerFileTool;
	private SearcherManagerFileTool searcherManagerFileTool;

	public IndexSearchManagerTool(String indexPath) {
		nrtManagerFileTool = new NRTManagerFileTool(indexPath);
		searcherManagerFileTool = new SearcherManagerFileTool(nrtManagerFileTool.getSearcherManager());
	}

	public NRTManagerFileTool getNrtManagerFileTool() {
		return nrtManagerFileTool;
	}

	public SearcherManagerFileTool getSearcherManagerFileTool() {
		return searcherManagerFileTool;
	}

}
