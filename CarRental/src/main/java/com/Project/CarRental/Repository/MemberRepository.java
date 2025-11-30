package com.Project.CarRental.Repository;
import com.Project.CarRental.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}