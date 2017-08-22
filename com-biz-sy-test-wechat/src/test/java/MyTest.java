import java.io.File;
import java.io.IOException;

/**
 * @author : sy
 * @date: 创建时间：2017年7月29日 下午2:41:49
 * @version: 1.0
 * @Description:
 */

public class MyTest {

	public static void main(String[] args) throws IOException {
		File file = new File("1.txt");
		System.out.println(file.createNewFile());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.delete());
	}
}
