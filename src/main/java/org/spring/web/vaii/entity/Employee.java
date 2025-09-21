package org.spring.web.vaii.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.web.vaii.EmployeeRole;
import org.spring.web.vaii.entity.statistics.Statistics;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statistics_id", nullable = false)
    private Statistics statistics;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EmployeeRole role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_schedule_id", nullable = false)
    private WorkSchedule workSchedule;
}
