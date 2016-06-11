package mylearncode.undergrowth.algorithm;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class MinStack {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinStack1 stack1=new MinStack1();
		/*for(int i=1;i<2999999;i++)
			stack1.push(i);*/
		stack1.push(2);
		stack1.push(0);
		stack1.push(3);
		stack1.push(0);
		System.out.println(stack1.getMin());
		stack1.pop();
		System.out.println(stack1.getMin());
		stack1.pop();
		System.out.println(stack1.getMin());
		stack1.pop();
		System.out.println(stack1.getMin());
		System.out.println(stack1.top());
		
	}

	/**
	 * Design a stack that supports push, pop, top, and retrieving the minimum
	 * element in constant time. • push(x) -- Push element x onto stack. • pop()
	 * -- Removes the element on top of the stack. • top() -- Get the top
	 * element. • getMin() -- Retrieve the minimum element in the stack.
	 * 
	 * 获取最小值 使用查找法--时间复杂度就是o(n)
	 *   替换方法--维护最小堆栈
	 *   当push时 比较push的元素和最小堆栈中的栈顶元素 如果小于最小堆栈的栈顶元素 即入最小堆栈
	 *   当pop时 验证pop出的元素 是否是最小堆栈的栈顶元素 如果是 则移出最小堆栈的栈顶元素
	 *   当getMin时 从最小堆栈取元素 即可 
	 * 
	 * @author u1
	 * 
	 */
	static class MinStack1 {
		
		Stack<Integer> stack=new Stack<Integer>();
		Stack<Integer> minStack=new Stack<Integer>();
		
		public void push(int x) {
			//所有元素正常入栈
			stack.push(x);
			//是否需要添加到最小堆栈中
			if(minStack.isEmpty()||minStack.peek()>=x){
				minStack.push(x);
			}
		}

		public void pop() {
			int elem=stack.pop();
			if(!minStack.isEmpty()&&elem==minStack.peek()){
				minStack.pop();
			}
		}

		public int top() {
			return stack.peek();
		}

		public int getMin() {
			/*int min=stack.peek();
			for (Iterator<Integer> iterator = stack.iterator(); iterator.hasNext();) {
				Integer num = (Integer) iterator.next();
				if(num<min) min=num;
			}
			return min;*/
			if(!minStack.isEmpty()) return minStack.peek();
			
			return 0;
		}
	}
}
