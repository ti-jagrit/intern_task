package com.jtim.borrower;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue
	private Long id;
	private String borrowerName;
	private String address;
	private String mobileNo;
	private String email;
}
