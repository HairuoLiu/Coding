import java.util.*;

public class Amazon{

	public static void main(String[] args) {
		
	}

	//基本必考 callback
	

	//第一轮：定义buddy system为一棵complete binary tree。一个node可能为0也可能为1. 
	//它的 value为1，当且仅当它所有的child的value均为1.
	//实现下列的method
	//	1' clearBit(int offset, int len);
	//	2' setBit(int offset, int len);
	
	class buddySystem{
		
		this.size = 

		public buddySystem(){

		}
		public clearBit(){
			
		}
		public setBit(){

		}
	}


	//第二轮：设计一个task dispatching system，里面有一个task queue和两个function。
	//1: trigger，这个function运行并清空task queue中所有的tasks。
	//2: addTask，在trigger之前把task加入task queue，在trigger之后直接运行task
	
	class Task{
		public Task(){

		}
		public void start(){

		}
	}

	class TaskDispatchingSystem{
		private volatile boolean striggering;
		private Lock lock = new ReentrantLock();
		public TaskDispatchingSystem(){
			this.TaskQ = new LinkedList<>();
		}

		//用synchronized	  或者 用lock
		public void addTask(Task nTask){
			lock.lock();
			try{
				if(Istriggering){
					task.start();
				}else{
					TaskQ.offer(nTask);
				}
			}finally{
				lock.unlock();
			}
		}

		public void trigger(){
			try {
    			istriggering = true;
    			while(!queue.isEmpty()){
					TaskQ.poll().start();
				}
  			} finally {
    			lock.unlock();
  			}
		}

	}


	//产生一个圆上的所有坐标。不用sqrt, sin, cos等内建函数。
	//提示：所有的点都是整点。首先我们可以利用对称性把圆分成8块，先画出0-45度角内的点
	//然后映射之。对于其中0-45度角中的点，当X＋1时，Y的值或者不变或者－1，然后放入圆方程中看哪一个是对的


	//设计一个Map<Integer, Integer>，满足下面的复杂度。
	//add: O(1)  deletion: O(1)  lookup: O(1)  clear:O(1)  iterate: O(number of elements)
	//DLL + HashMap (lazy deletion)
	//

}