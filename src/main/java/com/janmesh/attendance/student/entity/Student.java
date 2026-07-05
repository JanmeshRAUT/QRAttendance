package com.janmesh.attendance.student.entity;

import com.janmesh.attendance.common.entity.BaseEntity;
import com.janmesh.attendance.common.enums.AcademicYear;
import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.common.enums.Department;
import com.janmesh.attendance.common.enums.Gender;
import com.janmesh.attendance.user.entity.User;
import com.janmesh.attendance.enrollment.entity.Enrollment;
import com.janmesh.attendance.attendance.entity.Attendance;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "students")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true,length = 50)
    private String rollNumber;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(length = 15)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcademicYear academicYear;

    @Column(nullable = false, length = 10)
    private String division;


    @Column(nullable = false)
    private Integer admissionYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AccountStatus accountStatus=AccountStatus.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Attendance> attendances;

}
