import static org.junit.Assert.*;
import org.junit.Test;

public class junit_test{

    //测试方法必须有@test；
    //该测试方法必须由public void修饰，没有返回值；
    //该方法不带任何参数；
    //新建一个源代码测试文件单独存放测试代码；
    //测试类的包和被测试类的包保持一致；
    //测试方法间互相独立没有任何依赖；
    @Test
    public void testAdd(){
        assertEquals(4, new Quora().(3, 0));
    }
    
    @Test
    public void testSubtract(){
        assertEquals(3, new Quora().subtract(6, 3));
    }
    
    @Test
    public void testMultiply(){
        assertEquals(6, new Quora().multiply(6, 1));
    }
    
    @Test
    public void testDivision(){
        assertEquals(6, new Quora().division(6, 1));
    }

    @Test(timeout=2000)//单位是毫秒
	public void testWhile(){
	    while(true){
	        System.out.println("run forever");
	    }
	}
}