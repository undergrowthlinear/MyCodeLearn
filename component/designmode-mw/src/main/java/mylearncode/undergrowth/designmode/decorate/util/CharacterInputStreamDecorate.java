package mylearncode.undergrowth.designmode.decorate.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * �ַ�������װ����  ���ڶ�������ַ�����ת�� ��������ַ�ȫ��תΪ��д
 * @author Administrator
 *
 */
public class CharacterInputStreamDecorate extends FilterInputStream {

	protected CharacterInputStreamDecorate(InputStream in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		int c=super.read();
		return (c==-1)?c:Character.toUpperCase(c);
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		int result=super.read(b, off, len);
		if(result!=-1){
			for (int i = off; i < off+result; i++) {
				b[i]=(byte) Character.toUpperCase(b[i]);
			}
		}
		return result;
	}
	
	

}
