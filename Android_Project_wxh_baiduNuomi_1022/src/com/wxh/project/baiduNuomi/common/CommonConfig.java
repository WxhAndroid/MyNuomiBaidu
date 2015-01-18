package com.wxh.project.baiduNuomi.common;

public class CommonConfig {
	public static final int TIMEOUT = 5000;
	//分类接口
	public static final String URL_CATAGORY = "http://app.nuomi.com/naserver/home/homepage?logpage=Home&cityid=300210000&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Nexus+S+-+4.1.1+-+API+16+-+480x800&channel=1006900a&v=5.0.2&os=SDK16&location=0&cuid=477AD5CF1A3CC8B6073AD245387DCAA7000000000000000&uuid=ffffffff-bcbf-43b9-9c1c-96300033c587&timestamp=1410866512822&swidth=480&sheight=728&net=wifi&sign=d19662517af1fc33c387f9aa1b6344d1";
	public static final String URL_CATAGORY_BY_CITY_ID = "http://app.nuomi.com/naserver/home/homepage?logpage=Home&cityid=%s&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Nexus+S+-+4.1.1+-+API+16+-+480x800&channel=1006900a&v=5.0.2&os=SDK16&location=0&cuid=477AD5CF1A3CC8B6073AD245387DCAA7000000000000000&uuid=ffffffff-bcbf-43b9-9c1c-96300033c587&timestamp=1410866512822&swidth=480&sheight=728&net=wifi&sign=d19662517af1fc33c387f9aa1b6344d1";
	//猜你喜欢接口
	public static final String URL_LIKE = "http://app.nuomi.com/naserver/search/likeitem?logpage=Home&cityid=300210000&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Nexus+S+-+4.1.1+-+API+16+-+480x800&channel=1006900a&v=5.0.2&os=SDK16&location=0&cuid=477AD5CF1A3CC8B6073AD245387DCAA7000000000000000&uuid=ffffffff-bcbf-43b9-9c1c-96300033c587&timestamp=1410866512845&swidth=480&sheight=728&net=wifi&sign=d6dc9a24e2a74a0750443bbc19acfe1a";
	public static final String URL_LIKE_BY_CITY_ID = "http://app.nuomi.com/naserver/search/likeitem?logpage=Home&cityid=%s&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Nexus+S+-+4.1.1+-+API+16+-+480x800&channel=1006900a&v=5.0.2&os=SDK16&location=0&cuid=477AD5CF1A3CC8B6073AD245387DCAA7000000000000000&uuid=ffffffff-bcbf-43b9-9c1c-96300033c587&timestamp=1410866512845&swidth=480&sheight=728&net=wifi&sign=d6dc9a24e2a74a0750443bbc19acfe1a";
	//产品详情 item
	public static final String URL_DETAIL ="http://app.nuomi.com/naserver/item/ItemDetailPage?city_id=300210000&logpage=DealDetail&tinyPicHeight=0&tinyPicWidth=204&s=03ee7a98f5817edbaeade39b4a90b8b1&deal_id=%s&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Nexus+S+-+4.1.1+-+API+16+-+480x800&channel=1006900a&v=5.0.2&os=SDK16&cityid=300210000&location=0&cuid=477AD5CF1A3CC8B6073AD245387DCAA7000000000000000&uuid=ffffffff-bcbf-43b9-9c1c-96300033c587&timestamp=1411094014849&swidth=480&sheight=728&net=wifi&sign=3205a5e28024bb516ad6766adcf0807b" ;
//	private static final String URL_DETAIL1 ="http://app.nuomi.com/naserver/item/ItemDetailPage?city_id=300210000&logpage=DealDetail&tinyPicHeight=0&tinyPicWidth=204&s=03ee7a98f5817edbaeade39b4a90b8b1&deal_id=1362816&appid=android&tn=android&terminal_type=android&device=Genymotion+Google+Nexus+S+-+4.1.1+-+API+16+-+480x800&channel=1006900a&v=5.0.2&os=SDK16&cityid=300210000&location=0&cuid=477AD5CF1A3CC8B6073AD245387DCAA7000000000000000&uuid=ffffffff-bcbf-43b9-9c1c-96300033c587&timestamp=1411094014849&swidth=480&sheight=728&net=wifi&sign=3205a5e28024bb516ad6766adcf0807b" ;
	//团购数据库相关
	public static final String SQL_INSERT_TUAN = "INSERT INTO table_tuan(deal_id, image, brand_name, short_title, sale_count, groupon_price, market_price) VALUES(?, ?, ?, ?, ?, ?, ?)";
	
	public static final String SQL_QUERY_TUAN_BY_DEAL_ID = "SELECT * FROM table_tuan WHERE deal_id = ?";
	//城市数据库相关
	public static final String SQL_INSERT_CITY = "INSERT INTO city(city_id, city_url, city_name, short_name) VALUES(?, ?, ?, ?)";
	public static final String SQL_QUERY_CITY = "SELECT * FROM city";
	public static final String SQL_QUERY_CITY_BY_CITY_NAME ="SELECT * FROM city WHERE city_name=?";
	
	public static final String EXTRA_DETAIL_ID = "deal_id";//交易ID
	public static final String SQL_DELETE_TUAN_BY_DEAL_ID = "DELETE FROM table_tuan WHERE deal_id = ?";
}
