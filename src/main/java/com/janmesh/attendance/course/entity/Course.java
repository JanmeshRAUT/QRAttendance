package com.janmesh.attendance.course.entity;

import com.janmesh.attendance.common.entity.BaseEntity;
import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.common.enums.Department;
import com.janmesh.attendance.faculty.entity.Faculty;
import com.janmesh.attendance.faculty.entity.Faculty;
import com.janmesh.attendance.enrollment.entity.Enrollment;
import com.janmesh.attendance.qrsession.entity.QRSession;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
@Builder
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String courseCode;

    @Column(nullable = false, length = 100)
    private String courseName;

    @Column(length = 100)
    private String courseDescription;

    @Column(nullable = false)
    private Integer credits;

    @Column(nullable = false)
    private Integer semester;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AccountStatus accountStatus=AccountStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<QRSession> qrSessions;
}
