package com.ischoolbar.programmer.service.admin;

import org.springframework.stereotype.Service;
import com.ischoolbar.programmer.entity.admin.User;
/**
 * user�û�service��
 * @author yangrundong
 *
 */



public interface UserService {
	public User findByUsername(String username);
		

}
