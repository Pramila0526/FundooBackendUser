package com.bridgelabz.fundoouser.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.fundoouser.model.User;
/**********************************************************************************************************
 * @author 	:Pramila Tawari 
 * Purpose	:@Repository is a Spring annotation that indicates that the decorated class is a repository.
 * 			 A repository is a mechanism for encapsulating storage, retrieval, and search behavior which 
 * 			 emulates a collection of objec
 *
 *********************************************************************************************************/

@Repository
public interface UserRepository extends JpaRepository<User, Object /*String*/> 
{
	// For finding or recovring the user account by MailId
	User findByEmail(String email);
}