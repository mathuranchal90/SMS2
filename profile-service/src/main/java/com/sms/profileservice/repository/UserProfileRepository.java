package com.sms.profileservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sms.profileservice.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
	
	@Query("SELECT COUNT(p.imgUrl) from user_profile p where p.user.id = :userId")
    String getImgByUserId(@Param("userId") Long userId);
	
	@Query("SELECT p FROM user_profile p where p.user.id = :userId ")
	UserProfile getProfileById(Long userId);

}
