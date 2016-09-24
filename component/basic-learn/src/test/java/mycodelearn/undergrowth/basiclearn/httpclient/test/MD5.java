package mycodelearn.undergrowth.basiclearn.httpclient.test;

public class MD5 {

	public MD5() {
		state = new long[4];
		count = new long[2];
		buffer = new byte[64];
		digest = new byte[16];
		md5Init();
	}

	private void Decode(long al[], byte abyte0[], int i) {
		int j = 0;
		int k = 0;
		do {
			if (k >= i)
				return;
			al[j] = b2iu(abyte0[k]) | b2iu(abyte0[k + 1]) << 8 | b2iu(abyte0[k + 2]) << 16 | b2iu(abyte0[k + 3]) << 24;
			j++;
			k += 4;
		} while (true);
	}

	private void Encode(byte abyte0[], long al[], int i) {
		int j = 0;
		int k = 0;
		do {
			if (k >= i)
				return;
			abyte0[k] = (byte) (int) (255L & al[j]);
			abyte0[k + 1] = (byte) (int) (255L & al[j] >>> 8);
			abyte0[k + 2] = (byte) (int) (255L & al[j] >>> 16);
			abyte0[k + 3] = (byte) (int) (255L & al[j] >>> 24);
			j++;
			k += 4;
		} while (true);
	}

	private long F(long l, long l1, long l2) {
		return l & l1 | l2 & (-1L ^ l);
	}

	private long FF(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
		long l7 = l + (l6 + (l4 + F(l1, l2, l3)));
		return l1 + (long) ((int) l7 << (int) l5 | (int) l7 >>> (int) (32L - l5));
	}

	private long G(long l, long l1, long l2) {
		return l & l2 | l1 & (-1L ^ l2);
	}

	private long GG(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
		long l7 = l + (l6 + (l4 + G(l1, l2, l3)));
		return l1 + (long) ((int) l7 << (int) l5 | (int) l7 >>> (int) (32L - l5));
	}

	private long H(long l, long l1, long l2) {
		return l2 ^ (l ^ l1);
	}

	private long HH(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
		long l7 = l + (l6 + (l4 + H(l1, l2, l3)));
		return l1 + (long) ((int) l7 << (int) l5 | (int) l7 >>> (int) (32L - l5));
	}

	private long I(long l, long l1, long l2) {
		return l1 ^ (l | -1L ^ l2);
	}

	private long II(long l, long l1, long l2, long l3, long l4, long l5, long l6) {
		long l7 = l + (l6 + (l4 + I(l1, l2, l3)));
		return l1 + (long) ((int) l7 << (int) l5 | (int) l7 >>> (int) (32L - l5));
	}

	public static long b2iu(byte byte0) {
		if (byte0 < 0)
			byte0 &= 0xff;
		return (long) byte0;
	}

