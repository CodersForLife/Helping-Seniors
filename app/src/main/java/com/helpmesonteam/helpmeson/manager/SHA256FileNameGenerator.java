package com.helpmesonteam.helpmeson.manager;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.utils.L;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256FileNameGenerator implements FileNameGenerator {

	private static final String HASH_ALGORITHM = "SHA256";
	private static final int RADIX = 10 + 26; // 10 digits + 26 letters

	@Override
	public String generate(String imageUri) {
		byte[] sha256 = getSHA256(imageUri.getBytes());
		BigInteger bi = new BigInteger(sha256).abs();
		return bi.toString(RADIX);
	}

	private byte[] getSHA256(byte[] data) {
		byte[] hash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(data);
			hash = digest.digest();
		} catch (NoSuchAlgorithmException e) {
			L.e(e);
		}
		return hash;
	}

}
