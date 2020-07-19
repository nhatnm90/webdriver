import org.testng.annotations.*;

public class HelloWorld {
  
	// Anotation là những cái có chữ @ và đc đặt ngay trên method.
	// Vd: @BeforeTest, @Test, @AfterMethod... 
	
	@BeforeTest
	public void init() {
		// Chỉ chạy 1 lần duy nhất khi bắt đầu chạy test chương trình 
		System.out.println("1.  Before Test");
		
		// Để tạo driver test, khởi tạo những thứ chạy 1 lần cho toàn chương trình test 
	}
	
	@BeforeMethod
	void beforeMethod() {
		// Chạy trước mỗi test case, trước khi chạy 1 test case sẽ chạy 1 lần,
		// Có bao nhiêu test case sẽ đc chạy bấy nhiêu lần 
		System.out.println("2.  Before Method");
		
		// Chuẩn bị sẵn môi trường sạch sẽ trước khi chạy 1 test case nào đó 
	}

	
	@Test
	void test() {
		// Tất cả những hàm có anotation là @Test đều sẽ đc chạy 1 lần
		// Chạy trước nó là BeforeTest và sau nó là AfterTest 
		System.out.println("3.0 Test Case here");
		
		// Hiện thực hoá test case 
	}
	
	@Test
	void test1() {
		System.out.println("3.1 Test Case here");
	}
	

	@AfterMethod
	void afterMethod() {
		// Chạy sau khi chạy hết test case
		// Tương tự như BeforeMethod có bao nhiêu test case sẽ chạy bấy nhiêu lần 
		System.out.println("4.  After Method");
		
		// Dọn dẹp môi trường sau khi 1 test case đc chạy xong
		// Thường sẽ xoá cache, refresh page ...
		// Những thao tác ở phần này khá tương tự như beforeMethod
		// Cần cân nhắc để tối ưu kịch bản test 1 cách tốt nhất 
	}
	
	
	@AfterTest
	public void close() {
		// Chạy 1 lần duy nhất sau khi chạy hết tất cả mọi test case. 
		// Đối nghịch với BeforeTest
		System.out.println("5.  After Test");
		
		// Dọn dẹp phần cuối cùng sau khi chạy hết tất cả test case
		// Thường dùng để giải phóng chương trình, tắt website ... 
	}
	
	// BeforeTest > BeforeMethod > Test > AfterMethod > AfterTest

}
