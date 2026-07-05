package com.janmesh.attendance.faculty.entity;

import com.janmesh.attendance.common.entity.BaseEntity;
import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.common.enums.Department;
import com.janmesh.attendance.common.enums.Gender;
import com.janmesh.attendance.user.entity.User;
import com.janmesh.attendance.course.entity.Course;
import com.janmesh.attendance.qrsession.entity.QRSession;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "faculties")
public class Faculty extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String employeeId;

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

    @Column(nullable = false)
    private Integer joiningYear;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Course> courses;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<QRSession> qrSessions;

}
