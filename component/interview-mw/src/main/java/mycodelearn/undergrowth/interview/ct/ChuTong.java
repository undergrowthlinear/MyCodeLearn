package mycodelearn.undergrowth.interview.ct;


/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月20日
* @version  1.0.0
 */
public class ChuTong {

	/**
	 * 好处----跨越不同平台/操作系统/不同开发语言
	 * webServices---->远程的函数库
	 * 		自描述/自包含/基于xml http的/
	 * 		三个角色----web服务提供者/web服务请求者/web服务中介者
	 * 		三个动作----发布/查找/绑定	
	 * 三元素:
	 * 1.协议----Soap---->simple object access protocol,唯一一个没有发明新技术的技术
	 * 		基于xml实现消息的描述,http实现消息的传递
	 * 2.描述----wsdl---->web services description language,基于xml的用于描述web服务以及如何访问web服务的语言
	 * 3.uuid---->universal description discovery and integration,通用的描述/发现/整合
	 */
	
	/**
	 *   树--至少有一个数据元素存在不止一个直接前驱或者后继元素,存在唯一一个称为根的数据元素
	 * 	       结点的度--每个结点拥有的子树的个数
	 * 	      树的度--树中所有结点的度的最大值
	 *    树的深度--树的层次
	 * 二叉树--结点数为0或者每个结点最多只有左右两颗子树的树
	 * 		结论：第i层最多拥有2 i-1个结点  可进行论证
	 * 			深度为k的二叉树最多只有2 k  -1个结点  可用等比数列求和进行论证
	 * 			度为0的结点数比度为2的结点数多1  可用结点数和孩子数进行推理
	 *      满二叉树--除去最后一层，其它层次上的结点数都达到最大值 2 i-1
	 *      完全二叉树--每个节点的编号与相同深度的满二叉树--对应，即左子树的深度-右子树的深度等于0或者1
	 *      
	 *      前序遍历：根节点->左子树->右子树
			中序遍历：左子树->根节点->右子树
			后序遍历：左子树->右子树->根节点

	*/
}
