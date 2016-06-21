package mylearncode.undergrowth.algorithm;

import java.util.Arrays;

/**
 * 快速排序----一趟排序,分成两份
 * 		通过一趟排序 将数据分为两部分 其中一部分的所有数据比另一份的所有数据都小
 * 然后对这两份数据 进行递归操作 使整个数据变成有序数据
 * @author u1
 *
 */
public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] nums=new int[100];
		for (int i = 0; i < nums.length; i++) {
			nums[i]=(int) (Math.random()*100);
		}
		System.out.println(Arrays.toString(nums));
		QuickSort quickSort=new QuickSort();
		quickSort.quickSort(nums, 0, nums.length-1);
		System.out.println(Arrays.toString(nums));
	}

	public void quickSort(int[] nums,int left,int right){
		int pivot;
		if(left<right){//当左边序号小于右边序号时 接着递归
			//将数据分成两份
			pivot=partition(nums,left,right);
			//将左边的进行快速排序
			quickSort(nums, left, pivot-1);
			//将右边的进行快速排序
			quickSort(nums, pivot+1, right);
		}
	}

	/**
	 * 进行数据交换
	 * 选定一个中心值 中心值的数据不变 将其他数据与中心值进行比较 
	 * 大于等于中心值的放在中心值的右边
	 * 小于等于中心值的放在中心值的左边
	 * @param nums
	 * @param left
	 * @param right
	 * @return
	 */
	private int partition(int[] nums, int left, int right) {
		// TODO Auto-generated method stub
		//选取中心点的值
		int pivotValue=nums[left];
		while(left<right){ //将数组中的数据和中心值进行比较
			while(left<right&&nums[right]>=pivotValue) right--;
			//当出现中心点的值大于右边的值情况 进行交换 此时右边点的值为空 等待中心点左边的值进行填充
			nums[left]=nums[right];
			while(left<right&&nums[left]<=pivotValue) left++;
			//当出现中心点的值小于左边的情况 进行交互 此时左边点的值为空 等待中心点右边的值进行填充
			nums[right]=nums[left];
		}
		//将中心点的值 放入中间点
		nums[left]=pivotValue;
		//nums[right]=pivotValue;
		return left;
	}
	
}
