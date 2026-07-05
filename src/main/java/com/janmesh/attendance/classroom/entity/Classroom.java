package com.janmesh.attendance.classroom.entity;

import com.janmesh.attendance.common.entity.BaseEntity;
import com.janmesh.attendance.common.enums.AccountStatus;
import com.janmesh.attendance.common.enums.RoomType;
import com.janmesh.attendance.qrsession.entity.QRSession;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "classrooms")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Classroom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String roomNumber;

    @Column(nullable = false, length = 100)
    private String building;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<QRSession> qrSessions;
}