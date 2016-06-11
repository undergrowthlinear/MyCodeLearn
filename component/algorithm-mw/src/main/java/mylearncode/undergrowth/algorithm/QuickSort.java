package mylearncode.undergrowth.algorithm;

import java.util.Arrays;

/**
 * ��������
 * 		ͨ��һ������ �����ݷ�Ϊ������ ����һ���ֵ��������ݱ���һ�ݵ��������ݶ�С
 * Ȼ������������� ���еݹ���� ʹ�������ݱ����������
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
		if(left<right){//��������С���ұ����ʱ ���ŵݹ�
			//�����ݷֳ�����
			pivot=partition(nums,left,right);
			//����ߵĽ��п�������
			quickSort(nums, left, pivot-1);
			//���ұߵĽ��п�������
			quickSort(nums, pivot+1, right);
		}
	}

	/**
	 * �������ݽ���
	 * ѡ��һ������ֵ ����ֵ�����ݲ��� ����������������ֵ���бȽ� 
	 * ���ڵ�������ֵ�ķ�������ֵ���ұ�
	 * С�ڵ�������ֵ�ķ�������ֵ�����
	 * @param nums
	 * @param left
	 * @param right
	 * @return
	 */
	private int partition(int[] nums, int left, int right) {
		// TODO Auto-generated method stub
		//ѡȡ���ĵ��ֵ
		int pivotValue=nums[left];
		while(left<right){ //�������е����ݺ�����ֵ���бȽ�
			while(left<right&&nums[right]>=pivotValue) right--;
			//���������ĵ��ֵ�����ұߵ�ֵ��� ���н��� ��ʱ�ұߵ��ֵΪ�� �ȴ����ĵ���ߵ�ֵ�������
			nums[left]=nums[right];
			while(left<right&&nums[left]<=pivotValue) left++;
			//���������ĵ��ֵС����ߵ���� ���н��� ��ʱ��ߵ��ֵΪ�� �ȴ����ĵ��ұߵ�ֵ�������
			nums[right]=nums[left];
		}
		//�����ĵ��ֵ �����м��
		nums[left]=pivotValue;
		//nums[right]=pivotValue;
		return left;
	}
	
}
