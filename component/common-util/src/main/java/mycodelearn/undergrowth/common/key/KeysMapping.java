package mycodelearn.undergrowth.common.key;


/**
* Description: 
* (用于系统的键值映射)
* 	例如Controller的入口、出口
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月4日
* @version 1.0.0
*/
public interface KeysMapping {

	//用于jquery测试的配置项
	public static final String JQUERY_INDEX="/jquery";
	public static final String JQUERY_INDEX_VIEW="/jquery/jqueryTest";
	public static final String JQUERY_AJAX="/jqueryAjax";
	public static final String JQUERY_AJAX_VIEW="/jquery/jqueryAjaxTest";
	//jqueryAuthen
	public static final String JQUERY_AUTHEN="/jqueryAuthen";
	public static final String JQUERY_AUTHEN_VIEW="/jquery/jqueryAuthenTest";
	//效果
	public static final String JQUERY_EFFECT="/jqueryEffect";
	public static final String JQUERY_EFFECT_VIEW="/jquery/jqueryEffectTest";
	//事件
	public static final String JQUERY_EVENT="/jqueryEvent";
	public static final String JQUERY_EVENT_VIEW="/jquery/jqueryEventTest";
	//模型
	public static final String JQUERY_MODULE="/jqueryModule";
	public static final String JQUERY_MODULE_VIEW="/jquery/jqueryModuleTest";
	//查找
	public static final String JQUERY_SEARCH="/jquerySearch";
	public static final String JQUERY_SEARCH_VIEW="/jquery/jquerySearchTest";
	
	//测试rest配置项
	public static final String REST_GREETING="/greet";
	public static final String REST_GREETING_VIEW="/greet/greetTest";
	
	
}
