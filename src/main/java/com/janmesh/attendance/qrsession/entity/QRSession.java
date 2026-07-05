package com.janmesh.attendance.qrsession.entity;

import com.janmesh.attendance.attendance.entity.Attendance;
import com.janmesh.attendance.classroom.entity.Classroom;
import com.janmesh.attendance.common.entity.BaseEntity;
import com.janmesh.attendance.course.entity.Course;
import com.janmesh.attendance.faculty.entity.Faculty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "qr_sessions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QRSession extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = true)
    private Classroom classroom;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false, unique = true, length = 255)
    private String qrToken;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "qrSession", cascade = CascadeType.ALL)
    private List<Attendance> attendances;
}
