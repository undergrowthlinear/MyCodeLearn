package mycodelearn.undergrowth.interview.gdca;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Before;
import org.junit.Test;

/**
 * 
* Description: TODO(这里用一句话描述这个类的作用)
* @author <a href="zhangwu@wxchina.coom">Wu.Zhang</a>
* Date 2016年6月13日
* @version  1.0.0
 */
public class GDShuZiZhengShu {

	/**
	 * 什么是数字证书?
	 * A1:数字证书是网络身份证
	 * 什么事PKI系统?
	 * A2：公钥基础设施,通过CA把用户的公钥/基础信息捆绑在一起,在网络上验证用户信息
	 * CA----认证机构,证书的签发机构
	 * 数字证书库----查询自己或者别人的证书
	 * 密钥的备份及恢复----
	 * 证书作废系统----
	 * 应用接口(API)----
	 * 什么是SSL?
	 * A3:SSL是安全套接层
	 * 什么是Https?
	 * A4:应用SSL作为Http应用层子层,端口为443,用于身份验证和加密通讯
	 * 客户端----采用对称加密的主密钥加密内容,再用服务器非对称加密的公钥加密加密后的内容
	 * 服务器端----采用非对称加密的私钥解密,再用主密钥解密
	 * 什么是DES,3DES,AES,MD5,SHA1?
	 * A5:
	 * 非对称加密----使用私钥加密,公钥解密
	 * 	公钥是向对方公开的,使用自己的私钥进行签名,使用对方的公钥进行加密,对方接收到消息后
	 *   使用发送方的公钥验证签名,用自己的私钥进行解密消息
	 *   RSA(1024)----
	 * 对称加密----双方使用相同的密钥加解密,DES(56bit)----3DES----AES
	 * SHA1,MD5是一种加密算法,用于计算一段不可逆的值,验证文件是否被修改
	 * 
	 * 什么是缓存,一般有什么用?
	 * 
	 * 
	 */
	
	String content="测试加解密";
	String password="qwertyuiop";
	
	@Before
	public void Before(){
		System.out.println("content:"+content+",password:"+password);
	}
	
	@Test
	public void testSHA1() {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(content.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			System.out.println(hexString.toString());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSHA() {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA");
			digest.update(content.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			//4ae5e463e0fe6d2c3638d7161112c94727fc8685
			System.out.println(hexString.toString());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMD5() {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(content.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < md.length; i++) {
				String shaHex = Integer.toHexString(md[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			System.out.println(hexString.toString());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	//@Test
	public byte[] testEncryptAES() {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			//[B@5056dfcb
			System.out.println(result); // 加密
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testDecryptAES() throws UnsupportedEncodingException {
		try {
			byte[] encodeContent=testEncryptAES();
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] originResult = cipher.doFinal(encodeContent);
			System.out.println(new String(originResult, "utf-8")); // 解密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String key) {

		return "";
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String key) {

		return "";
	}
	
	/**
	 * 插入排序
	 */
	@Test
	public void insertTest(){
		List<Integer> list=new ArrayList<>();
		list.addAll(Arrays.asList(12,6,18,2,20));
		System.out.println("排序前,"+list);
		insertSort(list);
		System.out.println("排序后,"+list);
		list.clear();
		list.addAll(Arrays.asList(12,6,18,2,20));
		mpSort(list);
		System.out.println("冒泡排序后,"+list);
	}

	/**
	 * 两个数两两比较,将较小的交互到最前面
	 * @param list
	 */
	private void mpSort(List<Integer> list) {
		// TODO Auto-generated method stub
		int tmp=0;
		int num = list.size();
		for (int i = 0; i < num; i++) {
			for (int j = i; j < num; j++) {
				if (list.get(i) > list.get(j)) {
					tmp=list.get(i);
					list.set(i, list.get(j));
					list.set(j, tmp);
				}
			}
			System.out.println(i+"次排序,"+list);
		}
	}

	/**
	 * 插入排序
	 * 将待排序的数据插入到已排序的数组中
	 * @param list
	 */
	private void insertSort(List<Integer> list) {
		// TODO Auto-generated method stub
		int tmp=0,j=0;
		int num=list.size();
		for (int i = 1; i < num; i++) {
			if(list.get(i-1)>list.get(i)){
				//存储要被替换的值
				tmp=list.get(i);
				j=i;
				while(j>0 && list.get(j-1)>tmp){
					list.set(j, list.get(j-1));
					j--;
				}
				list.set(j,tmp);
			}
			System.out.println(i+"次排序,"+list);
		}
	}
}

