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
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statistics_id")
    private Statistics statistics;

    @Enumerated(EnumType.STRING)
    private EmployeeRole role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "work_schedule_id")
    private WorkSchedule workSchedule;
}
