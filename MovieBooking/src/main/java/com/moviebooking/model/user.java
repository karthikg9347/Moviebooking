package com.moviebooking.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nimbusds.oauth2.sdk.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class user {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true, nullable=false)
    private String email;
	@Column(nullable=false)
	private String password;
    private String fullName;
    private String phoneNumber;
    @Column(nullable=false)
    private boolean enabled=false;
    private String verificationToken;
    private LocalDateTime verificationTokenExpiry;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String resetPasswordToken;
    private LocalDateTime resetPasswordTokenExpiry;
    @Column(name="google_id")
    private String googleId;
    private String profilePicture;
    @OneToMany (mappedBy="user", cascade=CascadeType.ALL)
   	private List<Booking> bookings=new ArrayList<>();   
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() {
	 createdAt=LocalDateTime.now();
     updatedAt=LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
     updatedAt=LocalDateTime.now();
    }   
}
