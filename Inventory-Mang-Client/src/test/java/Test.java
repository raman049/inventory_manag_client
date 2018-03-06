import com.im.client.entity.VendorVO;
import com.in.cleint.cleintClass.VendorVOClient;

public class Test {

	public static void main(String args) throws Exception {
		VendorVO vo = new VendorVO();
		VendorVOClient client = new VendorVOClient();
		vo = client.findByID(new Long(1096));
		System.out.println(vo);
		
	}
}
