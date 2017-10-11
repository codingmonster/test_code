package testtttt;

import net.sf.json.JSONObject;

public class JSONTest {
	
	public void isNull() {
		try {
			JSONObject detail = JSONObject.fromObject("123");
			System.out.println("detail == null : " + (detail == null));
			System.out.println("detail.isNullObject() : " + detail.isNullObject());
			System.out.println("detail.isEmpty() : " + detail.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			JSONObject detail1 = JSONObject.fromObject("{}");
			System.out.println("detail1 == null : " + (detail1 == null));
			System.out.println("detail1.isNullObject() : " + detail1.isNullObject());
			System.out.println("detail1.isEmpty() : " + detail1.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			JSONObject detail2 = JSONObject.fromObject("null");
			System.out.println("detail2 == null : " + (detail2 == null));
			System.out.println("detail2.isNullObject() : " + detail2.isNullObject());
			System.out.println("detail2.isEmpty() : " + detail2.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			JSONObject detail3 = JSONObject.fromObject(null);
			System.out.println("detail3 == null : " + (detail3 == null));
			System.out.println("detail3.isNullObject() : " + detail3.isNullObject());
			System.out.println("detail3.isEmpty() : " + detail3.isEmpty());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JSONTest jt = new JSONTest();
		jt.isNull();
	}
}
