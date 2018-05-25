package com.wzy.shiro.service.impl;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.wzy.shiro.dao.entity.User;

/**
 * �û����������
 * 
 * @author Wen
 *
 */
@Service
public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	@Value("${password.algorithmName}") // md5
	private String algorithmName;

	@Value("${password.hashIterations}") // 2
	private int hashIterations;

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	//���� �����
	public void encryptPassword(User user){
		 //�����û����ܵ���
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		//����
		String encryptPassword=new SimpleHash(
				algorithmName,
				user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt())
				,hashIterations).toHex();
		user.setPassword(encryptPassword);
	}
}
