
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Create by Liu.3502 on 2018/8/31
 */
class Test {

  @Test
  void test1(){
    boolean s = new Can_I_Win_464().canIWin(10,11,true);
    assertEquals(false,s);
  }


  @Test
  void test2(){
    boolean s = new Can_I_Win_464().canIWin(10,0,true);
    assertEquals(true,s);
  }

  @Test
  void test3(){
    boolean s = new Can_I_Win_464().canIWin(10,11,true);
    assertEquals(false,s);
  }

  @Test
  void test4(){
    boolean s = new Can_I_Win_464().canIWin(10,40,true);
    assertEquals(false,s);
  }
}