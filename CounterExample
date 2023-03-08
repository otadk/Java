public class CounterExample {
	public static class MyCounter {
		private int count = 0;
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
	}
	public static class Counter extends Thread {
		private MyCounter count;
		private int n;
		private Thread t;
		private boolean muti;
		public Counter(MyCounter count,int n,Thread t, boolean muti) {
			this.count = count;
			this.n = n;
			this.t = t;
			this.muti = muti;
		}
		public void run() {
			if (muti) {
				try {
					if (t != null) {
						t.join();
					}
				} catch(InterruptedException e) {}
			}
			for(int i=0;i<n;i++) {
				int temp = count.getCount();
				temp++;
				count.setCount(temp);
			}
		}
	}
	private static void myDo(int a, int b, boolean muti) {
		long startTime = System.currentTimeMillis();
		MyCounter count = new MyCounter();
		int nCounters = a;
		Counter[] c = new Counter[nCounters];
		c[0] = new Counter(count,b,null, muti);
		c[0].start();
		for(int i=1;i<nCounters;i++) {
			if (!muti) {
				try {
					if (c[i - 1] != null) {
						c[i - 1].join();
					}
				} catch(InterruptedException e) {}
			}
			c[i] = new Counter(count,b, c[i - 1], muti);
			c[i].start();
		}
		try {
			if (c[nCounters - 1] != null) {
				c[nCounters - 1].join();
			}
		} catch(InterruptedException e) {}

		System.out.println(count.getCount());

		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		if (muti) {
			System.out.print("多线程");
		} else {
			System.out.print("单线程");
		}

		System.out.println("程序运行时间：" + elapsedTime + " 毫秒");
	}

	public static void main(String[] args) {
		int a = 100;
		int b = 1000;
		myDo(a,b,false);
		myDo(a,b,true);
	}
}
