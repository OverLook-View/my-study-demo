import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author : sy
 * @date: 创建时间：2017年7月29日 下午2:41:49
 * @version: 1.0
 * @Description:
 */

public class MyTest {

	public static void main(String[] args) throws IOException {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("gmat", true);
		map.put("姓名", "");
		map.put("性别", "");
		map.put("手机", "");
		map.put("邮箱", "");
		map.put("单位", "");
		map.put("身份", "");
		map.put("开具发票形式", "学校统一开票");
		map.put("酒店名", "");
		map.put("房间类型", "");
		map.put("车号信息", "");
		map.put("到站时间", "");
		map.put("目的地", "");
		map.put("subMeetingInfoList", "");
		map.put("备注", "");
		// 审核
		map.put("isPend", "");
		// 报名id
		map.put("signupId", "");
		// 拒绝理由
		map.put("rejectReason", "");
		for (Entry<String, Object> en : map.entrySet()) {
			System.out.println(en.getKey() + ":" + en.getValue());
		}
		
	}
}
