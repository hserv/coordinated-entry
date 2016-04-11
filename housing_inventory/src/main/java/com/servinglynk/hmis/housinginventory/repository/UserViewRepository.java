package com.servinglynk.hmis.housinginventory.repository;


import org.springframework.data.jpa.repository.*;

import com.servinglynk.hmis.housinginventory.domain.UserView;

import java.util.List;

/**
 * Spring Data JPA repository for the UserView entity.
 */
public interface UserViewRepository extends JpaRepository<UserView,Long> {

}