	public static String byteHEX(byte byte0) {
		char ac[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char ac1[] = new char[2];
		ac1[0] = ac[0xf & byte0 >>> 4];
		ac1[1] = ac[byte0 & 0xf];
		return new String(ac1);
	}

	private void md5Final() {
		byte abyte0[] = new byte[8];
		Encode(abyte0, count, 8);
		int i = 0x3f & (int) (count[0] >>> 3);
		int j;
		if (i < 56)
			j = 56 - i;
		else
			j = 120 - i;
		md5Update(PADDING, j);
		md5Update(abyte0, 8);
		Encode(digest, state, 16);
	}

	private void md5Init() {
		count[0] = 0L;
		count[1] = 0L;
		state[0] = 0x67452301L;
		state[1] = 0xefcdab89L;
		state[2] = 0x98badcfeL;
		state[3] = 0x10325476L;
	}

	private void md5Memcpy(byte abyte0[], byte abyte1[], int i, int j, int k) {
		int l = 0;
		do {
			if (l >= k)
				return;
			abyte0[i + l] = abyte1[j + l];
			l++;
		} while (true);
	}

	private void md5Transform(byte abyte0[]) {
		long l = state[0];
		long l1 = state[1];
		long l2 = state[2];
		long l3 = state[3];
		long al[] = new long[16];
		Decode(al, abyte0, 64);
		long l4 = FF(l, l1, l2, l3, al[0], 7L, 0xd76aa478L);
		long l5 = FF(l3, l4, l1, l2, al[1], 12L, 0xe8c7b756L);
		long l6 = FF(l2, l5, l4, l1, al[2], 17L, 0x242070dbL);
		long l7 = FF(l1, l6, l5, l4, al[3], 22L, 0xc1bdceeeL);
		long l8 = FF(l4, l7, l6, l5, al[4], 7L, 0xf57c0fafL);
		long l9 = FF(l5, l8, l7, l6, al[5], 12L, 0x4787c62aL);
		long l10 = FF(l6, l9, l8, l7, al[6], 17L, 0xa8304613L);
		long l11 = FF(l7, l10, l9, l8, al[7], 22L, 0xfd469501L);
		long l12 = FF(l8, l11, l10, l9, al[8], 7L, 0x698098d8L);
		long l13 = FF(l9, l12, l11, l10, al[9], 12L, 0x8b44f7afL);
		long l14 = FF(l10, l13, l12, l11, al[10], 17L, 0xffff5bb1L);
		long l15 = FF(l11, l14, l13, l12, al[11], 22L, 0x895cd7beL);
		long l16 = FF(l12, l15, l14, l13, al[12], 7L, 0x6b901122L);
		long l17 = FF(l13, l16, l15, l14, al[13], 12L, 0xfd987193L);
		long l18 = FF(l14, l17, l16, l15, al[14], 17L, 0xa679438eL);
		long l19 = FF(l15, l18, l17, l16, al[15], 22L, 0x49b40821L);
		long l20 = GG(l16, l19, l18, l17, al[1], 5L, 0xf61e2562L);
		long l21 = GG(l17, l20, l19, l18, al[6], 9L, 0xc040b340L);
		long l22 = GG(l18, l21, l20, l19, al[11], 14L, 0x265e5a51L);
		long l23 = GG(l19, l22, l21, l20, al[0], 20L, 0xe9b6c7aaL);
		long l24 = GG(l20, l23, l22, l21, al[5], 5L, 0xd62f105dL);
		long l25 = GG(l21, l24, l23, l22, al[10], 9L, 0x2441453L);
		long l26 = GG(l22, l25, l24, l23, al[15], 14L, 0xd8a1e681L);
		long l27 = GG(l23, l26, l25, l24, al[4], 20L, 0xe7d3fbc8L);
		long l28 = GG(l24, l27, l26, l25, al[9], 5L, 0x21e1cde6L);
		long l29 = GG(l25, l28, l27, l26, al[14], 9L, 0xc33707d6L);
		long l30 = GG(l26, l29, l28, l27, al[3], 14L, 0xf4d50d87L);
		long l31 = GG(l27, l30, l29, l28, al[8], 20L, 0x455a14edL);
		long l32 = GG(l28, l31, l30, l29, al[13], 5L, 0xa9e3e905L);
		long l33 = GG(l29, l32, l31, l30, al[2], 9L, 0xfcefa3f8L);
		long l34 = GG(l30, l33, l32, l31, al[7], 14L, 0x676f02d9L);
		long l35 = GG(l31, l34, l33, l32, al[12], 20L, 0x8d2a4c8aL);
		long l36 = HH(l32, l35, l34, l33, al[5], 4L, 0xfffa3942L);
		long l37 = HH(l33, l36, l35, l34, al[8], 11L, 0x8771f681L);
		long l38 = HH(l34, l37, l36, l35, al[11], 16L, 0x6d9d6122L);
		long l39 = HH(l35, l38, l37, l36, al[14], 23L, 0xfde5380cL);
		long l40 = HH(l36, l39, l38, l37, al[1], 4L, 0xa4beea44L);
		long l41 = HH(l37, l40, l39, l38, al[4], 11L, 0x4bdecfa9L);
		long l42 = HH(l38, l41, l40, l39, al[7], 16L, 0xf6bb4b60L);
		long l43 = HH(l39, l42, l41, l40, al[10], 23L, 0xbebfbc70L);
		long l44 = HH(l40, l43, l42, l41, al[13], 4L, 0x289b7ec6L);
		long l45 = HH(l41, l44, l43, l42, al[0], 11L, 0xeaa127faL);
		long l46 = HH(l42, l45, l44, l43, al[3], 16L, 0xd4ef3085L);
		long l47 = HH(l43, l46, l45, l44, al[6], 23L, 0x4881d05L);
		long l48 = HH(l44, l47, l46, l45, al[9], 4L, 0xd9d4d039L);
		long l49 = HH(l45, l48, l47, l46, al[12], 11L, 0xe6db99e5L);
		long l50 = HH(l46, l49, l48, l47, al[15], 16L, 0x1fa27cf8L);
		long l51 = HH(l47, l50, l49, l48, al[2], 23L, 0xc4ac5665L);
		long l52 = II(l48, l51, l50, l49, al[0], 6L, 0xf4292244L);
		long l53 = II(l49, l52, l51, l50, al[7], 10L, 0x432aff97L);
		long l54 = II(l50, l53, l52, l51, al[14], 15L, 0xab9423a7L);
		long l55 = II(l51, l54, l53, l52, al[5], 21L, 0xfc93a039L);
		long l56 = II(l52, l55, l54, l53, al[12], 6L, 0x655b59c3L);
		long l57 = II(l53, l56, l55, l54, al[3], 10L, 0x8f0ccc92L);
		long l58 = II(l54, l57, l56, l55, al[10], 15L, 0xffeff47dL);
		long l59 = II(l55, l58, l57, l56, al[1], 21L, 0x85845dd1L);
		long l60 = II(l56, l59, l58, l57, al[8], 6L, 0x6fa87e4fL);
		long l61 = II(l57, l60, l59, l58, al[15], 10L, 0xfe2ce6e0L);
		long l62 = II(l58, l61, l60, l59, al[6], 15L, 0xa3014314L);
		long l63 = II(l59, l62, l61, l60, al[13], 21L, 0x4e0811a1L);
		long l64 = II(l60, l63, l62, l61, al[4], 6L, 0xf7537e82L);
		long l65 = II(l61, l64, l63, l62, al[11], 10L, 0xbd3af235L);
		long l66 = II(l62, l65, l64, l63, al[2], 15L, 0x2ad7d2bbL);
		long l67 = II(l63, l66, l65, l64, al[9], 21L, 0xeb86d391L);
		long al1[] = state;
		al1[0] = l64 + al1[0];
		long al2[] = state;
		al2[1] = l67 + al2[1];
		long al3[] = state;
		al3[2] = l66 + al3[2];
		long al4[] = state;
		al4[3] = l65 + al4[3];
	}

	private void md5Update(byte[] paramArrayOfByte, int paramInt) {
		byte[] arrayOfByte1 = new byte[64];
		int i = 0x3F & (int) (this.count[0] >>> 3);
		long[] arrayOfLong1 = this.count;
		long l = arrayOfLong1[0] + (paramInt << 3);
		arrayOfLong1[0] = l;
		if (l < paramInt << 3) {
			long[] arrayOfLong3 = this.count;
			arrayOfLong3[1] = (1L + arrayOfLong3[1]);
		}
		long[] arrayOfLong2 = this.count;
		arrayOfLong2[1] += (paramInt >>> 29);
		int j = 64 - i;
		int k = 0;
		if (paramInt >= j) {
			md5Memcpy(this.buffer, paramArrayOfByte, i, 0, j);
			md5Transform(this.buffer);
			k = j;
			if (k + 63 >= paramInt)
				i = 0;
		}
		while (true) {
			byte[] arrayOfByte2 = this.buffer;
			int m = paramInt - k;
			md5Memcpy(arrayOfByte2, paramArrayOfByte, i, k, m);
			return;
		}
	}

	public String getMD5ofStr(String s) {
		md5Init();
		md5Update(s.getBytes(), s.length());
		md5Final();
		digestHexStr = "";
		int i = 0;
		do {
			if (i >= 16)
				return digestHexStr;
			digestHexStr = (new StringBuilder(String.valueOf(digestHexStr))).append(byteHEX(digest[i])).toString();
			i++;
		} while (true);
	}

	static final byte PADDING[];
	static final int S11 = 7;
	static final int S12 = 12;
	static final int S13 = 17;
	static final int S14 = 22;
	static final int S21 = 5;
	static final int S22 = 9;
	static final int S23 = 14;
	static final int S24 = 20;
	static final int S31 = 4;
	static final int S32 = 11;
	static final int S33 = 16;
	static final int S34 = 23;
	static final int S41 = 6;
	static final int S42 = 10;
	static final int S43 = 15;
	static final int S44 = 21;
	private byte buffer[];
	private long count[];
	private byte digest[];
	public String digestHexStr;
	private long state[];

	static {
		byte abyte0[] = new byte[64];
		abyte0[0] = -128;
		PADDING = abyte0;
	}
}
