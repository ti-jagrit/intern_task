package com.jtim.borrower;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
<<<<<<< HEAD
=======
import jakarta.persistence.GenerationType;
>>>>>>> 1c30e4a (Reinitialized repo after .git folder removal)
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Borrower {

	@Id
<<<<<<< HEAD
	@GeneratedValue
=======
	@GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 1c30e4a (Reinitialized repo after .git folder removal)
	private Long id;
	private String borrowerName;
	private String address;
	private String mobileNo;
	private String email;
}
